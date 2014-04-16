package tsuteto.tofu.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.util.CustomPacketDispatcher;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ItemTofuBugle extends TcItem
{

    public ItemTofuBugle(int par1)
    {
        super(par1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            CustomPacketDispatcher.create(TofuCraftCore.netChannelBugle)
                    .addFloat((float)player.posX)
                    .addFloat((float)player.posY)
                    .addFloat((float)player.posZ)
                    .addInt(player.entityId)
                    .sendToAllAround(player.posX, player.posY, player.posZ, 64, player.dimension);
        }
        
        player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }
    
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
}
