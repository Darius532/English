package com.example.english;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class YesNoPagerAdapter extends FragmentStateAdapter {

    private final List<QuestionYesNo> questions;
    private OnAnswerSelectedListener listener;

    public interface OnAnswerSelectedListener {
        void onAnswerSelected(QuestionYesNo question, boolean isCorrect);
    }

    public YesNoPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<QuestionYesNo> questions) {
        super(fragmentActivity);
        this.questions = questions;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return YesNoFragment.newInstance(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setOnAnswerSelectedListener(OnAnswerSelectedListener listener) {
        this.listener = listener;
    }

    public OnAnswerSelectedListener getOnAnswerSelectedListener() {
        return listener;
    }
}