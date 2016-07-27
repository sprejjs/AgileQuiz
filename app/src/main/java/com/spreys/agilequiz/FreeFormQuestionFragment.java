package com.spreys.agilequiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created with Android Studio
 *
 * @author vspreys
 *         Date: 24/07/16
 *         Project: AgileQuiz
 *         Contact by: vlad@spreys.com
 */
public class FreeFormQuestionFragment extends Fragment implements IQuestionFragment {
    private String question;
    private String answer;

    @BindView(R.id.free_from_fragment_question)
    TextView txtQuestion;

    @BindView(R.id.free_form_fragment_answer)
    EditText editTextResponse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.free_form_fragment, container, false);

        ButterKnife.bind(this, view);
        prepareView();

        editTextResponse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //This method has been intentionally left empty
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //This method has been intentionally left empty
            }

            @Override
            public void afterTextChanged(Editable editable) {
                ((MainActivity)getActivity()).answerPicked(!editable.toString().isEmpty());
            }
        });

        return view;
    }

    public void setQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
        prepareView();
    }

    private void prepareView() {
        if (isAdded()) {
            txtQuestion.setText(question);
        }
    }

    @Override
    public boolean triggerAnswer() {
        return editTextResponse.getText().toString().toLowerCase().equals(answer.toLowerCase());
    }

    @Override
    public void restart() {
        if(isAdded()) {
            editTextResponse.setText("");
        }
    }
}
