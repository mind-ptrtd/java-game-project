package fish;

import javafx.scene.image.ImageView;
import logic.GameObject;

public class Bomb extends Fish{
	public Bomb() {
		super();
		this.name = "Bomb";
		this.setPrice(10000); // CAN'T FISH
		this.fishType = fishType.BOMB;
		this.setSpeed(2*fishSpeedFactor);
	}
	
	protected ImageView imageViewFish() {
		return new ImageView(GameObject.getInstance().bomb);
	}

	protected boolean isNeedToRotate() {
		return false;
	}

}
