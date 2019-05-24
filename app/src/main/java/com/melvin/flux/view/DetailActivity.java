package com.melvin.flux.view;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.melvin.flux.R;

public class DetailActivity extends AppCompatActivity implements MapFragment.OnFragmentInteractionListener {

    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MapFragment fragment = new MapFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
