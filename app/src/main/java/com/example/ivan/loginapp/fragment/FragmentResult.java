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
import com.example.ivan.loginapp.util.RVAdapter;
import com.example.ivan.loginapp.util.Variables;
import com.example.ivan.loginapp.entity.ResultTest;
import com.example.ivan.loginapp.rest.Connection;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragmentResult extends Fragment {
    private List<ResultTest> mResultsTest;
    private RecyclerView rv;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        getActivity().setTitle("Результаты тестов");
        progressBar = (ProgressBar) view.findViewById(R.id.pr_fragment_result_tests);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        OutputResultTestsTask task = new OutputResultTestsTask();
        task.execute();
        return view;
    }


    private class OutputResultTestsTask extends AsyncTask<Void, Void, ResultTest[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ResultTest[] doInBackground(Void... params) {
            try {
                mResultsTest = new ArrayList<>();
                ResultTest[] resultTests = new Connection().getResultsTestByUser(Variables.getIdUser());
                mResultsTest.addAll(Arrays.asList(resultTests));
                return resultTests;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(ResultTest[] resultTests) {
            RVAdapter adapter = new RVAdapter(mResultsTest);
            rv.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }
}
