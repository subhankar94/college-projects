import processing.core.PApplet;

/**
 * This class represents a disk or
 * a bubble in the game
 * Probability and Points
 * 
 * @author subhankarghosh
 * @version Dec 06, 2014
 */
class Disk {
	private float xMax;
	private float yMax;
	private float xPos;
	private float yPos;
	private float r;
	
	private float xSpeed;
	private float ySpeed;
	
	private boolean visible = true;
	
	PApplet canvas;

	int col;
	
	/**
	 * Construct a disk based on the key
	 * entered, and enter the maximum
	 * x and y position that the disk can
	 * take to determine a random position
	 * on the canvas to start drawing the
	 * Disk
	 * 
	 * @param PApplet
	 * @param Maximum x position disk can take
	 * @param Maximum y position disk can take
	 * @param key to determine color of disk
	 * 		  and associated points and probability
	 */
	public Disk (PApplet canvas, int xMax, int yMax, int key) {
		this.canvas = canvas;
		this.xMax = xMax;
		this.yMax = yMax;
		this.xPos = canvas.random(0, xMax); 
		this.yPos = canvas.random(0, yMax);
		this.r    = 40;
		xSpeed = canvas.random(-5, 5 );
		if (xSpeed <= 0 && xSpeed > -3) xSpeed -= 1.5;
		if (xSpeed >= 0 && xSpeed <  3) xSpeed += 1.5;
		ySpeed = canvas.random(-5, 5 );
		if (ySpeed <= 0 && ySpeed > -3) ySpeed -= 1.5;
		if (ySpeed >= 0 && ySpeed <  3) ySpeed += 1.5;
		switch (key) {
			case 1: col = canvas.color (181, 219, 190); break;
			case 2: col = canvas.color (120, 170, 156); break;
			case 3: col = canvas.color (174, 198, 207); break;
			case 4: col = canvas.color (119, 158, 203); break;
			case 5: col = canvas.color (194, 59 , 34 ); break;
		}
	}


	/**
	 * This method moves the disk
	 * on the screen and makes it bounce off
	 * the walls if the x & y position
	 * indicate that the disk is about
	 * to hit the wall
	 */
	public void move() {
		// update position based on speed
		xPos += xSpeed;
		yPos += ySpeed;
		// bouncing logic - see if the ball hits any of the edges
		// note how we are referring to the width and height
		// instance vars on the PApplet object
		if (xPos > xMax - r/2) {
			xPos = xMax - r/2;
			xSpeed *= -1;
		}
		if (xPos < r/2) {
			xPos = r/2;
			xSpeed *= -1;
		}
		if (yPos > yMax - r/2) {
			yPos = yMax - r/2;
			ySpeed *= -1;
		}
		if (yPos < r/2) {
			yPos = r/2;
			ySpeed *= -1;
		}
		// call the ellipse method on the PApplet object to draw
		// this object to the canvas
		canvas.fill(col);
		this.canvas.ellipse(xPos, yPos, r, r);
	}
	
	/**
	 * Set the visible variable
	 * to false
	 */
	public void setInvisible() {
		this.visible = false;
	}

	/**
	 * @return boolean value of whether
	 * 		   disk is visible
	 */
	public boolean isVisible() {
		if (this.visible)
			return true;
		else
			return false;
	}
	
	/**
	 * @return x position of the disk
	 */
	public float getxPos () {
		return this.xPos;
	}
	
	
	/**
	 * @return y position of the disk
	 */
	public float getyPos () {
		return this.yPos;
	}
	

}