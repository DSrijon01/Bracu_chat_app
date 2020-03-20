package com.example.appforbrac.chatApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.appforbrac.R;





/**
 * A simple {@link Fragment} subclass.
 */
public class Chats_Fragments extends Fragment {

    public Chats_Fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats__fragments, container, false);
    }
}
