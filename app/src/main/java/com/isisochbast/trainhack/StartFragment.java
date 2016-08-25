package com.isisochbast.trainhack;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Lovisa on 2016-08-25.
 */
public class StartFragment extends Fragment {

    private EditText mStartEditStext;
    private EditText mSlutEditText;
    private Button mBerakna;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        mStartEditStext = (EditText) view.findViewById(R.id.start_editText);
        mSlutEditText = (EditText) view.findViewById(R.id.slut_editText);
        mBerakna = (Button) view.findViewById(R.id.berakna_button);

        return view;
    }
}
