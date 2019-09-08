package com.example.ivan.loginapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.util.RVAdapterStudent;
import com.example.ivan.loginapp.entity.ResultTest;
import com.example.ivan.loginapp.entity.User;
import com.example.ivan.loginapp.rest.Connection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FragmentRating extends Fragment {
    private List<User> mStudents;
    private int studentMark;
    private RecyclerView rating;
    private HashMap<String, Float> mRating;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        mRating = new HashMap<>();
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        getActivity().setTitle("Рейтинг");
        progressBar = (ProgressBar) view.findViewById(R.id.pr_rating);
        rating = (RecyclerView) view.findViewById(R.id.rv_students);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rating.setLayoutManager(llm);
        OutputResultTestsTask task = new OutputResultTestsTask();
        task.execute();
        return view;
    }


    private class OutputResultTestsTask extends AsyncTask<Void, Void, User[]> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected User[] doInBackground(Void... params) {
            try {
                mStudents = new ArrayList<>();
                User[] students = new Connection().getStudents();
                mStudents.addAll(Arrays.asList(students));
                return students;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(User[] students) {

            OutputStudentsResultTestsTask task = new OutputStudentsResultTestsTask();
            task.execute();

        }
    }

    private class OutputStudentsResultTestsTask extends AsyncTask<Void, Void, ResultTest[]> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected ResultTest[] doInBackground(Void... params) {
            studentMark = 0;
            try {
                for (int i = 0; i<mStudents.size(); i++)
                {
                    ResultTest[] resultTests = new Connection().getResultsTestByUser(mStudents.get(i).getIdUser());
                    for (int j = 0; j<resultTests.length; j++)
                    {

                            studentMark = studentMark + resultTests[j].getMark().intValue();

                    }
                    if (studentMark == 0){
                        mStudents.get(i).setRating(0);
                    }
                    else {
                        mStudents.get(i).setRating(studentMark / resultTests.length);
                        studentMark = 0;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return  null;
        }
        protected void onPostExecute(ResultTest[] students) {
            Collections.sort(mStudents, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {

                    return Float.compare(o2.getRating(),o1.getRating());
                }
            });
            RVAdapterStudent adapter = new RVAdapterStudent(mStudents);
            rating.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }
}
