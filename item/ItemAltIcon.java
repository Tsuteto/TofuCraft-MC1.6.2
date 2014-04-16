package tsuteto.tofu.item;

import net.minecraft.client.renderer.texture.IconRegister;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAltIcon extends TcItem {
	private String iconName;

	public ItemAltIcon(int par1) {
		super(par1);
	}

	public TcItem setIconName(String name)
	{
		this.iconName = name;
		return this;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
    	this.itemIcon = par1IconRegister.registerIcon(iconName);
    }
}
