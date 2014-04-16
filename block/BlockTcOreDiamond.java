package tsuteto.tofu.block;

import java.util.Random;

import tsuteto.tofu.material.TcMaterial;

public class BlockTcOreDiamond extends BlockTcOre
{

    public BlockTcOreDiamond(int par1, int minXp, int maxXp)
    {
        super(par1, TcMaterial.tofu, minXp, maxXp);
    }

    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(3) + 1;
    }
}
