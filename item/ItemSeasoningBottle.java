package tsuteto.tofu.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSeasoningBottle extends ItemColoredBottle implements ICraftingDurability
{
    public ItemSeasoningBottle(int par1, int color)
    {
        super(par1, color);
        this.setMaxDamage(9);
        this.setMaxStackSize(1);
    }

    @Override
    public Item getEmptyItem()
    {
        return Item.glassBottle;
    }
}
