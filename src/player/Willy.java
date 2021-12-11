package player;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.Entity;
import logic.GameLogic;

public class Willy extends Entity {
	public Willy() {
		super();
		this.x = 400;
		this.y = 120;
	}
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		WritableImage crop = new WritableImage(GameLogic.getInstance().playerPic.getPixelReader(),0,8*32,1*32,4*32);
		//gc.drawImage(GameLogic.getInstance().fishPic, 400, 400);
		gc.drawImage(crop, getX(), getY());

	}
}

