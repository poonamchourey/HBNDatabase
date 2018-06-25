package com.example.myapp.hbndatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends AppCompatActivity {
String textTitle,textName, textAddress,textDetails ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        textTitle = getIntent().getExtras().getString("TITLE");
        textName = getIntent().getExtras().getString("NAME");
        textAddress = getIntent().getExtras().getString("ADDRESS");
        textDetails = getIntent().getExtras().getString("DETAILS");

        TextView tvTitle = (TextView)findViewById(R.id.textViewTitle);
       // TextView tvName = (TextView)findViewById(R.id.textViewName);
        //TextView tvAddress = (TextView)findViewById(R.id.textViewAddress);
        //TextView tvDetails = (TextView)findViewById(R.id.textViewDetails);
        tvTitle.setText("Project :"+" "+textTitle+ '\n'+'\n'+"DETAILS : "+'\n'+"\t\t"+textDetails +" "+'\n'+"NAME : "+textName+'\n'+'\n'+"ADDRESS : "+textAddress +'\n'+'\n');
       // tvName.setText("NAME :" + "" + textName);
        //tvAddress.setText("ADDRESS :" +""+textAddress);
        //tvDetails.setText("DETAILS :" +""+ textDetails);


    }
}
