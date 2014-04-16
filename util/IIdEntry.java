package tsuteto.tofu.util;

import net.minecraftforge.common.Configuration;

public interface IIdEntry<T>
{
    void loadId(Configuration conf);
    T getId();
    String getPropName(int i);
}
