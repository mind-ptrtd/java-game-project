package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
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
import ui.Storage;
import fishing.FishingSystem;
import input.InputUtility;

public class Main extends Application {

	public Storage storage;
	private static Game screenNow;
	private static BuyPopUp sellPopUp;
	private static ManagerTab managerTab;
	private static BuyTab buyTab;
	private static boolean isClose;
	public static Pane imagePane = new Pane();
	private static Stage stage;
	private static Scene gameScene, startScene, testScene;
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

		Group screen = new Group();
		
		Main.sellPopUp = new BuyPopUp();
		sellPopUp.setVisible(false);

		Main.managerTab = new ManagerTab();

		
		
		Main.buyTab = new BuyTab();
		buyTab.setVisible(false);
		
		screen.getChildren().addAll(gameScreen, imagePane, buyTab, sellPopUp);
		
		
		gameRoot.getChildren().addAll(managerTab,screen);

		// START ---------------------- //
		HBox startRoot = new HBox();
		Main.startScene = new Scene(startRoot);

		MainMenu mainmenu = new MainMenu();
		startRoot.getChildren().add(mainmenu);

		// ---------------------------- //

		// --TEST ---------------//
		HBox testRoot = new HBox();
		Main.testScene = new Scene(testRoot);

		BuyTab buyTab = new BuyTab();
		testRoot.getChildren().addAll(buyTab);
		// ------------------------//

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
				Main.changeScene();
				GameObject.getInstance().objectUpdate();
				InputUtility.updateInputState();
				FishingSystem.fishUpdate();
				ShopSystem.shopUpdate();
			}
		};
		animation.start();
	}

	private static void changeScene() {
		if (screenNow == Game.START) {
			stage.setScene(startScene);
			// System.out.println("START");
		} else if (screenNow == Game.INGAME) {
			stage.setScene(gameScene);
			// System.out.println("GAME");
		}
		if (isClose) {
			stage.close();
			// System.out.println("CLOSE");
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
		return sellPopUp;
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