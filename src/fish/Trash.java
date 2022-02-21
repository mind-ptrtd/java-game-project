package fish;

import javafx.scene.image.ImageView;
import logic.Direction;
import logic.GameObject;

public class Trash extends Fish{
	public Trash() {
		super();
		fishType = FishType.TRASH;
		this.price = 0;
		this.name = "Trash";
		this.setSpeed(2*fishSpeedFactor);
		// ADD BELOW
	}

	@Override
	public ImageView imageViewFish() {
		if(getFishDirection()==Direction.RIGHT) {
			return new ImageView(GameObject.trash_Right);
		} else {
			return new ImageView(GameObject.trash_Left);
		}
	}
	@Override
	protected boolean isNeedToRotate() {
		return false;
	}
}
