package tsuteto.tofu.block.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import tsuteto.tofu.util.CustomPacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

abstract public class TileEntityTfMachineBase extends TileEntity implements IInventory
{
    protected ItemStack[] itemStacks = new ItemStack[0];

    protected String customName;

    abstract protected String getInventoryNameTranslate();

    @Override
    public String getInvName()
    {
        return this.isInvNameLocalized() ? this.customName : this.getInventoryNameTranslate();
    }

    @Override
    public boolean isInvNameLocalized() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setCustomName(String par1Str)
    {
        this.customName = par1Str;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        if (itemStacks.length > 0)
        {
            NBTTagList nbttaglist = nbtTagCompound.getTagList("Items");
            this.itemStacks = new ItemStack[this.getSizeInventory()];

            for (int i = 0; i < nbttaglist.tagCount(); ++i)
            {
                NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
                byte b0 = nbttagcompound1.getByte("Slot");

                if (b0 >= 0 && b0 < this.itemStacks.length)
                {
                    this.itemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
                }
            }
        }

        if (nbtTagCompound.hasKey("CustomName"))
        {
            this.customName = nbtTagCompound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte("Rev", (byte) this.getNBTRevision());

        if (itemStacks.length > 0)
        {
            NBTTagList nbttaglist = new NBTTagList();

            for (int i = 0; i < this.itemStacks.length; ++i)
            {
                if (this.itemStacks[i] != null)
                {
                    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                    nbttagcompound1.setByte("Slot", (byte) i);
                    this.itemStacks[i].writeToNBT(nbttagcompound1);
                    nbttaglist.appendTag(nbttagcompound1);
                }
            }

            nbtTagCompound.setTag("Items", nbttaglist);
        }

        if (this.isInvNameLocalized())
        {
            nbtTagCompound.setString("CustomName", this.customName);
        }
    }

    protected int getNBTRevision()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public void postGuiControl(int windowId, int eventId)
    {
        this.postGuiControl(windowId, eventId, null);
    }

    @SideOnly(Side.CLIENT)
    public void postGuiControl(int windowId, int eventId, CustomPacketDispatcher.DataHandler data)
    {
        CustomPacketDispatcher dispatcher = CustomPacketDispatcher.create("GuiControl")
                .addByte((byte)windowId)
                .addShort((short)eventId);
        
        if (data != null)
        {
            dispatcher.addData(data);
        }
        
        dispatcher.sendToServer();
    }

    public boolean isRedstonePowered()
    {
        return (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) & 8) == 8;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return this.itemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.itemStacks[par1];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.itemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.itemStacks[par1].stackSize <= par2)
            {
                var3 = this.itemStacks[par1];
                this.itemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.itemStacks[par1].splitStack(par2);

                if (this.itemStacks[par1].stackSize == 0)
                {
                    this.itemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.itemStacks[par1] != null)
        {
            ItemStack var2 = this.itemStacks[par1];
            this.itemStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.itemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    protected int getLiveMetadata()
    {
        return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}
}
