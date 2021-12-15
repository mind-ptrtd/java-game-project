package ui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.GameObject;
import main.Game;
import main.Main;

public class MainMenu extends VBox {
	private ImageView titleImage,newBtn,exitBtn;
	private HBox buttonPane;
	
	public MainMenu() {
		setPrefSize(800, 600);
		createBackground();
		setSpacing(50);
		setAlignment(Pos.CENTER);
		
		initGameName();
		initNewGame();
		initExitGame();
		buttonPane = new HBox();
		buttonPane.setAlignment(Pos.BASELINE_CENTER);
		buttonPane.getChildren().addAll(newBtn,exitBtn);
		buttonPane.setSpacing(50);
		getChildren().addAll(titleImage,buttonPane);
	}
	private void initGameName() {
		ImageView titleImage = new ImageView(GameObject.title);
		titleImage.setFitWidth(700);
		titleImage.setPreserveRatio(true);
		setTitleImage(titleImage);
	}
	private void initNewGame() {
		this.newBtn = new ImageView(GameObject.newBtn);
		newBtn.setViewport(new Rectangle2D(0, 5, 741, 1149/2));
		newBtn.setFitHeight(150);
		newBtn.setFitWidth(150);
		newBtn.setPreserveRatio(true);
		
		newBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				newBtn.setViewport(new Rectangle2D(0, 5+1149/2, 741, 1149/2));
			}
		});
		newBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				newBtn.setViewport(new Rectangle2D(0, 5+0, 741, 1149/2));
			}
		});
		
		newBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Main.setScreenNow(Game.INGAME);
			}
		});
	}

	private void initExitGame() {
		this.exitBtn = new ImageView(GameObject.exitBtn);
		exitBtn.setViewport(new Rectangle2D(0, 0, 741, 1149/2));
		exitBtn.setFitHeight(150);
		exitBtn.setFitWidth(150);
		exitBtn.setPreserveRatio(true);
		
		exitBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				exitBtn.setViewport(new Rectangle2D(0, 1149/2, 741, 1149/2));
			}
		});
		exitBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				exitBtn.setViewport(new Rectangle2D(0, 0, 741, 1149/2));
			}
		});
		
		exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Main.setClose(true);
			}
		});
	}

	private void createBackground() {
		Image backgroundImage = GameObject.map;
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(background));
	}

	// Setter
	public void setTitleImage(ImageView titleImage) {
		this.titleImage = titleImage;
	}
	public void setNewBtn(ImageView newBtn) {
		this.newBtn = newBtn;
	}
	public void setExitBtn(ImageView exitBtn) {
		this.exitBtn = exitBtn;
	}
}
