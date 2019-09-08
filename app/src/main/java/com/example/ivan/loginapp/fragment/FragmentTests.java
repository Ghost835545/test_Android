package com.example.ivan.loginapp.fragment;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.*;

import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ivan.loginapp.util.Disciplines;
import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.util.Variables;
import com.example.ivan.loginapp.activity.TestActivity;
import com.example.ivan.loginapp.entity.Subject;
import com.example.ivan.loginapp.entity.Test;
import com.example.ivan.loginapp.rest.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FragmentTests extends Fragment {

    public ProgressBar progressBar;
    private Set<Subject> mSubjects;
    public ArrayList<Test> mTests;
    private Subject[] subjects;
    private Test[] tests;
    private HashMap<Integer, List<Test>> mTestBySubject;
    private MyExpandableListAdapter adapter;
    private ExpandableListView listView;
    public static List<Test> selectedTest;

    public SparseArray<Disciplines> disciplinesSparseArray = new SparseArray<Disciplines>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_tests, container, false);
        if (Variables.getIdRole()==3) {
            getActivity().setTitle("Список Тестов");
        }
        else if (Variables.getIdRole()==2) {
            getActivity().setTitle("Составленные тесты ");
        }
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_progress_tests);
        mSubjects = new HashSet<>();
        OutputSubjectTask task = new OutputSubjectTask();
        task.execute();
        listView = (ExpandableListView) view.findViewById(R.id.listView);
        return view;
    }

    public void createdData() {
        List<Subject> mDiscip = new ArrayList<Subject>(mSubjects);
        for (int i = 0; i < mSubjects.size(); i++) {
            Disciplines disc = new Disciplines(mDiscip.get(i).getSubjectName());
            for (int j = 0; j < tests.length; j++) {
                if (mDiscip.get(i).getIdSubject() == tests[j].getSubject().getIdSubject()) {
                    disc.children.add(tests[j].getTestName());
                }
            }

            disciplinesSparseArray.append(i, disc);
        }

        adapter = new MyExpandableListAdapter(this, disciplinesSparseArray, tests);
        listView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }
    private class OutputSubjectTask extends AsyncTask<Void, Void, Subject[]> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Subject[] doInBackground(Void... params) {
            try {
                if (Variables.getIdRole()==3) {
                    subjects = new Connection().getSubjects_By_Direction(Variables.getIdDirection());
                }
                else if (Variables.getIdRole()==2) {
                    subjects = new Connection().getSubjects_By_id_user(Variables.getIdUser());
                }
                for (Subject s : subjects)
                    mSubjects.add(s);
                return subjects;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Subject[] subject) {
            OutputTestTask task = new OutputTestTask();
            task.execute();


        }
    }

    private class OutputTestTask extends AsyncTask<Void, Void, Test[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Test[] doInBackground(Void... params) {
            try {
                tests = new Connection().getTests();
                for (Test t: tests)
                   t.getTimer().setHours(0);//13 emulator
                return tests;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Test[] tests) {

            createdData();
        }

    }
    private class MyExpandableListAdapter extends BaseExpandableListAdapter {

        private final SparseArray<Disciplines> disciplinesSparseArray;
        public LayoutInflater inflater;
        public FragmentTests activity;
        private Test[] tests;

        public Test[] getTests() {
            return tests;
        }

        public void setTests(Test[] tests) {
            this.tests = tests;
        }

        public MyExpandableListAdapter(FragmentTests act, SparseArray<Disciplines> disciplinesSparseArray1, Test[] tests1) {
            activity = act;
            this.disciplinesSparseArray = disciplinesSparseArray1;
            inflater = act.getLayoutInflater();
            tests = tests1.clone();
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return disciplinesSparseArray.get(groupPosition).children.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            final String children = (String) getChild(groupPosition, childPosition);
            TextView text = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listrow_details, null);
            }
            text = (TextView) convertView.findViewById(R.id.textView1);
            text.setText(children);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedTest = new ArrayList<>();
                    for (int i = 0; i<tests.length;i++){
                        if (tests[i].getTestName()==children.toString())
                        {
                            selectedTest.addAll(Arrays.asList(tests[i]));
                            Variables.setTestName(tests[i].getTestName());
                            break;
                        }
                    }
                    Intent intent = new Intent(getActivity(), TestActivity.class);
                    startActivity(intent);
                }

            });
            return convertView;
        }


        @Override
        public int getChildrenCount(int groupPosition) {
            return disciplinesSparseArray.get(groupPosition).children.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return disciplinesSparseArray.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return disciplinesSparseArray.size();
        }

        @Override
        public void onGroupCollapsed(int groupPosition) {
            super.onGroupCollapsed(groupPosition);
        }

        @Override
        public void onGroupExpanded(int groupPosition) {
            super.onGroupExpanded(groupPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.listrow_group, null);
            }
            Disciplines group = (Disciplines) getGroup(groupPosition);
            ((CheckedTextView) convertView).setText(group.string);
            ((CheckedTextView) convertView).setChecked(isExpanded);
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }


}