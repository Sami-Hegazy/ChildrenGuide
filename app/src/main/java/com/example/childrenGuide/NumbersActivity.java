package com.example.childrenGuide;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

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
        words.add(new Word("one", "lutti", R.mipmap.number_one , R.raw.number_one));
        words.add(new Word("Two", "otiiko", R.mipmap.number_two, R.raw.number_two));
        words.add(new Word("three", "toLookosu", R.mipmap.number_three, R.raw.number_three));
        words.add(new Word("Four", "oyyisa", R.mipmap.number_four, R.raw.number_four));
        words.add(new Word("Five", "massokka", R.mipmap.number_five, R.raw.number_five));
        words.add(new Word("Six", "temmokka", R.mipmap.number_six, R.raw.number_six));
        words.add(new Word("Seven", "kenekaku", R.mipmap.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "kawinta", R.mipmap.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "wo'e", R.mipmap.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "na'aacha", R.mipmap.number_two, R.raw.number_ten));


        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_numbers);
        ListView listView = findViewById(R.id.root_list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Word word = words.get(i);
            player = MediaPlayer.create(NumbersActivity.this , word.getAudioResourceId());
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