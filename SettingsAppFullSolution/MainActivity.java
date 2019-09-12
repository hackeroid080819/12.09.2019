package com.example.itaykan.setteingsapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        displayShared();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action",
                        Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getBaseContext(),
                                        "clicked snack bar",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void displayShared()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = prefs.getString("username", "Default NickName");
        boolean updates = prefs.getBoolean("applicationUpdates", false);
        String st = prefs.getString("downloadDetials", "1");
        int downloadDetialsNumber = 1;
        try {
            //int downloadDetialsNumber = prefs.getInt("downloadDetials", 1);
            downloadDetialsNumber = Integer.parseInt(st);
        }
        catch (Exception ex)
        {
            Toast.makeText(getBaseContext(), "Cannot convert " + st + " to int", Toast.LENGTH_SHORT).show();
        }


        StringBuilder builder = new StringBuilder();
        builder.append("Username: " + username + "\n");
        builder.append("Updates: " + updates+ "\n");
        builder.append("Download details: " + getResources().
                getStringArray(R.array.listArray)[downloadDetialsNumber - 1]);
        TextView sttv = findViewById(R.id.settingsTV);
        sttv.setText(builder.toString());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Toast.makeText(getBaseContext(), "clicked settings",
            //   Toast.LENGTH_SHORT).show();

            Intent gotoSettings = new Intent(this, MyPreferencesActivity.class);
            startActivity(gotoSettings);

            return true;
        }
        if (id == R.id.action_settings_display) {

          displayShared();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
