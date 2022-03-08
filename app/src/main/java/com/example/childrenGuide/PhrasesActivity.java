package com.example.childrenGuide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer player;
    private final MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        // showing the back button in action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?" , "minto wuksus" , R.raw.phrase_where_are_you_going));
        words.add(new Word("What's Your name?" , "tinao oyaase'ne", R.raw.phrase_what_is_your_name));
        words.add(new Word("my name is ..." , "oyaaset", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?" , "michaksas", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good." , "kuch achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Yes , I'm coming" , "hee eeneem", R.raw.phrase_im_coming));
        words.add(new Word("I'm coming" , "eeneem", R.raw.phrase_im_coming));
        words.add(new Word("let's go" , "yoowutis", R.raw.phrase_lets_go));

        WordAdapter itemsAdapter = new WordAdapter(this, words , R.color.category_phrases);
        ListView listView = findViewById(R.id.root_list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Word word = words.get(i);
            player = MediaPlayer.create(PhrasesActivity.this , word.getAudioResourceId());
            player.start();
            player.setOnCompletionListener(mCompletionListener);
        });
    }


    //_____________________________back Button in actionBar_________________________________________
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (player != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            player.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            player = null;
        }
    }
}