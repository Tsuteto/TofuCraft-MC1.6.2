package tsuteto.tofu.block.tileentity;

import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.TfMaterialRegistry;
import tsuteto.tofu.util.CustomPacketDispatcher;

import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerTfStorage extends ContainerTfMachine
{
    private final TileEntityTfStorage machine;
    private final int lastCookTime = 0;
    private final int lastBurnTime = 0;
    private final int lastItemBurnTime = 0;
    private int lastWholeTimeInput;
    private int lastWholeTimeOutput;
    private int lastProcessTimeInput;
    private int lastProcessTimeOutput;
    private float lastTfCapacity;
    private float lastTfAmount;

    public ContainerTfStorage(InventoryPlayer invPlayer, TileEntityTfStorage machine)
    {
        super(machine);
        this.machine = machine;
        this.addSlotToContainer(new SlotTfStorage(invPlayer.player, machine, TileEntityTfStorage.SLOT_INPUT_ITEM, 45, 22));
        this.addSlotToContainer(new Slot(machine, TileEntityTfStorage.SLOT_INPUT_CONTAINER_ITEM, 18, 22));
        this.addSlotToContainer(new Slot(machine, TileEntityTfStorage.SLOT_OUTPUT_CONTAINER_ITEM, 45, 51));
        this.addSlotToContainer(new Slot(machine, TileEntityTfStorage.SLOT_OUTPUT_ITEM, 18, 51));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(invPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 98 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(invPlayer, var3, 8 + var3 * 18, 156));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, this.machine.wholeTimeInput);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.machine.processTimeInput);
        par1ICrafting.sendProgressBarUpdate(this, 2, this.machine.wholeTimeOutput);
        par1ICrafting.sendProgressBarUpdate(this, 3, this.machine.processTimeOutput);
        
        this.sendTfMachineData(par1ICrafting, this, 0, new CustomPacketDispatcher.DataHandler() {
            
            @Override
            public void addData(DataOutputStream dos) throws Exception
            {
                dos.writeFloat(machine.tfCapacity);
            }
        });
    
        this.sendTfMachineData(par1ICrafting, this, 1, new CustomPacketDispatcher.DataHandler() {
            
            @Override
            public void addData(DataOutputStream dos) throws Exception
            {
                dos.writeFloat(machine.tfAmount);
            }
        });
    }
    
    /**
     * Updates crafting matrix; called from onCraftMatrixChanged. Args: none
     */
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int var1 = 0; var1 < this.crafters.size(); ++var1)
        {
            ICrafting var2 = (ICrafting)this.crafters.get(var1);

            if (this.lastWholeTimeInput != this.machine.wholeTimeInput)
            {
                var2.sendProgressBarUpdate(this, 0, this.machine.wholeTimeInput);
            }

            if (this.lastProcessTimeInput != this.machine.processTimeInput)
            {
                var2.sendProgressBarUpdate(this, 1, this.machine.processTimeInput);
            }

            if (this.lastWholeTimeOutput != this.machine.wholeTimeOutput)
            {
                var2.sendProgressBarUpdate(this, 2, this.machine.wholeTimeOutput);
            }
            
            if (this.lastProcessTimeOutput != this.machine.processTimeOutput)
            {
                var2.sendProgressBarUpdate(this, 3, this.machine.processTimeOutput);
            }
            
            if (this.lastTfCapacity != this.machine.tfCapacity)
            {
                this.sendTfMachineData(var2, this, 0, new CustomPacketDispatcher.DataHandler() {
                    
                    @Override
                    public void addData(DataOutputStream dos) throws Exception
                    {
                        dos.writeFloat(machine.tfCapacity);
                    }
                });
            }
            
            if (this.lastTfAmount != this.machine.tfAmount)
            {
                this.sendTfMachineData(var2, this, 1, new CustomPacketDispatcher.DataHandler() {
                    
                    @Override
                    public void addData(DataOutputStream dos) throws Exception
                    {
                        dos.writeFloat(machine.tfAmount);
                    }
                });
            }
        }

        this.lastWholeTimeInput = this.machine.wholeTimeInput;
        this.lastProcessTimeInput = this.machine.processTimeInput;
        this.lastWholeTimeOutput = this.machine.wholeTimeOutput;
        this.lastProcessTimeOutput = this.machine.processTimeOutput;
        this.lastTfCapacity = this.machine.tfCapacity;
        this.lastTfAmount = this.machine.tfAmount;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 0)
        {
            this.machine.wholeTimeInput = par2;
        }

        if (par1 == 1)
        {
            this.machine.processTimeInput = par2;
        }

        if (par1 == 2)
        {
            this.machine.wholeTimeOutput = par2;
        }
        
        if (par1 == 3)
        {
            this.machine.processTimeOutput = par2;
        }
        
//        if (par1 == 4)
//        {
//            this.machine.tfCapacity = par2;
//        }
//        
//        if (par1 == 5)
//        {
//            this.machine.tfAmount = par2;
//        }
    }


    @Override
    public void updateTfMachineData(int id, ByteArrayDataInput data)
    {
        
        if (id == 0)
        {
            this.machine.tfCapacity = data.readFloat();
        }

        if (id == 1)
        {
            this.machine.tfAmount = data.readFloat();
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.machine.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public TransferResult transferStackInMachineSlot(EntityPlayer player, int slot, ItemStack itemStack)
    {
        if (TfMaterialRegistry.isTfMaterial(itemStack))
        {
            if (!this.mergeToSingleItemStack(itemStack, TileEntityTfStorage.SLOT_INPUT_ITEM))
            {
                return TransferResult.MISMATCHED;
            }
            return TransferResult.MATCHED;
        }
        return TransferResult.SKIPPING;
    }

}
