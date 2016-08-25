package com.isisochbast.trainhack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends FragmentActivity {

    final String TAG = "test";

    private Fragment createFragment() {
        return new StartFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_holder);

        //Hantera fragment
        FragmentManager fm = getSupportFragmentManager();
        //fragment_container är namnet på frameLayout i fragment_holder.xmll
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //Om det inte finns något fragment än (första gången onCreate körs), hämta nytt StartFragment
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();

        }

    }
}
