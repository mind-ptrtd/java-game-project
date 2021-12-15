package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class BuyTab extends GridPane {
	
	private ObservableList<BuyItem> buyItemList = FXCollections.observableArrayList();
	
	public BuyTab() {
		
		this.setAlignment(Pos.CENTER);
		this.setVgap(10);
		this.setHgap(10);
		
		BuyItem fishSpeed = new BuyItem("Fish Speed");
		BuyItem hookSpeed  = new BuyItem("Hook Speed");
		BuyItem hookSize = new BuyItem("Hook Size");
		BuyItem playerSpeed = new BuyItem("Player Speed");
	 	buyItemList.addAll(fishSpeed, hookSpeed, hookSize, playerSpeed);
	 	
//	 	for (BuyItem buyItem : buyItemList) {
//	 		buyItem.setOnMouseClicked(new EventHandler<MouseEvent>() {
//	 			//public void handle(ActionEvent event) 
//	 				
//	 			
//	 				
//	 		
//	 	}
	
	 	this.addRow(0, fishSpeed, hookSpeed, hookSize, playerSpeed);
	
	}
	

}
