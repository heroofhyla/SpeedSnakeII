import java.awt.Graphics2D;

public abstract class SnakeScene {
	public enum SceneType{
		TITLE,
		GAME,
		HIGHSCORE
	}
	
	public abstract void tick(Graphics2D g, int key);
	
	public boolean readyForNextScene(){
		//TODO;
		return false;
	}
	
	public SceneType nextSceneType(){
		//TODO;
		return SceneType.TITLE;
	}
}
