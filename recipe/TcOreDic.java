package tsuteto.tofu.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.ItemFoodSet;
import tsuteto.tofu.item.ItemGelatin;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.util.ModLog;

public enum TcOreDic
{
    // === Blocks ===
    blockSalt(TcBlock.salt),
    blockNatto(TcBlock.natto),

    blockTofuKinu(TcBlock.tofuKinu),
    blockTofuMomen(TcBlock.tofuMomen),
    blockTofuIshi(TcBlock.tofuIshi),
    blockTofuMetal(TcBlock.tofuMetal),
    blockTofuGrilled(TcBlock.tofuGrilled),
    blockTofuDried(TcBlock.tofuDried),
    blockTofuFriedPouch(TcBlock.tofuFriedPouch),
    blockTofuFried(TcBlock.tofuFried),
    blockTofuEgg(TcBlock.tofuEgg),
    blockTofuAnnin(TcBlock.tofuAnnin),
    blockTofuSesame(TcBlock.tofuSesame),
    blockTofuZunda(TcBlock.tofuZunda),
    blockTofuStrawberry(TcBlock.tofuStrawberry),
    blockTofuMiso(TcBlock.tofuMiso),
    blockTofuHell(TcBlock.tofuHell),
    blockTofuGlow(TcBlock.tofuGlow),
    blockTofuDiamond(TcBlock.tofuDiamond),

    oreTofu(TcBlock.oreTofu),
    oreTofuDiamond(TcBlock.oreTofuDiamond),

    leavesApricot(new ItemStack(TcBlock.tcLeaves, 1, 0)),
    leavesApricotF(new ItemStack(TcBlock.tcLeaves, 1, 1)),
    leavesTofu(new ItemStack(TcBlock.tcLeaves, 1, 2)),
//    logApricot(new ItemStack(TcBlock.tcLog, 1, 0)),
    saplingApricot(new ItemStack(TcBlock.tcSapling, 1, 0)),
    saplingTofu(new ItemStack(TcBlock.tcSapling, 1, 1)),

    // === Items ===
    salt(TcItem.salt),
    nigari(TcItem.nigari),
    leek(TcItem.leek),
    miso(TcItem.miso),
    natto(TcItem.natto),

    soybeans(TcItem.soybeans),
    soybeansParched(TcItem.soybeansParched),
    soybeansHell(TcItem.soybeansHell),
    kinako(TcItem.kinako),
    edamame(TcItem.edamame),
    edamameBoiled(TcItem.edamameBoiled),
    zunda(TcItem.zunda),
    okara(TcItem.okara),


    tofuKinu(TcItem.tofuKinu),
    tofuMomen(TcItem.tofuMomen),
    tofuIshi(TcItem.tofuIshi),
    tofuMetal(TcItem.tofuMetal),
    tofuGrilled(TcItem.tofuGrilled),
    tofuDried(TcItem.tofuDried),
    tofuFriedPouch(TcItem.tofuFriedPouch),
    tofuFried(TcItem.tofuFried),
    tofuEgg(TcItem.tofuEgg),
    tofuAnnin(TcItem.tofuAnnin),
    tofuSesame(TcItem.tofuSesame),
    tofuZunda(TcItem.tofuZunda),
    tofuStrawberry(TcItem.tofuStrawberry),
    tofuMiso(TcItem.tofuMiso),
    tofuHell(TcItem.tofuHell),
    tofuGlow(TcItem.tofuGlow),
    tofuDiamond(TcItem.tofuDiamond),

    bucketSoySauce(TcItem.bucketSoySauce),
    bucketSoymilk(TcItem.bucketSoymilk),
    bucketSoymilkHell(TcItem.bucketSoymilkHell),

    defattingPotion(TcItem.defattingPotion),
    dashi(new ItemStack(TcItem.dashi, 1, OreDictionary.WILDCARD_VALUE)),
    soyOil(new ItemStack(TcItem.soyOil, 1, OreDictionary.WILDCARD_VALUE)),
    bottleSoySauce(new ItemStack(TcItem.bottleSoySauce, 1, OreDictionary.WILDCARD_VALUE)),
    
    barrel(TcItem.barrelEmpty),
    barrelEmpty(TcItem.barrelEmpty),
    barrelMiso(TcItem.barrelMiso),
    barrelMisoTofu(TcItem.barrelMisoTofu),
    barrelGlowtofu(TcItem.barrelGlowtofu),

    kouji(TcItem.kouji),
    mincedPotato(TcItem.mincedPotato),
    filterCloth(TcItem.filterCloth),
    apricot(TcItem.apricot),
    apricotSeed(TcItem.apricotSeed),
    gelatin(new ItemStack(TcItem.gelatin, 1, ItemGelatin.Materials.gelatin.ordinal())),
    kyoninso(TcItem.kyoninso),
    sesame(TcItem.sesame),
    starch(TcItem.starch),
    doubanjiang(new ItemStack(TcItem.doubanjiang, 1, OreDictionary.WILDCARD_VALUE)),
    strawberryJam(new ItemStack(TcItem.strawberryJam, 1, OreDictionary.WILDCARD_VALUE)),
    onigiri(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.onigiri.id)),
    sprouts(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.sprouts.id)),
    oage(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.oage.id)),
    tofuChikuwa(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuChikuwa.id)),
    tofuDiamondNugget(new ItemStack(TcItem.materials, 1, ItemTcMaterials.tofuDiamondNugget.id)),
    tofuGem(new ItemStack(TcItem.materials, 1, ItemTcMaterials.tofuGem.id)),
    yuba(TcItem.yuba),
    tofuHamburgRaw(new ItemStack(TcItem.materials, 1, ItemTcMaterials.tofuHamburgRaw.id)),
    tofuHamburg(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuHamburg.id)),
    hamburg(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuHamburg.id)),
    tofuCookie(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuCookie.id)),
    cookie(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofuCookie.id)),
    tofufishRaw(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofufishRow.id)),
    tofufishCooked(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofufishCooked.id)),
    
    tfMachineCase(TcBlock.tfMachineCase),
    tfCapacitor(new ItemStack(TcItem.materials, 1, ItemTcMaterials.tfCapacitor.id)),
    ;

    TcOreDic(Item... items)
    {
        for (Item item : items)
        {
            OreDictionary.registerOre(this.name(), item);
        }
    }

    TcOreDic(Block... blocks)
    {
        for (Block block : blocks)
        {
            OreDictionary.registerOre(this.name(), block);
        }
    }

    TcOreDic(ItemStack... itemstacks)
    {
        for (ItemStack itemstack : itemstacks)
        {
            OreDictionary.registerOre(this.name(), itemstack);
        }
    }
    
    public static void load()
    {
        ModLog.debug("Loading TcOreDic");
    }
}
