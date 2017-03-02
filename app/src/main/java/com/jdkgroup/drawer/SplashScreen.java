package com.jdkgroup.drawer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.jdkgroup.general.General;
import com.jdkgroup.general.GeneralImplement;
import com.jdkgroup.utils.AppKeyword;
import com.jdkgroup.utils.SaveSharedPrefernces;

import java.util.HashMap;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    private Activity act;
    private Context context;
    private Thread mSplashThread;
    private General general;
    private SaveSharedPrefernces ssp;

    private Map utilMap;

    private AppCompatTextView actAppTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        act = this;
        context = getApplicationContext();

        ssp = new SaveSharedPrefernces();
        general = new GeneralImplement();
        utilMap = new HashMap();

        actAppTitle = (AppCompatTextView) findViewById(R.id.actAppTitle);

        general.FontAppCompatTextView(actAppTitle, AppKeyword.sourcesanspro_bold);

        if (!ssp.get_Userid(act).isEmpty()) {

        }

        // The thread to wait for splash screen events
        mSplashThread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
                        if (ssp.get_Userid(act).isEmpty()) {
                            wait(3000L);
                        }
                    }
                } catch (InterruptedException ex) {

                }

                if (ssp.get_Userid(act).isEmpty()) {
                    general.SwitchPassdataChangeFragment(act, AppKeyword.REDIRECT_DRAWER_4_0);
                    finish();
                }
            }
        };

        mSplashThread.start();
    }

    // Processes splash screen touch events
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (mSplashThread) {
                mSplashThread.notifyAll();
            }
        }
        return true;
    }
}
