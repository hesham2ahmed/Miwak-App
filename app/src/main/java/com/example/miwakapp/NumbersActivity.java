package com.example.miwakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    private AudioManager audioManager ;

    AudioManager.OnAudioFocusChangeListener changeListenerObject =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }
                    else if ( focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }

                }
            };


    // on create methode
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        // Create service to request audio focus
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        // Create a list of words
        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("One", "Lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new word("Two", "Ottiko", R.drawable.number_two, R.raw.number_two));
        words.add(new word("Three", "Tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new word("Four", "Oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new word("Five", "Massoka", R.drawable.number_five, R.raw.number_five));
        words.add(new word("Six", "Temmoka", R.drawable.number_six, R.raw.number_six));
        words.add(new word("Seven", "Kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new word("Eight", "Kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new word("Nine", "Wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new word("Ten", "Na'aacha'", R.drawable.number_ten, R.raw.number_ten));


        wordadapter itemsAdapter = new wordadapter(this, words, R.color.category_numbers);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long ID) {

                word item = words.get(position);

                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(changeListenerObject,audioManager.STREAM_MUSIC,audioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_GAIN){
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, item.getAudio());
                    ImageView imageView = view.findViewById(R.id.image_view);
                    imageView.setImageResource(R.drawable.baseline_pause_white_18);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            //
            audioManager.abandonAudioFocus(changeListenerObject);
        }
    }

    private void returnPlayImage(View view){
        ImageView imageView = findViewById(R.id.image_view);
        imageView.setImageResource(R.drawable.ic_play_arrow);
    }
}

