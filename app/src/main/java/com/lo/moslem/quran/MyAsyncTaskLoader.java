package com.lo.moslem.quran;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<QuranItems>> {

    private ArrayList<QuranItems> mData;
    private boolean mHasResult = false;

    private String mQuranList;

    public MyAsyncTaskLoader(final Context context, String quranList) {
        super(context);

        onContentChanged();
        this.mQuranList = quranList;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    @Override
    public void deliverResult(ArrayList<QuranItems> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    @Nullable
    @Override
    public ArrayList<QuranItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<QuranItems> quranItems = new ArrayList<>();
        String url = "http://staging.quran.com:3000/api/v3/chapters";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("chapters");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject quran = results.getJSONObject(i);
                        QuranItems items = new QuranItems(quran);
                        quranItems.add(items);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Jika response gagal maka, do nothing
            }

        });

        return quranItems;
    }

    private void onReleaseResources(ArrayList<QuranItems> mData) {
    }
}

