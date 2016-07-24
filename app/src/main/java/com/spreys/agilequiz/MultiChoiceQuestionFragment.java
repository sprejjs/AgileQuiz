package com.spreys.agilequiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created with Android Studio
 *
 * @author vspreys
 *         Date: 23/07/16
 *         Project: AgileQuiz
 *         Contact by: vlad@spreys.com
 */
public class MultiChoiceQuestionFragment extends Fragment implements IQuestionFragment {
    private String[] choices;
    private String question;
    private String[] correctAnswers;

    @BindView(R.id.multi_choice_fragment_option_1)
    CheckBox txtOption1;

    @BindView(R.id.multi_choice_fragment_option_2)
    CheckBox txtOption2;

    @BindView(R.id.multi_choice_fragment_option_3)
    CheckBox txtOption3;

    @BindView(R.id.multi_choice_fragment_option_4)
    CheckBox txtOption4;

    @BindView(R.id.multi_choice_fragment_question)
    TextView txtQuestion;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.multi_choice_fragment, container, false);
        ButterKnife.bind(this, view);
        prepareView();

        return view;
    }

    public void setQuestion(String question, String[] choices, String correctAnswers[]) {
        this.question = question;
        this.choices = choices;
        this.correctAnswers = correctAnswers;

        prepareView();
    }

    public boolean submitAnswer(int[] answerIds) {
        if (answerIds.length != correctAnswers.length) {
            return false;
        }

        boolean allAnswersCorrect = true;

        for (int i = 0; i < answerIds.length; i++) {
            if(!choices[answerIds[i]].equals(correctAnswers[i])) {
                allAnswersCorrect = false;
            }
        }
        return allAnswersCorrect;
    }

    private void prepareView() {
        if (isAdded()) {
            txtOption1.setText(choices[0]);
            txtOption2.setText(choices[1]);
            txtOption3.setText(choices[2]);
            txtOption4.setText(choices[3]);
            txtQuestion.setText(question);
        }
    }
}
