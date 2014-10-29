package dk.maegbaek.server.formularia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.*;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class WebFormularia extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_formularia);
        if (checkDeviceIsOnline(this))
        {
            Settings.setDefaultSettings(getApplicationContext());
            setFullscreenMode();
            setmWebView();
        }
        else
        {
            Toast.makeText(WebFormularia.this,
                    "Du er ikke forbundet til internettet, prøv at genindlæse i øverste menu", Toast.LENGTH_LONG).show();
        }


        //Tjekker om mainactivity skal lukkes. Det er grimt, men virker.
        // 3/9 14 : UDKOMMENTERET PGA OVERGANG TIL LAUNCHER
        //TODO: Rense kode efter overgang til launcher.
        // if (getIntent().getBoeaolnExtra("EXIT", false)) {
        //    finish();
        //}
    }

    public static boolean checkDeviceIsOnline(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        } else
        {
            return false;
        }
    }

    public void setmWebView() {
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true); //enable javascript
        mWebView.getSettings().setBuiltInZoomControls(true);  //enable zoom
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setFitsSystemWindows(true);
        mWebView.loadUrl(Settings.getWebsite(getApplicationContext()));

    }

    private void setFullscreenMode() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            if (Settings.isFullscreen(getApplicationContext())) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.web_formularia, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();
        //if (id == R.id.action_settings) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.action_reload) {
            if (checkDeviceIsOnline(this))
            {
                mWebView.loadUrl("javascript:window.location.reload(true)" );
            }
            else
            {
                Toast.makeText(WebFormularia.this,
                        "Du er ikke forbundet til internettet, prøv at genindlæse i øverste menu", Toast.LENGTH_LONG).show();
            }

        }
        else {
            throw new IllegalArgumentException("Ukendt menu punkt" + item.getItemId());
        }
        return true;
    }
}

