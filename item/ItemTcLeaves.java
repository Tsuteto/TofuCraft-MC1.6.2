package tsuteto.tofu.item;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import tsuteto.tofu.block.BlockTcLeaves;
import tsuteto.tofu.block.TcBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTcLeaves extends ItemTcBlock
{
    public ItemTcLeaves(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int par1)
    {
        return par1 | 4;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    @Override
    public Icon getIconFromDamage(int par1)
    {
        return TcBlock.tcLeaves.getIcon(0, par1);
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int var2 = par1ItemStack.getItemDamage();

        if (var2 < 0 || var2 >= BlockTcLeaves.LEAF_TYPES.length)
        {
            var2 = 0;
        }

        return super.getUnlocalizedName() + "." + BlockTcLeaves.LEAF_TYPES[var2];
    }

}
