package tsuteto.tofu.achievement;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import tsuteto.tofu.achievement.TcAchievementMgr.Key;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.ItemBottleSoyMilk;
import tsuteto.tofu.item.ItemFoodSet;
import tsuteto.tofu.item.ItemFoodSetStick;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.util.ModLog;

/**
 * Loads achievements for TofuCraft
 */
public class TcAchievementList
{
    public static void load()
    {
        /*
         *   -F-E-D-C-B-A-9-8-7-6-5-4-3-2-1 0 1 2 3 4 5 6 7 8 9 A B C D E F X
         * -A - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
         * -9 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
         * -8 - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
         * -7 - - - - - - - - - A * - - - - - * A * A - - - - - - - - - - -
         * -6 - - - - - - - - - - A * * - * * A * * A - - - - - - - - - - -
         * -5 - - - - - - - - - A - - * - * - * A * A - - - - - - - - - - -
         * -4 - - - - - - - - - - - - * - A - - * * A - - * * A * A - - - -
         * -3 - - - - - A * A * - - - * - * - - - - - - - * - * * A - - - -
         * -2 - - - - - - A * A * A * A * A * A - A - * * A - - - - - - - -
         * -1 - - - - - - - A * - - - * - - * - - * - * - - - - - - - - - -
         *  0 - - - - - - - - - - - - * * * A * * A * A * A - - - - - - - -
         *  1 - - - - - - A * A * A * * B * * - - * A * * * * * A A A A - -
         *  2 - - - - - - - - - - - - * - B * - A * * A * A - - - - - - - -
         *  3 - - - - - - A * A * A * * B * * - * - - - - - - - - - - - - -
         *  4 - - - - - - - - - - - - * - * A - A - C - - D * D - - - - - -
         *  5 - - - - - - - - A * * - * - * * - - - - - - - - * - - - - - -
         *  6 - - - - - - A * A * A * * - * A - E - F - - - - D * * * - - -
         *  7 - - - - - - - - A * * - - B * - - * - - - - - - - - - * - - -
         *  8 - - - - - - - - - - - - - - * - - * - - - - - - - - - D - - -
         *  9 - - - - - - - - - - - B * * * - - * - - - - - - - - - * - - -
         *  A - - - - - - - - - - - - - - - - - * - - - - - - - D * * - - -
         *  B - - - - - - - - - - - - - - - - - E - - - - - - - - - * - - -
         *  C - - - - - - - - - - - - - - - - - - - - - - - - - - - D - - -
         *  Y
         */

        // === Soybeans -> Tofu
        TcAchievement.create(Key.soybeans, -3, 0, TcItem.soybeans, null)
                .setTriggerItemPickup(new ItemStack(TcItem.soybeans))
                .setTitle("Let's Begin TofuCraft!", "豆腐Craftを始めよう!")
                .setDesc("Get soybeans from tall grass", "草むらから大豆を手に入れよう")
                .registerAchievement();

        TcAchievement.create(Key.soymilk, 0, 0, TcItem.bucketSoymilk, Key.soybeans)
                .setTriggerItemCrafting(new ItemStack(TcItem.bucketSoymilk))
                .setTitle("Craft Soymilk", "豆乳を作る")
                .setDesc("Craft bucket and soybeans into soymilk", "バケツと大豆から豆乳を作ろう")
                .registerAchievement();

        TcAchievement.create(Key.tofu, 3, 0, TcItem.tofuKinu, Key.soymilk)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuKinu))
                .setTitle("Now You Got Tofu!", "お豆腐の完成です")
                .setDesc("Add nigari to soymilk and finish!", "豆乳ににがりを加えて出来上がり！")
                .registerAchievement();

        //[fixed]
        TcAchievement.create(Key.momenTofu, 5, 0, TcItem.tofuMomen, Key.tofu)
                .setTriggerItemCrafting(new ItemStack(TcBlock.tofuMomen))
                .setTitle("Momen Tofu", "木綿豆腐")
                .setDesc("Crash kinugoshi and pack again", "絹ごし豆腐を崩して固め直す")
                .registerAchievement();

        // [fixed]
        TcAchievement.create(Key.ishiTofu, 7, -2, TcItem.tofuMomen, Key.momenTofu)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuIshi))
                .setTitle("Drain is the point", "水抜きが秘訣")
                .setDesc("Make solid tofu by putting rock on tofu", "豆腐に石を乗せて石豆腐を作る")
                .registerAchievement();

        TcAchievement.create(Key.metalTofu, 9, -4, TcItem.tofuMomen, Key.ishiTofu)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuMetal))
                .setTitle("Perseverance for tofu", "豆腐の上にもなんとやら")
                .setDesc("Just wait more and more", "さらに待とう")
                .registerAchievement();

        // === Kinu Tofu ===
        // [fixed]
        TcAchievement.create(Key.tofuCake, 3, -2, TcItem.tofuCake, Key.tofu)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuCake))
                .setTitle("Tofu Cake never lies", "豆腐ケーキは嘘つかない")
                .setDesc("Kinugoshi tofu instead of milk!", "牛乳の代わりに絹ごし豆腐!")
                .registerAchievement();

        // When: Rock block or entityLiving put on tofu [fixed]
        TcAchievement.create(Key.tofuMental, 4, 1, TcBlock.tofuKinu, Key.tofu)
                .setTitle("Tofu the fragile mind", "ザ・豆腐メンタル")
                .setDesc("Jump on a kinugoshi tofu block", "絹ごし豆腐ブロックの上でジャンプ")
                .registerAchievement();

        // When: Scattering bone meal and leek comes up [fixed]
        TcAchievement.create(Key.leek, 2, 2, TcBlock.leek, Key.tofu)
                .setTitle("Leek came up", "葱が生えた")
                .setDesc("Scatter bone meal on tofu", "豆腐に骨粉をまいてみよう")
                .registerAchievement();

        TcAchievement.create(Key.hiyayakko, 2, 4, new ItemStack(TcItem.foodSet, 1, ItemFoodSet.hiyayakko.id), Key.leek)
                .setTriggerItemCrafting(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.hiyayakko.id))
                .setTitle("Simple tofu cooking", "冷や奴")
                .setDesc("Just put leek on tofu", "豆腐に葱を乗せる")
                .registerAchievement();

        // === Momen Tofu ===
        // [fixed]
        TcAchievement.create(Key.tofuGrilled, 7, 0, TcItem.tofuGrilled, Key.momenTofu)
                .setTriggerSmelting(new ItemStack(TcItem.tofuGrilled))
                .setTitle("Grilled Tofu", "焼き豆腐")
                .setDesc("Grill tofu with furnace", "竈で豆腐を焼いてみる")
                .registerAchievement();

        // [fixed]
        TcAchievement.create(Key.dengaku, 6, -1, new ItemStack(TcItem.misoDengaku), Key.momenTofu)
                .setTriggerItemCrafting(new ItemStack(TcItem.misoDengaku))
                .setTitle("Tofu Dengaku", "豆腐田楽")
                .setDesc("Craft miso, grilled tofu and stick", "味噌と焼き豆腐と棒をクラフト")
                .registerAchievement();

        // === Metal Tofu ===
        TcAchievement.create(Key.tofuWarrior, 11, -4, TcItem.swordMetal, Key.metalTofu)
                .setTriggerItemCrafting(new ItemStack(TcItem.swordMetal))
                .setTitle("Birth of tofu warrior", "豆腐戦士の誕生")
                .setDesc("Craft sword from Metal Tofu", "鋼豆腐から剣を作る")
                .registerAchievement();

        TcAchievement.create(Key.tofuMining, 11, -3, TcItem.toolMetal[1], Key.metalTofu)
                .setTriggerItemCrafting(new ItemStack(TcItem.toolMetal[1]))
                .setTitle("Time to mine with tofu!", "いざ、豆腐で採掘!")
                .setDesc("Craft pickaxe from Metal Tofu", "鋼豆腐からツルハシを作る")
                .registerAchievement();

        // === Koya-tofu ===
        TcAchievement.create(Key.koyatofu, 5, 2, TcItem.tofuDried, Key.tofu)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuDried))
                .setTitle("Tofu in cold places", "寒い地方の豆腐")
                .setDesc("Put and freeze tofu in a cold place", "寒い地方で豆腐を置いて凍らせる")
                .registerAchievement();

        TcAchievement.create(Key.koyaStew, 7, 2, TcItem.koyadofuStew, Key.koyatofu)
                .setTriggerItemCrafting(new ItemStack(TcItem.koyadofuStew))
                .setTitle("Stew with koya-tofu", "高野豆腐の煮物")
                .setDesc("A dish featuring koya-tofu and mashroom", "高野豆腐ときのこのお料理")
                .registerAchievement();

        // === Various tofu ===
        TcAchievement.create(Key.strawberryTofu, 10, 1, TcItem.tofuStrawberry, Key.momenTofu)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuStrawberry))
                .setTitle("Strawberry Tofu", "いちごとうふ")
                .setDesc("Craft strawberry jam and tofu", "いちごジャムと豆腐")
                .registerAchievement();

        TcAchievement.create(Key.sesameTofu, 5, 6, TcItem.tofuSesame, null)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuSesame))
                .setTitle("Sesame Tofu", "胡麻豆腐")
                .setDesc("Sesame and starch for devotion tofu", "胡麻と片栗粉で精進豆腐")
                .registerAchievement();

        TcAchievement.create(Key.eggTofu, 4, 7, TcItem.tofuEgg, null)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuEgg))
                .setTitle("Egg Tofu", "玉子豆腐")
                .setDesc("Egg and stock for fluffy tofu", "タマゴと出汁でふんわり豆腐")
                .registerAchievement();

        TcAchievement.create(Key.anninTofu, 5, 7, TcItem.tofuAnnin, null)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuAnnin))
                .setTitle("Annin-Tofu", "杏仁豆腐")
                .setDesc("Fluit-flavored milk tofu", "みんな大好きあまーい豆腐♪")
                .registerAchievement();

        TcAchievement.create(Key.misoTofu, 11, 1, TcItem.tofuMiso, Key.momenTofu)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuMiso))
                .setTitle("Miso Tofu", "味噌漬け豆腐")
                .setDesc("Fermenting with miso in a barrel", "樽で味噌に漬け込んで作る珍味")
                .registerAchievement();

        TcAchievement.create(Key.glowtofu, 12, 1, TcItem.tofuGlow, Key.momenTofu)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuGlow))
                .setTitle("Glowtofu", "蛍豆腐")
                .setDesc("Strange tofu by fermenting with glowstone in a barrel", "樽でグロウストーンに漬け込んで作る不思議豆腐")
                .registerAchievement();

        // === salt ===
        TcAchievement.create(Key.saltFurnace, -2, 1, TcBlock.saltFurnaceIdle, null)
                .setTriggerItemCrafting(new ItemStack(TcBlock.saltFurnaceIdle))
                .setTitle("Salt Furnace", "製塩かまど")
                .setDesc("Craft Salt Furnace", "製塩用かまどを作ろう")
                .registerAchievement();

        TcAchievement.create(Key.salt, -1, 2, TcItem.salt, Key.saltFurnace)
                .setTitle("Refine Salt", "塩を精製する")
                .setDesc("Refine salt with Salt Furnace", "製塩用かまどで塩を精製しよう")
                .registerAchievement();

        TcAchievement.create(Key.nigari, -2, 3, TcItem.nigari, Key.salt)
                .setTriggerItemCrafting(new ItemStack(TcItem.nigari))
                .setTitle("Craft Nigari", "にがりを作る")
                .setDesc("Salt and bottle for nigari!", "塩とガラス瓶でにがり！")
                .registerAchievement();

        TcAchievement.create(Key.kiyome, -2, 7, Item.glowstone, Key.salt)
                .setTriggerItemCrafting(new ItemStack(TcItem.goldenSalt))
                .setTitle("Luck goods #2", "清めの塩")
                .setDesc("Salt and glowstone", "塩とグロウストーン")
                .registerAchievement();

        TcAchievement.create(Key.morijio, -3, 9, TcItem.morijio, Key.salt)
                .setTriggerItemCrafting(new ItemStack(TcItem.morijio))
                .setTitle("Luck goods #3", "盛り塩")
                .setDesc("Salt and diamond", "塩とダイヤモンド")
                .registerAchievement();

        // === Moyashi ===
        // When: Soybeans are planted on wool
        TcAchievement.create(Key.sproutPlanting, -5, 1, Block.cloth, Key.soybeans)
                .setTitle("Sprout planting", "もやしを育てる")
                .setDesc("Plant soybeans on wool", "羊毛に大豆を蒔く")
                .registerAchievement();

        TcAchievement.create(Key.sprout, -7, 1, new ItemStack(TcItem.foodSet, 1, ItemFoodSet.sprouts.id), Key.sproutPlanting)
                .setTriggerItemPickup(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.sprouts.id))
                .setTitle("Sprouts sprouted", "もやしが萌えた")
                .setDesc("Mow sprouts sprouted well", "十分に萌えたもやしを刈る")
                .registerAchievement();

        TcAchievement.create(Key.sproutMeal, -9, 1, new ItemStack(TcItem.foodSet, 1, ItemFoodSet.sproutSaute.id), Key.sprout)
                .setTriggerItemCrafting(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.sproutSaute.id))
                .setTitle("Sprout cooking", "もやしの定番料理")
                .setDesc("Craft sprout, soysauce, soy oil and bowl", "もやし、醤油、大豆油、お椀でクラフト")
                .registerAchievement();

        // === Natto ===
        // [fixed]
        TcAchievement.create(Key.nattoMaking, -5, 3, Item.wheat, Key.soybeans)
                .setTriggerItemCrafting(new ItemStack(TcBlock.nattoBed))
                .setTitle("Natto making", "納豆作り")
                .setDesc("Craft soybeans and wheat into natto bed", "大豆と麦で納豆床を作る")
                .registerAchievement();

        // When: Natto bed is placed on the ground
        TcAchievement.create(Key.nattoFarm, -7, 3, TcBlock.nattoBed, Key.nattoMaking)
                .setTitle("Fermenting!", "発酵")
                .setDesc("Put natto bed on the ground. Torches for cold places", "納豆床を地面に置く。寒いところは松明を")
                .registerStat();

        TcAchievement.create(Key.natto, -9, 3, TcItem.natto, Key.nattoFarm)
                .setTriggerItemPickup(new ItemStack(TcItem.natto))
                .setTitle("Natto!", "納豆!")
                .setDesc("Put the block and done when the color changed", "ブロックを置いて色が変わったらできあがり")
                .registerAchievement();

        TcAchievement.create(Key.nattoRice, -11, 3, TcItem.riceNatto, Key.natto)
                .setTriggerItemCrafting(new ItemStack(TcItem.riceNatto))
                .setTitle("Natto Rice", "納豆ご飯")
                .setDesc("Natto and mugimeshi of the Bamboo Mod for natto rice!", "納豆と竹modの麦飯で納豆ご飯!")
                .registerAchievement();

        // === Okara ===
        // [fixed]
        TcAchievement.create(Key.okara, 0, 4, TcItem.okara, Key.soymilk)
                .setTitle("Okara, a spin-off of tofu", "おから")
                .setDesc("Craft soymilk using filter", "漉し布を使って豆乳を作る")
                .registerAchievement();

        // [1.5.4 fixed]
        TcAchievement.create(Key.okaraStick, 0, 6, TcItem.okaraStick, Key.okara)
                .setTriggerItemCrafting(new ItemStack(TcItem.okaraStick))
                .setTitle("Okara food for carrying", "おからの携帯食")
                .setDesc("Craft okara, an egg and wheat", "おからとタマゴと小麦で作る")
                .registerAchievement();

        // === Miso ===
        TcAchievement.create(Key.koujiBase, -1, -2, TcItem.koujiBase, Key.soybeans)
                .setTriggerItemCrafting(new ItemStack(TcItem.koujiBase))
                .setTitle("Begin the miso making", "味噌造りをはじめよう")
                .setDesc("Craft soybeans and wheat", "大豆と麦で麹のもとを作る")
                .registerAchievement();

        // When: Koujibase changes into kouji
        TcAchievement.create(Key.kouji, -1, -4, TcItem.kouji, Key.koujiBase)
                .setTitle("Done culturing!", "麹ができた!")
                .setDesc("Keep kouji base in your inventory", "豆麹のもとを持ち物に入れておく")
                .registerAchievement();

        TcAchievement.create(Key.misoBarrel, 1, -6, TcItem.barrelMiso, Key.kouji)
                .setTriggerItemCrafting(new ItemStack(TcItem.barrelMiso))
                .setTitle("Miso Barrel", "味噌樽")
                .setDesc("Wait more for steel", "塩と麹と樽で味噌樽")
                .registerAchievement();

        TcAchievement.create(Key.miso, 2, -7, TcItem.miso, Key.misoBarrel)
                .setTriggerItemPickup(new ItemStack(TcItem.miso))
                .setTitle("Miso", "発酵調味料 味噌")
                .setDesc("Put on a stone to ferment", "石を乗せて発酵させる")
                .registerAchievement();

        TcAchievement.create(Key.misoSoup, 4, -7, TcItem.misoSoup, Key.miso)
                .setTriggerItemCrafting(new ItemStack(TcItem.misoSoup))
                .setTitle("Miso soup, the Japanese heart", "日本の心 味噌汁")
                .setDesc("Miso, stock and tofu", "味噌と出汁とお豆腐")
                .registerAchievement();

        TcAchievement.create(Key.yakionigiriMiso, 4, -6, new ItemStack(TcItem.foodSet, 1, ItemFoodSet.yakionigiriMiso.id), Key.miso)
                .setTriggerItemCrafting(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.yakionigiriMiso.id))
                .setTitle("Tasty smell of roasted miso", "焼けた味噌の香ばしい味")
                .setDesc("Craft miso and onigiri", "味噌とおにぎりでクラフト")
                .registerAchievement();

        TcAchievement.create(Key.goheimochi, 6, -6, new ItemStack(TcItem.foodSetStick, 1, ItemFoodSetStick.goheimochi.id), Key.yakionigiriMiso)
                .setTriggerItemCrafting(new ItemStack(TcItem.foodSetStick, 1, ItemFoodSetStick.goheimochi.id))
                .setTitle("Goheimochi", "五平餅")
                .setDesc("Craft miso, onigiri and stick", "味噌とおにぎりと棒をクラフト")
                .registerAchievement();

        // === Soy Sauce ===
        // When: Soysauce comes out of miso barrel [fixed]
        TcAchievement.create(Key.soySauce, 2, -5, TcItem.bucketSoySauce, Key.misoBarrel)
                .setTitle("Soy Sauce", "万能調味料 醤油")
                .setDesc("Use a bucket to fermented barrels", "発酵した味噌樽にバケツを使う")
                .registerAchievement();

        TcAchievement.create(Key.nikujaga, 4, -5, TcItem.nikujaga, Key.soySauce)
                .setTriggerItemCrafting(new ItemStack(TcItem.nikujaga))
                .setTitle("Nikujaga Stew", "肉じゃが")
                .setDesc("Japanese stew based on English one", "シチューをイメージして作られた、お肉と芋の煮物")
                .registerAchievement();

        TcAchievement.create(Key.yakionigiriShoyu, 4, -4, new ItemStack(TcItem.foodSet, 1, ItemFoodSet.yakionigiriShoyu.id), Key.soySauce)
                .setTriggerItemCrafting(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.yakionigiriShoyu.id))
                .setTitle("Enjoy smell of roasted soya", "焼けた醤油の香りを楽しむ")
                .setDesc("Craft soy sauce and onigiri", "醤油とおにぎりでクラフト")
                .registerAchievement();

        // === Edamame -> Zunda ===
        TcAchievement.create(Key.edamame, -5, -2, TcItem.edamame, Key.soybeans)
                .setTriggerItemPickup(new ItemStack(TcItem.edamame))
                .setTitle("Young beans, Edamame", "枝豆")
                .setDesc("Harvest soybeans a step earlier", "大豆を一段階手前で収穫しよう")
                .registerAchievement();

        TcAchievement.create(Key.zunda, -7, -2, TcItem.zunda, Key.edamame)
                .setTriggerItemCrafting(new ItemStack(TcItem.zunda))
                .setTitle("Bean paste, Zunda", "ヽ(ず･ω･だ)ﾉ")
                .setDesc("Boil edamame and craft it", "枝豆を茹でてクラフトしよう")
                .registerAchievement();

        TcAchievement.create(Key.zundaTofu, -9, -2, TcItem.tofuZunda, Key.zunda)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuZunda))
                .setTitle("Zunda tofu", "ずんだ豆腐")
                .setDesc("Zunda-kneaded tasty tofu", "ずんだを練り込んだ風味豊かな豆腐")
                .registerAchievement();

        // === Irimame -> Kinako ===
        // [1.5.4 absolutely fixed!!!]
        TcAchievement.create(Key.irimame, -5, -6, TcItem.soybeansParched, Key.soybeans)
                .setTriggerSmelting(new ItemStack(TcItem.soybeansParched))
                .setTitle("Parch!", "炒り豆")
                .setDesc("Parch soybeans", "大豆を焼いてみよう")
                .registerAchievement();

        TcAchievement.create(Key.kinako, -6, -5, TcItem.kinako, Key.irimame)
                .setTriggerItemCrafting(new ItemStack(TcItem.kinako))
                .setTitle("Soybean powder, Kinako", "きな粉")
                .setDesc("Craft parched soybeans and sugar", "炒り豆と砂糖でクラフト")
                .registerAchievement();

        TcAchievement.create(Key.fukumame, -6, -7, TcItem.fukumame, Key.irimame)
                .setTriggerItemCrafting(new ItemStack(TcItem.fukumame))
                .setTitle("Luck goods #1", "大豆で厄除け")
                .setDesc("Parched beans into a bowl", "炒り豆をうつわに")
                .registerAchievement();

        // === Soymilk ===
        TcAchievement.create(Key.soymilkFv, 1, -2, new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvRamune.id), Key.soymilk)
                .setTriggerItemCrafting(new ItemStack(TcItem.bottleSoymilk, 1, ItemBottleSoyMilk.flvRamune.id))
                .setTitle("Ramune flavor!", "ラムネ味!")
                .setDesc("Craft soymilk bottle and light-blue dye for ramune!", "豆乳瓶と空色染料でラムネ味!")
                .registerAchievement();

        // === Zunda Arrow ===
        TcAchievement.create(Key.zundaBow, -8, -1, TcItem.zundaBow, Key.zunda)
                .setTriggerItemCrafting(new ItemStack(TcItem.zundaBow, 1, TriggerItem.DMG_WILDCARD))
                .setTitle("Graceful art of Tohoku girl", "東北乙女の必殺技")
                .setDesc("Craft Zunda Bow", "ずんだ弓を作ろう")
                .registerAchievement();

        TcAchievement.create(Key.zundaArrow, -8, -3, TcItem.zundaArrow, Key.zunda)
                .setTriggerItemCrafting(new ItemStack(TcItem.zundaArrow))
                .setTitle("Heartful arrow", "一矢、心込めて")
                .setDesc("Paint arrows with zunda", "矢にずんだをぬってみる")
                .registerAchievement();

        TcAchievement.create(Key.zundaAttack, -10, -3, TcItem.tofuZunda, Key.zundaArrow)
                .setTitle("It's me, Tohoku Zunko!", "東北ずん子です。ずんずん♪")
                .setDesc("Hit Zunda Arrow to slimes", "スライムにずんだアローを当てる")
                .setSpecial()
                .registerAchievement();

        // === Soy Oil ===
        TcAchievement.create(Key.soyOil, -5, 6, TcItem.soyOil, Key.soybeans)
                .setTriggerItemCrafting(new ItemStack(TcItem.soyOil))
                .setTitle("The oil from soybean", "大豆油")
                .setDesc("Extract it with defatting potion", "脱脂ポーションで抽出する")
                .registerAchievement();

        TcAchievement.create(Key.tofuFried, -7, 5, TcItem.tofuFried, Key.soyOil)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuFried))
                .setTitle("Fried Tofu", "厚揚げ")
                .setDesc("Fry tofu", "豆腐を素揚げしてみる")
                .registerAchievement();

        TcAchievement.create(Key.friedTofuPouch, -7, 6, TcItem.tofuFriedPouch, Key.soyOil)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuFriedPouch))
                .setTitle("Fried Tofu Pouch", "揚げ豆腐")
                .setDesc("Fry tofu with starch", "豆腐に片栗粉をつけて揚げる")
                .registerAchievement();

        // [fixed]
        TcAchievement.create(Key.ttt, -9, 6, TcItem.tttBurger, Key.friedTofuPouch)
                .setTriggerItemCrafting(new ItemStack(TcItem.tttBurger))
                .setTitle("TTT the legendary burger", "伝説のバーガー TTT")
                .setDesc("Put 3 tofu between bread", "豆腐3つをパンで挟む")
                .registerAchievement();

        TcAchievement.create(Key.oage, -7, 7, new ItemStack(TcItem.foodSet, 1, ItemFoodSet.oage.id), Key.soyOil)
                .setTriggerItemCrafting(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.oage.id))
                .setTitle("Oage", "お揚げ")
                .setDesc("Fry a sliced tofu block", "スライスした豆腐ブロックを揚げる")
                .registerAchievement();

        TcAchievement.create(Key.oinarisan, -9, 7, new ItemStack(TcItem.foodSet, 1, ItemFoodSet.inari.id), Key.oage)
                .setTriggerItemCrafting(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.inari.id))
                .setTitle("Oinari-san", "お稲荷さん")
                .setDesc("Craft oage and onigiri", "お揚げとおにぎりをクラフト")
                .registerAchievement();

        // === Tofu cook ===
        TcAchievement.create(Key.tofuCook, 4, 4, TcItem.tofuKinu, null)
                .setTitle("Encounter with Tofu Cook", "豆腐職人との出会い")
                .setDesc("Right click when seeing a tofu cook", "村で豆腐職人に会ったら右クリック")
                .registerAchievement();

        // === Hell Soybeans ===
        TcAchievement.create(Key.hellsoybeans, 2, 6, TcItem.soybeansHell, null)
                .setTriggerItemPickup(new ItemStack(TcItem.soybeansHell))
                .setTitle("Seek for hell gourmet", "地獄の食材を求めて")
                .setDesc("Anyways go deep into Nether!", "とにかくネザーの奥深くへ!")
                .registerAchievement();

        TcAchievement.create(Key.hellTofu, 2, 11, TcItem.tofuHell, Key.hellsoybeans)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuHell))
                .setTitle("Tasting Hell Tofu", "地獄の豆腐を食す")
                .setDesc("Make tofu from hell beans", "地獄大豆から豆腐を作る")
                .setSpecial()
                .registerAchievement();

        // === Tofu World ===
        TcAchievement.create(Key.tofuGem, 7, 4, new ItemStack(TcItem.materials, 1, ItemTcMaterials.tofuGem.id), null)
                .setTriggerItemPickup(new ItemStack(TcItem.materials, 1, ItemTcMaterials.tofuGem.id))
                .setTitle("Getting Tofu Gem", "豆腐石ゲット")
                .setDesc("Mine tofu ore", "豆腐鉱石を採掘する")
                .registerAchievement();

        TcAchievement.create(Key.tofuSlimeRadar, 9, 4, TcItem.tofuRadar, Key.tofuGem)
                .setTriggerItemCrafting(new ItemStack(TcItem.tofuRadar))
                .setTitle("Let's find tofu slimes", "豆腐スライム探しの旅へ")
                .setDesc("Set tofu gem and make it work", "豆腐石を組み込んで使えるようにしよう")
                .registerAchievement();

        // [fixed]
        TcAchievement.create(Key.tofuSlimeHunter, 9, 6, TcItem.swordKinu, Key.tofuSlimeRadar)
                .setTitle("Tofu slime hunter", "豆腐スライムハンター")
                .setDesc("Beat tofu slimes", "豆腐スライムを倒す")
                .registerAchievement();

        TcAchievement.create(Key.tofuStick, 11, 7, TcItem.tofuStick, Key.tofuSlimeHunter)
                .setTriggerItemPickup(new ItemStack(TcItem.tofuStick))
                .setTitle("A pilot to dreamland", "夢の世界への案内人")
                .setDesc("Get Tofu Stick", "トーフステッキを手に入れる")
                .registerAchievement();

        // When: The player traveled to the Tofu World
        TcAchievement.create(Key.tofuWorld, 12, 8, TcBlock.tofuMomen, Key.tofuStick)
                .setTitle("Get to the Tofu World!", "トーフワールドへ!")
                .setDesc("Shake the stick to a grilled tofu gate", "焼き豆腐のゲートにトーフステッキを振ってみよう")
                .registerAchievement();

        TcAchievement.create(Key.tofuFishing, 10, 10, Item.fishingRod, Key.tofuWorld)
                .setTriggerItemPickup(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofufishRow.id))
                .setTitle("Tofu Fishing!", "トーフ釣り!")
                .setDesc("Fish Tofufish at a soymilk pond", "豆乳池でトーフウオを釣ろう")
                .registerAchievement();

        TcAchievement.create(Key.tofunian, 12, 12, TcItem.tofuKinu, Key.tofuWorld)
                .setTitle("Tofunian, sons of tofu", "豆腐から生まれたトーフ人")
                .setDesc("Right-click when seeing in tofu villages", "トーフ村で会ったら右クリック")
                .setSpecial()
                .registerAchievement();

        // === Tofu Force ===
        TcAchievement.create(Key.tfCapacitor, 7, 7, new ItemStack(TcItem.materials, 1, ItemTcMaterials.tfCapacitor.id), Key.tofuGem)
                .setTriggerItemCrafting(new ItemStack(TcItem.materials, 1, ItemTcMaterials.tfCapacitor.id))
                .setTitle("Electronic tofu?", "豆腐で電子工作？")
                .setDesc("Craft TF capacitor from tofu gem", "豆腐石からTFコンデンサを作る")
                .registerStat();

        TcAchievement.create(Key.tfStorage, 6, 9, TcBlock.tfStorageIdle, Key.tfCapacitor)
                .setTriggerItemCrafting(new ItemStack(TcBlock.tfStorageIdle))
                .setTitle("Tofu Machine!", "豆腐マシン！")
                .setDesc("Build a Tofu Force Storage", "豆腐フォース蓄積マシンを完成させる")
                .registerStat();

        // When: Put an element item of TF on the input slot of TF Storage
        TcAchievement.create(Key.tofuForce, 8, 11, TcBlock.tfStorageActive, Key.tfStorage)
                .setTitle("Dawn of Tofu Industry", "豆腐工業の幕開け")
                .setDesc("Extract TF from tofu with storage", "豆腐フォース蓄積マシンで豆腐からTFを抽出する")
                .registerStat();

        // Add a new achievement page for the mod
        Achievement[] array = TcAchievementMgr.getAllAsArray();
        AchievementPage.registerAchievementPage(new AchievementPage("TofuCraft", array));

        ModLog.log(Level.INFO, "%d achievements for TofuCraft has been registered.", array.length);
    }
}
