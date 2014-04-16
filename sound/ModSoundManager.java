package tsuteto.tofu.sound;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.ModLoader;
import paulscode.sound.SoundSystem;

/**
 * Manages sound for mod
 *
 * @author Tsuteto
 *
 */
public class ModSoundManager
{
    private static SoundSystem soundsystem = null;
    private static SoundManagerAccessor mcSndMgr = new SoundManagerAccessor();
    private static final String bgmIdentifiedName = "BgMusic";
    private static String nowPlaying = null;

    private ModSoundManager()
    {
    }

    public static void init()
    {
        soundsystem = mcSndMgr.sndSystem();
    }

    public static File getAssetsDir()
    {
        return mcSndMgr.getAssetsDir();
    }

    public static void registerWavFiles()
    {
        mcSndMgr.registerWavFiles();
    }

    public static void playBgm(String str, boolean toLoop)
    {
        play(str, mcSndMgr.soundPoolSounds(), toLoop);
    }

    public static void playSound(String str, double x, double y, double z, float volume, float pitch)
    {
        mcSndMgr.getSoundManager().playSound(str, (float) x, (float) y, (float) z, volume, pitch);
    }

    private static void play(String par1Str, SoundPool soundpool, boolean toLoop)
    {
        if (soundsystem == null)
            return;

        boolean loaded = mcSndMgr.loaded();

        if (soundpool == null)
            return;

        Minecraft mc = ModLoader.getMinecraftInstance();
        GameSettings options = mc.gameSettings;

        if (!loaded || options.musicVolume == 0.0F)
        {
            return;
        }

        if (playing())
            stop(nowPlaying);

        if (par1Str == null)
        {
            return;
        }

        SoundPoolEntry soundpoolentry = soundpool.getRandomSoundFromSoundPool(par1Str);

        if (soundpoolentry != null)
        {
            soundsystem.backgroundMusic(bgmIdentifiedName, soundpoolentry.getSoundUrl(), soundpoolentry.getSoundName(), toLoop);
            soundsystem.setVolume(bgmIdentifiedName, options.musicVolume);
            soundsystem.play(bgmIdentifiedName);

            nowPlaying = par1Str;
        }
    }

    public static void stop(String par1Str)
    {
        if (isReady() && (getNowPlaying() != null && getNowPlaying().equals(par1Str) || getNowPlaying() == null))
        {
            soundsystem.stop(bgmIdentifiedName);
            nowPlaying = null;
        }
    }

    public static void stopSpelunkerBgm()
    {
        if (isReady() && getNowPlaying() != null)
        {
            soundsystem.stop(bgmIdentifiedName);
            nowPlaying = null;
        }
    }

    public static boolean playing()
    {
        return isReady() && soundsystem.playing(bgmIdentifiedName);
    }

    public static boolean isReady()
    {
        return soundsystem != null;
    }

    public static String getNowPlaying()
    {
        return nowPlaying;
    }
}
