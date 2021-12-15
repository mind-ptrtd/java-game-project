package fish;

import javafx.scene.image.ImageView;
import logic.Direction;
import logic.GameObject;

public class LionFish extends Fish{
	public LionFish() {
		super();
		fishType = FishType.LIONFISH;
		this.price = 150;
		this.name = "Lion Fish";
		this.setSpeed(2*fishSpeedFactor);
	}

	@Override
	public ImageView imageViewFish() {
		if(getFishDirection()==Direction.RIGHT) {
			return new ImageView(GameObject.lionFish_Right);
		} else {
			return new ImageView(GameObject.lionFish_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
