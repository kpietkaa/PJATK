package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Task> {
    private List<Task> taskList;
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
//                Task task = taskList.get(position);
//                task.setDone();
//                db.updateTask(task);
//                Intent intent = new Intent(context, ShowTasksActivity.class);
//                context.startActivity(intent);
//                Toast.makeText(context, "Kupione: " + tasksList.get(position).getName() + " cena i ilość zmienione na 0", Toast.LENGTH_SHORT).show();
            }
        });

        return listViewItem;
    }
}
