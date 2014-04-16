package tsuteto.tofu.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * Creates a packet in one-liner and dispatches it
 *
 * @author Tsuteto
 * @version 1.0.1
 *
 */
public class CustomPacketDispatcher
{
    private final String channelName;
    private final Packet250CustomPayload packet = new Packet250CustomPayload();
    private boolean packed = false;

    private final ByteArrayOutputStream bytesStream;
    private final DataOutputStream dataStream;

    public static CustomPacketDispatcher create(String channelName)
    {
        return new CustomPacketDispatcher(channelName);
    }

    private CustomPacketDispatcher(String channelName)
    {
        this.channelName = channelName;

        this.bytesStream = new ByteArrayOutputStream();
        this.dataStream = new DataOutputStream(bytesStream);
    }

    public CustomPacketDispatcher addInt(int val)
    {
        try
        {
            dataStream.writeInt(val);
        }
        catch (IOException e)
        {
            ModLog.log(Level.WARNING, e, "Failed to set value to packet.");
        }
        return this;
    }

    public CustomPacketDispatcher addBoolean(boolean val)
    {
        try
        {
            dataStream.writeBoolean(val);
        }
        catch (IOException e)
        {
            ModLog.log(Level.WARNING, e, "Failed to set value to packet.");
        }
        return this;
    }

    public CustomPacketDispatcher addByte(byte val)
    {
        try
        {
            dataStream.writeByte(val);
        }
        catch (IOException e)
        {
            ModLog.log(Level.WARNING, e, "Failed to set value to packet.");
        }
        return this;
    }

    public CustomPacketDispatcher addShort(short val)
    {
        try
        {
            dataStream.writeShort(val);
        }
        catch (IOException e)
        {
            ModLog.log(Level.WARNING, e, "Failed to set value to packet.");
        }
        return this;
    }

    public CustomPacketDispatcher addDouble(double val)
    {
        try
        {
            dataStream.writeDouble(val);
        }
        catch (IOException e)
        {
            ModLog.log(Level.WARNING, e, "Failed to set value to packet.");
        }
        return this;
    }

    public CustomPacketDispatcher addFloat(float val)
    {
        try
        {
            dataStream.writeFloat(val);
        }
        catch (IOException e)
        {
            ModLog.log(Level.WARNING, e, "Failed to set value to packet.");
        }
        return this;
    }

    public CustomPacketDispatcher setString(String str)
    {
        try
        {
            dataStream.write(str.getBytes());
        }
        catch (IOException e)
        {
            ModLog.log(Level.WARNING, e, "Failed to set value to packet.");
        }
        return this;
    }

    public CustomPacketDispatcher addData(DataHandler handler)
    {
        if (handler == null)
        {
            ModLog.log(Level.WARNING, "No data handler given!");
        }
        else
        {
            try
            {
                handler.addData(dataStream);
            }
            catch (Exception e)
            {
                ModLog.log(Level.WARNING, e, "Failed to set data to packet.");
            }
        }

        return this;
    }

    public CustomPacketDispatcher setChunkDataPacket()
    {
        packet.isChunkDataPacket = true;
        return this;
    }

    public void sendToServer()
    {
        if (!this.packed) this.pack();
        PacketDispatcher.sendPacketToServer(packet);
    }

    public void sendToPlayer(EntityPlayer player)
    {
        if (!this.packed) this.pack();
        PacketDispatcher.sendPacketToPlayer(packet, (Player)player);
    }

    public void sendToAllInDimension(int dimId)
    {
        if (!this.packed) this.pack();
        PacketDispatcher.sendPacketToAllInDimension(packet, dimId);
    }

    public Packet250CustomPayload getPacket()
    {
        if (!this.packed)
            this.pack();
        return this.packet;
    }

    public void pack()
    {
        this.packet.channel = this.channelName;
        this.packet.data = bytesStream.toByteArray();
        this.packet.length = bytesStream.size();
        this.packed = true;
    }

    public static interface DataHandler
    {
        void addData(DataOutputStream dos) throws Exception;
    }

    public void sendToAllAround(double X, double Y, double Z, int range, int dimensionId)
    {
        if (!this.packed) this.pack();
        PacketDispatcher.sendPacketToAllAround(X, Y, Z, range, dimensionId, packet);
    }
    
    public void sendPacketToAllPlayers()
    {
        if (!this.packed) this.pack();
        PacketDispatcher.sendPacketToAllPlayers(packet);
    }
}
