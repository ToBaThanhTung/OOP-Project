package obj;

public class TextEffect {
	public float x;
	public float y;
	public int typeTextEffect;
	public String str = "Not Set !";
	public boolean isDestroy = false;
	public int counter = 0;
	public float moveSpeedY = 1;
	public int counterDeleteLength = 40;
	public TextEffect(float x, float y, int typeTextEffect, float moveSpeedY, String str) {
		this.x = x;
		this.y = y;
		this.typeTextEffect = typeTextEffect;
		this.moveSpeedY = moveSpeedY;
		this.str = str;
	}
	
	public void update() {
		y += moveSpeedY;
		counter ++;
		if(counter > counterDeleteLength ) {
			isDestroy = true;
		}
	}
	
	
}
