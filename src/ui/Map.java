package ui;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.IRenderable;
import logic.ObjectManager;

public class Map implements IRenderable{

	@Override
	public int getZ() {
		return -99;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(GameLogic.getInstance().map, 0, 0);
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
