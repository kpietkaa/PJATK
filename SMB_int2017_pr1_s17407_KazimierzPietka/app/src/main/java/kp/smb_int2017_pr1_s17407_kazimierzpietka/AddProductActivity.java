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

import org.json.JSONException;
import org.json.JSONObject;

public class AddProductActivity extends AppCompatActivity {
    private static final String URL = "https://task-backend-sinatra.herokuapp.com/tasks";
    private Task task = null;

    EditText name, price, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        loadTask();

        name =      (EditText) findViewById(R.id.input_name);
        price =     (EditText) findViewById(R.id.input_price);
        quantity =  (EditText) findViewById(R.id.quantity_input);
    }

    protected void onPostExecute(String result) {
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Integer taskId = intent.getIntExtra("task", 0);

        if (taskId == 0) {}
        else {
            name.setText(task.getName());
            price.setText(task.getPrice().toString());
            quantity.setText(task.getQuantity().toString());
        }
    }

    private void loadTask(){
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Integer taskId = intent.getIntExtra("task", 0);
        String getUrl = URL + "/" + taskId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject taskObject = new JSONObject(response);
                    task = new Task(taskObject.getInt("id"),
                            taskObject.getString("name"),
                            taskObject.getInt("price"),
                            taskObject.getInt("quantity"),
                            0);
                    Log.d("Response", response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void addProductToDb(View view) {
        name =      (EditText) findViewById(R.id.input_name);
        price =     (EditText) findViewById(R.id.input_price);
        quantity =  (EditText) findViewById(R.id.quantity_input);

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
}
