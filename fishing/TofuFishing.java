package tsuteto.tofu.fishing;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tsuteto.tofu.Settings;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.entity.EntityTofuCreeper;
import tsuteto.tofu.entity.EntityTofuSlime;
import tsuteto.tofu.fishing.TofuFishingLoot.TofuFishingEntryEntity;
import tsuteto.tofu.fishing.TofuFishingLoot.TofuFishingEntryEntity.ISpawnImpl;
import tsuteto.tofu.fishing.TofuFishingLoot.TofuFishingEntryItem;
import tsuteto.tofu.item.ItemFoodSet;
import tsuteto.tofu.item.TcItem;

/**
 * Tofu fishing implementation
 *
 * @author Tsuteto, original by dewfalse
 *
 */
public class TofuFishing
{

    public TofuFishing()
    {
        TofuFishingLoot.addItem(new ItemStack(TcItem.tofuMomen), 50);
        TofuFishingLoot.addItem(new ItemStack(TcItem.tofuKinu), 50);
        TofuFishingLoot.addItem(new ItemStack(TcItem.tofuIshi), 30);
        TofuFishingLoot.addItem(new ItemStack(TcItem.tofuMetal), 10);
        TofuFishingLoot.addItem(new ItemStack(TcItem.tofuGrilled), 20);
        TofuFishingLoot.addItem(new ItemStack(TcItem.tofuDried), 20);
        TofuFishingLoot.addItem(new ItemStack(TcItem.tofuDiamond), 3);
        TofuFishingLoot.addItem(new ItemStack(TcItem.foodSet, 1, ItemFoodSet.tofufishRow.id), 20);

        TofuFishingLoot.addItem(new ItemStack(TcItem.swordKinu), 6);
        TofuFishingLoot.addItem(new ItemStack(TcItem.swordMomen), 6);
        TofuFishingLoot.addItem(new ItemStack(TcItem.swordSolid), 4);
        TofuFishingLoot.addItem(new ItemStack(TcItem.swordMetal), 2);
        TofuFishingLoot.addItem(new ItemStack(TcItem.swordDiamond), 1);

        TofuFishingLoot.addItem(new ItemStack(TcItem.toolKinu[2]), 6);
        TofuFishingLoot.addItem(new ItemStack(TcItem.toolMomen[2]), 6);
        TofuFishingLoot.addItem(new ItemStack(TcItem.toolSolid[2]), 4);
        TofuFishingLoot.addItem(new ItemStack(TcItem.toolMetal[2]), 2);
        TofuFishingLoot.addItem(new ItemStack(TcItem.toolDiamond[2]), 1);

        TofuFishingLoot.addEntity(20, new ISpawnImpl() {
            @Override
            public Entity spawnEntity(World world)
            {
                EntityTofuSlime mob = new EntityTofuSlime(world);
                return mob;
            }
        });
        TofuFishingLoot.addEntity(7, new ISpawnImpl() {
            @Override
            public Entity spawnEntity(World world)
            {
                EntityTofuCreeper mob = new EntityTofuCreeper(world);
                return mob;
            }
        });
    }

    @ForgeSubscribe
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
    {
        if (event.entity.dimension != Settings.tofuDimNo)
        {
            return;
        }
        if (event.entity instanceof EntityItem)
        {
            // Avoid error when the entity contains no item
            if (event.entity.getDataWatcher().getWatchableObjectItemStack(10) == null)
            {
                return;
            }

            ItemStack is = ((EntityItem) event.entity).getEntityItem();
            if (is.itemID != Item.fishRaw.itemID)
            {
                return;
            }

            ForgeDirection[] dirs = { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
            for (ForgeDirection dir : dirs)
            {
                for (int j = 0; j <= 1; ++j)
                {
                    int blockID = event.world.getBlockId((int) Math.round(event.entity.posX) + dir.offsetX,
                            (int) Math.round(event.entity.posY) + dir.offsetY - j, (int) Math.round(event.entity.posZ) + dir.offsetZ);
                    if (blockID == TcBlock.soymilkStill.blockID)
                    {
                        TofuFishingLoot entry = TofuFishingLoot.lot();

                        if (entry instanceof TofuFishingEntryItem)
                        {
                            EntityItem entityitem = new EntityItem(event.world, event.entity.posX, event.entity.posY,
                                    event.entity.posZ, ((TofuFishingEntryItem) entry).item.copy());
                            entityitem.motionX = event.entity.motionX;
                            entityitem.motionY = event.entity.motionY;
                            entityitem.motionZ = event.entity.motionZ;
                            event.world.spawnEntityInWorld(entityitem);
                        }
                        else if (entry instanceof TofuFishingEntryEntity)
                        {
                            Entity mob = ((TofuFishingEntryEntity) entry).spawnImpl.spawnEntity(event.world);
                            mob.setLocationAndAngles(event.entity.posX, event.entity.posY, event.entity.posZ,
                                    event.world.rand.nextFloat() * 360.0F, 0.0F);
                            mob.motionX = event.entity.motionX * 2;
                            mob.motionY = event.entity.motionY * 2;
                            mob.motionZ = event.entity.motionZ * 2;
                            event.world.spawnEntityInWorld(mob);
                        }

                        event.entity.setDead();
                        event.setResult(Result.DENY);
                        return;
                    }
                }
            }
        }
    }
}
