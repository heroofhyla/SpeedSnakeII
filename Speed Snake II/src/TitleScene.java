import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TitleScene extends SnakeScene{
	BufferedImage bgImage;
	public TitleScene(){
		try {
			bgImage = ImageIO.read(this.getClass().getResource("titlescreen.png"));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Couldn't find titlescreen.png");
			e1.printStackTrace();
		}

	}
	@Override
	public void tick(Graphics2D g, int key) {
		g.drawImage(bgImage, 0, 0, null);
		switch(key){
		case KeyEvent.VK_ENTER:
			setNextSceneType(SceneType.GAME);
			alertReadyForNextScene();
			break;
		case KeyEvent.VK_H:
			setNextSceneType(SceneType.HIGHSCORE);
			alertReadyForNextScene();
			break;
		default:
			break;//nothing
		}
	}

}
