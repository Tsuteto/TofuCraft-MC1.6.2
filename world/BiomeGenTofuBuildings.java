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

public class BiomeGenTofuBuildings extends BiomeGenTofuBase
{
    public BiomeGenTofuBuildings(int localId, int par1)
    {
        super(localId, par1);
        this.treesPerChunk = -999;
        this.tofuPerChunk = 5;
    }
    
}
