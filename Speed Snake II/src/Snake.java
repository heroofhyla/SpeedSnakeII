import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Snake {
	public JFrame jframe;
	public Toolkit toolkit;
	
	public static Snake snake;
	
	public Snake(){
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jframe = new JFrame("Snake"); // declaring the instance of jframe
		jframe.setVisible(true);
		jframe.setSize(500, 500);
		// jframe.setTitle("ENTER-NEW-GAMENAME-HERE");
		jframe.setLocation(dim.width / 2 - jframe.getWidth() / 2, dim.height / 2 - jframe.getHeight() / 2); // center of screen
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		snake = new Snake();
		// ignore this comment
	}
	
	
}