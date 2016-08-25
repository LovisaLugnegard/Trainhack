package com.isisochbast.trainhack;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class StartFragment extends Fragment  {

    private EditText mStartEditStext;
    private EditText mSlutEditText;
    private Button mBerakna;
    private String mStartDest;
    private String mSlutDest;
    private TextView mAvstandTextView;
    private TextView mSumTextView;
    private TextView mTagTextView;
    private TextView mFlygTextView;
    private TextView mBussTextView;
    private TextView mBilTextView;
    private TextView mValkommen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        Typeface oswald = Typeface.createFromAsset(getContext().getAssets(), "fonts/Oswald-Heavy.ttf");
        Typeface pasifico = Typeface.createFromAsset(getContext().getAssets(), "fonts/Pacifico.ttf");

        mStartEditStext = (EditText) view.findViewById(R.id.start_editText);
        mSlutEditText = (EditText) view.findViewById(R.id.slut_editText);
        mBerakna = (Button) view.findViewById(R.id.berakna_button);

        mAvstandTextView = (TextView) view.findViewById(R.id.avstand);
        mSumTextView = (TextView) view.findViewById(R.id.sum);
        mTagTextView = (TextView) view.findViewById(R.id.tag);
        mBilTextView = (TextView) view.findViewById(R.id.bil);
        mBussTextView = (TextView) view.findViewById(R.id.buss);
        mFlygTextView = (TextView) view.findViewById(R.id.flyg);
        mValkommen = (TextView) view.findViewById(R.id.valkommen);

        mValkommen.setTypeface(pasifico);
        mStartEditStext.setTypeface(oswald);
        mSlutEditText.setTypeface(oswald);
        mBerakna.setTypeface(oswald);
        //mStartDest.setTypeface(oswald);
        //mSlutDest.setTypeface(oswald);

        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=40.6655101,-73.89188969999998&destinations=40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.6905615%2C-73.9976592%7C40.659569%2C-73.933783%7C40.729029%2C-73.851524%7C40.6860072%2C-73.6334271%7C40.598566%2C-73.7527626%7C40.659569%2C-73.933783%7C40.729029%2C-73.851524%7C40.6860072%2C-73.6334271%7C40.598566%2C-73.7527626&key=YOUR_API_KEYy=AIzaSyCCFP1Zdu51zwsF1x7mRlkRwYbxTwuqvdo");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
//            con.connect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mBerakna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStartDest = mStartEditStext.getText().toString();
                mSlutDest = mSlutEditText.getText().toString();


                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + mStartDest + "&destinations=" + mSlutDest + "&mode=driving&language=fr-FR&avoid=tolls&key=AIzaSyCCFP1Zdu51zwsF1x7mRlkRwYbxTwuqvdo";


                 new GetDistance(StartFragment.this.getContext(), mAvstandTextView, mTagTextView, mFlygTextView).execute(url);
                // new GetDistance().execute(url);
            }
//                                            setDouble("500000");


    }

    );

    return view;
}

   /* @Override
    public void setDouble(String result) {
        String res[] = result.split(",");
        double dist = Integer.parseInt(res[0]) / 1000;
        mAvstandTextView.setText("Du reser " + dist + " kilometers");
        mSumTextView.setText(R.string.utslapp);


        mTagTextView.setText(String.format("Tåg %s", dist * 0.06));
        mFlygTextView.setText(String.format("Flyg %s", dist * 0.18));

        mBussTextView.setText(String.format("Buss %s", dist * 0.089));

        mBilTextView.setText(String.format("Bil %s", dist * 0.26));

    }
*/

}
