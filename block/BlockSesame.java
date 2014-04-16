package tsuteto.tofu.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import tsuteto.tofu.item.TcItem;

public class BlockSesame extends BlockCrops
{
    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public BlockSesame(int par1)
    {
        super(par1);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    @Override
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 7)
        {
            return this.icons[par2 >> 1];
        }
        else
        {
            return this.icons[3];
        }
    }
    
    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
    {
        return 1;
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        return 1;
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    @Override
    protected int getSeedItem()
    {
        return TcItem.sesame.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    @Override
    protected int getCropItem()
    {
        return TcItem.sesame.itemID;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par1 >= 7 ? this.getCropItem() : this.getSeedItem();
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return EnumPlantType.Crop;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[4];

        for (int i = 0; i < this.icons.length; ++i)
        {
            this.icons[i] = par1IconRegister.registerIcon("tofucraft:sesame_" + i);
        }
    }
}
