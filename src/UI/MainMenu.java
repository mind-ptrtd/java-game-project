package ui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;

public class MainMenu {
	

	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	

	
	
	

	

	public Stage getMainStage() {
		return mainStage;
	}
		
	


	private void newGame() {
		Button newGameButton = new Button("New Game");
		newGameButton.setPrefWidth(190);
		newGameButton.setPrefHeight(49);
		newGameButton.setStyle("-fx-background-color: brown");
		mainPane.getChildren().add(newGameButton);
		
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.show();
				
				
			}
		});
	}
	
	private void exitGame() {
		Button exitButton = new Button("EXIT");
		
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
		});
		
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("beach.png", 800, 600, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
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
