package tsuteto.tofu.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import tsuteto.tofu.block.BlockTofu;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTofuBlock extends ItemBlock
{
    private final BlockTofu blockTofu;

    public ItemTofuBlock(int par1)
    {
        super(par1);
        this.blockTofu = (BlockTofu)Block.blocksList[this.getBlockID()];
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }
}
