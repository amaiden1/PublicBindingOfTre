package bindingofisaac;

import static bindingofisaac.Constants.ROOM_HEIGHT;
import static bindingofisaac.Constants.ROOM_WIDTH;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoseDialogScreen {

	private VBox outerBox;
	private ImageView background;
	private Text title;
	private Text desc;
        private Button mainMenuButton;

	public LoseDialogScreen(int floorLevel, Stage primaryStage) {

		outerBox = new VBox();
		outerBox.setAlignment(Pos.CENTER);
		outerBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLACK"), CornerRadii.EMPTY, Insets.EMPTY)));
		outerBox.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);

		title = new Text("GAME OVER!");
		title.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 60));
		title.setFill(Paint.valueOf("WHITE"));
		desc = new Text("You made it to Floor " + floorLevel);
		desc.setFont(Font.font(30));
		desc.setFill(Paint.valueOf("WHITE"));
                Button mainMenuButton = new Button("Main Menu");
                mainMenuButton.setPrefSize(300, 75);
                mainMenuButton.setOnAction(event -> {
                    try {
                        new StartGame(primaryStage);
                    }
                    catch(IOException e) {
                        // something went wrong
                    }
                });

		//pane.getChildren().add(this.background);
		VBox contentBox = new VBox();
		contentBox.setPadding(new Insets(8,8,8,8));
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().add(title);
		contentBox.getChildren().add(desc);
                contentBox.getChildren().add(mainMenuButton);
		outerBox.getChildren().add(contentBox);
		contentBox.relocate((ROOM_WIDTH / 2) - (contentBox.getBoundsInParent().getWidth() / 2), 500);
	}

	public VBox getPane() {
		return outerBox;
	}

}
