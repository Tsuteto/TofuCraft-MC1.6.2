package tsuteto.tofu.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNattoBed extends BlockFermentable
{
	private Icon iconTop;
	
    public BlockNattoBed(int par1, int par2, Material par3Material)
    {
        super(par1, par3Material);
        this.setTickRandomly(true);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int par1, int par2)
    {
        if (par1 <= 1) // top/bottom
        {
            return this.iconTop;
        }
        else
        {
            return this.blockIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("tofucraft:nattoBed_side");
        this.iconTop = par1IconRegister.registerIcon("tofucraft:nattoBed_top");
    }
    
    @Override
    public void addFermentedItem(List list)
    {
        list.add(new ItemStack(TcItem.natto, 6));
    }

    @Override
    public void addIngredients(List list)
    {
        list.add(new ItemStack(TcItem.soybeans, 6));
        list.add(new ItemStack(Item.wheat, 3));
    }

    @Override
    public boolean checkEnvironment(World world, int x, int y, int z)
    {
        boolean isSnowEnabled = world.getBiomeGenForCoords(x, z).getEnableSnow();
        return isSnowEnabled && world.getBlockLightValue(x, y, z) >= 8 || !isSnowEnabled;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        if (p_149689_5_ instanceof EntityPlayer)
        {
            TcAchievementMgr.achieve((EntityPlayer)p_149689_5_, TcAchievementMgr.Key.nattoFarm);
        }
    }
}
