package tsuteto.tofu.dispanse;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.TcItem;

public class DispenserBehaviorTcEmptyBucket extends BehaviorDefaultDispenseItem
{
    private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

    /**
     * Dispense the specified stack, play the dispense sound and spawn particles.
     */
    @Override
    public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
    {
        EnumFacing enumfacing = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata());
        World world = par1IBlockSource.getWorld();
        int i = par1IBlockSource.getXInt() + enumfacing.getFrontOffsetX();
        int j = par1IBlockSource.getYInt() + enumfacing.getFrontOffsetY();
        int k = par1IBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
        int id = world.getBlockId(i, j, k);
        Material material = world.getBlockMaterial(i, j, k);
        int l = world.getBlockMetadata(i, j, k);
        Item item;

        if ((id == TcBlock.soymilkStill.blockID) && l == 0)
        {
            item = TcItem.bucketSoymilk;
        }
        else if ((id == TcBlock.soymilkHellStill.blockID) && l == 0)
        {
            item = TcItem.bucketSoymilkHell;
        }
        else if ((id == TcBlock.soySauceStill.blockID) && l == 0)
        {
            item = TcItem.bucketSoySauce;
        }
        else if (Material.water.equals(material) && l == 0)
        {
            item = Item.bucketWater;
        }
        else
        {
            if (!Material.lava.equals(material) || l != 0)
            {
                return super.dispenseStack(par1IBlockSource, par2ItemStack);
            }

            item = Item.bucketLava;
        }

        world.setBlockToAir(i, j, k);

        if (--par2ItemStack.stackSize == 0)
        {
            par2ItemStack.itemID = item.itemID;
            par2ItemStack.stackSize = 1;
        }
        else if (((TileEntityDispenser)par1IBlockSource.getBlockTileEntity()).addItem(new ItemStack(item)) < 0)
        {
            this.defaultDispenserItemBehavior.dispense(par1IBlockSource, new ItemStack(item));
        }

        return par2ItemStack;
    }
}
