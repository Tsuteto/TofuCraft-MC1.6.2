package tsuteto.tofu.eventhandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.util.ModLog;

public class EventEntityLiving
{
//    @ForgeSubscribe
//    public void onUpdate(LivingUpdateEvent event)
//    {
//        EntityLivingBase living = event.entityLiving;
//        EntityInfo pinfo = EntityInfo.instance();
//        if (pinfo.doesDataExist(living.entityId, DataType.TicksPortalCooldown))
//        {
//            int ticks = pinfo.getInt(living.entityId, DataType.TicksPortalCooldown);
//
//            if (ticks >= 20)
//            {
//                pinfo.remove(living.entityId, DataType.TicksPortalCooldown);
//            }
//            else
//            {
//                pinfo.set(living.entityId, DataType.TicksPortalCooldown, ++ticks);
//            }
//            if (living instanceof EntityPlayer)
//            {
//                ModLog.debug(ticks);
//            }
//        }
//    }

    @ForgeSubscribe
    public void onSpawn(LivingSpawnEvent.CheckSpawn event)
    {
        World world = event.world;
        EntityLivingBase living = event.entityLiving;
        
        if (living instanceof IMob)
        {
            int tileX = (int)event.x;
            int tileY = (int)event.y;
            int tileZ = (int)event.z;
            
            int i, j, k, id;
            for (i = -10; i <= 10; i++)
            {
                for (j = -10; j <= 10; j++)
                {
                    for (k = -10; k <= 10; k++)
                    {
                        id = world.getBlockId(tileX + i, tileY + j, tileZ + k);
                        if (id == TcBlock.morijio.blockID)
                        {
                            event.setResult(Result.DENY);
                            ModLog.debug("%s canceled spawning by Morishio at (%.1f, %.1f, %.1f)", living.getEntityName(), event.x, event.y, event.z);
                            return;
                        }
                    }
                }
            }
        }
    }
}
