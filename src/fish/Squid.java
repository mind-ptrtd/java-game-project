package fish;

import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Squid extends Fish {
	public Squid() {
		super();
		fishType = FishType.SQUID;
		this.price = 350;
		this.name = "Squid";
		this.setSpeed(2);
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameLogic.getInstance().squid_Right);
		} else {
			return new ImageView(GameLogic.getInstance().squid_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
