package com.example.hangdroid.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class GameMultiActivity extends ActionBarActivity {

    String mWord;
    int mFailCounter = 0;
    int mGuessedLetters = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        String wordSent = getIntent().getStringExtra("WORD_MULTI");

        createTextViews(wordSent);
        mWord = wordSent.toUpperCase();

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
            Toast.makeText(this, "YOU ROCK", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    public void createTextViews(String words) {

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for (int i = 0; i < words.length(); i++) {

            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview, null);
            layoutLetters.addView(newTextView);
        }
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
                Toast.makeText(this, "YOU LOOSE", Toast.LENGTH_LONG).show();
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
