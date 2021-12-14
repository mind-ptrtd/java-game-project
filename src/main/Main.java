package main;

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
import logic.GameObject;
import ui.GameScreen;
import ui.ItemBar;
import ui.MainMenu;
import ui.SellPopUp;
import ui.Storage;
import animation.Animateable;
import fish.Fish;
import input.InputUtility;

public class Main extends Application {
	
	public Storage storage;
	private static Game screenNow;
	private static SellPopUp sellPopUp;
	private static boolean isClose;
	public static Pane imagePane = new Pane();
	private static Stage stage;
	private static Scene gameScene,startScene;
	private static GameScreen gameScreen;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		this.stage = stage;
		// IN GAME ---------------------- //
		HBox gameRoot = new HBox();
		this.gameScene = new Scene(gameRoot);
		GameLogic gameLogic = new GameLogic();
		this.gameScreen = new GameScreen(800, 600);

		//SellPopUp sellpopup = new SellPopUp();
		//sellpopup.setVisible(false);
		Group screen = new Group();
		
		this.sellPopUp = new SellPopUp();
		sellPopUp.setVisible(false);
		
		screen.getChildren().addAll(gameScreen, imagePane,sellPopUp);

		ItemBar itemBar = new ItemBar();

		gameRoot.getChildren().addAll(itemBar, screen);

		// START ---------------------- //
		HBox startRoot = new HBox();
		this.startScene = new Scene(startRoot);

		MainMenu mainmenu = new MainMenu();
		startRoot.getChildren().add(mainmenu);

		// ---------------------------- //
		
		screenNow = Game.START;
		stage.setScene(startScene);
		stage.setTitle("FISH GAME");
		stage.setResizable(false);

		gameScreen.requestFocus();
		stage.show();


		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
				gameLogic.logicUpdate();
				Main.this.changeScene();
				GameObject.getInstance().logicUpdate();
				InputUtility.updateInputState();
			}
		};
		animation.start();
	}
	public static void changeScene() {
		if (screenNow == Game.START) {
			stage.setScene(startScene);
			//System.out.println("START");
		} else if(screenNow == Game.INGAME){
			stage.setScene(gameScene);
			//System.out.println("GAME");
		}
		if(isClose) {
			stage.close();
			//System.out.println("CLOSE");
		}
		
	}
	public static void setScreenNow(Game screenNow) {
		Main.screenNow = screenNow;
	}

	public static void addToPane(ImageView imageview) {
		imagePane.getChildren().add(imageview);
	}

	public static void removeFromPane(ImageView imageview) {
		imagePane.getChildren().remove(imageview);
	}

	public static void setClose(boolean isClose) {
		Main.isClose = isClose;
	}

	public static SellPopUp getSellPopUp() {
		return sellPopUp;
	}

	public static GameScreen getGameScreen() {
		return gameScreen;
	}
}