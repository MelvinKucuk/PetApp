package com.melvin.flux.controller;


import android.content.Context;

import com.melvin.flux.dao.DaoPet;
import com.melvin.flux.model.Pet;
import com.melvin.flux.util.ResultListener;
import com.melvin.flux.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ControllerPet {

    public void getPets(Context context, final ResultListener<List<Pet>> listenerView){

        if (Utils.isOnline(context)){
            new DaoPet().getPets(context, new ResultListener<List<Pet>>() {
                @Override
                public void finish(List<Pet> resultado) {

                    // Filtro los de nombre null

                    List<Pet> aux = new ArrayList<>();
                    aux.addAll(resultado);
                    resultado.clear();
                    for (Pet element : aux)
                        if (element.getName() != null)
                            resultado.add(element);

                    listenerView.finish(resultado);
                }
            });
        }
    }
}
