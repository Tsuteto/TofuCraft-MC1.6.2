package tsuteto.tofu.item;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.DispenserBehaviorFilledBucket;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.eventhandler.TcCraftingHandler;
import tsuteto.tofu.util.IIdEntry;
import tsuteto.tofu.util.IdEntryArmor;
import tsuteto.tofu.util.IdEntrySingle;
import tsuteto.tofu.util.IdEntryTool;
import tsuteto.tofu.util.NameEntry;
import tsuteto.tofu.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Item Registry Class
 *
 * @author Tsuteto
 *
 */
public class TcItem extends Item
{
    private enum Id
    {
        salt(13665),
        saltyMelon(13666),
        tastyStew(13667),
        tastyBeefStew(13668),
        goldenSalt(13669),

        soybean(13670),
        nigari(13671),

        tofuKinu(13672),
        tofuMomen(13673),
        tofuIshi(13674),
        tofuMetal(13675),
        tofuGrilled(13676),
        tofuDried(13677),
        tofuFried(13678),
        tofuFriedPouch(13679),

        bucketSoySauce(13680),
        zundaManju(13681),
        barrelEmpty(13682),
        barrelMiso(13683),
        bottleSoymilk(13684),
        edamameBoiled(13685),
        bucketSoymilk(13686),
        zunda(13687),
        edamame(13688),
        misoDengaku(13689),
        misoSoup(13690),
        bugle(13691),
        morijio(13692),
        tttBurger(13693),
        yudofu(13694),
        koujiBase(13695),
        kouji(13696),
        miso(13697),
        tofuStick(13698),
        tofuCake(13699),

        armorKinu(13700, IdEntryArmor.class),
        armorMomen(13704, IdEntryArmor.class),
        armorSolid(13708, IdEntryArmor.class),
        armorMetal(13712, IdEntryArmor.class),

        swordKinu(13716),
        swordMomen(13717),
        swordSolid(13718),
        swordMetal(13719),

        soyOil(13720),
        bottleSoySauce(13721),
        soybeansParched(13722),
        kinako(13723),
        nikujaga(13724),
        tofuEgg(13725),
        defattingPotion(13726),
        dashi(13727),
        agedashiTofu(13728),
        kinakoManju(13729),
        fukumeni(13730),
        koyadofuStew(13731),
        natto(13732),
        nattoHiyayakko(13733),
        zundaArrow(13734), zundaBow(13735),
        apricot(13736), apricotSeed(13737),
        filterCloth(13738),
        okara(13739),
        mincedPotato(13740),
        starchRaw(13741),
        starch(13742),
        tofuAnnin(13743),
        tofuSesame(13744),
        tofuZunda(13745),
        kyoninso(13746),
        leek(13747),
        sesame(13748),
        okaraStick(13749),
        tofuStrawberry(13750),
        riceNatto(13751),
        riceNattoLeek(13752),
        gelatin(13753),
        zundama(13754),
        fukumame(13755),
        tofuHell(13756),
        tofuGlow(13757),
        tofuDiamond(13758),
        bucketSoymilkHell(13759),
        soybeanHell(13760),
        tofuScoop(13761),
        
        materials(13762),
        foodSet(13763),
        foodSetStick(13764),
        armorDiamond(13765, IdEntryArmor.class),
        swordDiamond(13769),

        toolKinu(13770, IdEntryTool.class),
        toolMomen(13773, IdEntryTool.class),
        toolSolid(13776, IdEntryTool.class),
        toolMetal(13779, IdEntryTool.class),
        toolDiamond(13782, IdEntryTool.class),

        doubanjiang(13785),
        strawberryJam(13786),
        tofuRadar(13787),
        yuba(13788),
        tofuMiso(13789),
        barrelMisoTofu(13790),
        barrelGlowtofu(13791),
        ;

        private IIdEntry entry;

        Id(int defaultId)
        {
            this(defaultId, (String)null);
        }
        Id(int defaultId, String comment)
        {
            this.entry = new IdEntrySingle(this.name(), defaultId, comment);
        }
        Id(int defaultId, Class<? extends IIdEntry> clazz)
        {
            try
            {
                this.entry = clazz.getDeclaredConstructor(String.class, int.class).newInstance(this.name(), defaultId);
            }
            catch (Exception e)
            {
                throw new RuntimeException("Caught an error in ID registration.", e);
            }
        }
        public void loadId(Configuration conf)
        {
            this.entry.loadId(conf);
        }
        public int getId()
        {
            return (Integer)this.entry.getId();
        }
        public <T> T getIdObj()
        {
            return (T) this.entry.getId();
        }
        public String getPropName(int i)
        {
            return this.entry.getPropName(i);
        }
    }

    public static final String[] armorNameList = new String[]{"helmet", "chestplate", "leggings", "boots"};
    public static final String[] toolNameList = new String[]{"shovel", "pickaxe", "axe"};

    public static Item soybeans;
    public static Item nigari;

    public static Item tofuKinu;
    public static Item tofuMomen;
    public static Item tofuIshi;
    public static Item tofuMetal;
    public static Item tofuGrilled;
    public static Item tofuDried;
    public static Item tofuFriedPouch;
    public static Item tofuFried;
    public static Item tofuEgg;

    public static Item tofuCake;
    public static Item tofuStick;
    public static Item koujiBase;
    public static Item kouji;
    public static Item miso;
    public static Item yudofu;
    public static Item tttBurger;
    public static Item morijio;
    public static Item bugle;
    public static Item misoSoup;
    public static Item misoDengaku;
    public static Item edamame;
    public static Item zunda;
    public static Item bucketSoymilk;
    public static Item edamameBoiled;
    public static Item bottleSoymilk;
    public static Item barrelEmpty;
    public static Item barrelMiso;
    public static Item zundaManju;
    public static Item bucketSoySauce;
    public static Item phialEmpty;
    public static Item bottleSoySauce;
    public static Item soybeansParched;
    public static Item kinako;
    public static Item nikujaga;
    public static Item defattingPotion;
    public static Item dashi;
    public static Item soyOil;
    public static Item agedashiTofu;
    public static Item kinakoManju;
    public static Item fukumeni;
    public static Item koyadofuStew;
    public static Item natto;
    public static Item nattoHiyayakko;

    public static Item salt;
    public static Item saltyMelon;
    public static Item tastyStew;
    public static Item tastyBeefStew;
    public static Item goldenSalt;

    public static Item[] armorKinu;
    public static Item[] armorMomen;
    public static Item[] armorSolid;
    public static Item[] armorMetal;
    public static Item[] armorDiamond;

    public static Item swordKinu;
    public static Item swordMomen;
    public static Item swordSolid;
    public static Item swordMetal;
    public static Item swordDiamond;

    public static Item[] toolKinu;
    public static Item[] toolMomen;
    public static Item[] toolSolid;
    public static Item[] toolMetal;
    public static Item[] toolDiamond;

    public static Item zundaArrow;
    public static Item zundaBow;

