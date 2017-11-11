package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

public class SettingsActivity extends AppCompatActivity {
    EditText name, email;
    public static final String MY_PREFS_NAME = "Settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EditText NameEditText = (EditText) findViewById(R.id.NameEditText);
        EditText EmailEditText = (EditText) findViewById(R.id.EmailEditText);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("name", null);
        if (name == null) {
            name =  "Imie nie zdefiniowane";
        }else {
            NameEditText.setText(name);
        }
        String email = prefs.getString("email", null);
        if (email == null) {
            email = "Email nie zdefiniowany";
        } else {
            EmailEditText.setText(email);
        }
    }

    public void saveSettings(View view) {
        name =  (EditText) findViewById(R.id.NameEditText);
        email = (EditText) findViewById(R.id.EmailEditText);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("name", name.getText().toString());
        editor.putString("email", email.getText().toString());
        editor.apply();

        Toast.makeText(getBaseContext(), R.string.add_task_success, Toast.LENGTH_SHORT).show();
    }
}
