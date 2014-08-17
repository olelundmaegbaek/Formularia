package dk.maegbaek.server.formularia;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private final static String PREFERENCES_NAME = "FormulariaPreferences";
    private final static String WEBSITE_KEY = "website";
    private final static String FULLSCREEN_KEY = "fullscreen";
    private final static String DEFAULT_WEBSITE_URL = "http://waitingroom.heroku.com/tablet";

    /**
     * Sets and persists default settings it they have not previously been set
     * @param activity
     */
    public static void setDefaultSettings(Activity activity){
        SharedPreferences preferences = getPreferences(activity);
        if (preferences.getString(WEBSITE_KEY, null) == null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(WEBSITE_KEY, DEFAULT_WEBSITE_URL);
            editor.commit();
        }
    }

    private static SharedPreferences getPreferences(Activity activity) {
        return activity.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static String getWebsite(Activity activity){
        return getPreferences(activity).getString(WEBSITE_KEY, null);
    }

    public static void setWebsite(Activity activity, String websiteUrl) {
        SharedPreferences.Editor editor = getPreferences(activity).edit();
        editor.putString(WEBSITE_KEY, websiteUrl);
        editor.commit();
    }

    public static boolean isFullscreen(Activity activity) {
        return getPreferences(activity).getBoolean(FULLSCREEN_KEY, true);
    }

    public static void setFullscreen(SettingsActivity activity, boolean fullscreen) {
        SharedPreferences.Editor editor = getPreferences(activity).edit();
        editor.putBoolean(FULLSCREEN_KEY, fullscreen);
        editor.commit();
    }
}
