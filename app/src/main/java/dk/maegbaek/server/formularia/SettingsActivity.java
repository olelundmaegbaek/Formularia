package dk.maegbaek.server.formularia;

import android.app.Activity;
import android.content.Context;
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
        Context applicationContext = getApplicationContext();

        final EditText websiteField = (EditText) findViewById(R.id.website);
        websiteField.setText(Settings.getWebsite(applicationContext));

        CheckBox fullScreenCheckBox = (CheckBox) findViewById(R.id.fullscreen);
        fullScreenCheckBox.setChecked(Settings.isFullscreen(applicationContext));

        CheckBox onBootCheckBox = (CheckBox) findViewById(R.id.onboot);
        onBootCheckBox.setChecked(Settings.isOnBoot(applicationContext));

        createButton(websiteField, fullScreenCheckBox, onBootCheckBox);
    }

    private void createButton(final EditText websiteField, final CheckBox fullScreenCheckBox, final CheckBox onBootCheckBox) {
        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String websiteUrl = websiteField.getText().toString();
                boolean fullscreen = fullScreenCheckBox.isChecked();
                boolean onboot = onBootCheckBox.isChecked();

                if (isValid(websiteUrl)) {
                    Context applicationContext = getApplicationContext();
                    websiteUrl = ensureAbsolutePath(websiteUrl);
                    Settings.setWebsite(applicationContext, websiteUrl);
                    Settings.setFullscreen(applicationContext, fullscreen);
                    Settings.setOnboot(applicationContext, onboot);
                    displayWebsite();
                }else{
                    websiteField.requestFocus();
                    websiteField.setError(getString(R.string.error_invalid_website_url));
                }
            }

            private void displayWebsite() {
                Intent intent = new Intent(SettingsActivity.this, WebFormularia.class);
                startActivity(intent);
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
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), WebFormularia.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
