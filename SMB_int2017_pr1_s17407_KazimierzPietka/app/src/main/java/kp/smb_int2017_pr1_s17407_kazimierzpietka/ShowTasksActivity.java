package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShowTasksActivity extends AppCompatActivity {
//    ListView listView;
//    private SQLiteDatabaseHandler db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_tasks);
//
//        db = new SQLiteDatabaseHandler(this);
//        List<Task> tasks;
//        tasks = db.allTasks();
//
//        final CustomListAdapter tasksList = new CustomListAdapter(this, tasks);
//        listView = (ListView) findViewById(R.id.listviewID);
//        listView.setAdapter(tasksList);
//    }
    private static final String JSON_URL = "https://task-backend-sinatra.herokuapp.com/tasks";
    ListView listView;
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks);

//        listView = (ListView) findViewById(R.id.listviewID);
        tasks = new ArrayList<>();

        loadTasks();

        final CustomListAdapter tasksList = new CustomListAdapter(this, tasks);
        listView = (ListView) findViewById(R.id.listviewID);
        listView.setAdapter(tasksList);
    }

    private void loadTasks(){
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject taskObject = array.getJSONObject(i);
                        Task task = new Task(taskObject.getInt("id"),
                                             taskObject.getString("name"),
                                             taskObject.getInt("price"),
                                             taskObject.getInt("quantity"),
                                             0);
                        System.out.print(task);
                        tasks.add(task);
                        System.out.print(tasks);
                    }

//                    CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), tasks);
//                    listView.setAdapter(adapter);

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
}
