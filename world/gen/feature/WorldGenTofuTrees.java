package tsuteto.tofu.world.gen.feature;

import java.util.Random;

import tsuteto.tofu.block.TcBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

public class WorldGenTofuTrees extends WorldGenTcTreesBase
{
    public WorldGenTofuTrees(boolean par1)
    {
        super(par1, 4, TcBlock.tofuIshi, 0, TcBlock.tcLeaves, 2, false);
    }
    
    @Override
    public boolean isTreePlantable(int blockid)
    {
        return blockid == TcBlock.tofuTerrain.blockID
                || blockid == TcBlock.tofuMomen.blockID;
    }
    
    @Override
    protected void putLeaves(int ox, int oy, int oz, int height, World par1World, Random par2Random)
    {
        byte zero = 0;
        int var9 = 3;
        int leavesY, blocksHighFromTop, radius, leavesX, relX;
        for (leavesY = oy - var9 + height; leavesY <= oy + height; ++leavesY)
        {
            blocksHighFromTop = leavesY - (oy + height);
            radius = height / 3;

            for (leavesX = ox - radius; leavesX <= ox + radius; ++leavesX)
            {
                relX = leavesX - ox;

                for (int leavesZ = oz - radius; leavesZ <= oz + radius; ++leavesZ)
                {
                    int relZ = leavesZ - oz;

                    Block block = Block.blocksList[par1World.getBlockId(leavesX, leavesY, leavesZ)];

                    if (block == null || block.canBeReplacedByLeaves(par1World, leavesX, leavesY, leavesZ))
                    {
                        int metadata;
                        if (fruitChance > 0 && par2Random.nextInt(fruitChance) == 0)
                        {
                            metadata = this.metaFruit;
                        }
                        else
                        {
                            metadata = this.metaLeaves;
                        }
                        this.setBlockAndMetadata(par1World, leavesX, leavesY, leavesZ, blockLeaves.blockID, metadata);
                    }
                }
            }
        }
    }

}
