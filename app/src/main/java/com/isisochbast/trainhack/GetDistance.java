package com.isisochbast.trainhack;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Lovisa on 2016-08-25.
 */
public class GetDistance extends AsyncTask<String, Void, String> {
    private TextView mAvstandTextView;
    private TextView mSumTextView;
    private TextView mTagTextView;
    private TextView mFlygTextView;
    private TextView mBussTextView;
    private TextView mBilTextView;
    private TextView mValkommen;


    Context mContext;
    //Geo geo1;

    //constructor is used to get the context.
    public GetDistance(Context mContext, TextView avsTV, TextView tTV, TextView fTV) {
        this.mContext = mContext;
 //        geo1 = (Geo) mContext;
this.mAvstandTextView = avsTV;
        this.mTagTextView = tTV;
        this.mFlygTextView = fTV;


        Typeface oswald = Typeface.createFromAsset(mContext.getAssets(), "fonts/Oswald-Heavy.ttf");
        Typeface pasifico = Typeface.createFromAsset(mContext.getAssets(), "fonts/Pacifico.ttf");

        mAvstandTextView.setTypeface(oswald);
      //  mSumTextView.setTypeface(oswald);
        mTagTextView.setTypeface(oswald);
        mFlygTextView.setTypeface(oswald);
        /*mBussTextView.setTypeface(oswald);
        mBilTextView.setTypeface(oswald);
*/
    }

    //This function is executed before before "doInBackground(String...params)" is executed to dispaly the progress dialog
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //This function is executed after the execution of "doInBackground(String...params)" to dismiss the dispalyed progress dialog and call "setDouble(Double)" defined in "MainActivity.java"
    @Override
    protected void onPostExecute(String aDouble) {
        super.onPostExecute(aDouble);
        if (aDouble != null) {
           setDouble(aDouble);
        } else
            Toast.makeText(mContext, "Error! Please Try Again wiht proper values", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode = con.getResponseCode();
            if (statuscode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                String json = sb.toString();
                Log.d("JSON", json);
                JSONObject root = new JSONObject(json);
                JSONArray array_rows = root.getJSONArray("rows");
                Log.d("JSON", "array_rows:" + array_rows);
                JSONObject object_rows = array_rows.getJSONObject(0);
                Log.d("JSON", "object_rows:" + object_rows);
                JSONArray array_elements = object_rows.getJSONArray("elements");
                Log.d("JSON", "array_elements:" + array_elements);
                JSONObject object_elements = array_elements.getJSONObject(0);
                Log.d("JSON", "object_elements:" + object_elements);
                // JSONObject object_duration=object_elements.getJSONObject("duration");
                JSONObject object_distance = object_elements.getJSONObject("distance");

                //  Log.d("JSON","object_duration:"+object_duration);
                return object_distance.getString("value");

            }
        } catch (MalformedURLException e) {
            Log.d("error", "error1");
        } catch (IOException e) {
            Log.d("error", "error2");
        } catch (JSONException e) {
            Log.d("error", "error3");

        }


        return null;
    }


    public void setDouble(String result) {
        String res[] = result.split(",");
        double dist = Integer.parseInt(res[0]) / 1000;
        mAvstandTextView.setText("Du reser " + dist + " kilometers");
//        mSumTextView.setText(R.string.utslapp);


       mTagTextView.setText(String.format("Tåg %s kg CO2", dist * 0.06));
        mFlygTextView.setText(String.format("Flyg %s kg CO2", dist * 0.18));

      //  mBussTextView.setText(String.format("Buss %s", dist * 0.089));

        //mBilTextView.setText(String.format("Bil %s", dist * 0.26));

    }



}
