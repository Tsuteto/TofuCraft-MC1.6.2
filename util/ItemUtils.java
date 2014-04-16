package tsuteto.tofu.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.collect.Lists;

/**
 * Utilities for items
 */
public class ItemUtils
{
    /**
     * Unites items registered with each name in OreDictionary into a name
     * @param name
     * @param targets
     */
    public static void uniteOreItems(String name, String... targets)
    {
        List<ItemStack> itemsRegistered = Lists.newArrayList();

        for (String target : targets)
        {
            List<ItemStack> stacks = OreDictionary.getOres(target);
            for (ItemStack stack : stacks)
            {
                if (!containsItemStackList(stack, itemsRegistered))
                {
                    OreDictionary.registerOre(name, stack);
                    itemsRegistered.add(stack);
                }
            }
        }
    }

    public static ItemStack getItemStack(Object in)
    {
        if (in instanceof Item)
        {
            return new ItemStack((Item)in);
        }
        else if (in instanceof Block)
        {
            return new ItemStack((Block)in);
        }
        else if (in instanceof ItemStack)
        {
            return (ItemStack)in;
        }
        else
        {
            return null;
        }
    }

    public static boolean containsItemStackList(ItemStack stack, List<ItemStack> list)
    {
        for (ItemStack entry : list)
        {
            if (entry.isItemEqual(stack))
            {
                return true;
            }
        }
        return false;
    }
}
