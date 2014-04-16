package tsuteto.tofu.world.gen.feature;

import java.util.Random;

import tsuteto.tofu.block.TcBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;

public class WorldGenApricotTrees extends WorldGenTcTreesBase
{
    public WorldGenApricotTrees(boolean par1)
    {
        super(par1, 4, TcBlock.tcLog, 0, TcBlock.tcLeaves, 0, false);
    }
}
