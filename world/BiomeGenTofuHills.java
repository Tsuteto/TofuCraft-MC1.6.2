package tsuteto.tofu.world;

import java.util.Random;

import net.minecraft.world.World;
import tsuteto.tofu.block.TcBlock;

public class BiomeGenTofuHills extends BiomeGenTofuBase
{
    protected BiomeGenTofuHills(int localId, int par1)
    {
        super(localId, par1);
    }

    @Override
    public void decorate(World par1World, Random par2Random, int par3, int par4)
    {
        super.decorate(par1World, par2Random, par3, par4);

        for (int k = 0; k < 1; ++k)
        {
            int x = par3 + par2Random.nextInt(16) + 8;
            int z = par4 + par2Random.nextInt(16) + 8;

            int y = par1World.getHeightValue(x, z) - 1;

            if (y > 80 && par1World.getBlockId(x, y, z) == TcBlock.tofuTerrain.blockID)
            {
                if (par1World.getBlockId(x + 1, y, z) == TcBlock.tofuTerrain.blockID
                        && par1World.getBlockId(x, y, z + 1) == TcBlock.tofuTerrain.blockID
                        && par1World.getBlockId(x - 1, y, z) == TcBlock.tofuTerrain.blockID
                        && par1World.getBlockId(x, y, z - 1) == TcBlock.tofuTerrain.blockID)
                {
                    int h = par2Random.nextInt(3) + 3;
                    for (int i = 0; i < h; i++)
                    {
                        par1World.setBlock(x, y + i, z, TcBlock.soymilkStill.blockID, 0, 2);
                    }
                }
            }
        }

    }
}
