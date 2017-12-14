package obj;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Game.CongfigGame;
import Game.InGame;
import Game.MyFont;
import sun.net.www.content.text.plain;

public class DrawCandy {
	
	
	public Matrix2D matrix2d;
	public ArrayList<TextEffect> textEffectArr;
	public Image cdImg;
	public Color color;
	
	//public static int gravity = 1; 
	public static float speed = 10;
	//MyFont scoreApear = new MyFont("font/Snowdrift.ttf", 25 );
	
	public DrawCandy() throws SlickException {
		this.matrix2d = InGame.matrix2d;
		this.textEffectArr = InGame.textEffectsArr;
		// candy
		 for(int i = 0; i < CongfigGame.lengthRow; i++) {
			 for(int j = 0; j < CongfigGame.lengthColum; j++) {
				 if(matrix2d.MT[i][j].x >= 145) {
					 boolean isClicked = matrix2d.MT[i][j].isActived;
					 if(!isClicked) {
						 myDraw(i, j);
					 }
					 else if(isClicked ) {
						 mydrawClicked(i, j);
					 }
					 
				 }
				
			 }
		 }
		 // score appear
		
		 for(TextEffect textEffect : InGame.textEffectsArr) {
			textEffect.update();
			if(textEffect.typeTextEffect == 0) {
				InGame.scoreApear.myFontDraw(textEffect.x, textEffect.y, textEffect.str, Color.green);
			}
			if(textEffect.typeTextEffect == 1) {
				InGame.scoreApear.myFontDraw(textEffect.x, textEffect.y, textEffect.str, Color.blue);
			}
			if(textEffect.typeTextEffect == 2) {
				InGame.scoreApear.myFontDraw(textEffect.x, textEffect.y, textEffect.str, Color.yellow);
			}
			if(textEffect.typeTextEffect == 3) {
				InGame.scoreApear.myFontDraw(textEffect.x, textEffect.y, textEffect.str, Color.orange);
			}
			if(textEffect.typeTextEffect == 4) {
				InGame.scoreApear.myFontDraw(textEffect.x, textEffect.y, textEffect.str, Color.lightGray);
			}
			if(textEffect.typeTextEffect == 5) {
				InGame.scoreApear.myFontDraw(textEffect.x, textEffect.y, textEffect.str, Color.red);
			}
			
			
		 }
		 
		 
	}
	public void getImg(int i, int j) throws SlickException {
		int n = matrix2d.MT[i][j].typeCandy;
		if(n<10)
		{
		//cdImg.draw(x, y, width, height);
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
}
	
	public void myDraw(int i, int j) throws SlickException {
		getImg(i, j);
		cdImg.draw( matrix2d.MT[i][j].y , matrix2d.MT[i][j].x);
	}
	
	public void mydrawClicked(int i, int j) throws SlickException {
		getImg(i, j);
		cdImg.draw( matrix2d.MT[i][j].y , matrix2d.MT[i][j].x, Color.gray);
	}
}
