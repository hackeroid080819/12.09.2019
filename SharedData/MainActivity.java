package com.example.itaykan.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET, passwordET;

    public static final String USER_DETAILS = "profile";
    public static final String USER_DETAILS_NAME = "USER_DETAILS_NAME";
    public static final String USER_DETAILS_PWD = "USER_DETAILS_PWD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameET);
        passwordET = findViewById(R.id.passwordET);

        findViewById(R.id.saveBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Editor - to update the data in the shared pref.
                SharedPreferences.Editor editor = getSharedPreferences(USER_DETAILS,
                       MODE_PRIVATE).edit();
                String name = nameET.getText().toString();
                String pwd = passwordET.getText().toString();
                editor.putString(USER_DETAILS_NAME, name);
                editor.putString(USER_DETAILS_PWD, pwd);
                editor.apply();
                Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.loadBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences prefs = getSharedPreferences(USER_DETAILS,
                       MODE_PRIVATE);
                String name = prefs.getString(USER_DETAILS_NAME, "");
                String pwd = prefs.getString(USER_DETAILS_PWD, "");
                if (name.equals("") && pwd.equals(""))
                {
                    Toast.makeText(getBaseContext(), "Nothing to load...", Toast.LENGTH_SHORT).show();
                    return;
                }
                nameET.setText(name);
                passwordET.setText(pwd);
                Toast.makeText(getBaseContext(), "Loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
