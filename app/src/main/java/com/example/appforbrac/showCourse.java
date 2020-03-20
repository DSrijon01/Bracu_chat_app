package com.example.appforbrac;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appforbrac.Adapter.courseAdapter;
import com.example.appforbrac.Model.Student;
import com.example.appforbrac.Model.course;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link showCourse.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link showCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class showCourse extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private courseAdapter courseAdapter;
    private List<course> courseList;
    private List<Student> stdList;
    private Student s;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public showCourse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment showCourse.
     */
    // TODO: Rename and change types and number of parameters
    public static showCourse newInstance(String param1, String param2) {
        showCourse fragment = new showCourse();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_course,container,false);

        recyclerView = view.findViewById(R.id.rec);
        Student tst = s;
        courseList = new ArrayList<>();
        readCourse();
        //readDet();
       // String string = Settings.Secure.getString( getContentResolver(), Settings.Secure.ANDROID_ID);
        courseAdapter= new courseAdapter(  getContext(), courseList,stdList,tst);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    private void readDet() {

       final String uid = auth.getCurrentUser().getUid();

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                s = dataSnapshot.child("Students").child(uid).getValue(Student.class);

               // reference.child("test").setValue(s.getFullname());

                courseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       // test();


    }
    private void test() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("test");
        //Student st = stdList.get(1);

        HashMap<String, Object> profileMap = new HashMap<>();
        //profileMap.put("name", setUserName);
        profileMap.put("status", courseList.get(0).getFaculty());
        ref.child("test").updateChildren(profileMap);
    }
    private void readCourse()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Courses");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                courseList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    course c = snapshot.getValue(course.class);
                    courseList.add(c);

                }

                courseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
