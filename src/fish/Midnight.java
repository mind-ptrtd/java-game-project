package fish;

import javafx.scene.image.ImageView;
import logic.Direction;
import logic.GameObject;

public class Midnight extends Fish{
	public Midnight() {
		super();
		fishType = FishType.MIDNIGHT;
		this.price = 400;
		this.name = "Midnight";
		this.setSpeed(4*fishSpeedFactor);
	}

	@Override
	public ImageView imageViewFish() {
		if(getFishDirection()==Direction.RIGHT) {
			return new ImageView(GameObject.midNight_Right);
		} else {
			return new ImageView(GameObject.midNight_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
