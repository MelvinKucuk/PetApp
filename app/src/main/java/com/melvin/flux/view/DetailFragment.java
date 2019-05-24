package com.melvin.flux.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.melvin.flux.R;
import com.melvin.flux.controller.ControllerPet;
import com.melvin.flux.model.Pet;
import com.melvin.flux.util.ResultListener;

public class DetailFragment extends Fragment {

    public static final String KEY_PETID = "petid";

    private OnFragmentInteractionListener mListener;
    private String id;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View roottView = inflater.inflate(R.layout.fragment_detail, container, false);

        try {
            id = getArguments().getString(KEY_PETID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final TextView textName = roottView.findViewById(R.id.text_pet_detail);
        ImageView mapImage = roottView.findViewById(R.id.map_image);

        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMapClicked();
            }
        });

        if (id != null) {
            new ControllerPet().getPetById(getContext(), new ResultListener<Pet>() {
                @Override
                public void finish(Pet resultado) {
                    textName.setText(resultado.getName());
                }
            }, id);
        }

        return roottView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onMapClicked();
    }
}
