package fish;

import javafx.scene.image.ImageView;
import logic.GameLogic;

public class BlueFish extends Fish{
	
	public BlueFish() {
		super();
		this.price = 100;
		this.name = "BlueFish";
		this.fishType = fishType.BLUEFISH;
		// ADD BELOW
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameLogic.getInstance().blueFish_Right);
		} else {
			return new ImageView(GameLogic.getInstance().blueFish_Left);
		}
	}

	@Override
	protected boolean isNeedToRotate() {
		return false;
	}
}
