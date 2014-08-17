package tsuteto.tofu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Message
{
    public static void load()
    {
        $("itemGroup.TofuCraft", "en_US", "TofuCraft");
        $("itemGroup.TofuCraft", "ja_JP", "豆腐Craft");

        $("container.tofucraft.SaltFurnace", "en_US", "Salt Furnace");
        $("container.tofucraft.SaltFurnace", "ja_JP", "製塩用かまど");
        
        $("container.tofucraft.TfStorage", "en_US", "Tofu Force Storage");
        $("container.tofucraft.TfStorage", "ja_JP", "トーフフォース貯蔵装置");
        
        $("potion.glowing", "en_US", "Glowing");
        $("potion.glowing", "ja_JP", "蛍光");

        $("potion.glowing.postfix", "en_US", "Potion of Glowing");
        $("potion.glowing.postfix", "ja_JP", "蛍光のポーション");

        $("potion.filling", "en_US", "Filling");
        $("potion.filling", "ja_JP", "腹持ち");

        $("potion.filling.postfix", "en_US", "Potion of Filling");
        $("potion.filling.postfix", "ja_JP", "腹持ちのポーション");

        $("entity.TofuSlime.name", "en_US", "Tofu Slime");
        $("entity.TofuSlime.name", "ja_JP", "豆腐スライム");

        $("entity.TofuCreeper.name", "en_US", "Tofu Creeper");
        $("entity.TofuCreeper.name", "ja_JP", "豆腐クリーパー");

        $("entity.Tofunian.name", "en_US", "Tofunian");
        $("entity.Tofunian.name", "ja_JP", "トーフニアン");

        $("commands.tofuslimecheck.usage", "/tofuslimecheck");

        $("commands.tofuslimecheck.found", "en_US", "Tofu Slimes habit in this chunk");
        $("commands.tofuslimecheck.found", "ja_JP", "このチャンクには豆腐スライムが生息しています");
        $("commands.tofuslimecheck.notFound", "en_US", "Tofu Slimes cannot be seen in this chunk");
        $("commands.tofuslimecheck.notFound", "ja_JP", "このチャンクには豆腐スライムはいないようだ");
        
        $("tofucraft.update.server", "en_US", "TofuCraft updated to %1$s! Available at %2$s");
        $("tofucraft.update.server", "ja_JP", "豆腐Craft %1$sに更新しました! ダウンロード: %2$s");
        $("tofucraft.update.client", "en_US", "§eTofuCraft§r updated to §a%1$s§r! Available at %2$s");
        $("tofucraft.update.client", "ja_JP", "§e豆腐Craft§r §a%1$s§rに更新しました! ダウンロード: %2$s");

        loadLocalization("/assets/tofucraft/lang/zh_CN.lang", "zh_CN", false);
        
    }
    
    private static void $(String key, String value)
    {
        LanguageRegistry.instance().addStringLocalization(key, value);
    }
    
    private static void $(String key, String lang, String value)
    {
        LanguageRegistry.instance().addStringLocalization(key, lang, value);
    }
    
    public static void loadLocalization(String localizationFile, String lang, boolean isXML)
    {
        URL urlResource = LanguageRegistry.class.getResource(localizationFile);
        try
        {
            Map<String, String> langPack = parseLangFile(urlResource.openStream());
        
            for (Entry<String, String> entry : langPack.entrySet())
            {
                LanguageRegistry.instance().addStringLocalization(entry.getKey(), lang, entry.getValue());
            }
        }
        catch (IOException e)
        {
            FMLLog.log(Level.SEVERE, e, "Unable to load localization from file %s", localizationFile);
        }
    }
    
    private static final Splitter equalSignSplitter = Splitter.on('=').limit(2);
    private static final Pattern numericVariablePattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
    
    public static HashMap<String,String> parseLangFile(InputStream inputStreamReader)
    {
        HashMap<String,String> table = Maps.newHashMap();
        try
        {
            Iterator iterator = IOUtils.readLines(inputStreamReader, Charsets.UTF_8).iterator();

            while (iterator.hasNext())
            {
                String s = (String)iterator.next();

                if (!s.isEmpty() && s.charAt(0) != 35)
                {
                    String[] astring = Iterables.toArray(equalSignSplitter.split(s), String.class);

                    if (astring != null && astring.length == 2)
                    {
                        String s1 = astring[0];
                        String s2 = numericVariablePattern.matcher(astring[1]).replaceAll("%$1s");
                        table.put(s1, s2);
                    }
                }
            }

        }
        catch (Exception ioexception)
        {
            ;
        }
        return table;
    }
}
