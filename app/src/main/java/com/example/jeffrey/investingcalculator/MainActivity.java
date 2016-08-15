package com.example.jeffrey.investingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
//import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    private GridviewAdapter mAdapter;
    private ArrayList<String> listMenu;
    private ArrayList<Integer> listMenuIcon;

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareList();

        // prepared arraylist and passed it to the Adapter class
        mAdapter = new GridviewAdapter(this,listMenu, listMenuIcon);

        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(mAdapter);

        // Implement On Item click listener
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                Toast.makeText(MainActivity.this, mAdapter.getItem(position), Toast.LENGTH_SHORT).show();

                Intent intent = null;

                // Open recipes main menu
                if (position == 0) {
                    //intent = new Intent(getBaseContext(), MainActivity.class);
                    intent = new Intent(getBaseContext(), ValueCalculatorActivity.class);
                    intent.putExtra("id", position);
                    intent.putExtra("act_title", "Value Calculator");
                }

                //Open facebook app
                else if (position == 1) {
                    startNewActivity(getBaseContext(),"com.facebook.katana");
                }

                //Open facebook messenger app
                else if (position == 2) {
                    startNewActivity(getBaseContext(),"com.facebook.orca");
                }

                //Open messaging app
                else if (position == 3) {
                    startNewActivity(getBaseContext(), "com.android.mms");
                }

                //Open google search
                else if (position == 4) {
                    startNewActivity(getBaseContext(), "com.google.android.googlequicksearchbox");
                }

                //Open google plus
                else if (position == 5) {
                    startNewActivity(getBaseContext(), "com.google.android.apps.plus");
                }

                //Open Instagram
                else if (position == 6) {
                    startNewActivity(getBaseContext(), "com.instagram.android");
                }

                //Open viber
                else if (position == 7) {
                    startNewActivity(getBaseContext(), "com.viber.voip");
                }

                //Open email app
                else if (position == 8) {
                    startNewActivity(getBaseContext(), "com.android.email");
                }

                // List installed apps
                else if (position == 9) {
                    GetInstalledAppList();
                }

                //Open about activity
                else if (position == 12) {

                    intent = new Intent(getBaseContext(), AboutActivity.class);
                    intent.putExtra("id", position);
                    intent.putExtra("act_title", "About");
                }

                if(intent != null)
                    startActivity(intent);
            }
        });
    }

    //Create list for grodview
    public void prepareList()
    {
        listMenu = new ArrayList<String>();

        listMenu.add("Calculator");
        listMenu.add("Facebook");
        listMenu.add("F Messenger");
        listMenu.add("Messenger");
        listMenu.add("Google");
        listMenu.add("Google+");
        listMenu.add("Instagram");
        listMenu.add("Viber");
        listMenu.add("Mail");
        listMenu.add("Installed Apps");
        listMenu.add("Rate");
        listMenu.add("Share");
        listMenu.add("About");

        listMenuIcon = new ArrayList<Integer>();

        listMenuIcon.add(R.drawable.stockchart);
        listMenuIcon.add(R.drawable.facebook);
        listMenuIcon.add(R.drawable.facebook_48);
        listMenuIcon.add(R.drawable.messaging);
        listMenuIcon.add(R.drawable.google48);
        listMenuIcon.add(R.drawable.googleplus);
        listMenuIcon.add(R.drawable.instagram);
        listMenuIcon.add(R.drawable.viberlight);
        listMenuIcon.add(R.drawable.email);
        listMenuIcon.add(R.drawable.apps48);
        listMenuIcon.add(R.drawable.rate);
        listMenuIcon.add(R.drawable.share);
        listMenuIcon.add(R.drawable.about);
    }

    //Procedure to start an application using its package name
    public void startNewActivity(Context context, String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
        /* We found the activity now start the activity */
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
        /* Bring user to the market or let them choose an app? */
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            context.startActivity(intent);
        }
    }

    // Code Snippet for getting installed applications in Android
    void GetInstalledAppList()
    {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);
        for (Object object : pkgAppsList)
        {
            ResolveInfo info = (ResolveInfo) object;
            Drawable icon    = getBaseContext().getPackageManager().getApplicationIcon(info.activityInfo.applicationInfo);
            String strAppName  	= info.activityInfo.applicationInfo.publicSourceDir.toString();
            String strPackageName  = info.activityInfo.applicationInfo.packageName.toString();
            final String title 	= (String)((info != null) ? getBaseContext().getPackageManager().getApplicationLabel(info.activityInfo.applicationInfo) : "???");
        }
    }
}
