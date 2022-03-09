package com.example.childrenGuide;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {

    private MediaPlayer player;
    private AudioManager mAudionManger;
    private final MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();
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

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.word_list, container, false);

        mAudionManger = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Red" , "watetti" , R.mipmap.color_red , R.raw.color_red));
        words.add(new Word("Mustard yellow" , "chiwiite", R.mipmap.color_mustard_yellow , R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow" , "topiise", R.mipmap.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green" , "chokokki", R.mipmap.color_green, R.raw.color_green));
        words.add(new Word("brown" , "takaakki", R.mipmap.color_brown, R.raw.color_brown));
        words.add(new Word("gray" , "topoppi", R.mipmap.color_gray, R.raw.color_gray));
        words.add(new Word("black" , "kululli", R.mipmap.color_black, R.raw.color_black));
        words.add(new Word("white" , "kelelli", R.mipmap.color_white, R.raw.color_white));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words , R.color.category_colors);
        ListView listView = rootView.findViewById(R.id.root_list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            Word word = words.get(i);

            int result = mAudionManger.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                player = MediaPlayer.create(getContext(), word.getAudioResourceId());
                player.start();
                player.setOnCompletionListener(mCompletionListener);
            }
        });

        return rootView;
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