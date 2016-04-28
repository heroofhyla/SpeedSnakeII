import java.awt.Graphics2D;

public abstract class SnakeScene {
	public enum SceneType{
		TITLE,
		GAME,
		HIGHSCORE
	}
	private int score = 0;
	
	public void addScore(int score){
		this.score += score;
	}
	boolean readyForNextScene = false;
	SceneType nextSceneType = SceneType.TITLE;
	public abstract void tick(Graphics2D g, int key);
	
	public boolean readyForNextScene(){
		return readyForNextScene;
		
	}
	public void alertReadyForNextScene(){
		readyForNextScene = true;
	}
	public SceneType nextSceneType(){
		
		return nextSceneType;
	}
	
	public void setNextSceneType(SceneType nextSceneType){
		this.nextSceneType = nextSceneType;
	}
	
	public int getScore(){
		return score;
	}
}
