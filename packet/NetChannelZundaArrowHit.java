package tsuteto.tofu.packet;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class NetChannelZundaArrowHit implements INetChannelHandler
{

    @Override
    public void onChannelData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
        
        Minecraft mc = FMLClientHandler.instance().getClient();
        double var8 = data.readDouble();
        double var10 = data.readDouble();
        double var12 = data.readDouble();
        
        EntityPlayer entityplayer = (EntityPlayer)player;
        Random rand = mc.theWorld.rand;
        int var15 = 0x8dd746;
        float var16 = (float)(var15 >> 16 & 255) / 255.0F;
        float var17 = (float)(var15 >> 8 & 255) / 255.0F;
        float var18 = (float)(var15 >> 0 & 255) / 255.0F;
        int var20;
        double var23;
        double var25;
        double var27;
        double var29;
        double var39;
        
        for (var20 = 0; var20 < 100; ++var20)
        {
            var39 = rand.nextDouble() * 4.0D;
            var23 = rand.nextDouble() * Math.PI * 2.0D;
            var25 = Math.cos(var23) * var39;
            var27 = 0.01D + rand.nextDouble() * 0.5D;
            var29 = Math.sin(var23) * var39;
            EntityFX var31 = new EntitySpellParticleFX(mc.theWorld, var8 + var25 * 0.1D, var10 + 0.3D, var12 + var29 * 0.1D, var25, var27, var29);

            if (var31 != null)
            {
                float var32 = 0.75F + rand.nextFloat() * 0.25F;
                var31.setRBGColorF(var16 * var32, var17 * var32, var18 * var32);
                var31.multiplyVelocity((float)var39);
                mc.effectRenderer.addEffect(var31);
            }
        }
    }
}
