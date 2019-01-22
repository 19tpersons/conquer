import java.io.*;
import javax.sound.sampled.*;

/**
 * This is the sound control class for the game.
 * @author DAT Software Engineering
 *
 */
public class BackgroundMusic {
	private int current = 0; //The current song playing.
	public static Clip clip;
	
	public BackgroundMusic() {
		try {
	         // Open an audio input stream.
			 AudioInputStream[] music = new AudioInputStream[3];
			 
	         music[0] = AudioSystem.getAudioInputStream(new BufferedInputStream(BackgroundMusic.class.getResourceAsStream("intro.wav")));
	         music[1] = AudioSystem.getAudioInputStream(new BufferedInputStream(BackgroundMusic.class.getResourceAsStream("song_1.wav")));
	         music[2] = AudioSystem.getAudioInputStream(new BufferedInputStream(BackgroundMusic.class.getResourceAsStream("cherubim.wav")));

	         
	         clip = AudioSystem.getClip();
	         clip.open(music[0]);
	         clip.start();
	         clip.addLineListener(new LineListener() {
				public void update(LineEvent arg0) {
					if (!clip.isActive()) {
				        clip.close(); //Close the last clip
						
				        current++;
						if (current >= music.length) {
							current = 0;
						} 
						
						try { //Try to open the next clip
							clip.open(music[current]);
						} catch (LineUnavailableException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						clip.start();
						System.out.println("Next Song!");
					}
					
				}
	        	 
	         });
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	}
}
