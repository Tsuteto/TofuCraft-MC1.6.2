package tsuteto.tofu.item;

import tsuteto.tofu.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemReed;

public class ItemTcReed extends ItemReed {
	
	public ItemTcReed(int par1, Block par2Block) {
		super(par1, par2Block);
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}

}
