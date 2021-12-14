package ui;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.GameLogic;

public class Storage extends GridPane {
	
	private ObservableList<StorageCell> cell = FXCollections.observableArrayList();
	
	
	public Storage() {
			
		this.setPrefWidth(150);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setVgap(12);
		this.setHgap(20);
		this.setPadding(new Insets(12));
		this.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
		
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 10; y++) {
				StorageCell cells = new StorageCell();
				cell.add(cells);
				this.add(cells, x, y);
			}
		}
		
		for (StorageCell storageCell : cell) {
	 		storageCell.setOnAction(new EventHandler<ActionEvent>() {
	 			public void handle(ActionEvent event) {
	 				if (storageCell != null) { 
	 					// add show
	 					System.out.println("ok");
	 					
	 				}
	 				
	
	 			}
	 		});
	 	}
		
	}
	

	
	
	
	
}




