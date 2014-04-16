package tsuteto.tofu.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidContainerItem;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.block.tileentity.TileEntityTfStorage;
import tsuteto.tofu.entity.EntityWhiteSmokeFX;
import tsuteto.tofu.gui.TcGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Tofu Force Storage block
 * 
 * @author Tsuteto
 *
 */
public class BlockTfStorage extends BlockContainer
{
    /**
     * Is the random generator used by furnace to drop the inventory contents in random directions.
     */
    private final Random machineRand = new Random();

    /** True if this is an active furnace, false if idle */
    private final boolean isActive;
    
    private Icon iconFront;

    /**
     * This flag is used to prevent the furnace inventory to be dropped upon block removal, is used internally when the
     * furnace block changes from idle to active and vice-versa.
     */
    private static boolean keepMachineInventory = false;

    protected BlockTfStorage(int par1, boolean par2)
    {
        super(par1, Material.iron);
        this.isActive = par2;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return TcBlock.tfStorageIdle.blockID;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
            byte var9 = 1;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
            {
                var9 = 1;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 0;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 3;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 2;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9, 2);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Icon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
        if (side == 1) // top
        {
            return this.blockIcon;
        }
        else if (side == 0) // bottom
        {
            return this.blockIcon;
        }
        else
        {
        	int d = (iblockaccess.getBlockMetadata(x, y, z) & 3) + 2;
            return side != d ? this.blockIcon // side & back
                             : this.iconFront; // front
        }
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    @Override
    public Icon getIcon(int par1, int par2)
    {
        if (par1 == 1) // top
        {
            return this.blockIcon;
        }
        else if (par1 == 0) // bottom
        {
            return this.blockIcon;
        }
        else
        {
            return par1 != 3 ? this.blockIcon // side & back
                             : this.iconFront; // front
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("tofucraft:tfMachine_side");
        this.iconFront = par1IconRegister.registerIcon(this.isActive ? "tofucraft:tfStorage_front_lit" : "tofucraft:tfStorage_front");
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (this.isActive)
        {
            int metadata = par1World.getBlockMetadata(par2, par3, par4);
            //int direction = metadata & 3;
            float var7 = par2 + 0.5F;
            float var8 = par3 + 0.0F + par5Random.nextFloat() * 6.0F / 16.0F;
            float var9 = par4 + 0.5F;
            float var10 = 0.52F;
            float var11 = par5Random.nextFloat() * 0.6F - 0.3F;
            EntityWhiteSmokeFX fx;
            
            fx = new EntityWhiteSmokeFX(par1World, (var7 - var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            fx = new EntityWhiteSmokeFX(par1World, (var7 + var10), var8, (var9 + var11), 0.0D, 0.0D, 0.0D);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            fx = new EntityWhiteSmokeFX(par1World, (var7 + var11), var8, (var9 - var10), 0.0D, 0.0D, 0.0D);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
            fx = new EntityWhiteSmokeFX(par1World, (var7 + var11), var8, (var9 + var10), 0.0D, 0.0D, 0.0D);
            Minecraft.getMinecraft().effectRenderer.addEffect(fx);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        TileEntity tile = par1World.getBlockTileEntity(par2, par3, par4);
        
        ItemStack itemEquipped = par5EntityPlayer.getCurrentEquippedItem();

        if (tile != null && (itemEquipped == null || !(itemEquipped.getItem() instanceof IFluidContainerItem)))
        {
            par5EntityPlayer.openGui(TofuCraftCore.instance, TcGuiHandler.GUIID_TF_STORAGE, par1World, par2, par3, par4);
        }

        return true;
    }

    /**
     * Update which block ID the furnace is using depending on whether or not it is burning
     */
    public static void updateMachineState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
        keepMachineInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, TcBlock.tfStorageActive.blockID);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, TcBlock.tfStorageIdle.blockID);
        }

        keepMachineInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var5, 2);

        if (var6 != null)
        {
            var6.validate();
            par1World.setBlockTileEntity(par2, par3, par4, var6);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityTfStorage();
    }

    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int var6 = MathHelper.floor_double((par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 2);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 2);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityTfStorage)par1World.getBlockTileEntity(par2, par3, par4)).setCustomName(par6ItemStack.getDisplayName());
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (!keepMachineInventory)
        {
            TileEntityTfStorage var7 = (TileEntityTfStorage)par1World.getBlockTileEntity(par2, par3, par4);

            if (var7 != null)
            {
                for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                {
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if (var9 != null)
                    {
                        float var10 = this.machineRand.nextFloat() * 0.8F + 0.1F;
                        float var11 = this.machineRand.nextFloat() * 0.8F + 0.1F;
                        float var12 = this.machineRand.nextFloat() * 0.8F + 0.1F;

                        while (var9.stackSize > 0)
                        {
                            int var13 = this.machineRand.nextInt(21) + 10;

                            if (var13 > var9.stackSize)
                            {
                                var13 = var9.stackSize;
                            }

                            var9.stackSize -= var13;
                            EntityItem var14 = new EntityItem(par1World, (par2 + var10), (par3 + var11), (par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                            if (var9.hasTagCompound())
                            {
                                var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                            }

                            float var15 = 0.05F;
                            var14.motionX = ((float)this.machineRand.nextGaussian() * var15);
                            var14.motionY = ((float)this.machineRand.nextGaussian() * var15 + 0.2F);
                            var14.motionZ = ((float)this.machineRand.nextGaussian() * var15);
                            par1World.spawnEntityInWorld(var14);
                        }
                    }
                }
            }
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
    
    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    @Override
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
    }
}
