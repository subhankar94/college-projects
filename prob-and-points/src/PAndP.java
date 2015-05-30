import processing.core.PApplet;
import java.util.Random;

/**
 * This programs is a game in which the goal is to
 * get as many points as possible.
 * Points are scored by shooting bullets,
 * which are a representation of the Bullet class,
 * at bubbles, which are representations 
 * of the Disk class. The gun's movements and
 * the shooting is controlled by the user.
 * 
 * @author subhankarghosh
 * @version Dec 06, 2014
 */

public class PAndP extends PApplet {
	
	// Canvas properties
	int xWindow = 1000;
	int yWindow = 500;
	// Gameplay window properties
	int xMax = 750;
	int yMax = 500;
	
	// Gun properties
	// size
	float xBaseSize = 75;
	float yBaseSize = 5;
	float xNozzleSize = 6;
	float yNozzleSize = 6;
	// position
	float xGunPos = xMax/2 - xBaseSize/2;
	float yGunPos = yMax - yBaseSize;
	float xNozzlePos = xGunPos + xBaseSize/2 - xNozzleSize/2;
	float yNozzlePos = yMax - yBaseSize - yNozzleSize; 
	// increment
	float xGuni = 5;
	
	// Disk reference variable & counters 
	Disk d1  = new Disk (this, xMax, yMax, 1);
	Disk d2  = new Disk (this, xMax, yMax, 2);
	Disk d3  = new Disk (this, xMax, yMax, 3);
	Disk d4  = new Disk (this, xMax, yMax, 4);
	Disk d51 = new Disk (this, xMax, yMax, 5);
	Disk d52 = new Disk (this, xMax, yMax, 5);
	Disk d53 = new Disk (this, xMax, yMax, 5);
	Disk d54 = new Disk (this, xMax, yMax, 5);
	int d1i = 0;
	int d2i = 0;
	int d3i = 0;
	int d4i = 0;
	int d5i = 0;

	Bullet [] bullet = new Bullet [50];
	int bi = 0;
	
	boolean calculate = false;
	int total = 0;

	// Method to ensure that only one command
	// of the UP key is sent at a time
	boolean upKey = false;
	public void keyPressed () {
		if (keyCode == UP)
			upKey = true;
	}
	
	// Time limit set to 2 minutes
	long startTime = System.currentTimeMillis();
	long endTime   = startTime + 60000L;
	
	// Set up probability evaluator to calculate score
	Random rand = new Random ();
	
	boolean gameOn = false;
	boolean gameStart = true;

	// Collision method 
	public void collision (Disk d, Bullet b) {
		if (b.isVisible() && d.isVisible()) {	
			float xRelative = b.getxPos() - d.getxPos();
			float yRelative = b.getyPos() - d.getyPos();
			if ((xRelative > -26 && xRelative < 26) && (yRelative > -26 && yRelative < 26)) {
				b.setInvisible();
				d.setInvisible();
			} 
		}		
	}

	public void setup () {
		size (xWindow, yWindow);
		frameRate(60f);
	}

