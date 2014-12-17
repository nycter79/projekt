package Main;


import javax.swing.JFrame;

import Entity.sound;

public class Game {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Dungeon");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);

		//sound.BACK.loop();

	}
}
