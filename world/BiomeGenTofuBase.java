package tsuteto.tofu.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import tsuteto.tofu.Settings;
import tsuteto.tofu.block.BlockLeek;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.entity.EntityTofuSlime;
import tsuteto.tofu.world.gen.feature.WorldGenTofuBuilding;
import tsuteto.tofu.world.gen.feature.WorldGenTofuTrees;

abstract public class BiomeGenTofuBase extends BiomeGenBase
{
    public static BiomeGenTofuBase[] tofuBiomeList = new BiomeGenTofuBase[32];
    public static final BiomeGenTofuBase tofuPlains = (BiomeGenTofuBase) (new BiomeGenTofuPlains(0, Settings.tofuBiomeId))
            .setColor(9286496).setBiomeName("TofuPlains").setTemperatureRainfall(0.422F, 0.917F);
    public static final BiomeGenTofuBase tofuForest = (BiomeGenTofuBase) (new BiomeGenTofuForest(1, Settings.tofuBiomeId + 1))
            .setColor(9286496).setBiomeName("TofuForest").setTemperatureRainfall(0.475F, 0.969F);
    public static final BiomeGenTofuBase tofuBuildings = (BiomeGenTofuBase) (new BiomeGenTofuBuildings(2, Settings.tofuBiomeId + 2))
            .setColor(9286496).setBiomeName("TofuBuildings").setTemperatureRainfall(0.422F, 0.917F);
    public static final BiomeGenTofuBase tofuExtremeHills = (BiomeGenTofuBase) (new BiomeGenTofuHills(3, Settings.tofuBiomeId + 3))
            .setColor(9286496).setBiomeName("TofuExtremeHills").setTemperatureRainfall(0.317F, 0.759F).setMinMaxHeight(0.3F, 1.5F);
    public static final BiomeGenTofuBase tofuPlainHills = (BiomeGenTofuBase) (new BiomeGenTofuPlains(4, Settings.tofuBiomeId + 4))
            .setColor(9286496).setBiomeName("TofuPlainHills").setTemperatureRainfall(0.422F, 0.917F).setMinMaxHeight(0.3F, 0.7F);
    public static final BiomeGenTofuBase tofuForestHills = (BiomeGenTofuBase) (new BiomeGenTofuForest(5, Settings.tofuBiomeId + 5))
            .setColor(9286496).setBiomeName("TofuForestHills").setTemperatureRainfall(0.475F, 0.969F).setMinMaxHeight(0.3F, 0.7F);
    public static final BiomeGenTofuBase tofuHillsEdge = (BiomeGenTofuBase) (new BiomeGenTofuHills(6, Settings.tofuBiomeId + 6))
            .setColor(9286496).setBiomeName("TofuExtremeHillsEdge").setTemperatureRainfall(0.317F, 0.759F).setMinMaxHeight(0.2F, 0.8F);
    public static final BiomeGenTofuBase tofuLeekPlains = (BiomeGenTofuBase) (new BiomeGenLeekPlains(7, Settings.tofuBiomeId + 7))
            .setColor(9286496).setBiomeName("LeekPlains").setTemperatureRainfall(0.510F, 0.934F);

    public static BiomeGenTofuBase[] decorationBiomes = new BiomeGenTofuBase[]{
        tofuPlains, tofuLeekPlains, tofuPlains, tofuForest, tofuBuildings, tofuExtremeHills};
    
    protected int treesPerChunk;
    protected int tofuPerChunk;
    protected int maxGrassPerChunk;
    protected int chanceOfLeeks;
    protected boolean generateLakes;

    protected WorldGenTofuTrees worldGeneratorTrees;
    protected WorldGenTofuBuilding worldGeneratorTofuBuilding;

    public BiomeGenTofuBase(int localBiomeId, int par1)
    {
        super(par1);
        this.worldGeneratorTrees = new WorldGenTofuTrees(false);
        this.worldGeneratorTofuBuilding = new WorldGenTofuBuilding(false);

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntityTofuSlime.class, 3, 4, 4));

        this.topBlock = (byte)TcBlock.tofuTerrain.blockID;
        this.fillerBlock = (byte)TcBlock.tofuTerrain.blockID;

        this.minHeight = 0.1F;
        this.maxHeight = 0.2F;

        this.treesPerChunk = 0;
        this.tofuPerChunk = 0;
        this.maxGrassPerChunk = 1;
        this.chanceOfLeeks = 50;
        this.generateLakes = true;

        tofuBiomeList[localBiomeId] = this;
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
        int i, j, k, l, i1;

        i = this.treesPerChunk;
        if (par2Random.nextInt(10) == 0)
        {
            ++i;
        }

        for (j = 0; j < i; ++j)
        {
            k = chunk_X + par2Random.nextInt(16) + 8;
            l = chunk_Z + par2Random.nextInt(16) + 8;
            WorldGenerator worldgenerator = this.getRandomWorldGenForTrees(par2Random);
            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }

        if (par2Random.nextInt(this.chanceOfLeeks) == 0)
        {
            for (j = 0; j < maxGrassPerChunk; j++)
            {
                k = chunk_X + par2Random.nextInt(16) + 8;
                l = par2Random.nextInt(128);
                i1 = chunk_Z + par2Random.nextInt(16) + 8;
                WorldGenerator var6 = new WorldGenTallGrass(TcBlock.leek.blockID, BlockLeek.META_NATURAL);
                var6.generate(par1World, par2Random, k, l, i1);
            }
        }

        if (this.generateLakes)
        {
            for (j = 0; j < 50; ++j)
            {
                k = chunk_X + par2Random.nextInt(16) + 8;
                l = par2Random.nextInt(par2Random.nextInt(120) + 8);
                i1 = chunk_Z + par2Random.nextInt(16) + 8;
                (new WorldGenLiquids(Block.waterMoving.blockID)).generate(par1World, par2Random, k, l, i1);
            }
        }

        i = this.tofuPerChunk;
        if (par2Random.nextInt(50) == 0)
        {
            ++i;
        }
        for (j = 0; j < i; ++j)
        {
            k = chunk_X + par2Random.nextInt(16) + 8;
            l = chunk_Z + par2Random.nextInt(16) + 8;
            WorldGenerator worldgenerator = this.worldGeneratorTofuBuilding;
            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
            worldgenerator.generate(par1World, par2Random, k, par1World.getHeightValue(k, l), l);
        }

    }


}
