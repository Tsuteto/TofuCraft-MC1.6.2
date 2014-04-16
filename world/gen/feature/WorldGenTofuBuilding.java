package tsuteto.tofu.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import tsuteto.tofu.block.BlockLeek;
import tsuteto.tofu.block.TcBlock;

public class WorldGenTofuBuilding extends WorldGenerator
{
    protected final int minHeight;
    protected final Block blockTofu;
    protected final int metaTofu;

    public WorldGenTofuBuilding(boolean par1)
    {
        this(par1, 3, TcBlock.tofuTerrain, 0);
    }

    public WorldGenTofuBuilding(boolean par1, int height, Block tofu, int meta)
    {
        super(par1);
        this.minHeight = height;
        this.blockTofu = tofu;
        this.metaTofu = meta;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par2Random.nextInt(3) + this.minHeight;
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

                            if (var12 != 0)
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

                if ((var8 == TcBlock.tofuTerrain.blockID || var8 == TcBlock.tofuMomen.blockID)
                        && par4 < 256 - var6 - 1)
                {
                    this.buildTofu(par3, par4, par5, var6, par1World, par2Random);
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

    protected void buildTofu(int ox, int oy, int oz, int height, World par1World, Random par2Random)
    {
        int blockY, radius, blockX, relX;
        radius = 1 + height / 2;

        for (blockY = oy; blockY <= oy + height; ++blockY)
        {
            for (blockX = ox - radius; blockX <= ox + radius; ++blockX)
            {
                relX = blockX - ox;

                for (int blockZ = oz - radius; blockZ <= oz + radius; ++blockZ)
                {
                    int relZ = blockZ - oz;

                    if (blockY == oy)
                    {
                        for (int y = oy - 1; y > 0; y--)
                        {
                            int blockId = par1World.getBlockId(blockX, y, blockZ);
                            if (blockId == 0 || blockId == TcBlock.leek.blockID)
                            {
                                this.setBlockAndMetadata(par1World, blockX, y, blockZ, blockTofu.blockID, metaTofu);
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                    this.setBlockAndMetadata(par1World, blockX, blockY, blockZ, blockTofu.blockID, metaTofu);
                }
            }
        }

        this.plantLeeks(ox, oy + height + 1, oz, radius, par1World, par2Random);
    }

    protected void plantLeeks(int x, int y, int z, int r, World world, Random random)
    {
        WorldGenerator var6 = new WorldGenTallGrassRanged(TcBlock.leek.blockID, BlockLeek.META_NATURAL, r);
        var6.generate(world, random, x, y, z);
    }
}
