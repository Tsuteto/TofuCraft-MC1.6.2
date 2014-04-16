package tsuteto.tofu.eventhandler;

import java.util.EnumSet;

import tsuteto.tofu.params.DataType;
import tsuteto.tofu.params.EntityInfo;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class GameTickHandler implements ITickHandler
{

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        // 
        EntityInfo pinfo = EntityInfo.instance();
        Integer[] array = pinfo.getEntitySet().toArray(new Integer[0]);
        for (int i = 0; i < array.length; i++)
        {
            int entityId = array[i];
            if (pinfo.doesDataExist(entityId, DataType.TicksPortalCooldown))
            {
                int ticks = pinfo.get(entityId, DataType.TicksPortalCooldown);
    
                if (ticks >= 20)
                {
                    pinfo.remove(entityId, DataType.TicksPortalCooldown);
                }
                else
                {
                    pinfo.set(entityId, DataType.TicksPortalCooldown, ++ticks);
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
