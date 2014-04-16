package tsuteto.tofu.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tsuteto.tofu.block.tileentity.ContainerTfMachine;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelTfMachineData implements INetChannelHandler
{

    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

        Minecraft mc = FMLClientHandler.instance().getClient();
        EntityClientPlayerMP entityclientplayermp = mc.thePlayer;
        
        int windowId = data.readByte();
        int dataId = data.readShort();

        if (entityclientplayermp.openContainer != null && entityclientplayermp.openContainer.windowId == windowId)
        {
            ((ContainerTfMachine)entityclientplayermp.openContainer).updateTfMachineData(dataId, data);
        }

    }

}
