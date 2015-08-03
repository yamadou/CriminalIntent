package com.android.bignerdranc.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

;



/**
 * Created by YamadouTraore on 7/2/15.
 */
public abstract class SingleFragmentActivity extends ActionBarActivity {

    protected abstract Fragment createFragment();

     @Override
    public void onCreate(Bundle savedInsanceState) {
         super.onCreate(savedInsanceState);
         setContentView(R.layout.activity_fragment);

         FragmentManager fm = getSupportFragmentManager();
         Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

         if(fragment == null){
             fragment = createFragment();
             fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
         }
     }
}
