package tsuteto.tofu.potion;

import java.util.logging.Level;

import net.minecraft.potion.Potion;
import net.minecraftforge.common.Configuration;
import tsuteto.tofu.item.ItemTcFood;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.util.ModLog;

public class TcPotion
{
    private static final String CONF_CATEGORY = "potion";

    public static Potion glowing = null;
    public static Potion filling = null;
    
    public static void register(Configuration conf)
    {
        int id;
        
        try
        {
            id = assignId("glowing", conf);
            glowing = new PotionGlowing(id, false, 0xcccc00).setPotionName("potion.glowing");
            
            id = assignId("filling", conf);
            filling = new PotionFilling(id, false, 0xffa734).setPotionName("potion.filling");
        }
        catch (Exception e)
        {
            ModLog.log(Level.WARNING, e, e.getLocalizedMessage());
        }
        
        if (TcPotion.filling != null)
        {
            ((ItemTcFood)TcItem.tofuMiso).setPotionEffect(TcPotion.filling.id, 300, 0, 1.0F);
        }

        if (TcPotion.glowing != null)
        {
            ((ItemTcFood)TcItem.tofuGlow).setPotionEffect(TcPotion.glowing.id, 240, 0, 1.0F);
        }
    }
    
    public static int assignId(String confKey, Configuration conf) throws Exception
    {
        if (conf.hasKey(CONF_CATEGORY, confKey))
        {
            int id = conf.get(CONF_CATEGORY, confKey, -1).getInt();
            if (id != -1)
            {
                return id;
            }
        }

        // Find an undefined entry
        for (int i = Potion.potionTypes.length - 1; i >= 0; i--)
        {
            Potion potion = Potion.potionTypes[i];
            if (potion == null)
            {
                conf.get(CONF_CATEGORY, confKey, i).set(i);
                return i;
            }
        }
        
        // Unable to find entry
        throw new Exception("Failed to register a POTION effect. Seems to be running out of potion ID.");
    }
}
