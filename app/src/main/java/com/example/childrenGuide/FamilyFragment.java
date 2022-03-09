package com.example.childrenGuide;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {


    MediaPlayer player;
    private final MediaPlayer.OnCompletionListener mCompletionListener = mediaPlayer -> releaseMediaPlayer();

    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

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

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), words,R.color.category_family);
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