/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingoftre;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static bindingoftre.Constants.*;

/**
 * Generates a displayable Pane to showcase a newly collected item.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class ItemDialogScreen {

	private VBox outerBox;
	private ImageView background;
	private ImageView item;
	private Text title;
	private Text desc;

	/**
	 * Constructor for ItemDialogScreen.
	 * @param item the Item to display
	 */
	public ItemDialogScreen(Item item) {

		outerBox = new VBox();
		outerBox.setAlignment(Pos.CENTER);
		this.item = item.getImageView();
		outerBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLACK"), CornerRadii.EMPTY, Insets.EMPTY)));
		outerBox.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);

		title = new Text("You have collected " + item.getItemName() + "!");
		title.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
		title.setFill(Paint.valueOf("WHITE"));
		desc = new Text(item.getDescription());
		desc.setFont(Font.font(16));
		desc.setFill(Paint.valueOf("WHITE"));
		Text dismissLabel = new Text("automatically dismisses in 2 seconds");
		dismissLabel.setFont(Font.font(13));
		dismissLabel.setFill(Paint.valueOf("WHITE"));

		VBox contentBox = new VBox();
		contentBox.setPadding(new Insets(8,8,8,8));
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().add(this.item);
		contentBox.getChildren().add(title);
		contentBox.getChildren().add(desc);
		contentBox.getChildren().add(dismissLabel);
		outerBox.getChildren().add(contentBox);
		contentBox.relocate((ROOM_WIDTH / 2) - (contentBox.getBoundsInParent().getWidth() / 2), 500);
	}

	/**
	 * Returns the Pane used for display.
	 * @return Pane of this ItemDialogScreen
	 */
	public VBox getPane() {
		return outerBox;
	}

}
