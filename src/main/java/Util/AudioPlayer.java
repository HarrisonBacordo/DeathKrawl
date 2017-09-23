package Util;

import com.badlogic.gdx.Audio;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String s) {
        try {
            AudioInputStream inputStream =
                    AudioSystem.getAudioInputStream(
                            getClass().getResourceAsStream("/Audio/" + s));
            AudioFormat baseFormat = inputStream.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
            AudioInputStream decodedInputStream = AudioSystem.getAudioInputStream(decodeFormat, inputStream);
            clip = AudioSystem.getClip();
            clip.open(decodedInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if(clip == null) {
            return;
        }
        stop();
        clip.setFramePosition(0);
        clip.start();

    }

    public void stop() {
        if(clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.close();
    }
}
