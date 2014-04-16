package tsuteto.tofu.block.tileentity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.util.CustomPacketDispatcher;

import com.google.common.io.ByteArrayDataInput;

abstract public class ContainerTfMachine extends Container
{

    public abstract void updateTfMachineData(int id, ByteArrayDataInput data);
    
    public void sendTfMachineData(ICrafting crafter, ContainerTfMachine par1Container, int par2, CustomPacketDispatcher.DataHandler data)
    {
        if (crafter instanceof EntityPlayerMP)
        {
            CustomPacketDispatcher.create(TofuCraftCore.netChannelTfMachineData)
                    .addByte((byte)par1Container.windowId)
                    .addShort((short)par2)
                    .addData(data)
                    .sendToPlayer((EntityPlayerMP)crafter);
        }
    }
}
