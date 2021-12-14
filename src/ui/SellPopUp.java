package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class SellPopUp extends VBox {
	
	private boolean isShown;
	
	
	public SellPopUp() {
		
		this.setPrefWidth(120);
		this.setPrefHeight(200);
	    this.setAlignment(Pos.CENTER);
	    this.setSpacing(3.5);
	    Text sellText = new Text("Sell this item?");
	    sellText.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, FontPosture.REGULAR, 20 ));
	    Button yesBut = new Button("YES");
	    yesBut.setFont(new Font(10));
	    yesBut.setPrefWidth(40);
	    yesBut.setPrefHeight(30);
	    Button noBut = new Button("NO");
	    noBut.setFont(new Font(10));
	    noBut.setPrefWidth(40);
	    noBut.setPrefHeight(30);

	    this.getChildren().addAll(sellText, yesBut, noBut);
	    this.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
	    
	    
	}


	public void setShown(boolean isShown) {
		
		if (isShown) {
			this.setVisible(true);
		}
	}
	
	

}
