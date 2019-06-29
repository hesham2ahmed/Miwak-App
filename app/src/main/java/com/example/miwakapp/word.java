package com.example.miwakapp;

import android.widget.TextView;

public class word {

    private String english;
    private String miwok;
    private int image = no_image;
    private static final int no_image = -1;
    private int audio_id;

    public word(String englishtranslation,String mitranslation,int imageId,int audioId){
        this.english = englishtranslation;
        this.miwok = mitranslation;
        this.image = imageId;
        this.audio_id=audioId;
    }

    public word(String englishtranslation,String mitranslation,int audioId){
        this.english = englishtranslation;
        this.miwok = mitranslation;
        this.audio_id=audioId;
    }

    public String getEnglishtrans(){
        return english;
    }

    public String getMiwok(){
        return miwok;
    }
    public int getImageId(){
        return image;
    }

    public boolean hasimage(){
        return image!= no_image;
    }

    public int getAudio(){return audio_id;}


}
