package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import objects.SuperObject;
import tiles.TilesManager;

public class GamePanel extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3587031526761099141L;
	// SCREEN SETTINGS 16X16 tile
	final int originalTileSize = 16;
	// scale times ,it looks like 48(16X3)
	final int scale = 3;
	// 48 x 48 tile
	public final int tileSize = originalTileSize * scale;

	// screen size
	public final int maxScreenCol = 16;

	public final int maxScreenRow = 12;

	// screen pixels
	public final int screenWidth = tileSize * maxScreenCol;

	public final int screenHeight = tileSize * maxScreenRow;

	// world settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// Set player's default position
//	int playerX = 100;
//	int playerY = 100;
//	int playerSpeed = 4;
	// FPS
	int FPS = 60;
	TilesManager tileM = new TilesManager(this);
	// thread for game start,game time....
	Thread gameThread;

	// KeyHandler
	KeyHandler keyH = new KeyHandler();
//	bgm,se player
	Sound music = new Sound();
	Sound se = new Sound();
//	UI manager
	public UI ui = new UI(this);
// create player
	public Player player = new Player(this, keyH);
//	Collision check
	public CollisionChecker cChecker = new CollisionChecker(this);

	public AssetSetter assetSetter = new AssetSetter(this);
//	Objects
	public SuperObject objs[] = new SuperObject[10];

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
//		Sets whether this component should use a buffer to paint. 
//		If set to true, all the drawing from this component will be done in an off screen painting buffer. 
//		The off screen painting buffer will the be copied onto the screen.
//		If a Component is buffered and one of its ancestor is also buffered, the ancestor buffer will be used.
		this.setDoubleBuffered(true);

		this.addKeyListener(keyH);

		this.setFocusable(true);

	}

	/**
	 * game settings
	 */
	public void setupGame() {
		assetSetter.setObject();
		playMusic(0);
	}

	/**
	 * start game thread
	 */
	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

//	@Override
//	public void run() {
//		// 0.01666 secods draw once
//		double drawInterval = 1000000000 / FPS;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		// TODO create game loop
//		while (gameThread != null) {
//
//			// 1. UPDATE : update information such as character positions
//			update();
//			// 2. DRAW : draw the screen with the updated information
//			repaint();
//			// calculate the time for next drawing.
//			double remainTime = nextDrawTime - System.nanoTime();
//			try {
//				// convert to milliseconds for sleeping.
//				remainTime = remainTime / 1000000;
//				if (remainTime < 0) {
//					remainTime = 0;
//				}
//				Thread.sleep((long) remainTime);
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * update information
	 */
	private void update() {
		player.update();
	}

	/**
	 * paint Component
	 * 
	 * @param g
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
//		DEBUG
		long drawStart = 0;
		if (keyH.checkDrawTime) {
			drawStart = System.nanoTime();
		}

//		tile
		tileM.draw(g2);
//		objects
		drawObject(g2);
//		player
		player.draw(g2);
		ui.draw(g2);
		if (keyH.checkDrawTime) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.WHITE);
			g2.drawString("Draw Time :" + passed, 10, 400);
			System.out.println(passed);
		}

		g2.dispose();
	}

	/**
	 * @param i
	 */
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}

	/**
	 * 
	 */
	public void stopMusic() {
		music.stop();
	}

	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}

	/**
	 * 
	 */
	private void drawObject(Graphics2D g2) {
		for (SuperObject obj : objs) {
			if (obj != null) {
				obj.draw(g2, this);
			}
		}

	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}

	}

}
