package com.android.bignerdranc.criminalintent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;
import java.util.Date;

/**
 * Created by YamadouTraore on 6/30/15.
 */
public class Crime {

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE="title";
    private static final String JSON_SOLVED="solved";
    private static final String JSON_DATE="date";
    private static final String JSON_SUSPECT="suspect";

    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public Crime() {
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    public JSONObject toJSON() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(JSON_ID, mID.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, mDate.getTime() );
        json.put(JSON_SUSPECT, mSuspect);
        return json;
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }

    public Crime(JSONObject json) throws JSONException {
        mID = UUID.fromString(json.getString(JSON_ID));
        if(json.has(JSON_TITLE)) {
            mTitle = json.getString(JSON_TITLE);
        }
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
        if(json.has(JSON_SUSPECT)) {
            mSuspect = json.getString(JSON_SUSPECT);
        }
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    @Override
    public String toString() {return mTitle; }

    public Date getDate() {
        return mDate;

    }

    public void setDate(Date date) {
        mDate = date;
    }
}
