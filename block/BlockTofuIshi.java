package tsuteto.tofu.block;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockTofuIshi extends BlockTofu
{

    public BlockTofuIshi(int par1, Material material)
    {
        super(par1, material);
    }

    @Override
    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return true;
    }
}
