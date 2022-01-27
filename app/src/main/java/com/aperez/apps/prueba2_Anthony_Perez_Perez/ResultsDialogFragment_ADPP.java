package com.aperez.apps.prueba2_Anthony_Perez_Perez;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;

import com.aperez.apps.lifecyclehelpers.QuizViewModel_ADPP;

public class ResultsDialogFragment_ADPP extends DialogFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final QuizViewModel_ADPP quizViewModel = ViewModelProviders.of(getActivity()).get(QuizViewModel_ADPP.class);
        int totalGuesses = quizViewModel.getTotalGuesses();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(
                getString(R.string.results, totalGuesses, (1000 / (double) totalGuesses)));

        builder.setPositiveButton(R.string.reset_quiz, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    MainActivityFragment_ADPP quizFragment = (MainActivityFragment_ADPP) getParentFragment();
                    try{
                        quizFragment.resetQuiz();
                    }catch (Exception e){
                        Log.e(quizViewModel.getTag(),"Unable to call resetQuiz()", e);
                    }
                }
                catch (Exception e){
                    Log.e(quizViewModel.getTag(),"Unable to get ActivityMainFragment", e);
                }
            }
        });
        return builder.create();
    }
}
