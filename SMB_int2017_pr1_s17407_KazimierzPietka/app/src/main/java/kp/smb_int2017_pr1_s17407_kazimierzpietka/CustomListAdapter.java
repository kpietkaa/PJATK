package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<Task> tasksList;
    private SQLiteDatabaseHandler db;
    Button edit;
    Button destroy = null;
    Button done = null;

    public CustomListAdapter(Activity context, List<Task> tasks){

        super(context,R.layout.listview_row , tasks);

        this.context=context;
        this.tasksList = tasks;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null,true);
        db = new SQLiteDatabaseHandler(context);

        TextView nameTextField =     (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView priceTextField =    (TextView) rowView.findViewById(R.id.priceTextViewID);
        TextView quantityTextField = (TextView) rowView.findViewById(R.id.quantityTextViewID);
        destroy = (Button) rowView.findViewById(R.id.destroyButton);
        done                       = (Button) rowView.findViewById(R.id.doneButton);

        nameTextField.setText("Nazwa: " + tasksList.get(position).getName());
        priceTextField.setText("Cena: " + tasksList.get(position).getPrice());
        quantityTextField.setText("Ilość: " + tasksList.get(position).getQuantity());

        rowView.findViewById(R.id.destroyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Usuwanie: " + tasksList.get(position).getName(), Toast.LENGTH_SHORT).show();
                db.deleteOne(tasksList.get(position));
                Intent intent = new Intent(context, ShowTasksActivity.class);
                context.startActivity(intent);
            }
        });

        rowView.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = tasksList.get(position);
                task.setDone();
                db.updateTask(task);
                Intent intent = new Intent(context, ShowTasksActivity.class);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
