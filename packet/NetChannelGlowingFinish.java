package tsuteto.tofu.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tsuteto.tofu.glowtofu.GlowingHandler;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelGlowingFinish implements INetChannelHandler
{

    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

        Minecraft mc = FMLClientHandler.instance().getClient();
        int entityId = data.readInt();
        
        Entity entity = mc.theWorld.getEntityByID(entityId);
        
        if (entity != null && entity instanceof EntityLivingBase)
        {
            GlowingHandler.removeLight(mc.theWorld, (EntityLivingBase)entity);
        }
    }

}
