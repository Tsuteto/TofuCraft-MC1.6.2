package tsuteto.tofu.world;

import java.util.Random;

import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.ItemTcMaterials;
import tsuteto.tofu.item.TcItem;

public class TofuDangeonChest
{
    public static final String TOFU_DANGEON = "tofuDangeon";

    public static final WeightedRandomChestContent[] chestContent = new WeightedRandomChestContent[] {
        new WeightedRandomChestContent(TcItem.tofuKinu.itemID, 0, 1, 64, 20),
        new WeightedRandomChestContent(TcItem.tofuDiamond.itemID, 0, 1, 4, 3),
        new WeightedRandomChestContent(TcItem.materials.itemID, ItemTcMaterials.tofuGem.id, 1, 32, 15),
        new WeightedRandomChestContent(TcItem.materials.itemID, ItemTcMaterials.tofuDiamondNugget.id, 1, 8, 5),
        new WeightedRandomChestContent(TcBlock.tcSapling.blockID, 1, 8, 16, 10),
        new WeightedRandomChestContent(TcItem.armorDiamond[0].itemID, 0, 1, 1, 1),
        new WeightedRandomChestContent(TcItem.armorDiamond[1].itemID, 0, 1, 1, 1),
        new WeightedRandomChestContent(TcItem.armorDiamond[2].itemID, 0, 1, 1, 1),
        new WeightedRandomChestContent(TcItem.armorDiamond[3].itemID, 0, 1, 1, 1),
        new WeightedRandomChestContent(TcItem.swordDiamond.itemID, 0, 1, 1, 1),
        new WeightedRandomChestContent(TcItem.armorMetal[0].itemID, 0, 1, 1, 5),
        new WeightedRandomChestContent(TcItem.armorMetal[1].itemID, 0, 1, 1, 5),
        new WeightedRandomChestContent(TcItem.armorMetal[2].itemID, 0, 1, 1, 5),
        new WeightedRandomChestContent(TcItem.armorMetal[3].itemID, 0, 1, 1, 5),
        new WeightedRandomChestContent(TcItem.swordMetal.itemID, 0, 1, 1, 8),
        new WeightedRandomChestContent(TcItem.tofuCake.itemID, 0, 1, 1, 10),
        new WeightedRandomChestContent(TcItem.zundaBow.itemID, 0, 1, 1, 2),
   };

    public static final ChestGenHooks chestInfo = new ChestGenHooks(TOFU_DANGEON, chestContent, 8, 15);

    public static void generateDangeonChestContent(Random rand, TileEntityChest chest)
    {
        WeightedRandomChestContent.generateChestContents(rand, chestInfo.getItems(rand), chest, chestInfo.getCount(rand));
    }

}
