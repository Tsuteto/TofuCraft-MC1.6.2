package tsuteto.tofu;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.fluids.TcFluids;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItem;

import com.google.common.collect.Lists;

/**
 * Collection of TF materials
 * 
 * TODO: Registration of fluids
 * 
 * @author Tsuteto
 *
 */
public class TfMaterialRegistry
{
    private static final List<TfMaterialRegistry> tfMaterialList = Lists.newArrayList();
    private static TfMaterialRegistry soymilkBucket;
    
    public static void init()
    {
        soymilkBucket = registerMaterial(TcItem.bucketSoymilk.itemID, 40);
        registerMaterial(TcItem.soybeans.itemID, 20);
        registerMaterial(TcItem.tofuKinu.itemID, 10);
        registerMaterial(TcBlock.tofuKinu.blockID, 40);
        registerMaterial(TcItem.tofuMomen.itemID, 10);
        registerMaterial(TcBlock.tofuMomen.blockID, 40);
        registerMaterial(TcItem.tofuIshi.itemID, 20);
        registerMaterial(TcBlock.tofuIshi.blockID, 80);
        registerMaterial(TcItem.tofuMetal.itemID, 30);
        registerMaterial(TcBlock.tofuMetal.blockID, 120);
        registerMaterial(TcItem.materials.itemID, ItemTcMaterials.tofuGem.id, 600);
        registerMaterial(TcItem.tofuHell.itemID, 100);
        registerMaterial(TcBlock.tofuHell.blockID, 400);
        registerMaterial(TcItem.materials.itemID, ItemTcMaterials.tofuDiamondNugget.id, 200);
        registerMaterial(TcItem.tofuDiamond.itemID, 1800);
        registerMaterial(TcBlock.tofuDiamond.blockID, 8000);
    }
    
    public static TfMaterialRegistry registerMaterial(int itemID, float tfAmount)
    {
        TfMaterialRegistry newEntry = new TfMaterialRegistry(itemID, tfAmount);
        tfMaterialList.add(newEntry);
        return newEntry;
    }
    
    public static void registerMaterial(int itemID, int itemDmg, float tfAmount)
    {
        tfMaterialList.add(new TfMaterialRegistry(itemID, itemDmg, tfAmount));
    }
    
    public static float getTfAmount(ItemStack itemstack)
    {
        // For soymilk container items
        FluidStack content = FluidContainerRegistry.getFluidForFilledItem(itemstack);
        if (content != null && content.fluidID == TcFluids.SOYMILK.getID())
        {
            return calcTfAmountFrom(content.amount);
        }
        else
        {
            for (TfMaterialRegistry entry : tfMaterialList)
            {
                if (entry.matches(itemstack))
                {
                    return entry.tfAmount;
                }
            }
        }
        return 0;
    }
    
    public static boolean isTfMaterial(ItemStack itemstack)
    {
        return getTfAmount(itemstack) > 0;
    }
    
    public static float getSoymilkTfAmount()
    {
        return soymilkBucket.tfAmount;
    }
    
    public static float calcTfAmountFrom(int millibucketsOfSoymilk)
    {
        return millibucketsOfSoymilk * TfMaterialRegistry.getSoymilkTfAmount() / FluidContainerRegistry.BUCKET_VOLUME;
    }
    
    public static int calcSoymilkAmountFrom(float tfAmount)
    {
        return (int)(tfAmount / TfMaterialRegistry.getSoymilkTfAmount() * FluidContainerRegistry.BUCKET_VOLUME);
    }
    
    public final int itemID;
    public final int itemDmg;
    
    public final float tfAmount;
    
    private TfMaterialRegistry(int itemID, float tfAmount)
    {
        this(itemID, OreDictionary.WILDCARD_VALUE, tfAmount);
    }
    
    private TfMaterialRegistry(int itemID, int itemDmg, float tfAmount)
    {
        this.itemID = itemID;
        this.itemDmg = itemDmg;
        this.tfAmount = tfAmount;
    }

    public boolean matches(ItemStack is)
    {
        if (is == null) return false;
        return is.itemID == this.itemID
                && (this.itemDmg == OreDictionary.WILDCARD_VALUE || this.itemDmg == is.getItemDamage());
    }
}
