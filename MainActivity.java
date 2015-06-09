package com.tlians.watchstop;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
long time;
Handler mHandler=new Handler();

    TextView display;
    Boolean stopped=false;
    long starttime;

    public void startClicked(View view){
        showStop();
        if(stopped) {
            starttime = System.currentTimeMillis()-time;
            stopped=false;
        }
        else
        starttime=System.currentTimeMillis();
        display=(TextView) findViewById(R.id.display);
        mHandler.removeCallbacks(Timer);
        mHandler.postDelayed(Timer,0);
    }

    public Runnable Timer=new Runnable(){
        public void run(){
            if(stopped==false){
            time=System.currentTimeMillis()-starttime;
            display=(TextView) findViewById(R.id.display);
                {
                    long mins,secs,hours,msecs;
                    secs=time/1000;
                    mins=time/1000/60;
                    hours=time/1000/60/60;
                    msecs=time/10;
                    secs=secs%60;
                    msecs=msecs%100;
                    mins=mins%60;
                    hours=hours%60;
                    String h,m,s,ms;
                    ms=String.valueOf(msecs);
                    if(msecs<10)
                        ms=String.valueOf("0"+msecs);
                    h=String.valueOf(hours);
                    if(hours<10)
                        h=String.valueOf("0"+hours);
                    m=String.valueOf(mins);
                    if(mins<10)
                        m=String.valueOf("0"+mins);
                    s=String.valueOf(secs);
                    if(secs<10)
                        s=String.valueOf("0"+secs);

                    String tt=String.valueOf(time);
                    display.setText(h+":"+m+":"+s+":"+ms);

                }
            mHandler.postDelayed(this, 100);}
        }
    };


    private void showStop(){
        ((Button) findViewById(R.id.start)).setVisibility(View.GONE);
        ((Button) findViewById(R.id.stop)).setVisibility(View.VISIBLE);

    }

    public void stopClicked(View view){
        showStart();
        stopped=true;
    }

    private void showStart(){
        ((Button) findViewById(R.id.stop)).setVisibility(View.GONE);
        ((Button) findViewById(R.id.start)).setVisibility(View.VISIBLE);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.stop)).setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
