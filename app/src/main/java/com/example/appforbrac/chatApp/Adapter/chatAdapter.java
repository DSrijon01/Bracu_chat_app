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

import com.example.appforbrac.Model.Student;
import com.example.appforbrac.R;
import com.example.appforbrac.chatApp.ChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class chatAdapter  extends RecyclerView.Adapter<chatAdapter.ViewHolder>{

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
    public chatAdapter(Context context, List<Student> mStd, String myStr) {
        this.mContext=context;
        this.mStd= mStd;
        this.myStr=myStr;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_courses, viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        String userid = firebaseUser.getUid();
        final Student std = mStd.get(position);

        TextView textView = holder.txt;
        textView.setText(std.getFullname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChatActivity.class);
                intent.putExtra("userid", std.getId());
                mContext.startActivity(intent);
            }
        });

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
