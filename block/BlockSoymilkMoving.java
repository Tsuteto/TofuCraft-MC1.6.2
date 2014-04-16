package tsuteto.tofu.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockSoymilkMoving extends BlockTcFlowing
{

    public BlockSoymilkMoving(int par1, Material material, String[] iconNames)
    {
        super(par1, material, iconNames);
    }

    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
        
        if (par1World.getBlockMaterial(par2, par3 + 1, par4) != Material.water && par5Random.nextInt(3) == 0)
        {
            if (this.getHeatStrength(par1World, par2, par3, par4) > 0)
            {
                float steamX = par2 + 0.5F;
                float steamY = par3 + 0.9F;
                float steamZ = par4 + 0.5F;
                float steamRandX = par5Random.nextFloat() * 0.6F - 0.3F;
                float steamRandZ = par5Random.nextFloat() * 0.6F - 0.3F;
                double gRand1 = par5Random.nextGaussian() * 0.01D;
                double gRand2 = par5Random.nextGaussian() * 0.01D;
                double gRand3 = par5Random.nextGaussian() * 0.01D;
                par1World.spawnParticle("explode", (steamX + steamRandX), steamY, (steamZ + steamRandZ), gRand1, gRand2, gRand3);
            }
        }
    }

    private int getHeatStrength(World par1World, int par2, int par3, int par4)
    {
        for (int i = 2; i < 5; i++)
        {
            int blockId = par1World.getBlockId(par2, par3 - i, par4);
            if (blockId == Block.fire.blockID || blockId == Block.lavaStill.blockID || blockId == Block.lavaMoving.blockID)
            {
                return i == 2 ? 2 : 1;
            }
        }
        return 0;
    }

}
