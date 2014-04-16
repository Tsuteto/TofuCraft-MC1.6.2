package tsuteto.tofu.fluids;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import tsuteto.tofu.block.TcBlock;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;

public class TcFluids
{
    public static final Fluid SOYMILK = new Fluid("Soy Milk");
    public static final Fluid SOYMILK_HELL = new Fluid("Hell Soy Milk");
    public static final Fluid SOY_SAUCE = new Fluid("Soy Sauce");

    static
    {
        FluidRegistry.registerFluid(SOYMILK);
        FluidRegistry.registerFluid(SOYMILK_HELL);
        FluidRegistry.registerFluid(SOY_SAUCE);
    }
    
    public static void register(Side side)
    {
        if (side == Side.CLIENT)
        {
            MinecraftForge.EVENT_BUS.register(new TcFluids());
        }
        
        // Soymilk
        SOYMILK.setBlockID(TcBlock.soymilkStill.blockID)
                .setUnlocalizedName(TcBlock.soymilkStill.getUnlocalizedName());
        FluidContainerRegistry.registerFluidContainer(
                new FluidStack(SOYMILK, FluidContainerRegistry.BUCKET_VOLUME),
                new ItemStack(TcItem.bucketSoymilk), new ItemStack(Item.bucketEmpty));

        // Hell Soymilk
        SOYMILK_HELL.setBlockID(TcBlock.soymilkHellStill.blockID)
                .setUnlocalizedName(TcBlock.soymilkHellStill.getUnlocalizedName());
        FluidContainerRegistry.registerFluidContainer(
                new FluidStack(SOYMILK_HELL, FluidContainerRegistry.BUCKET_VOLUME),
                new ItemStack(TcItem.bucketSoymilkHell), new ItemStack(Item.bucketEmpty));

        // Soy Sauce
        SOY_SAUCE.setBlockID(TcBlock.soySauceStill.blockID)
                .setUnlocalizedName(TcBlock.soySauceStill.getUnlocalizedName());
        FluidContainerRegistry.registerFluidContainer(
                new FluidStack(SOY_SAUCE, FluidContainerRegistry.BUCKET_VOLUME),
                new ItemStack(TcItem.bucketSoySauce), new ItemStack(Item.bucketEmpty));
    }

    @ForgeSubscribe
    public void onTextureStitch(TextureStitchEvent.Post event)
    {
        SOYMILK.setIcons(TcBlock.soymilkStill.getIcon(0, 0), TcBlock.soymilkStill.getIcon(2, 0));
        SOYMILK_HELL.setIcons(TcBlock.soymilkHellStill.getIcon(0, 0), TcBlock.soymilkHellStill.getIcon(2, 0));
        SOY_SAUCE.setIcons(TcBlock.soySauceStill.getIcon(0, 0), TcBlock.soySauceStill.getIcon(2, 0));
    }
}
