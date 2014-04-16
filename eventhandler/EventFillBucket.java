package tsuteto.tofu.eventhandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.achievement.TcAchievementMgr.Key;
import tsuteto.tofu.block.BlockMisoBarrel;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.TcItem;

public class EventFillBucket
{
    @ForgeSubscribe
    public void onFillBucket(FillBucketEvent event)
    {
        World par2World = event.world;
        ItemStack par1ItemStack = event.current;
        EntityPlayer par3EntityPlayer = event.entityPlayer;
        MovingObjectPosition var12 = event.target;

        int var13 = var12.blockX;
        int var14 = var12.blockY;
        int var15 = var12.blockZ;

        if (!par2World.canMineBlock(par3EntityPlayer, var13, var14, var15))
        {
            return;
        }

        if (par1ItemStack.itemID == Item.bucketEmpty.itemID)
        {
            if (var12.typeOfHit == EnumMovingObjectType.TILE)
            {
                int blockId = par2World.getBlockId(var13, var14, var15);
                ItemStack filledBucket = this.pickUpFluid(par3EntityPlayer, par2World, var13, var14, var15, blockId);
                if (filledBucket != null)
                {
                    event.result = filledBucket;
                    event.setResult(Result.ALLOW);
                }
            }
        }
    }

    private ItemStack pickUpFluid(EntityPlayer player, World world, int var13, int var14, int var15, int blockId)
    {
        if (blockId == TcBlock.soymilkStill.blockID)
        {
        	world.setBlockToAir(var13, var14, var15); // replace with an air block
            return new ItemStack(TcItem.bucketSoymilk);
        }

        if (blockId == TcBlock.soymilkHellStill.blockID)
        {
            world.setBlockToAir(var13, var14, var15);
            return new ItemStack(TcItem.bucketSoymilkHell);
        }

        if (blockId == TcBlock.soySauceStill.blockID)
        {
        	world.setBlockToAir(var13, var14, var15);
            return new ItemStack(TcItem.bucketSoySauce);
        }

        if (blockId == TcBlock.barrelMiso.blockID)
        {
            if (((BlockMisoBarrel)TcBlock.barrelMiso).removeSoySauce(world, var13, var14, var15))
            {
                TcAchievementMgr.achieve(player, Key.soySauce);
                return new ItemStack(TcItem.bucketSoySauce);
            }

        }

        return null;
    }
}
