package main;
import input.InputUtility;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.ObjectManager;
import input.StartButton;
import ui.GameScreen;
import ui.ItemBar;
import ui.Storage;

public class Main extends Application {
	
	public Storage storage;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		HBox root = new HBox();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Fish Game");
		stage.setResizable(false);
		
		ObjectManager objectManager = new ObjectManager();
		GameScreen gameScreen = new GameScreen(800, 600);
		
		StackPane popUp = new StackPane();
		Text t = new Text("Hello World");
		t.setFont(new Font(50));
		popUp.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		popUp.setMaxSize(300, 300);
		popUp.setAlignment(Pos.CENTER);
		popUp.getChildren().add(t);
		popUp.setVisible(true);
		
		ItemBar itemBar = new ItemBar();
		
		root.getChildren().addAll(itemBar, gameScreen);

		gameScreen.requestFocus();
		
		stage.show();
		
		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
				objectManager.update();
				GameLogic.getInstance().update();
				InputUtility.updateInputState();
			}
		};
		animation.start();
	}

}
