package utils;

import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;

public final class Audio {

    // PRIVATE ACCESS CONSTRUCTOR
    private Audio(){}

    /**
     * Plays .WAV audio file.
     * @param path Path to .WAV audio file.
     */
    public static void playAudio(String path){
        File f = new File(path);
        try{
            Clip c = AudioSystem.getClip();
            c.open(AudioSystem.getAudioInputStream(f));
            c.start();
        }
        catch (IOException | UnsupportedAudioFileException | LineUnavailableException ignored){}
    }
}
