package tsuteto.tofu.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import tsuteto.tofu.Settings;
import tsuteto.tofu.dimension.TofuTeleporter;

class ItemTofuStick extends TcItem
{
    public ItemTofuStick(int var1)
    {
        super(var1);
        this.setMaxDamage(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {
        int currDim = player.dimension;
        if (currDim == Settings.tofuDimNo || currDim == 0)
        {
            if (!world.isRemote)
            {
                MovingObjectPosition mpos = this.getMovingObjectPositionFromPlayer(world, player, false);
    
                if (mpos != null && mpos.typeOfHit == EnumMovingObjectType.TILE)
                {
                    int i = mpos.blockX;
                    int j = mpos.blockY;
                    int k = mpos.blockZ;
                    boolean isSuccess = this.activate(itemstack, player, world, i, j, k, mpos.sideHit);
                }
            }
            else
            {
                // Emit particles
                for (int var1 = 0; var1 < 16; ++var1)
                {
                    double mx = (this.itemRand.nextFloat() * 2.0F - 1.0F);
                    double my = (this.itemRand.nextFloat() * 2.0F - 1.0F);
                    double mz = (this.itemRand.nextFloat() * 2.0F - 1.0F);
                    if (mx * mx + my * my + mz * mz <= 1.0D)
                    {
                        Vec3 lookVec = player.getLookVec();
                        world.spawnParticle("crit", player.posX + lookVec.xCoord, player.posY + lookVec.yCoord, player.posZ + lookVec.zCoord, mx, my + 0.2f, mz);
                    }
                }
            }
        }
        return itemstack;
    }

    public boolean activate(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7)
    {
        if (par7 == 0)
        {
            --par5;
        }

        if (par7 == 1)
        {
            ++par5;
        }

        if (par7 == 2)
        {
            --par6;
        }

        if (par7 == 3)
        {
            ++par6;
        }

        if (par7 == 4)
        {
            --par4;
        }

        if (par7 == 5)
        {
            ++par4;
        }

        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else
        {
            if (par3World.isAirBlock(par4, par5, par6))
            {
                this.tryToCreatePortal(par3World, par4, par5, par6);
            }
            return true;
        }
    }

    /**
     * Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z
     */
    public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
    {
        byte b0 = 0;
        byte b1 = 0;
        Block frameBlock = TofuTeleporter.frameBlock;
        Block portalBlock = TofuTeleporter.portalBlock;

        if (par1World.getBlockId(par2 - 1, par3, par4) == frameBlock.blockID || par1World.getBlockId(par2 + 1, par3, par4) == frameBlock.blockID)
        {
            b0 = 1;
        }

        if (par1World.getBlockId(par2, par3, par4 - 1) == frameBlock.blockID || par1World.getBlockId(par2, par3, par4 + 1) == frameBlock.blockID)
        {
            b1 = 1;
        }

        if (b0 == b1)
        {
            return false;
        }
        else
        {
            if (par1World.isAirBlock(par2 - b0, par3, par4 - b1))
            {
                par2 -= b0;
                par4 -= b1;
            }

            int l;
            int i1;

            for (l = -1; l <= 2; ++l)
            {
                for (i1 = -1; i1 <= 3; ++i1)
                {
                    boolean flag = l == -1 || l == 2 || i1 == -1 || i1 == 3;

                    if (l != -1 && l != 2 || i1 != -1 && i1 != 3)
                    {
                        int j1 = par1World.getBlockId(par2 + b0 * l, par3 + i1, par4 + b1 * l);
                        boolean isAirBlock = par1World.isAirBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l);

                        if (flag)
                        {
                            if (j1 != frameBlock.blockID)
                            {
                                return false;
                            }
                        }
                        else if (!isAirBlock && j1 != Block.fire.blockID)
                        {
                            return false;
                        }
                    }
                }
            }

            for (l = 0; l < 2; ++l)
            {
                for (i1 = 0; i1 < 3; ++i1)
                {
                    par1World.setBlock(par2 + b0 * l, par3 + i1, par4 + b1 * l, portalBlock.blockID, 0, 2);
                }
            }

            return true;
        }
    }
    
    @Override
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.uncommon;
    }
}