package tsuteto.tofu.eventhandler;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import tsuteto.tofu.achievement.TcAchievementMgr;
import cpw.mods.fml.common.IPickupNotifier;

public class EventItemPickup implements IPickupNotifier
{

    @Override
    public void notifyPickup(EntityItem item, EntityPlayer player)
    {
        if (item.getEntityItem() != null)
        {
            TcAchievementMgr.achieveItemPickup(item.getEntityItem(), player);
        }
    }
}
