package com.example.quizapp;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    // setting up things
    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton prevButton;

    private TextView questionTextView;
    private int correct = 0;

    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {

            new Question(R.string.a, true),
            new Question(R.string.b, false),
            new Question(R.string.c, true),
            new Question(R.string.d, false),
            new Question(R.string.e, true),
            new Question(R.string.f, true),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

        questionTextView
                = findViewById(R.id.answer_text_view);

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")

    @Override
    public void onClick(View v)
    {


        if (v.getId() == R.id.false_button) {
            checkAnswer(false);
        } else if (v.getId() == R.id.true_button) {
            checkAnswer(true);
        } else if (v.getId() == R.id.next_button) {
            if (currentQuestionIndex < 6) {
                currentQuestionIndex
                        = currentQuestionIndex + 1;
                // we are safe now!
                // last question reached
                // making buttons
                // invisible
                if (currentQuestionIndex == 6) {
                    questionTextView.setText(getString(
                            R.string.correct, correct));
                    nextButton.setVisibility(
                            View.INVISIBLE);
                    prevButton.setVisibility(
                            View.INVISIBLE);
                    trueButton.setVisibility(
                            View.INVISIBLE);
                    falseButton.setVisibility(
                            View.INVISIBLE);
                    if (correct > 3)

                        questionTextView.setText(
                                "CORRECTNESS IS " + correct
                                        + " "
                                        + "OUT OF 6");
                        // showing correctness
                    else
                        questionTextView.setText("CORRECTNESS IS " + correct
                                + " "
                                + "OUT OF 6\n Better Luck Next time #99/100");

                }
                else {
                    updateQuestion();
                }
            }
        } else if (v.getId() == R.id.prev_button) {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex
                        = (currentQuestionIndex - 1)
                        % questionBank.length;
                updateQuestion();
            }
        }
    }


    private void updateQuestion()
    {
        Log.d("Current",
                "onClick: " + currentQuestionIndex);

        questionTextView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());

    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue
                = questionBank[currentQuestionIndex]
                .isAnswerTrue();

        int toastMessageId;

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {

            toastMessageId = R.string.wrong_answer;
        }

        Toast
                .makeText(MainActivity.this, toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();
    }
}
