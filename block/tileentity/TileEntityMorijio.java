package tsuteto.tofu.block.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMorijio extends TileEntity
{
    /** The morijio's rotation. */
    private int rotation;

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("Rot", (byte)(this.rotation & 255));
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.rotation = par1NBTTagCompound.getByte("Rot");
    }

    @Override
    public void onDataPacket(INetworkManager netManager, Packet132TileEntityData packet)
    {
        this.readFromNBT(packet.data);
    }

    /**
     * Overriden in a sign to provide the text.
     */
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        this.writeToNBT(var1);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, var1);
    }

    /**
     * Set the morijio's rotation
     */
    public void setRotation(int par1)
    {
        this.rotation = par1;
    }

    @SideOnly(Side.CLIENT)
    public int getRotation()
    {
        return this.rotation;
    }
}
