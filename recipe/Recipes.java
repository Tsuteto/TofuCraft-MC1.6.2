package tsuteto.tofu.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.entity.TcEntity;
import tsuteto.tofu.item.ItemBottleSoyMilk;
import tsuteto.tofu.item.ItemFoodSet;
import tsuteto.tofu.item.ItemFoodSetStick;
import tsuteto.tofu.item.ItemGelatin;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.util.ItemUtils;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
    private static final int DMG_WILDCARD = 0x7fff;
    private static final String bambooMod_wheatRice = "wheatRice";

    public static void unifyOreDicItems()
    {
        ItemUtils.uniteOreItems("salt", "itemSalt", "oreSalt", "Salt");
        ItemUtils.uniteOreItems("tofuMomen", "tofu", "Tofu", "itemTofu");
        ItemUtils.uniteOreItems("blockTofuMomen", "blockTofu");
    }

    public static void register()
    {
        /*
         * Smelting
         */
        FurnaceRecipes frecipes = FurnaceRecipes.smelting();

        frecipes.addSmelting(TcItem.tofuKinu.itemID, new ItemStack(TcItem.tofuGrilled), 0.2f);
        frecipes.addSmelting(TcItem.tofuMomen.itemID, new ItemStack(TcItem.tofuGrilled), 0.2f);
        frecipes.addSmelting(TcBlock.tofuKinu.blockID, new ItemStack(TcBlock.tofuGrilled), 0.8f);
        frecipes.addSmelting(TcBlock.tofuMomen.blockID, new ItemStack(TcBlock.tofuGrilled), 0.8f);
        frecipes.addSmelting(TcItem.edamame.itemID, new ItemStack(TcItem.edamameBoiled, 12), 0.5f);
        frecipes.addSmelting(TcItem.soybeans.itemID, new ItemStack(TcItem.soybeansParched), 0.2f);
        frecipes.addSmelting(TcItem.starchRaw.itemID, new ItemStack(TcItem.starch), 0.5f);
        frecipes.addSmelting(TcBlock.tcLog.blockID, new ItemStack(Item.coal, 1, 1), 0.5f);
        frecipes.addSmelting(TcItem.gelatin.itemID, 1, new ItemStack(TcItem.gelatin, 1, 0), 0.5f);
        frecipes.addSmelting(TcItem.materials.itemID, ItemTcMaterials.tofuHamburgRaw.id,
                new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuHamburg.id), 0.8f);
        frecipes.addSmelting(TcItem.foodSet.itemID, ItemFoodSet.tofufishRow.id,
                new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofufishCooked.id), 1.5f);

        /*
         * Crafting
         */

        // Soymilk
        addSharedRecipe(new ItemStack(TcItem.bucketSoymilk),
                "S",
                "B",
                Character.valueOf('S'), TcOreDic.soybeans,
                Character.valueOf('B'), Item.bucketEmpty
        );

        // Soymilk and Okara
        addSharedRecipe(new ItemStack(TcItem.bucketSoymilk),
                "S",
                "F",
                "B",
                Character.valueOf('S'), TcOreDic.soybeans,
                Character.valueOf('F'), TcOreDic.filterCloth,
                Character.valueOf('B'), Item.bucketEmpty
        );

        // Hell Soymilk
        addSharedRecipe(new ItemStack(TcItem.bucketSoymilkHell),
                "S",
                "B",
                Character.valueOf('S'), TcOreDic.soybeansHell,
                Character.valueOf('B'), Item.bucketEmpty
        );

        // Tofu Blocks
        addSharedRecipe(new ItemStack(TcBlock.tofuMomen, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuKinu
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuMomen, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuMomen
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuIshi, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuIshi
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuMetal, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuMetal
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuGrilled, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuGrilled
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuDried, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuDried
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuFriedPouch, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuFriedPouch
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuFried, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuFried
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuEgg, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuEgg
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuAnnin, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuAnnin
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuSesame, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuSesame
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuZunda, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuZunda
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuStrawberry, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuStrawberry
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuHell, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuHell
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuGlow, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuGlow
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuMiso, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuMiso
        );

        addSharedRecipe(new ItemStack(TcBlock.tofuDiamond, 1),
                "TT",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuDiamond
        );

        // Fried Tofu Pouch
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuFriedPouch),
                TcOreDic.tofuKinu,
                TcOreDic.starch,
                new ItemStack(TcItem.soyOil, 1, 0x7fff)
        );

        addShapelessSharedRecipe(new ItemStack(TcItem.tofuFriedPouch),
                TcOreDic.tofuMomen,
                TcOreDic.starch,
                new ItemStack(TcItem.soyOil, 1, 0x7fff)
        );

        // Fried Tofu
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuFried),
                TcOreDic.tofuKinu,
                new ItemStack(TcItem.soyOil, 1, 0x7fff)
        );

        addShapelessSharedRecipe(new ItemStack(TcItem.tofuFried),
                TcOreDic.tofuMomen,
                new ItemStack(TcItem.soyOil, 1, 0x7fff)
        );

        // Egg Tofu
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuEgg, 4),
                Item.egg,
                TcOreDic.dashi
        );

        // Salt Furnace
        ModLoader.addRecipe(new ItemStack(TcBlock.saltFurnaceIdle),
                "@ @",
                "@ @",
                "@@@",
               Character.valueOf('@'), Block.cobblestone
        );

        // Nigari
        addShapelessSharedRecipe(new ItemStack(TcItem.nigari),
                TcOreDic.salt,
                Item.glassBottle
        );

        // Golden Salt
        addShapelessSharedRecipe(new ItemStack(TcItem.goldenSalt),
                TcOreDic.salt,
                Item.goldNugget,
                Item.goldNugget,
                Item.goldNugget
        );

        // Salty Melon
        addShapelessSharedRecipe(new ItemStack(TcItem.saltyMelon),
                TcOreDic.salt,
                Item.melon
        );

        // Tasty Chicken Stew
        addShapelessSharedRecipe(new ItemStack(TcItem.tastyStew),
                Block.mushroomBrown,
                Block.mushroomRed,
                Item.chickenCooked,
                TcOreDic.salt,
                Item.bucketMilk,
                Item.wheat,
                Item.bowlEmpty
        );

        // Tasty Pork Stew
        addShapelessSharedRecipe(new ItemStack(TcItem.tastyStew),
                Block.mushroomBrown,
                Block.mushroomRed,
                Item.porkCooked,
                TcOreDic.salt,
                Item.bucketMilk,
                Item.wheat,
                Item.bowlEmpty
        );

        // Tasty Beef Stew
        addShapelessSharedRecipe(new ItemStack(TcItem.tastyBeefStew),
                Block.mushroomBrown,
                Block.mushroomRed,
                Item.beefCooked,
                TcOreDic.salt,
                Item.bucketMilk,
                Item.wheat,
                Item.bowlEmpty
        );

        // Tofu Cake
        addSharedRecipe(new ItemStack(TcItem.tofuCake),
                "TTT",
                "SES",
                "WWW",
                Character.valueOf('T'), TcOreDic.tofuKinu,
                Character.valueOf('S'), Item.sugar,
                Character.valueOf('E'), Item.egg,
                Character.valueOf('W'), Item.wheat);

        // Yudofu
        addShapelessSharedRecipe(new ItemStack(TcItem.yudofu),
                TcOreDic.tofuKinu,
                Item.potion, // Water bottle
                Item.bowlEmpty
        );
        addShapelessSharedRecipe(new ItemStack(TcItem.yudofu),
                TcOreDic.tofuMomen,
                Item.potion, // Water bottle
                Item.bowlEmpty
        );

        // TTT Burger
        addSharedRecipe(new ItemStack(TcItem.tttBurger),
                " B ",
                "TTT",
                " B ",
                Character.valueOf('B'), Item.bread,
                Character.valueOf('T'), TcOreDic.tofuFriedPouch
        );

        // Kouji Base
        addShapelessSharedRecipe(new ItemStack(TcItem.koujiBase),
                Item.wheat,
                TcOreDic.soybeans
        );

        // Morijio
        addSharedRecipe(new ItemStack(TcItem.morijio, 4),
                "D",
                "S",
                "B",
                Character.valueOf('D'), Item.diamond,
                Character.valueOf('S'), TcOreDic.salt,
                Character.valueOf('B'), Item.bowlEmpty
        );

        // Rappa
        ModLoader.addRecipe(new ItemStack(TcItem.bugle),
                "I  ",
                "III",
                Character.valueOf('I'), Item.ingotIron
        );

        // Miso Soup
        addShapelessSharedRecipe(new ItemStack(TcItem.misoSoup),
                TcOreDic.miso,
                TcOreDic.tofuKinu,
                TcOreDic.dashi,
                Item.bowlEmpty
        );

        // Miso Soup
        addShapelessSharedRecipe(new ItemStack(TcItem.misoSoup),
                TcOreDic.miso,
                TcOreDic.tofuMomen,
                TcOreDic.dashi,
                Item.bowlEmpty
        );

        // Miso Dengaku
        addSharedRecipe(new ItemStack(TcItem.misoDengaku),
                "M",
                "T",
                "|",
                Character.valueOf('M'), TcOreDic.miso,
                Character.valueOf('T'), TcOreDic.tofuMomen,
                Character.valueOf('|'), Item.stick
        );

        // Zunda
        addSharedRecipe(new ItemStack(TcItem.zunda),
                "EEE",
                "ESE",
                "EEE",
                Character.valueOf('E'), TcOreDic.edamameBoiled,
                Character.valueOf('S'), Item.sugar
        );

        // Zunda Manju
        addSharedRecipe(new ItemStack(TcItem.zundaManju, 2),
                " Z ",
                "WWW",
                Character.valueOf('Z'), TcOreDic.zunda,
                Character.valueOf('W'), Item.wheat
        );

        // Kinako Manju
        addSharedRecipe(new ItemStack(TcItem.kinakoManju, 2),
                " K ",
                "WWW",
                Character.valueOf('K'), TcOreDic.kinako,
                Character.valueOf('W'), Item.wheat
        );

        // Barrel
        ModLoader.addRecipe(new ItemStack(TcItem.barrelEmpty),
                "W W",
                "===",
                "WWW",
                Character.valueOf('W'), Block.planks,
                Character.valueOf('='), Item.reed);

        // Miso Barrel
        addSharedRecipe(new ItemStack(TcItem.barrelMiso),
                "SSS",
                "MMM",
                " B ",
                Character.valueOf('S'), TcOreDic.salt,
                Character.valueOf('M'), TcOreDic.kouji,
                Character.valueOf('B'), TcItem.barrelEmpty);

        // Nikujaga
        addShapelessSharedRecipe(new ItemStack(TcItem.nikujaga),
                Item.bowlEmpty,
                Item.beefCooked,
                Item.potato,
                Item.carrot,
                TcOreDic.bottleSoySauce,
                TcOreDic.dashi,
                Item.sugar
        );

        addShapelessSharedRecipe(new ItemStack(TcItem.nikujaga),
                Item.bowlEmpty,
                Item.porkCooked,
                Item.potato,
                Item.carrot,
                TcOreDic.bottleSoySauce,
                TcOreDic.dashi,
                Item.sugar
        );

        // Soy Sauce Bucket
        // This is impossible because a soy sauce bottle always takes 1 point of damage when crafting

        // Soy Sauce Bottle
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoySauce, 1, 0),
                Item.glassBottle,
                TcOreDic.bucketSoySauce
        );

        // Kinako
        addShapelessSharedRecipe(new ItemStack(TcItem.kinako),
                TcOreDic.soybeansParched,
                Item.sugar
        );

        // Kinako manju
        addSharedRecipe(new ItemStack(TcItem.kinakoManju, 2),
                " K ",
                "BBB",
                Character.valueOf('K'), TcOreDic.kinako,
                Character.valueOf('B'), Item.wheat
        );

        // Agedashi Tofu
        addShapelessSharedRecipe(new ItemStack(TcItem.agedashiTofu),
                TcOreDic.dashi,
                TcOreDic.bottleSoySauce,
                TcOreDic.tofuFriedPouch,
                Item.bowlEmpty
        );

        // Soy Milk Bottle (Plain)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvPlain.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk
        );

        // Soy Milk Bottle (Kinako)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvKinako.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk,
                TcOreDic.kinako
        );

        // Soy Milk Bottle (Cocoa)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvCocoa.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk,
                new ItemStack(Item.dyePowder, 1, 3)
        );

        // Soy Milk Bottle (Zunda)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvZunda.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk,
                TcOreDic.zunda
        );

        // Soy Milk Bottle (Apple)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvApple.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk,
                Item.appleRed
        );

        // Soy Milk Bottle (Pumpkin)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvPumpkin.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk,
                Block.pumpkin
        );

        // Soy Milk Bottle (Ramune)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvRamune.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk,
                new ItemStack(Item.dyePowder, 1, 12)
        );
        
        // Soy Milk Bottle (Strawberry)
        addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvStrawberry.id),
                Item.glassBottle,
                TcOreDic.bucketSoymilk,
                TcItem.strawberryJam
        );

        // Dashi
        ModLoader.addShapelessRecipe(new ItemStack(TcItem.dashi, 1, 0),
                Item.glassBottle,
                Item.bucketWater,
                Item.fishCooked
        );

        // Soy Oil
        addShapelessSharedRecipe(new ItemStack(TcItem.soyOil),
                TcOreDic.defattingPotion,
                Item.glassBottle,
                TcOreDic.soybeans
        );

        // Koya Tofu fukumeni
        addShapelessSharedRecipe(new ItemStack(TcItem.fukumeni, 8),
                TcOreDic.tofuDried,
                TcOreDic.dashi,
                TcOreDic.salt
        );

        // Koya Tofu Stew
        addShapelessSharedRecipe(new ItemStack(TcItem.koyadofuStew),
                TcOreDic.tofuDried,
                TcOreDic.dashi,
                Block.mushroomBrown,
                TcOreDic.bottleSoySauce,
                Item.bowlEmpty
        );

        // Natto Bed Block
        addSharedRecipe(new ItemStack(TcBlock.nattoBed),
                "BBB",
                "BBB",
                "WWW",
                Character.valueOf('B'), TcOreDic.soybeans,
                Character.valueOf('W'), Item.wheat
        );

        // Natto Hiyayakko
        addShapelessSharedRecipe(new ItemStack(TcItem.nattoHiyayakko),
                TcOreDic.tofuKinu,
                TcItem.natto,
                TcItem.leek,
                new ItemStack(TcItem.bottleSoySauce, 1, 0x7fff),
                Item.bowlEmpty
        );

        // Minced Potato
        ModLoader.addShapelessRecipe(new ItemStack(TcItem.mincedPotato),
                Item.potato
        );

        // Raw Starch
        addShapelessSharedRecipe(new ItemStack(TcItem.starchRaw),
                TcOreDic.mincedPotato,
                TcOreDic.filterCloth
        );

        // Apricot Seed
        addShapelessSharedRecipe(new ItemStack(TcItem.apricotSeed),
                TcOreDic.apricot
        );

        // Kyoninso
        addShapelessSharedRecipe(new ItemStack(TcItem.kyoninso),
                TcOreDic.apricotSeed
        );

        // Annin Tofu
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuAnnin, 4),
                TcOreDic.gelatin,
                Item.sugar,
                Item.bucketMilk,
                TcOreDic.kyoninso
        );

        // Sesame Tofu
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuSesame, 4),
                TcOreDic.starch,
                TcOreDic.sesame,
                TcOreDic.dashi,
                TcOreDic.salt
        );

        // Zunda Tofu
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuZunda, 4),
                TcOreDic.starch,
                TcOreDic.zunda,
                TcOreDic.dashi,
                TcOreDic.salt
        );

        // Log -> planks
