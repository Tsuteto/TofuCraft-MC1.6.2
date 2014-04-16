package tsuteto.tofu.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockFermentable extends TcBlock
{
    private int fermRate;

    public BlockFermentable(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (this.checkEnvironment(par1World, par2, par3, par4))
        {
            int fermStep = getFermStep(par1World.getBlockMetadata(par2, par3, par4));

            if (fermStep < 7 && par5Random.nextInt(fermRate) == 0)
            {
                par1World.setBlockMetadataWithNotify(par2, par3, par4, ++fermStep, 3);
            }
        }
    }
    
    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = super.getBlockDropped(world, x, y, z, metadata, fortune);
        if (this.getFermStep(metadata) == 7)
        {
            this.addFermentedItem(ret);
        }
        else
        {
            this.addIngredients(ret);
        }
        return ret;
    }
    
    public abstract void addFermentedItem(List list);
    
    public abstract void addIngredients(List list);
    
    public abstract boolean checkEnvironment(World world, int x, int y, int z);
    
    public Block setFermRate(int rate)
    {
        this.fermRate = rate;
        return this;
    }
    
    public int getFermStep(int metadata)
    {
        return metadata & 7;
    }
    
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int metadata = this.getFermStep(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
        return metadata == 7 ? 0xcb944b : 0xffffff;
    }

    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, par1);
    }
}
