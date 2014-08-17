package tsuteto.tofu.achievement;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import tsuteto.tofu.achievement.TcAchievementMgr.Key;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class TcAchievement extends Achievement
{
    public final TcAchievementMgr.Key key;
    private AchievementTrigger trigger;

    public static TcAchievement create(Key key, int par3, int par4, Object obj, Key relation)
    {
        if (obj instanceof Block)
        {
            return new TcAchievement(key, par3, par4, (Block)obj, relation);
        }
        else if (obj instanceof Item)
        {
            return new TcAchievement(key, par3, par4, (Item)obj, relation);
        }
        else if (obj instanceof ItemStack)
        {
            return new TcAchievement(key, par3, par4, (ItemStack)obj, relation);
        }

        throw new RuntimeException("Caught an error in achievement registration.");
    }

    private TcAchievement(Key key, int par3, int par4, Block block, Key relation)
    {
        super(key.id, "tofucraft:" + key.toString(), par3, par4, block, TcAchievementMgr.get(relation));
        this.key = key;
    }

    private TcAchievement(Key key, int par3, int par4, Item item, Key relation)
    {
        super(key.id, "tofucraft:" + key.toString(), par3, par4, item, TcAchievementMgr.get(relation));
        this.key = key;
    }

    private TcAchievement(Key key, int par3, int par4, ItemStack itemstack, Key relation)
    {
        super(key.id, "tofucraft:" + key.toString(), par3, par4, itemstack, TcAchievementMgr.get(relation));
        this.key = key;
    }

    public TcAchievement setTriggerItemPickup(ItemStack item)
    {
        this.trigger = new TriggerItem(item);
        TcAchievementMgr.itemPickupMap.add(this);
        return this;
    }

    public TcAchievement setTriggerItemCrafting(ItemStack item)
    {
        this.trigger = new TriggerItem(item);
        TcAchievementMgr.itemCraftingMap.add(this);
        return this;
    }

    public TcAchievement setTriggerSmelting(ItemStack item)
    {
        this.trigger = new TriggerItem(item);
        TcAchievementMgr.itemSmeltingMap.add(this);
        return this;
    }

    public TcAchievement setTitle(String en, String ja)
    {
        LanguageRegistry.instance().addStringLocalization(this.statName, "en_US", en);
        LanguageRegistry.instance().addStringLocalization(this.statName, "ja_JP", ja);
        return this;
    }

    public TcAchievement setDesc(String en, String ja)
    {
        LanguageRegistry.instance().addStringLocalization(this.statName + ".desc", "en_US", en);
        LanguageRegistry.instance().addStringLocalization(this.statName + ".desc", "ja_JP", ja);
        return this;
    }

    @Override
    public TcAchievement setIndependent()
    {
        super.setIndependent();
        return this;
    }

    @Override
    public TcAchievement setSpecial()
    {
        super.setSpecial();
        return this;
    }

    @Override
    public TcAchievement registerAchievement()
    {
        super.registerAchievement();
        TcAchievementMgr.add(this);
        return this;
    }

    public AchievementTrigger getTrigger()
    {
        return this.trigger;
    }
}

