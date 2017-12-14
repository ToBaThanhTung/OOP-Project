package Game;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class MySound {
	public Sound sound;
	String res;
	public MySound(String res) throws SlickException {
		this.res = res;
		sound = new Sound(res);
	}
	
	void myPlaySound() {
		sound.play();
	}
}
