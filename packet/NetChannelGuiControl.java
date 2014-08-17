package tsuteto.tofu.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tsuteto.tofu.block.tileentity.ContainerTfMachine;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelGuiControl implements INetChannelHandler
{
    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        EntityPlayer entityplayer = (EntityPlayer)player;

        ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
        int eventId = data.readByte();
        int windowId = data.readShort();

        if (entityplayer.openContainer != null && entityplayer.openContainer.windowId == windowId)
        {
            ((ContainerTfMachine)entityplayer.openContainer).onGuiControl(eventId, data);
        }
    }

}
