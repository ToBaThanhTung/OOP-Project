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
	
	
	static Random random = new Random();
	public int typeCandy;
	
	public float x;
	public float y;
	
	public boolean isActived = false;
	public  boolean isFalling = false;
	
	public Candy(int typeCandy, float x, float y ) {
		this.typeCandy = typeCandy;
		this.x = x;
		this.y = y;
	}
	
	public static int getTypeCandy() {
		return random.nextInt(6);
		
	}
	

	
	
	
	
}
