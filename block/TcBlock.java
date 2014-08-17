package tsuteto.tofu.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHalfSlab;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import tsuteto.tofu.Settings;
import tsuteto.tofu.block.tileentity.TileEntityMorijio;
import tsuteto.tofu.block.tileentity.TileEntitySaltFurnace;
import tsuteto.tofu.block.tileentity.TileEntityTfStorage;
import tsuteto.tofu.fluids.TcFluids;
import tsuteto.tofu.item.ItemLeekDense;
import tsuteto.tofu.item.ItemTcBlock;
import tsuteto.tofu.item.ItemTcLeaves;
import tsuteto.tofu.item.ItemTcMultiTextureTile;
import tsuteto.tofu.item.ItemTcSlab;
import tsuteto.tofu.item.ItemTcSlabFaces;
import tsuteto.tofu.material.TcMaterial;
import tsuteto.tofu.util.ModLog;
import tsuteto.tofu.util.NameEntry;
import tsuteto.tofu.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;

public class TcBlock extends Block
{
    private enum Id
    {
        soybean(1280),
        tofuKinu(1281),
        tofuMomen(1282),
        tofuIshi(1283),
        tofuMetal(1284),
        tofuGrilled(1285),
        tofuDried(1286),
        tofuPouchFried(1287),
        tofuFried(1288),
        tofuEgg(1289),
        tofuTerrain(199, "Tofu block ID below should be 255 or less! Not shifted by blockShiftIndex", true),

        leek(1290),
        saltFurnaceIdle(1291),
        saltFurnaceActive(1292),
        tofuCake(1293),
        morijio(1294),
        soymilk(1295, "Soymilk block uses 2 IDs which are this and the next.", false),
        barrelMiso(1297),
        soySauce(1298, "SoySauce block uses 2 IDs which are this and the next.", false),
        nattoBed(1300),
        tcLeaves(1301),
        tcLog(1302),
        tcSapling(1303),
        tofuAnnin(1304),
        tofuSesame(1305),
        tofuZunda(1306),
        sesame(1307),
        tofuStrawberry(1308),
        tofuStairsKinu(1309),
        tofuStairsMomen(1310),
        tofuStairsIshi(1311),
        tofuStairsMetal(1312),
        tofuStairsGrilled(1313),
        tofuStairsDried(1314),
        tofuStairsFriedPouch(1315),
        tofuStairsFried(1316),
        tofuStairsEgg(1317),
        tofuStairsAnnin(1318),
        tofuStairsSesame(1319),
        tofuStairsZunda(1320),
        tofuStairsStrawberry(1321),
        tofuSingleSlab1(1322),
        tofuSingleSlab2(1323),
        tofuSingleSlab3(1324),
        tofuSingleSlabFaces(1325),
        tofuSingleSlabGlow(1326),
        tofuDoubleSlab1(1327),
        tofuDoubleSlab2(1328),
        tofuDoubleSlab3(1329),
        tofuDoubleSlabFaces(1330),
        tofuDoubleSlabGlow(1331),
        natto(1332),
        sprouts(1333),
        tofuHell(1334),
        tofuGlow(1335),
        tofuDiamond(1336),
        soymilkHell(1337, "soymilkHell block uses 2 IDs which are this and the next.", false),
        soybeanHell(1339),
        oreTofu(1340),
        oreTofuDiamond(1341),
        salt(1342),
        tofuStairsHell(1343),
        tofuStairsGlow(1344),
        tofuStairsDiamond(1345),
        tofuPortal(1346),
        yuba(1347),
        tofuMiso(1348), barrelMisoTofu(1349), tofuStairsMiso(1350), barrelGlowtofu(1351),
        tfMachineCase(1352), tfStorageIdle(1353), tfStorageActive(1354)
        ;

        private int id;
        private final boolean isTerrainBlock;
        private final String comment;

        Id(int defaultId)
        {
            this(defaultId, null, false);
        }

        Id(int defaultId, boolean fixed)
        {
            this(defaultId, null, fixed);
        }

        Id(int defaultId, String comment, boolean fixed)
        {
            this.id = defaultId;
            this.isTerrainBlock = fixed;
            this.comment = comment;
        }

        public void loadId(Configuration conf)
        {
            if (this.isTerrainBlock)
            {
                this.id = conf.getTerrainBlock("block", this.name(), this.id, this.comment).getInt();
            }
            else
            {
                if (Settings.autoAssign)
                {
                    this.id = conf.getBlock("block", this.name(), this.id, this.comment).getInt();
                }
                else
                {
                    this.id = conf.get("block", this.name(), this.id, this.comment).getInt() + Settings.blockShiftIndex;
                }
            }
        }

        public int getId()
        {
            return this.id;
        }
    }

    public static Block soybean;
    public static Block leek;

    public static Block tofuTerrain;
    public static BlockTofuBase tofuKinu;
    public static BlockTofuBase tofuMomen;
    public static BlockTofuBase tofuIshi;
    public static BlockTofuBase tofuMetal;
    public static BlockTofuBase tofuGrilled;
    public static BlockTofuBase tofuDried;
    public static BlockTofuBase tofuFriedPouch;
    public static BlockTofuBase tofuFried;
    public static BlockTofuBase tofuEgg;
    public static BlockTofuBase tofuAnnin;
    public static BlockTofuBase tofuSesame;
    public static BlockTofuBase tofuZunda;
    public static BlockTofuBase tofuMiso;
    public static BlockTofuBase tofuStrawberry;
    public static BlockTofuBase tofuHell;
    public static BlockTofuBase tofuGlow;
    public static BlockTofuBase tofuDiamond;

    public static Block saltFurnaceIdle;
    public static Block saltFurnaceActive;
    public static Block tofuCake;
    public static BlockTofuPortal tofuPortal;
    public static Block morijio;
    //public static BlockFluid soymilkMoving;
    public static BlockTcStationary soymilkStill;
    public static Block barrelMiso;
    //public static BlockFluid soySauceMoving;
    public static BlockTcStationary soySauceStill;
    public static Block nattoBed;
    public static BlockTcLeaves tcLeaves;
    public static Block tcLog;
    public static Block tcSapling;
    public static Block sesame;
    public static Block natto;
    public static Block sprouts;

    public static Block tofuStairsKinu;
    public static Block tofuStairsMomen;
    public static Block tofuStairsIshi;
    public static Block tofuStairsMetal;
    public static Block tofuStairsGrilled;
    public static Block tofuStairsDried;
    public static Block tofuStairsFriedPouch;
    public static Block tofuStairsFried;
    public static Block tofuStairsEgg;
    public static Block tofuStairsAnnin;
    public static Block tofuStairsSesame;
    public static Block tofuStairsZunda;
    public static Block tofuStairsStrawberry;
    public static Block tofuStairsHell;
    public static Block tofuStairsGlow;
    public static Block tofuStairsDiamond;

    public static BlockHalfSlab tofuSingleSlab1;
    public static BlockHalfSlab tofuSingleSlab2;
    public static BlockHalfSlab tofuSingleSlab3;
    public static BlockHalfSlab tofuSingleSlabFaces;
    public static BlockHalfSlab tofuSingleSlabGlow;
    public static BlockHalfSlab tofuDoubleSlab1;
    public static BlockHalfSlab tofuDoubleSlab2;
    public static BlockHalfSlab tofuDoubleSlab3;
    public static BlockHalfSlab tofuDoubleSlabFaces;
    public static BlockHalfSlab tofuDoubleSlabGlow;

