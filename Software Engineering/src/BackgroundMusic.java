import java.io.*;
import javax.sound.sampled.*;

/**
 * This is the sound control class for the game.
 * @author DAT Software Engineering
 *
 */
public class BackgroundMusic {
	private int current = 0; //The current song playing.
	public Clip clip;
	private String[] music = new String[3];
	
	public BackgroundMusic() {
		try {
	         // Open an audio input stream.
			 
	         music[0] = "intro.wav";
	         music[1] = "song_1.wav";
	         music[2] = "cherubim.wav";
	         //music[2] = AudioSystem.getAudioInputStream(new BufferedInputStream(BackgroundMusic.class.getResourceAsStream("cherubim.wav")));

	         
	         clip = AudioSystem.getClip();
	         AudioInputStream song = AudioSystem.getAudioInputStream(new BufferedInputStream(BackgroundMusic.class.getResourceAsStream(music[0])));
	         clip.open(song);
	         clip.start();
	         clip.addLineListener(new MusicRepeater());
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
	
	/**
	 * This is the listener that allows for the songs to replay and for the next one to be played.
	 */
	class MusicRepeater implements LineListener {
		public void update(LineEvent evt) {
			if (evt.getType() == LineEvent.Type.STOP) {
				clip.close(); //Close the last clip
				try {
					clip = AudioSystem.getClip();
					clip.addLineListener(new MusicRepeater());
				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				}
				
		        current++;
				if (current >= music.length) {
					current = 0;
				} 
				
				AudioInputStream song = null;
				try { //Open the audio stream.
					song = AudioSystem.getAudioInputStream(new BufferedInputStream(BackgroundMusic.class.getResourceAsStream(music[current])));
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				try { //Try to open the next clip
					clip.open(song);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				clip.start(); //Start the song.
				System.out.println("Next Song!");
			}
			
		}
    	 
     }
	
}
