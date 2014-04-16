package tsuteto.tofu.world.gen.feature;

import java.util.Random;

import tsuteto.tofu.block.TcBlock;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

abstract public class WorldGenTcTreesBase extends WorldGenerator
{
    /** The minimum height of a generated tree. */
    protected final int minTreeHeight;

    /** True if this tree should grow Vines. */
    protected final boolean vinesGrow;
    
    protected final Block blockWood;
    protected final Block blockLeaves;

    /** The metadata value of the wood to use in tree generation. */
    protected final int metaWood;

    /** The metadata value of the leaves to use in tree generation. */
    protected final int metaLeaves;
    protected int metaFruit = 0;
    protected int fruitChance = 0;

    public WorldGenTcTreesBase(boolean par1, int par2, Block blockWood, int par3, Block blockLeaves, int par4, boolean par5)
    {
        super(par1);
        this.minTreeHeight = par2;
        this.blockWood = blockWood;
        this.metaWood = par3;
        this.blockLeaves = blockLeaves;
        this.metaLeaves = par4;
        this.vinesGrow = par5;
    }
    
    public void setFruit(int metaFruit, int chance)
    {
        this.metaFruit = metaFruit;
        this.fruitChance = chance;
    }

    public boolean isTreePlantable(int blockid)
    {
        return blockid == Block.dirt.blockID
                || blockid == Block.grass.blockID;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(3) + this.minTreeHeight;
        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 256)
        {
            int var8;
            byte var9;
            int var11;
            int var12;

            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                var9 = 1;

                if (var8 == par4)
                {
                    var9 = 0;
                }

                if (var8 >= par4 + 1 + var6 - 2)
                {
                    var9 = 2;
                }

                for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
                {
                    for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
                    {
                        if (var8 >= 0 && var8 < 256)
                        {
                            var12 = par1World.getBlockId(var10, var8, var11);

                            Block block = Block.blocksList[var12];

                            if (var12 != 0 &&
                               !block.isLeaves(par1World, var10, var8, var11) &&
                               !isTreePlantable(var12) &&
                               !block.isWood(par1World, var10, var8, var11))
                            {
                                var7 = false;
                            }
                        }
                        else
                        {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7)
            {
                return false;
            }
            else
            {
                var8 = par1World.getBlockId(par3, par4 - 1, par5);

                if (isTreePlantable(var8) && par4 < 256 - var6 - 1)
                {
                    if (var8 == Block.grass.blockID)
                    {
                        this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
                    }
                    
                    this.putLeaves(par3, par4, par5, var6, par1World, par2Random);
                    
                    for (var11 = 0; var11 < var6; ++var11)
                    {
                        var12 = par1World.getBlockId(par3, par4 + var11, par5);

                        Block block = Block.blocksList[var12];

                        if (var12 == 0 || block == null || block.isLeaves(par1World, par3, par4 + var11, par5))
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + var11, par5, blockWood.blockID, this.metaWood);

                            if (this.vinesGrow && var11 > 0)
                            {
                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var11, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 - 1, par4 + var11, par5, Block.vine.blockID, 8);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var11, par5))
                                {
                                    this.setBlockAndMetadata(par1World, par3 + 1, par4 + var11, par5, Block.vine.blockID, 2);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 - 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 - 1, Block.vine.blockID, 1);
                                }

                                if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 + 1))
                                {
                                    this.setBlockAndMetadata(par1World, par3, par4 + var11, par5 + 1, Block.vine.blockID, 4);
                                }
                            }
                        }
                    }

                    if (this.vinesGrow)
                    {
                        int var13;
                        int var14;
                        int var15;

                        for (var11 = par4 - 3 + var6; var11 <= par4 + var6; ++var11)
                        {
                            var12 = var11 - (par4 + var6);
                            var13 = 2 - var12 / 2;

                            for (var14 = par3 - var13; var14 <= par3 + var13; ++var14)
                            {
                                for (var15 = par5 - var13; var15 <= par5 + var13; ++var15)
                                {
                                    Block block = Block.blocksList[par1World.getBlockId(var14, var11, var15)];
                                    if (block != null && block.isLeaves(par1World, var14, var11, var15))
                                    {
                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var11, var15) == 0)
                                        {
                                            this.growVines(par1World, var14 - 1, var11, var15, 8);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var11, var15) == 0)
                                        {
                                            this.growVines(par1World, var14 + 1, var11, var15, 2);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 - 1) == 0)
                                        {
                                            this.growVines(par1World, var14, var11, var15 - 1, 1);
                                        }

                                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 + 1) == 0)
                                        {
                                            this.growVines(par1World, var14, var11, var15 + 1, 4);
                                        }
                                    }
                                }
                            }
                        }

//                        if (par2Random.nextInt(5) == 0 && var6 > 5)
//                        {
//                            for (var11 = 0; var11 < 2; ++var11)
//                            {
//                                for (var12 = 0; var12 < 4; ++var12)
//                                {
//                                    if (par2Random.nextInt(4 - var11) == 0)
//                                    {
//                                        var13 = par2Random.nextInt(3);
//                                        this.setBlockAndMetadata(par1World, par3 + Direction.offsetX[Direction.footInvisibleFaceRemap[var12]], par4 + var6 - 5 + var11, par5 + Direction.offsetZ[Direction.footInvisibleFaceRemap[var12]], Block.cocoaPlant.blockID, var13 << 2 | var12);
//                                    }
//                                }
//                            }
//                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
    
    protected void putLeaves(int ox, int oy, int oz, int height, World par1World, Random par2Random)
    {
        byte zero = 0;
        int var9 = 3;
        int leavesY, blocksHighFromTop, radius, leavesX, relX;
        for (leavesY = oy - var9 + height; leavesY <= oy + height; ++leavesY)
        {
            blocksHighFromTop = leavesY - (oy + height);
            radius = zero + 1 - blocksHighFromTop / 2;

            for (leavesX = ox - radius; leavesX <= ox + radius; ++leavesX)
            {
                relX = leavesX - ox;

                for (int leavesZ = oz - radius; leavesZ <= oz + radius; ++leavesZ)
                {
                    int relZ = leavesZ - oz;

                    Block block = Block.blocksList[par1World.getBlockId(leavesX, leavesY, leavesZ)];

                    if ((Math.abs(relX) != radius || Math.abs(relZ) != radius || par2Random.nextInt(2) != 0 && blocksHighFromTop != 0) &&
                        (block == null || block.canBeReplacedByLeaves(par1World, leavesX, leavesY, leavesZ)))
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

    /**
     * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
     */
    private void growVines(World par1World, int par2, int par3, int par4, int par5)
    {
        this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
        int var6 = 4;

        while (true)
        {
            --par3;

            if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0)
            {
                return;
            }

            this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
            --var6;
        }
    }
}
