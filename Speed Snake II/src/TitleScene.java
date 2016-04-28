import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TitleScene extends SnakeScene{

	@Override
	public void tick(Graphics2D g, int key) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, g.getDeviceConfiguration().getBounds().width, g.getDeviceConfiguration().getBounds().height);
		
		g.setColor(Color.WHITE);
		g.drawString("Press [ENTER] to begin or [H] for high scores.", 40, 40);
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