//        addSharedRecipe(new ItemStack(Block.planks, 4, 0),
//                "L",
//                Character.valueOf('L'), TcOreDic.logApricot
//        );

        ModLoader.addRecipe(new ItemStack(Block.planks, 4, 0),
                "L",
                Character.valueOf('L'), new ItemStack(TcBlock.tcLog, 1, 0)
        );

        // Filter Cloth
        ModLoader.addRecipe(new ItemStack(TcItem.filterCloth, 32),
                "WWW",
                Character.valueOf('W'), new ItemStack(Block.cloth, 1, 0x7fff)
        );

        // Okara Stick
        addSharedRecipe(new ItemStack(TcItem.okaraStick, 3),
                "O",
                "E",
                "W",
                Character.valueOf('O'), TcOreDic.okara,
                Character.valueOf('E'), Item.egg,
                Character.valueOf('W'), Item.wheat
        );

        // Zundama
        addSharedRecipe(new ItemStack(TcItem.zundama),
                " Z ",
                "ZGZ",
                " Z ",
                Character.valueOf('Z'), TcOreDic.zunda,
                Character.valueOf('G'), Item.glowstone
        );

        // Zunda Bow
        ModLoader.addRecipe(new ItemStack(TcItem.zundaBow),
                "O O",
                " B ",
                "O O",
                Character.valueOf('O'), TcItem.zundama,
                Character.valueOf('B'), Item.bow
        );

        // Zunda Arrow
        addShapelessSharedRecipe(new ItemStack(TcItem.zundaArrow),
                TcOreDic.zunda,
                Item.arrow
        );

        // Gelatin Base
        ModLoader.addShapelessRecipe(new ItemStack(TcItem.gelatin, 1, ItemGelatin.Materials.gelatinRaw.ordinal()),
                Item.leather,
                Item.bone
        );

        // Fukumame (Initial)
        addSharedRecipe(new ItemStack(TcItem.fukumame),
                "sss",
                "sss",
                " B ",
                Character.valueOf('s'), TcOreDic.soybeansParched,
                Character.valueOf('B'), Item.bowlEmpty
        );

        // Fukumame (Refill)
        addSharedRecipe(new ItemStack(TcItem.fukumame),
                "sss",
                "sss",
                " M ",
                Character.valueOf('s'), TcOreDic.soybeansParched,
                Character.valueOf('M'), new ItemStack(TcItem.fukumame, 1, DMG_WILDCARD)
        );

        // Tofu Chikuwa
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuChikuwa.id),
                TcOreDic.tofuMomen,
                Item.fishCooked
        );
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuChikuwa.id),
                TcOreDic.tofufishCooked
        );

        // Oage
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 4, ItemFoodSet.oage.id),
                new ItemStack(TcBlock.tofuSingleSlab1, 1, 1),
                TcOreDic.soyOil
        );

        // Natto -> Natto Block
        addSharedRecipe(new ItemStack(TcBlock.natto, 1),
                "NNN",
                "NNN",
                "NNN",
                Character.valueOf('N'), TcOreDic.natto
        );

        // Natto Block -> Item
        addSharedRecipe(new ItemStack(TcItem.natto, 9),
                "N",
                Character.valueOf('N'), TcOreDic.blockNatto
        );

        // Salt -> Salt Block
        addSharedRecipe(new ItemStack(TcBlock.salt, 1),
                "SSS",
                "SSS",
                "SSS",
                Character.valueOf('S'), TcOreDic.salt
        );

        // Salt Block -> Item
        addSharedRecipe(new ItemStack(TcItem.salt, 9),
                "S",
                Character.valueOf('S'), TcOreDic.blockSalt
        );

        // Moyashiitame
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.sproutSaute.id),
                TcOreDic.soyOil,
                TcOreDic.bottleSoySauce,
                TcOreDic.salt,
                TcOreDic.sprouts,
                Item.bowlEmpty
        );

        // Moyashi no ohitashi
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.moyashiOhitashi.id),
                TcOreDic.bottleSoySauce,
                TcOreDic.dashi,
                TcOreDic.sprouts,
                Item.bowlEmpty
        );

        // Goheimochi
        addSharedRecipe(new ItemStack(TcItem.foodSetStick, 1, ItemFoodSetStick.goheimochi.id),
                "M",
                "O",
                "S",
                Character.valueOf('M'), TcOreDic.miso,
                Character.valueOf('O'), TcOreDic.onigiri,
                Character.valueOf('S'), Item.stick
        );

        // Tofu Scoop
        ModLoader.addRecipe(new ItemStack(TcItem.tofuScoop),
                "N",
                "S",
                "S",
                Character.valueOf('N'), Block.fenceIron,
                Character.valueOf('S'), Item.stick
        );

        // Onigiri
        ModLoader.addRecipe(new ItemStack(TcItem.foodSet, 2, ItemFoodSet.onigiri.id),
                " W ",
                "WWW",
                Character.valueOf('W'), new ItemStack(Item.wheat)
        );

        // Salty Onigiri
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.onigiriSalt.id),
                TcOreDic.salt,
                TcOreDic.onigiri
        );

        // Miso yakionigiri
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.yakionigiriMiso.id),
                TcOreDic.miso,
                TcOreDic.onigiri
        );

        // Shoyu yakionigiri
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.yakionigiriShoyu.id),
                TcOreDic.bottleSoySauce,
                TcOreDic.onigiri
        );

        // Mabodofu (momen)
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.mabodofu.id),
                TcOreDic.tofuMomen,
                TcOreDic.starch,
                Item.porkRaw,
                TcOreDic.doubanjiang,
                TcOreDic.bottleSoySauce,
                Item.bowlEmpty
        );

        // Mabodofu (kinu)
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.mabodofu.id),
                TcOreDic.tofuKinu,
                TcOreDic.starch,
                Item.porkRaw,
                TcOreDic.doubanjiang,
                TcOreDic.bottleSoySauce,
                Item.bowlEmpty
        );

        // Strawberry Tofu
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuStrawberry),
                TcOreDic.tofuMomen,
                TcOreDic.strawberryJam
        );

        // Tofu Creeper Egg
        addSharedRecipe(new ItemStack(Item.monsterPlacer, 1, TcEntity.entityIdTofuCreeper),
                " G ",
                "GTG",
                " G ",
                Character.valueOf('G'), TcOreDic.tofuGem,
                Character.valueOf('T'), Block.tnt
        );

        // Tofu Diamond (Nuggets <-> piece)
        addSharedRecipe(new ItemStack(TcItem.tofuDiamond),
                "NNN",
                "NNN",
                "NNN",
                Character.valueOf('N'), TcOreDic.tofuDiamondNugget);

        addSharedRecipe(new ItemStack(TcItem.materials, 9, ItemTcMaterials.tofuDiamondNugget.id),
                "D",
                Character.valueOf('D'), TcOreDic.tofuDiamond);

        // Tofu Slime Radar
        addSharedRecipe(new ItemStack(TcItem.tofuRadar, 1, TcItem.tofuRadar.getMaxDamage() + 1),
                "SR",
                "TT",
                Character.valueOf('T'), TcOreDic.tofuMetal,
                Character.valueOf('S'), Item.slimeBall,
                Character.valueOf('R'), Item.redstone
        );

        // Tofu Slime Radar (Charge)
        addShapelessSharedRecipe(new ItemStack(TcItem.tofuRadar, 1, 0),
                new ItemStack(TcItem.tofuRadar, 1, DMG_WILDCARD),
                TcOreDic.tofuGem
        );

        // Negi Hiyayakko
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.hiyayakko.id),
                TcOreDic.tofuKinu,
                TcItem.leek,
                new ItemStack(TcItem.bottleSoySauce, 1, DMG_WILDCARD),
                Item.bowlEmpty
        );

        // Natto rice
        addShapelessSharedRecipe(new ItemStack(TcItem.riceNatto),
                TcOreDic.bottleSoySauce,
                TcOreDic.natto,
                bambooMod_wheatRice
        );

        // Natto rice with leek
        addShapelessSharedRecipe(new ItemStack(TcItem.riceNattoLeek),
                TcOreDic.leek,
                TcOreDic.bottleSoySauce,
                TcOreDic.natto,
                bambooMod_wheatRice
        );

        // Tofu Rice
        addShapelessSharedRecipe(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.riceTofu.id),
                TcOreDic.tofuKinu,
                TcItem.leek,
                TcOreDic.bottleSoySauce,
                bambooMod_wheatRice
        );
        
        // Tofu Hamburg (Raw)
        addSharedRecipe(new ItemStack(TcItem.materials, 3, ItemTcMaterials.tofuHamburgRaw.id),
                "TTT",
                "MPB",
                "TTT",
                'T', TcOreDic.tofuMomen,
                'P', Item.porkRaw,
                'M', TcOreDic.miso,
                'B', Item.bread
        );

        // Tofu Cookie
        addSharedRecipe(new ItemStack(TcItem.foodSet, 8, ItemFoodSet.tofuCookie.id),
                "WTW",
                'T', TcOreDic.tofuMomen,
                'W', Item.wheat
        );
        
        // Miso Barrel
        addSharedRecipe(TcItem.barrelMisoTofu,
                "MMM",
                "TTT",
                " B ",
                'M', TcOreDic.miso,
                'T', TcOreDic.tofuMomen,
                'B', TcOreDic.barrel);

        // Inari
        addSharedRecipe(new ItemStack(TcItem.foodSet, 2, ItemFoodSet.inari.id),
                "#O*",
                " R ",
                '#', TcOreDic.bottleSoySauce,
                '*', Item.sugar,
                'O', TcOreDic.oage,
                'R', TcOreDic.onigiri
        );
        
        // Glowtofu Barrel
        addSharedRecipe(TcItem.barrelGlowtofu,
                "GGG",
                "TTT",
                " B ",
                'G', Item.glowstone,
                'T', TcOreDic.tofuMomen,
                'B', TcOreDic.barrel
        );
        
        // TF Machine Case
        addSharedRecipe(TcBlock.tfMachineCase,
                "TTT",
                "T T",
                "TTT",
                'T', TcOreDic.blockTofuMetal
        );
        
        addSharedRecipe(new ItemStack(TcBlock.tofuMetal, 8),
                "C",
                'C', TcOreDic.tfMachineCase
        );
        
        // TF Capacitor
        addSharedRecipe(new ItemStack(TcItem.materials, 1, ItemTcMaterials.tfCapacitor.id),
                " T ",
                "RGR",
                " T ",
                'T', TcItem.tofuMetal,
                'G', new ItemStack(TcItem.materials, 1, ItemTcMaterials.tofuGem.id),
                'R', Item.redstone
        );
        
        // TF Storage
        addSharedRecipe(TcBlock.tfStorageIdle,
                "CCC",
                "GMG",
                'C', new ItemStack(TcItem.materials, 1, ItemTcMaterials.tfCapacitor.id),
                'M', TcBlock.tfMachineCase,
                'G', Block.glass
        );
        
        /*
         * Stairs Blocks
         */
        addStairsRecipes(TcOreDic.tofuKinu, TcBlock.tofuStairsKinu);
        addStairsRecipes(TcOreDic.tofuMomen, TcBlock.tofuStairsMomen);
        addStairsRecipes(TcOreDic.tofuIshi, TcBlock.tofuStairsIshi);
        addStairsRecipes(TcOreDic.tofuMetal, TcBlock.tofuStairsMetal);
        addStairsRecipes(TcOreDic.tofuGrilled, TcBlock.tofuStairsGrilled);
        addStairsRecipes(TcOreDic.tofuDried, TcBlock.tofuStairsDried);
        addStairsRecipes(TcOreDic.tofuFriedPouch, TcBlock.tofuStairsFriedPouch);
        addStairsRecipes(TcOreDic.tofuFried, TcBlock.tofuStairsFried);
        addStairsRecipes(TcOreDic.tofuEgg, TcBlock.tofuStairsEgg);
        addStairsRecipes(TcOreDic.tofuAnnin, TcBlock.tofuStairsAnnin);
        addStairsRecipes(TcOreDic.tofuSesame, TcBlock.tofuStairsSesame);
        addStairsRecipes(TcOreDic.tofuZunda, TcBlock.tofuStairsZunda);
        addStairsRecipes(TcOreDic.tofuStrawberry, TcBlock.tofuStairsStrawberry);
        addStairsRecipes(TcOreDic.tofuHell, TcBlock.tofuStairsHell);
        addStairsRecipes(TcOreDic.tofuGlow, TcBlock.tofuStairsGlow);
        addStairsRecipes(TcOreDic.tofuDiamond, TcBlock.tofuStairsDiamond);
        addStairsRecipes(TcOreDic.tofuMiso, TcBlock.tofuStairsMiso);
        
        /*
         * Half Slabs
         */
        addSlabRecipe(TcOreDic.tofuKinu, TcBlock.tofuSingleSlab1, 0);
        addSlabRecipe(TcOreDic.tofuMomen, TcBlock.tofuSingleSlab1, 1);
        addSlabRecipe(TcOreDic.tofuIshi, TcBlock.tofuSingleSlab1, 2);
        addSlabRecipe(TcOreDic.tofuMetal, TcBlock.tofuSingleSlab1, 3);
        addSlabRecipe(TcOreDic.tofuGrilled, TcBlock.tofuSingleSlabFaces, 0);
        addSlabRecipe(TcOreDic.tofuDried, TcBlock.tofuSingleSlab1, 5);
        addSlabRecipe(TcOreDic.tofuFriedPouch, TcBlock.tofuSingleSlab1, 6);
        addSlabRecipe(TcOreDic.tofuFried, TcBlock.tofuSingleSlab1, 7);
        addSlabRecipe(TcOreDic.tofuEgg, TcBlock.tofuSingleSlab2, 0);
        addSlabRecipe(TcOreDic.tofuAnnin, TcBlock.tofuSingleSlab2, 1);
        addSlabRecipe(TcOreDic.tofuSesame, TcBlock.tofuSingleSlab2, 2);
        addSlabRecipe(TcOreDic.tofuZunda, TcBlock.tofuSingleSlab2, 3);
        addSlabRecipe(TcOreDic.tofuStrawberry, TcBlock.tofuSingleSlab2, 4);
        addSlabRecipe(TcOreDic.tofuHell, TcBlock.tofuSingleSlab2, 5);
        addSlabRecipe(TcOreDic.tofuGlow, TcBlock.tofuSingleSlabGlow, 0);
        addSlabRecipe(TcOreDic.tofuDiamond, TcBlock.tofuSingleSlab2, 7);
        addSlabRecipe(TcOreDic.tofuMiso, TcBlock.tofuSingleSlab3, 0);
        
        // Converting recipes
        addSharedRecipe(TcBlock.tofuSingleSlabFaces,
                "S",
                'S', new ItemStack(TcBlock.tofuSingleSlab1, 1, 4)
        );
        addSharedRecipe(TcBlock.tofuSingleSlabGlow,
                "S",
                'S', new ItemStack(TcBlock.tofuSingleSlab2, 1, 6)
        );
        

        // Armors
        addCombatItemRecipes(TcOreDic.blockTofuKinu, TcItem.armorKinu, TcItem.swordKinu);
        addCombatItemRecipes(TcOreDic.blockTofuMomen, TcItem.armorMomen, TcItem.swordMomen);
        addCombatItemRecipes(TcOreDic.blockTofuIshi, TcItem.armorSolid, TcItem.swordSolid);
        addCombatItemRecipes(TcOreDic.blockTofuMetal, TcItem.armorMetal, TcItem.swordMetal);
        addCombatItemRecipes(TcOreDic.blockTofuDiamond, TcItem.armorDiamond, TcItem.swordDiamond);

        // Tools
        addToolItemRecipes(TcOreDic.blockTofuKinu, TcItem.toolKinu);
        addToolItemRecipes(TcOreDic.blockTofuMomen, TcItem.toolMomen);
        addToolItemRecipes(TcOreDic.blockTofuIshi, TcItem.toolSolid);
        addToolItemRecipes(TcOreDic.blockTofuMetal, TcItem.toolMetal);
        addToolItemRecipes(TcOreDic.blockTofuDiamond, TcItem.toolDiamond);
    }

    private static void addCombatItemRecipes(TcOreDic tofu, Item[] armors, Item sword)
    {
        addSharedRecipe(new ItemStack(armors[0]),
            "TTT",
            "T T",
            Character.valueOf('T'), tofu
        );
        addSharedRecipe(new ItemStack(armors[1]),
            "T T",
            "TTT",
            "TTT",
            Character.valueOf('T'), tofu
        );
        addSharedRecipe(new ItemStack(armors[2]),
            "TTT",
            "T T",
            "T T",
            Character.valueOf('T'), tofu
        );
        addSharedRecipe(new ItemStack(armors[3]),
            "T T",
            "T T",
            Character.valueOf('T'), tofu
        );
        addSharedRecipe(new ItemStack(sword),
            "T",
            "T",
            "|",
            Character.valueOf('T'), tofu,
            Character.valueOf('|'), Item.stick
        );
    }

    private static void addToolItemRecipes(TcOreDic tofu, Item[] tools)
    {
        addSharedRecipe(new ItemStack(tools[0]),
            "T",
            "|",
            "|",
            Character.valueOf('T'), tofu,
            Character.valueOf('|'), Item.stick
        );
        addSharedRecipe(new ItemStack(tools[1]),
            "TTT",
            " | ",
            " | ",
            Character.valueOf('T'), tofu,
            Character.valueOf('|'), Item.stick
        );
        addSharedRecipe(new ItemStack(tools[2]),
            "TT",
            "T|",
            " |",
            Character.valueOf('T'), tofu,
            Character.valueOf('|'), Item.stick
        );
    }

    private static void addStairsRecipes(TcOreDic tofu, Block stairs)
    {
        // Stairs Blocks
        addSharedRecipe(new ItemStack(stairs),
                "  T",
                " TT",
                "TTT",
                Character.valueOf('T'), tofu
        );
        // Stairs Blocks
        addSharedRecipe(new ItemStack(stairs),
                "T  ",
                "TT ",
                "TTT",
                Character.valueOf('T'), tofu
        );
    }

    private static void addSlabRecipe(TcOreDic tofu, Block slab, int meta)
    {
        addSharedRecipe(new ItemStack(slab, 1, meta),
                "TT",
                Character.valueOf('T'), tofu
        );
    }

    /*
     * === Facade methods for Forge Ore Dictionary ===
     */
    private static void addSharedRecipe(Block block, Object... recipe)
    {
        decodeDicRecipe(recipe);
        GameRegistry.addRecipe(new ShapedOreRecipe(block, recipe));
    }

    private static void addSharedRecipe(Item item, Object... recipe)
    {
        decodeDicRecipe(recipe);
        GameRegistry.addRecipe(new ShapedOreRecipe(item, recipe));
    }

    private static void addSharedRecipe(ItemStack is, Object... recipe)
    {
        decodeDicRecipe(recipe);
        GameRegistry.addRecipe(new ShapedOreRecipe(is, recipe));
    }

    private static void addShapelessSharedRecipe(Block block, Object... recipe)
    {
        decodeDicRecipe(recipe);
        GameRegistry.addRecipe(new ShapelessOreRecipe(block, recipe));
    }

    private static void addShapelessSharedRecipe(Item item, Object... recipe)
    {
        decodeDicRecipe(recipe);
        GameRegistry.addRecipe(new ShapelessOreRecipe(item, recipe));
    }

    private static void addShapelessSharedRecipe(ItemStack is, Object... recipe)
    {
        decodeDicRecipe(recipe);
        GameRegistry.addRecipe(new ShapelessOreRecipe(is, recipe));
    }

    private static void decodeDicRecipe(Object[] list)
    {
        for (int i = 0; i < list.length; i++)
        {
            Object item = list[i];
            if (item instanceof TcOreDic)
            {
                list[i] = ((TcOreDic)item).name();
            }
        }
    }



    /**
     * === External Mod Recipes ===
     */
    public static void registerExternalModRecipes()
    {
        if (TcItem.plantBall != null)
        {
            addShapelessSharedRecipe(new ItemStack(TcItem.plantBall, 1),
                    TcOreDic.okara,
                    TcOreDic.okara,
                    TcOreDic.okara,
                    TcOreDic.mincedPotato
            );
        }
        
        if (TcBlock.sakuraLeaves != null)
        {
            // Soy Milk Bottle (Sakura)
            addShapelessSharedRecipe(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvSakura.id),
                    Item.glassBottle,
                    TcOreDic.bucketSoymilk,
                    new ItemStack(TcBlock.sakuraLeaves, 1, 15)
            );
        }
    }
}
