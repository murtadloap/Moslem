package com.lo.moslem.quran;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.lo.moslem.R;

import java.util.ArrayList;

public class QuranActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<QuranItems>>{

    ListView listView ;
    QuranAdapter adapter;
    ArrayList<QuranItems> quran;
    static final String EXTRAS_QURAN = "EXTRAS_QURAN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        adapter = new QuranAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        quran = new ArrayList<>();

        Bundle bundle = new Bundle();

        getSupportLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<QuranItems>> onCreateLoader(int id, Bundle args) {
        String quranList = "";
        if (args != null){
            quranList = args.getString(EXTRAS_QURAN);
        }

        return new MyAsyncTaskLoader(this,quranList);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<QuranItems>> loader, ArrayList<QuranItems> data) {
        quran.addAll(data);
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<QuranItems>> loader) {
        adapter.setData(null);
    }
}
