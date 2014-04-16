package tsuteto.tofu.packet;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelDimTrip implements INetChannelHandler
{
    private Random rand = new Random();

    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        mc.sndManager.playSoundFX("portal.trigger", 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
    }

}
