package com.example.appforbrac.chatApp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.appforbrac.Adapter.addedCourseAdapter;
import com.example.appforbrac.Model.Student;
import com.example.appforbrac.Model.course;
import com.example.appforbrac.R;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appforbrac.chatApp.Adapter.chatAdapter;
import com.example.appforbrac.chatApp.Adapter.peopleAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Chats_Fragments extends Fragment {


    private RecyclerView recyclerView;
    private chatAdapter stdAdapter;
    private List<Student> stdList;
    private FirebaseUser firebaseUser;
    private DatabaseReference Rootref;
    private String myStr;

    public Chats_Fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chats__fragments,container,false);

        recyclerView = view.findViewById(R.id.std_list);

        myStr = getArguments().getString("name");
        Rootref = FirebaseDatabase.getInstance().getReference();


        stdList = new ArrayList<>();

        readStd();
        stdAdapter= new chatAdapter( getContext(), stdList,myStr);
        recyclerView.setAdapter(stdAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    private void readStd() {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child("Students").child(userid).child("Courses").child(myStr).child("list");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                stdList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Student c = snapshot.getValue(Student.class);
                    stdList.add(c);

                }

                stdAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
