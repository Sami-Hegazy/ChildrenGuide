package com.example.childrenGuide;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment {

    MediaPlayer player;
    private final MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();

    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?" , "minto wuksus" , R.raw.phrase_where_are_you_going));
        words.add(new Word("What's Your name?" , "tinao oyaase'ne", R.raw.phrase_what_is_your_name));
        words.add(new Word("my name is ..." , "oyaaset", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?" , "michaksas", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good." , "kuch achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Yes , I'm coming" , "hee eeneem", R.raw.phrase_im_coming));
        words.add(new Word("I'm coming" , "eeneem", R.raw.phrase_im_coming));
        words.add(new Word("let's go" , "yoowutis", R.raw.phrase_lets_go));

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words , R.color.category_phrases);
        ListView listView = rootView.findViewById(R.id.root_list_view);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Word word = words.get(i);
            player = MediaPlayer.create(getActivity() , word.getAudioResourceId());
            player.start();
            player.setOnCompletionListener(mCompletionListener);
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
        }
    }
}