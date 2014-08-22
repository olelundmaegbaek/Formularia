package dk.maegbaek.server.formularia;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private final static String PREFERENCES_NAME = "FormulariaPreferences";
    private final static String WEBSITE_KEY = "website";
    private final static String FULLSCREEN_KEY = "fullscreen";
    private final static String ONBOOT_KEY = "onboot";
    private final static String DEFAULT_WEBSITE_URL = "http://waitingroom.heroku.com/tablet";

    /**
     * Sets and persists default settings it they have not previously been set
     * @param context
     */
    public static void setDefaultSettings(Context context){
        SharedPreferences preferences = getPreferences(context);
        if (preferences.getString(WEBSITE_KEY, null) == null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(WEBSITE_KEY, DEFAULT_WEBSITE_URL);
            editor.commit();
        }
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static String getWebsite(Context context){
        return getPreferences(context).getString(WEBSITE_KEY, null);
    }

    public static void setWebsite(Context context, String websiteUrl) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(WEBSITE_KEY, websiteUrl);
        editor.commit();
    }

    public static boolean isFullscreen(Context context) {
        return getPreferences(context).getBoolean(FULLSCREEN_KEY, true);
    }

    public static void setFullscreen(Context context, boolean fullscreen) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(FULLSCREEN_KEY, fullscreen);
        editor.commit();
    }

    public static boolean isOnBoot(Context context) {
        return getPreferences(context).getBoolean(ONBOOT_KEY, true);
    }

    public static void setOnboot(Context context, boolean onboot) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(ONBOOT_KEY, onboot);
        editor.commit();
    }
}
