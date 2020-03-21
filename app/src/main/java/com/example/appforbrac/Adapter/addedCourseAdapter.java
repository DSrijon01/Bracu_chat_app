package com.example.appforbrac.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appforbrac.Model.Student;
import com.example.appforbrac.Model.course;
import com.example.appforbrac.R;
import com.example.appforbrac.chatApp.main;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class addedCourseAdapter extends RecyclerView.Adapter<addedCourseAdapter.ViewHolder>{

    public Context mContext;
    public List<course> mCourse;
    int count=0;
    int stdCount=0;
    String test="Nope";
    Student student;
    private FirebaseUser firebaseUser;
    public Bundle bundle = new Bundle();

    DatabaseReference reference;
    public addedCourseAdapter(Context mContext, List<course> mCourse)
    {
        this.mContext=mContext;
        this.mCourse= mCourse;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_courses, viewGroup,false);
        addedCourseAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull addedCourseAdapter.ViewHolder viewHolder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final course course = mCourse.get(position);

        TextView textView = viewHolder.txt;
        textView.setText(course.getName());
        ImageView imageView = viewHolder.img;
        test = course.getName();
        Button button = viewHolder.btn;

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test1(course.getName());
            }
        });

    }
    private void test1(String n) {

        String name = n;

        String userid = firebaseUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("test");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("test",name);
        reference.setValue(hashMap);


       Intent i = new Intent(mContext, main.class );
        i.putExtra("name",name);
        mContext.startActivity(i);

       // bundle.putString("name", name);
        //People_Fragments myFrag = new People_Fragments();
        //myFrag.setArguments(bundle);



    }


    @Override
    public int getItemCount() {
        return mCourse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public TextView txt;
        public Button btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageView);
            txt= itemView.findViewById(R.id.textView);
            btn = itemView.findViewById(R.id.button);

            btn.setVisibility(itemView.GONE);


        }


    }
}
