import java.awt.Graphics2D;

import javax.swing.JFrame;

public class GUI {
	JFrame frame;
	GameCanvas canvas;
	public GUI(int width, int height, KeyboardManager keyboardManager){
		frame = new JFrame();
		frame.setTitle("Speed Snake II: Revenge of the Mice");
		canvas = new GameCanvas(width,height);
		
		frame.addKeyListener(keyboardManager);
		frame.add(canvas);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public Graphics2D getGraphics(){
		return canvas.getImageGraphics();
	}
}
