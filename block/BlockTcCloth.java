package tsuteto.tofu.block;

import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockTcCloth extends BlockColored
{

    public BlockTcCloth(int par1, Material par2Material)
    {
		super(par1, par2Material);
	}

	@Override
    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        int plantID = plant.getPlantID(world, x, y + 1, z);

        if (plantID == TcBlock.sprouts.blockID)
        {
            return true;
        }
        return false;
    }

}
