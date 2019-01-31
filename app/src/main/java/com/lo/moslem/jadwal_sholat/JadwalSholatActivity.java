package com.lo.moslem.jadwal_sholat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.lo.moslem.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalSholatActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, View.OnClickListener {

    ProgressDialog pd;
    List<Jadwal> jadwal;
    FloatingActionButton btnRefresh;
    SwipeRefreshLayout swipeId;
    String lokasi;
    Location location;
    String zuhur, ashar, magrib, isya, subuh, tanggal;
    TextView tvNamaDaerah, tvDzuhur, tvAshar, tvMaghrib, tvIsya, tvSubuh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sholat);

        btnRefresh = findViewById(R.id.btnRefresh);
        btnRefresh.setOnClickListener(this);
        tvNamaDaerah = findViewById(R.id.tvLokasialue);
        tvDzuhur = findViewById(R.id.tvDzhuhurValue);
        tvAshar = findViewById(R.id.tvAsharValue);
        tvMaghrib = findViewById(R.id.tvMaghribValue);
        tvIsya = findViewById(R.id.tvIshaValue);
        tvSubuh = findViewById(R.id.tvSubuhValue);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRefresh) {
            swipeId.setRefreshing(true);
            actionLoad();
        }
    }

    private void actionLoad() {

        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
            EasyPermissions.requestPermissions(JadwalSholatActivity.this, "Butuh Permission Location", 10, perms);
        } else {
            FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        // Do it all with location
                        Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                        // Display in Toast
                        Geocoder gcd3 = new Geocoder(getBaseContext(), Locale.getDefault());
                        List<Address> addresses;

                        try {
                            addresses = gcd3.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses.size() > 0)

                            {
                                //while(locTextView.getText().toString()=="Location") {
                                Log.d("Cek lokasi", "1 :" + addresses.get(0).getLocality().toString());
                                lokasi = addresses.get(0).getLocality().toString();

                                if (lokasi != null) {
                                    Log.d("location", "locatin :" + lokasi);

                                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                    Call<Items> call = apiInterface.getJadwalSholat(lokasi);
                                    call.enqueue(new Callback<Items>() {
                                        @Override
                                        public void onResponse(Call<Items> call, Response<Items> response) {
                                            Log.d("Data ", " respon" + response.body().getItems());
                                            jadwal = response.body().getItems();
                                            Log.d("respon data ", "" + new Gson().toJson(jadwal));

                                            if (jadwal != null) {
                                                zuhur = jadwal.get(0).getZuhur();
                                                ashar = jadwal.get(0).getAshar();
                                                magrib = jadwal.get(0).getMaghrib();
                                                isya = jadwal.get(0).getIsya();
                                                subuh = jadwal.get(0).getFajar();
                                                tanggal = jadwal.get(0).getTanggal();
                                                Log.d("respon :", "" + zuhur);
                                                tvDzuhur.setText(zuhur);
                                                tvAshar.setText(ashar);
                                                tvMaghrib.setText(magrib);
                                                tvIsya.setText(isya);
                                                tvSubuh.setText(subuh);

                                                Log.d("", "lokasi" + lokasi);
                                                tvNamaDaerah.setText(lokasi);
                                                swipeId.setRefreshing(false);
                                                pd.dismiss();
                                            } else {
                                                Toast.makeText(JadwalSholatActivity.this, "Error Network", Toast.LENGTH_SHORT).show();
                                                swipeId.setRefreshing(false);
                                            }
                                            //  loadData(jadwal);
                                        }

                                        @Override
                                        public void onFailure(Call<Items> call, Throwable t) {
                                            Log.d("Data ", "" + t.getMessage());
                                            swipeId.setRefreshing(false);
                                            pd.dismiss();
                                        }
                                    });

                                }
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
        }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == 10) {
            actionLoad();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

}
