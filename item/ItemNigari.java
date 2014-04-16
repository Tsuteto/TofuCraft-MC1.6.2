package tsuteto.tofu.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import tsuteto.tofu.block.TcBlock;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNigari extends ItemColoredBottle
{
    public ItemNigari(int par1)
    {
        super(par1, 0x809cff);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

        if (var4 == null)
        {
            return par1ItemStack;
        }
        else
        {
            if (var4.typeOfHit == EnumMovingObjectType.TILE)
            {
                int var5 = var4.blockX;
                int var6 = var4.blockY;
                int var7 = var4.blockZ;
                int var11 = par2World.getBlockId(var5, var6, var7);
                Block var13 = null;
                
                if (var11 == TcBlock.soymilkStill.blockID)
                {
                    var13 = TcBlock.tofuKinu;
                }
                else if (var11 == TcBlock.soymilkHellStill.blockID)
                {
                    var13 = TcBlock.tofuHell;
                }
                
                if (var13 != null)
                {
                    par2World.playSoundEffect((double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), (double)((float)var7 + 0.5F),
                            var13.stepSound.getStepSound(), (var13.stepSound.getVolume() + 1.0F) / 2.0F, var13.stepSound.getPitch() * 0.8F);
    
                    par2World.setBlock(var5, var6, var7, var13.blockID);
                    
                    if (!par3EntityPlayer.capabilities.isCreativeMode)
                    {
                        --par1ItemStack.stackSize;
        
                        if (par1ItemStack.stackSize <= 0)
                        {
                            return new ItemStack(Item.glassBottle);
                        }
        
                        if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle)))
                        {
                            par3EntityPlayer.dropPlayerItem(new ItemStack(Item.glassBottle));
                        }
                    }
                }
            }
            return par1ItemStack;
        }
    }
}
