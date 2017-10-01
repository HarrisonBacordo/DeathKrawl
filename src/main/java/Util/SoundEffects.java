package Util;

/**
 * Holds the file names for each sound effect
 *
 * PRIMARY AUTHOR: Harrison Bacordo (bacordharr)
 */
public enum SoundEffects {
    BAP("bap.wav"),
    BOOM("boom.wav"),
    BSH("bsh.wav"),
    SKRRRA("skrrra.wav");

    String filename;

    SoundEffects(String filename) {
        this.filename = filename;
    }

    /**
     * @return - the filename associated with this enum
     */
    public String getValue() {
        return filename;
    }
}
