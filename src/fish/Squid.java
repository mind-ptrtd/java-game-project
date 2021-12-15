package fish;

import javafx.scene.image.ImageView;
import logic.Direction;
import logic.GameObject;

public class Squid extends Fish {
	public Squid() {
		super();
		fishType = FishType.SQUID;
		this.price = 350;
		this.name = "Squid";
		this.setSpeed(2*fishSpeedFactor);
	}

	@Override
	public ImageView imageViewFish() {
		if(getFishDirection()==Direction.RIGHT) {
			return new ImageView(GameObject.squid_Right);
		} else {
			return new ImageView(GameObject.squid_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return false;
	}
}
