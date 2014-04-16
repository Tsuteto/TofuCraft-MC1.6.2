package tsuteto.tofu.village;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.common.BiomeDictionary;
import tsuteto.tofu.Settings;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.block.BlockLeek;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.util.ModLog;

/**
 * Tofu cook's House in village
 * 
 * @author Tsuteto
 *
 */
public class ComponentVillageHouseTofu extends ComponentVillage
{
    public static final int[] displayedItemList = new int[]{
            TcItem.tofuKinu.itemID, TcItem.tofuMomen.itemID, TcItem.tofuMetal.itemID,
            TcItem.tofuZunda.itemID, TcItem.tofuMiso.itemID, TcItem.tofuStrawberry.itemID};
    
    private ComponentVillageStartPiece startPiece;
    private int averageGroundLevel = -1;

    public ComponentVillageHouseTofu() {}
    
    public ComponentVillageHouseTofu(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
    {
        super(par1ComponentVillageStartPiece, par2);
        this.startPiece = par1ComponentVillageStartPiece;
        this.coordBaseMode = par5;
        this.boundingBox = par4StructureBoundingBox;
    }

    /**
     * second Part of Structure generating, this for example places Spiderwebs, Mob Spawners, it closes Mineshafts at
     * the end, it adds Fences...
     */
    @Override
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
    {
        ModLog.debug(startPiece);
        if (startPiece != null)
        {
            if (BiomeDictionary.isBiomeOfType(startPiece.biome, TofuCraftCore.BIOME_TYPE_TOFU)) return false;
        }
        
        if (this.averageGroundLevel < 0)
        {
            this.averageGroundLevel = this.getAverageGroundLevel(par1World, par3StructureBoundingBox);

            if (this.averageGroundLevel < 0)
            {
                return true;
            }

            this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 6 - 1, 0);
        }
        
        int width = 8;
        int height = 4;
        int length = 6;

        // Floor
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, width, 0, length, TcBlock.tofuIshi.blockID, TcBlock.tofuIshi.blockID, false);
        // Ceiling
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 4, 0, width, height, length, TcBlock.tofuIshi.blockID, TcBlock.tofuIshi.blockID, false);
        
        // Wall
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0,     1, 0,      0,     height - 1, length, TcBlock.tofuIshi.blockID, TcBlock.tofuIshi.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, width, 1, 0,      width, height - 1, length, TcBlock.tofuIshi.blockID, TcBlock.tofuIshi.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0,     1, length, width, height - 1, length, TcBlock.tofuIshi.blockID, TcBlock.tofuIshi.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0,     1, 0,      width, height - 1, 0,      TcBlock.tofuIshi.blockID, TcBlock.tofuIshi.blockID, false);
        
        // Window
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, length / 2 - 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, length / 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, 0, 2, length / 2 + 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, width / 2 - 1, 2, length, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, width / 2, 2, length, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, width / 2 + 1, 2, length, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, width, 2, length / 2 - 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, width, 2, length / 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.thinGlass.blockID, 0, width, 2, length / 2 + 1, par3StructureBoundingBox);
                
        // Door
        this.placeDoorAtCurrentPosition(par1World, par3StructureBoundingBox, par2Random, width / 2, 1, 0, this.getMetadataWithOffset(Block.doorWood.blockID, 1));

        if (this.getBlockIdAtCurrentPosition(par1World, 2, 0, -1, par3StructureBoundingBox) == 0 && this.getBlockIdAtCurrentPosition(par1World, 2, -1, -1, par3StructureBoundingBox) != 0)
        {
            this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuStairsIshi.blockID, this.getMetadataWithOffset(Block.stairsCobblestone.blockID, 3), width / 2, 0, -1, par3StructureBoundingBox);
        }

        // Cleaning
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 1, 1, width - 1, height - 1, length - 1, 0, 0, false);
        
        /*
         * Basement
         */
        // Room
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, -3, 1, width - 1, -1, length - 1, 0, 0, false);
        // Floor
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, -4, 0, width, -4, length, Block.planks.blockID, Block.planks.blockID, false);
        // Wall
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, -3, 0, 0, -1, length, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, width, -3, 0, width, -1, length, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, -3, length, width, -1, length, Block.planks.blockID, Block.planks.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, -3, 0, width, -1, 0, Block.planks.blockID, Block.planks.blockID, false);
        
        // Stairs
        this.placeBlockAtCurrentPosition(par1World, 0, 0, width - 1, 0, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, width - 1, 0, 3, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, width - 1, 0, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, 0, 0, width - 1, 0, 5, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 2), width - 1, 0, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 2), width - 1, -1, 3, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 2), width - 1, -2, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -1, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -2, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -3, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -1, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -3, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -2, 3, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -3, 3, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -3, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.planks.blockID, 0, width - 1, -3, 5, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.stairsWoodOak.blockID, this.getMetadataWithOffset(Block.stairsWoodOak.blockID, 0), width - 2, -3, 5, par3StructureBoundingBox);
        
        /*
         * Interior
         */
        
        int i, j, k;
        
        // Carpet
        for (i = 1; i <= length - 1; ++i)
        {
            for (j = 1; j <= width - 2; ++j)
            {
                this.placeBlockAtCurrentPosition(par1World, Block.carpet.blockID, 0, j, 1, i, par3StructureBoundingBox);
            }
        }
        this.placeBlockAtCurrentPosition(par1World, Block.carpet.blockID, 0, width - 1, 1, 1, par3StructureBoundingBox);
        
        // Torch
        this.placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 0, width / 2, 3, 1, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 0, width - 2, -2, length / 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.torchWood.blockID, 0, 1, -2, length / 2, par3StructureBoundingBox);

        // Workbench
        this.placeBlockAtCurrentPosition(par1World, Block.workbench.blockID, 0, 2, 1, 5, par3StructureBoundingBox);
        // Salt Furnace
        this.placeBlockAtCurrentPosition(par1World, TcBlock.saltFurnaceIdle.blockID, 0, 1, 1, 5, par3StructureBoundingBox);
        // Cauldron
        this.placeBlockAtCurrentPosition(par1World, Block.cauldron.blockID, 3, 1, 2, 5, par3StructureBoundingBox);
        // Book shelf
        this.placeBlockAtCurrentPosition(par1World, Block.bookShelf.blockID, 0, 6, 1, 3, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.bookShelf.blockID, 0, 6, 1, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.bookShelf.blockID, 0, 6, 1, 5, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.bookShelf.blockID, 0, 6, 2, 3, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.bookShelf.blockID, 0, 6, 2, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, Block.bookShelf.blockID, 0, 6, 2, 5, par3StructureBoundingBox);
        
        // Item frame
        k = 0;
        for (j = 3; j >= 2; j--)
        {
            for (i = 1; i <= 3; i++)
            {
                this.hangItemFrame(par1World, par3StructureBoundingBox, i, j, 0, new ItemStack(Item.itemsList[displayedItemList[k++]]));
            }
        }

        // Tofu
        this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuMomen.blockID, 0, 2, -3, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuMomen.blockID, 0, 3, -3, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuMomen.blockID, 0, 4, -3, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuMomen.blockID, 0, 5, -3, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuMomen.blockID, 0, 2, -3, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuMomen.blockID, 0, 3, -3, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.tofuMomen.blockID, 0, 4, -3, 4, par3StructureBoundingBox);
        // Weight
        this.placeBlockAtCurrentPosition(par1World, TcBlock.cobblestone.blockID, 0, 2, -2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.cobblestone.blockID, 0, 3, -2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.cobblestone.blockID, 0, 4, -2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.cobblestone.blockID, 0, 5, -2, 2, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.cobblestone.blockID, 0, 2, -2, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.cobblestone.blockID, 0, 3, -2, 4, par3StructureBoundingBox);
        this.placeBlockAtCurrentPosition(par1World, TcBlock.cobblestone.blockID, 0, 4, -2, 4, par3StructureBoundingBox);
        
        // Leek
        for (i = 0; i < 20; i++)
        {
            j = par2Random.nextInt(width - 1) + 1;
            k = par2Random.nextInt(length - 1) + 1;
            
            this.placeBlockAtCurrentPosition(par1World, TcBlock.leek.blockID, BlockLeek.META_NATURAL, j, height + 1, k, par3StructureBoundingBox);
        }
        
        for (i = 0; i <= length; ++i)
        {
            for (j = 0; j <= width; ++j)
            {
                this.clearCurrentPositionBlocksUpwards(par1World, j, height + 2, i, par3StructureBoundingBox);
                this.fillCurrentPositionBlocksDownwards(par1World, Block.cobblestone.blockID, 0, j, -5, i, par3StructureBoundingBox);
            }
        }

        this.spawnVillagers(par1World, par3StructureBoundingBox, 1, 1, 2, 1);
        return true;
    }
    
    protected void hangItemFrame(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, ItemStack item)
    {
        int j1 = this.getXWithOffset(par3, par5);
        int k1 = this.getYWithOffset(par4);
        int l1 = this.getZWithOffset(par3, par5);

        if (!par2StructureBoundingBox.isVecInside(j1, k1, l1))
        {
            return;
        }

        int i1 = this.coordBaseMode;
        EntityItemFrame itemFrame = new EntityItemFrame(par1World, j1, k1, l1, i1);
        itemFrame.setDisplayedItem(item);
        
        if (itemFrame.onValidSurface())
        {
            par1World.spawnEntityInWorld(itemFrame);
        }
    }
    
    @Override
    protected int getVillagerType(int par1)
    {
        return Settings.professionIdTofucook;
    }
}
