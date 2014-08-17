package tsuteto.tofu.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.achievement.TcAchievementMgr.Key;
import tsuteto.tofu.material.TcMaterial;
import tsuteto.tofu.util.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTofuStep extends BlockTofuStepBase
{
    /** The list of the types of step blocks. */
    public static final String[] blockStepTypes1 = new String[] {"kinu", "momen", "ishi", "metal", "grilled", "dried", "pouchFried", "fried"};
    public static final boolean[] typesObsolete1 = new boolean[] {false, false, false, false, true, false, false, false};
    public static final String[] blockStepTypes2 = new String[] {"egg", "annin", "sesame", "zunda", "strawberry", "hell", "glow", "diamond"};
    public static final boolean[] typesObsolete2 = new boolean[] {false, false, false, false, false, false, true, false};
    public static final String[] blockStepTypes3 = new String[] {"yamauni"};
    public static final boolean[] typesObsolete3 = new boolean[] {false};
    
    private final String[] blockStepTypes;
    private final boolean[] typesObsolete;
    private Icon[] icons;

    public BlockTofuStep(int par1, boolean par2, String[] types, boolean[] obsolete)
    {
        super(par1, par2, TcMaterial.tofu);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.blockStepTypes = types;
        this.typesObsolete = obsolete;
        this.setLightOpacity(0);
        this.setTickRandomly(true);
    }
    
    private boolean isFragile(int meta)
    {
        return (meta & 7) == 0 && (this.blockID == TcBlock.tofuSingleSlab1.blockID || this.blockID == TcBlock.tofuDoubleSlab1.blockID);
    }
    
    /**
     * Block's chance to react to an entity falling on it.
     */
    @Override
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
    {
        int meta = par1World.getBlockMetadata(par2, par3, par4);
        
        if (!par1World.isRemote && isFragile(meta))
        {
            if (par6 > 0.5F)
            {
                if (!(par5Entity instanceof EntityPlayer) && !par1World.getGameRules().getGameRuleBooleanValue("mobGriefing"))
                {
                    return;
                }
                this.onEntityWeightedBlock(par1World, par5Entity);
            }
        }
    }

    private void onEntityWeightedBlock(World world, Entity entity)
    {
        int i = MathHelper.floor_double(entity.boundingBox.minX + 0.001D);
        int j = MathHelper.floor_double(entity.boundingBox.minY + 0.001D);
        int k = MathHelper.floor_double(entity.boundingBox.minZ + 0.001D);
        int l = MathHelper.floor_double(entity.boundingBox.maxX - 0.001D);
        int i1 = MathHelper.floor_double(entity.boundingBox.maxY - 0.001D);
        int j1 = MathHelper.floor_double(entity.boundingBox.maxZ - 0.001D);

        if (entity.worldObj.checkChunksExist(i, j, k, l, i1, j1))
        {
            for (int k1 = i; k1 <= l; ++k1)
            {
                for (int l1 = j; l1 <= i1; ++l1)
                {
                    for (int i2 = k; i2 <= j1; ++i2)
                    {
                        int bx = k1;
                        int by = l1 - 1;
                        int bz = i2;
                        if (world.getBlockId(bx, by, bz) == this.blockID)
                        {
                            this.collapseBlock(entity, world, bx, by, bz);
                        }
                    }
                }
            }
        }
    }

    private void collapseBlock(Entity entity, World world, int x, int y, int z)
    {
        dropBlockAsItem(world, x, y, z, 0, 0);
        world.setBlock(x, y, z, 0);

        if (entity instanceof EntityPlayer)
        {
            TcAchievementMgr.achieve((EntityPlayer)entity, Key.tofuMental);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);
        int meta = par1World.getBlockMetadata(par2, par3, par4);

        if (isFragile(meta) && this.isDoubleSlab)
        {
            Block weightBlock = Block.blocksList[par1World.getBlockId(par2, par3 + 1, par4)];

            if (weightBlock != null)
            {
               if (weightBlock.blockMaterial == Material.rock || weightBlock.blockMaterial == Material.iron)
               {
                   dropBlockAsItem(par1World, par2, par3, par4, 0, 0);
                   par1World.setBlock(par2, par3, par4, 0);
               }
            }
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public Icon getIcon(int par1, int par2)
    {
        if (par2 >= this.icons.length)
        {
            par2 = 0;
        }
        return icons[par2 & 7];
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return isBlockSingleSlab(this.blockID) ? this.blockID
                : (this.blockID == TcBlock.tofuDoubleSlab1.blockID ? TcBlock.tofuSingleSlab1.blockID
                        : (this.blockID == TcBlock.tofuDoubleSlab2.blockID ? TcBlock.tofuSingleSlab2.blockID
                                : (this.blockID == TcBlock.tofuDoubleSlab3.blockID ? TcBlock.tofuSingleSlab3.blockID
                                        : TcBlock.tofuSingleSlab1.blockID)));
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 2, par1 & 7);
    }

    /**
     * Returns the slab block name with step type.
     */
    @Override
    public String getFullSlabName(int par1)
    {
        if (par1 < 0 || par1 >= blockStepTypes.length)
        {
            par1 = 0;
        }

        return super.getUnlocalizedName() + "." + blockStepTypes[par1] + (typesObsolete[par1] ? "_old" : "");
    }
    
    /**
     * Takes a block ID, returns true if it's the same as the ID for a stone or wooden single slab.
     */
    @Override
    protected boolean isBlockSingleSlab(int par0)
    {
        return par0 == TcBlock.tofuSingleSlab1.blockID || par0 == TcBlock.tofuSingleSlab2.blockID || par0 == TcBlock.tofuSingleSlab3.blockID;
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return isBlockSingleSlab(this.blockID) ? this.blockID
                : (this.blockID == TcBlock.tofuDoubleSlab1.blockID ? TcBlock.tofuSingleSlab1.blockID
                        : (this.blockID == TcBlock.tofuDoubleSlab2.blockID ? TcBlock.tofuSingleSlab2.blockID
                                : (this.blockID == TcBlock.tofuDoubleSlab3.blockID ? TcBlock.tofuSingleSlab3.blockID
                                        : TcBlock.tofuSingleSlab1.blockID)));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (isBlockSingleSlab(par1))
        {
            for (int var4 = 0; var4 < blockStepTypes.length; var4++)
            {
                if (!typesObsolete[var4])
                {
                    par3List.add(new ItemStack(par1, 1, var4));
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.icons = new Icon[blockStepTypes.length];
        for (int i = 0; i < blockStepTypes.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("tofucraft:blockTofu" + Utils.capitalize(blockStepTypes[i]));
        }
    }
}