    public static Item apricot;
    public static Item apricotSeed;
    public static Item filterCloth;
    public static Item okara;
    public static Item mincedPotato;
    public static Item starchRaw;
    public static Item starch;
    public static Item tofuAnnin;
    public static Item tofuSesame;
    public static Item tofuZunda;
    public static Item kyoninso;
    public static Item leek;
    public static Item sesame;
    public static Item okaraStick;
    public static Item tofuStrawberry;
    public static Item gelatin; // Contains gelatin base
    public static Item riceNatto;
    public static Item riceNattoLeek;
    public static Item zundama;
    public static Item fukumame;
    public static Item tofuHell;
    public static Item tofuGlow;
    public static Item tofuDiamond;
    public static Item bucketSoymilkHell;
    public static Item soybeansHell;
    public static Item tofuScoop;
    public static ItemTcMaterials materials;
    public static ItemFoodSetBase foodSet;
    public static ItemFoodSetBase foodSetStick;
    public static Item doubanjiang;
    public static Item strawberryJam;
    public static Item tofuRadar;
    public static Item yuba;
    public static Item tofuMiso;
    public static Item barrelMisoTofu;
    public static Item barrelGlowtofu;

    // === External Mod Items ===
    public static Item plantBall; // from IC2
    public static Item bambooFood; // from Bamboo Mod
    public static Item bambooBasket; // from Bamboo Mod

    public static void loadId(Configuration conf)
    {
        for (Id entry : EnumSet.allOf(Id.class)) entry.loadId(conf);
    }

