package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ShowTasksActivity extends AppCompatActivity {
    ListView listView;
    private SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tasks);

        db = new SQLiteDatabaseHandler(this);
        List<Task> tasks;
        tasks = db.allTasks();

        CustomListAdapter tasksList = new CustomListAdapter(this, tasks);
        listView = (ListView) findViewById(R.id.listviewID);
        listView.setAdapter(tasksList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int position, long id) {
                Toast.makeText(ShowTasksActivity.this,
                        "List Item Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }
}
