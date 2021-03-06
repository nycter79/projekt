package Main;

import javax.swing.JPanel;


import Stavy.PoradacStavu;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


@SuppressWarnings("serial")
public class GamePanel extends JPanel
implements Runnable, KeyListener,MouseListener{
	
	
	//Dimension
	public static final int WIDTH = 620;
	public static final int HEIGHT = 460;
	public static final int SCALE =2;
	
	//Game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	//Image
	private BufferedImage image;
	private Graphics2D g;
	
	//GameStateManager
	
	private PoradacStavu gsm;
	
	public GamePanel() {
		
		super();
		setPreferredSize(
				new Dimension(WIDTH * SCALE,HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread==null) {
			thread = new Thread(this);
			addKeyListener(this);
			addMouseListener(this);
			thread.start();
		}
	}
	
	public void init() {
		image = new BufferedImage(
				WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB
				);
		g = (Graphics2D) image.getGraphics();
		running = true;
		
		gsm = new PoradacStavu();
		
	}
	
	
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		//Game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;
			if(wait < 0){
				wait = 5;
			}
			
			try{
				Thread.sleep(wait);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void update() {
		gsm.update();
	}
	public void draw() {
		gsm.draw(g);
	}
	public void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH * SCALE,HEIGHT * SCALE, null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	
	public void keyReleased(KeyEvent key) {}
	
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}

	@Override
	public void mousePressed(MouseEvent evt){}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
