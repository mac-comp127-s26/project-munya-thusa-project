import javax.sound.sampled.*; 
import java.io.File;

// referred to https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java ; //https://docs.oracle.com/javase/8/docs/api/javax/sound/sampled/package-summary.html

// Music class
// plays background music for the game

public class Music {

    private Clip clip;


    public Music(String fileName) {

        try {

            File file = new File(fileName);

            AudioInputStream audio =
                AudioSystem.getAudioInputStream(file);

            clip = AudioSystem.getClip();
            clip.open(audio);

        } catch (Exception e) {

            System.out.println("Could not load music");
        }
    }


    // start music 
    public void play() {

        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }


    // stop music
    public void stop() {

        if (clip != null) {
            clip.stop();
        }
    }
}