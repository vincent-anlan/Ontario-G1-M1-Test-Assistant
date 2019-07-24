package ca.uwaterloo.cs349.switchpages;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;

import android.view.View;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;

/**
 * Created by vincent on 3/27/2018.
 */

public class SelectActivity extends AppCompatActivity{

    private EditText et;
    private Button btn;
    private CharSequence name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        //disable button
        btn = (Button) findViewById(R.id.nextbuttonp1);
        btn.setEnabled(false);
        //add listener to edittext
        et = (EditText) findViewById(R.id.name_input);
        et.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                name = s;
                if(s.length() != 0)
                    btn.setEnabled(true);
                else
                    btn.setEnabled(false);
            }
        });
    }

    //go to next page
    public void goNext(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", name.toString());
        startActivity(intent);
    }


}
