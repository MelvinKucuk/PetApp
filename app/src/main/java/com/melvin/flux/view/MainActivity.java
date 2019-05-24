package com.melvin.flux.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.melvin.flux.R;
import com.melvin.flux.controller.ControllerPet;
import com.melvin.flux.model.Pet;
import com.melvin.flux.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PetAdapter.PetAdapterInteface,
        SearchView.OnQueryTextListener {

    private List<Pet> data = new ArrayList<>();
    private PetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler_main);
        final SwipeRefreshLayout swipeRefresh = findViewById(R.id.swipeRefresh_main);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new PetAdapter(data, this);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);

        swipeRefresh.setRefreshing(true);
        new ControllerPet().getPets(this, new ResultListener<List<Pet>>() {
            @Override
            public void finish(List<Pet> resultado) {
                data = resultado;
                adapter.setData(data);
                swipeRefresh.setRefreshing(false);
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ControllerPet().getPets(MainActivity.this, new ResultListener<List<Pet>>() {
                    @Override
                    public void finish(List<Pet> resultado) {
                        data = resultado;
                        adapter.setData(data);
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filtro, menu);
        MenuItem item = menu.findItem(R.id.itemSearchMenu);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemAscMenu:
                adapter.orderAsc();
                break;
            case R.id.itemDescMenu:
                adapter.orderDec();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(String id) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        Bundle data = new Bundle();
        data.putString(DetailActivity.KEY_ID, id);
        intent.putExtras(data);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        adapter.search(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.search(s);
        return true;
    }
}
