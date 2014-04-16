package tsuteto.tofu.item;

import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ItemSoybeansHell extends ItemTcSeeds implements IPlantable
{
    public ItemSoybeansHell(int par1)
    {
        super(par1, TcBlock.soybeanHell.blockID, Block.slowSand.blockID);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public EnumPlantType getPlantType(World world, int x, int y, int z)
    {
        return EnumPlantType.Nether;
    }

    @Override
    public int getPlantID(World world, int x, int y, int z)
    {
        return TcBlock.soybeanHell.blockID;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z)
    {
        return 0;
    }
}
