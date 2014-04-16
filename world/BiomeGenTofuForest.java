package tsuteto.tofu.world;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.entity.EntityTofuSlime;

public class BiomeGenTofuForest extends BiomeGenTofuBase
{
    public BiomeGenTofuForest(int localId, int par1)
    {
        super(localId, par1);
        this.treesPerChunk = 10;
        this.chanceOfLeeks = 10;
    }
    
    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return (WorldGenerator)this.worldGeneratorTrees;
    }
    
    public void decorate(World par1World, Random par2Random, int chunk_X, int chunk_Z)
    {
        super.decorate(par1World, par2Random, chunk_X, chunk_Z);
    }
}
