package fish;

import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Bass extends Fish {
	public Bass() {
		super();
		fishType =FishType.BASS;
		this.price = 500;
		this.name = "Bass";
		this.setSpeed(7);
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameLogic.getInstance().bass_Right);
		} else {
			return new ImageView(GameLogic.getInstance().bass_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return true;
	}
}
