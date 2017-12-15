package obj;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.javafx.geom.transform.GeneralTransform3D;
import com.sun.org.apache.xerces.internal.util.DraconianErrorHandler;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac.IntegrityHmacSHA256;

import Game.InGame;
import MyLog.MyLog;
import sun.security.action.GetBooleanAction;

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
