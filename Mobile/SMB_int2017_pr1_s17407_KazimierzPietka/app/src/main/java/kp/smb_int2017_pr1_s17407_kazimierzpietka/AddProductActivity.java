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
    private static final String URL = "https://task-backend-sinatra.herokuapp.com/tasks";
    private Task task = null;
    Intent intent = null;

    EditText name, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        intent = getIntent();
        Integer taskId      = intent.getIntExtra("taskId", 0);
        String taskName     = intent.getStringExtra("taskName");
        String taskPrice    = intent.getStringExtra("taskPrice");
        String taskQunatity = intent.getStringExtra("taskQuantity");

        name     = (EditText) findViewById(R.id.input_name);
        price    = (EditText) findViewById(R.id.input_price);
        quantity = (EditText) findViewById(R.id.quantity_input);

        if (taskId == 0) {}
        else {
            name.setText(taskName);
            price.setText(taskPrice);
            quantity.setText(taskQunatity);
        }
    }

    public void addProductToDb(View view) {
        name = (EditText) findViewById(R.id.input_name);
        price = (EditText) findViewById(R.id.input_price);
        quantity = (EditText) findViewById(R.id.quantity_input);

        Integer taskId = intent.getIntExtra("taskId", 0);

        if (taskId == 0) {
            StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
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
        else {
            String putUrl = URL + "/" + taskId;
            StringRequest putRequest = new StringRequest(Request.Method.PUT, putUrl,
                new Response.Listener<String>() {
                   @Override
                    public void onResponse(String response) {
                       Log.d("Response", response);
                       Toast.makeText(getBaseContext(), "Aktualizacja produktou...", Toast.LENGTH_SHORT).show();
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
            requestQueue.add(putRequest);
        }
    }
}
