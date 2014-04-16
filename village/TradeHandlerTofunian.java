package tsuteto.tofu.village;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import tsuteto.tofu.entity.EntityTofunian;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.util.ModLog;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class TradeHandlerTofunian implements IVillageTradeHandler
{
    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
    {
        /*
         * MerchantRecipe(ItemStack, ItemStack, ItemStack) : want1, want2, sell
         * MerchantRecipe(ItemStack, ItemStack) : want, sell
         * MerchantRecipe(ItemStack, Item) : want, sell
         */

        // Buy
        int tofu = 3 + random.nextInt(18);

        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.tofuMomen, tofu),
                new ItemStack(TcItem.tofuKinu, tofu))
        );

        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.tofuKinu, tofu),
                new ItemStack(TcItem.tofuMomen, tofu))
        );

        if (villager instanceof EntityTofunian)
        {
            EntityTofunian tofunian = (EntityTofunian)villager;
            ModLog.debug("friendship=%d", tofunian.getFriendship());
            if (tofunian.getFriendship() > 255)
            {
                recipeList.addToListWithCheck(new MerchantRecipe(
                        new ItemStack(TcItem.tofuMomen, 24 + random.nextInt(11)),
                        new ItemStack(TcItem.tofuDiamond, 1))
                );
            }
        }
    }

}
