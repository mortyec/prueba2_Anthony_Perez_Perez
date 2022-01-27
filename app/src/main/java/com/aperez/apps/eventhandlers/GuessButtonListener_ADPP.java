package com.aperez.apps.eventhandlers;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.aperez.apps.prueba2_Anthony_Perez_Perez.MainActivityFragment_ADPP;
import com.aperez.apps.prueba2_Anthony_Perez_Perez.R;
import com.aperez.apps.prueba2_Anthony_Perez_Perez.ResultsDialogFragment_ADPP;
import com.aperez.apps.lifecyclehelpers.QuizViewModel_ADPP;

public class GuessButtonListener_ADPP implements OnClickListener {
    private MainActivityFragment_ADPP ADPPMainActivityFragment;
    private Handler handler;

    public GuessButtonListener_ADPP(MainActivityFragment_ADPP ADPPMainActivityFragment) {
        this.ADPPMainActivityFragment = ADPPMainActivityFragment;
        this.handler = new Handler();
    }

    @Override
    public void onClick(View v) {
        Button guessButton = ((Button) v);
        String guess = guessButton.getText().toString();
        String answer = this.ADPPMainActivityFragment.getQuizViewModel().getCorrectCountryName();
        this.ADPPMainActivityFragment.getQuizViewModel().setTotalGuesses(1);

        if (guess.equals(answer)) {
            this.ADPPMainActivityFragment.getQuizViewModel().setCorrectAnswers(1);
            this.ADPPMainActivityFragment.getAnswerTextView().setText(answer + "!");
            this.ADPPMainActivityFragment.getAnswerTextView().setTextColor(
                    this.ADPPMainActivityFragment.getResources().getColor(R.color.correct_answer));

            this.ADPPMainActivityFragment.disableButtons();

            if (this.ADPPMainActivityFragment.getQuizViewModel().getCorrectAnswers()
                    == QuizViewModel_ADPP.getFlagsInQuiz()) {
                ResultsDialogFragment_ADPP quizResults = new ResultsDialogFragment_ADPP();
                quizResults.setCancelable(false);
                try {
                    quizResults.show(this.ADPPMainActivityFragment.getChildFragmentManager(), "Quiz Results");
                } catch (NullPointerException e) {
                    Log.e(QuizViewModel_ADPP.getTag(),
                            "GuessButtonListener: this.mainActivityFragment.getFragmentManager() " +
                                    "returned null",
                            e);
                }
            } else {
                this.handler.postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                ADPPMainActivityFragment.animate(true);
                            }
                        }, 2000);
            }
        } else {
            this.ADPPMainActivityFragment.incorrectAnswerAnimation();
            guessButton.setEnabled(false);
        }
    }
}
