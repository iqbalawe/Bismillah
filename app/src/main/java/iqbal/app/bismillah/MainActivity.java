package iqbal.app.bismillah;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import iqbal.app.bismillah.fragment.HomeFragment;
import iqbal.app.bismillah.fragment.ProfileFragment;
import iqbal.app.bismillah.fragment.ReminderFragment;
import iqbal.app.bismillah.fragment.SearchFragment;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private FlipperLayout flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.btm_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        flipper = (FlipperLayout) findViewById(R.id.img_flipper);
        setLayout();

        //load fragment by default
        toolbar.setTitle("Home");
        loadFragment(new HomeFragment());
    }

    private void setLayout() {
        String url[] = new String[]{
                "https://sportaways.com/media/magiccart/magicslider/cache/900x455//p/r/promo_go_deals_slider_website.jpg",
                "https://sportaways.com/media/magiccart/magicslider/cache/900x455//p/e/pembayaran_indomaret_slider.jpg",
                "https://sportaways.com/media/magiccart/magicslider/cache/900x455//s/p/sportaways_go-pay_slider.jpg"
        };

        for (int i = 0; i < 3; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageUrl(url[i])
                    .setDescription("Image" + (i + 1));
            flipper.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    Toast.makeText(MainActivity.this, "" + (flipper.getCurrentPagePosition() + 1), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.home_menu:
                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.search_menu:
                    toolbar.setTitle("Search");
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.reminder_menu:
                    toolbar.setTitle("Reminder");
                    fragment = new ReminderFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.profile_menu:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
