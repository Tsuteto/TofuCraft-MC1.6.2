package tsuteto.tofu.world.tofuvillage;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.BiomeEvent;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.world.BiomeGenTofuBase;

public class GetVillageBlockIDEventHandler
{

	@ForgeSubscribe
	public void GetVillageBlockID(BiomeEvent.GetVillageBlockID event)
	{
		if(event.biome instanceof BiomeGenTofuBase)
		{
            if (event.original == Block.wood.blockID)
            {
                event.replacement = TcBlock.tofuMomen.blockID;
            }
            else if (event.original == Block.cobblestone.blockID)
            {
            	event.replacement = TcBlock.tofuMomen.blockID;
            }
            else if (event.original == Block.planks.blockID)
            {
            	event.replacement = TcBlock.tofuMomen.blockID;
            }
            else if (event.original == Block.stairsWoodOak.blockID)
            {
            	event.replacement = TcBlock.tofuStairsMomen.blockID;
            }
            else if (event.original == Block.stairsCobblestone.blockID)
            {
            	event.replacement = TcBlock.tofuStairsMomen.blockID;
            }
            else if (event.original == Block.gravel.blockID)
            {
            	event.replacement = TcBlock.tofuGrilled.blockID;
            }
            else if (event.original == Block.dirt.blockID)
            {
            	event.replacement = TcBlock.tofuDried.blockID;
            }
            else if (event.original == Block.furnaceIdle.blockID)
            {
            	event.replacement = TcBlock.saltFurnaceIdle.blockID;
            }
            else if (event.original == Block.carrot.blockID)
            {
            	event.replacement = TcBlock.soybean.blockID;
            }
            else if (event.original == Block.potato.blockID)
            {
            	event.replacement = TcBlock.soybean.blockID;
            }
            else if (event.original == Block.crops.blockID)
            {
            	event.replacement = TcBlock.soybean.blockID;
            }
            else if (event.original == Block.waterStill.blockID)
            {
                event.replacement = TcBlock.soymilkStill.blockID;
            }
            else if (event.original == Block.waterMoving.blockID)
            {
                event.replacement = TcBlock.soymilkStill.blockID;
            }
            else if (event.original == Block.lavaStill.blockID)
            {
                event.replacement = TcBlock.soymilkStill.blockID;
            }
            else if (event.original == Block.lavaMoving.blockID)
            {
                event.replacement = TcBlock.soymilkStill.blockID;
            }
            else if (event.original == Block.cloth.blockID)
            {
                event.replacement = TcBlock.tofuMomen.blockID;
            }
            else if (event.original == Block.stoneSingleSlab.blockID)
            {
                event.replacement = TcBlock.tofuSingleSlab1.blockID;
            }
            else if (event.original == Block.stoneDoubleSlab.blockID)
            {
                event.replacement = TcBlock.tofuMomen.blockID;
            }
            else if (event.original == Block.thinGlass.blockID)
            {
                event.replacement = 0;
            }
            else if (event.original == Block.fenceIron.blockID)
            {
                event.replacement = 0;
            }
            else if (event.original == Block.carpet.blockID)
            {
                event.replacement = 0;
            }
            else if (event.original == Block.chest.blockID)
            {
                event.replacement = 0; // not working...
            }
            else
            {
            	return;
            }

            event.setResult(Result.DENY);
		}
	}
}
