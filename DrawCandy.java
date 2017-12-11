package obj;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Game.InGame;
import sun.net.www.content.text.plain;

public class DrawCandy {
	public static final int lengthColum = 9;
	public static final int lengthRow = 9;
	public Matrix2D matrix2d;
	public Image cdImg;
	
	public Color color;
	public DrawCandy() throws SlickException, InterruptedException {
		this.matrix2d = InGame.matrix2d;
		 for(int i = 0; i < lengthRow; i++) {
			 for(int j = 0; j < lengthColum; j++) {
				boolean isClicked = matrix2d.MT[i][j].isActived;
				 if(!isClicked) {
					 if(matrix2d.MT[i][j].typeCandy<10)
					 {
					 myDraw(i, j);}
				 }
				 else if(isClicked ) {
					 mydrawClicked(i, j);
				 }
				 
				
			 }
		 }
	}
	
	public void getImg(int i, int j) throws SlickException {
		int n = matrix2d.MT[i][j].typeCandy;
		
		if(n == 5 ) {
			cdImg = new Image("images/Redcandy.png");	
				
		}
		else if(n == 1) {
			cdImg = new Image("images/blueCandy.png");
		}
		else if(n == 0) {
			cdImg = new Image("images/Greencandy.png");
		}
		else if(n == 2) {
			cdImg = new Image("images/Yellowcandy.png");
		}
		else if(n == 3) {
			cdImg = new Image("images/Orangecandy.png");
		}
		else if(n == 4) {
			cdImg = new Image("images/Lightbluecandy.png");
		}		
		
	}
	
	public void myDraw(int i, int j) throws SlickException {
		getImg(i, j);
		cdImg.draw( 150 + 80 * j, 150 + 80 * i);
	}
	
	public void mydrawClicked(int i, int j) throws SlickException {
		getImg(i, j);
		cdImg.draw( 150 + 80 * j, 150 + 80 * i, Color.gray);
	}
}
