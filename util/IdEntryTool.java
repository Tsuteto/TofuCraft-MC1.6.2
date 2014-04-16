package tsuteto.tofu.util;

import net.minecraftforge.common.Configuration;
import tsuteto.tofu.Settings;
import tsuteto.tofu.item.TcItem;

public class IdEntryTool implements IIdEntry<Integer[]>
{
    private Integer[] id;
    private String[] propName;

    public IdEntryTool(String key, int startDefaultId)
    {
        id = new Integer[3];
        propName = new String[3];
        for (int i = 0; i < 3; i++)
        {
            this.id[i] = startDefaultId + i;
            this.propName[i] = key + Utils.capitalize(TcItem.toolNameList[i]);
        }
    }
    @Override
    public void loadId(Configuration conf)
    {
        for (int i = 0; i < 3; i++)
        {
            if (Settings.autoAssign)
            {
                this.id[i] = conf.getItem("item", this.propName[i], this.id[i]).getInt();
            }
            else
            {
                this.id[i] = conf.get("item", this.propName[i], this.id[i]).getInt() + Settings.itemShiftIndex;
            }
        }
    }
    @Override
    public Integer[] getId()
    {
        return this.id;
    }
    @Override
    public String getPropName(int i)
    {
        return this.propName[i];
    }
}
