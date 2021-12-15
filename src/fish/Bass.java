package fish;

import javafx.scene.image.ImageView;
import logic.Direction;
import logic.GameObject;

public class Bass extends Fish {
	public Bass() {
		super();
		fishType =FishType.BASS;
		this.price = 500;
		this.name = "Bass";
		this.setSpeed(7*fishSpeedFactor);
	}

	@Override
	public ImageView imageViewFish() {
		if(getFishDirection()==Direction.RIGHT) {
			return new ImageView(GameObject.bass_Right);
		} else {
			return new ImageView(GameObject.bass_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
