package tsuteto.tofu.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTofuTerrain extends BlockTofuBase
{
    public BlockTofuTerrain(int par1)
    {
        super(par1, Material.sponge);
    }
    
    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return TcItem.tofuMomen.itemID;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
    	this.blockIcon = par1IconRegister.registerIcon("tofucraft:blockTofuMomen");
    }
    
    @Override
    public ItemStack createScoopedBlockStack()
    {
        return new ItemStack(TcBlock.tofuMomen);
    }
}
