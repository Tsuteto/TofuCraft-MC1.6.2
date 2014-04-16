package tsuteto.tofu.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBlockBarrel extends TcItem
{
    private final Block barrelBlock;

    public ItemBlockBarrel(int par1, Block block)
    {
        super(par1);
        this.barrelBlock = block;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 == 0)
        {
            return false;
        }
        else if (!par3World.getBlockMaterial(par4, par5, par6).isSolid())
        {
            return false;
        }
        else
        {
            if (par7 == 1)
            {
                ++par5;
            }
    
            if (par7 == 2)
            {
                --par6;
            }
    
            if (par7 == 3)
            {
                ++par6;
            }
    
            if (par7 == 4)
            {
                --par4;
            }
    
            if (par7 == 5)
            {
                ++par4;
            }

            if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
            {
                return false;
            }
            else if (!barrelBlock.canPlaceBlockAt(par3World, par4, par5, par6))
            {
                return false;
            }
            else
            {
                par3World.setBlock(par4, par5, par6, barrelBlock.blockID);
                --par1ItemStack.stackSize;
            }
            return true;
        }
    }

}
