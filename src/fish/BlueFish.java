package fish;

import javafx.scene.image.ImageView;
import logic.GameObject;

public class BlueFish extends Fish{
	
	public BlueFish() {
		super();
		this.price = 100;
		this.name = "Blue Fish";
		this.fishType = FishType.BLUEFISH;
		this.setSpeed(1*fishSpeedFactor);
		// ADD BELOW
		
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameObject.blueFish_Right);
		} else {
			return new ImageView(GameObject.blueFish_Left);
		}
	}

	@Override
	protected boolean isNeedToRotate() {
		return false;
	}
}
