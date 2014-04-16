package tsuteto.tofu.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.item.TcItem;
import tsuteto.tofu.util.CustomPacketDispatcher;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;


public class EntityZundaArrow extends EntityArrowBase implements IEntityAdditionalSpawnData
{
    @SidedProxy(serverSide = "tsuteto.tofu.entity.EntityZundaArrow$CommonProxy", clientSide = "tsuteto.tofu.entity.EntityZundaArrow$ClientProxy")
    public static CommonProxy sidedProxy;

    public EntityZundaArrow(World par1World)
    {
        super(par1World);
    }

    public EntityZundaArrow(World par1World, double par2, double par3, double par4)
    {
        super(par1World, par2, par3, par4);
    }

    public EntityZundaArrow(World par1World, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving, float par4, float par5)
    {
        super(par1World, par2EntityLiving, par3EntityLiving, par4, par5);
    }

    public EntityZundaArrow(World par1World, EntityLivingBase par2EntityLiving, float par3)
    {
        super(par1World, par2EntityLiving, par3);
    }

    @Override
    protected void onHitEntity(MovingObjectPosition var4)
    {
        if (var4.entityHit instanceof EntitySlime)
        {
            EntitySlime slime = (EntitySlime)var4.entityHit;
            if (!worldObj.isRemote)
            {
                for (int i = 0; i < slime.getSlimeSize(); i++)
                {
                    slime.dropItem(TcItem.tofuZunda.itemID, 1);
                }
            }
            slime.setDead();

            if (this.shootingEntity instanceof EntityPlayer)
            {
                TcAchievementMgr.achieve((EntityPlayer)this.shootingEntity, TcAchievementMgr.Key.zundaAttack);
            }
        }
        else if (var4.entityHit instanceof EntityLivingBase)
        {
            EntityLivingBase living = (EntityLivingBase)var4.entityHit;
            living.addPotionEffect(new PotionEffect(Potion.regeneration.id, this.getIsCritical() ? 200 : 100, 1));
        }

        this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

        if (!(var4.entityHit instanceof EntityEnderman))
        {
            this.setDead();
        }

        CustomPacketDispatcher.create(TofuCraftCore.netChannelZundaArrowHit)
                .addDouble(var4.entityHit.posX)
                .addDouble(var4.entityHit.posY)
                .addDouble(var4.entityHit.posZ)
                .sendToAllInDimension(var4.entityHit.dimension);
    }


    @Override
    protected void emitCriticalEffect()
    {
        sidedProxy.emitCriticalEffect(this);
    }

    public static class CommonProxy
    {
        public void emitCriticalEffect(EntityZundaArrow arrow)
        {
        }
    }

    public static class ClientProxy extends CommonProxy
    {
        @Override
        public void emitCriticalEffect(EntityZundaArrow arrow)
        {
            if (arrow.worldObj.isRemote)
            {
                Minecraft mc = FMLClientHandler.instance().getClient();
                for (int var9 = 0; var9 < 4; ++var9)
                {
                    EntityFX fx = new EntityCritFX(arrow.worldObj, arrow.posX + arrow.motionX * var9 / 4.0D,
                            arrow.posY + arrow.motionY * var9 / 4.0D,
                            arrow.posZ + arrow.motionZ * var9 / 4.0D,
                            -arrow.motionX, -arrow.motionY + 0.2D, -arrow.motionZ);
                    fx.setRBGColorF(0.4f, 1.0f, 0.2f);
                    mc.effectRenderer.addEffect(fx);
                }
            }
        }

    }

    @Override
    public void writeSpawnData(ByteArrayDataOutput data)
    {
        if (this.shootingEntity != null)
        {
            data.writeInt(this.shootingEntity.entityId);
        }
        else
        {
            data.writeInt(-1);
        }
    }

    @Override
    public void readSpawnData(ByteArrayDataInput data)
    {
        int entityId = data.readInt();
        if (entityId != -1)
        {
            this.shootingEntity = this.worldObj.getEntityByID(entityId);
        }
    }

    @Override
    protected ItemStack getArrowItemStack()
    {
        return new ItemStack(TcItem.zundaArrow, 1);
    }
}
