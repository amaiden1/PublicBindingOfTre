/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import static bindingofisaac.Constants.ROOM_HEIGHT;
import static bindingofisaac.Constants.ROOM_WIDTH;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Generates a displayable Pane that tells the user when the game
 * is over and accepts score input for the leaderboard.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class LoseDialogScreen {

	private VBox outerBox;
	private Text title;
	private Text desc;
	private Text letter1;
	private Text letter2;
	private Text letter3;
	private int activeLetterPosition;
	private int rank;
	private Text activeLetter;
	private Leaderboard leaderboard;
	private int floorLevel;

	/**
	 * Constructor for LoseDialogScreen.
	 * @param rank the rank of the player
	 * @param floorLevel the floor level at time of game over
	 * @param primaryStage the Stage to display the screen on
	 */
	public LoseDialogScreen(int rank, int floorLevel, Stage primaryStage) {

		this.floorLevel = floorLevel;
		this.rank = rank;
		outerBox = new VBox();
		outerBox.setAlignment(Pos.CENTER);
		outerBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLACK"), CornerRadii.EMPTY, Insets.EMPTY)));
		outerBox.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);

		title = new Text("GAME OVER!");
		title.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 60));
		title.setFill(Paint.valueOf("RED"));
		desc = new Text("You made it to Floor " + floorLevel);
		desc.setFont(Font.font(30));
		desc.setFill(Paint.valueOf("WHITE"));
		Button mainMenuButton = new Button("Main Menu");
		mainMenuButton.setPrefSize(200, 75);
		mainMenuButton.setBackground(new Background(new BackgroundFill(Paint.valueOf("#5b5b5b"), CornerRadii.EMPTY, Insets.EMPTY)));
		mainMenuButton.setTextFill(Paint.valueOf("WHITE"));
		mainMenuButton.setOnAction(event -> {
			try {
				new GameStarter(primaryStage);
			} catch (IOException e) {
				System.out.println("Unable to start a new game: " + e.getMessage());
			}
		});

		//pane.getChildren().add(this.background);
		VBox contentBox = new VBox();
		contentBox.setSpacing(20);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().add(title);
		contentBox.getChildren().add(desc);

		leaderboard = Main.player.getGame().getLeaderboard();
		if (rank < 10) {
			HBox letterSelection = new HBox();
			letterSelection.setAlignment(Pos.CENTER);
			letterSelection.setPadding(new Insets(4,4,4,4));
			letter1 = new Text("A");
			letter1.setFont(Font.font(null, FontWeight.BOLD, 40));
			letter1.setFill(Paint.valueOf("GREEN"));
			letter2 = new Text("A");
			letter2.setFont(Font.font(null, FontWeight.BOLD, 40));
			letter2.setFill(Paint.valueOf("#00f21c"));
			letter3 = new Text("A");
			letter3.setFont(Font.font(null, FontWeight.BOLD, 40));
			letter3.setFill(Paint.valueOf("#00f21c"));
			letterSelection.getChildren().addAll(letter1, letter2, letter3);
			activeLetter = letter1;
			activeLetterPosition = 1;

			Text topInstructions = new Text("Enter your name in the leaderboard:");
			topInstructions.setFont(Font.font(20));
			topInstructions.setFill(Paint.valueOf("#00f21c"));
			contentBox.getChildren().add(topInstructions);
			contentBox.getChildren().add(letterSelection);
			Text instructions = new Text("left/right to move, up/down to change letters");
			instructions.setFill(Paint.valueOf("#00f21c"));
			contentBox.getChildren().add(instructions);

			primaryStage.getScene().setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.UP) {
					activeLetter.setText("" + (char)(activeLetter.getText().toCharArray()[0] + 1));
				}
				if (event.getCode() == KeyCode.DOWN) {
					activeLetter.setText("" + (char)(activeLetter.getText().toCharArray()[0] - 1));
				}
				if (event.getCode() == KeyCode.RIGHT) {
					if (activeLetterPosition == 1) {
						activeLetterPosition = 2;
						activeLetter.setFill(Paint.valueOf("#00f21c"));
						activeLetter = letter2;
						activeLetter.setFill(Paint.valueOf("GREEN"));
					} else if (activeLetterPosition == 2) {
						activeLetterPosition = 3;
						activeLetter.setFill(Paint.valueOf("#00f21c"));
						activeLetter = letter3;
						activeLetter.setFill(Paint.valueOf("GREEN"));
					} else {
						activeLetterPosition = 1;
						activeLetter.setFill(Paint.valueOf("#00f21c"));
						activeLetter = letter1;
						activeLetter.setFill(Paint.valueOf("GREEN"));
					}
				}
				if (event.getCode() == KeyCode.LEFT) {
					if (activeLetterPosition == 3) {
						activeLetterPosition = 2;
						activeLetter.setFill(Paint.valueOf("#00f21c"));
						activeLetter = letter2;
						activeLetter.setFill(Paint.valueOf("GREEN"));
					} else if (activeLetterPosition == 2) {
						activeLetterPosition = 1;
						activeLetter.setFill(Paint.valueOf("#00f21c"));
						activeLetter = letter1;
						activeLetter.setFill(Paint.valueOf("GREEN"));
					} else {
						activeLetterPosition = 3;
						activeLetter.setFill(Paint.valueOf("#00f21c"));
						activeLetter = letter3;
						activeLetter.setFill(Paint.valueOf("GREEN"));
					}
				}
				if(event.getCode() == KeyCode.ENTER) {
					try {
						commitScore(letter1.getText() + letter2.getText() + letter3.getText());
						new GameStarter(primaryStage);
					} catch (IOException e) {
						System.out.println("Unable to start a new game: " + e.getMessage());
					}
				}
			});
			mainMenuButton.setText("Save Score (Enter)");
			mainMenuButton.setOnAction(event -> {
				try {
					commitScore(letter1.getText() + letter2.getText() + letter3.getText());
					new GameStarter(primaryStage);
				} catch (IOException e) {
					System.out.println("Unable to start a new game: " + e.getMessage());
				}
			});
		}

		contentBox.getChildren().add(mainMenuButton);
		outerBox.getChildren().add(contentBox);
		contentBox.relocate((ROOM_WIDTH / 2) - (contentBox.getBoundsInParent().getWidth() / 2), 500);
	}

	private void commitScore(String name) {
		if (Main.DEBUG) System.out.println("refactor scores runs");
		leaderboard.refactorScores(rank, floorLevel, name);
		leaderboard.overwriteScores();
	}

	/**
	 * Returns a Pane for display.
	 * @return Pane to display
	 */
	public VBox getPane() {
		return outerBox;
	}

}