	public void draw () {
		// Set background color
		background (255, 255, 240);
		stroke(255, 255, 240);

		// Side pane to hold information 
		fill(163, 163, 155);
		rect(xMax, -1, xWindow + 1, yWindow + 1);

		// Gun movements
		if ( keyPressed && key == CODED ) {
			if ( keyCode == LEFT ) {
				xGunPos = xGunPos - xGuni;
				xNozzlePos = xGunPos + xBaseSize/2 - xNozzleSize/2;
			} else if (keyCode == RIGHT) {
				xGunPos = xGunPos + xGuni;
				xNozzlePos = xGunPos + xBaseSize/2 - xNozzleSize/2;
			}	
			
			if (xGunPos < 0 ) {
				xGunPos = 0;
				xNozzlePos = xGunPos + xBaseSize/2 - xNozzleSize/2;
			} else if (xGunPos + xBaseSize > xMax) {
				xGunPos = xMax - xBaseSize;
				xNozzlePos = xGunPos + xBaseSize/2 - xNozzleSize/2;
			}	
		}
		
		// Draw gun & nozzle
		rect (xGunPos, yGunPos, xBaseSize, yBaseSize);
		rect (xNozzlePos, yNozzlePos, xNozzleSize, yNozzleSize);
			
		if (gameStart) {
			textAlign (CENTER);
			text ("INSTRUCTIONS:\n"
				+ "Use the gun to shoot at the\n"
				+ "blue and green bubbles\n"
				+ "DON'T SHOOT THE RED BUBBLES!\n"
				+ "each bubble has an associated\n"
				+ "points value and probability value\n"
				+ "of getting the points if you\n"
				+ "manage to shoot it.\n\n"
				+ "CONTROLS:\n"
				+ "UP: shoot\n"
				+ "LEFT: move gun left\n"
				+ "RIGHT: move gun right\n\n"
				+ "Press the space bar to start.\n"
				+ "You have one minute."
				, 325, 125);
			textAlign (LEFT);
			if ( keyPressed && key == ' ' ) {
				gameStart = false;
				gameOn    = true;
			}
		} else if (gameOn) {	
			// shoot bullet and check for collision using for loop
			if (upKey && bi < bullet.length) {
				bullet[bi] = new Bullet (this, (int) (xNozzlePos + xNozzleSize/2), (int) (yMax - yNozzleSize - yBaseSize));
				bi++;
				upKey = false;
			}
			for (int j = 0; j < bi; j++) {
				if (bullet[j].isVisible())
					bullet[j].shoot();
				collision (d1,  bullet[j]);
				collision (d2,  bullet[j]);
				collision (d3,  bullet[j]);
				collision (d4,  bullet[j]);
				collision (d51, bullet[j]);
				collision (d52, bullet[j]);
				collision (d53, bullet[j]);
				collision (d54, bullet[j]);
			}
	
			// If disks are visible, draws them
			// If disks are not visible, i.e., after a collision,
			// reassign reference variable to new disk
			// and increment respective disk counter by 1
			if (d1.isVisible()) d1.move();
			else if (!d1.isVisible()) {
				d1 = new Disk (this, xMax, yMax, 1);
				d1i++;
			}
			if (d2.isVisible()) d2.move();	
			else if (!d2.isVisible()) {
				d2 = new Disk (this, xMax, yMax, 2);
				d2i++;
			}
			if (d3.isVisible()) d3.move();	
			else if (!d3.isVisible()) {
				d3 = new Disk (this, xMax, yMax, 3);
				d3i++;
			}
			if (d4.isVisible()) d4.move();
			else if (!d4.isVisible()) {
				d4 = new Disk (this, xMax, yMax, 4);
				d4i++;
			}
			if (d51.isVisible()) d51.move();
			else if (!d51.isVisible()){
				d51 = new Disk (this, xMax, yMax, 5);
				d5i++;
			}
			if (d52.isVisible()) d52.move();
			else if (!d52.isVisible()){
				d52 = new Disk (this, xMax, yMax, 5);
				d5i++;
			}
			if (d53.isVisible()) d53.move();
			else if (!d53.isVisible()){
				d53 = new Disk (this, xMax, yMax, 5);
				d5i++;
			}
			if (d54.isVisible()) d54.move();
			else if (!d54.isVisible()){
				d54 = new Disk (this, xMax, yMax, 5);
				d5i++;
			}
			
			// Check time limit & # of bullets remaining
			if ((System.currentTimeMillis() > endTime) || bi == 50) gameOn = false;
			if (!gameOn) calculate = true;
		} else if (calculate) {
			// boolean value calculate allows this block
			// to run only once in the draw loop because
			// it is turned to true as soon as gameOn is
			// false, after which the total is calculated
			// and boolean calculate is set to false.
			total = total + d5i*-100;
			// calculate total score with probability values accounted for
			for (int i = 0; i <= d1i; i++) {
				if (rand.nextInt(10) <= 2)
					total += 300;
			}
			for (int i = 0; i <= d2i; i++) {
				if (rand.nextInt(10) <= 4)
					total += 150;
			}
			for (int i = 0; i <= d3i; i++) {
				if (rand.nextInt(10) <= 6)
					total += 100;
			}
			for (int i = 0; i <= d4i; i++) {
				if (rand.nextInt(10) <= 8)
					total += 75;
			}
			calculate = false;
		} else {
			fill (163, 163, 155);
			text ("Game Over\n"
				+ "Score : " + total, xMax/2, yMax/2);
		}
		
		// Information on side pane
		fill (255);
		text ("Bullets Remaining : " + (bullet.length - bi), xMax + 63, yMax - 425);
		text ("Probability    Points",    xMax + 100, yMax - 375);
		text ("    0.2           300   ", xMax + 100, yMax - 325);
		text ("    0.4           150   ", xMax + 100, yMax - 275);
		text ("    0.6           100   ", xMax + 100, yMax - 225);
		text ("    0.8            75   ", xMax + 100, yMax - 175);
		text ("    1.0          -100   ", xMax + 100, yMax - 125 );
		
		fill   (181, 219, 190);
		stroke (181, 219, 190);
		ellipse ((float) (xMax + 62.5), yMax - 325, 25, 25);
		
		fill   (120, 170, 156);
		stroke (120, 170, 156);
		ellipse ((float) (xMax + 62.5), yMax - 275, 25, 25);
		
		fill   (174, 198, 207);
		stroke (174, 198, 207);
		ellipse ((float) (xMax + 62.5), yMax - 225, 25, 25);
		
		fill   (119, 158, 203);
		stroke (119, 158, 203);
		ellipse ((float) (xMax + 62.5), yMax - 175, 25, 25);
		
		fill   (194, 59, 34);
		stroke (194, 59, 34);
		ellipse ((float) (xMax + 62.5), yMax - 125, 25, 25);
	}
	
}