package com.melvin.flux.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.melvin.flux.model.Pet;
import com.melvin.flux.model.Pets;
import com.melvin.flux.util.ResultListener;

import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoPet extends DaoHelper {

    private static final String BASE_URL = "http://petstore.swagger.io/v2/";

    private ServicePet servicePet;

    public DaoPet() {
        super(BASE_URL);
        servicePet = retrofit.create(ServicePet.class);
    }

    public void getPets(final Context context, final ResultListener<List<Pet>> listenerController) {
        servicePet.getAllPets("available").enqueue(new Callback<Pets>() {
            @Override
            public void onResponse(Call<Pets> call, Response<Pets> response) {
                Pets container = response.body();
                List<Pet> data = container.getPets();
                listenerController.finish(data);
                Toast.makeText(context, "Exito", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Pets> call, Throwable t) {
                Toast.makeText(context, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPetById(final ResultListener<Pet> listenerController, String id) {
        servicePet.getPetById(id).enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                Pet data = response.body();
                listenerController.finish(data);
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {

            }
        });
    }
}
