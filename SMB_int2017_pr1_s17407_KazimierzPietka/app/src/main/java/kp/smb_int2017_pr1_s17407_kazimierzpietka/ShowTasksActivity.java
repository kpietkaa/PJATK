package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
    }
}
