package com.android.bignerdranc.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by YamadouTraore on 7/2/15.
 */
public class CrimeLab {

    private ArrayList<Crime> mCrimes;
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private static final String FILENAME = "crimes.json";
    private CriminalIntentJSONSerializer mSerializer;


    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);

        try{
            mCrimes = mSerializer.loadCrimes();

        }catch (Exception e){
            mCrimes = new ArrayList<Crime>();
        }

    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }

    public static CrimeLab get(Context c) {
        if(sCrimeLab == null)
            sCrimeLab = new CrimeLab(c.getApplicationContext());

        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {

        for(int i = 0; i < mCrimes.size(); i++){
            if(mCrimes.get(i).getID().equals(id))
                return  mCrimes.get(i);
        }
        return null;
    }

    public void deleteCrime(Crime c) {
        mCrimes.remove(c);
    }

    public boolean saveCrimes(){

        try{
            mSerializer.saveCrimes(mCrimes);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
