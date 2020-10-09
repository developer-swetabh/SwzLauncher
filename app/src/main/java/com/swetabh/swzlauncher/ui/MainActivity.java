package com.swetabh.swzlauncher.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.swetabh.launchersdk.AppInfo;
import com.swetabh.launchersdk.LauncherUtils;
import com.swetabh.swzlauncher.R;
import com.swetabh.swzlauncher.adapter.AppsAdapter;


import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private List<AppInfo> mAppList;
    private RecyclerView mAppListView;
    private SearchView mAppSearchView;
    private AppsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAppListView = findViewById(R.id.app_list);
        mAppSearchView = findViewById(R.id.app_search_view);
        mAppListView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        mAppListView.setHasFixedSize(true);

        new AppListTask().execute();

        mAppSearchView.setOnQueryTextListener(this);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        mAdapter.filter(text);
        return false;
    }

    public class AppListTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            mAppList = LauncherUtils.getAppsList(MainActivity.this);
            if (mAppList.size() > 0)
                return LauncherUtils.SUCCESS;
            else return LauncherUtils.FAILURE;
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            Log.d(TAG, "AppListTask : onPostExecute, result = " + i);
            if (i == LauncherUtils.SUCCESS) {
                mAdapter = new AppsAdapter(MainActivity.this, mAppList);
                mAppListView.setAdapter(mAdapter);
                Log.d(TAG, "AppListTask : onPostExecute, Adapter set for recycler view");
            }
        }
    }
}
