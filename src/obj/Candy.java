package obj;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import Game.InGame;
import MyLog.MyLog;


public class Candy {
	// random candy
	static Random random = new Random();
	// type candy
	public int typeCandy;
	// location canndy
	public float x;
	public float y;
	// is candy active ?
	public boolean isActived = false;
	// is candy falling ?
	public  boolean isFalling = false;
	
	
	public Candy(int typeCandy, float x, float y ) {
		this.typeCandy = typeCandy;
		this.x = x;
		this.y = y;
	}
	// a random function to get random candy
	public static int getTypeCandy() {
		return random.nextInt(6);
	}
	

	
	
	
	
}
