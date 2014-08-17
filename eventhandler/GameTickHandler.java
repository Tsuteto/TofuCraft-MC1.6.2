package tsuteto.tofu.eventhandler;

import java.util.EnumSet;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import tsuteto.tofu.params.DataType;
import tsuteto.tofu.params.EntityInfo;
import tsuteto.tofu.params.PortalTripInfo;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class GameTickHandler implements ITickHandler
{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        // 
        EntityInfo pinfo = EntityInfo.instance();
        Integer[] array = pinfo.getEntitySet().toArray(new Integer[pinfo.getEntitySet().size()]);
        for (Integer entityId : array)
        {
            if (pinfo.doesDataExist(entityId, DataType.TicksPortalCooldown))
            {
                PortalTripInfo info = pinfo.get(entityId, DataType.TicksPortalCooldown);
                MinecraftServer server = MinecraftServer.getServer();
                World world = server.worldServerForDimension(info.dimensionIdTripTo);
                if (world != null)
                {
                    Entity entity = world.getEntityByID(entityId);
                    if (entity != null && entity.addedToChunk && entity.worldObj.blockExists(
                            (int)entity.posX, (int)entity.posY, (int)entity.posZ))
                    {
                        int ticks = info.ticksCooldown;
                        if (ticks >= 20)
                        {
                            pinfo.remove(entityId, DataType.TicksPortalCooldown);
                        }
                        else
                        {
                            info.ticksCooldown += 1;
                        }
                    }
                }
            }
        }
    }


    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }

    @Override
    public String getLabel()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
