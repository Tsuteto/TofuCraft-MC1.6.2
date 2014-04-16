package tsuteto.tofu.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockTcStationary extends BlockFluidClassic
{
    private final String[] iconNames;
    private Icon[] theIcon;
    private int fluidColor = 0xffffff;

    public BlockTcStationary(int par1, Fluid fluid, Material material, String[] iconNames)
    {
        super(par1, fluid, material);
        this.iconNames = iconNames;
        disableStats();
    }

    public BlockTcStationary setColor(int color)
    {
        this.fluidColor = color;
        return this;
    }
    
    @Override
    public int getBlockColor()
    {
        return 16777215;
    }
    
    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return fluidColor;
    }
    
    @Override
    public Icon getIcon(int par1, int par2)
    {
        return par1 != 0 && par1 != 1 ? this.theIcon[1] : this.theIcon[0];
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.theIcon = new Icon[] {par1IconRegister.registerIcon(iconNames[0]), par1IconRegister.registerIcon(iconNames[1])};
    }
    
    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {
        int bId = world.getBlockId(x, y, z);
        if (!(Block.blocksList[bId] instanceof BlockFluidBase))
        {
            if (world.getBlockMaterial(x, y, z).isLiquid()) return false;
        }
        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
        int bId = world.getBlockId(x, y, z);
        if (!(Block.blocksList[bId] instanceof BlockFluidBase))
        {
            if (world.getBlockMaterial(x, y, z).isLiquid()) return false;
        }
        return super.displaceIfPossible(world, x, y, z);
    }
}
