package tsuteto.tofu.item;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraftforge.common.EnumHelper;

public class TofuArmorMaterial
{
    public static EnumArmorMaterial KINU;
    public static EnumArmorMaterial MOMEN;
    public static EnumArmorMaterial SOLID;
    public static EnumArmorMaterial METAL;
    public static EnumArmorMaterial DIAMOND;
    
    static
    {
        KINU    = EnumHelper.addArmorMaterial("TOFU_KINU",     0, new int[]{1, 1, 1, 1},  1);
        MOMEN   = EnumHelper.addArmorMaterial("TOFU_MOMEN",    1, new int[]{1, 2, 1, 1},  3);
        SOLID   = EnumHelper.addArmorMaterial("TOFU_SOLID",    8, new int[]{2, 4, 3, 2},  8);
        METAL   = EnumHelper.addArmorMaterial("TOFU_METAL",   13, new int[]{2, 7, 6, 2},  9); // IRON={2, 6, 5, 2}
        DIAMOND = EnumHelper.addArmorMaterial("TOFU_DIAMOND", 33, new int[]{3, 8, 6, 3}, 20); // DIAMOND={3, 8, 6, 3}
    }
}
