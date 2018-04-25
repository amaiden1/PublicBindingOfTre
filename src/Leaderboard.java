
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author amaiden1
 */
public class Leaderboard {
    
    private String[] scoreTexts = new String[10];
    private int[] scores = new int[10];
    
    public Leaderboard(){
        Scanner fileScan = new Scanner("/fiels/High_Scores");
        Scanner lineScan;
        int count = 0;
        
        while(fileScan.hasNextLine()){
            lineScan = new Scanner(fileScan.nextLine());
            lineScan.useDelimiter(",");
            String name = lineScan.next();
            scores[count] = lineScan.nextInt();
            scoreTexts[count] = (name + ", Highest Floor: " + scores[count]);
        }
    }
    
    public void rankPlayer(int playerScore){
        int rank = -1;
        boolean flag = false;
        String playerName;
        for(rank = 0; rank < scores.length && !flag; rank++){
            if(playerScore > scores[rank]){
                playerName = promptNameInput();
                refactorScores(rank, playerScore, playerName);
                flag = true;
            }
        }
        if(rank > 10){
            //do not rank, this is for reading purposes
        }
        
    }
    
    public String promptNameInput(){
        //have player insert name
        return "";
    }
    
    public void refactorScores(int rank, int newScore, String newName){
        for(int i = scores.length; i > rank; i--){
            scores[i] = scores[i-1];
            scoreTexts[i] = scoreTexts[i-1];
        }
        scores[rank] = newScore;
        scoreTexts[rank] = newName;
    }
    
}
