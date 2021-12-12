package animation;

import javafx.scene.image.ImageView;
import logic.Entity;
import main.Main;
public interface Animateable {
	// for Classify
	public abstract ImageView getImageView();
	public abstract void upDateImageView();
	/*
	    Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	 */
}
