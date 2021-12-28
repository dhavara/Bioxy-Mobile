package com.keld.bioxy.view.MainView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.keld.bioxy.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    NavHostFragment navHostFragment;
    NavController navController;
    Toolbar toolbar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar_main = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);
        navigationView = findViewById(R.id.bottom_nav_menu);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);

        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.difficultyFragment2, R.id.profileFragment, R.id.leaderboardFragment2, R.id.shopFragment2).build();
        navController = navHostFragment.getNavController();
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.difficultyFragment2 || destination.getId() == R.id.leaderboardFragment2 || destination.getId() == R.id.profileFragment || destination.getId() == R.id.shopFragment2 ){
                navigationView.setVisibility(View.VISIBLE);
                getSupportActionBar().show();
            }else {
                navigationView.setVisibility(View.GONE);
            }
        });

        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, configuration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (Openable) null);
    }
}