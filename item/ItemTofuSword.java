package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;
import tsuteto.tofu.util.Utils;

public class ItemTofuSword extends ItemSword
{
    public ItemTofuSword(int id, EnumToolMaterial material)
    {
        super(id, material);
    }

    @Override
    public CreativeTabs[] getCreativeTabs() {
        return Utils.getCreativeTabs(this);
    }
}
