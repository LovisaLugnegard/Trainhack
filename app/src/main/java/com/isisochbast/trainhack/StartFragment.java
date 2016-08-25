package com.isisochbast.trainhack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Lovisa on 2016-08-25.
 */
public class StartFragment extends Fragment implements GetDistance.Geo{

    private EditText mStartEditStext;
    private EditText mSlutEditText;
    private Button mBerakna;
    private String mStartDest;
    private String mSlutDest;
    private TextView mAvstancdTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        mStartEditStext = (EditText) view.findViewById(R.id.start_editText);
        mSlutEditText = (EditText) view.findViewById(R.id.slut_editText);
        mBerakna = (Button) view.findViewById(R.id.berakna_button);

        mBerakna.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mStartDest = mStartEditStext.getText().toString();
                mSlutDest = mSlutEditText.getText().toString();


                String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + mStartDest + "&destinations=" + mSlutDest + "&mode=driving&language=fr-FR&avoid=tolls&key=AIzaSyCCFP1Zdu51zwsF1x7mRlkRwYbxTwuqvdo";
                new GetDistance(StartFragment.this).execute(url);
            }

        }

        );

        return view;
    }

    @Override
    public void setDouble(String result) {
        String res[]=result.split(",");
        int dist=Integer.parseInt(res[1])/1000;
        mAvstancdTextView.setText("Distance= " + dist + " kilometers");

    }



}
