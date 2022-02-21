package fish;

import javafx.scene.image.ImageView;
import logic.GameObject;

public class Bomb extends Fish{
	public Bomb() {
		super();
		this.name = "Bomb";
		this.price = 0; // CAN'T FISH
		this.fishType = FishType.BOMB;
		this.setSpeed(2*fishSpeedFactor);
	}
	
	protected ImageView imageViewFish() {
		return new ImageView(GameObject.bomb);
	}

	protected boolean isNeedToRotate() {
		return false;
	}

}
