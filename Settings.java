package tsuteto.tofu;

import net.minecraftforge.common.Configuration;

public class Settings
{
    public static int itemShiftIndex = 0;
    public static int blockShiftIndex = 0;

    public static EnumCreativeTabOptions creativeTab = EnumCreativeTabOptions.ORIGINAL;

    public static int entityIdTofuSlime = -1;
    public static int entityIdTofuCreeper = -1;
    public static int entityIdTofunian = -1;

    public static int tofuDimNo = 12;

    public static int tofuBiomeId = 23;

    public static int professionIdTofucook = 1212;
    public static int professionIdTofunian = 1213;

    //public static int potionGlowingId;
    
    public static int achievementInitId = 120001;
    public static boolean debug = false;
    public static boolean autoAssign = true;
    public static boolean achievement = true;
    public static int clientGlowTofuLightInterval = 2;
    public static int serverGlowTofuLightInterval = 5;
    public static boolean safeFor162;
    public static boolean updateCheck = true;

    public static void load(Configuration conf)
    {
        tofuDimNo = conf.get("general", "tofuDim", tofuDimNo).getInt();
        tofuBiomeId = conf.get("general", "tofuBiomeId", tofuBiomeId).getInt();

        // For initial start-up
        boolean autoAssignDefault;
        if (conf.hasKey("general", "itemShiftIndex") || conf.hasKey("general", "blockShiftIndex"))
        {
            autoAssignDefault = false;
        }
        else
        {
            autoAssignDefault = true;
        }

        itemShiftIndex = conf.get("general", "itemShiftIndex", itemShiftIndex).getInt();
        blockShiftIndex  = conf.get("general", "blockShiftIndex", blockShiftIndex).getInt();
        autoAssign = conf.get("general", "autoAssign", autoAssignDefault,
                "If true, item/block IDs are registered by Forge auto assignment, ignoring itemShiftIndex/blockShiftIndex.").getBoolean(autoAssignDefault);

        professionIdTofucook = conf.get("villager", "tofucookId", professionIdTofucook).getInt();
        professionIdTofunian = conf.get("villager", "tofunianId", professionIdTofunian).getInt();

        entityIdTofuSlime = conf.get("entity", "tofuSlimeId", entityIdTofuSlime).getInt();
        entityIdTofuCreeper = conf.get("entity", "tofuCreeperId", entityIdTofuCreeper,
                "Followings are entity IDs to be assigned. -1 for auto assignment.").getInt();
        entityIdTofunian = conf.get("entity", "tofunianId", entityIdTofunian).getInt();
        
        //potionGlowingId = conf.get("potion", "glowingId", TcPotion.assignNewId()).getInt();

        creativeTab = EnumCreativeTabOptions.values()[conf.get("general", "creativeTab", creativeTab.ordinal(),
                "Creative tab which shows TofuCraft items and blocks. 0=original tab, 1=sorted into vanilla tabs, 2=both").getInt()];
        achievementInitId = conf.get("general", "achievementInitId", achievementInitId).getInt();
//        demoAchievement = conf.get("general", "demoAchievement", demoAchievement,
//                "Achievement is work in progress (WIP). Note that there are few achievements and they will be lost at future update.").getBoolean(false);
        achievement = conf.get("general", "achievement", achievement).getBoolean(true);
        
        updateCheck = conf.get("general", "updateCheck", updateCheck).getBoolean(true);
        
        debug = conf.get("general", "debug", debug).getBoolean(false);
    }

    public enum EnumCreativeTabOptions {
    	ORIGINAL, SORTED, BOTH;
    }

}
