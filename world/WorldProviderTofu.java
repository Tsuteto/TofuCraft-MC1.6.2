package tsuteto.tofu.world;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import tsuteto.tofu.Settings;
import tsuteto.tofu.util.Utils;

public class WorldProviderTofu extends WorldProvider
{
    /**
     * creates a new world chunk manager for WorldProvider
     */
    @Override
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerTofu(worldObj);
        this.dimensionId = Settings.tofuDimNo;
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    @Override
    public IChunkProvider createChunkGenerator()
    {
        long newSeed = Utils.getSeedForTofuWorld(this.worldObj);
        return new ChunkProviderTofu(this.worldObj, newSeed, true);
    }
//
//    /**
//     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
//     */
//    public boolean isSurfaceWorld()
//    {
//        return true;
//    }
//
//    /**
//     * Will check if the x, z position specified is alright to be set as the map spawn point
//     */
//    public boolean canCoordinateBeSpawn(int par1, int par2)
//    {
//        return false;
//    }
//
//    /**
//     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
//     */
//    public float calculateCelestialAngle(long par1, float par3)
//    {
//        return 0.5F;
//    }
//
    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    @Override
    public String getDimensionName()
    {
        return "Tofu World";
    }

    @Override
    public String getWelcomeMessage()
    {
        return "Entering the Tofu World";
    }

    /**
     * A Message to display to the user when they transfer out of this dismension.
     *
     * @return The message to be displayed
     */
    @Override
    public String getDepartMessage()
    {
        return "Leaving the Tofu World";
    }
}
