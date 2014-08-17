package tsuteto.tofu.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.util.CustomPacketDispatcher;

import com.google.common.io.ByteArrayDataInput;

abstract public class ContainerTfMachine extends Container
{

    protected TileEntityTfMachineBase machine;

    public ContainerTfMachine(TileEntityTfMachineBase machine)
    {
        this.machine = machine;
    }

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
    
    public void onGuiControl(int eventId, ByteArrayDataInput buffer) {}

    public void preparePlayerInventory(InventoryPlayer invPlayer, int ox, int oy)
    {
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(invPlayer, var4 + var3 * 9 + 9, ox + var4 * 18, oy + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(invPlayer, var3, ox + var3 * 18, oy + 58));
        }

    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);
        int machineSlotIdFrom = 0;
        int machineSlotIdTo = this.machine.itemStacks.length - 1;
        int playerInvSlotIdFrom = machineSlotIdTo + 1;
        int playerInvSlotIdTo = playerInvSlotIdFrom + 26;
        int hotbarSlotIdFrom = playerInvSlotIdTo + 1;
        int hotbarSlotIdTo = hotbarSlotIdFrom + 8;


        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 <= machineSlotIdTo)
            {
                if (!this.mergeItemStack(var5, playerInvSlotIdFrom, hotbarSlotIdTo + 1, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 > machineSlotIdTo)
            {
                TransferResult result = this.transferStackInMachineSlot(par1EntityPlayer, par2, var5);
                if (result != TransferResult.SKIPPING)
                {
                    if (result == TransferResult.MISMATCHED) return null;
                    // DO NOTHING WHEN MATCHED
                }
                else if (par2 >= playerInvSlotIdFrom && par2 <= playerInvSlotIdTo)
                {
                    if (!this.mergeItemStack(var5, hotbarSlotIdFrom, hotbarSlotIdTo + 1, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= hotbarSlotIdFrom && par2 <= hotbarSlotIdTo && !this.mergeItemStack(var5, playerInvSlotIdFrom, playerInvSlotIdTo + 1, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, playerInvSlotIdFrom, hotbarSlotIdTo + 1, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
    
    public TransferResult transferStackInMachineSlot(EntityPlayer player, int slot, ItemStack itemStack)
    {
        return TransferResult.SKIPPING;
    }

    protected boolean mergeToSingleItemStack(ItemStack itemStack, int slotId)
    {
        return this.mergeItemStack(itemStack, slotId, slotId + 1, false);
    }
    
    protected enum TransferResult
    {
        SKIPPING,
        MATCHED,
        MISMATCHED;
    }
}
