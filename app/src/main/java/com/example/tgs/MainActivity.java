package com.example.tgs;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MovieFragment movieFragment;
    private FavoriteFragment favoriteMovieFragment;
    private String title;
    private final String STATE_TITLE = "state_string";
    private final String STATE_MODE = "state_mode";


    private int mode = R.id.navigation_movie ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = getResources().getString(R.string.title_movie);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
        movieFragment = new MovieFragment();

        favoriteMovieFragment = new FavoriteFragment();

        if (savedInstanceState != null){
            mode = savedInstanceState.getInt(STATE_MODE);
            title = savedInstanceState.getString(STATE_TITLE);

            if (mode == R.id.navigation_movie){
                setToMovieFragment();
            } else if (mode == R.id.navigation_favorite_movie){
                setToFavoriteMovieFragment();
            }
        } else {
            setToMovieFragment();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_MODE, mode);
        outState.putString(STATE_TITLE, title);
    }

    private void setToMovieFragment() {
        setTitle(title);

        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());

        if (!(fragment instanceof MovieFragment)) {
            fragmentTransaction.replace(
                    R.id.linear_layout_container,
                    movieFragment,
                    MovieFragment.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }





    private void setToFavoriteMovieFragment() {
        setTitle(title);

        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(FavoriteFragment.class.getSimpleName());

        if (!(fragment instanceof FavoriteFragment)) {
            fragmentTransaction.replace(
                    R.id.linear_layout_container,
                    favoriteMovieFragment,
                    FavoriteFragment.class.getSimpleName()
            );
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    title = getResources().getString(R.string.title_movie);
                    mode = item.getItemId();

                    setToMovieFragment();
                    return true;

                case R.id.navigation_favorite_movie:
                    title = getResources().getString(R.string.title_favorite_movie);
                    mode = item.getItemId();

                    setToFavoriteMovieFragment();
                    return true;

            }
            return false;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
