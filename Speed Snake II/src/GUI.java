import java.awt.Dimension;

import javax.swing.JFrame;

public class GUI {
	JFrame frame;
	public GUI(int width, int height, KeyboardManager keyboardManager){
		frame = new JFrame();
		GameCanvas canvas = new GameCanvas();
		canvas.setPreferredSize(new Dimension(width, height));
		
		frame.addKeyListener(keyboardManager);
		frame.add(canvas);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
