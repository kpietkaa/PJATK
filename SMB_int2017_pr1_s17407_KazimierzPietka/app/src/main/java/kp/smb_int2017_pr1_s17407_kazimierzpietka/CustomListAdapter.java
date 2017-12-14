package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomListAdapter extends ArrayAdapter<Task> {
    private static final String URL = "https://task-backend-sinatra.herokuapp.com/tasks";
    private final List<Task> taskList;
    private Context mCtx;
    Button edit = null;
    Button destroy = null;
    Button done = null;

    public CustomListAdapter(Context mCtx, List<Task> taskList) {
        super(mCtx, R.layout.listview_row, taskList);
        this.taskList = taskList;
        this.mCtx = mCtx;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        final View listViewItem = inflater.inflate(R.layout.listview_row, null, true);

        TextView name     = (TextView) listViewItem.findViewById(R.id.nameTextViewID);
        TextView price    = (TextView) listViewItem.findViewById(R.id.priceTextViewID);
        TextView quantity = (TextView) listViewItem.findViewById(R.id.quantityTextViewID);
        destroy           = (Button) listViewItem.findViewById(R.id.destroyButton);
        edit              = (Button) listViewItem.findViewById(R.id.editButton);
        done              = (Button) listViewItem.findViewById(R.id.doneButton);

        Task task = taskList.get(position);

        name.setText("Nazwa: " + task.getName());
        price.setText("Cena: " + task.getPrice());
        quantity.setText("Ilość: " + task.getQuantity());

        listViewItem.findViewById(R.id.destroyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "Usuwanie: " + taskList.get(position).getName(), Toast.LENGTH_SHORT).show();
                String deleteUrl = URL + "/" + taskList.get(position).getId();
                StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, deleteUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("Response", response);
                            Toast.makeText(mCtx, response, Toast.LENGTH_SHORT).show();
                            Toast.makeText(mCtx, "Odśwież widok :)", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", error.toString());
                        }
                    }
                );
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(deleteRequest);
            }
        });

        listViewItem.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Toast.makeText(mCtx, "Edytowanie: " + taskList.get(position).getName(), Toast.LENGTH_SHORT).show();
//                Task task = taskList.get(position);
//                Intent intent = new Intent(mCtx, AddProductActivity.class);
//                Integer id = Integer.parseInt(task.getId());
//                intent.putExtra("task", id);
//                context.startActivity(intent);
            }
        });

        listViewItem.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "Donowanie: " + taskList.get(position).getName(), Toast.LENGTH_SHORT).show();
                String putUrl = URL + "/" + taskList.get(position).getId();
                StringRequest putRequest = new StringRequest(Request.Method.PUT, putUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(mCtx, "Donowanie...", Toast.LENGTH_SHORT).show();
                            Toast.makeText(mCtx, "Odśwież widok :)", Toast.LENGTH_SHORT).show();
                            Log.d("Response", response);
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
                        params.put("price", "0");
                        params.put("quantity", "0");
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(putRequest);
            }
        });

        return listViewItem;
    }
}
