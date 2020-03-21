package com.example.appforbrac.chatApp.fragments;

import android.app.Activity;
import android.content.Intent;
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

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * A simple {@link Fragment} subclass.
 */
public class People_Fragments extends Fragment {

    private RecyclerView recyclerView;
    private peopleAdapter stdAdapter;
    private List<Student> stdList;
    private FirebaseUser firebaseUser;
    private DatabaseReference Rootref;
    private String myStr;
    public People_Fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_added_course,container,false);

        recyclerView = view.findViewById(R.id.rec2);

            myStr = getArguments().getString("name");
         Rootref = FirebaseDatabase.getInstance().getReference();


        stdList = new ArrayList<>();

        test();
        readStd();
        stdAdapter= new peopleAdapter(  getContext(), stdList,myStr);
        recyclerView.setAdapter(stdAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    private void test() {

        HashMap<String, Object> profileMap = new HashMap<>();
        //profileMap.put("name", setUserName);
        profileMap.put("status", myStr);
        Rootref.child("frag").updateChildren(profileMap);
    }

    private void readStd() {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = firebaseUser.getUid();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Courses").child(myStr).child("StudentList");

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
