package com.example.childrenGuide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer player;
    private AudioManager mAudionManger;
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                player.pause();
                player.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                player.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    private final MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        // showing the back button in action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        mAudionManger = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Red" , "watetti" , R.mipmap.color_red , R.raw.color_red));
        words.add(new Word("Mustard yellow" , "chiwiite", R.mipmap.color_mustard_yellow , R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow" , "topiise", R.mipmap.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green" , "chokokki", R.mipmap.color_green, R.raw.color_green));
        words.add(new Word("brown" , "takaakki", R.mipmap.color_brown, R.raw.color_brown));
        words.add(new Word("gray" , "topoppi", R.mipmap.color_gray, R.raw.color_gray));
        words.add(new Word("black" , "kululli", R.mipmap.color_black, R.raw.color_black));
        words.add(new Word("white" , "kelelli", R.mipmap.color_white, R.raw.color_white));

        WordAdapter itemsAdapter = new WordAdapter(this, words , R.color.category_colors);
        ListView listView = findViewById(R.id.root_list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            Word word = words.get(i);

            int result = mAudionManger.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            player = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
            player.start();
            player.setOnCompletionListener(mCompletionListener);
        }
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

            mAudionManger.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


}