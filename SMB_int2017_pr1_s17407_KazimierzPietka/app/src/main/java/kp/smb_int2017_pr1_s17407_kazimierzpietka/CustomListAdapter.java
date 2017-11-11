package kp.smb_int2017_pr1_s17407_kazimierzpietka;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter {
    private final Activity context;
    private final List<Task> tasksList;

    public CustomListAdapter(Activity context, List<Task> tasks){

        super(context,R.layout.listview_row , tasks);

        this.context=context;
        this.tasksList = tasks;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null,true);

        TextView idTextField =       (TextView) rowView.findViewById(R.id.idTextViewID);
        TextView nameTextField =     (TextView) rowView.findViewById(R.id.nameTextViewID);
        TextView priceTextField =    (TextView) rowView.findViewById(R.id.priceTextViewID);
        TextView quantityTextField = (TextView) rowView.findViewById(R.id.quantityTextViewID);

        idTextField.setText(tasksList.get(position).getId());
        nameTextField.setText(tasksList.get(position).getName());
        priceTextField.setText(tasksList.get(position).getPrice());
        quantityTextField.setText(tasksList.get(position).getQuantity());

        return rowView;
    }
}
