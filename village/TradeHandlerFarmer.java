package tsuteto.tofu.village;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.TradeEntry;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class TradeHandlerFarmer implements IVillageTradeHandler
{
    @Override
    public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
    {
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcItem.sesame, 4 + random.nextInt(4)))
        );
        
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcItem.strawberryJam, 1))
        );

    }

}
