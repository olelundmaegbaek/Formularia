package dk.maegbaek.server.formularia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebFormularia extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_formularia);
        Settings.setDefaultSettings(this);
        setmWebView();
    }

    private void setmWebView() {
        mWebView = (WebView) findViewById(R.id.activity_main_webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(Settings.getWebsite(this));
        setFullscreenMode(mWebView);
    }

    private void setFullscreenMode(WebView mWebView) {
        if (Settings.isFullscreen(this))
        //TODO:Bundbar stadig tilstede.
        {
            mWebView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | WebView.SYSTEM_UI_FLAG_IMMERSIVE
                    | WebView.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }else{
            mWebView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
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
            //WebFormularia.this.finish();
        } else {
            throw new IllegalArgumentException("Ukendt menu punkt" + item.getItemId());
        }
        return true;
    }
}

