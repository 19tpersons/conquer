import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class BackgroundMusic {
	private int current = 0; //The current song playing.
	
	public BackgroundMusic() {
		try {
	         // Open an audio input stream.
			 AudioInputStream[] music = new AudioInputStream[3];
			 
	         music[0] = AudioSystem.getAudioInputStream(new File("Game Music/intro.wav"));
	         music[1] = AudioSystem.getAudioInputStream(new File("Game Music/song_1.wav"));
	         music[2] = AudioSystem.getAudioInputStream(new File("Game Music/cherubim.wav"));

	         
	         Clip clip = AudioSystem.getClip();
	         clip.open(music[0]);
	         clip.start();
	         clip.addLineListener(new LineListener() {
				public void update(LineEvent arg0) {
					if (!clip.isActive()) {
						current++;
						try {
							clip.open(music[current]);
						} catch (LineUnavailableException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						clip.start();
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
