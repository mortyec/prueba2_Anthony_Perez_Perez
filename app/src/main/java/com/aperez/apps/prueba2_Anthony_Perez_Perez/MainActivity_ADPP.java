package com.aperez.apps.prueba2_Anthony_Perez_Perez;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aperez.apps.eventhandlers.PreferenceChangeListener_ADPP;
import com.aperez.apps.lifecyclehelpers.QuizViewModel_ADPP;

public class MainActivity_ADPP extends AppCompatActivity {
    public static final String CHOICES = "pref_numberOfChoices";
    public static final String REGIONS = "pref_regionsToInclude";
    private boolean deviceIsPhone = true;
    private boolean preferencesChanged = true;
    private MainActivityFragment_ADPP quizFragment;
    private QuizViewModel_ADPP quizViewModel;
    private OnSharedPreferenceChangeListener preferencesChangeListener;

    private TextView textView_usuario;

    private void setSharedPreferences() {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);


        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(preferencesChangeListener);
    }

    private void screenSetUp() {
        if (getScreenSize() == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                getScreenSize() == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            deviceIsPhone = false;
        }
        if (deviceIsPhone) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.quizViewModel = ViewModelProviders.of(this).get(QuizViewModel_ADPP.class);
        this.preferencesChangeListener = new PreferenceChangeListener_ADPP(this);
        setContentView(R.layout.activity_main_adpp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setSharedPreferences();
        this.screenSetUp();
        //
        Bundle datosExtra =  getIntent().getExtras();

        String nombre = datosExtra.getString("key_nombre");

        textView_usuario = findViewById(R.id.textView_usuario);
        textView_usuario.setText("Usuario: "+ nombre);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferencesChanged) {
            this.quizFragment = (MainActivityFragment_ADPP) getSupportFragmentManager()
                    .findFragmentById(R.id.quizFragment);
            this.quizViewModel.setGuessRows(PreferenceManager.getDefaultSharedPreferences(this)
                    .getString(CHOICES, null));
            this.quizViewModel.setRegionsSet(PreferenceManager.getDefaultSharedPreferences(this)
                    .getStringSet(REGIONS, null));

            this.quizFragment.resetQuiz();

            preferencesChanged = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main_adpp, menu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent preferencesIntent = new Intent(this, SettingsActivity_ADPP.class);
        startActivity(preferencesIntent);
        return super.onOptionsItemSelected(item);
    }

    public int getScreenSize() {
        return getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
    }

    public MainActivityFragment_ADPP getQuizFragment() {
        return this.quizFragment;
    }

    public QuizViewModel_ADPP getQuizViewModel() {
        return quizViewModel;
    }

    public static String getCHOICES() {
        return CHOICES;
    }

    public static String getREGIONS() {
        return REGIONS;
    }

    public void setPreferencesChanged(boolean preferencesChanged) {
        this.preferencesChanged = preferencesChanged;
    }


}
