package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.GameLogic;
import logic.GameObject;
import shop.ShopSystem;
import ui.BuyTab;
import ui.GameScreen;
import ui.MainMenu;
import ui.ManagerTab;
import ui.BuyPopUp;
import fishing.FishingSystem;
import input.InputUtility;

public class Main extends Application {

	private static boolean isClose;
	private static Game screenNow;
	private static BuyPopUp buyPopUp;
	private static ManagerTab managerTab;
	private static BuyTab buyTab;
	private static Pane imagePane;
	private static Stage stage;
	private static Scene gameScene, startScene;
	private static GameScreen gameScreen;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {

		Main.stage = stage;
		// IN GAME ---------------------- //
		VBox gameRoot = new VBox();
		Main.gameScene = new Scene(gameRoot);
		GameLogic gameLogic = new GameLogic();
		Main.gameScreen = new GameScreen(800, 600);
		
		imagePane = new Pane();
		
		Group screen = new Group();
		Main.buyPopUp = new BuyPopUp();
		buyPopUp.setVisible(false);
		Main.buyTab = new BuyTab();
		buyTab.setVisible(false);
		Main.managerTab = new ManagerTab();
		
		screen.getChildren().addAll(gameScreen, imagePane, buyTab,buyPopUp);
		
		gameRoot.getChildren().addAll(managerTab,screen);

		// START ---------------------- //
		HBox startRoot = new HBox();
		Main.startScene = new Scene(startRoot);

		MainMenu mainmenu = new MainMenu();
		startRoot.getChildren().add(mainmenu);

		
		screenNow = Game.START;
		stage.setScene(startScene);
		stage.setTitle("FISHING VALLEY");
		stage.setResizable(false);
		stage.getIcons().add(GameObject.icon);

		gameScreen.requestFocus();
		stage.show();
		GameObject.getInstance();
		AudioClip bgSong = GameObject.bgSong;
		bgSong.setCycleCount(AudioClip.INDEFINITE);
		bgSong.play();

		AnimationTimer animation = new AnimationTimer() {
			public void handle(long now) {
				gameScreen.paintComponent();
				gameLogic.logicUpdate();
				changeScene();
				GameObject.getInstance().objectUpdate();
				InputUtility.updateInputState();
				FishingSystem.fishUpdate();
				ShopSystem.shopUpdate();
			}
		};
		animation.start();
	}

	private void changeScene() {
		if (screenNow == Game.START) {
			stage.setScene(startScene);
		} else if (screenNow == Game.INGAME) {
			stage.setScene(gameScene);
		}
		if (isClose) {
			stage.close();
		}
	}

	public static void addToPane(ImageView imageview) {
		imagePane.getChildren().add(imageview);
	}

	public static void removeFromPane(ImageView imageview) {
		imagePane.getChildren().remove(imageview);
	}

	// Getter-Setter
	public static void setScreenNow(Game screenNow) {
		Main.screenNow = screenNow;
	}

	public static void setClose(boolean isClose) {
		Main.isClose = isClose;
	}

	public static BuyPopUp getSellPopUp() {
		return buyPopUp;
	}

	public static GameScreen getGameScreen() {
		return gameScreen;
	}

	public static BuyTab getBuyTab() {
		return buyTab;
	}

	public static ManagerTab getManagerTab() {
		return managerTab;
	}

}