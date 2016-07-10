package com.dravid.tictactoe;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Welcome extends ActionBarActivity {

    EditText pl1,pl2;
    Button start;
    String p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onStart(View v){
        if(v.getId()==R.id.start){
            pl1=(EditText)findViewById(R.id.pl1);
            pl2=(EditText)findViewById(R.id.pl2);
            p1=pl1.getText().toString();
            p2=pl2.getText().toString();
            if(p1.equals("")||p2.equals("")){
                Toast t = Toast.makeText(this,"Enter both player names",Toast.LENGTH_SHORT);
                t.show();
            }
            else
            {
                String a[] = {p1,p2};
                Intent i = new Intent(Welcome.this,MainActivity.class);
                i.putExtra("players",a);
                startActivity(i);
            }


        }
    }



}
