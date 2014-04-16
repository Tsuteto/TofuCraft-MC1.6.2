package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemDrinkBottle extends ItemColoredBottle
{
    private boolean alwaysEdible;
    
    private ItemFood energy;

    private int potionId;
    private int potionDuration;
    private int potionAmplifier;
    private float potionEffectProbability;
    
    public ItemDrinkBottle(int par1)
    {
        super(par1);
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    public ItemDrinkBottle(int par1, int color, Item energy)
    {
        super(par1, color);
        this.energy = (ItemFood)energy;
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    @Override
    public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        --par1ItemStack.stackSize;

        par3EntityPlayer.getFoodStats().addStats(this.getEnergyFood(par1ItemStack.getItemDamage()));
        this.func_77849_c(par1ItemStack, par2World, par3EntityPlayer);
        
        if (par1ItemStack.stackSize <= 0)
        {
            return new ItemStack(Item.glassBottle);
        }

        par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle));

        return par1ItemStack;
    }
    
    protected void func_77849_c(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote && this.potionId > 0 && par2World.rand.nextFloat() < this.potionEffectProbability)
        {
            par3EntityPlayer.addPotionEffect(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
        }
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par3EntityPlayer.canEat(this.alwaysEdible))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }
    
    public ItemDrinkBottle setPotionEffect(int par1, int par2, int par3, float par4)
    {
        this.potionId = par1;
        this.potionDuration = par2;
        this.potionAmplifier = par3;
        this.potionEffectProbability = par4;
        return this;
    }

    public ItemDrinkBottle setAlwaysEdible()
    {
        this.alwaysEdible = true;
        return this;
    }
    
    public void setEnergyFood(Item food)
    {
        this.energy = (ItemFood)food;
    }
    
    protected ItemFood getEnergyFood(int damage)
    {
        return this.energy;
    }
}
