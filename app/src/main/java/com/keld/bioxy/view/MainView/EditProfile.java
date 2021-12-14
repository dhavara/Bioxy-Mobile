package com.keld.bioxy.view.MainView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.keld.bioxy.R;

public class EditProfile extends AppCompatActivity {
    Spinner spinner;
    TextView selection;
    String []item = {"Helper","Support","Attack","Hitter"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_profile);

        selection = (TextView) findViewById(R.id.selection);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(aa);

    }

}
