/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static bindingofisaac.Constants.*;

/**
 * Manages the saving, loading, and display of the top player scores.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class Leaderboard {
    
    private String[] scoreTexts = new String[10];
    private int[] scores = new int[10];
    private VBox scoresPane;
    private VBox scoresBox;
    private boolean wasRanked;

	/**
	 * Constructor for Leaderboard.
	 */
	public Leaderboard(){
        wasRanked = false;
        try{
            Scanner fileScan = new Scanner(new File("./src/files/High_Scores.txt"));
            Scanner lineScan;
            int count = 0;

            while(fileScan.hasNextLine()){
                lineScan = new Scanner(fileScan.nextLine());
                lineScan.useDelimiter(",");
                String name = lineScan.next();
                scores[count] = lineScan.nextInt();
                scoreTexts[count] = (name + "," + scores[count]);
                count++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("high scores file not found");
        }
        scoresPane = new VBox();
        scoresPane.setAlignment(Pos.TOP_CENTER);
        scoresPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLACK"), CornerRadii.EMPTY, Insets.EMPTY)));
        scoresPane.setPrefSize(500, ROOM_HEIGHT);
        scoresBox = new VBox();
        scoresBox.setAlignment(Pos.CENTER);
        scoresBox.setPadding(new Insets(10,10,10,10));
        scoresPane.getChildren().add(scoresBox);

    }

	/**
	 * Ranks the player on the leaderboard.
	 * @param playerScore the score of the player (in this case, floor level)
	 * @return rank of the player
	 */
	public int rankPlayer(int playerScore){
        int rank = -1;
        for(rank = 0; rank < scores.length && !wasRanked; rank++){
            if(playerScore > scores[rank]){
                wasRanked = true;
            }
        }
		if (Main.DEBUG) System.out.println("rank: " + rank);
        return rank - 1;
    }

	/**
	 * Adjusts the scores to accept a new score.
	 * @param rank the rank to place this score at
	 * @param newScore the new score to add
	 * @param newName the new name to add
	 */
	public void refactorScores(int rank, int newScore, String newName){
        for(int i = scores.length - 1; i > rank; i--){
            scores[i] = scores[i-1];
            scoreTexts[i] = scoreTexts[i-1];
        }
        scores[rank] = newScore;
        scoreTexts[rank] = (newName + "," + newScore);
    }

	/**
	 * Writes all scores to the scores file.
	 */
	public void overwriteScores(){
        PrintWriter pw;
        try{
            pw = new PrintWriter("./src/files/High_Scores.txt");
            BufferedWriter bw = new BufferedWriter(pw);
            for(String s: scoreTexts){
                bw.write(s);
                bw.newLine();
            }
            bw.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

	/**
	 * Returns a Pane that can display the leaderboard.
	 * @return leaderboard pane
	 */
	public Pane display(){
        
        scoresBox.getChildren().clear();
        Text leaderboard = new Text("Leaderboard");
        leaderboard.setFont(Font.font(null, FontWeight.BOLD, 42));
        leaderboard.setFill(Paint.valueOf("WHITE"));
        scoresBox.getChildren().add(leaderboard);
        for (int i = 0 ; i < scoreTexts.length ; i++) {
            Text score = new Text(scoreTexts[i]);
            score.setFill(Paint.valueOf("#00f21c"));
            score.setFont(Font.font(30));
            scoresBox.getChildren().add(score);
        }
        
        return scoresPane;
    }

}
