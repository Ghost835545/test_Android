package com.example.ivan.loginapp.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.activity.ResultsOfSelectedTestActivity;
import com.example.ivan.loginapp.entity.ResultTest;

import java.text.SimpleDateFormat;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ResultTestsViewHolder>{
    public List<ResultTest> resultTests;
    public RVAdapter(List<ResultTest> resultTests){
        this.resultTests = resultTests;
        this.plusHours();
    }
    public class ResultTestsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView titleTest;
        TextView markTest;
        TextView dateTesting;
        ResultTestsViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            titleTest = (TextView) itemView.findViewById(R.id.title_result_test);
            markTest = (TextView) itemView.findViewById(R.id.mark_test);
            dateTesting = (TextView) itemView.findViewById(R.id.date_testing);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Variables.setTestName(resultTests.get(getAdapterPosition()).getTest().getTestName());
            Variables.setIdTest(resultTests.get(getAdapterPosition()).getTest().getIdTest());
            Variables.setIdResult(resultTests.get(getAdapterPosition()).getIdResult());
            Variables.setMARK(resultTests.get(getAdapterPosition()).getMark());
            Variables.setClockEnd(false);
            Context context = view.getContext();
            Intent intent = new Intent(context, ResultsOfSelectedTestActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public int getItemCount(){
        return resultTests.size();
    }
    @Override
    public ResultTestsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_result_tests, viewGroup, false);
        ResultTestsViewHolder pvh = new ResultTestsViewHolder(v);
        return pvh;

    }
    @Override
    public void onBindViewHolder(ResultTestsViewHolder resultTestsViewHolder, int i) {
        resultTestsViewHolder.titleTest.setText(resultTests.get(i).getTest().getTestName());
        resultTestsViewHolder.markTest.setText(resultTests.get(i).getMark().toString());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'в' HH:mm:ss");
        resultTestsViewHolder.dateTesting.setText(dateFormat.format(resultTests.get(i).getDateBegin()));
    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }
    public void plusHours()
    {
        for (int i = 0; i<resultTests.size(); i++)
            resultTests.get(i).getDateBegin().setHours(resultTests.get(i).getDateBegin().getHours()+12);
    }
}
