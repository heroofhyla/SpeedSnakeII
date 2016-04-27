import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HighScoreScene extends SnakeScene{

	ArrayList<Integer> scores;
	public HighScoreScene(int startingScore) {
		File scoresFile = new File("highscores.txt");
		if (scoresFile.exists()){
			System.out.println("File exists");
			//TODO
		}
		try (FileWriter writer = new FileWriter("highscores.txt")){
			writer.write("Test");
			System.out.println("File written");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO
	}

	@Override
	public void tick(Graphics2D g, int key) {
		
	}

}
