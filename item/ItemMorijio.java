package tsuteto.tofu.item;

import java.util.List;

import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.block.tileentity.TileEntityMorijio;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSkull;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMorijio extends TcItem
{

    public ItemMorijio(int par1)
    {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setMaxDamage(0);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
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
            else if (!TcBlock.morijio.canPlaceBlockAt(par3World, par4, par5, par6))
            {
                return false;
            }
            else
            {
                par3World.setBlock(par4, par5, par6, TcBlock.morijio.blockID, par7, 3);
                int var11 = 0;

                if (par7 == 1)
                {
                    var11 = MathHelper.floor_double((double)(par2EntityPlayer.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
                }

                TileEntity var12 = par3World.getBlockTileEntity(par4, par5, par6);

                if (var12 != null && var12 instanceof TileEntityMorijio)
                {
                    ((TileEntityMorijio)var12).setRotation(var11);
                }

                --par1ItemStack.stackSize;
                return true;
            }
        }
    }
}
