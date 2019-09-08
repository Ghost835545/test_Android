package com.example.ivan.loginapp.util;

import android.content.Context;

import com.example.ivan.loginapp.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class SingletResultQuestions {
    private static SingletResultQuestions sSingletResultQuestions;

    private List<Question> mResultQuestions;

    public static SingletResultQuestions get(Context context) {
        if (sSingletResultQuestions == null) {
            sSingletResultQuestions = new SingletResultQuestions(context);
        }
        return sSingletResultQuestions;

    }

    private SingletResultQuestions(Context context) {
        mResultQuestions = new ArrayList<>();

    }

    public List<Question> getTests(List<Question> resultQuestion) {
        mResultQuestions=resultQuestion;
        return mResultQuestions;
    }

}
