package ca.uwaterloo.cs349.switchpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
//for debug
//import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;
import java.util.ArrayList;
import java.util.List;


public class VersionActivity extends AppCompatActivity {

    private int question;
    private int index;
    private int score;
    private int flag;
    private int bb;
    private int radiocheck;
    private int boxcheck;
    //store the selection
    private List<Integer> choose;
    //field of required button and layout
    private Button btp;
    private Button btn;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private String name;
    private int english;
    private int math;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        // Get the intent that started this activity
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        //pre set button
        btp = (Button) findViewById(R.id.button);
        btn = (Button) findViewById(R.id.button2);
        btp.setEnabled(false);
        //preset layout
        layout1 = (LinearLayout) findViewById(R.id.multiple);
        layout2 = (LinearLayout) findViewById(R.id.check_box);
        // extract the intent value in int
        int v = intent.getIntExtra("version", 0);
        english = 0;
        math= 0;
        if(v<0){
            english = 1;
            v = 0 - v;
        }else if(v>1000){
            v = v -1000;
            math = 1;
        }
        index = 1;
        flag = 0;
        bb = 0;
        radiocheck = 0;
        boxcheck = 0;
        //init the array list
        choose = new ArrayList<Integer>(10);
        for(int i = 0; i <9;++i){
            choose.add(0);
        }
        setName(name);
        question = v;
        if(question == 1){
            btn.setText("Finish");
            bb =1;
        }
        if(english == 1||math ==1){
            SetVersion1(index);
        }else{
            SetVersion(index);
        }
        //add listener to radio group
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                    case R.id.radioButton1:
                        radiocheck = 1;
                        break;
                    case R.id.radioButton2:
                        radiocheck = 2;
                        break;
                    case R.id.radioButton3:
                        radiocheck = 3;
                        break;
                    case R.id.radioButton4:
                        radiocheck = 4;
                        break;
                }
            }
        });

        score = 0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //set the version of question
    void SetVersion(int v){
        //text
        TextView label = findViewById(R.id.version_txt);
        TextView label2 = findViewById(R.id.textView2);
        //image
        ImageView image_view = findViewById(R.id.imageView);
        //radio button group
        //layout

        //radio button
        RadioButton radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton)findViewById(R.id.radioButton3);
        RadioButton radioButton4 = (RadioButton)findViewById(R.id.radioButton4);
        //checked box
        CheckBox checkbox1 = (CheckBox)findViewById(R.id.checkbox1);
        CheckBox checkbox2 = (CheckBox)findViewById(R.id.checkbox2);
        CheckBox checkbox3 = (CheckBox)findViewById(R.id.checkbox3);
        CheckBox checkbox4 = (CheckBox)findViewById(R.id.checkbox4);

        //check radio button at first and restore previous checked button
        if(radiocheck ==1){
            radioButton1.setChecked(true);
        }else if(radiocheck == 2){
            radioButton2.setChecked(true);
        }else if(radiocheck == 3){
            radioButton3.setChecked(true);
        }else if(radiocheck == 4){
            radioButton4.setChecked(true);
        }
        //disable checkbox at first
        checkbox1.setChecked(false);
        checkbox2.setChecked(false);
        checkbox3.setChecked(false);
        checkbox4.setChecked(false);
        if(boxcheck % 10 == 1){
            checkbox1.setChecked(true);
        }
        if(boxcheck % 100 >= 10){
            checkbox2.setChecked(true);
        }
        if(boxcheck % 1000 >= 100){
            checkbox3.setChecked(true);
        }
        if(boxcheck >=  1000){
            checkbox4.setChecked(true);
        }
        StringBuilder ssb = new StringBuilder();
        ssb.append(index);
        ssb.append("/");
        ssb.append(question);
        label2.setText(ssb);
        // set version code and image
        switch(v){
            case 1://No.1 question
                label.setText("1 请选择正确的一项");
                image_view.setImageResource(R.drawable.image1);
                radioButton1.setText("靠交通安全岛右侧行驶");
                radioButton2.setText("弯曲路在前");
                radioButton3.setText("单行道在前");
                radioButton4.setText("急弯路在前");
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            case 2://No.2 question
                label.setText("2 多选题，请选择正确的所有项");
                image_view.setImageResource(R.drawable.image2);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                checkbox1.setText("目的地指示牌");
                checkbox2.setText("医院标识");
                checkbox3.setText("Taylor在左方");
                checkbox4.setText("警告路牌");
                break;
            case 3://No.3 question
                label.setText("3 请选择正确的一项");
                image_view.setImageResource(R.drawable.image3);
                radioButton1.setText("禁止驶入");
                radioButton2.setText("让牌标识");
                radioButton3.setText("任何时候都必须停车");
                radioButton4.setText("警告标识");
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            case 4://No.4 question
                label.setText("4 请选择正确的一项");
                image_view.setImageResource(R.drawable.image4);
                radioButton1.setText("任何时候都要停车");
                radioButton2.setText("限制路牌");
                radioButton3.setText("修路的标识");
                radioButton4.setText("慢行车辆在前");
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            case 5://No.5 question
                label.setText("5 多选题，请选择正确的所有项");
                image_view.setImageResource(R.drawable.image5);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                checkbox1.setText("右线行驶必须右转");
                checkbox2.setText("小心行人过马路");
                checkbox3.setText("限制路牌");
                checkbox4.setText("保持右行");
                break;
            case 6://result page
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                if(choose.get(1) == 1)  score++;
                if(choose.get(2) == 101)  score++;
                if(choose.get(3) == 3)  score++;
                if(choose.get(4) == 4)  score++;
                if(choose.get(5) == 1100)  score++;
                StringBuilder sb = new StringBuilder();
                sb.append("Your Score:");
                sb.append(score);
                sb.append("/");
                sb.append(question);
                label.setText(sb );
                btn.setText("Select");
                btp.setVisibility(View.GONE);
                label2.setVisibility(View.GONE);
                image_view.setVisibility(View.GONE);
                break;

        }

    }

    public void SetVersion1(int v){
        //text
        TextView label = findViewById(R.id.version_txt);
        TextView label2 = findViewById(R.id.textView2);
        //image
        ImageView image_view = findViewById(R.id.imageView);
        //radio button group
        //layout

        //radio button
        RadioButton radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        RadioButton radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        RadioButton radioButton3 = (RadioButton)findViewById(R.id.radioButton3);
        RadioButton radioButton4 = (RadioButton)findViewById(R.id.radioButton4);
        //checked box
        CheckBox checkbox1 = (CheckBox)findViewById(R.id.checkbox1);
        CheckBox checkbox2 = (CheckBox)findViewById(R.id.checkbox2);
        CheckBox checkbox3 = (CheckBox)findViewById(R.id.checkbox3);
        CheckBox checkbox4 = (CheckBox)findViewById(R.id.checkbox4);

        //check radio button at first and restore previous checked button
        if(radiocheck ==1){
            radioButton1.setChecked(true);
        }else if(radiocheck == 2){
            radioButton2.setChecked(true);
        }else if(radiocheck == 3){
            radioButton3.setChecked(true);
        }else if(radiocheck == 4){
            radioButton4.setChecked(true);
        }
        //disable checkbox at first
        checkbox1.setChecked(false);
        checkbox2.setChecked(false);
        checkbox3.setChecked(false);
        checkbox4.setChecked(false);
        if(boxcheck % 10 == 1){
            checkbox1.setChecked(true);
        }
        if(boxcheck % 100 >= 10){
            checkbox2.setChecked(true);
        }
        if(boxcheck % 1000 >= 100){
            checkbox3.setChecked(true);
        }
        if(boxcheck >=  1000){
            checkbox4.setChecked(true);
        }
        StringBuilder ssb = new StringBuilder();
        ssb.append(index);
        ssb.append("/");
        ssb.append(question);
        label2.setText(ssb);
        // set version code and image
        switch(v){
            case 1://No.1 question
                if(math == 1){
                    label.setText("1 简单的逻辑问题 一加一等于");
                    image_view.setImageResource(R.drawable.image1);
                    radioButton1.setText("二");
                    radioButton2.setText("三");
                    radioButton3.setText("四");
                    radioButton4.setText("五");
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.GONE);
                    break;
                }else{
                    label.setText("1 请问图片的颜色");
                    image_view.setImageResource(R.drawable.image1);
                    radioButton1.setText("黑白");
                    radioButton2.setText("蓝黑");
                    radioButton3.setText("红黄");
                    radioButton4.setText("棕红");
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.GONE);
                    break;
                }

            case 2://No.2 question
                if(math == 1){
                    label.setText("2 请问图片的颜色");
                    image_view.setImageResource(R.drawable.image3);
                    radioButton1.setText("红");
                    radioButton2.setText("黄");
                    radioButton3.setText("蓝");
                    radioButton4.setText("绿");
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.GONE);
                    break;
                }else {
                    label.setText("2 Select what does it contain in flag");
                    image_view.setImageResource(R.drawable.image1);
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    checkbox1.setText("Maple");
                    checkbox2.setText("Egg");
                    checkbox3.setText("Leaf");
                    checkbox4.setText("Cow");
                }
                break;
            case 3://No.3 question
                label.setText("3 Select the color of Pentagram in this flag");
                image_view.setImageResource(R.drawable.image3);
                radioButton1.setText("Black");
                radioButton2.setText("Red");
                radioButton3.setText("Yellow");
                radioButton4.setText("Blue");
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            case 4://No.4 question
                label.setText("4 Select the background color that in this flag");
                image_view.setImageResource(R.drawable.image3);
                radioButton1.setText("Black");
                radioButton2.setText("Yellow");
                radioButton3.setText("Blue");
                radioButton4.setText("Red");
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            case 5://No.5 question
                label.setText("5 Select the countries that have these flags");
                image_view.setImageResource(R.drawable.image5);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                checkbox1.setText("US");
                checkbox2.setText("China");
                checkbox3.setText("South Africa");
                checkbox4.setText("United Kingdom");
                break;
            case 6://result page
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.GONE);
                if(choose.get(1) == 1)  score++;
                if(choose.get(2) == 101)  score++;
                if(choose.get(3) == 3)  score++;
                if(choose.get(4) == 4)  score++;
                if(choose.get(5) == 1100)  score++;
                StringBuilder sb = new StringBuilder();
                sb.append("Your Score:");
                sb.append(score);
                sb.append("/");
                sb.append(question);
                label.setText(sb );
                btn.setText("Select");
                btp.setVisibility(View.GONE);
                label2.setVisibility(View.GONE);
                image_view.setVisibility(View.GONE);
                break;

        }

    }

    //method for previous button
    public void clickprevious(View view){

        if(index == 2) radiocheck = choose.get(1);
        if(index == 3){
            boxcheck = choose.get(2);
            choose.set(3,radiocheck);
        }
        if(index == 4) {
            radiocheck = choose.get(3);
            //choose.set(4,radiocheck);
        }
        if(index == 5) {
            radiocheck = choose.get(4);
            choose.set(5,boxcheck);
        }


        if(bb == 1) {
            btn.setText("NEXT");
            bb = 0;
        }
        if(index > 0)    index--;
        if(index == 1)  btp.setEnabled(false);
        if(english == 1||math ==1){
            SetVersion1(index);
        }else{
            SetVersion(index);
        }
    }
    //method for next button
    public  void clicknext(View view){
        TextView label = findViewById(R.id.version_txt);
        if(index != 2 && index != 5){
            choose.set(index,radiocheck);
            RadioGroup radioGroup = findViewById(R.id.radioGroup);
            radioGroup.clearCheck();
            if(index == 1)  radiocheck = choose.get(3);
            if(index == 3)  radiocheck = choose.get(4);
            if(index == 4)  {
                radiocheck = choose.get(4);
                boxcheck = choose.get(5);
            }
        }else{
            choose.set(index,boxcheck);
            if(index == 2)  radiocheck = choose.get(3);
            //if(index == 5)  boxcheck = choose.get(5);
        }

        //if flag then go to select page
        if(flag == 1){
            flag = 0;
            //reset the value
            for(int i = 0; i <9;++i){
                choose.add(0);
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", name);
            startActivity(intent);
            return;
        }
        //if bb then go to result page
        if(bb==1) {
            if(english == 1 || math ==1){
                SetVersion1(6);
            }else{
                SetVersion(6);
            }
            flag = 1;
            bb= 0;
            return;
        }
        if(index < question)
        {index++;}
        if(index == question) {
            btn.setText("Finish");
            bb = 1;
        }

        btp.setEnabled(true);
        if(english == 1||math ==1){
            SetVersion1(index);
        }else{
            SetVersion(index);
        }
    }
    //go back for logout
    public void goBack(View view){
        Intent intent = new Intent(this, SelectActivity.class);
        startActivity(intent);
    }



    // For the checkbox
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox1:
                if (checked) {
                    boxcheck += 1;
                } else{
                    boxcheck -= 1;
                }
                break;
            case R.id.checkbox2:
                if (checked){
                    boxcheck += 10;
                } else {
                    boxcheck -= 10;
                }
                break;
            case R.id.checkbox3:
                if (checked){
                    boxcheck += 100;
                } else {
                    boxcheck -= 100;
                }
                break;
            case R.id.checkbox4:
                if (checked){
                    boxcheck += 1000;
                } else {
                    boxcheck -= 1000;
                }
                break;
        }

    }
    //set the name of current user.
    public void setName(String s){
        TextView textView = findViewById(R.id.n_update);
        textView.setText(s);
    }

}
