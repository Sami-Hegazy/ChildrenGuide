package com.example.childrenGuide;

public class Word {
   private String miDefaultTranslation , mMiWokTranslation;
   private int imgResource = NO_IMAGE_PROVIDED;
   private static final int NO_IMAGE_PROVIDED = -1;
   private int audioResourceId;

    public Word(String miDefaultTranslation, String mMiWokTranslation , int audioResourceId) {
        this.miDefaultTranslation = miDefaultTranslation;
        this.mMiWokTranslation = mMiWokTranslation;
        this.audioResourceId = audioResourceId;
    }

    public Word(String miDefaultTranslation, String mMiWokTranslation , int imgResource , int audioResourceId) {
        this.miDefaultTranslation = miDefaultTranslation;
        this.mMiWokTranslation = mMiWokTranslation;
        this.imgResource = imgResource;
        this.audioResourceId = audioResourceId;
    }

    public String getMiDefaultTranslation() {
        return miDefaultTranslation;
    }

    public String getMmiWokTranslation() {
        return mMiWokTranslation;
    }

    public int getImgResource() {
        return imgResource;
    }

    public boolean hasImage(){
        return imgResource != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }
}
