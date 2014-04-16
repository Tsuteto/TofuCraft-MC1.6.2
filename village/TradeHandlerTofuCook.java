package tsuteto.tofu.village;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.ItemBottleSoyMilk;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class TradeHandlerTofuCook implements IVillageTradeHandler
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
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.soybeans, 18 + random.nextInt(4)),
                new ItemStack(Item.emerald, 1))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcBlock.tofuMomen, 14 + random.nextInt(4)),
                new ItemStack(Item.emerald, 1))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.barrelEmpty, 6 + random.nextInt(4)),
                new ItemStack(Item.emerald, 1))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.edamame, 14 + random.nextInt(4)),
                new ItemStack(Item.emerald, 1))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.miso, 9 + random.nextInt(4)),
                new ItemStack(Item.emerald, 1))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.materials, 2 + random.nextInt(3), ItemTcMaterials.tofuGem.id),
                new ItemStack(Item.emerald, 1))
        );

        // Sell
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcBlock.tofuIshi, 17 + random.nextInt(8)))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcBlock.tofuFriedPouch, 10 + random.nextInt(4)))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcBlock.tofuFried, 12 + random.nextInt(4)))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcBlock.tofuEgg, 26 + random.nextInt(8)))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcItem.bottleSoymilk, 8 + random.nextInt(4), ItemBottleSoyMilk.flvApple.id))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcItem.bottleSoymilk, 8 + random.nextInt(4), ItemBottleSoyMilk.flvPumpkin.id))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcItem.bottleSoymilk, 8 + random.nextInt(4), ItemBottleSoyMilk.flvKinako.id))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 3),
                new ItemStack(TcBlock.tofuMetal, 2 + random.nextInt(2)))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 3),
                new ItemStack(TcItem.defattingPotion, 1))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcBlock.tofuDried, 8 + random.nextInt(4)))
        );
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(Item.emerald, 1),
                new ItemStack(TcItem.zundaManju, 9 + random.nextInt(4)))
        );

        // Exchange
        recipeList.addToListWithCheck(new MerchantRecipe(
                new ItemStack(TcItem.materials, 6, ItemTcMaterials.tofuGem.id),
                new ItemStack(TcItem.materials, 1, ItemTcMaterials.tofuDiamondNugget.id))
        );


    }

}
