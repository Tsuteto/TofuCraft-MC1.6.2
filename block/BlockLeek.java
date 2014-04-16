package tsuteto.tofu.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLeek extends BlockFlower implements IShearable
{
    public static final int META_NATURAL = 0x8;

    protected BlockLeek(int par1)
    {
        super(par1, Material.vine);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return this.blockIcon;
    }

    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return meta > 0 ? 4 : 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return TcItem.leek.itemID;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    @Override
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

//    @SideOnly(Side.CLIENT)

//    /**
//     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
//     * when first determining what to render.
//     */
//    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
//    {
//        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeGrassColor();
//    }

    @SideOnly(Side.CLIENT)

    @Override
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int meta, int fortune)
    {
        return super.getBlockDropped(world, x, y, z, meta, fortune);
    }

    @Override
    public boolean isShearable(ItemStack item, World world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, World world, int x, int y, int z, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }

    @Override
    public boolean canThisPlantGrowOnThisBlockID(int blockId)
    {
        return Block.blocksList[blockId] instanceof BlockTofuBase;
    }
}
