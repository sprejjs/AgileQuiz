package com.spreys.agilequiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

/**
 * Created with Android Studio
 *
 * @author vspreys
 *         Date: 23/07/16
 *         Project: AgileQuiz
 *         Contact by: vlad@spreys.com
 */
public class SingleChoiceQuestionFragment extends Fragment implements IQuestionFragment {
    private String[] choices;
    private String question;
    private String correctAnswer;

    @BindView(R.id.single_choice_fragment_option_1)
    RadioButton txtOption1;

    @BindView(R.id.single_choice_fragment_option_2)
    RadioButton txtOption2;

    @BindView(R.id.single_choice_fragment_option_3)
    RadioButton txtOption3;

    @BindView(R.id.single_choice_fragment_option_4)
    RadioButton txtOption4;

    @BindView(R.id.single_choice_fragment_question)
    TextView txtQuestion;

    @BindView(R.id.single_choice_fragment_radio_group)
    RadioGroup radioGroup;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.single_choice_fragment, container, false);
        ButterKnife.bind(this, view);
        prepareView();

        return view;
    }

    public void setQuestion(String question, String[] choices, String correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;

        prepareView();
    }

    public boolean triggerAnswer() {
        return radioGroup.getCheckedRadioButtonId();
    }

    private void prepareView() {
        if (isAdded()) {
            txtOption1.setText(choices[0]);
            txtOption2.setText(choices[1]);
            txtOption3.setText(choices[2]);
            txtOption4.setText(choices[3]);
            txtQuestion.setText(question);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    ((MainActivity)getActivity()).answerPicked();
                }
            });
        }
    }
}
