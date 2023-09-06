package siam.moemoetun.com.shwedailyenglish.quiz.frag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.Map;
import siam.moemoetun.com.shwedailyenglish.R;
public class ContentFragment extends Fragment {
    private static final String ARG_SELECTED_CATEGORY = "selectedCategory";

    private String selectedCategory;
    private Map<String, String> categoryHtmlMapping;

    public static ContentFragment newInstance(String selectedCategory) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SELECTED_CATEGORY, selectedCategory);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the selected category from the arguments bundle
        if (getArguments() != null) {
            selectedCategory = getArguments().getString(ARG_SELECTED_CATEGORY);
        }
        // Initialize the category to HTML file mapping
        initializeCategoryHtmlMapping();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_tab, container, false);

        WebView webView = view.findViewById(R.id.webView);

        if (selectedCategory != null && categoryHtmlMapping.containsKey(selectedCategory)) {
            String htmlFileName = categoryHtmlMapping.get(selectedCategory);
            if (htmlFileName != null) {
                String htmlFilePath = "file:///android_asset/tense/" + htmlFileName;
                webView.loadUrl(htmlFilePath);

            }
        }

        return view;
    }

    private void initializeCategoryHtmlMapping() {
        categoryHtmlMapping = new HashMap<>();
        categoryHtmlMapping.put("Nouns -နာမ်", "Nouns.htm");
        categoryHtmlMapping.put("Pronoun - နာမ်စား", "Pronouns.htm");
        categoryHtmlMapping.put("Verbs - ကြိယာ", "verb_definition.htm");
        categoryHtmlMapping.put("Adjective-နာမဝိသေသန", "types of adjectives.htm");
        categoryHtmlMapping.put("Adverb - ကြိယာဝိသေသန", "Adverb.htm");
        categoryHtmlMapping.put("Preposition - ဝိဘတ်", "Preposition.htm");
        categoryHtmlMapping.put("Conjunction - စကားဆက်", "conjunction.htm");
        categoryHtmlMapping.put("Interjection - အာမေဍိတ်", "interjection.htm");
        
        categoryHtmlMapping.put("Be (am,is,are) ရှိသည်၊၊ဖြစ်သည်","verbtobepresent.htm");
        categoryHtmlMapping.put("Be (was/were) ရှိခဲ့သည်၊ဖြစ်ခဲ့သည်","verbtobepast.htm");
        categoryHtmlMapping.put("Be - will be/would be - အနာဂတ်","verbtobefuture.htm");
        categoryHtmlMapping.put("Be- have been/has been - ပြီးစီးကါလ","verbtobeperfect.htm");
        categoryHtmlMapping.put("Be + Adjective","verbtobeadjective.htm");
        categoryHtmlMapping.put("Be + preposition","verbtobepreposition.htm");
        categoryHtmlMapping.put("There + be + Noun. - ရှိသည် (တည်နေရာ)","Therebe.htm");
        categoryHtmlMapping.put("Be + wh Questions","verbtobe_wh_question.html");
        
        categoryHtmlMapping.put("Present Simple (I do - လုပ်သည်)","verb_to_do_simple_present.htm");
        categoryHtmlMapping.put("Present Continuous -I'm doing - လုပ်နေသည်","Present Continuous.htm");
        categoryHtmlMapping.put("Present Perfect - (I've done.  ပြီးပြီ","Present_perfect_verbtodo.htm");
        categoryHtmlMapping.put("Present Perfect Continuous - လုပ်ပြီးနေသည်","PresentPerfectContinuous.htm");
        categoryHtmlMapping.put("Past Simple - I did - လုပ်ခဲ့သည်","simple_past_tense.htm");
        categoryHtmlMapping.put("Past Continuous - I was doing-လုပ်နေခဲ့သည်","Past Continuous.htm");
        categoryHtmlMapping.put("Past Perfect - I had done-လုပ်ခဲ့ပြီးပြီး","Past Perfect Tense.htm");
        categoryHtmlMapping.put("Past Perfect Continuous - လုပ်ပြီး နေခဲ့","Past Perfect Continuous.htm");
        categoryHtmlMapping.put("Future Simple - I will do-လုပ်လိမ့်မယ်","Simple Future Tense.htm");
        categoryHtmlMapping.put("Future Continuous - I'll be doing - လုပ်နေလိမ့်မည်","Future Continuous Tense.htm");
        categoryHtmlMapping.put("Future Perfect - I will have done -လုပ်ပြီးလိမ့်မည်","Future Perfect Tense.htm");
        categoryHtmlMapping.put("Future Perfect Continuous - လုပ်ပြီးပြီး နေလိမ့်မည်","Future Perfect Continuous Tense.htm");


        categoryHtmlMapping.put("Passive Voice အကျည်းချုပ်","Passive_voice.html");
        categoryHtmlMapping.put("Present Simple (S + am/is/are + V3)","Passive Voice Present Simple.htm");
        categoryHtmlMapping.put("Present Continuous (S+ am/is/are + V3)","Passive Voice Present Continuous.htm");
        categoryHtmlMapping.put("Present Perfect -S + have/has been + V3","Passive Voice Present Perfect.htm");
        categoryHtmlMapping.put("Present Perfect Continuous-S + have/has been + being V3","Passive Voice Present Perfect.htm");
        categoryHtmlMapping.put("Past Simple (S+ was/were + v3","Passive Voice Simple Past Tense.htm");
        categoryHtmlMapping.put("Past Continuous (S+ was/were + being + V3","Passive Voice Past Continuous.htm");
        categoryHtmlMapping.put("Past Perfect - (S + had been + v3.","Passive Voice Past Perfect.htm");
        categoryHtmlMapping.put("Past Perfect Continuous (S+ had been being + v3","Passive Voice Past Continuous.htm");
        categoryHtmlMapping.put("Future Simple - (S + will be + v3","Passive Voce Simple Future.htm");
        categoryHtmlMapping.put("Future Continuous - (S + will be being + v3","passive_voice_future_continuous.html");
        categoryHtmlMapping.put("Future Perfect (S + will have beeen + v3","passive_voice_future_perfect.html");
        categoryHtmlMapping.put("Future Perfect Continuous - S + will have been being + v3","");
        categoryHtmlMapping.put("Irregular Verbs to from Passive Voice","irregular_verb.htm");
        categoryHtmlMapping.put("Get နှင့် Passive Voice သုံးပုံ","get_passive_voice.htm");
        categoryHtmlMapping.put("Modal verb နှင့် Passive Voice သုံးပုံ","model_verb_passive_voice.htm");
        categoryHtmlMapping.put("Passive Voice with Agent","passive_voice_with_agent.htm");
        categoryHtmlMapping.put("Passive Voice without Agent","passive_voice_without_agent.htm");
        categoryHtmlMapping.put("Object နှစ်လုံးပါသည့် Passive Voice ပုံစံ","Passive_with_two_object_verb.htm");
        categoryHtmlMapping.put("ကံလိုကြိယာနှင့် ကံမလိုကြိယာ (Transitive and Intransitive Verbs","transitive_verb_n_intransitive_verb.htm");

        categoryHtmlMapping.put("Noun Clause","noun_clause.htm");
        categoryHtmlMapping.put("Adjective Clause","adjective_clause.htm");
        categoryHtmlMapping.put("Adverb Clause","adverb_clause.htm");
        categoryHtmlMapping.put("V-ing Clause","ing_clause.htm");
        categoryHtmlMapping.put("V-ed Clause","ed clausse.htm");


        categoryHtmlMapping.put("Present Tense - Positive","have_has_present-tense_positive.htm");
        categoryHtmlMapping.put("Present tense - Negative","have_has_present_sentse_negative.htm");
        categoryHtmlMapping.put("Present tense - Question","have_has_present_tense_question.htm");
        categoryHtmlMapping.put("Present tense - Negative Question","have_has_present_tense_negative_question.htm");
        categoryHtmlMapping.put("Past tense (had)","verb_tohave_past_tense.htm");
        categoryHtmlMapping.put("have got/has got","have_has_got.htm");


        categoryHtmlMapping.put("Comparative နှင့် Superlative ပြောင်းပုံ","making-comparative-and-superlative.htm");
        categoryHtmlMapping.put("Comparison -နှိုင်းယှန်မှု ပုစံ ၁","as-adj-as-comparison-in-english.htm");
        categoryHtmlMapping.put("Comparison -နှိုင်းယှန်မှု ပုစံ ၂","the-sonnger-the-better.htm");
        categoryHtmlMapping.put("Comparison -နှိုင်းယှန်မှု ပုစံ ၃","the-same-as.htm");

        categoryHtmlMapping.put("Linking verb ဆိုတာဘာလဲ","what-is-linking-verb.html");
        categoryHtmlMapping.put("Go","go.htm");
        categoryHtmlMapping.put("Come","come.htm");
        categoryHtmlMapping.put("Keep","keep.htm");
        categoryHtmlMapping.put("Get","get.htm");
        categoryHtmlMapping.put("Appear/prove/turn out","appear-seem-proved-turn-out.htm");
        categoryHtmlMapping.put("Look, Sound, Smell, Taste, etc","five-senses-verbs.htm");
        categoryHtmlMapping.put("Look like, feel like, smell llike, etc","look-like-feel-like.htm");


        categoryHtmlMapping.put("Adjective + of/to","adjective+preposition-1.htm");
        categoryHtmlMapping.put("Adjective + with/about","adjective+preposition-2.htm");
        categoryHtmlMapping.put("Adjective + of (2)","Adjective+of-2.htm");

        categoryHtmlMapping.put("Will -လိမ့်မည်","will-positive.htm");
        categoryHtmlMapping.put("Will not/won't လိမ့်မည်မဟုတ်","will_negative.htm");
        categoryHtmlMapping.put("Will you? လိမ့်မည်လား","will-question.htm");
        categoryHtmlMapping.put("Can နိုင်သည်","can_positive.htm");
        categoryHtmlMapping.put("Can't - မနိုင်ဘူး","can-genative.htm");
        categoryHtmlMapping.put("Can you? နိုင်သလား","can-question.htm");
        categoryHtmlMapping.put("Should သင့်သည်","should-positive.htm");
        categoryHtmlMapping.put("Shouldn't -မသင့်ဘူး","should-negative.htm");
        categoryHtmlMapping.put("Should you? သင့်သလား","should-question.htm");
        categoryHtmlMapping.put("May ဖြစ်နိုင်သည်","may-positive.htm");
        categoryHtmlMapping.put("May ...? ဖြစ်နိုင်လား","may-question.htm");
        categoryHtmlMapping.put("May not - မဖြစ်နိုင်","may-negative.htm");
        categoryHtmlMapping.put("Must - ရမယ်","must.html");
        categoryHtmlMapping.put("Could - နိုင်ခဲ့သည်","could.htm");
        categoryHtmlMapping.put("Shall we ......? ကြမလားး","shall.htm");
        categoryHtmlMapping.put("Modal Verb + Adjective","modal_verb_adjective.html");
        categoryHtmlMapping.put("Modal Verbs + Preposition","modal_verb_preposition.html");
        categoryHtmlMapping.put("Passive with Modal verbs","model_verb_passive_voice.html");
        categoryHtmlMapping.put("Wh Question with Modal Verbs","model_verb_wh_question.html");

        categoryHtmlMapping.put("how to use v-ing.","how to use v-ing.html");
        categoryHtmlMapping.put("Verb + -ing","Verb-ing.html");
        categoryHtmlMapping.put("prefer and would rather","prefer and would rather.html");
        categoryHtmlMapping.put("preposition + V-ing","Preposition + V-ing_new.html");
        categoryHtmlMapping.put("Verb + -ing or to 1","Verb + ing or to-1.html");
        categoryHtmlMapping.put("Verb + -ing or to 2","verb -ing or to-2.html");
        categoryHtmlMapping.put("Verb + -ing or to 3","Verb + ing or to 3.html");
        categoryHtmlMapping.put("Verb +to..","Verb + to.html");
        categoryHtmlMapping.put("verb + object + to..","Verb object + to.html");
        categoryHtmlMapping.put("verb + object + preposition + v-ing","Verb +object + prep + -ving.html");
        // Add more category HTML mappings as needed
    }
}
