package com.example.childrenGuide;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersFragment extends Fragment {

    MediaPlayer player;
    private final MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.word_list, container, false);

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


        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words,R.color.category_numbers);
        ListView listView = view.findViewById(R.id.root_list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener((adapterView, viewC, i, l) -> {
            Word word = words.get(i);
            player = MediaPlayer.create(getActivity() , word.getAudioResourceId());
            player.start();
            player.setOnCompletionListener(mCompletionListener);
        });
        return view;
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