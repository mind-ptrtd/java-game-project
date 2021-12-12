package fish;

import javafx.scene.image.ImageView;
import logic.GameLogic;

public class LionFish extends Fish{
	public LionFish() {
		super();
		fishType = FishType.LIONFISH;
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameLogic.getInstance().lionFish_Right);
		} else {
			return new ImageView(GameLogic.getInstance().lionFish_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
