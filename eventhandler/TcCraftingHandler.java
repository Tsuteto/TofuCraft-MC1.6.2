package tsuteto.tofu.eventhandler;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.achievement.TcAchievementMgr.Key;
import tsuteto.tofu.item.ICraftingDurability;
import tsuteto.tofu.item.INonDurabilityTool;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.common.ICraftingHandler;

public class TcCraftingHandler implements ICraftingHandler
{
    private static ArrayList<Integer> durabilityItemRegistry = new ArrayList<Integer>();
    private static ArrayList<Integer> itemsMadeOfBottleContent = new ArrayList<Integer>();

    public static void registerDurabilityItem(Item item)
    {
        if (!(item instanceof ICraftingDurability))
        {
            throw new RuntimeException("Not ICraftingDurability implemented!");
        }
        durabilityItemRegistry.add(item.itemID);
    }

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
    {
        boolean isFiltering = false;

        // Crafting mode
        for (int var2 = 0; var2 < craftMatrix.getSizeInventory(); ++var2)
        {
            ItemStack var3 = craftMatrix.getStackInSlot(var2);
            if (var3 != null)
            {
                if (var3.itemID == TcItem.filterCloth.itemID)
                {
                    isFiltering = true;
                }
            }
        }
        
        if (this.itemsMadeOfBottleContent.contains(item.itemID))
        {
            for (int var2 = 0; var2 < craftMatrix.getSizeInventory(); ++var2)
            {
                ItemStack var3 = craftMatrix.getStackInSlot(var2);
                if (var3.itemID == Item.potion.itemID)
                {
                    if (var3.stackSize == 1)
                    {
                        craftMatrix.setInventorySlotContents(var2, new ItemStack(Item.glassBottle, 2));
                    }
                    else
                    {
                        player.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle, 1));
                    }
                }
            }
        }

        for (int var2 = 0; var2 < craftMatrix.getSizeInventory(); ++var2)
        {
            ItemStack var3 = craftMatrix.getStackInSlot(var2);
            if (var3 != null)
            {                
                if (this.durabilityItemRegistry.contains(var3.itemID))
                {
                    ItemStack var4 = new ItemStack(var3.getItem(), var3.stackSize + 1, var3.getItemDamage() + 1);
                    if(var3.getItem().isDamageable() && var3.getItemDamage() >= var3.getMaxDamage())
                    {
                        Item emptyItem = ((ICraftingDurability)var3.getItem()).getEmptyItem();
                        if (var3.stackSize == 1)
                        {
                            var4 = new ItemStack(emptyItem, 2);
                        }
                        else
                        {
                            player.inventory.addItemStackToInventory(new ItemStack(emptyItem, 1));
                        }
                    }
                    craftMatrix.setInventorySlotContents(var2, var4);
                }

                if (var3.getItem() instanceof INonDurabilityTool)
                {
                    ItemStack var4 = new ItemStack(var3.getItem(), var3.stackSize + 1);
                    craftMatrix.setInventorySlotContents(var2, var4);
                }

                if (isFiltering)
                {
                    if (var3.itemID == TcItem.soybeans.itemID)
                    {
                        if (var3.stackSize == 1)
                        {
                            craftMatrix.setInventorySlotContents(var2, new ItemStack(TcItem.okara, 2));
                        }
                        else
                        {
                            player.inventory.addItemStackToInventory(new ItemStack(TcItem.okara, 1));
                        }
                        TcAchievementMgr.achieve(player, Key.okara);
                    }
                }
            }
        }

        TcAchievementMgr.achieveCraftingItem(item, player);
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item)
    {
        TcAchievementMgr.achieveSmeltingItem(item, player);
    }

}
