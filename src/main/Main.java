package main;
import input.InputUtility;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.ObjectManager;
import ui.GameScreen;
import ui.ItemBar;
import ui.Storage;
import animation.Animateable;
import fish.Fish;

public class Main extends Application {
	
	public Storage storage;
	public static Pane imagePane = new Pane();
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
		
		imagePane.setMaxSize(800, 600);
		Group screen = new Group();
		screen.getChildren().addAll(gameScreen,imagePane);
		
		/*
		Text t = new Text("Hello World");
		t.setFont(new Font(50));
		popUp.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		popUp.setMaxSize(300, 300);
		popUp.setAlignment(Pos.CENTER);
		popUp.getChildren().add(t);
		popUp.setVisible(true);
		*/
		
		
		ItemBar itemBar = new ItemBar();
		
		root.getChildren().addAll(itemBar, screen);

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
	public static void addToPane(ImageView imageview) {
		imagePane.getChildren().add(imageview);
	}

	public static void removeFromPane(ImageView imageview) {
		imagePane.getChildren().remove(imageview);
	}
}

