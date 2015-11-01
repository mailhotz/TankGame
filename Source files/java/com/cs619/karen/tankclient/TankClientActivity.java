package com.cs619.karen.tankclient;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.cs619.karen.tankclient.Logic.GameGrid;
import com.cs619.karen.tankclient.rest.BulletZoneRestClient;
import com.cs619.karen.tankclient.rest.PollerTask;
import com.cs619.karen.tankclient.ui.GridAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_main)
public class TankClientActivity extends AppCompatActivity {

    private static final String TAG = "TankClientActivity";


    @Bean
    protected GridAdapter mGridAdapter;

    @ViewById
    protected GridView gridView;


    @RestService
    BulletZoneRestClient restClient;

    @Bean
    PollerTask gridPollTask;

    //GameGrid Object
   //GameGrid gGrid = new GameGrid();

    private long tankId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        //Get gridview and set it's adapter
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(mGridAdapter);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
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

    @AfterViews
    protected void afterViewInjection() {
        joinAsync();
        SystemClock.sleep(500);
        //gridView.setAdapter(mGridAdapter);
    }

    @Background
    void joinAsync() {
        try {
            tankId = restClient.join().getResult();

            Log.d(TAG, "tankId is " + tankId);
            gridPollTask.doPoll(); // start polling the server
            gridAsync();
        } catch (Exception e) {

        }
    }

    //Creates a gameGrid and updates the gridview asynchronously
    @Background
    void gridAsync(){
        try{
            //Create a gameGrid that gets grid from restClient
            GameGrid gGrid = new GameGrid(restClient);
            gGrid.getID(tankId);
            gGrid.makeTGrid();
            gGrid.print();


            //Use adapter to update grid
            mGridAdapter.updateList(gGrid.getGrid());

           // System.out.println(mGridAdapter.areAllItemsEnabled());


        }catch  (Exception e){

        }
    }

    // Async call to the rest asking to move or rotate the tank
    // to move up or rotate to be facing up depending on the direction
    // the tank is facing
    @Background
    @Click(R.id.buttonUp)
    void upClicked(){
        Log.d(TAG, "Up");
        restClient.move(tankId, Byte.valueOf("0"));   // call the rest to move up
    }

    // Async call to the rest asking to move or rotate the tank so that it
    // is facing down or so that it moves down depending on the direction
    // the tank is facing
    @Background
    @Click(R.id.buttonDown)
    void downClicked(){
        Log.d(TAG, "Down");
        restClient.move(tankId, Byte.valueOf("4")); // call the rest to move down 8=/=D
    }

    @Background
    @Click(R.id.buttonLeft)
    void leftClicked(){
        Log.d(TAG, "Left");
        restClient.move(tankId, Byte.valueOf("6"));
    }

    @Background
    @Click(R.id.buttonRight)
    void rightClicked(){
        Log.d(TAG, "Right");
        restClient.move(tankId, Byte.valueOf("2"));
    }

    @Background
    @Click(R.id.buttonFire)
    void fireClicked(){
        Log.d(TAG, "Fire");
        restClient.fire(tankId);
        gridAsync();
    }
}
