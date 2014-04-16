package tsuteto.tofu.eventhandler;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.achievement.TcAchievementMgr.Key;
import tsuteto.tofu.block.BlockLeek;
import tsuteto.tofu.block.BlockTcSapling;
import tsuteto.tofu.block.BlockTofuBase;
import tsuteto.tofu.block.TcBlock;

public class EventBonemeal
{
    @ForgeSubscribe
    public void onBonemeal(BonemealEvent event)
    {
        EntityPlayer player = event.entityPlayer;
        World world = event.world;
        Random rand = world.rand;
        int blockId = event.ID;
        int posX = event.X;
        int posY = event.Y;
        int posZ = event.Z;
        int var11;
        int var12;
        int var13;

        // Saplings
        if (blockId == TcBlock.tcSapling.blockID)
        {
            if (!world.isRemote)
            {
                ((BlockTcSapling)TcBlock.tcSapling).growTree(world, posX, posY, posZ, world.rand);
            }
            event.setResult(Result.ALLOW);
        }

        // Leek
        if (Block.blocksList[blockId] instanceof BlockTofuBase)
        {
            if (!world.isRemote)
            {
                label133:

                for (var12 = 0; var12 < 32; ++var12)
                {
                    var13 = posX;
                    int var14 = posY + 1;
                    int var15 = posZ;

                    for (int var16 = 0; var16 < var12 / 16; ++var16)
                    {
                        var13 += rand.nextInt(3) - 1;
                        var14 += (rand.nextInt(3) - 1) * rand.nextInt(3) / 2;
                        var15 += rand.nextInt(3) - 1;

                        if (!(Block.blocksList[world.getBlockId(var13, var14 - 1, var15)] instanceof BlockTofuBase) || world.isBlockNormalCube(var13, var14, var15))
                        {
                            continue label133;
                        }
                    }

                    if (world.getBlockId(var13, var14, var15) == 0)
                    {
                        if (rand.nextInt(10) < 5)
                        {
                            if (TcBlock.leek.canBlockStay(world, var13, var14, var15))
                            {
                                world.setBlock(var13, var14, var15, TcBlock.leek.blockID, BlockLeek.META_NATURAL, 3);
                                TcAchievementMgr.achieve(player, Key.leek);
                            }
                        }
                        else
                        {
                            ForgeHooks.plantGrass(world, var13, var14, var15);
                        }
                    }
                }
            }
            event.setResult(Result.ALLOW);
        }

        //event.setCanceled(false);
    }
}
