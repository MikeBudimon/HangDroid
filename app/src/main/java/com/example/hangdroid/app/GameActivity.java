package com.example.hangdroid.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends ActionBarActivity {

    String mWord;
    int mFailCounter = 0;
    int mGuessedLetters = 0;
    int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRandomWord();
    }

    /**
     * Retrieving the letter introduced on the editText
     *
     * @param v, (button clicked)
     */
    public void introduceLetter(View v) {

        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
        String letter = myEditText.getText().toString();
        myEditText.setText("");

        if (letter.length() == 1) {
            checkLetter(letter.toUpperCase());
        } else {
            Toast.makeText(this, "Please Introduce letter", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Checking if the letter introduced matches any letter in the word to guess
     *
     * @param introducedLetter, letter introduced by the user
     */
    public void checkLetter(String introducedLetter) {

        boolean letterGuessed = false;

        for (int i = 0; i < mWord.length(); i++) {

            // if one match is found
            if (mWord.charAt(i) == introducedLetter.charAt(0)) {
                letterGuessed = true;
                showLettersAtIndex(i, introducedLetter.charAt(0));
                mGuessedLetters++;
            }
        }

        if (!letterGuessed) {
            letterFailed(Character.toString(introducedLetter.charAt(0)));
        }

        if (mGuessedLetters == mWord.length()) {
            mPoints++;
            clearScreen();
            setRandomWord();
        }
    }

    public void setRandomWord() {
        String words = "BEAR BULL WOLF LION";
        String[] arrayWords = words.split(" ");

        int randomNumber = (int) (Math.random() * arrayWords.length);
        mWord = arrayWords[randomNumber];
    }

    public void clearScreen() {
        TextView textViewFailed = (TextView) findViewById(R.id.textView7);
        textViewFailed.setText("");

        mGuessedLetters = 0;
        mFailCounter = 0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < layoutLetters.getChildCount(); i++) {
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
    }

    public void letterFailed(String letterFailed) {

        TextView textViewFailed = (TextView) findViewById(R.id.textView7);
        textViewFailed.append(letterFailed);

        mFailCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch (mFailCounter) {
            case 1:
                imageView.setImageResource(R.drawable.hangdroid_1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.hangdroid_2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.hangdroid_3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.hangdroid_4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.hangdroid_5);
                break;
            case 6:
                Intent gameOverIntent = new Intent(this, GameOverActivity.class);
                gameOverIntent.putExtra("POINTS_IDENTIFIER", mPoints);
                startActivity(gameOverIntent);
                finish();
            default:
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * Displaying a letter guessed by the user
     *
     * @param position      of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed) {

        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }
}
