package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AddProductActivity extends AppCompatActivity {
    private SQLiteDatabaseHandler db;
    private static final String JSON_URL = "https://task-backend-sinatra.herokuapp.com/tasks";

    EditText name, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Integer taskId = intent.getIntExtra("task", 0);
        if (taskId == 0) {}
        else {
            Task task = db.getTask(taskId);

            name =      (EditText) findViewById(R.id.input_name);
            price =     (EditText) findViewById(R.id.input_price);
            quantity =  (EditText) findViewById(R.id.quantity_input);

            name.setText(task.getName());
            price.setText(task.getPrice());
            quantity.setText(task.getQuantity());

        }
    }

    public void addProductToDb(View view) {
        name =      (EditText) findViewById(R.id.input_name);
        price =     (EditText) findViewById(R.id.input_price);
        quantity =  (EditText) findViewById(R.id.quantity_input);

        StringRequest postRequest = new StringRequest(Request.Method.POST, JSON_URL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("Response", response);
                    Toast.makeText(getBaseContext(), "Dodawanie produktu...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), ShowTasksActivity.class);
                    startActivity(intent);
                }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error.Response", error.toString());
                }
            }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name.getText().toString());
                params.put("price", price.getText().toString());
                params.put("quantity", quantity.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }
}
