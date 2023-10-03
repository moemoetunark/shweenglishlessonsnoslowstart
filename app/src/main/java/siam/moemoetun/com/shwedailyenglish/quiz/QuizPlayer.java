package siam.moemoetun.com.shwedailyenglish.quiz;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.utility.ToolbarUtils;

public class QuizPlayer extends AppCompatActivity {

    private LinearLayout optionsContainer;
    private Button nextButton;
    private TextView questionTextView;
    private TextView explanationTextView;
    private ProgressBar progressBar;

    private List<QuizQuestion> filteredQuestions;
    private int currentQuestionIndex;
    private String selectedCategory;
    private List<QuizQuestion> quizQuestions;
    private List<Integer> selectedButtonPositions;
    private int correctCount = 0;
    private int incorrectCount;

    private Drawable correctIcon;
    private Drawable wrongIcon;

    private int totalQuestions;
    private int progress;
    private Toolbar toolbar;
    private InterstitialAd mInterstitialAd;
    String statusText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_quiz_player_tab);

        toolbar = findViewById(R.id.toolbar);
        ToolbarUtils.setupToolbarWithCustomFont(
                this,
                toolbar,
                getIntent().getStringExtra("clickedItemName"),
                "fonts/tharlon.ttf" // Replace with your font path
        );
        loadInterstitialAds();
        selectedButtonPositions = new ArrayList<>();

        optionsContainer = findViewById(R.id.optionsContainer);
        nextButton = findViewById(R.id.showNext);
        questionTextView = findViewById(R.id.questionTextView);
        explanationTextView = findViewById(R.id.explanationTextView);
        progressBar = findViewById(R.id.progressBar);

        correctIcon = ContextCompat.getDrawable(this, R.drawable.correct);
        wrongIcon = ContextCompat.getDrawable(this, R.drawable.error);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNextQuestion();
            }
        });

        // Load the quiz questions from JSON file
        quizQuestions = QuizQuestionLoader.loadQuizQuestions(this);

        // Filter the quiz questions based on the selected category
        selectedCategory = getIntent().getStringExtra("selectedCategory");
        filteredQuestions = new ArrayList<>();
        for (QuizQuestion question : quizQuestions) {
            if (question.getCategory().equals(selectedCategory)) {
                filteredQuestions.add(question);
            }
        }

        currentQuestionIndex = 0;
        displayQuestion();

        // Calculate the total number of quiz questions
        totalQuestions = filteredQuestions.size();

        // Set the initial progress to 0%
        progress = 0;
        progressBar.setProgress(progress);
    }
    private void submitAnswer() {
        int selectedPosition = getSelectedButtonPosition();
        if (selectedPosition == -1) {
            // No option selected
            Toast.makeText(QuizPlayer.this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        QuizQuestion question = filteredQuestions.get(currentQuestionIndex);
        Button selectedButton = (Button) optionsContainer.getChildAt(selectedPosition);
        String selectedOption = selectedButton.getText().toString();
        int paddingPixels = getResources().getDimensionPixelSize(R.dimen.left_padding);


        if (selectedOption.equals(question.getCorrectAnswer())) {
            question.setUserAnswer(selectedOption);
            // Correct answer
            Toast.makeText(QuizPlayer.this, "Right", Toast.LENGTH_SHORT).show();
            selectedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, correctIcon, null);
            selectedButton.setCompoundDrawablePadding(paddingPixels);
            playAudioFromAsset("right_answer.mp3");
            correctCount++;
        } else {
            // Incorrect answer
            Toast.makeText(QuizPlayer.this, "Wrong", Toast.LENGTH_SHORT).show();
            selectedButton.setCompoundDrawablesWithIntrinsicBounds(null, null, wrongIcon, null);
            selectedButton.setCompoundDrawablePadding(paddingPixels);
            String correctAnswer = question.getCorrectAnswer();
            for (int i = 0; i < optionsContainer.getChildCount(); i++) {
                Button button = (Button) optionsContainer.getChildAt(i);
                if (button.getText().toString().equals(correctAnswer)) {
                    button.setCompoundDrawablesWithIntrinsicBounds(null, null, correctIcon, null);
                    selectedButton.setCompoundDrawablePadding(paddingPixels);
                    break;
                }
            }
            playAudioFromAsset("wrong_answer.mp3");
            incorrectCount++;
        }

        String explanation = question.getExplanation();
        if (explanation != null && !explanation.isEmpty()) {
            explanationTextView.setText(explanation);
            explanationTextView.setVisibility(View.VISIBLE);
        } else {
            explanationTextView.setVisibility(View.GONE);
        }

        nextButton.setEnabled(true);
        selectedButtonPositions.add(selectedPosition);
        updateProgressBar();
    }

    private int getSelectedButtonPosition() {
        for (int i = 0; i < optionsContainer.getChildCount(); i++) {
            Button button = (Button) optionsContainer.getChildAt(i);
            if (button.isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private void displayQuestion() {
        if (currentQuestionIndex < filteredQuestions.size()) {
            QuizQuestion question = filteredQuestions.get(currentQuestionIndex);
            questionTextView.setText(question.getQuestionText());
            String[] options = question.getOptions();

            // Shuffle the options array
            shuffleArray(options);

            // Clear existing buttons
            optionsContainer.removeAllViews();

            for (int i = 0; i < options.length; i++) {
                Button button = new Button(QuizPlayer.this);
                button.setText(options[i]);
                button.setId(i);

                Drawable customDrawable = ContextCompat.getDrawable(QuizPlayer.this, R.drawable.options_button_bg);
                button.setBackground(customDrawable);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deselectAllButtons();
                        button.setSelected(true);
                        submitAnswer();
                    }
                });

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, 8, 0, 8);
                button.setLayoutParams(layoutParams);
                optionsContainer.addView(button);
            }

            deselectAllButtons();
            nextButton.setEnabled(false);
            explanationTextView.setVisibility(View.GONE);
        }
    }

    private void shuffleArray(String[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String temp = array[i];
            array[i] = array[index];
            array[index] = temp;
        }
    }

    private void deselectAllButtons() {
        for (int i = 0; i < optionsContainer.getChildCount(); i++) {
            Button button = (Button) optionsContainer.getChildAt(i);
            button.setSelected(false);
        }
    }

    private void displayNextQuestion() {
        if (getSelectedButtonPosition() == -1) {
            // No option selected
            Toast.makeText(QuizPlayer.this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }

        currentQuestionIndex++;
        if (currentQuestionIndex < filteredQuestions.size()) {
            displayQuestion();
            nextButton.setEnabled(true);
            explanationTextView.setVisibility(View.GONE);
        } else {
            handleEndOfQuiz();
        }
    }

    private void handleEndOfQuiz() {
        double percentage = (double) correctCount / filteredQuestions.size() * 100;
        if (percentage >= 90) {
            statusText = "A";
        } else if (percentage >= 80) {
            statusText = "B";
        } else if (percentage >= 70) {
            statusText = "C";
        } else if (percentage >= 60) {
            statusText = "D";
        } else {
            statusText = "Failed";
        }

        if (!filteredQuestions.isEmpty() && selectedCategoryHasQuestions()) {
            if(mInterstitialAd !=null){
                showInterstitialAds();
            }else {
                // Handle the case when filteredQuestions is empty
                Intent intent = new Intent(QuizPlayer.this, QuizReviewActivity.class);
                intent.putParcelableArrayListExtra("filteredQuestions", new ArrayList<>(filteredQuestions));
                intent.putExtra("correctCount", correctCount);
                intent.putExtra("incorrectCount", incorrectCount);
                intent.putExtra("statusText", statusText);
                startActivity(intent);
                finish();
            }
        } else {
           Toast.makeText(QuizPlayer.this, "No Questions left", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean selectedCategoryHasQuestions() {
        for (QuizQuestion question : quizQuestions) {
            if (question.getCategory().equals(selectedCategory)) {
                return true;
            }
        }
        return false;
    }

    private void playAudioFromAsset(String fileName) {
        try {
            AssetFileDescriptor afd = QuizPlayer.this.getAssets().openFd(fileName);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateProgressBar() {
        progress = (selectedButtonPositions.size() * 100) / totalQuestions;
        progressBar.setProgress(progress);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void loadInterstitialAds(){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-4137439985376631/2373510414", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                       mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                           @Override
                           public void onAdDismissedFullScreenContent() {
                               Intent intent = new Intent(QuizPlayer.this, QuizReviewActivity.class);
                               intent.putParcelableArrayListExtra("filteredQuestions", new ArrayList<>(filteredQuestions));
                               intent.putExtra("correctCount", correctCount);
                               intent.putExtra("incorrectCount", incorrectCount);
                               intent.putExtra("statusText", statusText);
                               startActivity(intent);
                               finish();
                           }
                       });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        mInterstitialAd = null;
                    }
                });
    }
private void showInterstitialAds(){
    if (mInterstitialAd != null) {
        mInterstitialAd.show(QuizPlayer.this);
    }
}

}
