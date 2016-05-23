package com.example.randomizationmalinea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //declare reference to XML's view:
    Button mybutton;
    EditText myname;
    TextView output_name;
    PatientsDbHelper mypatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //retrieve views (button,text,etc;) on the xml:
        mybutton = (Button) findViewById(R.id.button_assign);
        myname = (EditText) findViewById(R.id.name_patient);
        output_name = (TextView) findViewById(R.id.output_name);
        mypatients = new PatientsDbHelper(this, null, null, 17);
        assign_group(mybutton);

    }
    public void printDatabase() {
        String dbstring = mypatients.databaseToString();
        output_name.setText(dbstring);
        myname.setText("");
    }
    public void assign_group(View view) {
        //retrieve text of the textview:
        String myname_str = myname.getText().toString();
        Boolean name_not_null= myname_str != null && !myname_str.isEmpty();
        // if mybutton is clicked and the name is not null:
      if ( view.equals(mybutton) && name_not_null  ) {
          //popup, randomly assign group
          Random rand = new Random();
          int max=3;
          int min=1;
          int random_group = rand.nextInt((max - min) + 1) + min;
          String mymessage_to_user= myname_str + " is assigned to group "+ random_group;
          //effectively insert with addPatientName method:
          mypatients.addPatientName(myname_str);
          printDatabase();
          Toast.makeText(getApplicationContext(), mymessage_to_user,Toast.LENGTH_LONG).show();

      } else {
          Toast.makeText(getApplicationContext(), "Enter a name!",Toast.LENGTH_SHORT).show();
      }
    }


}
