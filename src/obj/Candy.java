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
	
	Pos pos;
	Image cdImg;
	int n;
	Random random = new Random();
	public Candy() throws SlickException {
		n = getTypeCandy();		
		if(n == 5 ) {
			cdImg = new Image("images/Redcandy.png");
			//cdImg.draw(30, 30);
			
		}
		else if(n == 1) {
			cdImg = new Image("images/Darkbluecandy.png");
			//cdImg.draw(30, 30);
		}
		else if(n == 0) {
			cdImg = new Image("images/Greencandy.png");
		//	cdImg.draw(30, 30);
		}
		else if(n == 2) {
			cdImg = new Image("images/Yellowcandy.png");
			//cdImg.draw(30, 30);
		}
		else if(n == 3) {
			cdImg = new Image("images/Orangecandy.png");
		//	cdImg.draw(30, 30);
		}
		else if(n == 4) {
			cdImg = new Image("images/Lightbluecandy.png");
		//	
		}
	}
	
	public int getN() {
		return n;
	}
	public void removeImg() throws SlickException {
		
	}
	
	void showMtrix() {
		
	}
	
	
	public Candy(Pos pos) {
		this.pos = pos;
	}
		
	
	public int getTypeCandy() {
		return random.nextInt(6);
		
	}
	
	
	public void myDraw(int x, int y) {
		cdImg.draw(x, y);
	}
	
	
	
	
}
