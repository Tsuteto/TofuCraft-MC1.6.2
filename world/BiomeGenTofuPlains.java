package tsuteto.tofu.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTofuPlains extends BiomeGenTofuBase
{
    public BiomeGenTofuPlains(int localId, int par1)
    {
        super(localId, par1);
        this.treesPerChunk = -999;
        this.minHeight = 0.1F;
        this.maxHeight = 0.1F;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return this.worldGeneratorTrees;
    }
    
    @Override
    public void decorate(World par1World, Random par2Random, int chunk_X, int chunk_Z)
    {
        super.decorate(par1World, par2Random, chunk_X, chunk_Z);
        
    }
}
