package tsuteto.tofu.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMisoTofuBarrel extends BlockFermentable
{
	private Icon iconTop;
	private Icon iconBottom;
	
    public BlockMisoTofuBarrel(int par1, Material par3Material)
    {
        super(par1, par3Material);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return TcItem.barrelEmpty.itemID;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int par1, int par2)
    {
        if (par1 == 1) // top
        {
            return this.iconTop;
        }
        else if (par1 == 0) // bottom
        {
            return this.iconBottom;
        }
        else
        {
            return this.blockIcon;
        }
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int metadata = this.getFermStep(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
        return metadata == 7 ? 0x885511 : 0xffd399;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(this.textureName + "_side");
        this.iconTop = par1IconRegister.registerIcon("tofucraft:barrel_top");
        this.iconBottom = par1IconRegister.registerIcon("tofucraft:barrel_bottom");
    }

    @Override
    public void addFermentedItem(List list)
    {
        list.add(new ItemStack(TcItem.tofuMiso, 3));
        list.add(new ItemStack(TcItem.miso, 2));
    }

    @Override
    public void addIngredients(List list)
    {
        list.add(new ItemStack(TcItem.tofuMomen, 3));
        list.add(new ItemStack(TcItem.miso, 3));
    }

    @Override
    public boolean checkEnvironment(World world, int x, int y, int z)
    {
        return true;
    }
}
