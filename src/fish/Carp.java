package fish;

import javafx.scene.image.ImageView;
import logic.GameObject;

public class Carp extends Fish {
	public Carp() {
		super();
		fishType = FishType.CARP;
		this.price = 350;
		this.name = "Carp";
		this.setSpeed(3*speedFactor);
	
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameObject.getInstance().carp_Right);
		} else {
			return new ImageView(GameObject.getInstance().carp_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
