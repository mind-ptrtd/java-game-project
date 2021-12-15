package ui;

import javafx.scene.canvas.GraphicsContext;
import logic.IRenderable;
import logic.GameLogic;
import logic.GameObject;

public class Map implements IRenderable{

	@Override
	public int getZ() {
		return -99;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(GameObject.map, 0, 0);
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
