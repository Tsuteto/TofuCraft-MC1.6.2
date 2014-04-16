package tsuteto.tofu.util;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsuteto.tofu.Settings;
import tsuteto.tofu.TofuCraftCore;

import com.google.common.base.Strings;

/**
 * Utility Methods
 *
 * @author Tsuteto
 *
 */
public class Utils
{
	/**
	 * Get CreativeTabs list of a specific item/block
	 * @param item
	 * @return
	 */
	public static CreativeTabs[] getCreativeTabs(Item item)
	{
		switch (Settings.creativeTab) {
		case ORIGINAL:
			return new CreativeTabs[]{ TofuCraftCore.tabTofuCraft };
		case SORTED:
			return new CreativeTabs[]{ item.getCreativeTab() };
		case BOTH:
		default:
			return new CreativeTabs[]{ item.getCreativeTab(), TofuCraftCore.tabTofuCraft };
		}
	}

    /**
     * Get a specific item from IC2 by a field name, NOT unlocalized name
     * @param name
     * @return
     */
    public static Item getIc2Item(String name)
    {
        try
        {
            Class Ic2Items = Class.forName("ic2.core.Ic2Items");

            Object itemstack = Ic2Items.getField(name).get(null);

            if (itemstack instanceof ItemStack)
            {
                Item item = ((ItemStack) itemstack).getItem();
                return item;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            ModLog.log(Level.WARNING, e, "Failed to get IC2 item '" + name + "'");
            return null;
        }
    }

    /**
     * Get a specific item from external mods by unlocalized name
     * @param name
     * @return
     */
    public static Item getExternalModItem(String name)
    {
        String realName = "item." + name;
        for (Item item : Item.itemsList)
        {
            if (item != null && realName.equals(item.getUnlocalizedName()))
            {
                return item;
            }
        }
        ModLog.log(Level.WARNING, "Failed to get external mod item '" + name + "'");
        return null;
    }

    /**
     * Get a specific item from external mods by matching regex with unlocalized name
     * @param subregex
     * @return
     */
    public static Item getExternalModItemWithRegex(String subregex)
    {
        String regex = "item\\." + subregex;
        for (Item item : Item.itemsList)
        {
            if (item != null && item.getUnlocalizedName() != null
                    && item.getUnlocalizedName().matches(regex))
            {
                return item;
            }
        }
        ModLog.log(Level.WARNING, "Failed to get external mod item with /" + regex + "/");
        return null;
    }

    /**
     * Get a specific block from external mods by matching regex with unlocalized name
     * @param subregex
     * @return
     */
    public static Block getExternalModBlockWithRegex(String subregex)
    {
        String regex = "tile\\." + subregex;
        for (Block block : Block.blocksList)
        {
            if (block != null && block.getUnlocalizedName() != null
                    && block.getUnlocalizedName().matches(regex))
            {
                return block;
            }
        }
        ModLog.log(Level.WARNING, "Failed to get external mod block with /" + regex + "/");
        return null;
    }

    /**
     * Get Tofu World seed from the world seed
     *
     * @param world
     * @return
     */
    public static long getSeedForTofuWorld(World world)
    {
        long upper = (world.getSeed() & 31) << 60;
        long lower = world.getSeed() >>> 4;
        return upper | lower;
    }

    public static String capitalize(String s)
    {
        if (Strings.isNullOrEmpty(s))
        {
            return s;
        }
        return s.substring(0, 1).toUpperCase() + (s.length() >= 2 ? s.substring(1) : "");
    }
}
