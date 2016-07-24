package com.spreys.agilequiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
}
