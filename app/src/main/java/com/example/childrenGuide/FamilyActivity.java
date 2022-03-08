package com.example.childrenGuide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        words.add(new Word("Father" , "tinao oyaase'ne" , R.mipmap.family_father , R.raw.family_father));
        words.add(new Word("Mother" , "oyaaset",R.mipmap.family_mother,R.raw.family_mother));
        words.add(new Word("GrandFather" , "michaksas" , R.mipmap.family_grandfather,R.raw.family_grandfather));
        words.add(new Word("GrandMother" , "kuch achit" , R.mipmap.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("Older brother" , "hee eeneem" , R.mipmap.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("Older Sister" , "eeneem",R.mipmap.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("Family Son" , "yoowutis",R.mipmap.family_son,R.raw.family_son));
        words.add(new Word("Younger Brother" , "yoowutis",R.mipmap.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("Younger sister" , "yoowutis",R.mipmap.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("Daughter" , "yoowutis",R.mipmap.family_daughter,R.raw.family_daughter));

        WordAdapter itemsAdapter = new WordAdapter(this, words,R.color.category_family);
        ListView listView = findViewById(R.id.root_list_view);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Word word = words.get(i);
            player = MediaPlayer.create(FamilyActivity.this , word.getAudioResourceId());
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