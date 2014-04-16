package tsuteto.tofu.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBottleSoyMilk extends ItemDrinkBottle
{
    public static final Flavor[] flavorList = new Flavor[16];

    public static Flavor flvPlain = new Flavor(0, null, 0xf5f7df, Item.melon);
    public static Flavor flvKinako = new Flavor(1, "kinako", 0xd6bc2d, Item.bakedPotato);
    public static Flavor flvCocoa = new Flavor(2, "cocoa", 0x8d3d0d, Item.bakedPotato);
    public static Flavor flvZunda = new Flavor(3, "zunda", 0x95e24a, Item.bakedPotato);
    public static Flavor flvApple = new Flavor(4, "apple", 0xf2e087, Item.bakedPotato);
    public static Flavor flvPumpkin = new Flavor(5, "pumpkin", 0xffb504, Item.bakedPotato);
    public static Flavor flvRamune = new Flavor(6, "ramune", 0xa1c7ff, Item.bakedPotato);
    public static Flavor flvStrawberry = new Flavor(7, "strawberry", 0xf4a4b7, Item.bakedPotato);
    public static Flavor flvSakura = new Flavor(8, "sakura", 0xffd1d7, Item.bakedPotato);

    public static class Flavor
    {
        public final int id;
        public final String name;
        public final int color;
        public final ItemFood energyFood;

        public Flavor(int id, String name, int color, Item foodItem)
        {
            this.id = id;
            this.name = name;
            this.color = color;
            this.energyFood = (ItemFood)foodItem;
            ItemBottleSoyMilk.flavorList[id] = this;
        }
    }


    public ItemBottleSoyMilk(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public ItemFood getEnergyFood(int itemDamage)
    {
        return flavorList[itemDamage].energyFood;
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected int getColorFromDamage(int itemDamage)
    {
        return flavorList[itemDamage].color;
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int i = 0; i < flavorList.length; i++)
        {
            if (flavorList[i] != null)
            {
                par3List.add(new ItemStack(this, 1, i));
            }
        }
    }



    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int itemDamage = par1ItemStack.getItemDamage();
        return this.getUnlocalizedName()
                + (flavorList[itemDamage].name != null ? "." + flavorList[itemDamage].name : "");
    }
}
