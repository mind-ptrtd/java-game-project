package ui;

import javafx.scene.canvas.GraphicsContext;
import logic.GameObject;
import logic.IRenderable;
import logic.GameLogic;

public class Map implements IRenderable{

	@Override
	public int getZ() {
		return -99;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(GameObject.getInstance().map, 0, 0);
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}
	
}
