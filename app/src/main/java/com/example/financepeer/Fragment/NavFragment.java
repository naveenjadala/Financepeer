package com.example.financepeer.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.financepeer.R;
import com.example.financepeer.databinding.NavFragmentBinding;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import static android.content.Context.LOCATION_SERVICE;

public class NavFragment extends Fragment {

    private NavFragmentBinding binding;
    private static int REQUST_PERMISSION = 1001;
    private LocationManager service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.nav_fragment, container, false);

        service = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        binding.nav.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(getActivity().getApplication(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUST_PERMISSION);

            } else {
                if(service.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getCurrentLocation();
                } else {
                    Toast.makeText(getContext(), "Turn on GPS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUST_PERMISSION && grantResults.length >0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if(service.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getCurrentLocation();
                } else {
                    binding.showLong.setText("Turn on GPS");
                    Toast.makeText(getContext(), "Turn on GPS", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Proved Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(getContext()).requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                LocationServices.getFusedLocationProviderClient(getActivity()).removeLocationUpdates(this);

                if(locationResult != null && locationResult.getLocations().size() >0) {
                    int locactionIndex = locationResult.getLocations().size() -1;
                    double log = locationResult.getLocations().get(locactionIndex).getLongitude();
                    double lat = locationResult.getLocations().get(locactionIndex).getLatitude();
                    binding.showLong.setText(String.format("Latitude:%S\nLogitude: %s",lat, log));
                }

            }
        }, Looper.getMainLooper());

    }
}
