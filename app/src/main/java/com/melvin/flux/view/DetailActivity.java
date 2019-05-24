package com.melvin.flux.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.melvin.flux.R;

public class DetailActivity extends AppCompatActivity implements DetailFragment.OnFragmentInteractionListener {

    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String id = getIntent().getExtras().getString(KEY_ID);

        DetailFragment fragment = new DetailFragment();
        Bundle data = new Bundle();
        data.putString(DetailFragment.KEY_PETID, id);
        fragment.setArguments(data);

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment).commit();
    }

    @Override
    public void onMapClicked() {
        MapFragment fragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container, fragment).commit();
    }
}
