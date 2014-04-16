package tsuteto.tofu.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemDefattingPotion extends ItemColoredBottle implements INonDurabilityTool
{
    public ItemDefattingPotion(int par1, int color)
    {
        super(par1, color);
    }

    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }
    
    
}
