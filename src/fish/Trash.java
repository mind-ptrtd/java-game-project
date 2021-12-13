package fish;

import javafx.scene.image.ImageView;
import logic.GameLogic;

public class Trash extends Fish{
	public Trash() {
		super();
		fishType = FishType.TRASH;
		this.price = 0;
		this.name = "Trash";
		this.setSpeed(2);
		// ADD BELOW
	}

	@Override
	public ImageView imageViewFish() {
		if(isRight) {
			return new ImageView(GameLogic.getInstance().trash_Right);
		} else {
			return new ImageView(GameLogic.getInstance().trash_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return false;
	}
}
