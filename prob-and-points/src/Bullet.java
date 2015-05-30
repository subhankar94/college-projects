import processing.core.PApplet;

/**
 * This class represents a bullet to be
 * shot by the gun in the Probability & Points
 * game.
 * 
 * @author subhankarghosh
 * @version Dec 06, 2014
 */
class Bullet {
	float xPos;
	private float yPos;
	private final int r = 15;
	
	private final int ySpeed = 8;
	
	private boolean visible = true;
	
	PApplet canvas;
	
	/**
	 * Constructs a bullet based on the
	 * xPos and yPos parameter entered
	 * in the constructor.
	 * 
	 * @param PApplet
	 * @param x position
	 * @param y position
	 */
	public Bullet (PApplet canvas, int xPos, int yPos) {
		this.canvas = canvas;
		this.xPos = xPos; 
		this.yPos = yPos;
	}
	
	/**
	 * This method moves the bullet
	 * that it is called on in the
	 * y direction only.
	 */
	public void shoot () {
		yPos -= ySpeed;
		canvas.fill(255, 179, 71);
		this.canvas.ellipse(xPos, yPos, r, r);
	}
	
	/**
	 * @return boolean value of whether
	 * 		   bullet is visible
	 */
	public boolean isVisible() {
		if (this.visible)
			return true;
		else
			return false;
	}
	
	/**
	 * Set the visible variable
	 * to false
	 */
	public void setInvisible() {
		this.visible = false;
	}
	
	/**
	 * @return x position of the bullet
	 */
	public float getxPos () {
		return this.xPos;
	}
	
	/**
	 * @return y position of the bullet
	 */
	public float getyPos () {
		return this.yPos;
	}

}
