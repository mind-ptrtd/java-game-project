package ui;

import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import logic.IRenderable;
import logic.ObjectManager;

public class Map implements IRenderable{

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return -99;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(GameLogic.getInstance().map, 0, 0);
		// System.out.println("Yes");
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
