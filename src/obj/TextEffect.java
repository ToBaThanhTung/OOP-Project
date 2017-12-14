package obj;

public class TextEffect {
	public float x;
	public float y;
	public int typeTextEffect;
	public String str = "Not Set !";
	public boolean isDestroy = false;
	public float counter = 0;
	public float moveSpeedY = 1;
	public int counterDeleteLength = 60;
	public int typeScore;
	public boolean moveScore = false;
	public TextEffect(float x, float y, int typeTextEffect, float moveSpeedY, String str, int typeScore) {
		this.x = x;
		this.y = y;
		this.typeTextEffect = typeTextEffect;
		this.moveSpeedY = moveSpeedY;
		this.str = str;
		this.typeScore = typeScore;
	}
	
	public void update() {
		y += moveSpeedY;
		counter += 0.5f;
		if(counter > counterDeleteLength ) {
			isDestroy = true;
		}
	}
	
	
}
