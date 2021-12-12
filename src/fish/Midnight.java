package fish;

import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Midnight extends Fish{
	public Midnight() {
		super();
		fishType = FishType.MIDNIGHT;
		this.price = 400;
		this.name = "Midnight";
		this.setSpeed(4);
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
