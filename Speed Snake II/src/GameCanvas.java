import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameCanvas extends JPanel{
	BufferedImage image;
	public GameCanvas(int width, int height){
		image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		this.setPreferredSize(new Dimension(width, height));
	}
	public Graphics2D getImageGraphics() {
		return image.createGraphics();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
