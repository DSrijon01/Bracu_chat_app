package com.example.appforbrac.chatApp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appforbrac.Adapter.addedCourseAdapter;
import com.example.appforbrac.Model.Student;
import com.example.appforbrac.Model.course;
import com.example.appforbrac.R;
import com.example.appforbrac.chatApp.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class peopleAdapter extends RecyclerView.Adapter<peopleAdapter.ViewHolder> {
    public Context mContext;
    public List<Student> mStd;
    int count=0;
    int stdCount=0;
    String myStr;
    Student student;
    private FirebaseUser firebaseUser;
    DatabaseReference reference;
    DatabaseReference users;
    FirebaseAuth auth;

    public peopleAdapter(Context mContext, List<Student> mStd, String myStr)
    {
        this.mContext=mContext;
        this.mStd= mStd;
        this.myStr=myStr;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_courses, viewGroup,false);
        peopleAdapter.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull peopleAdapter.ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       auth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = auth.getCurrentUser();
        String userid = firebaseUser.getUid();
        final Student std = mStd.get(position);

        TextView textView = holder.txt;
        textView.setText(std.getFullname());

       String id = std.getId();

        if(userid.equalsIgnoreCase(id))
        {
            holder.itemView.setVisibility(View.GONE);
        }
        users = FirebaseDatabase.getInstance().getReference().child("Users").child("Students");








        Button button = holder.btn;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uId = firebaseUser.getUid();
                reference =  FirebaseDatabase.getInstance().getReference().child("Users").child("Students").child(uId).child("Courses").child(myStr).child("list").child(std.getId());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("id",std.getId());
                hashMap.put("fullname",std.getFullname());
                reference.updateChildren(hashMap);
                goToChat(std);
            }
        });

    }

    private void goToChat(Student std) {
        Intent intent = new Intent(mContext,  ChatActivity.class);
        intent.putExtra("visit_user_id", std.getId());
        intent.putExtra("visit_user_name", std.getFullname());
        intent.putExtra("myStr", myStr);
        //intent.putExtra("visit_image", retImage[0]);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mStd.size();
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

            //btn.setVisibility(itemView.GONE);

            //itemView.setVisibility(View.GONE);

        }


    }
}
