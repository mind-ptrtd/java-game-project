package animation;

public interface ImageViewable {
	// Use ImageView Method To Show (instead of Draw(gc))
	
	public abstract void createFirstSprite();
	public abstract void upDateSprite();
	public abstract void upDateImageView();
	/*
		Main.removeFromPane(imageView);
		imageView.relocate(getX(), getY());
		Main.addToPane(imageView);
	*/
}
