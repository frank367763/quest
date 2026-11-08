package com.example.quest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_HELP_USED = "help_used";
    private static final int REQUEST_HELP = 1;
    public static final String EXTRA_QUESTION = "com.example.quest.extra_question";

    private TextView mQuestionTextView;

    private Button mV1Button;
    private Button mV2Button;
    private Button mV3Button;
    private Button mV4Button;
    private Button mV5Button;
    private Button mV6Button;

    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private Button mDevelopersButton;
    private Button mHelpButton;

    private NumberOption[] mQuestionArray = new NumberOption[]{
new NumberOption(R.string.question_prime, R.string.hint_prime, true,false,false,false,true,false),
            new NumberOption(R.string.question_even,R.string.hint_even,false,true,false,false,false,true),
            new NumberOption(R.string.question_divided_by_3,R.string.hint_divided_by_3,false,false,true,true,false,false),
            new NumberOption(R.string.question_meme,R.string.hint_meme,false,false,false,false,true,false),
            new NumberOption(R.string.question_no_even,R.string.hint_odd,true,false,true,true,true,false),
    };

    private int mQuestIndex = 0;
    private boolean[] mHelpUsed = new boolean[mQuestionArray.length];

    private void updateQuestion(){
        int question = mQuestionArray[mQuestIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(int buttonNumber){
        boolean isCorrect = false;
        NumberOption currentQuestion = mQuestionArray[mQuestIndex];

        switch (buttonNumber){
            case 1:
                isCorrect = currentQuestion.isTrueQuestion1();
                break;
            case 2:
                isCorrect = currentQuestion.isTrueQuestion2();
                break;
            case 3:
                isCorrect = currentQuestion.isTrueQuestion3();
                break;
            case 4:
                isCorrect = currentQuestion.isTrueQuestion4();
                break;
            case 5:
                isCorrect = currentQuestion.isTrueQuestion5();
                break;
            case 6:
                isCorrect = currentQuestion.isTrueQuestion6();
                break;
        }
        int messageResId = isCorrect ? R.string.correct_toast : R.string.incorrect_toast;
        Toast.makeText(MainActivity.this,messageResId,Toast.LENGTH_SHORT).show();

        if (mHelpUsed[mQuestIndex]) {
            Toast.makeText(this, R.string.help_used, Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mQuestIndex = savedInstanceState.getInt(KEY_INDEX, 0);

            boolean[] savedHelpUsed =
                    savedInstanceState.getBooleanArray(KEY_HELP_USED);

            if (savedHelpUsed != null) {
                mHelpUsed = savedHelpUsed;
            }
        }

       //mQuestionTextView = findViewById(R.id.question_text_view);

        mV1Button = findViewById(R.id.v1);
        mV2Button = findViewById(R.id.v2);
        mV3Button = findViewById(R.id.v3);
        mV4Button = findViewById(R.id.v4);
        mV5Button = findViewById(R.id.v5);
        mV6Button = findViewById(R.id.v6);

        mDevelopersButton = findViewById(R.id.developers);
        mHelpButton = findViewById(R.id.help);

        mNextButton = findViewById(R.id.next_button);
        mPreviousButton = findViewById(R.id.previous_button);


        mV1Button.setOnClickListener(v -> checkAnswer(1));
        mV2Button.setOnClickListener(v -> checkAnswer(2));
        mV3Button.setOnClickListener(v -> checkAnswer(3));
        mV4Button.setOnClickListener(v -> checkAnswer(4));
        mV5Button.setOnClickListener(v -> checkAnswer(5));
        mV6Button.setOnClickListener(v -> checkAnswer(6));

        mNextButton.setOnClickListener(v -> {
            mQuestIndex = (mQuestIndex + 1) % mQuestionArray.length;
            updateQuestion();
        });

        mPreviousButton.setOnClickListener(v -> {
            if (mQuestIndex > 0) {
                mQuestIndex = mQuestIndex - 1;
            } else {
                mQuestIndex = mQuestionArray.length - 1; // если на первом, листаем в конец
            }
            updateQuestion();
        });

        mDevelopersButton.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, R.string.developers_toast, Toast.LENGTH_SHORT).show()
        );

        mHelpButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);

            int questionResId = mQuestionArray[mQuestIndex].getQuestion();
            String questionText = getString(questionResId);

            intent.putExtra(HelpActivity.EXTRA_QUESTION, questionText);

            int hintResId = mQuestionArray[mQuestIndex].getHint();
            String hintText = getString(hintResId);

            intent.putExtra(HelpActivity.EXTRA_HINT, hintText);

            startActivityForResult(intent, REQUEST_HELP);
        });

        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_HELP
                && resultCode == RESULT_OK
                && data != null){

            boolean wasUsed = data.getBooleanExtra(HelpActivity.EXTRA_HELP_WAS_USED, false);

            mHelpUsed[mQuestIndex] =
                    mHelpUsed[mQuestIndex] || wasUsed;
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState() called");
        outState.putInt(KEY_INDEX, mQuestIndex);
        outState.putBooleanArray(KEY_HELP_USED, mHelpUsed);
    }
}
