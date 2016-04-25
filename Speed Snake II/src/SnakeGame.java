

public class SnakeGame {
	private static final int TILE_SIZE = 20;
	private static final int X_TILES = 25;
	private static final int Y_TILES = 25;
	private KeyboardManager keyboardManager;
	private GUI gui;
	public SnakeGame(){
		keyboardManager = new KeyboardManager();
		gui = new GUI(TILE_SIZE * X_TILES, TILE_SIZE * Y_TILES, keyboardManager);
	}
	public static void main(String[] args){
		SnakeGame game = new SnakeGame();
	}
}
