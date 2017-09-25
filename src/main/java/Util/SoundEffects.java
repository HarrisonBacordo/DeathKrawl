package Util;

public enum SoundEffects {
    BAP("bap.wav"),
    BOOM("boom.wav"),
    BSH("bsh.wav"),
    SKRRRA("skrrra.wav");

    String filename;

    SoundEffects(String filename) {
        this.filename = filename;
    }

    public String getValue() {
        return filename;
    }
}
