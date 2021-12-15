package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import logic.FishingSystem;
import logic.GameLogic;
import logic.GameObject;
import ui.BuyTab;
import ui.GameScreen;
import ui.MainMenu;
import ui.ManagerTab;
import ui.SellPopUp;
import ui.Storage;
import Shop.ShopSystem;
import input.InputUtility;

public class Main extends Application {

	public Storage storage;
	private static Game screenNow;
	private static SellPopUp sellPopUp;
	private static ManagerTab managerTab;
	private static BuyTab buyTab;
	private static boolean isClose;
	public static Pane imagePane = new Pane();
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
		HBox gameRoot = new HBox();
		Main.gameScene = new Scene(gameRoot);
		GameLogic gameLogic = new GameLogic();
		Main.gameScreen = new GameScreen(800, 600);

		// SellPopUp sellpopup = new SellPopUp();
		// sellpopup.setVisible(false);
		Group screen = new Group();
		
		Main.managerTab = new ManagerTab();
		
		Main.buyTab = new BuyTab();	
		buyTab.setVisible(false);
		screen.getChildren().addAll(gameScreen, imagePane, managerTab, buyTab);

		//ItemBar itemBar = new ItemBar();

		gameRoot.getChildren().addAll(screen);

		// START ---------------------- //
		HBox startRoot = new HBox();
		Main.startScene = new Scene(startRoot);

		MainMenu mainmenu = new MainMenu();
		startRoot.getChildren().add(mainmenu);

		
		// ---------------------------- //

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

	public static SellPopUp getSellPopUp() {
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