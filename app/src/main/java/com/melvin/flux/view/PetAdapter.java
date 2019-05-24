package com.melvin.flux.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melvin.flux.R;
import com.melvin.flux.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetAdapter extends RecyclerView.Adapter {

    private List<Pet> data;
    private List<Pet> dataCopy = new ArrayList<>();
    private PetAdapterInteface listener;

    public PetAdapter(List<Pet> data, PetAdapterInteface listener) {
        this.data = data;
        this.listener = listener;
        this.dataCopy.addAll(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_pet, viewGroup, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((PetViewHolder) viewHolder).loadData(data.get(i));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class PetViewHolder extends RecyclerView.ViewHolder {

        private TextView textName;

        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_pet);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(data.get(getAdapterPosition()).getId());
                }
            });
        }

        public void loadData(Pet pet) {
            textName.setText(pet.getName());
        }
    }

    public interface PetAdapterInteface {
        void onItemClicked(String id);
    }

    public void setData(List<Pet> data) {
        this.data = data;
        dataCopy = new ArrayList<>();
        dataCopy.addAll(this.data);
        notifyDataSetChanged();
    }

    public void search(String text) {
        data.clear();
        if (text.isEmpty())
            data.addAll(dataCopy);
        else {
            text = text.toLowerCase();
            for (Pet element : dataCopy) {
                if (element.getName() != null)
                    if (element.getName().toLowerCase().contains(text))
                        data.add(element);
            }
        }
        notifyDataSetChanged();
    }

    public void orderAsc(){
        Pet aux;

        for (int i = 0; i <data.size(); i++){
            for (int j = i+1; j< data.size(); j++){
                if (data.get(i).getName() != null && data.get(j).getName() != null) {
                    if (data.get(i).getName().toLowerCase().compareTo(data.get(j).getName().toLowerCase()) > 0) {
                        aux = data.get(i);
                        data.set(i, data.get(j));
                        data.set(j, aux);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void orderDec(){
        Pet aux;

        for (int i = 0; i <data.size(); i++){
            for (int j = i+1; j< data.size(); j++){
                if (data.get(i).getName() != null && data.get(j).getName() != null) {
                    if (data.get(i).getName().toLowerCase().compareTo(data.get(j).getName().toLowerCase()) < 0) {
                        aux = data.get(i);
                        data.set(i, data.get(j));
                        data.set(j, aux);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}
