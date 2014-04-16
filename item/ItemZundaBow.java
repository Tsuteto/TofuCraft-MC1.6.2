package tsuteto.tofu.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.entity.EntityZundaArrow;
import tsuteto.tofu.params.DataType;
import tsuteto.tofu.params.EntityInfo;
import tsuteto.tofu.util.CustomPacketDispatcher;
import tsuteto.tofu.util.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemZundaBow extends ItemBow
{
    public enum EnumArrowType
    {
        NORMAL(Item.arrow.itemID), ZUNDA(TcItem.zundaArrow.itemID);

        public final int itemID;
        public Icon[] icons;

        EnumArrowType(int itemID)
        {
            this.itemID = itemID;

        }
    }

    public static final String[] iconNameSuffix = new String[] {"pull_0", "pull_1", "pull_2"};

    public ItemZundaBow(int par1)
    {
        super(par1);
        this.setMaxDamage(1345);
    }

    @Override
    public Icon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if (usingItem != null)
        {
            EnumArrowType arrowType = EntityInfo.instance().get(player.entityId, DataType.ZundaArrowType);
            if (arrowType == null) arrowType = EnumArrowType.NORMAL;
            
                int k = usingItem.getMaxItemUseDuration() - useRemaining;
                if (k >= 18) return arrowType.icons[2];
                if (k >  13) return arrowType.icons[1];
                if (k >   0) return arrowType.icons[0];
        }
        return this.getIconIndex(stack);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;

        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        EnumArrowType arrowType = EntityInfo.instance().get(par3EntityPlayer.entityId, DataType.ZundaArrowType);

        if (arrowType != null)
        {
            float var7 = var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if (var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            Entity var8;

            if (arrowType == EnumArrowType.NORMAL)
            {
                var8 = this.getNormalArrow(par1ItemStack, par2World, par3EntityPlayer, var7);

                if (var5)
                {
                    ((EntityArrow)var8).canBePickedUp = 2;
                }
            }
            else
            {
                var8 = this.getZundaArrow(par1ItemStack, par2World, par3EntityPlayer, var7);

                if (var5)
                {
                    ((EntityZundaArrow)var8).canBePickedUp = 2;
                }
            }

            if (!var5)
            {
                par3EntityPlayer.inventory.consumeInventoryItem(arrowType.itemID);
            }
            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            if (!par2World.isRemote)
            {
                par2World.spawnEntityInWorld(var8);
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        EnumArrowType arrowType = this.getArrowType(par1ItemStack, par3EntityPlayer);
        if (arrowType != null)
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            
            CustomPacketDispatcher.create(TofuCraftCore.netChannelZundaArrowType)
                    .addInt(par3EntityPlayer.entityId)
                    .addByte((byte)arrowType.ordinal())
                    .sendPacketToAllPlayers();
            EntityInfo.instance().set(par3EntityPlayer.entityId, DataType.ZundaArrowType, arrowType);
        }

        return par1ItemStack;
    }

    public EntityArrow getNormalArrow(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, float var7)
    {
        EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, var7 * 2.0F);

        if (var7 == 1.0F)
        {
            var8.setIsCritical(true);
        }

        int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

        if (var9 > 0)
        {
            var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);
        }

        int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

        if (var10 > 0)
        {
            var8.setKnockbackStrength(var10);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
        {
            var8.setFire(100);
        }

        return var8;
    }

    public EntityZundaArrow getZundaArrow(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, float var7)
    {
        EntityZundaArrow var8 = new EntityZundaArrow(par2World, par3EntityPlayer, var7 * 2.0F);

        if (var7 == 1.0F)
        {
            var8.setIsCritical(true);
        }

        int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

        if (var9 > 0)
        {
            var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);
        }

        int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

        if (var10 > 0)
        {
            var8.setKnockbackStrength(var10);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
        {
            var8.setFire(100);
        }

        return var8;
    }

    private EnumArrowType getArrowType(ItemStack par1ItemStack, EntityPlayer player)
    {
        boolean var5 = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        EnumArrowType arrowType;
        if (var5 || player.inventory.hasItem(EnumArrowType.ZUNDA.itemID))
        {
            return EnumArrowType.ZUNDA;
        }
        else if (player.inventory.hasItem(EnumArrowType.NORMAL.itemID))
        {
            return EnumArrowType.NORMAL;
        }
        else
        {
            return null;
        }
    }

    @Override
    public int getItemEnchantability()
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("tofucraft:zundaBow");

        for (EnumArrowType type : EnumArrowType.values())
        {
        	type.icons = new Icon[iconNameSuffix.length];

	        for (int i = 0; i < type.icons.length; ++i)
	        {
	        	type.icons[i] = par1IconRegister.registerIcon(String.format("tofucraft:zundaBow_%s_%s", type.toString().toLowerCase(), iconNameSuffix[i]));
	        }
        }
    }

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}
}
