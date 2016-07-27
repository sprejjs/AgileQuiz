package com.spreys.agilequiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick({R.id.multi_choice_fragment_option_1, R.id.multi_choice_fragment_option_2,
            R.id.multi_choice_fragment_option_3, R.id.multi_choice_fragment_option_4})
    public void onCheckedChanged(){
        ((MainActivity)getActivity())
                .answerPicked(txtOption1.isChecked() || txtOption2.isChecked()
                        || txtOption3.isChecked() || txtOption4.isChecked());
    }

    public void setQuestion(String question, String[] choices, String correctAnswers[]) {
        this.question = question;
        this.choices = choices;
        this.correctAnswers = correctAnswers;

        prepareView();
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

    @Override
    public boolean triggerAnswer() {
        ArrayList<String> selectedAnswers = new ArrayList<>();

        if (txtOption1.isChecked()) {
            selectedAnswers.add(txtOption1.getText().toString());
        }

        if (txtOption2.isChecked()) {
            selectedAnswers.add(txtOption2.getText().toString());
        }

        if (txtOption3.isChecked()) {
            selectedAnswers.add(txtOption3.getText().toString());
        }

        if (txtOption4.isChecked()) {
            selectedAnswers.add(txtOption4.getText().toString());
        }

        return Arrays.equals(selectedAnswers.toArray(), correctAnswers);
    }
}
