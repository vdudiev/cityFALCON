package com.tradestock.cityfalcom.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.tradestock.cityfalcom.Fragments.MoreFragment;
import com.tradestock.cityfalcom.Fragments.SearchFragment;
import com.tradestock.cityfalcom.Fragments.SectorsFragment;
import com.tradestock.cityfalcom.Fragments.WatchlistFragment;
import com.tradestock.cityfalcom.Fragments.LiveTradingResultsBottomSheet;
import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.Fragments.SignalFragment;
import com.tradestock.cityfalcom.Fragments.TermsConditionsBottomSheet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    private final String SAVE_ANSWER = "SAVE_ANSWER";
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sPref = getPreferences(MODE_PRIVATE);
        String answer =  sPref.getString(SAVE_ANSWER,"");
        if (!answer.equals("agree"))
        {
            TermsConditionsBottomSheet termsConditionsBottomSheet = new TermsConditionsBottomSheet();
            termsConditionsBottomSheet.show(getSupportFragmentManager(), "termsConditionsBottomSheet");
        }
        else
        {
            LiveTradingResultsBottomSheet liveTradingResultsBottomSheet  = new LiveTradingResultsBottomSheet();
            liveTradingResultsBottomSheet.show(getSupportFragmentManager(),"liveTradingResultsBottomSheet");
        }


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




    public void saveAnswerForTermsCont(String saveAnswerForGet) {
        sPref =  getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor =  sPref.edit();
        editor.putString(SAVE_ANSWER,saveAnswerForGet);
        editor.commit();
    }


}


