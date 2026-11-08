package com.example.quest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Button;

import android.content.Intent;

public class HelpActivity extends AppCompatActivity {

    public static final String EXTRA_QUESTION =
            "com.example.quest.extra_question";
    public static final String EXTRA_HINT =
            "com.example.quest.extra_hint";
    public static final String EXTRA_HELP_WAS_USED =
            "com.example.quest.extra_help_was_used";

    private static final String KEY_HINT_SHOWN = "hint_shown";
    private boolean mHintShown = false;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView questionTextView = findViewById(R.id.help_question_text_view);

        String questionText = getIntent().getStringExtra(EXTRA_QUESTION);

        if (questionText != null){
            questionTextView.setText(questionText);
        }


        Button showHintButton = findViewById(R.id.show_hint_button);
        TextView hintTextView = findViewById(R.id.hint_text_view);


        String hintText = getIntent().getStringExtra(EXTRA_HINT);


        if (savedInstanceState != null) {
            mHintShown = savedInstanceState.getBoolean(KEY_HINT_SHOWN, false);
        }

        if (mHintShown && hintText != null) {
            hintTextView.setText(hintText);
        }

        setHelpResult(mHintShown);

        showHintButton.setOnClickListener(v -> {
            if (hintText != null) {
                hintTextView.setText(hintText);
                mHintShown = true;
                setHelpResult(mHintShown);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_HINT_SHOWN, mHintShown);
    }

    private void setHelpResult(boolean wasUsed){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_HELP_WAS_USED, wasUsed);
        setResult(RESULT_OK, resultIntent);
    }
}
