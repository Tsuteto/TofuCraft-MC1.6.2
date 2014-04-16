package tsuteto.tofu.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ItemTofu extends ItemTcFood
{
    public ItemTofu(int par1, int par2, float par3, boolean par4)
    {
        super(par1, par2, par3, par4);
    }

    public String getItemNameIS(ItemStack par1ItemStack)
    {
        return this.getFullTofuName(par1ItemStack.getItemDamage());
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }

    /**
     * Returns the slab block name with step type.
     */
    public String getFullTofuName(int par1)
    {
        return super.getUnlocalizedName();
    }
    
//    /**
//     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
//     */
//    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
//    {
//        for (int var4 = 0; var4 < 3; ++var4)
//        {
//            par3List.add(new ItemStack(par1, 1, var4));
//        }
//    }
}
