package tsuteto.tofu.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSoybeanHell extends BlockFlower
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public BlockSoybeanHell(int par1)
    {
        super(par1);
        this.setTickRandomly(true);
        float f = 0.5F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
        this.setCreativeTab(null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
    }

    @Override
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        int blockId = par1World.getBlockId(par2, par3 - 1, par4);
        return blockId == Block.slowSand.blockID;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);

        if (l < 7 && par5Random.nextInt(4) == 0)
        {
            ++l;
            par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
        }

        super.updateTick(par1World, par2, par3, par4, par5Random);
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 0;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int count = 1;

        if (metadata == 7)
        {
            for (int n = 0; n < 3 + fortune; n++)
            {
                if (world.rand.nextInt(15) <= 7)
                {
                    count++;
                }
            }
        }

        for (int i = 0; i < count; i++)
        {
            ret.add(new ItemStack(TcItem.soybeansHell));
        }

        return ret;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return TcItem.soybeansHell.itemID;
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return EnumPlantType.Nether;
    }

    @Override
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 > 7)
        {
            par2 = 7;
        }

        return this.icons[par2 >> 1];
    }

    @Override
    public int getRenderType()
    {
        return 6;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[5];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("tofucraft:soybeanHell_" + i);
        }
    }
}