    //public static BlockFluid soymilkHellMoving;
    public static BlockTcStationary soymilkHellStill;
    public static Block soybeanHell;
    public static BlockTcOre oreTofuDiamond;
    public static BlockTcOre oreTofu;
    public static Block salt;
    public static Block yuba;
    public static Block barrelMisoTofu;
    public static Block tofuStairsMiso;
    public static Block barrelGlowtofu;
    public static Block tfStorageIdle;
    public static Block tfStorageActive;
    public static Block tfMachineCase;

    // === External Mod Items ===
    public static Block sakuraLeaves; // from Bamboo Mod

    
    public static void loadId(Configuration conf)
    {
        for (Id entry : Id.values()) entry.loadId(conf);

        if (Id.tofuTerrain.getId() > 255)
        {
            throw new RuntimeException("[TofuCraft] tofuTerrain's block ID should be 255 or less! (tofuTerrainのブロックIDは255以下でなければなりません！)");
        }
    }

    public static void register()
    {
        soybean = new BlockSoybean(Id.soybean.getId())
                .setUnlocalizedName("tofucraft:blockSoybeans");
        GameRegistry.registerBlock(soybean, "soybeans");
        ModLoader.addName(soybean, "en_US", "Soybean");
        ModLoader.addName(soybean, "ja_JP", "大豆");

        soybeanHell = new BlockSoybeanHell(Id.soybeanHell.getId())
                .setUnlocalizedName("tofucraft:blockSoybeansHell");
        GameRegistry.registerBlock(soybeanHell, "soybeansHell");
        ModLoader.addName(soybeanHell, "en_US", "Hell Soybean");
        ModLoader.addName(soybeanHell, "ja_JP", "地獄大豆");

        /*
         * Tofu
         */
        tofuTerrain = new BlockTofuTerrain(Id.tofuTerrain.getId())
                .setUnlocalizedName("tofucraft:blockTofuTerrain")
                .setHardness(0.4F)
                .setStepSound(Block.soundSnowFootstep);
        GameRegistry.registerBlock(tofuTerrain, ItemBlock.class, "blockTofuTerrain");
        MinecraftForge.setBlockHarvestLevel(tofuTerrain, "shovel", 0);
        ModLoader.addName(tofuTerrain, "en_US", "Tofu Block");
        ModLoader.addName(tofuTerrain, "ja_JP", "豆腐ブロック");

        tofuKinu = (BlockTofuBase)new BlockTofu(Id.tofuKinu.getId(), TcMaterial.tofu)
                .setFragile()
                .setFreeze(5)
                .setHardness(0.1F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuKinu")
                .setTextureName("tofucraft:blockTofuKinu");
        GameRegistry.registerBlock(tofuKinu, ItemTcBlock.class, "blockTofuKinu");
        MinecraftForge.setBlockHarvestLevel(tofuKinu, "shovel", 0);
        ModLoader.addName(tofuKinu, "en_US", "Kinugoshi Tofu Block");
        ModLoader.addName(tofuKinu, "ja_JP", "絹ごし豆腐ブロック");

        tofuMomen = (BlockTofuBase)new BlockTofu(Id.tofuMomen.getId(), TcMaterial.tofu)
                .setDrain(3)
                .setHardness(0.4F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuMomen");
        GameRegistry.registerBlock(tofuMomen, ItemTcBlock.class, "blockTofuMomen");
        MinecraftForge.setBlockHarvestLevel(tofuMomen, "shovel", 0);
        ModLoader.addName(tofuMomen, "en_US", "Momen Tofu Block");
        ModLoader.addName(tofuMomen, "ja_JP", "木綿豆腐ブロック");

        tofuIshi = (BlockTofuBase)new BlockTofuIshi(Id.tofuIshi.getId(), TcMaterial.tofu)
                .setDrain(8)
                .setScoopable(false)
                .setHardness(2.0F)
                .setResistance(10.0F)
                .setStepSound(Block.soundStoneFootstep)
                .setUnlocalizedName("tofucraft:blockTofuIshi");
        GameRegistry.registerBlock(tofuIshi, ItemTcBlock.class, "blockTofuIshi");
        MinecraftForge.setBlockHarvestLevel(tofuIshi, "pickaxe", 0);
        ModLoader.addName(tofuIshi, "en_US", "Solid Tofu Block");
        ModLoader.addName(tofuIshi, "ja_JP", "石豆腐ブロック");

        tofuMetal = (BlockTofuBase)new BlockTofu(Id.tofuMetal.getId(), Material.iron)
                .setScoopable(false)
                .setHardness(6.0F)
                .setResistance(15.0F)
                .setStepSound(Block.soundMetalFootstep)
                .setUnlocalizedName("tofucraft:blockTofuMetal");
        GameRegistry.registerBlock(tofuMetal, ItemTcBlock.class, "blockTofuMetal");
        MinecraftForge.setBlockHarvestLevel(tofuMetal, "pickaxe", 1);
        ModLoader.addName(tofuMetal, "en_US", "Metal Tofu Block");
        ModLoader.addName(tofuMetal, "ja_JP", "鋼豆腐ブロック");

        tofuGrilled = (BlockTofuBase)new BlockTofuGrilled(Id.tofuGrilled.getId())
                .setHardness(1.0F)
                .setResistance(50.0F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuGrilled");
        GameRegistry.registerBlock(tofuGrilled, ItemTcBlock.class, "blockTofuGrilled");
        MinecraftForge.setBlockHarvestLevel(tofuGrilled, "shovel", 0);
        ModLoader.addName(tofuGrilled, "en_US", "Grilled Tofu Block");
        ModLoader.addName(tofuGrilled, "ja_JP", "焼き豆腐ブロック");

        tofuDried = (BlockTofuBase)new BlockTofu(Id.tofuDried.getId(), Material.sponge)
                .setScoopable(false)
                .setHardness(1.0F)
                .setResistance(2.0F)
                .setStepSound(Block.soundStoneFootstep)
                .setUnlocalizedName("tofucraft:blockTofuDried");
        GameRegistry.registerBlock(tofuDried, ItemTcBlock.class, "blockTofuDried");
        MinecraftForge.setBlockHarvestLevel(tofuDried, "shovel", 0);
        ModLoader.addName(tofuDried, "en_US", "Koya-Tofu Block");
        ModLoader.addName(tofuDried, "ja_JP", "高野豆腐ブロック");

        tofuFriedPouch = (BlockTofuBase)new BlockTofu(Id.tofuPouchFried.getId(), TcMaterial.tofu)
                .setHardness(1.0F)
                .setResistance(8.0F)
                .setStepSound(Block.soundPowderFootstep)
                .setUnlocalizedName("tofucraft:blockTofuPouchFried");
        GameRegistry.registerBlock(tofuFriedPouch, ItemTcBlock.class, "blockTofuFriedPouch");
        MinecraftForge.setBlockHarvestLevel(tofuFriedPouch, "shovel", 0);
        ModLoader.addName(tofuFriedPouch, "en_US", "Fried Tofu Pouch Block");
        ModLoader.addName(tofuFriedPouch, "ja_JP", "揚げ豆腐ブロック");

        tofuFried = (BlockTofuBase)new BlockTofu(Id.tofuFried.getId(), TcMaterial.tofu)
                .setHardness(2.0F)
                .setResistance(10.0F)
                .setStepSound(Block.soundGravelFootstep)
                .setUnlocalizedName("tofucraft:blockTofuFried");
        GameRegistry.registerBlock(tofuFried, ItemTcBlock.class, "blockTofuFried");
        MinecraftForge.setBlockHarvestLevel(tofuFried, "shovel", 0);
        ModLoader.addName(tofuFried, "en_US", "Fried Tofu Block");
        ModLoader.addName(tofuFried, "ja_JP", "厚揚げブロック");

        tofuEgg = (BlockTofuBase)new BlockTofu(Id.tofuEgg.getId(), TcMaterial.tofu)
                .setHardness(0.2F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuEgg");
        GameRegistry.registerBlock(tofuEgg, ItemTcBlock.class, "blockTofuEgg");
        MinecraftForge.setBlockHarvestLevel(tofuEgg, "shovel", 0);
        ModLoader.addName(tofuEgg, "en_US", "Egg Tofu Block");
        ModLoader.addName(tofuEgg, "ja_JP", "玉子豆腐ブロック");

        tofuAnnin = (BlockTofuBase)new BlockTofuAnnin(Id.tofuAnnin.getId(), TcMaterial.tofu)
                .setHardness(0.2F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuAnnin");
        GameRegistry.registerBlock(tofuAnnin, ItemTcBlock.class, "blockTofuAnnin");
        MinecraftForge.setBlockHarvestLevel(tofuAnnin, "shovel", 0);
        ModLoader.addName(tofuAnnin, "en_US", "Annin-Tofu Block");
        ModLoader.addName(tofuAnnin, "ja_JP", "杏仁豆腐ブロック");

        tofuSesame = (BlockTofuBase)new BlockTofu(Id.tofuSesame.getId(), TcMaterial.tofu)
                .setHardness(0.2F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuSesame");
        GameRegistry.registerBlock(tofuSesame, ItemTcBlock.class, "blockTofuSesame");
        MinecraftForge.setBlockHarvestLevel(tofuSesame, "shovel", 0);
        ModLoader.addName(tofuSesame, "en_US", "Sesame Tofu Block");
        ModLoader.addName(tofuSesame, "ja_JP", "胡麻豆腐ブロック");

        tofuZunda = (BlockTofuBase)new BlockTofu(Id.tofuZunda.getId(), TcMaterial.tofu)
                .setHardness(0.3F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuZunda");
        GameRegistry.registerBlock(tofuZunda, ItemTcBlock.class, "blockTofuZunda");
        MinecraftForge.setBlockHarvestLevel(tofuZunda, "shovel", 0);
        ModLoader.addName(tofuZunda, "en_US", "Zunda Tofu Block");
        ModLoader.addName(tofuZunda, "ja_JP", "ずんだ豆腐ブロック");

        tofuStrawberry = (BlockTofuBase)new BlockTofu(Id.tofuStrawberry.getId(), TcMaterial.tofu)
                .setHardness(0.5F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuStrawberry");
        GameRegistry.registerBlock(tofuStrawberry, ItemTcBlock.class, "blockTofuStrawberry");
        MinecraftForge.setBlockHarvestLevel(tofuStrawberry, "shovel", 0);
        ModLoader.addName(tofuStrawberry, "en_US", "Strawberry Tofu Block");
        ModLoader.addName(tofuStrawberry, "ja_JP", "いちごとうふブロック");
        
        tofuMiso = (BlockTofuBase)new BlockTofu(Id.tofuMiso.getId(), TcMaterial.tofu)
                .setHardness(0.5F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuYamauni");
        GameRegistry.registerBlock(tofuMiso, ItemTcBlock.class, "blockTofuMiso");
        MinecraftForge.setBlockHarvestLevel(tofuMiso, "shovel", 0);
        ModLoader.addName(tofuMiso, "en_US", "Miso Tofu Block");
        ModLoader.addName(tofuMiso, "ja_JP", "味噌漬け豆腐");

        tofuHell = (BlockTofuBase)new BlockTofu(Id.tofuHell.getId(), TcMaterial.tofu)
                .setHardness(0.5F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuHell");
        GameRegistry.registerBlock(tofuHell, ItemTcBlock.class, "blockTofuHell");
        MinecraftForge.setBlockHarvestLevel(tofuHell, "shovel", 0);
        ModLoader.addName(tofuHell, "en_US", "Hell Tofu Block");
        ModLoader.addName(tofuHell, "ja_JP", "地獄豆腐ブロック");

        tofuGlow = (BlockTofuBase)new BlockTofu(Id.tofuGlow.getId(), TcMaterial.tofu)
                .setHardness(0.5F)
                .setLightValue(0.9375F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockTofuGlow");
        GameRegistry.registerBlock(tofuGlow, ItemTcBlock.class, "blockTofuGlow");
        MinecraftForge.setBlockHarvestLevel(tofuGlow, "shovel", 0);
        ModLoader.addName(tofuGlow, "en_US", "Glow Tofu Block");
        ModLoader.addName(tofuGlow, "ja_JP", "蛍豆腐ブロック");

        tofuDiamond = (BlockTofuBase)new BlockTofu(Id.tofuDiamond.getId(), TcMaterial.tofu)
                .setScoopable(false)
                .setHardness(8.0F)
                .setResistance(15.0F)
                .setStepSound(Block.soundGlassFootstep)
                .setUnlocalizedName("tofucraft:blockTofuDiamond");
        GameRegistry.registerBlock(tofuDiamond, ItemTcBlock.class, "blockTofuDiamond");
        MinecraftForge.setBlockHarvestLevel(tofuDiamond, "pickaxe", 1);
        ModLoader.addName(tofuDiamond, "en_US", "Diamond Tofu Block");
        ModLoader.addName(tofuDiamond, "ja_JP", "金剛豆腐ブロック");

        /*
         * Misc
         */
        leek = new BlockLeek(Id.leek.getId())
                .setHardness(0.0F)
                .setStepSound(Block.soundGrassFootstep)
                .setCreativeTab(CreativeTabs.tabDecorations)
                .setTextureName("tofucraft:blockLeek")
                .setUnlocalizedName("tofucraft:blockLeek");
        GameRegistry.registerBlock(leek, ItemLeekDense.class, "blockLeek");
        ModLoader.addName(leek, "en_US", "Green Onion");
        ModLoader.addName(leek, "ja_JP", "ネギ");

        saltFurnaceIdle = new BlockSaltFurnace(Id.saltFurnaceIdle.getId(), false)
                .setHardness(3.5F)
                .setStepSound(Block.soundStoneFootstep)
                .setUnlocalizedName("tofucraft:saltFurnace")
                .setCreativeTab(CreativeTabs.tabDecorations);
        GameRegistry.registerBlock(saltFurnaceIdle, ItemTcBlock.class, "saltFurnaceIdle");
        MinecraftForge.setBlockHarvestLevel(saltFurnaceIdle, "pickaxe", 0);
        ModLoader.addName(saltFurnaceIdle, "en_US", "Salt Furcnace");
        ModLoader.addName(saltFurnaceIdle, "ja_JP", "製塩かまど");

        saltFurnaceActive = new BlockSaltFurnace(Id.saltFurnaceActive.getId(), true)
                .setHardness(3.5F)
                .setStepSound(Block.soundStoneFootstep)
                .setLightValue(0.875F)
                .setUnlocalizedName("tofucraft:saltFurnace")
                ;
        GameRegistry.registerBlock(saltFurnaceActive, "saltFurnaceActive");
        GameRegistry.registerTileEntity(TileEntitySaltFurnace.class, "tofucraft:SaltFurnace");
        MinecraftForge.setBlockHarvestLevel(saltFurnaceActive, "pickaxe", 0);
        ModLoader.addName(saltFurnaceActive, "en_US", "Salt Furcnace");
        ModLoader.addName(saltFurnaceActive, "ja_JP", "製塩かまど");

        tofuCake = new BlockTofuCake(Id.tofuCake.getId()).disableStats()
                .setHardness(0.5F)
                .setStepSound(Block.soundClothFootstep)
                .setUnlocalizedName("tofucraft:tofuCake")
                ;
        GameRegistry.registerBlock(tofuCake, "blockTofuCake");
        ModLoader.addName(tofuCake, "en_US", "Tofu Cake");
        ModLoader.addName(tofuCake, "ja_JP", "豆腐ケーキ");

        tofuPortal = (BlockTofuPortal)(new BlockTofuPortal(Id.tofuPortal.getId()))
                .setHardness(-1.0F)
                .setStepSound(Block.soundGlassFootstep)
                .setLightValue(0.75F)
                .setUnlocalizedName("tofucraft:tofuPortal");
        GameRegistry.registerBlock(tofuPortal, "blockTofuPortal");
        ModLoader.addName(tofuPortal, "en_US", "Tofu Portal Block");
        ModLoader.addName(tofuPortal, "ja_JP", "トーフポータルブロック");

        morijio = new BlockMorijio(Id.morijio.getId())
                .setHardness(0.5F)
                .setStepSound(Block.soundClothFootstep)
                .setUnlocalizedName("tofucraft:morijio")
                ;
        GameRegistry.registerBlock(morijio, "blockMorijio");
        GameRegistry.registerTileEntity(TileEntityMorijio.class, "tofucraft:Morijio");
        ModLoader.addName(morijio, "en_US", "Morijio");
        ModLoader.addName(morijio, "ja_JP", "盛り塩");

        barrelMiso = new BlockMisoBarrel(Id.barrelMiso.getId(), Material.wood)
                .setFermRate(3)
                .setHardness(2.0F)
                .setStepSound(Block.soundWoodFootstep)
                .setUnlocalizedName("tofucraft:barrelMiso")
                ;
        GameRegistry.registerBlock(barrelMiso, "blockBarrelMiso");
        MinecraftForge.setBlockHarvestLevel(barrelMiso, "axe", 0);
        ModLoader.addName(barrelMiso, "en_US", "Miso Barrel");
        ModLoader.addName(barrelMiso, "ja_JP", "味噌樽");

        nattoBed = new BlockNattoBed(Id.nattoBed.getId(), 0x8c, Material.grass)
                .setFermRate(3)
                .setHardness(0.8F)
                .setStepSound(Block.soundGrassFootstep)
                .setUnlocalizedName("tofucraft:nattoBed")
                .setCreativeTab(CreativeTabs.tabDecorations);
        GameRegistry.registerBlock(nattoBed, ItemTcBlock.class, "nattoBed");
        ModLoader.addName(nattoBed, "en_US", "Natto Bed");
        ModLoader.addName(nattoBed, "ja_JP", "納豆床");

        sesame = new BlockSesame(Id.sesame.getId())
                .setUnlocalizedName("tofucraft:blockSesame");
        GameRegistry.registerBlock(sesame, "blockSesame");
        ModLoader.addName(sesame, "en_US", "Sesame");
        ModLoader.addName(sesame, "ja_JP", "胡麻");

        natto = new BlockNatto(Id.natto.getId())
                .setHardness(0.3F)
                .setStepSound(Block.soundSnowFootstep)
                .setCreativeTab(CreativeTabs.tabBlock)
                .setUnlocalizedName("tofucraft:blockNatto")
                .setTextureName("tofucraft:blockNatto");
        GameRegistry.registerBlock(natto, ItemTcBlock.class, "blockNatto");
        ModLoader.addName(natto, "en_US", "Natto Block");
        ModLoader.addName(natto, "ja_JP", "納豆ブロック");

        sprouts = new BlockSprouts(Id.sprouts.getId())
                .setUnlocalizedName("tofucraft:blockSprouts");
        GameRegistry.registerBlock(sprouts, "sprouts");
        ModLoader.addName(sprouts, "en_US", "Sprouts");
        ModLoader.addName(sprouts, "ja_JP", "もやし");

        salt = new BlockSand(Id.salt.getId())
                .setHardness(0.5F)
                .setStepSound(Block.soundSandFootstep)
                .setUnlocalizedName("tofucraft:blockSalt")
                .setTextureName("tofucraft:blockSalt");
        GameRegistry.registerBlock(salt, ItemTcBlock.class, "blockSalt");
        ModLoader.addName(salt, "en_US", "Salt Block");
        ModLoader.addName(salt, "ja_JP", "塩ブロック");
        
        yuba = (new BlockYuba(Id.yuba.getId()))
                .setHardness(0.0F)
                .setStepSound(soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockYuba")
                .setTextureName("tofucraft:blockYuba");
        GameRegistry.registerBlock(yuba, "blockYuba");
        ModLoader.addName(yuba, "en_US", "Yuba");
        ModLoader.addName(yuba, "ja_JP", "湯葉");
        
        barrelMisoTofu = new BlockMisoTofuBarrel(Id.barrelMisoTofu.getId(), Material.wood)
                .setFermRate(2)
                .setHardness(2.0F)
                .setStepSound(Block.soundWoodFootstep)
                .setUnlocalizedName("tofucraft:barrelMisoTofu")
                ;
        GameRegistry.registerBlock(barrelMisoTofu, "blockBarrelMisoTofu");
        MinecraftForge.setBlockHarvestLevel(barrelMisoTofu, "axe", 0);
        ModLoader.addName(barrelMisoTofu, "en_US", "Miso Tofu Barrel");
        ModLoader.addName(barrelMisoTofu, "ja_JP", "味噌豆腐樽");

        barrelGlowtofu = new BlockGlowtofuBarrel(Id.barrelGlowtofu.getId(), Material.wood)
                .setFermRate(4)
                .setHardness(2.0F)
                .setStepSound(Block.soundWoodFootstep)
                .setUnlocalizedName("tofucraft:barrelGlowtofu")
        ;
        GameRegistry.registerBlock(barrelGlowtofu, "blockBarrelGlowtofu");
        MinecraftForge.setBlockHarvestLevel(barrelGlowtofu, "axe", 0);
        ModLoader.addName(barrelGlowtofu, "en_US", "Glowtofu Barrel");
        ModLoader.addName(barrelGlowtofu, "ja_JP", "蛍豆腐樽");
        
        /*
         * TF machine
         */
        tfMachineCase = new BlockTfMachineCase(Id.tfMachineCase.getId())
                .setHardness(2.5F)
                .setStepSound(Block.soundMetalFootstep)
                .setCreativeTab(CreativeTabs.tabBlock)
                .setUnlocalizedName("tofucraft:tfMachineCase")
                ;
        GameRegistry.registerBlock(tfMachineCase, ItemTcBlock.class, "tfMachineCase");
        MinecraftForge.setBlockHarvestLevel(tfMachineCase, "pickaxe", 0);
        ModLoader.addName(tfMachineCase, "en_US", "Tofu Machine Case");
        ModLoader.addName(tfMachineCase, "ja_JP", "豆腐マシン筐体");

        tfStorageIdle = new BlockTfStorage(Id.tfStorageIdle.getId(), false)
                .setHardness(2.5F)
                .setStepSound(Block.soundMetalFootstep)
                .setCreativeTab(CreativeTabs.tabDecorations)
                .setUnlocalizedName("tofucraft:tfStorage")
                ;
        GameRegistry.registerBlock(tfStorageIdle, ItemTcBlock.class, "tfStorageIdle");
        MinecraftForge.setBlockHarvestLevel(tfStorageIdle, "pickaxe", 0);
        ModLoader.addName(tfStorageIdle, "en_US", "Tofu Force Storage");
        ModLoader.addName(tfStorageIdle, "ja_JP", "豆腐フォース蓄積マシン");

        tfStorageActive = new BlockTfStorage(Id.tfStorageActive.getId(), true)
                .setHardness(2.5F)
                .setStepSound(Block.soundMetalFootstep)
                .setLightValue(0.875F)
                .setUnlocalizedName("tofucraft:tfStorage")
                ;
        GameRegistry.registerBlock(tfStorageActive, "tfStorageActive");
        GameRegistry.registerTileEntity(TileEntityTfStorage.class, "tofucraft:TfStorage");
        MinecraftForge.setBlockHarvestLevel(tfStorageActive, "pickaxe", 0);
        ModLoader.addName(tfStorageActive, "en_US", "Tofu Force Storage");
        ModLoader.addName(tfStorageActive, "ja_JP", "豆腐フォース蓄積装置");

        /*
         * Ore
         */
        // Contained ore is set in TcItem.
        oreTofu = (BlockTcOre)new BlockTcOre(Id.oreTofu.getId(), 2, 5)
                .setHardness(3.0F)
                .setResistance(5.0F)
                .setStepSound(Block.soundStoneFootstep)
                .setUnlocalizedName("tofucraft:blockOreTofu");
        GameRegistry.registerBlock(oreTofu, ItemTcBlock.class, "blockOreTofu");
        MinecraftForge.setBlockHarvestLevel(oreTofu, "pickaxe", 1);
        ModLoader.addName(oreTofu, "en_US", "Tofu Ore");
        ModLoader.addName(oreTofu, "ja_JP", "豆腐鉱石");

        oreTofuDiamond = (BlockTcOre)new BlockTcOreDiamond(Id.oreTofuDiamond.getId(), 3, 7)
                .setHardness(1.0F)
                .setResistance(5.0F)
                .setStepSound(Block.soundSnowFootstep)
                .setUnlocalizedName("tofucraft:blockOreTofuDiamond");
        GameRegistry.registerBlock(oreTofuDiamond, ItemTcBlock.class, "blockOreTofuDiamond");
        MinecraftForge.setBlockHarvestLevel(oreTofuDiamond, "shovel", 0);
        ModLoader.addName(oreTofuDiamond, "en_US", "Tofu Diamond Ore");
        ModLoader.addName(oreTofuDiamond, "ja_JP", "金剛豆腐鉱石");

        /*
         * Fluid
         */
        soymilkStill = (BlockTcStationary)(new BlockSoymilkStill(Id.soymilk.getId() + 1, Material.water,
                new String[]{"tofucraft:soymilk", "tofucraft:soymilk_flow"}))
                .setHardness(100.0F)
                .setLightOpacity(8)
                .setUnlocalizedName("tofucraft:soymilk")
                ;
        GameRegistry.registerBlock(soymilkStill, "soymilkStill");
        ModLoader.addName(soymilkStill, "en_US", "Soy Milk");
        ModLoader.addName(soymilkStill, "ja_JP", "豆乳");

        soymilkHellStill = (BlockTcStationary)(new BlockTcStationary(Id.soymilkHell.getId() + 1, TcFluids.SOYMILK_HELL, Material.water,
                new String[]{"tofucraft:soymilkHell", "tofucraft:soymilkHell_flow"}))
                .setHardness(100.0F)
                .setLightOpacity(8)
                .setUnlocalizedName("tofucraft:soymilkHell")
                ;
        GameRegistry.registerBlock(soymilkHellStill, "soymilkHellStill");
        ModLoader.addName(soymilkHellStill, "en_US", "Hell Soy Milk");
        ModLoader.addName(soymilkHellStill, "ja_JP", "地獄豆乳");

        soySauceStill = (BlockTcStationary)(new BlockTcStationary(Id.soySauce.getId() + 1, TcFluids.SOY_SAUCE, Material.water,
                new String[]{"tofucraft:soySauce", "tofucraft:soySauce_flow"}))
                .setHardness(100.0F)
                .setLightOpacity(8)
                .setUnlocalizedName("tofucraft:soySauce")
                ;
        GameRegistry.registerBlock(soySauceStill, "soySauceStill");
        NameEntry.of(soySauceStill).nameEn("Soy Sauce").nameJa("醤油");

        /*
         * Trees
         */
        tcLeaves = (BlockTcLeaves)new BlockTcLeaves(Id.tcLeaves.getId())
                .setHardness(0.2F)
                .setLightOpacity(1)
                .setStepSound(Block.soundGrassFootstep)
                .setUnlocalizedName("tofucraft:leaves")
                ;
        GameRegistry.registerBlock(tcLeaves, ItemTcLeaves.class, "tcLeaves");
        NameEntry.of(tcLeaves)
                .forDmg(0).nameEn("Apricot Leaves").nameJa("杏の葉")
                .forDmg(2).nameEn("Tofu Leaves").nameJa("豆腐の葉");

        tcLog = (new BlockTcLog(Id.tcLog.getId()))
                .setHardness(2.0F)
                .setStepSound(Block.soundWoodFootstep)
                .setUnlocalizedName("tofucraft:log")
                ;
        {
            ItemBlock item = new ItemTcMultiTextureTile(tcLog.blockID - 256, tcLog, BlockTcLog.woodType);
            GameRegistry.registerItem(item, "tcWood");
        }
        MinecraftForge.setBlockHarvestLevel(tcLog, "axe", 0);

        NameEntry.of(tcLog)
                .forDmg(0).nameEn("Apricot Wood").nameJa("杏の原木");

        tcSapling = (new BlockTcSapling(Id.tcSapling.getId()))
                .setHardness(0.0F)
                .setStepSound(Block.soundGrassFootstep)
                .setUnlocalizedName("tofucraft:sapling")
                ;
        {
            ItemBlock item = new ItemTcMultiTextureTile(tcSapling.blockID - 256, tcSapling, BlockTcSapling.WOOD_TYPES);
            GameRegistry.registerItem(item, "tcSapling");
        }
        NameEntry.of(tcSapling)
                .forDmg(0).nameEn("Apricot Sapling").nameJa("杏の苗木")
                .forDmg(1).nameEn("Tofu Sapling").nameJa("豆腐の苗木");

        /*
         * Stairs
         */
        tofuStairsKinu = (new BlockTofuStairs(Id.tofuStairsKinu.getId(), tofuKinu, 0)).setFragile().setUnlocalizedName("tofucraft:stairsTofuKinu");
        GameRegistry.registerBlock(tofuStairsKinu, ItemTcBlock.class, "tofuStairsKinu");
        MinecraftForge.setBlockHarvestLevel(tofuStairsKinu, "shovel", 0);
        ModLoader.addName(tofuStairsKinu, "en_US", "Kinugoshi Tofu Stairs");
        ModLoader.addName(tofuStairsKinu, "ja_JP", "絹ごし豆腐の階段");

        tofuStairsMomen = (new BlockTofuStairs(Id.tofuStairsMomen.getId(), tofuMomen, 0)).setUnlocalizedName("tofucraft:stairsTofuMomen");
        GameRegistry.registerBlock(tofuStairsMomen, ItemTcBlock.class, "tofuStairsMomen");
        MinecraftForge.setBlockHarvestLevel(tofuStairsMomen, "shovel", 0);
        ModLoader.addName(tofuStairsMomen, "en_US", "Tofu Stairs");
        ModLoader.addName(tofuStairsMomen, "ja_JP", "木綿豆腐の階段");

        tofuStairsIshi = (new BlockTofuStairs(Id.tofuStairsIshi.getId(), tofuIshi, 0)).setUnlocalizedName("tofucraft:stairsTofuIshi");
        GameRegistry.registerBlock(tofuStairsIshi, ItemTcBlock.class, "tofuStairsIshi");
        MinecraftForge.setBlockHarvestLevel(tofuStairsIshi, "pickaxe", 0);
        ModLoader.addName(tofuStairsIshi, "en_US", "Solid Tofu Stairs");
        ModLoader.addName(tofuStairsIshi, "ja_JP", "石豆腐の階段");

        tofuStairsMetal = (new BlockTofuStairs(Id.tofuStairsMetal.getId(), tofuMetal, 0)).setUnlocalizedName("tofucraft:stairsTofuMetal");
        GameRegistry.registerBlock(tofuStairsMetal, ItemTcBlock.class, "tofuStairsMetal");
        MinecraftForge.setBlockHarvestLevel(tofuStairsMetal, "pickaxe", 1);
        ModLoader.addName(tofuStairsMetal, "en_US", "Metal Tofu Stairs");
        ModLoader.addName(tofuStairsMetal, "ja_JP", "鋼豆腐の階段");

        tofuStairsGrilled = (new BlockTofuStairs(Id.tofuStairsGrilled.getId(), tofuGrilled, 0)).setUnlocalizedName("tofucraft:stairsTofuGrilled");
        GameRegistry.registerBlock(tofuStairsGrilled, ItemTcBlock.class, "tofuStairsGrilled");
        MinecraftForge.setBlockHarvestLevel(tofuStairsGrilled, "shovel", 0);
        ModLoader.addName(tofuStairsGrilled, "en_US", "Grilled Tofu Stairs");
        ModLoader.addName(tofuStairsGrilled, "ja_JP", "焼き豆腐の階段");

        tofuStairsDried = (new BlockTofuStairs(Id.tofuStairsDried.getId(), tofuDried, 0)).setUnlocalizedName("tofucraft:stairsTofuDried");
        GameRegistry.registerBlock(tofuStairsDried, ItemTcBlock.class, "tofuStairsDried");
        MinecraftForge.setBlockHarvestLevel(tofuStairsDried, "shovel", 0);
        ModLoader.addName(tofuStairsDried, "en_US", "Koya-Tofu Stairs");
        ModLoader.addName(tofuStairsDried, "ja_JP", "高野豆腐の階段");

        tofuStairsFriedPouch = (new BlockTofuStairs(Id.tofuStairsFriedPouch.getId(), tofuFriedPouch, 0)).setUnlocalizedName("tofucraft:tofuStairsFriedPouch");
        GameRegistry.registerBlock(tofuStairsFriedPouch, ItemTcBlock.class, "tofuStairsFriedPouch");
        MinecraftForge.setBlockHarvestLevel(tofuStairsFriedPouch, "shovel", 0);
        ModLoader.addName(tofuStairsFriedPouch, "en_US", "Fried Tofu Pouch Stairs");
        ModLoader.addName(tofuStairsFriedPouch, "ja_JP", "揚げ豆腐の階段");

        tofuStairsFried = (new BlockTofuStairs(Id.tofuStairsFried.getId(), tofuFried, 0)).setUnlocalizedName("tofucraft:tofuStairsFried");
        GameRegistry.registerBlock(tofuStairsFried, ItemTcBlock.class, "tofuStairsFried");
        MinecraftForge.setBlockHarvestLevel(tofuStairsFried, "shovel", 0);
        ModLoader.addName(tofuStairsFried, "en_US", "Fried Tofu Stairs");
        ModLoader.addName(tofuStairsFried, "ja_JP", "厚揚げの階段");

        tofuStairsEgg = (new BlockTofuStairs(Id.tofuStairsEgg.getId(), tofuEgg, 0)).setUnlocalizedName("tofucraft:tofuStairsEgg");
        GameRegistry.registerBlock(tofuStairsEgg, ItemTcBlock.class, "tofuStairsEgg");
        MinecraftForge.setBlockHarvestLevel(tofuStairsEgg, "shovel", 0);
        ModLoader.addName(tofuStairsEgg, "en_US", "Egg Tofu Stairs");
        ModLoader.addName(tofuStairsEgg, "ja_JP", "玉子豆腐の階段");

        tofuStairsAnnin = (new BlockTofuStairs(Id.tofuStairsAnnin.getId(), tofuAnnin, 0)).setUnlocalizedName("tofucraft:tofuStairsAnnin");
        GameRegistry.registerBlock(tofuStairsAnnin, ItemTcBlock.class, "tofuStairsAnnin");
        MinecraftForge.setBlockHarvestLevel(tofuStairsAnnin, "shovel", 0);
        ModLoader.addName(tofuStairsAnnin, "en_US", "Annin-Tofu Stairs");
        ModLoader.addName(tofuStairsAnnin, "ja_JP", "杏仁豆腐の階段");

        tofuStairsSesame = (new BlockTofuStairs(Id.tofuStairsSesame.getId(), tofuSesame, 0)).setUnlocalizedName("tofucraft:tofuStairsSesame");
        GameRegistry.registerBlock(tofuStairsSesame, ItemTcBlock.class, "tofuStairsSesame");
        MinecraftForge.setBlockHarvestLevel(tofuStairsSesame, "shovel", 0);
        ModLoader.addName(tofuStairsSesame, "en_US", "Sesame Tofu Stairs");
        ModLoader.addName(tofuStairsSesame, "ja_JP", "胡麻豆腐の階段");

        tofuStairsZunda = (new BlockTofuStairs(Id.tofuStairsZunda.getId(), tofuZunda, 0)).setUnlocalizedName("tofucraft:tofuStairsZunda");
        GameRegistry.registerBlock(tofuStairsZunda, ItemTcBlock.class, "tofuStairsZunda");
        MinecraftForge.setBlockHarvestLevel(tofuStairsZunda, "shovel", 0);
        ModLoader.addName(tofuStairsZunda, "en_US", "Zunda Tofu Stairs");
        ModLoader.addName(tofuStairsZunda, "ja_JP", "ずんだ豆腐の階段");

        tofuStairsStrawberry = (new BlockTofuStairs(Id.tofuStairsStrawberry.getId(), tofuStrawberry, 0)).setUnlocalizedName("tofucraft:tofuStairsStrawberry");
        GameRegistry.registerBlock(tofuStairsStrawberry, ItemTcBlock.class, "tofuStairsStrawberry");
        MinecraftForge.setBlockHarvestLevel(tofuStairsStrawberry, "shovel", 0);
        ModLoader.addName(tofuStairsStrawberry, "en_US", "Strawberry Tofu Stairs");
        ModLoader.addName(tofuStairsStrawberry, "ja_JP", "いちごとうふの階段");

        tofuStairsHell = (new BlockTofuStairs(Id.tofuStairsHell.getId(), tofuHell, 0)).setUnlocalizedName("tofucraft:tofuStairsHell");
        GameRegistry.registerBlock(tofuStairsHell, ItemTcBlock.class, "tofuStairsHell");
        MinecraftForge.setBlockHarvestLevel(tofuStairsHell, "shovel", 0);
        ModLoader.addName(tofuStairsHell, "en_US", "Hell Tofu Stairs");
        ModLoader.addName(tofuStairsHell, "ja_JP", "地獄豆腐の階段");

        tofuStairsGlow = (new BlockTofuStairs(Id.tofuStairsGlow.getId(), tofuGlow, 0)).setLightValue(1.0F).setUnlocalizedName("tofucraft:tofuStairsGlow");
        GameRegistry.registerBlock(tofuStairsGlow, ItemTcBlock.class, "tofuStairsGlow");
        MinecraftForge.setBlockHarvestLevel(tofuStairsGlow, "shovel", 0);
        ModLoader.addName(tofuStairsGlow, "en_US", "Glow Tofu Stairs");
        ModLoader.addName(tofuStairsGlow, "ja_JP", "蛍豆腐の階段");

        tofuStairsDiamond = (new BlockTofuStairs(Id.tofuStairsDiamond.getId(), tofuDiamond, 0)).setUnlocalizedName("tofucraft:tofuStairsDiamond");
        GameRegistry.registerBlock(tofuStairsDiamond, ItemTcBlock.class, "tofuStairsDiamond");
        MinecraftForge.setBlockHarvestLevel(tofuStairsDiamond, "pickaxe", 1);
        ModLoader.addName(tofuStairsDiamond, "en_US", "Diamond Tofu Stairs");
        ModLoader.addName(tofuStairsDiamond, "ja_JP", "金剛豆腐の階段");

        tofuStairsMiso = (new BlockTofuStairs(Id.tofuStairsMiso.getId(), tofuMiso, 0)).setUnlocalizedName("tofucraft:tofuStairsMiso");
        GameRegistry.registerBlock(tofuStairsMiso, ItemTcBlock.class, "tofuStairsMiso");
        MinecraftForge.setBlockHarvestLevel(tofuStairsMiso, "shovel", 0);
        ModLoader.addName(tofuStairsMiso, "en_US", "Miso Tofu Stairs");
        ModLoader.addName(tofuStairsMiso, "ja_JP", "味噌漬け豆腐の階段");

        /*
         * Slabs
         */
        tofuSingleSlab1 = (BlockHalfSlab)(new BlockTofuStep(Id.tofuSingleSlab1.getId(), false, BlockTofuStep.blockStepTypes1, BlockTofuStep.typesObsolete1))
                .setHardness(1.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab");
        MinecraftForge.setBlockHarvestLevel(tofuSingleSlab1, "shovel", 0);
        ModLoader.addName(tofuSingleSlab1, "en_US", "Tofu Slab");
        
        tofuDoubleSlab1 = (BlockHalfSlab)(new BlockTofuStep(Id.tofuDoubleSlab1.getId(), true, BlockTofuStep.blockStepTypes1, BlockTofuStep.typesObsolete1))
                .setHardness(1.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab");
        MinecraftForge.setBlockHarvestLevel(tofuDoubleSlab1, "shovel", 0);
        ModLoader.addName(tofuDoubleSlab1, "en_US", "Tofu Slab");
        
        {
            ItemBlock item1 = (ItemBlock) new ItemTcSlab(tofuSingleSlab1.blockID - 256, tofuSingleSlab1, tofuDoubleSlab1, false).setUnlocalizedName("tofucraft:tofuSlab");
            GameRegistry.registerItem(item1, "tofuSingleSlab1");
            ItemBlock item2 = (ItemBlock) new ItemTcSlab(tofuDoubleSlab1.blockID - 256, tofuSingleSlab1, tofuDoubleSlab1, true).setUnlocalizedName("tofucraft:tofuSlab");
            GameRegistry.registerItem(item2, "tofuDoubleSlab1");
        }

        tofuSingleSlab2 = (BlockHalfSlab)(new BlockTofuStep(Id.tofuSingleSlab2.getId(), false, BlockTofuStep.blockStepTypes2, BlockTofuStep.typesObsolete2))
                .setHardness(1.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab");
        MinecraftForge.setBlockHarvestLevel(tofuSingleSlab2, "shovel", 0);
        ModLoader.addName(tofuSingleSlab2, "en_US", "Tofu Slab");
        tofuDoubleSlab2 = (BlockHalfSlab)(new BlockTofuStep(Id.tofuDoubleSlab2.getId(), true, BlockTofuStep.blockStepTypes2, BlockTofuStep.typesObsolete2))
                .setHardness(1.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab");
        MinecraftForge.setBlockHarvestLevel(tofuDoubleSlab2, "shovel", 0);
        ModLoader.addName(tofuDoubleSlab2, "en_US", "Tofu Slab");
        {
            ItemBlock item1 = (ItemBlock) new ItemTcSlab(tofuSingleSlab2.blockID - 256, tofuSingleSlab2, tofuDoubleSlab2, false).setUnlocalizedName("tofucraft:tofuSlab");
            GameRegistry.registerItem(item1, "tofuSingleSlab2");
            ItemBlock item2 = (ItemBlock) new ItemTcSlab(tofuDoubleSlab2.blockID - 256, tofuSingleSlab2, tofuDoubleSlab2, true).setUnlocalizedName("tofucraft:tofuSlab");
            GameRegistry.registerItem(item2, "tofuDoubleSlab2");
        }
        
        tofuSingleSlab3 = (BlockHalfSlab)(new BlockTofuStep(Id.tofuSingleSlab3.getId(), false, BlockTofuStep.blockStepTypes3, BlockTofuStep.typesObsolete3))
                .setHardness(1.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab");
        MinecraftForge.setBlockHarvestLevel(tofuSingleSlab3, "shovel", 0);
        ModLoader.addName(tofuSingleSlab3, "en_US", "Tofu Slab");
        tofuDoubleSlab3 = (BlockHalfSlab)(new BlockTofuStep(Id.tofuDoubleSlab3.getId(), true, BlockTofuStep.blockStepTypes3, BlockTofuStep.typesObsolete3))
                .setHardness(1.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab");
        MinecraftForge.setBlockHarvestLevel(tofuDoubleSlab3, "shovel", 0);
        ModLoader.addName(tofuDoubleSlab3, "en_US", "Tofu Slab");
        {
            ItemBlock item1 = (ItemBlock) new ItemTcSlab(tofuSingleSlab3.blockID - 256, tofuSingleSlab3, tofuDoubleSlab3, false).setUnlocalizedName("tofucraft:tofuSlab");
            GameRegistry.registerItem(item1, "tofuSingleSlab3");
            ItemBlock item2 = (ItemBlock) new ItemTcSlab(tofuDoubleSlab3.blockID - 256, tofuSingleSlab3, tofuDoubleSlab3, true).setUnlocalizedName("tofucraft:tofuSlab");
            GameRegistry.registerItem(item2, "tofuDoubleSlab3");
        }
        
        NameEntry.of(tofuDoubleSlab1)
                .forDmg(0).nameEn("Kinugoshi Tofu Slab").nameJa("絹ごし豆腐ハーフブロック")
                .forDmg(1).nameEn("Momen Tofu Slab").nameJa("木綿豆腐ハーフブロック")
                .forDmg(2).nameEn("Solid Tofu Slab").nameJa("石豆腐ハーフブロック")
                .forDmg(3).nameEn("Metal Tofu Slab").nameJa("鋼豆腐ハーフブロック")
                .forDmg(4).nameEn("Grilled Tofu Slab (Old)").nameJa("焼き豆腐ハーフブロック(旧)")
                .forDmg(5).nameEn("Koya-Tofu Slab").nameJa("高野豆腐ハーフブロック")
                .forDmg(6).nameEn("Fried Tofu Pouch Slab").nameJa("揚げ豆腐ハーフブロック")
                .forDmg(7).nameEn("Fried Tofu Slab").nameJa("厚揚げハーフブロック");

        NameEntry.of(tofuDoubleSlab2)
                .forDmg(0).nameEn("Egg Tofu Slab").nameJa("玉子豆腐ハーフブロック")
                .forDmg(1).nameEn("Annin-Tofu Tofu Slab").nameJa("杏仁豆腐ハーフブロック")
                .forDmg(2).nameEn("Sesame Tofu Slab").nameJa("胡麻豆腐ハーフブロック")
                .forDmg(3).nameEn("Zunda Tofu Slab").nameJa("ずんだ豆腐ハーフブロック")
                .forDmg(4).nameEn("Strawberry Tofu Slab").nameJa("いちごとうふハーフブロック")
                .forDmg(5).nameEn("Hell Tofu Slab").nameJa("地獄豆腐ハーフブロック")
                .forDmg(6).nameEn("Glow Tofu Slab").nameJa("蛍豆腐ハーフブロック")
                .forDmg(7).nameEn("Diamond Tofu Slab").nameJa("金剛豆腐ハーフブロック");

        NameEntry.of(tofuDoubleSlab3)
                .forDmg(0).nameEn("Miso Tofu Slab").nameJa("味噌漬け豆腐ハーフブロック");
        
        tofuSingleSlabFaces = (BlockHalfSlab)(new BlockTofuStepFaces(Id.tofuSingleSlabFaces.getId(), false))
                .setHardness(1.0F).setResistance(50.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab.grilled");
        MinecraftForge.setBlockHarvestLevel(tofuSingleSlabFaces, "shovel", 0);
        ModLoader.addName(tofuSingleSlabFaces, "en_US", "Tofu Slab");

        tofuDoubleSlabFaces = (BlockHalfSlab)(new BlockTofuStepFaces(Id.tofuDoubleSlabFaces.getId(), true))
                .setHardness(1.0F).setResistance(50.0F).setStepSound(Block.soundSnowFootstep).setUnlocalizedName("tofucraft:tofuSlab.grilled");
        MinecraftForge.setBlockHarvestLevel(tofuSingleSlabFaces, "shovel", 0);
        ModLoader.addName(tofuSingleSlabFaces, "en_US", "Tofu Slab");
        {
            ItemBlock item1 = (ItemBlock) new ItemTcSlabFaces(tofuSingleSlabFaces.blockID - 256, tofuSingleSlabFaces, tofuDoubleSlabFaces, false).setUnlocalizedName("tofucraft:tofuSlab.grilled");
            GameRegistry.registerItem(item1, "tofuSingleSlabFaces");
            ItemBlock item2 = (ItemBlock) new ItemTcSlabFaces(tofuDoubleSlabFaces.blockID - 256, tofuSingleSlabFaces, tofuDoubleSlabFaces, true).setUnlocalizedName("tofucraft:tofuSlab.grilled");
            GameRegistry.registerItem(item2, "tofuDoubleSlabFaces");
        }
        NameEntry.of(tofuDoubleSlabFaces).nameEn("Grilled Tofu Slab").nameJa("焼き豆腐ハーフブロック");
        
        tofuSingleSlabGlow = (BlockHalfSlab)(new BlockTofuStepSimple(Id.tofuSingleSlabGlow.getId(), false))
                .setHardness(0.5F).setLightValue(0.9375F).setStepSound(Block.soundSnowFootstep).setTextureName("tofucraft:blockTofuGlow").setUnlocalizedName("tofucraft:tofuSlab.glow");
        MinecraftForge.setBlockHarvestLevel(tofuSingleSlabGlow, "shovel", 0);
        ModLoader.addName(tofuSingleSlabGlow, "en_US", "Tofu Slab");

        tofuDoubleSlabGlow = (BlockHalfSlab)(new BlockTofuStepSimple(Id.tofuDoubleSlabGlow.getId(), true))
                .setHardness(0.5F).setLightValue(0.9375F).setStepSound(Block.soundSnowFootstep).setTextureName("tofucraft:blockTofuGlow").setUnlocalizedName("tofucraft:tofuSlab.glow");
        MinecraftForge.setBlockHarvestLevel(tofuDoubleSlabGlow, "shovel", 0);
        ModLoader.addName(tofuDoubleSlabGlow, "en_US", "Tofu Slab");
        {
            ItemBlock item1 = (ItemBlock) new ItemTcSlab(tofuSingleSlabGlow.blockID - 256, tofuSingleSlabGlow, tofuDoubleSlabGlow, false).setUnlocalizedName("tofucraft:tofuSlab.glow");
            GameRegistry.registerItem(item1, "tofuSingleSlabGlow");
            ItemBlock item2 = (ItemBlock) new ItemTcSlab(tofuDoubleSlabGlow.blockID - 256, tofuSingleSlabGlow, tofuDoubleSlabGlow, true).setUnlocalizedName("tofucraft:tofuSlab.glow");
            GameRegistry.registerItem(item2, "tofuDoubleSlabGlow");
        }
        NameEntry.of(tofuDoubleSlabGlow).nameEn("Glow Tofu Slab").nameJa("蛍豆腐ハーフブロック");

    }
    
    /**
     * === External Mod Items ===
     */
    public static void registerExternalModBlocks()
    {
        if (ModLoader.isModLoaded("BambooMod"))
        {
            sakuraLeaves = Utils.getExternalModBlockWithRegex("(?i)sakuraleaves");
            ModLog.debug("sakuraLeaves: " + sakuraLeaves);
        }
    }

    public TcBlock(int par1, Material par2Material)
    {
        super(par1, par2Material);
    }

    @Override
    public TcBlock setUnlocalizedName(String name)
    {
        super.setUnlocalizedName(name);
        this.setTextureName(name);
        return this;
    }
}
