package com.example.appforbrac.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.appforbrac.Model.Student;
import com.example.appforbrac.Model.course;
import com.example.appforbrac.R;
import com.example.appforbrac.fragments.MyDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.List;
import com.google.android.material.tabs.TabLayout;

import static android.content.Context.MODE_PRIVATE;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.ViewHolder>  {

    public Context mContext;
    public List<course> mCourse;
    int count=0;
    int stdCount=0;
    String test;
    List<Student> student;
     Student sdet;
     String name;
    private FirebaseUser firebaseUser;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference;
    public courseAdapter(Context mContext, List<course> mCourse, List<Student> std, Student s)
    {
        this.mContext=mContext;
        this.mCourse= mCourse;
        //this.student=std;
        //this.s=s;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_courses, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        readDet();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final course course = mCourse.get(position);
       // final Student std = this.s;
        TextView textView = viewHolder.txt;
        textView.setText(course.getName());
        ImageView imageView = viewHolder.img;

        Button button = viewHolder.btn;
       final HashMap<String, Object> hashMap = new HashMap<>();

        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userid = firebaseUser.getUid();
                reference = FirebaseDatabase.getInstance().getReference().child("Users").child("Students").child(userid).child("Courses").child(course.getName());
                hashMap.put("faculty",course.getFaculty());
                hashMap.put("name",course.getName());
                hashMap.put("key",course.getKey());
                reference.setValue(hashMap);
                count++;








                reference = FirebaseDatabase.getInstance().getReference().child("Courses").child(course.getName()).child("StudentList").child(String.valueOf(userid));

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("fullname",sdet.getFullname());
                reference.setValue(hashMap);
                //stdCount++;
            }
        });
    }

    private void readDet() {

        final String uid = auth.getCurrentUser().getUid();

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                sdet = dataSnapshot.child("Students").child(uid).getValue(Student.class);

               // reference.child("testtis").setValue(sdet.getFullname());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        // test();

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


        }
    }
}
