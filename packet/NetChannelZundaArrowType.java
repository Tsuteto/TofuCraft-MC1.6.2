package tsuteto.tofu.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tsuteto.tofu.item.ItemZundaBow.EnumArrowType;
import tsuteto.tofu.params.DataType;
import tsuteto.tofu.params.EntityInfo;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelZundaArrowType implements INetChannelHandler
{

    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
        
        Minecraft mc = FMLClientHandler.instance().getClient();
        int entityId = data.readInt();
        int type = data.readByte();
        
        EntityInfo.instance().set(entityId, DataType.ZundaArrowType, EnumArrowType.values()[type]);
    }
}
