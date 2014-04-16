package tsuteto.tofu;

import net.minecraft.creativetab.CreativeTabs;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

final class CreativeTabTofuCraft extends CreativeTabs {
	
	CreativeTabTofuCraft(String par2Str)
    {
        super(par2Str);
    }

    @Override
    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return TcItem.tofuMomen.itemID;
    }

}
