package tsuteto.tofu.util;

import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;

/**
 * Logger
 *
 * @author Tsuteto
 *
 */
public class ModLog
{
    public static String modId;
    public static boolean isDebug;

    public static void log(Level level, Throwable e, String format, Object... data)
    {
        FMLLog.log(modId, level, e, format, data);
    }

    public static void log(Level level, String format, Object... data)
    {
        FMLLog.log(modId, level, format, data);
    }

    public static void info(String format, Object... data)
    {
        FMLLog.log(modId, Level.INFO, format, data);
    }

    public static void debug(Object format, Object... data)
    {
        if (isDebug)
        {
            System.out.printf("[" + modId + "] " + format + "%n", data);
        }
    }
}
