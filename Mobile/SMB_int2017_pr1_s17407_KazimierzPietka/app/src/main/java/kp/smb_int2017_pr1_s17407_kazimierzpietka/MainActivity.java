package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class MainActivity extends AppCompatActivity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createProduct(View view) {
        Toast.makeText(getBaseContext(), "createProduct", Toast.LENGTH_SHORT).show();
        /*
        The Intent constructor takes two parameters:
        A Context as its first parameter (this is used because the Activity class is a subclass of Context)
        The Class of the app component to which the system should deliver the Intent (in this case,
        the activity that should be started).
         */
        Intent intent = new Intent(this, AddProductActivity.class);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        String message = "createProduct";
        /*
        putExtra() method adds the EditText's value to the intent
        An Intent can carry data types as key-value pairs called extras. Your key is
        a public constant EXTRA_MESSAGE because the next activity uses the key to retrieve the text value.
         */
        // intent.putExtra(EXTRA_MESSAGE, message);
        // startActivity() method starts an instance of the DisplayMessageActivity specified by the Intent
        startActivity(intent);
    }

    public void showTasks(View view) {
        Intent intent = new Intent(this, ShowTasksActivity.class);
        startActivity(intent);
    }

    public void settings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