    public static void register()
    {
        soybeans = $("seeds_soybeans", new ItemSoybeans(Id.soybean.getId()))
                .setUnlocalizedName("tofucraft:seeds_soybeans");
        MinecraftForge.addGrassSeed(new ItemStack(soybeans), 2);
        ModLoader.addName(soybeans, "en_US", "Soybeans");
        ModLoader.addName(soybeans, "ja_JP", "大豆");

        soybeansHell = $("seeds_soybeansHell", new ItemSoybeansHell(Id.soybeanHell.getId()))
                .setUnlocalizedName("tofucraft:seeds_soybeansHell");
        ModLoader.addName(soybeansHell, "en_US", "Hell Soybeans");
        ModLoader.addName(soybeansHell, "ja_JP", "地獄大豆");

        /*
         * === Tofu ===
         */
        tofuKinu = $("tofuKinu", new ItemTcFood(Id.tofuKinu.getId(), 2, 0.1F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuKinu");
        TcBlock.tofuKinu.setDropItem(tofuKinu);
        ModLoader.addName(tofuKinu, "en_US", "Kinugoshi Tofu");
        ModLoader.addName(tofuKinu, "ja_JP", "絹ごし豆腐");

        tofuMomen = $("tofuMomen", new ItemTcFood(Id.tofuMomen.getId(), 2, 0.1F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuMomen");
        TcBlock.tofuMomen.setDropItem(tofuMomen);
        ModLoader.addName(tofuMomen, "en_US", "Momen Tofu");
        ModLoader.addName(tofuMomen, "ja_JP", "木綿豆腐");

        tofuIshi = $("tofuIshi", new ItemTcFood(Id.tofuIshi.getId(), 3, 0.4F, false))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:tofuIshi");
        TcBlock.tofuIshi.setDropItem(tofuIshi);
        ModLoader.addName(tofuIshi, "en_US", "Solid Tofu");
        ModLoader.addName(tofuIshi, "ja_JP", "石豆腐");

        tofuMetal = $("tofuMetal", new TcItem(Id.tofuMetal.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:tofuMetal");
        TcBlock.tofuMetal.setDropItem(tofuMetal);
        ModLoader.addName(tofuMetal, "en_US", "Metal Tofu");
        ModLoader.addName(tofuMetal, "ja_JP", "鋼豆腐");

        tofuGrilled = $("tofuGrilled", new ItemTcFood(Id.tofuGrilled.getId(), 3, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuGrilled");
        TcBlock.tofuGrilled.setDropItem(tofuGrilled);
        ModLoader.addName(tofuGrilled, "en_US", "Grilled Tofu");
        ModLoader.addName(tofuGrilled, "ja_JP", "焼き豆腐");

        tofuDried = $("tofuDried", new TcItem(Id.tofuDried.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:tofuDried");
        TcBlock.tofuDried.setDropItem(tofuDried);
        ModLoader.addName(tofuDried, "en_US", "Koya-Tofu");
        ModLoader.addName(tofuDried, "ja_JP", "高野豆腐");

        tofuFriedPouch = $("tofuPouchFried", new ItemTcFood(Id.tofuFriedPouch.getId(), 4, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuPouchFried");
        TcBlock.tofuFriedPouch.setDropItem(tofuFriedPouch);
        ModLoader.addName(tofuFriedPouch, "en_US", "Fried Tofu Pouch");
        ModLoader.addName(tofuFriedPouch, "ja_JP", "揚げ豆腐");

        tofuFried = $("tofuFried", new ItemTcFood(Id.tofuFried.getId(), 4, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuFried");
        TcBlock.tofuFried.setDropItem(tofuFried);
        ModLoader.addName(tofuFried, "en_US", "Fried Tofu");
        ModLoader.addName(tofuFried, "ja_JP", "厚揚げ");

        tofuEgg = $("tofuEgg", new ItemTcFood(Id.tofuEgg.getId(), 4, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuEgg");
        TcBlock.tofuEgg.setDropItem(tofuEgg);
        ModLoader.addName(tofuEgg, "en_US", "Egg Tofu");
        ModLoader.addName(tofuEgg, "ja_JP", "玉子豆腐");

        tofuAnnin = $("tofuAnnin", new ItemTcFood(Id.tofuAnnin.getId(), 4, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuAnnin");
        TcBlock.tofuAnnin.setDropItem(tofuAnnin);
        ModLoader.addName(tofuAnnin, "en_US", "Annin-Tofu");
        ModLoader.addName(tofuAnnin, "ja_JP", "杏仁豆腐");

        tofuSesame = $("tofuSesame", new ItemTcFood(Id.tofuSesame.getId(), 4, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuSesame");
        TcBlock.tofuSesame.setDropItem(tofuSesame);
        ModLoader.addName(tofuSesame, "en_US", "Sesame Tofu");
        ModLoader.addName(tofuSesame, "ja_JP", "胡麻豆腐");

        tofuZunda = $("tofuZunda", new ItemTcFood(Id.tofuZunda.getId(), 4, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuZunda");
        TcBlock.tofuZunda.setDropItem(tofuZunda);
        ModLoader.addName(tofuZunda, "en_US", "Zunda Tofu");
        ModLoader.addName(tofuZunda, "ja_JP", "ずんだ豆腐");

        tofuStrawberry = $("tofuStrawberry", new ItemTcFood(Id.tofuStrawberry.getId(), 3, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuStrawberry");
        TcBlock.tofuStrawberry.setDropItem(tofuStrawberry);
        ModLoader.addName(tofuStrawberry, "en_US", "Strawberry Tofu");
        ModLoader.addName(tofuStrawberry, "ja_JP", "いちごとうふ");
        
        tofuMiso = $("tofuMiso", new ItemTcFood(Id.tofuMiso.getId(), 5, 0.8F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuMiso");
        TcBlock.tofuMiso.setDropItem(tofuMiso);
        ModLoader.addName(tofuMiso, "en_US", "Miso Tofu");
        ModLoader.addName(tofuMiso, "ja_JP", "味噌漬け豆腐");

        tofuHell = $("tofuHell", new ItemTcFood(Id.tofuHell.getId(), 2, 0.2F, false))
                .setPotionEffect(Potion.fireResistance.id, 30, 0, 1.0F)
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuHell");
        TcBlock.tofuHell.setDropItem(tofuHell);
        ModLoader.addName(tofuHell, "en_US", "Hell Tofu");
        ModLoader.addName(tofuHell, "ja_JP", "地獄豆腐");

        tofuGlow = $("tofuGlow", new ItemTcFood(Id.tofuGlow.getId(), 2, 0.2F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:tofuGlow");
        TcBlock.tofuGlow.setDropItem(tofuGlow);
        ModLoader.addName(tofuGlow, "en_US", "Glowtofu");
        ModLoader.addName(tofuGlow, "ja_JP", "蛍豆腐");

        tofuDiamond = $("tofuDiamond", new TcItem(Id.tofuDiamond.getId()))
                .setUnlocalizedName("tofucraft:tofuDiamond");
        TcBlock.tofuDiamond.setDropItem(tofuDiamond);
        ModLoader.addName(tofuDiamond, "en_US", "Diamond Tofu");
        ModLoader.addName(tofuDiamond, "ja_JP", "金剛豆腐");

        /*
         * === Fluid Buckets ===
         */
        bucketSoymilk = $("bucketSoymilk", new ItemDrinkBucket(
                Id.bucketSoymilk.getId(),
                TcBlock.soymilkStill.blockID, 1, 0.1F))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:bucketSoymilk")
                .setCreativeTab(CreativeTabs.tabMisc)
                .setContainerItem(Item.bucketEmpty);
        ModLoader.addName(bucketSoymilk, "en_US", "Soy Milk Bucket");
        ModLoader.addName(bucketSoymilk, "ja_JP", "豆乳バケツ");

        bucketSoymilkHell = $("bucketSoymilkHell", new ItemDrinkBucket(
                Id.bucketSoymilkHell.getId(),
                TcBlock.soymilkHellStill.blockID, 2, 0.2F))
                .setPotionEffect(Potion.fireResistance.id, 60, 0, 1.0F)
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:bucketSoymilkHell")
                .setCreativeTab(CreativeTabs.tabMisc)
                .setContainerItem(Item.bucketEmpty);
        ModLoader.addName(bucketSoymilkHell, "en_US", "Hell Soy Milk Bucket");
        ModLoader.addName(bucketSoymilkHell, "ja_JP", "地獄豆乳バケツ");

        bucketSoySauce = $("bucketSoySauce", new ItemTcBucket(Id.bucketSoySauce.getId(), TcBlock.soySauceStill.blockID))
                .setUnlocalizedName("tofucraft:bucketSoySauce")
                .setContainerItem(Item.bucketEmpty)
                .setCreativeTab(CreativeTabs.tabMisc);
        ModLoader.addName(bucketSoySauce, "en_US", "Soy Sauce Bucket");
        ModLoader.addName(bucketSoySauce, "ja_JP", "醤油バケツ");

        DispenserBehaviorFilledBucket dispenserbehaviorfilledbucket = new DispenserBehaviorFilledBucket();
        BlockDispenser.dispenseBehaviorRegistry.putObject(bucketSoymilk, dispenserbehaviorfilledbucket);
        BlockDispenser.dispenseBehaviorRegistry.putObject(bucketSoymilkHell, dispenserbehaviorfilledbucket);
        BlockDispenser.dispenseBehaviorRegistry.putObject(bucketSoySauce, dispenserbehaviorfilledbucket);

        // Salt
        salt = $("salt", new ItemAltIcon(Id.salt.getId()))
        		.setIconName("sugar")
                .setUnlocalizedName("tofucraft:salt")
                .setCreativeTab(CreativeTabs.tabMaterials);
        ModLoader.addName(salt, "en_US", "Salt");
        ModLoader.addName(salt, "ja_JP", "塩");

        // Golden Salt
        goldenSalt = $("goldensalt", new ItemGoldenSalt(Id.goldenSalt.getId()))
                .setIconName("glowstone_dust")
                .setUnlocalizedName("tofucraft:goldensalt")
                .setCreativeTab(CreativeTabs.tabTools)
                .setMaxDamage(180);
        ModLoader.addName(goldenSalt, "en_US", "Purification Salt");
        ModLoader.addName(goldenSalt, "ja_JP", "魔除けの塩");

        // Salty Melon
        saltyMelon = $("saltyMelon", new ItemTcFood(Id.saltyMelon.getId(), 3, 0.5F, false))
                .setUnlocalizedName("tofucraft:saltyMelon");
        ModLoader.addName(saltyMelon, "en_US", "Salty Melon");
        ModLoader.addName(saltyMelon, "ja_JP", "塩スイカ");

        // Tasty Stew
        tastyStew = $("tastyStew", new ItemSoupBase(Id.tastyStew.getId(), 20, 1.0F, false))
                .setUnlocalizedName("tofucraft:tastyStew");
        ModLoader.addName(tastyStew, "en_US", "Tasty Stew");
        ModLoader.addName(tastyStew, "ja_JP", "おいしそうなシチュー");

        // Tasty Beef Stew
        tastyBeefStew = $("tastyBeefStew", new ItemSoupBase(Id.tastyBeefStew.getId(), 20, 1.0F, false))
                .setUnlocalizedName("tofucraft:tastyBeefStew");
        ModLoader.addName(tastyBeefStew, "en_US", "Tasty Beef Stew");
        ModLoader.addName(tastyBeefStew, "ja_JP", "おいしそうなビーフシチュー");

        // Nigari
        nigari = $("nigari", new ItemNigari(Id.nigari.getId()))
                .setUnlocalizedName("tofucraft:nigari")
                .setCreativeTab(CreativeTabs.tabMaterials);
        ModLoader.addName(nigari, "en_US", "Nigari");
        ModLoader.addName(nigari, "ja_JP", "にがり");

        // Tofu Cake
        tofuCake = $("tofuCake", new ItemTcReed(Id.tofuCake.getId(), TcBlock.tofuCake))
                .setUnlocalizedName("tofucraft:tofuCake")
                .setTextureName("tofucraft:tofuCake")
        		.setMaxStackSize(1)
                .setCreativeTab(CreativeTabs.tabFood);
        ModLoader.addName(tofuCake, "en_US", "Tofu Cake");
        ModLoader.addName(tofuCake, "ja_JP", "豆腐ケーキ");

        tofuStick = $("tofuStick", new ItemTofuStick(Id.tofuStick.getId()))
                .setFull3D()
                .setUnlocalizedName("tofucraft:tofuStick")
                .setCreativeTab(CreativeTabs.tabTools);
        ModLoader.addName(tofuStick, "en_US", "Tofu Stick");
        ModLoader.addName(tofuStick, "ja_JP", "トーフステッキ");

        koujiBase = $("koujiBase", new ItemKoujiBase(Id.koujiBase.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:koujiBase");
        ModLoader.addName(koujiBase, "en_US", "Kouji Base");
        ModLoader.addName(koujiBase, "ja_JP", "豆麹の種");

        kouji = $("kouji", new TcItem(Id.kouji.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:kouji");
        ModLoader.addName(kouji, "en_US", "Kouji");
        ModLoader.addName(kouji, "ja_JP", "豆麹");

        yudofu = $("yudofu", new ItemSoupBase(Id.yudofu.getId(), 3, 0.3F, false))
                .setUnlocalizedName("tofucraft:yudofu");
        ModLoader.addName(yudofu, "en_US", "Boiled Hot Tofu");
        ModLoader.addName(yudofu, "ja_JP", "湯豆腐");

        miso = $("miso", new TcItem(Id.miso.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:miso");
        ModLoader.addName(miso, "en_US", "Miso");
        ModLoader.addName(miso, "ja_JP", "味噌");

        tttBurger = $("tttBurger", new ItemTcFood(Id.tttBurger.getId(), 8, 0.4F, false))
                .setUnlocalizedName("tofucraft:tttBurger");
        ModLoader.addName(tttBurger, "en_US", "Triple Tofu Toast");
        ModLoader.addName(tttBurger, "ja_JP", "TTT");

        morijio = $("morijio", new ItemMorijio(Id.morijio.getId()))
                .setUnlocalizedName("tofucraft:morijio");
        ModLoader.addName(morijio, "en_US", "Morijio");
        ModLoader.addName(morijio, "ja_JP", "盛り塩");

        bugle = $("bugle", new ItemTofuBugle(Id.bugle.getId()))
                .setCreativeTab(CreativeTabs.tabMisc)
                .setUnlocalizedName("tofucraft:bugle");
        ModLoader.addName(bugle, "en_US", "Tofu Bugle");
        ModLoader.addName(bugle, "ja_JP", "豆腐屋のラッパ");

        misoSoup = $("misoSoup", new ItemSoupBase(Id.misoSoup.getId(), 4, 0.6F, false))
                .setUnlocalizedName("tofucraft:misoSoup");
        ModLoader.addName(misoSoup, "en_US", "Miso Soup");
        ModLoader.addName(misoSoup, "ja_JP", "味噌汁");

        misoDengaku = $("misoDengaku", new ItemFoodContainer(Id.misoDengaku.getId(), 5, 0.6F, true))
                .setContainedItem(new ItemStack(Item.stick))
                .setFull3D()
                .setUnlocalizedName("tofucraft:misoDengaku");
        ModLoader.addName(misoDengaku, "en_US", "Tofu Dengaku");
        ModLoader.addName(misoDengaku, "ja_JP", "豆腐田楽");

        edamame = $("edamame", new TcItem(Id.edamame.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:edamame");
        ModLoader.addName(edamame, "en_US", "Edamame Beans");
        ModLoader.addName(edamame, "ja_JP", "枝豆");

        zunda = $("zunda", new TcItem(Id.zunda.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:zunda");
        ModLoader.addName(zunda, "en_US", "Zunda (Edamame Paste)");
        ModLoader.addName(zunda, "ja_JP", "ずんだ");

        edamameBoiled = $("edamameBoiled", new ItemTcFood(Id.edamameBoiled.getId(), 1, 0.25F, false))
                .setAlwaysEdible()
                .setUnlocalizedName("tofucraft:edamameBoiled");
        ModLoader.addName(edamameBoiled, "en_US", "Boiled Edamame");
        ModLoader.addName(edamameBoiled, "ja_JP", "茹でた枝豆");

        barrelEmpty = $("barrelEmpty", new TcItem(Id.barrelEmpty.getId()))
                .setUnlocalizedName("tofucraft:barrelEmpty")
                .setCreativeTab(CreativeTabs.tabMaterials);
        ModLoader.addName(barrelEmpty, "en_US", "Barrel");
        ModLoader.addName(barrelEmpty, "ja_JP", "樽");

        barrelMiso = $("barrelMiso", new ItemBlockBarrel(Id.barrelMiso.getId(), TcBlock.barrelMiso))
                .setUnlocalizedName("tofucraft:barrelMiso")
                .setCreativeTab(CreativeTabs.tabDecorations);
        ModLoader.addName(barrelMiso, "en_US", "Miso Barrel");
        ModLoader.addName(barrelMiso, "ja_JP", "味噌樽");

        zundaManju = $("zundaManju", new ItemTcFood(Id.zundaManju.getId(), 6, 0.8F, true))
                .setUnlocalizedName("tofucraft:zundaManju");
        ModLoader.addName(zundaManju, "en_US", "Bun with Zunda");
        ModLoader.addName(zundaManju, "ja_JP", "ずんだまんじゅう");

//        phialEmpty = new ItemPhial(Id.phialEmpty.getId())
////
//                .setCreativeTab(CreativeTabs.tabMaterials)
//                .setUnlocalizedName("tofucraft:phialEmpty");
//        ModLoader.addName(phialEmpty, "en_US", "Phial");
//        ModLoader.addName(phialEmpty, "ja_JP", "小瓶");

        bottleSoySauce = $("bottleSoySause", new ItemSeasoningBottle(Id.bottleSoySauce.getId(), 0x432709))
                .setMaxDamage(19)
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:bottleSoySause");
        TcCraftingHandler.registerDurabilityItem(TcItem.bottleSoySauce);
        ModLoader.addName(bottleSoySauce, "en_US", "Soy Sauce Bottle");
        ModLoader.addName(bottleSoySauce, "ja_JP", "醤油瓶");

        soybeansParched = $("soybeansParched", new TcItem(Id.soybeansParched.getId()))
                .setUnlocalizedName("tofucraft:soybeansParched")
                .setCreativeTab(CreativeTabs.tabMaterials);
        ModLoader.addName(soybeansParched, "en_US", "Parched Soybeans");
        ModLoader.addName(soybeansParched, "ja_JP", "炒り豆");

        kinako = $("kinako", new TcItem(Id.kinako.getId()))
                .setUnlocalizedName("tofucraft:kinako")
                .setCreativeTab(CreativeTabs.tabMaterials);
        ModLoader.addName(kinako, "en_US", "Kinako");
        ModLoader.addName(kinako, "ja_JP", "きな粉");

        nikujaga = $("nikujaga", new ItemSoupBase(Id.nikujaga.getId(), 10, 0.7F, false))
                .setUnlocalizedName("tofucraft:nikujaga");
        ModLoader.addName(nikujaga, "en_US", "Nikujaga Stew");
        ModLoader.addName(nikujaga, "ja_JP", "肉じゃが");

        defattingPotion = $("defattingPotion", new ItemDefattingPotion(Id.defattingPotion.getId(), 0xada1cc))
                .setUnlocalizedName("tofucraft:defattingPotion")
                .setCreativeTab(CreativeTabs.tabMaterials);
        ModLoader.addName(defattingPotion, "en_US", "Defatting Potion");
        ModLoader.addName(defattingPotion, "ja_JP", "脱脂ポーション");

        dashi = $("dashi", new ItemSeasoningBottle(Id.dashi.getId(), 0xfcf6ac))
                .setMaxDamage(9)
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:dashi");
        TcCraftingHandler.registerDurabilityItem(dashi);
        ModLoader.addName(dashi, "en_US", "Soup Stock");
        ModLoader.addName(dashi, "ja_JP", "出汁");

        soyOil = $("soyOil", new ItemSeasoningBottle(Id.soyOil.getId(), 0xfeff82))
                .setMaxDamage(19)
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:soyOil");
        TcCraftingHandler.registerDurabilityItem(TcItem.soyOil);
        ModLoader.addName(soyOil, "en_US", "Soybean Oil");
        ModLoader.addName(soyOil, "ja_JP", "大豆油");

        agedashiTofu = $("agedashiTofu", new ItemSoupBase(Id.agedashiTofu.getId(), 6, 0.6F, false))
                .setUnlocalizedName("tofucraft:agedashiTofu");
        ModLoader.addName(agedashiTofu, "en_US", "Fried Tofu Pouch with Soup");
        ModLoader.addName(agedashiTofu, "ja_JP", "揚げ出し豆腐");

        kinakoManju = $("kinakoManju", new ItemTcFood(Id.kinakoManju.getId(), 4, 0.5F, true))
                .setUnlocalizedName("tofucraft:kinakoManju");
        ModLoader.addName(kinakoManju, "en_US", "Bun with Kinako");
        ModLoader.addName(kinakoManju, "ja_JP", "きなこまんじゅう");

        bottleSoymilk = $("bottleSoymilk", new ItemBottleSoyMilk(Id.bottleSoymilk.getId()))
                .setUnlocalizedName("tofucraft:bottleSoymilk");

        NameEntry.of(bottleSoymilk)
                .forDmg(0).nameEn("Soy Milk").nameJa("豆乳")
                .forDmg(1).nameEn("Kinako Soy Milk").nameJa("きなこ豆乳")
                .forDmg(2).nameEn("Cocoa Soy Milk").nameJa("ココア豆乳")
                .forDmg(3).nameEn("Zunda Soy Milk").nameJa("ずんだ豆乳")
                .forDmg(4).nameEn("Apple Soy Milk").nameJa("りんご豆乳")
                .forDmg(5).nameEn("Pumpkin Soy Milk").nameJa("かぼちゃ豆乳")
                .forDmg(6).nameEn("Ramune Soy Milk").nameJa("ラムネ豆乳")
                .forDmg(7).nameEn("Strawberry Soy Milk").nameJa("いちご豆乳")
                .forDmg(8).nameEn("Sakura Soy Milk").nameJa("桜豆乳");

        fukumeni = $("fukumeni", new ItemTcFood(Id.fukumeni.getId(), 3, 1.0f, true))
                .setUnlocalizedName("tofucraft:fukumeni");
        ModLoader.addName(fukumeni, "en_US", "Koya-Tofu Boiled in Stock");
        ModLoader.addName(fukumeni, "ja_JP", "高野豆腐の含め煮");

        koyadofuStew = $("koyadofuStew", new ItemSoupBase(Id.koyadofuStew.getId(), 8, 0.8f, false))
                .setUnlocalizedName("tofucraft:koyadofuStew");
        ModLoader.addName(koyadofuStew, "en_US", "Koya-Tofu Stew");
        ModLoader.addName(koyadofuStew, "ja_JP", "高野豆腐の煮物");

        natto = $("natto", new TcItem(Id.natto.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:natto");
        ModLoader.addName(natto, "en_US", "Natto");
        ModLoader.addName(natto, "ja_JP", "納豆");

        nattoHiyayakko = $("nattoHiyayakko", new ItemSoupBase(Id.nattoHiyayakko.getId(), 8, 0.8f, false))
                .setUnlocalizedName("tofucraft:nattoHiyayakko");
        ModLoader.addName(nattoHiyayakko, "en_US", "Natto Hiyayakko");
        ModLoader.addName(nattoHiyayakko, "ja_JP", "納豆冷や奴");

        apricotSeed = $("apricotSeed", new TcItem(Id.apricotSeed.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:apricotSeed");
        ModLoader.addName(apricotSeed, "en_US", "Apricot Seed");
        ModLoader.addName(apricotSeed, "ja_JP", "杏の種");

        apricot = $("apricot", new ItemFoodContainer(Id.apricot.getId(), 3, 0.3f, false, new ItemStack(apricotSeed)))
                .setUnlocalizedName("tofucraft:apricot");
        ModLoader.addName(apricot, "en_US", "Apricot");
        ModLoader.addName(apricot, "ja_JP", "杏");

        filterCloth = $("filterCloth", new TcItem(Id.filterCloth.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:filterCloth");
        ModLoader.addName(filterCloth, "en_US", "Filter Cloth");
        ModLoader.addName(filterCloth, "ja_JP", "漉し布");

        okara = $("okara", new TcItem(Id.okara.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:okara");
        ModLoader.addName(okara, "en_US", "Okara");
        ModLoader.addName(okara, "ja_JP", "おから");

        mincedPotato = $("mincedPotato", new TcItem(Id.mincedPotato.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:mincedPotato");
        ModLoader.addName(mincedPotato, "en_US", "Minced Potato");
        ModLoader.addName(mincedPotato, "ja_JP", "ジャガイモのみじん切り");

        starchRaw = $("starchRaw", new TcItem(Id.starchRaw.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:starchRaw");
        ModLoader.addName(starchRaw, "en_US", "Raw Starch");
        ModLoader.addName(starchRaw, "ja_JP", "生澱粉");

        starch = $("starch", new TcItem(Id.starch.getId()))
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:starch");
        ModLoader.addName(starch, "en_US", "Starch");
        ModLoader.addName(starch, "ja_JP", "片栗粉");

        kyoninso = $("kyoninso", new ItemAltIcon(Id.kyoninso.getId()))
        		.setIconName("tofucraft:starch")
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:kyoninso");
        ModLoader.addName(kyoninso, "en_US", "Apricot Seed Powder");
        ModLoader.addName(kyoninso, "ja_JP", "杏仁霜");

        leek = $("leek", new ItemLeek(Id.leek.getId()))
                .setFull3D()
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:leek");
        ModLoader.addName(leek, "en_US", "Leek");
        ModLoader.addName(leek, "ja_JP", "葱");

        sesame = $("sesame", new ItemTcSeeds(Id.sesame.getId(), TcBlock.sesame.blockID, Block.tilledField.blockID))
                .setUnlocalizedName("tofucraft:sesame")
                .setCreativeTab(CreativeTabs.tabMaterials);
        ModLoader.addName(sesame, "en_US", "Sesame");
        ModLoader.addName(sesame, "ja_JP", "胡麻");

        okaraStick = $("okaraStick", new ItemTcFood(Id.okaraStick.getId(), 5, 0.6f, false))
                .setEatingDuration(8)
                .setUnlocalizedName("tofucraft:okaraStick");
        ModLoader.addName(okaraStick, "en_US", "Okara Stick");
        ModLoader.addName(okaraStick, "ja_JP", "おからスティック");

        riceNatto = $("riceNatto", new ItemFoodContainer(Id.riceNatto.getId(), 8, 0.8f, false))
                .setUnlocalizedName("tofucraft:riceNatto");
        ModLoader.addName(riceNatto, "en_US", "Natto Rice");
        ModLoader.addName(riceNatto, "ja_JP", "納豆ご飯");

        riceNattoLeek = $("riceNattoLeek", new ItemFoodContainer(Id.riceNattoLeek.getId(), 9, 0.8f, false))
                .setUnlocalizedName("tofucraft:riceNattoLeek");
        ModLoader.addName(riceNattoLeek, "en_US", "Natto Rice with Leek");
        ModLoader.addName(riceNattoLeek, "ja_JP", "葱納豆ご飯");

        zundama = $("zundama", new TcItem(Id.zundama.getId()))
                .setUnlocalizedName("tofucraft:zundama");
        ModLoader.addName(zundama, "en_US", "Zundama");
        ModLoader.addName(zundama, "ja_JP", "ずんだま");

        // Gelatin and Gelatin Base
        gelatin = $("gelatin", new ItemGelatin(Id.gelatin.getId()))
                .setUnlocalizedName("tofucraft:gelatin");
        ModLoader.addName(gelatin, "en_US", "Gelatin");
        ModLoader.addName(gelatin, "ja_JP", "ゼラチン");
        ModLoader.addLocalization("item.tofucraft:gelatinRaw.name", "en_US", "Gelatin Base");
        ModLoader.addLocalization("item.tofucraft:gelatinRaw.name", "ja_JP", "ゼラチンベース");

        fukumame = $("fukumame", new ItemFukumame(Id.fukumame.getId()))
                .setUnlocalizedName("tofucraft:fukumame");
        ModLoader.addName(fukumame, "en_US", "Divine Beans");
        ModLoader.addName(fukumame, "ja_JP", "福豆");

        tofuScoop = $("tofuScoop", new ItemTofuScoop(Id.tofuScoop.getId()))
                .setUnlocalizedName("tofucraft:tofuScoop");
        ModLoader.addName(tofuScoop, "en_US", "Tofu Scoop");
        ModLoader.addName(tofuScoop, "ja_JP", "豆腐すくい");

        doubanjiang = $("doubanjiang", new ItemCraftingDurability(Id.doubanjiang.getId()))
                .setMaxDamage(57)
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:doubanjiang");
        TcCraftingHandler.registerDurabilityItem(doubanjiang);
        ModLoader.addName(doubanjiang, "en_US", "Doubanjiang");
        ModLoader.addName(doubanjiang, "ja_JP", "豆板醤");

        strawberryJam = $("strawberryJam", new ItemCraftingDurability(Id.strawberryJam.getId()))
                .setMaxDamage(99)
                .setCreativeTab(CreativeTabs.tabMaterials)
                .setUnlocalizedName("tofucraft:strawberryJam");
        TcCraftingHandler.registerDurabilityItem(TcItem.strawberryJam);
        ModLoader.addName(strawberryJam, "en_US", "Strawberry Jam");
        ModLoader.addName(strawberryJam, "ja_JP", "いちごジャム");

        tofuRadar = $("tofuRadar", new ItemTofuSlimeRadar(Id.tofuRadar.getId()))
                .setUnlocalizedName("tofucraft:tofuRadar");
        ModLoader.addName(tofuRadar, "en_US", "Tofu Slime Radar");
        ModLoader.addName(tofuRadar, "ja_JP", "豆腐スライムレーダー");
        
        yuba = $("yuba", new ItemYuba(Id.yuba.getId(), 1, 1.0f, false))
                .setUnlocalizedName("tofucraft:yuba")
                .setAlwaysEdible();
        ModLoader.addName(yuba, "en_US", "Yuba");
        ModLoader.addName(yuba, "ja_JP", "湯葉");

        barrelMisoTofu = $("barrelMisoTofu", new ItemBlockBarrel(Id.barrelMisoTofu.getId(), TcBlock.barrelMisoTofu))
                .setUnlocalizedName("tofucraft:barrelMisoTofu")
                .setCreativeTab(CreativeTabs.tabDecorations);
        ModLoader.addName(barrelMisoTofu, "en_US", "Miso Tofu Barrel");
        ModLoader.addName(barrelMisoTofu, "ja_JP", "味噌豆腐樽");

        barrelGlowtofu = $("barrelGlowtofu", new ItemBlockBarrel(Id.barrelGlowtofu.getId(), TcBlock.barrelGlowtofu))
                .setUnlocalizedName("tofucraft:barrelGlowtofu")
                .setCreativeTab(CreativeTabs.tabDecorations);
        ModLoader.addName(barrelGlowtofu, "en_US", "Glowtofu Barrel");
        ModLoader.addName(barrelGlowtofu, "ja_JP", "蛍豆腐樽");

        /*
         * === Material Items - Added as of 1.4 ===
         */
        materials = (ItemTcMaterials)$("materials", new ItemTcMaterials(Id.materials.getId()))
                .setUnlocalizedName("tofucraft:materials");

        TcBlock.oreTofu.setItemContained(
                new ItemStack(materials, 1, ItemTcMaterials.tofuGem.id));
        TcBlock.oreTofuDiamond.setItemContained(
                new ItemStack(materials, 1, ItemTcMaterials.tofuDiamondNugget.id));

        NameEntry.of(materials)
                .forDmg(0).nameEn("Tofu Gem").nameJa("豆腐石")
                .forDmg(1).nameEn("Diamond Tofu Nugget").nameJa("金剛豆腐の粒")
                .forDmg(2).nameEn("Tofu Hamburg (Raw)").nameJa("豆腐ハンバーグ(生)")
                .forDmg(3).nameEn("TF Capacitor").nameJa("TFコンデンサ");

        /*
         * === Food Set ===
         */
        foodSet = (ItemFoodSetBase)$("foodSet", new ItemFoodSet(Id.foodSet.getId()))
                .setUnlocalizedName("tofucraft:foodSet");

        NameEntry.of(foodSet)
                .forDmg(0).nameEn("Steamed Fish Tofu").nameJa("とうふちくわ")
                .forDmg(1).nameEn("Fried Tofu Slice").nameJa("お揚げ")
                .forDmg(2).nameEn("Mapo Tofu").nameJa("麻婆豆腐")
                .forDmg(3).nameEn("Riceball").nameJa("おにぎり")
                .forDmg(4).nameEn("Salty Riceball").nameJa("塩にぎり")
                .forDmg(5).nameEn("Roasted Miso Riceball").nameJa("味噌焼きおにぎり")
                .forDmg(6).nameEn("Roasted Soy Riceball").nameJa("醤油焼きおにぎり")
                .forDmg(7).nameEn("Sprout Saute").nameJa("もやし炒め")
                .forDmg(8).nameEn("Boiled Sprouts").nameJa("もやしのおひたし")
                .forDmg(9).nameEn("Sprouts").nameJa("もやし")
                .forDmg(10).nameEn("Hiyayakko").nameJa("冷や奴")
                .forDmg(11).nameEn("Tofu on Rice").nameJa("豆腐丼")
                .forDmg(12).nameEn("Tofu Hamburg").nameJa("豆腐ハンバーグ")
                .forDmg(13).nameEn("Tofu Cookie").nameJa("豆腐クッキー")
                .forDmg(14).nameEn("Oinari").nameJa("お稲荷")
                .forDmg(15).nameEn("Tofufish").nameJa("トーフウオ")
                .forDmg(16).nameEn("Grilled Tofufish").nameJa("焼きトーフウオ");

        foodSetStick = (ItemFoodSetBase)$("foodSetStick", new ItemFoodSetStick(Id.foodSetStick.getId()))
                .setUnlocalizedName("tofucraft:foodSetStick");

        NameEntry.of(foodSetStick)
                .forDmg(0).nameEn("Goheimochi (Mashed Rice with Miso)").nameJa("五平餅");

        /*
         * Zunda Arrow & Bow
         */
        zundaBow = $("zundaBow", new ItemZundaBow(Id.zundaBow.getId()))
                .setUnlocalizedName("tofucraft:zundaBow");
        ModLoader.addName(zundaBow, "en_US", "Zunda Bow");
        ModLoader.addName(zundaBow, "ja_JP", "ずんだ弓");
        zundaArrow = $("zundaArrow", new TcItem(Id.zundaArrow.getId()))
                .setUnlocalizedName("tofucraft:zundaArrow")
                .setCreativeTab(CreativeTabs.tabCombat);
        ModLoader.addName(zundaArrow, "en_US", "Zunda Arrow");
        ModLoader.addName(zundaArrow, "ja_JP", "ずんだアロー");

        /*
         * Armors & weapons
         */
        armorKinu = addArmorSet(Id.armorKinu, TofuArmorMaterial.KINU);
        ModLoader.addName(armorKinu[0], "en_US", "Kinu Tofu Helmet");
        ModLoader.addName(armorKinu[0], "ja_JP", "絹豆腐ヘルメット");
        ModLoader.addName(armorKinu[1], "en_US", "Kinu Tofu Chestplate");
        ModLoader.addName(armorKinu[1], "ja_JP", "絹豆腐チェストプレート");
        ModLoader.addName(armorKinu[2], "en_US", "Kinu Tofu Leggings");
        ModLoader.addName(armorKinu[2], "ja_JP", "絹豆腐レギンス");
        ModLoader.addName(armorKinu[3], "en_US", "Kinu Tofu Boots");
        ModLoader.addName(armorKinu[3], "ja_JP", "絹豆腐ブーツ");
        swordKinu = $("swordKinu", new ItemTofuSword(Id.swordKinu.getId(), TofuToolMaterial.KINU))
                .setUnlocalizedName("tofucraft:swordKinu")
                .setTextureName("tofucraft:swordKinu");
        ModLoader.addName(swordKinu, "en_US", "Kinu Tofu Sword");
        ModLoader.addName(swordKinu, "ja_JP", "絹豆腐剣");

        armorMomen = addArmorSet(Id.armorMomen, TofuArmorMaterial.MOMEN);
        ModLoader.addName(armorMomen[0], "en_US", "Tofu Helmet");
        ModLoader.addName(armorMomen[0], "ja_JP", "木綿豆腐ヘルメット");
        ModLoader.addName(armorMomen[1], "en_US", "Tofu Chestplate");
        ModLoader.addName(armorMomen[1], "ja_JP", "木綿豆腐チェストプレート");
        ModLoader.addName(armorMomen[2], "en_US", "Tofu Leggings");
        ModLoader.addName(armorMomen[2], "ja_JP", "木綿豆腐レギンス");
        ModLoader.addName(armorMomen[3], "en_US", "Tofu Boots");
        ModLoader.addName(armorMomen[3], "ja_JP", "木綿豆腐ブーツ");
        swordMomen = $("swordMomen", new ItemTofuSword(Id.swordMomen.getId(), TofuToolMaterial.MOMEN))
                .setUnlocalizedName("tofucraft:swordMomen")
                .setTextureName("tofucraft:swordMomen");
        ModLoader.addName(swordMomen, "en_US", "Tofu Sword");
        ModLoader.addName(swordMomen, "ja_JP", "木綿豆腐剣");

        armorSolid = addArmorSet(Id.armorSolid, TofuArmorMaterial.SOLID);
        ModLoader.addName(armorSolid[0], "en_US", "Solid Tofu Helmet");
        ModLoader.addName(armorSolid[0], "ja_JP", "石豆腐ヘルメット");
        ModLoader.addName(armorSolid[1], "en_US", "Solid Tofu Chestplate");
        ModLoader.addName(armorSolid[1], "ja_JP", "石豆腐チェストプレート");
        ModLoader.addName(armorSolid[2], "en_US", "Solid Tofu Leggings");
        ModLoader.addName(armorSolid[2], "ja_JP", "石豆腐レギンス");
        ModLoader.addName(armorSolid[3], "en_US", "Solid Tofu Boots");
        ModLoader.addName(armorSolid[3], "ja_JP", "石豆腐ブーツ");
        swordSolid = $("swordSolid", new ItemTofuSword(Id.swordSolid.getId(), TofuToolMaterial.SOLID))
                .setUnlocalizedName("tofucraft:swordSolid")
                .setTextureName("tofucraft:swordSolid");
        ModLoader.addName(swordSolid, "en_US", "Solid Tofu Sword");
        ModLoader.addName(swordSolid, "ja_JP", "石豆腐剣");

        armorMetal = addArmorSet(Id.armorMetal, TofuArmorMaterial.METAL);
        ModLoader.addName(armorMetal[0], "en_US", "Metal Tofu Helmet");
        ModLoader.addName(armorMetal[0], "ja_JP", "鋼豆腐ヘルメット");
        ModLoader.addName(armorMetal[1], "en_US", "Metal Tofu Chestplate");
        ModLoader.addName(armorMetal[1], "ja_JP", "鋼豆腐チェストプレート");
        ModLoader.addName(armorMetal[2], "en_US", "Metal Tofu Leggings");
        ModLoader.addName(armorMetal[2], "ja_JP", "鋼豆腐レギンス");
        ModLoader.addName(armorMetal[3], "en_US", "Metal Tofu Boots");
        ModLoader.addName(armorMetal[3], "ja_JP", "鋼豆腐ブーツ");
        swordMetal = $("swordMetal", new ItemTofuSword(Id.swordMetal.getId(), TofuToolMaterial.METAL))
                .setUnlocalizedName("tofucraft:swordMetal")
                .setTextureName("tofucraft:swordMetal");
        ModLoader.addName(swordMetal, "en_US", "Metal Tofu Sword");
        ModLoader.addName(swordMetal, "ja_JP", "鋼豆腐剣");

        armorDiamond = addArmorSet(Id.armorDiamond, TofuArmorMaterial.DIAMOND);
        ModLoader.addName(armorDiamond[0], "en_US", "Diamond Tofu Helmet");
        ModLoader.addName(armorDiamond[0], "ja_JP", "金剛豆腐ヘルメット");
        ModLoader.addName(armorDiamond[1], "en_US", "Diamond Tofu Chestplate");
        ModLoader.addName(armorDiamond[1], "ja_JP", "金剛豆腐チェストプレート");
        ModLoader.addName(armorDiamond[2], "en_US", "Diamond Tofu Leggings");
        ModLoader.addName(armorDiamond[2], "ja_JP", "金剛豆腐レギンス");
        ModLoader.addName(armorDiamond[3], "en_US", "Diamond Tofu Boots");
        ModLoader.addName(armorDiamond[3], "ja_JP", "金剛豆腐ブーツ");
        swordDiamond = $("swordDiamond", new ItemTofuSword(Id.swordDiamond.getId(), TofuToolMaterial.DIAMOND))
                .setUnlocalizedName("tofucraft:swordDiamond")
                .setTextureName("tofucraft:swordDiamond");
        ModLoader.addName(swordDiamond, "en_US", "Diamond Tofu Sword");
        ModLoader.addName(swordDiamond, "ja_JP", "金剛豆腐剣");

        /*
         * Tools
         */
        toolKinu = addToolSet(Id.toolKinu, TofuToolMaterial.KINU, 0);
        ModLoader.addName(toolKinu[0], "en_US", "Kinugoshi Tofu Shovel");
        ModLoader.addName(toolKinu[0], "ja_JP", "絹ごし豆腐シャベル");
        ModLoader.addName(toolKinu[1], "en_US", "Kinugoshi Tofu Pickaxe");
        ModLoader.addName(toolKinu[1], "ja_JP", "絹ごし豆腐ツルハシ");
        ModLoader.addName(toolKinu[2], "en_US", "Kinugoshi Tofu Axe");
        ModLoader.addName(toolKinu[2], "ja_JP", "絹ごし豆腐斧");

        toolMomen = addToolSet(Id.toolMomen, TofuToolMaterial.MOMEN, 0);
        ModLoader.addName(toolMomen[0], "en_US", "Momen Tofu Shovel");
        ModLoader.addName(toolMomen[0], "ja_JP", "木綿豆腐シャベル");
        ModLoader.addName(toolMomen[1], "en_US", "Momen Tofu Pickaxe");
        ModLoader.addName(toolMomen[1], "ja_JP", "木綿豆腐ツルハシ");
        ModLoader.addName(toolMomen[2], "en_US", "Momen Tofu Axe");
        ModLoader.addName(toolMomen[2], "ja_JP", "木綿豆腐斧");

        toolSolid = addToolSet(Id.toolSolid, TofuToolMaterial.SOLID, 1);
        ModLoader.addName(toolSolid[0], "en_US", "Solid Tofu Shovel");
        ModLoader.addName(toolSolid[0], "ja_JP", "石豆腐シャベル");
        ModLoader.addName(toolSolid[1], "en_US", "Solid Tofu Pickaxe");
        ModLoader.addName(toolSolid[1], "ja_JP", "石豆腐ツルハシ");
        ModLoader.addName(toolSolid[2], "en_US", "Solid Tofu Axe");
        ModLoader.addName(toolSolid[2], "ja_JP", "石豆腐斧");

        toolMetal = addToolSet(Id.toolMetal, TofuToolMaterial.METAL, 2);
        ModLoader.addName(toolMetal[0], "en_US", "Metal Tofu Shovel");
        ModLoader.addName(toolMetal[0], "ja_JP", "鋼豆腐シャベル");
        ModLoader.addName(toolMetal[1], "en_US", "Metal Tofu Pickaxe");
        ModLoader.addName(toolMetal[1], "ja_JP", "鋼豆腐ツルハシ");
        ModLoader.addName(toolMetal[2], "en_US", "Metal Tofu Axe");
        ModLoader.addName(toolMetal[2], "ja_JP", "鋼豆腐斧");

        toolDiamond = addToolSet(Id.toolDiamond, TofuToolMaterial.DIAMOND, 3);
        ModLoader.addName(toolDiamond[0], "en_US", "Diamond Tofu Shovel");
        ModLoader.addName(toolDiamond[0], "ja_JP", "金剛豆腐シャベル");
        ModLoader.addName(toolDiamond[1], "en_US", "Diamond Tofu Pickaxe");
        ModLoader.addName(toolDiamond[1], "ja_JP", "金剛豆腐ツルハシ");
        ModLoader.addName(toolDiamond[2], "en_US", "Diamond Tofu Axe");
        ModLoader.addName(toolDiamond[2], "ja_JP", "金剛豆腐斧");

    }

    private static Item[] addArmorSet(Id armorId, EnumArmorMaterial material)
    {
        String key;

        if (material == TofuArmorMaterial.KINU) key = "kinu";
        else if (material == TofuArmorMaterial.MOMEN) key = "momen";
        else if (material == TofuArmorMaterial.SOLID) key = "solid";
        else if (material == TofuArmorMaterial.METAL) key = "metal";
        else if (material == TofuArmorMaterial.DIAMOND) key = "diamond";
        else throw new IllegalArgumentException("Unknown material for armor");

        Item[] armors = new Item[4];
        for (int i = 0; i < 4; i++)
        {
            armors[i] = $(getArmorName(key, i), new ItemTofuArmor(armorId.<Integer[]>getIdObj()[i], material, 2, i))
                    .setArmorTexture(String.format("tofucraft:textures/armor/armor_%s_%d.png", key, i == 2 ? 2 : 1))
                    .setUnlocalizedName("tofucraft:" + armorId.getPropName(i))
                    .setTextureName("tofucraft:" + armorId.getPropName(i));
        }
        return armors;
    }

    private static Item[] addToolSet(Id armorId, EnumToolMaterial material, int harvestLevel)
    {
        String key;
        if (material == TofuToolMaterial.KINU) key = "kinu";
        else if (material == TofuToolMaterial.MOMEN) key = "momen";
        else if (material == TofuToolMaterial.SOLID) key = "solid";
        else if (material == TofuToolMaterial.METAL) key = "metal";
        else if (material == TofuToolMaterial.DIAMOND) key = "diamond";
        else throw new IllegalArgumentException("Unknown material for tool");

        Item[] tools = new Item[3];
        tools[0] = $(getToolName(key, 0), new ItemTcSpade(armorId.<Integer[]>getIdObj()[0], material))
                .setUnlocalizedName("tofucraft:" + armorId.getPropName(0))
                .setTextureName("tofucraft:" + armorId.getPropName(0));
        MinecraftForge.setToolClass(tools[0], "shovel", harvestLevel);

        tools[1] = $(getToolName(key, 1), new ItemTcPickaxe(armorId.<Integer[]>getIdObj()[1], material))
                .setUnlocalizedName("tofucraft:" + armorId.getPropName(1))
                .setTextureName("tofucraft:" + armorId.getPropName(1));
        MinecraftForge.setToolClass(tools[1], "pickaxe", harvestLevel);

        tools[2] = $(getToolName(key, 2), new ItemTcAxe(armorId.<Integer[]>getIdObj()[2], material))
                .setUnlocalizedName("tofucraft:" + armorId.getPropName(2))
                .setTextureName("tofucraft:" + armorId.getPropName(2));
        MinecraftForge.setToolClass(tools[2], "axe", harvestLevel);

        return tools;
    }

    private static String getArmorName(String key, int id)
    {
        return "armor" + Utils.capitalize(key) + Utils.capitalize(armorNameList[id]);
    }

    private static String getToolName(String key, int id)
    {
        return "tool" + Utils.capitalize(key) + Utils.capitalize(toolNameList[id]);
    }

    /**
     * === External Mod Items ===
     */
    public static void registerExternalModItems()
    {
        if (ModLoader.isModLoaded("IC2"))
        {
            plantBall = Utils.getIc2Item("plantBall");
        }

        if (ModLoader.isModLoaded("BambooMod"))
        {
            bambooBasket = Utils.getExternalModItemWithRegex("(?i)bamboobasket");
            bambooFood = Utils.getExternalModItemWithRegex("(?i)bamboofoods?");

            if (bambooBasket != null)
            {
                ((ItemFoodContainer)riceNatto).setContainedItem(new ItemStack(bambooBasket));
                ((ItemFoodContainer)riceNattoLeek).setContainedItem(new ItemStack(bambooBasket));
                ItemFoodSet.riceTofu.setContainer(new ItemStack(bambooBasket));
            }
        }
    }
    
    public static <T extends Item> T $(String name, T item)
    {
        GameRegistry.registerItem(item, name);
        return item;
    }

    public TcItem(int var1)
    {
    	super(var1);
    }

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}

    public void breakItem(ItemStack itemstack, EntityLivingBase entityLiving)
    {
        entityLiving.renderBrokenItemStack(itemstack);

        if (entityLiving instanceof EntityPlayer)
        {
            ((EntityPlayer)entityLiving).addStat(StatList.objectBreakStats[this.itemID], 1);
        }

        --itemstack.stackSize;

        if (itemstack.stackSize < 0)
        {
            itemstack.stackSize = 0;
        }

        itemstack.setItemDamage(0);

        if (itemstack.stackSize == 0 && entityLiving instanceof EntityPlayer)
        {
            ((EntityPlayer)entityLiving).inventory.mainInventory[((EntityPlayer)entityLiving).inventory.currentItem] = null;
        }
    }

    @Override
    public TcItem setUnlocalizedName(String name)
    {
        super.setUnlocalizedName(name);
        this.setTextureName(name);
        return this;
    }
}
