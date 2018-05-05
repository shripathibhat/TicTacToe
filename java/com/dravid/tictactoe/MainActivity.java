package com.dravid.tictactoe;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    Button a1, a2, a3, b1, b2, b3, c1, c2, c3, bNewGame;
    int x=0,o=0;
    String xc,oc;
    Button[] bArray;
    TextView t1 , t2;
    boolean turn = true; // X=true O=false
    int turn_count = 0;
    TextView s1,s2;
    String a[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = getIntent().getStringArrayExtra("players");
        t1 = (TextView)findViewById(R.id.pl1);
        t2 = (TextView)findViewById(R.id.pl2);
        s1 = (TextView)findViewById(R.id.score1);
        s2 = (TextView)findViewById(R.id.score2);
        t1.setText(a[0]);
        t2.setText(a[1]);

        a1 = (Button) findViewById(R.id.A1);
        a2 = (Button) findViewById(R.id.A2);
        a3 = (Button) findViewById(R.id.A3);
        b1 = (Button) findViewById(R.id.B1);
        b2 = (Button) findViewById(R.id.B2);
        b3 = (Button) findViewById(R.id.B3);
        c1 = (Button) findViewById(R.id.C1);
        c2 = (Button) findViewById(R.id.C2);
        c3 = (Button) findViewById(R.id.C3);
        bNewGame = (Button)findViewById(R.id.bNewGame);

        bArray = new Button[]{a1, a2, a3, b1, b2, b3, c1, c2, c3};
        for (Button b : bArray) {
            b.setOnClickListener(this);
        }

        bNewGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                turn = true;
                turn_count = 0;
                enabledisabling(true);
            }
        });
    }

    private void toast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        Button b = (Button) view;
        buttonClicked(b);

    }

    public void buttonClicked(Button b) {
        if (turn) {
            b.setText("X");
        } else {
            b.setText("O");
        }
        turn_count++;
        b.setClickable(false);
        b.setBackgroundColor(Color.LTGRAY);
        turn = !turn;
        checkForWinner();

    }

    private void checkForWinner() {
        boolean winner = false;
        // Horizontal
        if (a1.getText() == a2.getText() && a2.getText() == a3.getText() && !a1.isClickable())
            winner = true;
        else if (b1.getText() == b2.getText() && b2.getText() == b3.getText() && !b1.isClickable())
            winner = true;
        else if (c1.getText() == c2.getText() && c2.getText() == c3.getText() && !c1.isClickable())
            winner = true;
            // Vertical
        else if (a1.getText() == b1.getText() && b1.getText() == c1.getText() && !a1.isClickable())
            winner = true;
        else if (a2.getText() == b2.getText() && b2.getText() == c2.getText() && !b2.isClickable())
            winner = true;
        else if (a3.getText() == b3.getText() && b3.getText() == c3.getText() && !c3.isClickable())
            winner = true;
            //diagonal
        else if (a1.getText() == b2.getText() && b2.getText() == c3.getText() && !a1.isClickable())
            winner = true;
        else if (a3.getText() == b2.getText() && b2.getText() == c1.getText() && !b2.isClickable())
            winner = true;

        if (winner) {
            if (!turn) {
                toast(a[0]+" Wins");
                x++;
                xc=Integer.toString(x);
                s1.setText(xc);
            } else {
                toast(a[1]+" Wins");
                o++;
                oc=Integer.toString(o);
                s2.setText(oc);
            }
            enabledisabling(false);
        } else if (turn_count == 9) {
            toast("DRAW");
        }
    }

    private void enabledisabling(boolean enable) {
        for (Button b : bArray) {
            b.setClickable(enable);
            if (enable) {
                b.setBackgroundColor(Color.parseColor("#33bcff"));
                b.setText("");
            }
            else {
                b.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
}
