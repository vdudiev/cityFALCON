package com.example.cityfalcon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (id) {
                    case R.id.nav_signal:
                        SignalFragment fragmentSignal = new SignalFragment();
                        fragmentTransaction.replace(R.id.content_fragment,fragmentSignal);
                        break;

                    case R.id.nav_watchlist:
                        WatchlistFragment fragmentWatchlist = new WatchlistFragment();
                        fragmentTransaction.replace(R.id.content_fragment,fragmentWatchlist);
                        break;

                    case R.id.nav_search:
                        SearchFragment fragmentSearch = new SearchFragment();
                        fragmentTransaction.replace(R.id.content_fragment,fragmentSearch);
                        break;

                    case R.id.nav_sectors:
                        SectorsFragment fragmentSectors = new SectorsFragment();
                        fragmentTransaction.replace(R.id.content_fragment,fragmentSectors);
                        break;

                    case R.id.nav_more:
                        MoreFragment fragmentMore = new MoreFragment();
                        fragmentTransaction.replace(R.id.content_fragment,fragmentMore);
                        break;
                }

                fragmentTransaction.commit();
                return true;
            }
        });

    }
}


