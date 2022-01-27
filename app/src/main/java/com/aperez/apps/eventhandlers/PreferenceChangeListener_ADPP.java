package com.aperez.apps.eventhandlers;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.widget.Toast;

import com.aperez.apps.prueba2_Anthony_Perez_Perez.MainActivity_ADPP;
import com.aperez.apps.prueba2_Anthony_Perez_Perez.R;

import java.util.Set;

public class PreferenceChangeListener_ADPP implements OnSharedPreferenceChangeListener {
    private MainActivity_ADPP mainActivity;

    public PreferenceChangeListener_ADPP(MainActivity_ADPP mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.mainActivity.setPreferencesChanged(true);

        if (key.equals(this.mainActivity.getREGIONS())) {
            this.mainActivity.getQuizViewModel().setGuessRows(sharedPreferences.getString(
                    MainActivity_ADPP.CHOICES, null));
            this.mainActivity.getQuizFragment().resetQuiz();
        } else if (key.equals(this.mainActivity.getCHOICES())) {
            Set<String> regions = sharedPreferences.getStringSet(this.mainActivity.getREGIONS(),
                    null);
            if (regions != null && regions.size() > 0) {
                this.mainActivity.getQuizViewModel().setRegionsSet(regions);
                this.mainActivity.getQuizFragment().resetQuiz();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                regions.add(this.mainActivity.getString(R.string.default_region));
                editor.putStringSet(this.mainActivity.getREGIONS(), regions);
                editor.apply();
                Toast.makeText(this.mainActivity, R.string.default_region_message,
                        Toast.LENGTH_LONG).show();
            }
        }

        Toast.makeText(this.mainActivity, R.string.restarting_quiz,
                Toast.LENGTH_SHORT).show();
    }
}
