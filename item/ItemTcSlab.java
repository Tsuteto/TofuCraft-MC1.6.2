package tsuteto.tofu.item;

import tsuteto.tofu.util.Utils;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSlab;

public class ItemTcSlab extends ItemSlab {

	public ItemTcSlab(int par1, BlockHalfSlab par2BlockHalfSlab,
			BlockHalfSlab par3BlockHalfSlab, boolean par4) {
		super(par1, par2BlockHalfSlab, par3BlockHalfSlab, par4);
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}
}
