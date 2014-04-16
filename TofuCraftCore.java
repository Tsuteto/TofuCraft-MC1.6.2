package tsuteto.tofu;

import java.util.Arrays;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.DispenserBehaviorEmptyBucket;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofu.achievement.TcAchievementList;
import tsuteto.tofu.block.RenderYubaBlock;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.block.tileentity.TileEntityMorijio;
import tsuteto.tofu.dispanse.DispenserBehaviorTcEmptyBucket;
import tsuteto.tofu.entity.TcEntity;
import tsuteto.tofu.entity.TofuCreeperSeed;
import tsuteto.tofu.eventhandler.EventBonemeal;
import tsuteto.tofu.eventhandler.EventEntityLiving;
import tsuteto.tofu.eventhandler.EventFillBucket;
import tsuteto.tofu.eventhandler.EventItemPickup;
import tsuteto.tofu.eventhandler.EventPlayer;
import tsuteto.tofu.eventhandler.EventPlayerInteract;
import tsuteto.tofu.eventhandler.GameTickHandler;
import tsuteto.tofu.eventhandler.TcCraftingHandler;
import tsuteto.tofu.fishing.TofuFishing;
import tsuteto.tofu.fluids.TcFluids;
import tsuteto.tofu.gui.TcGuiHandler;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.packet.NetChannelBugle;
import tsuteto.tofu.packet.NetChannelDimTrip;
import tsuteto.tofu.packet.NetChannelGlowingFinish;
import tsuteto.tofu.packet.NetChannelTfMachineData;
import tsuteto.tofu.packet.NetChannelTofuRadar;
import tsuteto.tofu.packet.NetChannelZundaArrowHit;
import tsuteto.tofu.packet.NetChannelZundaArrowType;
import tsuteto.tofu.packet.TcPacketHandler;
import tsuteto.tofu.potion.TcPotion;
import tsuteto.tofu.recipe.Recipes;
import tsuteto.tofu.recipe.TcOreDic;
import tsuteto.tofu.sound.SoundHandler;
import tsuteto.tofu.util.ModLog;
import tsuteto.tofu.util.UpdateNotification;
import tsuteto.tofu.village.ComponentVillageHouseTofu;
import tsuteto.tofu.village.TofuCookHouseHandler;
import tsuteto.tofu.village.TradeHandlerFarmer;
import tsuteto.tofu.village.TradeHandlerTofuCook;
import tsuteto.tofu.village.TradeHandlerTofunian;
import tsuteto.tofu.world.BiomeGenTofuBase;
import tsuteto.tofu.world.TcChunkProviderEvent;
import tsuteto.tofu.world.WorldProviderTofu;
import tsuteto.tofu.world.tofuvillage.EntityJoinWorldEventHandler;
import tsuteto.tofu.world.tofuvillage.GetVillageBlockIDEventHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * The Main Class of the TofuCraft
 *
 * @author Tsuteto
 *
 */
@Mod(modid = TofuCraftCore.modid, version = "1.5.14-MC1.6.2", acceptedMinecraftVersions = "[1.6.2,1.7)")
@NetworkMod(
    clientSideRequired = true, serverSideRequired = false,
    channels = {
            TofuCraftCore.netChannelDimTrip,
            TofuCraftCore.netChannelBugle,
            TofuCraftCore.netChannelZundaArrowHit,
            TofuCraftCore.netChannelZundaArrowType,
            TofuCraftCore.netChannelTofuRadar,
            TofuCraftCore.netChannelGlowingFinish,
            TofuCraftCore.netChannelTfMachineData
    },
    packetHandler = TcPacketHandler.class,
    versionBounds = "Forge@(,)"
)
public class TofuCraftCore
{
    public static final String modid = "TofuCraft";

    // Packet channel names
    public static final String netChannelDimTrip = "DimTrip";
    public static final String netChannelBugle = "Bugle";
    public static final String netChannelZundaArrowHit = "ZundaHit";
    public static final String netChannelZundaArrowType = "ZundaType";
    public static final String netChannelTofuRadar = "TofuRadar";
    public static final String netChannelGlowingFinish = "GlowingFinish";
    public static final String netChannelTfMachineData = "tfv";

    @Mod.Instance(modid)
    public static TofuCraftCore instance;
    
    @Mod.Metadata(modid)
    public static ModMetadata metadata;

    @SidedProxy(clientSide = "tsuteto.tofu.TofuCraftCore$ClientProxy", serverSide = "tsuteto.tofu.TofuCraftCore$ServerProxy")
    public static ISidedProxy sidedProxy;
    
