package tsuteto.tofu.item;

import net.minecraft.item.Item;

public class ItemCraftingDurability extends TcItem implements ICraftingDurability
{
    public ItemCraftingDurability(int par1)
    {
        super(par1);
        this.setMaxDamage(9);
        this.setMaxStackSize(1);
    }

    @Override
    public Item getEmptyItem()
    {
        return Item.glassBottle;
    }

}
