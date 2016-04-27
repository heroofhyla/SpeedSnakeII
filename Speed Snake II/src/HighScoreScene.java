import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScoreScene extends SnakeScene{
	private static final int SCORE_LIST_SIZE = 10;
	ArrayList<Integer> scores = new ArrayList<>(SCORE_LIST_SIZE);
	
	public HighScoreScene(int newScore) {
		for (int i = 0; i < SCORE_LIST_SIZE; ++i){
			scores.add(0);
		}
		File scoresFile = new File("highscores.txt");
		if (scoresFile.exists()){
			System.out.println("File exists");
			try (Scanner reader = new Scanner(scoresFile)){
				int writeHead = 0;
				while (reader.hasNextInt() && writeHead < scores.size()){
					scores.set(writeHead, reader.nextInt());
					++writeHead;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO
		}
		int newIndex = -1;
		for (int i = 0; i < scores.size(); ++i){
			if (newScore > scores.get(i)){
				newIndex = i;
				System.out.println(newScore + " > " + scores.get(i));
				scores.add(i,newScore);
				while (scores.size() > SCORE_LIST_SIZE){
					scores.remove(scores.size() - 1);
				}
				break;
			}
		}
		try (FileWriter writer = new FileWriter("highscores.txt")){
			for (Integer i: scores){
				writer.write(i + " ");
			}
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
