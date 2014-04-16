package tsuteto.tofu.eventhandler;

import net.minecraft.entity.player.EntityPlayer;
import tsuteto.tofu.TofuCraftCore;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.relauncher.Side;

public class EventPlayer implements IPlayerTracker
{
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        if (TofuCraftCore.update != null)
        {
            TofuCraftCore.update.notifyUpdate(player, Side.CLIENT);
        }
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
        // TODO Auto-generated method stub
        
    }}
