package tsuteto.tofu.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tsuteto.tofu.Settings;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.achievement.TcAchievementMgr.Key;
import tsuteto.tofu.dimension.DimensionTeleportation;
import tsuteto.tofu.entity.EntityTofuPortalFX;
import tsuteto.tofu.params.DataType;
import tsuteto.tofu.params.EntityInfo;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTofuPortal extends BlockBreakable
{
    private final DimensionTeleportation teleportHandler = new DimensionTeleportation();
    
    public BlockTofuPortal(int par1)
    {
        super(par1, "tofucraft:tofuPortal", Material.portal, false);
        this.setTickRandomly(true);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

//        if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting)
//        {
//            int var6;
//
//            for (var6 = par3; !par1World.doesBlockHaveSolidTopSurface(par2, var6, par4) && var6 > 0; --var6)
//            {
//                ;
//            }
//
//            if (var6 > 0 && !par1World.isBlockNormalCube(par2, var6 + 1, par4))
//            {
//                Entity var7 = ItemMonsterPlacer.spawnCreature(par1World, TcEntity.entityIdTofuSlime, par2 + 0.5D, var6 + 1.1D, par4 + 0.5D);
//
//                if (var7 != null)
//                {
//                    var7.timeUntilPortal = var7.getPortalCooldown();
//                }
//            }
//        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float var5;
        float var6;

        if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) != this.blockID && par1IBlockAccess.getBlockId(par2 + 1, par3, par4) != this.blockID)
        {
            var5 = 0.125F;
            var6 = 0.5F;
            this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
        }
        else
        {
            var5 = 0.5F;
            var6 = 0.125F;
            this.setBlockBounds(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z
     */
    public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
    {
        Block frameBlock = TcBlock.tofuGrilled;
        byte var5 = 0;
        byte var6 = 0;

        if (par1World.getBlockId(par2 - 1, par3, par4) == frameBlock.blockID || par1World.getBlockId(par2 + 1, par3, par4) == frameBlock.blockID)
        {
            var5 = 1;
        }

        if (par1World.getBlockId(par2, par3, par4 - 1) == frameBlock.blockID || par1World.getBlockId(par2, par3, par4 + 1) == frameBlock.blockID)
        {
            var6 = 1;
        }

        if (var5 == var6)
        {
            return false;
        }
        else
        {
            if (par1World.getBlockId(par2 - var5, par3, par4 - var6) == 0)
            {
                par2 -= var5;
                par4 -= var6;
            }

            int var7;
            int var8;

            for (var7 = -1; var7 <= 2; ++var7)
            {
                for (var8 = -1; var8 <= 3; ++var8)
                {
                    boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;

                    if (var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3)
                    {
                        int var10 = par1World.getBlockId(par2 + var5 * var7, par3 + var8, par4 + var6 * var7);

                        if (var9)
                        {
                            if (var10 != frameBlock.blockID)
                            {
                                return false;
                            }
                        }
                        else if (var10 != 0 && var10 != Block.fire.blockID)
                        {
                            return false;
                        }
                    }
                }
            }

            for (var7 = 0; var7 < 2; ++var7)
            {
                for (var8 = 0; var8 < 3; ++var8)
                {
                    par1World.setBlock(par2 + var5 * var7, par3 + var8, par4 + var6 * var7, TcBlock.tofuPortal.blockID, 0, 2);
                }
            }
            return true;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        Block frameBlock = TcBlock.tofuGrilled;
        byte var6 = 0;
        byte var7 = 1;

        if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
        {
            var6 = 1;
            var7 = 0;
        }

        int var8;

        for (var8 = par3; par1World.getBlockId(par2, var8 - 1, par4) == this.blockID; --var8)
        {
            ;
        }

        if (par1World.getBlockId(par2, var8 - 1, par4) != frameBlock.blockID)
        {
            par1World.setBlockToAir(par2, par3, par4);
        }
        else
        {
            int var9;

            for (var9 = 1; var9 < 4 && par1World.getBlockId(par2, var8 + var9, par4) == this.blockID; ++var9)
            {
                ;
            }

            if (var9 == 3 && par1World.getBlockId(par2, var8 + var9, par4) == frameBlock.blockID)
            {
                boolean var10 = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
                boolean var11 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;

                if (var10 && var11)
                {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else
                {
                    if ((par1World.getBlockId(par2 + var6, par3, par4 + var7) != frameBlock.blockID || par1World.getBlockId(par2 - var6, par3, par4 - var7) != this.blockID) && (par1World.getBlockId(par2 - var6, par3, par4 - var7) != frameBlock.blockID || par1World.getBlockId(par2 + var6, par3, par4 + var7) != this.blockID))
                    {
                        par1World.setBlockToAir(par2, par3, par4);
                    }
                }
            }
            else
            {
                par1World.setBlockToAir(par2, par3, par4);
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID)
        {
            return false;
        }
        else
        {
            boolean var6 = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID;
            boolean var7 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID;
            boolean var8 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID;
            boolean var9 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID;
            boolean var10 = var6 || var7;
            boolean var11 = var8 || var9;
            return var10 && par5 == 4 ? true : (var10 && par5 == 5 ? true : (var11 && par5 == 2 ? true : var11 && par5 == 3));
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null)
        {
            byte var3;

            if (par5Entity.worldObj.provider.dimensionId == Settings.tofuDimNo)
            {
                var3 = 0;
            }
            else
            {
                var3 = (byte)Settings.tofuDimNo;
            }

            if (!par1World.isRemote)
            {
                if (par5Entity instanceof EntityPlayerMP)
                {
                    EntityInfo pinfo = EntityInfo.instance();
                    if (!pinfo.doesDataExist(par5Entity.entityId, DataType.TicksPortalCooldown))
                    {
                        this.travelToDimension(var3, (EntityPlayerMP)par5Entity);
    
                        // Make a sound on client side
                        PacketDispatcher.sendPacketToPlayer(
                                new Packet250CustomPayload(TofuCraftCore.netChannelDimTrip, new byte[0]), (Player)par5Entity);
                        
                        TcAchievementMgr.achieve((EntityPlayerMP)par5Entity, Key.tofuWorld);
                    }
                    pinfo.set(par5Entity.entityId, DataType.TicksPortalCooldown, 0);
                }
                else
                {
                    EntityInfo pinfo = EntityInfo.instance();
                    if (!pinfo.doesDataExist(par5Entity.entityId, DataType.TicksPortalCooldown))
                    {
                        Entity traveledEntity = this.travelToDimension(var3, par5Entity);
                        if (traveledEntity != null)
                        {
                            pinfo.set(traveledEntity.entityId, DataType.TicksPortalCooldown, 0);
                        }
                    }
                    else
                    {
                        pinfo.set(par5Entity.entityId, DataType.TicksPortalCooldown, 0);
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par5Random.nextInt(100) == 0)
        {
            par1World.playSound(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int var6 = 0; var6 < 4; ++var6)
        {
            double var7 = (par2 + par5Random.nextFloat());
            double var9 = (par3 + par5Random.nextFloat());
            double var11 = (par4 + par5Random.nextFloat());
            double var13 = 0.0D;
            double var15 = 0.0D;
            double var17 = 0.0D;
            int var19 = par5Random.nextInt(2) * 2 - 1;
            var13 = (par5Random.nextFloat() - 0.5D) * 0.5D;
            var15 = (par5Random.nextFloat() - 0.5D) * 0.5D;
            var17 = (par5Random.nextFloat() - 0.5D) * 0.5D;

            if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID && par1World.getBlockId(par2 + 1, par3, par4) != this.blockID)
            {
                var7 = par2 + 0.5D + 0.25D * var19;
                var13 = (par5Random.nextFloat() * 2.0F * var19);
            }
            else
            {
                var11 = par4 + 0.5D + 0.25D * var19;
                var17 = (par5Random.nextFloat() * 2.0F * var19);
            }

            EntityTofuPortalFX fx = new EntityTofuPortalFX(par1World, var7, var9, var11, var13, var15, var17);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    @Override
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return 0;
    }

    /**
     * Teleports the entity to another dimension. Params: Dimension number to teleport to
     */
    public void travelToDimension(int par1, EntityPlayerMP player)
    {
        teleportHandler.transferPlayerToDimension(player, par1);
    }
    
    /**
     * Teleports the entity to another dimension. Params: Dimension number to teleport to
     */
    public Entity travelToDimension(int par1, Entity entity)
    {
        return teleportHandler.transferEntityToDimension(entity, par1);
    }
}
