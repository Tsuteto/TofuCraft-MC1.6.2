package tsuteto.tofu.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLeekDense extends ItemTcBlock
{
    private final Block blockRef;

    public ItemLeekDense(int par1)
    {
        super(par1);
        this.blockRef = Block.blocksList[this.getBlockID()];
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int par1)
    {
        return this.blockRef.getIcon(0, par1);
    }
}
