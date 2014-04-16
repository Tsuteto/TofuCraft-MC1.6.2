package tsuteto.tofu.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelBugle implements INetChannelHandler
{
    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        EntityPlayer entityplayer = (EntityPlayer)player;

        ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
        float x = data.readFloat();
        float y = data.readFloat();
        float z = data.readFloat();
        int entityId = data.readInt();

        if (entityplayer.entityId == entityId)
        {
            mc.sndManager.playSoundFX("tofucraft:tofubugle", 1.0F, 1.0F);
        }
        else
        {
            mc.sndManager.playSound("tofucraft:tofubugle", x, y, z, 1.0F, 1.0F);
        }
    }

}
