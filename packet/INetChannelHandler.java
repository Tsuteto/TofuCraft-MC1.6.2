package tsuteto.tofu.packet;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

public interface INetChannelHandler
{
    void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player);
}
