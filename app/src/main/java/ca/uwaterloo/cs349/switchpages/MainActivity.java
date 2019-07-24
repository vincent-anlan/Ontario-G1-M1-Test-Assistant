package ca.uwaterloo.cs349.switchpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.String;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainActivity extends AppCompatActivity {

    //private int num;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //receive the intend
        Intent intent = getIntent();
        // extract the intent value in int
        name = intent.getStringExtra("name");
        setName(name);
        //num = 0;
        // set spinner value
        Spinner spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spin_item, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        //add listener
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            //add listener to spinner
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                //num = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                //num = 0;
            }

        });
    }




    //method that go to question page
    public void goNextPage1(View view){

        // get selected value from the Radio Group
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String num = mySpinner.getSelectedItem().toString();
        int nn = Integer.valueOf(num);

        // Set the selected value
        Intent intent = new Intent(this, VersionActivity.class);
        intent.putExtra("version", nn);
        intent.putExtra("name", name);
        startActivity(intent);

    }

    //method that go to question page
    public void goNextPage2(View view){

        // get selected value from the Radio Group
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String num = mySpinner.getSelectedItem().toString();
        int nn = Integer.valueOf(num);
        nn = 0 - nn;
        // Set the selected value
        Intent intent = new Intent(this, VersionActivity.class);
        intent.putExtra("version", nn);
        intent.putExtra("name", name);
        startActivity(intent);

    }

    public void goNextPage3(View view){

        // get selected value from the Radio Group
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String num = mySpinner.getSelectedItem().toString();
        int nn = Integer.valueOf(num);
        nn = 1000+ nn;
        // Set the selected value
        Intent intent = new Intent(this, VersionActivity.class);
        intent.putExtra("version", nn);
        intent.putExtra("name", name);
        startActivity(intent);

    }

    //set the name for user
    public void setName(String s){
        TextView textView = findViewById(R.id.n_update);
        textView.setText(s);
    }
    //method for logout
    public void goBack(View view){
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
    }

}
