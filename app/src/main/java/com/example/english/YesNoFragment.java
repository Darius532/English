package com.example.english;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class YesNoFragment extends Fragment {

    private static final String ARG_QUESTION_TEXT = "question_text";
    private static final String ARG_IS_CORRECT = "is_correct";

    private QuestionYesNo question;
    private YesNoPagerAdapter.OnAnswerSelectedListener listener;

    public static YesNoFragment newInstance(QuestionYesNo question) {
        YesNoFragment fragment = new YesNoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QUESTION_TEXT, question.getText());
        args.putBoolean(ARG_IS_CORRECT, question.isCorrect());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof YesNoActivity) {
            YesNoActivity activity = (YesNoActivity) context;
            listener = activity.getOnAnswerSelectedListener();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String text = getArguments().getString(ARG_QUESTION_TEXT);
            boolean isCorrect = getArguments().getBoolean(ARG_IS_CORRECT);
            question = new QuestionYesNo(text, isCorrect);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yes_no, container, false);
        TextView textViewQuestion = view.findViewById(R.id.textViewQuestion);
        textViewQuestion.setText(question.getText());

        Button buttonYes = view.findViewById(R.id.buttonYes);
        Button buttonNo = view.findViewById(R.id.buttonNo);

        buttonYes.setOnClickListener(v -> answerSelected(true));
        buttonNo.setOnClickListener(v -> answerSelected(false));

        return view;
    }

    private void answerSelected(boolean answer) {
        if (listener != null) {
            boolean isCorrect = (answer == question.isCorrect());
            listener.onAnswerSelected(question, isCorrect);
        }
    }
}