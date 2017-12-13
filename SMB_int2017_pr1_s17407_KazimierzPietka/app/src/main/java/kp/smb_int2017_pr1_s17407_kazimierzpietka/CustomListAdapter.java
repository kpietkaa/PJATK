package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//public class CustomListAdapter extends ArrayAdapter {
//    private Context context;
//    private final List<Task> tasksList;
//    private SQLiteDatabaseHandler db;
//    Button edit = null;
//    Button destroy = null;
//    Button done = null;
//
//    public CustomListAdapter(Context context, List<Task> tasks){
//
//        super(context,R.layout.listview_row , tasks);
//
////        this.context=context;
//        this.tasksList = tasks;
//    }

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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View listViewItem = inflater.inflate(R.layout.listview_row, null, true);

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

        return listViewItem;
    }
}

//    public View getView(final int position, View view, ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View rowView = inflater.inflate(R.layout.listview_row, null,true);
//        db = new SQLiteDatabaseHandler(context);
//
//        TextView nameTextField =     (TextView) rowView.findViewById(R.id.nameTextViewID);
//        TextView priceTextField =    (TextView) rowView.findViewById(R.id.priceTextViewID);
//        TextView quantityTextField = (TextView) rowView.findViewById(R.id.quantityTextViewID);
//        destroy                    = (Button) rowView.findViewById(R.id.destroyButton);
//        edit                       = (Button) rowView.findViewById(R.id.editButton);
//        done                       = (Button) rowView.findViewById(R.id.doneButton);

//        nameTextField.setText("Nazwa: " + tasksList.get(position).getName());
//        priceTextField.setText("Cena: " + tasksList.get(position).getPrice());
//        quantityTextField.setText("Ilość: " + tasksList.get(position).getQuantity());
//
//        rowView.findViewById(R.id.destroyButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Usuwanie: " + tasksList.get(position).getName(), Toast.LENGTH_SHORT).show();
//                db.deleteOne(tasksList.get(position));
//                Intent intent = new Intent(context, ShowTasksActivity.class);
//                context.startActivity(intent);
//            }
//        });
//
//        rowView.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Task task = tasksList.get(position);
//                Intent intent = new Intent(context, AddProductActivity.class);
//                Integer id = Integer.parseInt(task.getId());
//                intent.putExtra("task", id);
//                context.startActivity(intent);
//            }
//        });
//
//        rowView.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Task task = tasksList.get(position);
//                task.setDone();
//                db.updateTask(task);
//                Intent intent = new Intent(context, ShowTasksActivity.class);
//                context.startActivity(intent);
//                Toast.makeText(context, "Kupione: " + tasksList.get(position).getName() + " cena i ilość zmienione na 0", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return rowView;
//    }
//}

