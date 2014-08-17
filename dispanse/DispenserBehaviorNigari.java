package tsuteto.tofu.dispanse;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tsuteto.tofu.block.TcBlock;

public class DispenserBehaviorNigari extends BehaviorDefaultDispenseItem
{
    private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

    /**
     * Dispense the specified stack, play the dispense sound and spawn particles.
     */
    @Override
    public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack)
    {
        EnumFacing enumfacing = BlockDispenser.getFacing(par1IBlockSource.getBlockMetadata()); // getFacing
        World world = par1IBlockSource.getWorld();
        int i = par1IBlockSource.getXInt() + enumfacing.getFrontOffsetX();
        int j = par1IBlockSource.getYInt() + enumfacing.getFrontOffsetY();
        int k = par1IBlockSource.getZInt() + enumfacing.getFrontOffsetZ();
        int block = world.getBlockId(i, j, k);
        //Material material = world.getBlock(i, j, k).getMaterial();
        //int l = world.getBlockMetadata(i, j, k);
        Item item;

        if (block == TcBlock.soymilkStill.blockID)
        {
            world.setBlock(i, j, k, TcBlock.tofuKinu.blockID);
            item = Item.glassBottle;

            if (--par2ItemStack.stackSize == 0)
            {
                par2ItemStack.itemID = item.itemID;
                par2ItemStack.stackSize = 1;
            }
            else if (((TileEntityDispenser)par1IBlockSource.getBlockTileEntity()).addItem(new ItemStack(item)) < 0)
            {
                this.defaultDispenserItemBehavior.dispense(par1IBlockSource, new ItemStack(item));
            }
        }
        else
        {
            super.dispenseStack(par1IBlockSource, par2ItemStack);
        }

        return par2ItemStack;
    }
}
