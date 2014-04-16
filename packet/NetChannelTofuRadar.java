package tsuteto.tofu.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tsuteto.tofu.item.ItemTofuSlimeRadar;
import tsuteto.tofu.item.TcItem;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelTofuRadar implements INetChannelHandler
{

    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);

        Minecraft mc = FMLClientHandler.instance().getClient();
        boolean isSpawnChunk = data.readBoolean();

        EntityPlayer entityPlayer = (EntityPlayer)player;
        ItemStack itemstack = entityPlayer.getCurrentEquippedItem();

        if (itemstack != null && itemstack.itemID == TcItem.tofuRadar.itemID)
        {
            ((ItemTofuSlimeRadar)TcItem.tofuRadar).onUse(isSpawnChunk, itemstack, entityPlayer.worldObj, entityPlayer);
        }
    }
}