    public static BiomeDictionary.Type BIOME_TYPE_TOFU = EnumHelper.addEnum(BiomeDictionary.Type.class, "TOFU", new Class[0], new Object[0]);
    public static CreativeTabs tabTofuCraft;
    public static UpdateNotification update = null;
    
    private Configuration conf;

    static
    {
        ModLog.modId = TofuCraftCore.modid;
    }

    @EventHandler
    public void preload(FMLPreInitializationEvent event)
    {
        conf = new Configuration(event.getSuggestedConfigurationFile());
        conf.load();

        Settings.load(conf);
        ModLog.isDebug = Settings.debug;

        TcBlock.loadId(conf);
        TcItem.loadId(conf);

        conf.save();

        // Install sound files if not yet
        TofuCraftCore.sidedProxy.installSoundFiles();

        // Register basic features
        TcBlock.register();
        TcItem.register();
        TcEntity.register(this);
        TcOreDic.load();

        // Register liquid blocks
        TcFluids.register(event.getSide());
        
        // Prepare Tofu Force Materials
        TfMaterialRegistry.init();

        // Add Achievements
        if (Settings.achievement)
        {
            TcAchievementList.load();
        }
        
        // Update check!
        if (Settings.updateCheck)
        {
            update = new UpdateNotification();
            update.checkUpdate();
        }
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	// Create a creative tab for this mod
    	tabTofuCraft = new CreativeTabTofuCraft(modid);

        // Register usage of the bone meal
        MinecraftForge.EVENT_BUS.register(new EventBonemeal());

        // Register usage of the empty bucket
        MinecraftForge.EVENT_BUS.register(new EventFillBucket());

        // Register event on player interact
        MinecraftForge.EVENT_BUS.register(new EventPlayerInteract());

        // Register event on EntityLiving
        MinecraftForge.EVENT_BUS.register(new EventEntityLiving());

        // Register event on tofu fishing
        MinecraftForge.EVENT_BUS.register(new TofuFishing());

        // Register event on player
        GameRegistry.registerPlayerTracker(new EventPlayer());

        // Register gui handler
        NetworkRegistry.instance().registerGuiHandler(this, new TcGuiHandler());

        // Register crafting handler
        GameRegistry.registerCraftingHandler(new TcCraftingHandler());

        // Register pick-up Handler
        GameRegistry.registerPickupHandler(new EventItemPickup());

        {
            TcChunkProviderEvent eventhandler = new TcChunkProviderEvent();
            
            // Nether populating
            MinecraftForge.EVENT_BUS.register(eventhandler);
    
            // Ore generation
            MinecraftForge.ORE_GEN_BUS.register(eventhandler);
        }
        
        // Register the Tofu World
        DimensionManager.registerProviderType(Settings.tofuDimNo, WorldProviderTofu.class, false);
        DimensionManager.registerDimension(Settings.tofuDimNo, Settings.tofuDimNo);
        
        // Register Tofu World biomes to the Forge Biome Dictionary
        for (BiomeGenTofuBase biome : BiomeGenTofuBase.tofuBiomeList)
        {
            if (biome != null) BiomeDictionary.registerBiomeType(biome, BIOME_TYPE_TOFU);
        }
        ModLog.debug("Registered biomes as TOFU: " + Arrays.toString(BiomeDictionary.getBiomesForType(BIOME_TYPE_TOFU)));
        
        // Tofu Village handler
        if (event.getSide() == Side.CLIENT || event.getSide() == Side.SERVER && ForgeVersion.getBuildVersion() >= 900)
        {
            BiomeManager.addVillageBiome(BiomeGenTofuBase.tofuPlains, true);
            BiomeManager.addVillageBiome(BiomeGenTofuBase.tofuLeekPlains, true);
            BiomeManager.addVillageBiome(BiomeGenTofuBase.tofuForest, true);
            MinecraftForge.TERRAIN_GEN_BUS.register(new GetVillageBlockIDEventHandler());
            MinecraftForge.EVENT_BUS.register(new EntityJoinWorldEventHandler());
        }

        // Register the profession of Tofu Cook
        VillagerRegistry vill = VillagerRegistry.instance();
        vill.registerVillagerId(Settings.professionIdTofucook);
        vill.registerVillageTradeHandler(Settings.professionIdTofucook, new TradeHandlerTofuCook());

        // Register the profession of Tofunian
        vill.registerVillagerId(Settings.professionIdTofunian);
        vill.registerVillageTradeHandler(Settings.professionIdTofunian, new TradeHandlerTofunian());

        // Add Trade Recipes
        vill.registerVillageTradeHandler(0, new TradeHandlerFarmer());
        
        // Tofu cook's house
        vill.registerVillageCreationHandler(new TofuCookHouseHandler());
        // To be applicable to 1.6.4
        if (ForgeVersion.getBuildVersion() >= 875)
        {
            net.minecraft.world.gen.structure.MapGenStructureIO.func_143031_a(ComponentVillageHouseTofu.class, "ViTfH");
        }

        // Register Packet Handler
        if (event.getSide() == Side.CLIENT)
        {
            TcPacketHandler.handlerRegistry.put(netChannelDimTrip, new NetChannelDimTrip());
            TcPacketHandler.handlerRegistry.put(netChannelBugle, new NetChannelBugle());
            TcPacketHandler.handlerRegistry.put(netChannelZundaArrowHit, new NetChannelZundaArrowHit());
            TcPacketHandler.handlerRegistry.put(netChannelZundaArrowType, new NetChannelZundaArrowType());
            TcPacketHandler.handlerRegistry.put(netChannelTofuRadar, new NetChannelTofuRadar());
            TcPacketHandler.handlerRegistry.put(netChannelGlowingFinish, new NetChannelGlowingFinish());
            TcPacketHandler.handlerRegistry.put(netChannelTfMachineData, new NetChannelTfMachineData());
        }

        // Add chest loot
        this.registerChestLoot(new ItemStack(TcItem.defattingPotion), 1, 1, 8);
        this.registerChestLoot(new ItemStack(TcBlock.tcSapling, 1, 0), 1, 4, 8);
        this.registerChestLoot(new ItemStack(TcItem.doubanjiang), 1, 1, 4);

        ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH,
                new WeightedRandomChestContent(new ItemStack(TcItem.bugle), 1, 1, 5));

