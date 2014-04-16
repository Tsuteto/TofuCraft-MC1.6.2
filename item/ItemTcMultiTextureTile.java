package tsuteto.tofu.item;

import tsuteto.tofu.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemMultiTextureTile;

public class ItemTcMultiTextureTile extends ItemMultiTextureTile {

	public ItemTcMultiTextureTile(int par1, Block par2Block, String[] par3ArrayOfStr) {
		super(par1, par2Block, par3ArrayOfStr);
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}
}
