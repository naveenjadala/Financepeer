package com.example.financepeer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.financepeer.Fragment.CamFragment;
import com.example.financepeer.Fragment.HomeFragment;
import com.example.financepeer.Fragment.ImageFragment;
import com.example.financepeer.Fragment.NavFragment;
import com.example.financepeer.Fragment.ServiceFragment;
import com.example.financepeer.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.navigation.setOnNavigationItemSelectedListener(navigation);

        //setting default as home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
    }

    // to add fragment to bottom navigation
    BottomNavigationView.OnNavigationItemSelectedListener navigation = item -> {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.cam:
                fragment = new CamFragment();
                break;
            case R.id.images:
                fragment = new ImageFragment();
                break;
            case R.id.nav:
                fragment = new NavFragment();
                break;
            case R.id.service:
                fragment = new ServiceFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
        return true;
    };
}
