package kp.sender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void send(View view){
        text = (EditText) findViewById(R.id.editText);
        Intent intent = new Intent();
        intent.setAction("kp.sender.KAZ_BROADCAST");
        intent.putExtra("text_message", text.getText().toString());
        sendBroadcast(intent);
        Toast.makeText(getBaseContext(), "Rozgloszenie wyslane", Toast.LENGTH_SHORT).show();
    }
}
