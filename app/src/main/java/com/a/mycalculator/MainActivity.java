package com.a.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText tEdit;
    private RadioButton manRadioButton;
    private RadioButton womanRadioButton;
    private Button caculatorButton;
    private TextView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tEdit = (EditText) findViewById(R.id.tEdit);
        manRadioButton = (RadioButton) findViewById(R.id.man);
        womanRadioButton = (RadioButton) findViewById(R.id.woman);
        caculatorButton = (Button) findViewById(R.id.caculator);
        tView = (TextView) findViewById(R.id.result);
    }

    @Override
    protected void onStart() {
        registerEvent();
        super.onStart();
    }

    public void registerEvent(){
        caculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tEdit.getText().toString().trim().equals("")){
                    if(manRadioButton.isChecked()||womanRadioButton.isChecked()){
                        Double weight = Double.parseDouble(tEdit.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("-----------身高结果---------\n");
                        if(manRadioButton.isChecked()){
                            sb.append("男性标准身高");
                            double result = evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");
                        }else if(womanRadioButton.isChecked()){
                            sb.append("女性标准身高");
                            double result = evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米");
                        }
                        tView.setText(sb.toString());
                    }else{
                        showmessage("请选择性别");
                    }
                }else{
                    showmessage("请填写体重");
                }
            }
        });
    }
    private double evaluateHeight(double weight,String str){
        double height;
        if(str=="男"){
            height = 170-(62-weight)/0.6;
        }else{
            height = 158-(52-weight)/0.5;
        }
        return height;
    }
    private void showmessage(String str){
        AlertDialog alert = new  AlertDialog.Builder(this).create();
        alert.setTitle("系统提示");
        alert.setMessage(str);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
