package tsuteto.tofu.block;

import tsuteto.tofu.item.TcItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTofuCake extends BlockCake
{
    @SideOnly(Side.CLIENT)
    private Icon iconTop;
    @SideOnly(Side.CLIENT)
    private Icon iconBottom;
    @SideOnly(Side.CLIENT)
    private Icon iconInner;
    
    protected BlockTofuCake(int par1)
    {
        super(par1);
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return TcItem.tofuCake.itemID;
    }
    
    @Override
    public Block disableStats()
    {
        return super.disableStats();
    }
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.iconTop : (par1 == 0 ? this.iconBottom : (par2 > 0 && par1 == 4 ? this.iconInner : this.blockIcon));
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("tofucraft:tofuCake_side");
        this.iconInner = par1IconRegister.registerIcon("tofucraft:tofuCake_inner");
        this.iconTop = par1IconRegister.registerIcon("tofucraft:tofuCake_top");
        this.iconBottom = par1IconRegister.registerIcon("tofucraft:tofuCake_bottom");
    }
}
