package animation;

public interface ImageViewable {
	// Use ImageView Method To Show (instead of Draw(gc))
	
	public abstract void createFirstSprite();
	public abstract void updateSprite();
	public abstract void updateImageView();
	/*
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	*/
}
