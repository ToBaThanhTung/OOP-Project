package obj;

import java.util.Random;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.org.apache.xerces.internal.util.DraconianErrorHandler;

import Game.InGame;
import MyLog.MyLog;

public class Candy {
	
	Pos pos;
	Image cdImg;
	Random random = new Random();
	public Candy() throws SlickException {
		int n = getTypeCandy();
		
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
