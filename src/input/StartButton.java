package input;

import fish.Fish;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartButton extends Button {
	
	// private Button startButton;
	
	public StartButton() {

		this.setText("START");
		this.setFont(new Font(20));
		this.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null)));
		this.setMaxSize(100, 40);
		this.setAlignment(Pos.CENTER);
		
	}
	
	

}
