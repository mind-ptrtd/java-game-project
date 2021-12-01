package fish;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import logic.GameLogic;

public class BlueFish extends Fish{
	public BlueFish() {
		super();
		this.speedX = 2;
		this.price = 100;
		this.name = "BlueFish";
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		WritableImage crop = new WritableImage(GameLogic.getInstance().fishPic.getPixelReader(),0,0,16,16);
		//gc.drawImage(GameLogic.getInstance().fishPic, 400, 400);
		gc.drawImage(crop, 400, 400);
	}
}
