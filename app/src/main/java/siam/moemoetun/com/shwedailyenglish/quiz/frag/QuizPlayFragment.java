package siam.moemoetun.com.shwedailyenglish.quiz.frag;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.quiz.QuizPlayer;
import siam.moemoetun.com.shwedailyenglish.quiz.adapter.SelectedCategoryAdapter;


public class QuizPlayFragment extends Fragment {
    private ArrayList<String> selectedCategoryArrayList = new ArrayList<>();
    private final HashMap<String, ArrayList<String>> categoryMap = new HashMap<>();
    private String selectedCategory;
    public QuizPlayFragment() {
    }



    public static QuizPlayFragment newInstance(String category) {
        QuizPlayFragment fragment = new QuizPlayFragment();
        Bundle args = new Bundle();
        args.putString("selectedCategory", category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeExerciseLists();
        if (getArguments() != null) {
            selectedCategory = getArguments().getString("selectedCategory");
            selectedCategoryArrayList = categoryMap.get(selectedCategory);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_quiz_player, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        if (selectedCategoryArrayList != null && !selectedCategoryArrayList.isEmpty()) {
            SelectedCategoryAdapter adapter = new SelectedCategoryAdapter(selectedCategoryArrayList, new SelectedCategoryAdapter.ItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(requireContext(), QuizPlayer.class);
                    String selectedItem = selectedCategoryArrayList.get(position);
                    intent.putExtra("selectedCategory", selectedItem);
                    intent.putExtra("clickedItemName", selectedItem);
                    startActivity(intent);
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            recyclerView.setAdapter(adapter);
        } else {
            // Handle the case where selectedCategoryArrayList is null or empty
           noExercise();
        }
        return view;
    }

    private void initializeExerciseLists() {
        ArrayList<String> nounExercise = new ArrayList<>();
        nounExercise.add("Singular or Plural Exercise");
        nounExercise.add("Kinds of Nouns Exercise");
        nounExercise.add("Noun Spelling Rule Exercise");
        categoryMap.put("Nouns -နာမ်", nounExercise);

        ArrayList<String> pronounExercise = new ArrayList<>();
        pronounExercise.add("Checking Understanding of Pronoun Exercise");
        pronounExercise.add("Kinds of Pronoun Exercise");
        pronounExercise.add("Finding Pronoun in a Sentence လေ့ကျင့်ခန်း");
        categoryMap.put("Pronoun - နာမ်စား", pronounExercise);

        ArrayList<String> adJectiveExercise = new ArrayList<>();
        adJectiveExercise.add("Kinds of Adjectives လေ့ကျင့်ခန်း");
        adJectiveExercise.add("Find the Adjective");
        adJectiveExercise.add("Adjective အမျိုးစားလေ့ကျင့်ခန်း");
        categoryMap.put("Adjective-နာမဝိသေသန", adJectiveExercise);

        ArrayList<String> verbExercise = new ArrayList<>();
        verbExercise.add("Verb လေ့ကျင့်ခန်း ၁");
        verbExercise.add("Verb လေ့ကျင့်ခန်း ၂");
        categoryMap.put("Verbs - ကြိယာ", verbExercise);

        ArrayList<String> adverbExercise = new ArrayList<>();
        adverbExercise.add("Adverb လေ့ကျင့်ခန်း (၁)");
        adverbExercise.add("Adverb လေ့ကျင့်ခန်း (၂)");
        categoryMap.put("Adverb - ကြိယာဝိသေသန", adverbExercise);

        ArrayList<String> prepositionExercise = new ArrayList<>();
        prepositionExercise.add("Preposition ဝိဘတ်လေ့ကျင့်ခန်း (၁)");
        prepositionExercise.add("Preposition ဝိဘတ်လေ့ကျင့်ခန်း (၂)");
        categoryMap.put("Preposition - ဝိဘတ်", prepositionExercise);

        ArrayList<String> conjunctionExercise = new ArrayList<>();
        conjunctionExercise.add("Conjunctions လေ့ကျင့်ခန်း (၁)");
        conjunctionExercise.add("Conjunctions လေ့ကျင့်ခန်း (၂)");
        categoryMap.put("Conjunction - စကားဆက်", conjunctionExercise);

        ArrayList<String> bePresentExercise = new ArrayList<>();
        bePresentExercise.add("Verb to be(am/is/are) လေ့ကျင့်ခန်း");
        bePresentExercise.add("Verb to be(am/is/are) လေ့ကျင့်ခန်း ၂");
        categoryMap.put("Be (am,is,are) ရှိသည်၊၊ဖြစ်သည်", bePresentExercise);

        ArrayList<String> bePastExercise = new ArrayList<>();
        bePastExercise.add("Verb to be(was/were) လေ့ကျင့်ခန်း");
        bePastExercise.add("Verb to be(was/were) လေ့ကျင့်ခန်း 2");
        categoryMap.put("Be (was/were) ရှိခဲ့သည်၊ဖြစ်ခဲ့သည်",bePastExercise);

        ArrayList<String> beFutureExercise = new ArrayList<>();
        beFutureExercise.add("Verb to be (will be) လေ့ကျင့်ခန်း");
        beFutureExercise.add("Verb to be (will be) လေ့ကျင့်ခန်း 2");
        categoryMap.put("Be - will be/would be - အနာဂတ်",beFutureExercise);

        ArrayList<String> bePerfectExercise = new ArrayList<>();
        bePerfectExercise.add("Be- Perefect Tense လေ့ကျင့်ခန်း ၁");
        bePerfectExercise.add("Be- Perefect Tense လေ့ကျင့်ခန်း ၂");
        categoryMap.put("Be- have been/has been - ပြီးစီးကါလ",bePerfectExercise);

        ArrayList<String> beAdjectiveExercise = new ArrayList<>();
        beAdjectiveExercise.add("Be + adjective လေ့ကျင့်ခန်း (၁)");
        beAdjectiveExercise.add("Be + adjective  လေ့ကျင့်ခန်း (၂)");
        beAdjectiveExercise.add("Be + Adjective - Short Forms");
        categoryMap.put("Be + Adjective",beAdjectiveExercise);

        ArrayList<String> bePrepositionExercise = new ArrayList<>();
        bePrepositionExercise.add("Be + Preposition လေ့ကျင့်ခန်း");
        bePrepositionExercise.add("Be + Preposition - Short Forms");
        categoryMap.put("Be + preposition", bePrepositionExercise);
        ArrayList<String> thereBeExercise = new ArrayList<>();
        thereBeExercise.add("There + am/is/are လေ့ကျင့်ခန်း");
        thereBeExercise.add("There Was/Were");
        thereBeExercise.add("There be + short form လေ့ကျင့်ခန်း");
        thereBeExercise.add("There be + preposition လေ့ကျင့်ခန်း");
        categoryMap.put("There + be + Noun. - ရှိသည် (တည်နေရာ)",thereBeExercise);

        ArrayList<String> whQuestionExercise = new ArrayList<>();
        whQuestionExercise.add("Wh-Questions(am/is/are) လေ့ကျင့်ခန်း");
        whQuestionExercise.add("Wh-Questions with Was/Were");
        categoryMap.put("Be + wh Questions",whQuestionExercise);

        ArrayList<String> doPresentSimpleExercise = new ArrayList<>();
        doPresentSimpleExercise.add("Present Simple Tense -လေ့ကျင့်ခန်း ၁");
        doPresentSimpleExercise.add("Present Simple Tense -လေ့ကျင့်ခန်း ၂");
        doPresentSimpleExercise.add("Present Simple Tense -လေ့ကျင့်ခန်း ၃");
        categoryMap.put("Present Simple (I do - လုပ်သည်)",doPresentSimpleExercise);

        ArrayList<String> doPresentContinuousExercise = new ArrayList<>();
        doPresentContinuousExercise.add("Present Continuous Tense လေ့ကျင့်ခန်း ၁");
        doPresentContinuousExercise.add("Present Continuous Tense လေ့ကျင့်ခန်း ၂");
        doPresentContinuousExercise.add("Present Continuous Tense လေ့ကျင့်ခန်း ၃");
        categoryMap.put("Present Continuous -I'm doing - လုပ်နေသည်", doPresentContinuousExercise);

        ArrayList<String> doPresentPerfectExercise = new ArrayList<>();
        doPresentPerfectExercise.add("Present Perfect Tense လေ့ကျင့်ခန်း (၁)");
        doPresentPerfectExercise.add("Present Perfect Tense လေ့ကျင့်ခန်း (၂)");
        categoryMap.put("Present Perfect - (I've done.  ပြီးပြီ",doPresentPerfectExercise);

        ArrayList<String> doPresentPerContinuousfectExercise = new ArrayList<>();
        doPresentPerContinuousfectExercise.add("Present Perfect Continuous Tense လေ့ကျင့်ခန်း (၁)");
        doPresentPerContinuousfectExercise.add("Present Perfect Continuous Tense လေ့ကျင့်ခန်း (၁)");
        categoryMap.put("Present Perfect Continuous - လုပ်ပြီးနေသည်",doPresentPerContinuousfectExercise);


        ArrayList<String> simplePastExercise = new ArrayList<>();
        simplePastExercise.add("Simple Past Tense လေ့ကျင့်ခန်း (၁)");
        simplePastExercise.add("Simple Past Tense လေ့ကျင့်ခန်း (၂)");
        categoryMap.put("Past Simple - I did - လုပ်ခဲ့သည်",simplePastExercise);

        ArrayList<String> pastContinuousExercise = new ArrayList<>();
        pastContinuousExercise.add("Past Continuous Tense");
        pastContinuousExercise.add("Past Continuous Tense လေ့ကျင့်ခန်း ၂");
        categoryMap.put("Past Continuous - I was doing-လုပ်နေခဲ့သည်",pastContinuousExercise);

        ArrayList<String> pastPerfectExercise = new ArrayList<>();
        pastPerfectExercise.add("Past Perfect Tense Exercise 1");
        pastPerfectExercise.add("Past Perfect Tense Exercise 2");
        categoryMap.put("Past Perfect - I had done-လုပ်ခဲ့ပြီးပြီး",pastPerfectExercise);

        ArrayList<String> pastPerfectContinuousExercise = new ArrayList<>();
        pastPerfectContinuousExercise.add("Past Perfect Continuous Tense Exercise");
        categoryMap.put("Past Perfect Continuous - လုပ်ပြီး နေခဲ့",pastPerfectContinuousExercise);

        ArrayList<String> simpleFutureExercise = new ArrayList<>();
        simpleFutureExercise.add("Simple Future Tense Exercise");
        simpleFutureExercise.add("Simple Future Tense Short Form Exercise");
        categoryMap.put("Future Simple - I will do-လုပ်လိမ့်မယ်",simpleFutureExercise);

        ArrayList<String> continuousFutureExercise = new ArrayList<>();
        continuousFutureExercise.add("Future Continuous Tense Exercise");
        categoryMap.put("Future Continuous - I'll be doing - လုပ်နေလိမ့်မည်",continuousFutureExercise);

        ArrayList<String> perfectFutureExercise = new ArrayList<>();
        perfectFutureExercise.add("Future Perfect Tense Exercise");
        categoryMap.put("Future Perfect - I will have done -လုပ်ပြီးလိမ့်မည်",perfectFutureExercise);

        ArrayList<String> perfectContinuousFutureExercise = new ArrayList<>();
        perfectContinuousFutureExercise.add("Future Perfect Continuous Tense Exercise");
        categoryMap.put("Future Perfect Continuous - လုပ်ပြီးပြီး နေလိမ့်မည်",perfectContinuousFutureExercise);

        ArrayList<String> passiveVoicePresentSimple = new ArrayList<>();
        passiveVoicePresentSimple.add("Passive Voice Present Simple လေ့ကျင့်ခန်း (၁)");
        passiveVoicePresentSimple.add("Passive Voice Present Simple လေ့ကျင့်ခန်း (၂)");
        categoryMap.put("Present Simple (S + am/is/are + V3)",passiveVoicePresentSimple);

        ArrayList<String> passiveVoicePresentContinuous = new ArrayList<>();
        passiveVoicePresentContinuous.add("Passive Voice Present Continuous Tense လေ့ကျင့်ခန်း (၁၅ မေးခွန်း)");
        passiveVoicePresentContinuous.add("Passive Voice Present Continuous Tense လေ့ကျင့်ခန်း (၁၀ မေးခွန်း)");
        categoryMap.put("Present Continuous (S+ am/is/are + V3)",passiveVoicePresentContinuous);

        ArrayList<String> passiveVoicePresentPerfect = new ArrayList<>();
        passiveVoicePresentPerfect.add("Passive Voice Present Perfect Tense လေ့ကျင့်ခန်း");
        categoryMap.put("Present Perfect -S + have/has been + V3",passiveVoicePresentPerfect);

        ArrayList<String> passiveVoicePresentPerfectContinuous = new ArrayList<>();
        passiveVoicePresentPerfectContinuous.add("Passive Voice Present Perfect Tense လေ့ကျင့်ခန်း");
        categoryMap.put("Present Perfect Continuous- (S + have/has been + being V3",passiveVoicePresentPerfectContinuous);

        ArrayList<String> passiveVoicePastSimple = new ArrayList<>();
        passiveVoicePastSimple.add("Passive Voice past Simple လေ့ကျင့်ခန်း");
        passiveVoicePastSimple.add("Passive Voice Past Simple လေ့ကျင့်ခန်း ၂");
        categoryMap.put("Past Simple (S+ was/were + v3",passiveVoicePastSimple);

        ArrayList<String> passiveVoicePastContinuous = new ArrayList<>();
        passiveVoicePastContinuous.add("Passive Voice Past Continuous လေ့ကျင့်ခန်း");
        categoryMap.put("Past Continuous (S+ was/were + being + V3",passiveVoicePastContinuous);

        ArrayList<String> passiveVoicePastperfect = new ArrayList<>();
        passiveVoicePastperfect.add("Passive Voice Past Perfect လေ့ကျင့်ခန်း");
        categoryMap.put("Past Perfect - (S + had been + v3.",passiveVoicePastperfect);

        ArrayList<String> passiveVoiceFutureSimple = new ArrayList<>();
        passiveVoiceFutureSimple.add("Passive Voice Future Simple Tense Exercise");
        passiveVoiceFutureSimple.add("Passive Voice Future Simple Tense Exercise (Interrogative/Negative)");
        categoryMap.put("Future Simple - (S + will be + v3",passiveVoiceFutureSimple);

        ArrayList<String> passiveVoiceFutureContinuous = new ArrayList<>();
        passiveVoiceFutureContinuous.add("Passive Voice Future Continuous Tense Exercise");
        categoryMap.put("Future Continuous - (S + will be being + v3",passiveVoiceFutureContinuous);


        ArrayList<String> passiveVoiceFuturePerfect = new ArrayList<>();
        passiveVoiceFuturePerfect.add("Passive Voice Future Perfect Tense Exercise");
        categoryMap.put("Future Perfect (S + will have beeen + v3",passiveVoiceFuturePerfect);

        ArrayList<String> nounClause = new ArrayList<>();
        nounClause.add("Noun Clause လေ့ကျင့်ခန်း");
        categoryMap.put("Noun Clause",nounClause);

        ArrayList<String> adjectiveClause = new ArrayList<>();
        adjectiveClause.add("Adjective Clause လေ့ကျင့်ခန်း");
        categoryMap.put("Adjective Clause",adjectiveClause);

        ArrayList<String> AdverbClause = new ArrayList<>();
        AdverbClause.add("Adjective Clause လေ့ကျင့်ခန်း");
        categoryMap.put("Adverb Clause",AdverbClause);

        ArrayList<String> verbEdClause = new ArrayList<>();
        verbEdClause.add("Verb-ed Clause Quiz");
        categoryMap.put("Ve -ed Clause",verbEdClause);

        ArrayList<String> verbIngClause = new ArrayList<>();
        verbIngClause.add("Verb-ing Clause Quiz");
        categoryMap.put("V-ing Clause",verbIngClause);

        ArrayList<String> haveHasQuiz = new ArrayList<>();
        haveHasQuiz.add("Have/has လေ့ကျင့်ခန်း ၁");
        haveHasQuiz.add("Have/has လေ့ကျင့်ခန်း ၂");
        haveHasQuiz.add("Have/has လေ့ကျင့်ခန်း ၃");
        categoryMap.put("Present Tense - Positive", haveHasQuiz);
        categoryMap.put("Present tense - Negative", haveHasQuiz);
        categoryMap.put("Present tense - Question",haveHasQuiz);
        categoryMap.put("Present tense - Negative Question",haveHasQuiz);
        categoryMap.put("Past tense (had)",haveHasQuiz);
        categoryMap.put("have got/has got",haveHasQuiz);

        ArrayList<String> ComparisonQuiz = new ArrayList<>();
        ComparisonQuiz.add("Comparison လေ့ကျင့်ခန်း ၁");
        ComparisonQuiz.add("Comparison လေ့ကျင့်ခန်း ၂");
        ComparisonQuiz.add("Comparison လေ့ကျင့်ခန်း ၃");

        categoryMap.put("Comparative နှင့် Superlative ပြောင်းပုံ",ComparisonQuiz);
        categoryMap.put("Comparison -နှိုင်းယှန်မှု ပုစံ ၁",ComparisonQuiz);
        categoryMap.put("Comparison -နှိုင်းယှန်မှု ပုစံ ၂",ComparisonQuiz);
        categoryMap.put("Comparison -နှိုင်းယှန်မှု ပုစံ ၃",ComparisonQuiz);

    }

    private void noExercise(){
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("No Exercise Available!");
        builder.setMessage("သင်္ခန်းစာလေ့ကျင့်ခန်းမရှိသေးပါ။ Update ကိုစောင့်ပါနော်");
        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.dismiss(); // Dismiss the dialog on OK button click
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(true);
    }


}
