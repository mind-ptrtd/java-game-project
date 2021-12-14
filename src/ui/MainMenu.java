package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameObject;
import main.Game;
import main.Main;

public class MainMenu extends VBox {
	private Button newGame,exitGame;
	private Text gameName;
	
	public MainMenu() {
		setPrefSize(800, 600);
		createBackground();
		setSpacing(50);
		setAlignment(Pos.CENTER);
		
		initGameName();
		initNewGame();
		initExitGame();
		
		getChildren().addAll(gameName,newGame,exitGame);
	}
	private void initGameName() {
		Text gameName = new Text("FISH GAME");
		gameName.setFont(new Font(50));
		setGameName(gameName);
	}
	private void initNewGame() {
		Button newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(190);
		newGameButton.setPrefHeight(49);
		//newGameButton.setStyle("-fx-background-color: brown");
		setNewGame(newGameButton);

		newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Main.setScreenNow(Game.INGAME);
			}
		});
	}

	private void initExitGame() {
		Button exitButton = new Button("EXIT");
		setExitGame(exitButton);
		exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Main.setClose(true);
			}
		});
	}

	private void createBackground() {
		Image backgroundImage = GameObject.getInstance().map;
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(background));
	}

	public void setNewGame(Button newGame) {
		this.newGame = newGame;
	}

	public void setExitGame(Button exitGame) {
		this.exitGame = exitGame;
	}
	public void setGameName(Text gameName) {
		this.gameName = gameName;
	}
	

//	private void createLogo() {
//		ImageView logo = new ImageView("/resources/space_runner.png");
//		logo.setLayoutX(380);
//		logo.setLayoutY(50);
//		
//		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				logo.setEffect(new DropShadow());
//				
//			}
//		});
//		
//		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				logo.setEffect(null);
//				
//			}
//		});
//		
//		mainPane.getChildren().add(logo);
//		
//	}

}
