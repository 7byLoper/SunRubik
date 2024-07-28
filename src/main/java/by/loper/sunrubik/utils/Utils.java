package by.loper.sunrubik.utils;

import by.loper.sunrubik.SunRubik;

public class Utils {
    public static String cfgMessage(String patch) {
        String message = SunRubik.getInstance().getConfig().getString(patch);
        message = message.replace("&", "§");
        return message;
    }
    public static int random(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
