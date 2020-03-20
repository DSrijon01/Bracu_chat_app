package com.example.appforbrac.chatApp.Adapter;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class peopleAdapter extends RecyclerView.Adapter<peopleAdapter.ViewHolder> {
    public Context mContext;
    public List<Student> mStd;
    int count=0;
    int stdCount=0;
    String test="Nope";
    Student student;
    private FirebaseUser firebaseUser;
    DatabaseReference reference;

    public peopleAdapter(Context mContext, List<Student> mStd)
    {
        this.mContext=mContext;
        this.mStd= mStd;
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

        final Student std = mStd.get(position);

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

            btn.setVisibility(itemView.GONE);


        }


    }
}
