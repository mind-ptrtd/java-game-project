package ui;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class SellPopUp extends VBox {
	private Text sellText;
	private Button yesBtn,noBtn;
	private static boolean isBuy;
	

	public SellPopUp() {
		this.setPrefWidth(120);
		this.setPrefHeight(200);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(3.5);
		
		initSellText();
		initYesBtn();
		initNoBtn();
		
//	    VBox fishInfo = new VBox(20);
//	    fishInfo.setAlignment(Pos.CENTER);		
//	    Image fishImage = GameLogic.getInstance().bass_Right;
//	    BackgroundImage bgImg = new BackgroundImage(fishImage, 
//	    	    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//	    	    BackgroundPosition.DEFAULT, 
//	    	    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
//	    fishInfo.getChildren().add(bgImg);
		
		this.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
		this.getChildren().addAll(sellText, yesBtn, noBtn);
	}
	private void initSellText() {
		Text sellText = new Text("Buy this item?");
		sellText.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, FontPosture.REGULAR, 20));
		setSellText(sellText);
	}
	private void initYesBtn() {
		Button yesBtn = new Button("YES");
		yesBtn.setFont(new Font(10));
		yesBtn.setPrefWidth(40);
		yesBtn.setPrefHeight(30);
		setYesBtn(yesBtn);
		yesBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				isBuy = true;
				Main.getSellPopUp().setVisible(false);
				Main.getGameScreen().requestFocus();
			
			}
		});
	}
	private void initNoBtn() {
		Button noBtn = new Button("NO");
		noBtn.setFont(new Font(10));
		noBtn.setPrefWidth(40);
		noBtn.setPrefHeight(30);
		setNoBtn(noBtn);
		noBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				isBuy = false;
				Main.getSellPopUp().setVisible(false);
				Main.getGameScreen().requestFocus();
			}
		});
	}
	
	public void setSellText(Text sellText) {
		this.sellText = sellText;
	}
	
	public void setYesBtn(Button yesBtn) {
		this.yesBtn = yesBtn;
	}
	
	public void setNoBtn(Button noBtn) {
		this.noBtn = noBtn;
	}
	
	public static boolean getIsBuy() {
		return SellPopUp.isBuy;
	}
	
}
