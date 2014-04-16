package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import tsuteto.tofu.util.Utils;

public class ItemTcSpade extends ItemSpade
{

    public ItemTcSpade(int par1, EnumToolMaterial par2EnumToolMaterial)
    {
        super(par1, par2EnumToolMaterial);
        if (this.damageVsEntity < 0)
        {
            this.damageVsEntity = 0;
        }
    }

    @Override
    public CreativeTabs[] getCreativeTabs() {
        return Utils.getCreativeTabs(this);
    }
}
