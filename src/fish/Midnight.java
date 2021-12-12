package fish;

import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Midnight extends Fish{
	public Midnight() {
		super();
		fishType = FishType.MIDNIGHT;
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameLogic.getInstance().midNight_Right);
		} else {
			return new ImageView(GameLogic.getInstance().midNight_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
