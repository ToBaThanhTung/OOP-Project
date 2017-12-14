package Game;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import obj.TextEffect;

public class MyFont{
	
	
	private Font UIFont1;
	private UnicodeFont uniFont;

	public MyFont(String resFont, float size) {
		try		
		{
	        UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
	        org.newdawn.slick.util.ResourceLoader.getResourceAsStream(resFont));
	        UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, size); 

	        uniFont = new UnicodeFont(UIFont1);
	        uniFont.addAsciiGlyphs();
	        uniFont.getEffects().add(new ColorEffect(java.awt.Color.white)); 
	        uniFont.addAsciiGlyphs();
	        uniFont.loadGlyphs();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	}
	
	public void myFontDraw(float x, float y, String text,  Color col ) {
		uniFont.drawString(x, y, text, col);
	}
	
	
}
