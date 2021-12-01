package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class GameLogic {
	private static final GameLogic instance = new GameLogic();
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image mapSprite;
	public static Image mineSprite;
	public static AudioClip  explosionSound;
	
	public static Image map;
	public static Image fishPic;
	

	static {
		loadResource();
	}

	public GameLogic() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static GameLogic getInstance() {
		return instance;
	}

	public static void loadResource() {
		map = new Image(ClassLoader.getSystemResource("map.png").toString());
		fishPic = new Image(ClassLoader.getSystemResource("blue_discus.png").toString());
		/*
		mapSprite = new Image(ClassLoader.getSystemResource("Map.png").toString());
		mineSprite = new Image(ClassLoader.getSystemResource("Mine.png").toString());
		explosionSound = new AudioClip(ClassLoader.getSystemResource("Explosion.wav").toString());
		*/
	}

	public void add(IRenderable entity) {
		System.out.println("add");
		entities.add(entity);
		Collections.sort(entities, comparator);
		/*
		for(IRenderable x: entities){
			if(x instanceof Tank) System.out.println("tank");
			if(x instanceof Mine) System.out.println("mine");
			if(x instanceof Field) System.out.println("field");
			
		}
		*/
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
