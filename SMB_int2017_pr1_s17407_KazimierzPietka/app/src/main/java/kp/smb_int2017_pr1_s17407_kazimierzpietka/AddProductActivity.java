package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import java.util.Random;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {
    private SQLiteDatabaseHandler db;
    EditText name, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        // String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
    }

    public void addProductToDb(View view) {
        // create our sqlite helper class
        db = new SQLiteDatabaseHandler(this);

        name =      (EditText) findViewById(R.id.input_name);
        price =     (EditText) findViewById(R.id.input_price);
        quantity =  (EditText) findViewById(R.id.quantity_input);

        Random r = new Random();
        int id = r.nextInt((int)Math.pow(10,9));

        Task task = new Task();
        task.setId(id);
        task.setName(name.getText().toString());
        task.setPrice(Integer.parseInt(price.getText().toString()));
        task.setQuantity(Integer.parseInt(quantity.getText().toString()));

        db.addTask(task);

        Toast.makeText(getBaseContext(), R.string.add_task_success, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ShowTasksActivity.class);
        startActivity(intent);
    }
}
