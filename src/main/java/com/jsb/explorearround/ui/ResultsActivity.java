package com.jsb.explorearround.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jsb.explorearround.Controller;
import com.jsb.explorearround.R;
import com.jsb.explorearround.location.LocationTracker;
import com.jsb.explorearround.parser.Location;
import com.jsb.explorearround.parser.Model;
import com.jsb.explorearround.parser.OpenHours;
import com.jsb.explorearround.parser.Photos;
import com.jsb.explorearround.parser.PlaceDetailsModel;
import com.jsb.explorearround.parser.Results;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by JSB on 10/9/15.
 */
public class ResultsActivity extends AppCompatActivity {
    private static final String TAG = "ResultsActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static Model mResults = null;
    private ControllerResults mControllerCallback;
    private int mPosition;
    private Toolbar toolbar;
    private String mPhotoWidth;
    private String mPhotoRef;


    public static void actionLaunchResultsActivity(Activity fromActivity, Model res) {
        mResults = res;
        //TODO - Need to use Bundle here
        Intent i = new Intent(fromActivity, ResultsActivity.class);
        fromActivity.startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_card_view);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(getDataSet(), this);
        mRecyclerView.setAdapter(mAdapter);

        //Initialise the Controller from here
        mControllerCallback = new ControllerResults();
        Controller.getsInstance(this).addResultCallback(mControllerCallback);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        ((RecyclerViewAdapter) mAdapter).setOnItemClickListener(new RecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Controller.getInstance().getPlaceDetails(ResultsActivity.this,
                        mResults.getResults()[position].getPlace_id());

                Photos[] photos = mResults.getResults()[position].getPhotos();
                if (photos != null && photos.length > 0) {
                    mPhotoRef = photos[0].getPhoto_reference();
                    mPhotoWidth = photos[0].getWidth();
                }
                mPosition = position;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        Controller.getInstance().removeResultCallback(mControllerCallback);
    }

    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        Results[] res = mResults.getResults();
        LocationTracker locTracker = new LocationTracker(this);
        Double lat = locTracker.getLatitude();
        Double longtitude = locTracker.getLongitude();
        for (int index = 0; index < res.length; index++) {
            OpenHours openHrs = res[index].getOpening_hours();
            String status = "";
            if (openHrs != null) {
                boolean open = openHrs.getOpen_now();
                if (open) {
                    status = "OPEN";
                } else {
                    status = "CLOSED";
                }
            }
            DataObject obj = new DataObject(res[index].getName(),
                    res[index].getVicinity(),/*getAdress(res[index].getVicinity())*/
                     status, calculateDst(res[index].getGeometry().getLocation(), lat, longtitude));
            results.add(index, obj);
        }
        return results;
    }

    private String getAdress(String vicinity) {
        if (vicinity == null) {
            return "";
        }
        String[] results = vicinity.split(",");
        return results[0];
    }

    private String getCityName(String vicinity) {
        if (vicinity == null) {
            return "";
        }
        String[] results = vicinity.split(",");
        if(results.length > 1){
            return results[1];
        }
        return results[0];
    }

    private String calculateDst(Location location, Double lat, Double longtitude) {
       String ret = null;
        android.location.Location srcLoc = new android.location.Location("source");
        srcLoc.setLatitude(Double.valueOf(lat));
        srcLoc.setLongitude(Double.valueOf(longtitude));


        android.location.Location dstLoc = new android.location.Location("destination");
        dstLoc.setLatitude(Double.valueOf(location.getLatitude()));
        dstLoc.setLongitude(Double.valueOf(location.getLongtitude()));

        Float dst = (srcLoc.distanceTo(dstLoc)/1000);
        Double miles = dst * 0.621;
        //ret = NumberFormat.getInstance().format(miles);


        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        ret = df.format(miles);
        ret = ret + " Miles";
        return ret;
    }

    /**
     * Controller result Callback.
     */
    private class ControllerResults extends Controller.Result {

        @Override
        public void getPlaceDetailsCallback(final PlaceDetailsModel status, final String reason) {
            Log.d(TAG, "getPlaceDetailsCallback");
            runOnUiThread(new Runnable() {
                public void run() {
                    if (status == null) {
                        Toast.makeText(ResultsActivity.this, reason, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    DetailsActivity.actionLaunchResultsActivity(ResultsActivity.this, status.getResult(), mPhotoWidth, mPhotoRef);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Log.d(TAG, "action bar clicked");
        }

        return super.onOptionsItemSelected(item);
    }
}
