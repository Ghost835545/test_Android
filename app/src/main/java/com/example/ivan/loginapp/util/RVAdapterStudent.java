package com.example.ivan.loginapp.util;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivan.loginapp.R;
import com.example.ivan.loginapp.entity.User;

import java.util.List;

public class RVAdapterStudent extends RecyclerView.Adapter<RVAdapterStudent.RatingStudentViewHolder>{
    public List<User> students;
    public RVAdapterStudent(List<User> students){
        this.students = students;
    }
    public class RatingStudentViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView fio;
        TextView mark_rating;
        TextView group;
        RatingStudentViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.vc);
            fio = (TextView) itemView.findViewById(R.id.fio_student);
            group = (TextView) itemView.findViewById(R.id.name_group);
            mark_rating = (TextView) itemView.findViewById(R.id.rating_student);
        }

    }

    @Override
    public int getItemCount(){
        return students.size();
    }
    @Override
    public RatingStudentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_rating_students, viewGroup, false);
        RatingStudentViewHolder pvh = new RatingStudentViewHolder(v);
        return pvh;

    }
    @Override
    public void onBindViewHolder(RatingStudentViewHolder ratingStudentViewHolder, int i) {
        ratingStudentViewHolder.fio.setText(students.get(i).getFio());
        ratingStudentViewHolder.group.setText(students.get(i).getGroup().getGroupName());
        ratingStudentViewHolder.mark_rating.setText(String.valueOf(students.get(i).getRating()));
    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

}
