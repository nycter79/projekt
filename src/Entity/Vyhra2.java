package Entity;

import java.awt.*;

public class  Vyhra2 {
	
	private Font titleFont;
	
	public  Vyhra2(Hrac2 p) {

	}
	
	public void draw(Graphics2D g) {
		
		titleFont = new Font("Century Gothic",
				Font.BOLD | Font.ITALIC, 20);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString(
			"Vyhr·l hr·Ë ËÌslo 2.",
			210,
			50
		);
		
	}
	
}













