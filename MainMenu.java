package Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.Color;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
public class MainMenu extends BasicGameState {
	
	private Image imgMainMenu;
	private Image play;
	
	
	 
	
	@Override
	public void init(GameContainer ag, StateBasedGame sbg) throws SlickException {
		imgMainMenu = new Image("images/mainMenu.jpg");
		play = new Image("images/Play-button.png");
	}

	@Override
	public void render(GameContainer ag, StateBasedGame sbg, Graphics g) throws SlickException {
		imgMainMenu.draw(0, 0);
		
		int posX= Mouse.getX();
		int posY=Mouse.getY();
		String mouse= "X:"+ posX +"Y:" + posY;
		g.drawString(mouse,50,500);
		if(posX>300&& posX<556&&posY>319&&posY<400)
		{play.draw(300,500,org.newdawn.slick.Color.gray);

		if(Mouse.isButtonDown(0))
			{
			 sbg.enterState(1);
			}
		}
		else {
			play.draw(300,500);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		
		
		
		
	}

	@Override
	public int getID() {
		return 0;
	}
	
	
}
