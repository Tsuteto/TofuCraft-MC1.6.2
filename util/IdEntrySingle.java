package tsuteto.tofu.util;

import net.minecraftforge.common.Configuration;
import tsuteto.tofu.Settings;

public class IdEntrySingle implements IIdEntry<Integer>
{
    private int id;
    private String propName;
    private final String comment;

    public IdEntrySingle(String key, int defaultId)
    {
        this(key, defaultId, null);
    }

    public IdEntrySingle(String key, int defaultId, String comment)
    {
        this.propName = key;
        this.id = defaultId;
        this.comment = comment;
    }

    @Override
    public void loadId(Configuration conf)
    {
        if (Settings.autoAssign)
        {
            this.id = conf.getItem("item", propName, this.id, this.comment).getInt();
        }
        else
        {
            this.id = conf.get("item", propName, this.id, this.comment).getInt() + Settings.itemShiftIndex;
        }
    }

    @Override
    public Integer getId()
    {
        return this.id;
    }

    @Override
    public String getPropName(int i)
    {
        return propName;
    }
}