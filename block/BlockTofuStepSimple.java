package tsuteto.tofu.block;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tsuteto.tofu.material.TcMaterial;

public class BlockTofuStepSimple extends BlockTofuStepBase
{
    public BlockTofuStepSimple(int par1, boolean par2)
    {
        super(par1, par2, TcMaterial.tofu);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setLightOpacity(0);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return isBlockSingleSlab(this.blockID) ? this.blockID : TcBlock.tofuSingleSlabGlow.blockID;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    @Override
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 2, 0);
    }

    /**
     * Returns the slab block name with step type.
     */
    @Override
    public String getFullSlabName(int par1)
    {
        return super.getUnlocalizedName();
    }
    
    /**
     * Takes a block ID, returns true if it's the same as the ID for a stone or wooden single slab.
     */
    @Override
    protected boolean isBlockSingleSlab(int par0)
    {
        return par0 == TcBlock.tofuSingleSlabGlow.blockID;
    }
    
    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return isBlockSingleSlab(this.blockID) ? this.blockID : TcBlock.tofuSingleSlabGlow.blockID;
    }
    
    @Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (isBlockSingleSlab(par1))
        {
            super.getSubBlocks(par1, par2CreativeTabs, par3List);
        }
    }
}
