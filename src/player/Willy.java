package player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.Entity;
import logic.GameLogic;

public class Willy extends Entity {
	
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		WritableImage crop = new WritableImage(GameLogic.getInstance().playerPic.getPixelReader(),2,128,16,65);
		//gc.drawImage(GameLogic.getInstance().fishPic, 400, 400);
		gc.drawImage(crop, 400, 190);

	}
}

