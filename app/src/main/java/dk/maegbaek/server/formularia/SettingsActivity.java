package dk.maegbaek.server.formularia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SettingsActivity extends Activity {
    private final static String URL_PATTERN = "^(https?:\\/\\/)?([\\da-zA-Z\\.-]+)\\.([a-zA-Z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final EditText websiteField = (EditText) findViewById(R.id.website);
        websiteField.setText(Settings.getWebsite(this));

        CheckBox fullScreenCheckBox = (CheckBox) findViewById(R.id.fullscreen);
        fullScreenCheckBox.setChecked(Settings.isFullscreen(this));

        createButton(websiteField, fullScreenCheckBox);
    }

    private void createButton(final EditText websiteField, final CheckBox fullScreenCheckBox) {
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String websiteUrl = websiteField.getText().toString();
                boolean fullscreen = fullScreenCheckBox.isChecked();
                if (isValid(websiteUrl)) {
                    websiteUrl = ensureAbsolutePath(websiteUrl);
                    Settings.setWebsite(SettingsActivity.this, websiteUrl);
                    Settings.setFullscreen(SettingsActivity.this, fullscreen);
                    displayWebsite();
                }else{
                    websiteField.requestFocus();
                    websiteField.setError(getString(R.string.error_invalid_website_url));
                }
            }

            private void displayWebsite() {
                Intent intent = new Intent(SettingsActivity.this, WebFormularia.class);
                startActivity(intent);
                //SettingsActivity.this.finish();
            }
        });
    }

    private String ensureAbsolutePath(String websiteUrl) {
        if (!websiteUrl.startsWith("http")){
            websiteUrl = "http://" + websiteUrl;
        }
        return websiteUrl;
    }

    private boolean isValid(String websiteUrl) {
        return websiteUrl.matches(URL_PATTERN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            SettingsActivity.this.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            getParent().finish();
            //return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