        // Ore Dictionary additions for common use
        OreDictionary.registerOre("logWood", new ItemStack(TcBlock.tcLog, 1, OreDictionary.WILDCARD_VALUE));
        
        // Register recipes
        Recipes.unifyOreDicItems();
        Recipes.register();

        // Register renderer of yuba
        RenderingRegistry.registerBlockHandler(new RenderYubaBlock());

        // Register sided components
        sidedProxy.registerComponents();

        Message.load();

        TickRegistry.registerTickHandler(new GameTickHandler(), Side.SERVER);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        TcItem.registerExternalModItems();
        TcBlock.registerExternalModBlocks();
        Recipes.registerExternalModRecipes();
        
        // Register potion effects
        TcPotion.register(conf);
        
        conf.save();
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        event.registerServerCommand(new CommandTofuSlimeCheck());

        // Replace dispenser behavior for empty bucket
        if (BlockDispenser.dispenseBehaviorRegistry.getObject(Item.bucketEmpty) instanceof DispenserBehaviorEmptyBucket)
        {
            BlockDispenser.dispenseBehaviorRegistry.putObject(Item.bucketEmpty, new DispenserBehaviorTcEmptyBucket());
        }
        
        // To handle spawn of Tofu Creeper ;)
        TofuCreeperSeed.initialize(12L);
        TofuCreeperSeed.instance().initSeed(event.getServer().worldServerForDimension(0).getSeed());
        
        // Notify if update is available
        if (update != null && event.getSide() == Side.SERVER)
        {
            update.notifyUpdate(event.getServer(), event.getSide());
        }
    }

    public void registerChestLoot(ItemStack loot, int min, int max, int rarity)
    {
        ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST,
                new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR,
                new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR,
                new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING,
                new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_LIBRARY,
                new WeightedRandomChestContent(loot, min, max, rarity));
        ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST,
                new WeightedRandomChestContent(loot, min, max, rarity));
    }

    @SideOnly(Side.CLIENT)
    public static class ClientProxy implements ISidedProxy
    {
        @Override
        public void registerComponents()
        {
            TcEntity.registerEntityRenderer();

            VillagerRegistry vill = VillagerRegistry.instance();
            vill.registerVillagerSkin(Settings.professionIdTofucook, new ResourceLocation("tofucraft", "textures/mob/tofucook.png"));
        }
        @Override
        public void registerTextures(String textureFile)
        {
        }
        @Override
        public void installSoundFiles()
        {
            MinecraftForge.EVENT_BUS.register(new SoundHandler());
        }
    }

    @SideOnly(Side.SERVER)
    public static class ServerProxy implements ISidedProxy
    {
        @Override
        public void registerComponents()
        {
            GameRegistry.registerTileEntity(TileEntityMorijio.class, "TmMorijio");
        }
        @Override
        public void registerTextures(String textureFile)
        {
        }
        @Override
        public void installSoundFiles()
        {
        }
    }

    public static interface ISidedProxy
    {
        public void registerComponents();
        public void registerTextures(String textureFile);
        public void installSoundFiles();
    }
}
