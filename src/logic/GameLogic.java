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
	
	public static Image map;
	public static Image blueFish_Right,blueFish_Left,tuna_Right,tuna_Left,trash_Right,trash_Left,
		bass_Right,bass_Left,carp_Right,carp_Left,lionFish_Right,lionFish_Left,midNight_Right,midNight_Left,
		squid_Right,squid_Left,rainbowTrout_Right,rainbowTrout_Left;
	public static Image playerPic;
	public static Image emptySprite;
	

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
		map = new Image(ClassLoader.getSystemResource("beach.png").toString());
		playerPic = new Image(ClassLoader.getSystemResource("willy.png").toString());
		emptySprite = new Image(ClassLoader.getSystemResource("EmptySprite.png").toString());
		// --- FISH -- //
		blueFish_Right = new Image(ClassLoader.getSystemResource("blueFish_Right.png").toString());
		blueFish_Left = new Image(ClassLoader.getSystemResource("blueFish_Left.png").toString());
		tuna_Right = new Image(ClassLoader.getSystemResource("tuna_Right.png").toString());
		tuna_Left = new Image(ClassLoader.getSystemResource("tuna_Left.png").toString());
		bass_Right = new Image(ClassLoader.getSystemResource("bass_Right.png").toString());
		bass_Left = new Image(ClassLoader.getSystemResource("bass_Left.png").toString());
		carp_Right = new Image(ClassLoader.getSystemResource("carp_Right.png").toString());
		carp_Left = new Image(ClassLoader.getSystemResource("carp_Left.png").toString());
		lionFish_Right = new Image(ClassLoader.getSystemResource("lionFish_Right.png").toString());
		lionFish_Left = new Image(ClassLoader.getSystemResource("lionFish_Left.png").toString());
		midNight_Right = new Image(ClassLoader.getSystemResource("midNight_Right.png").toString());
		midNight_Left = new Image(ClassLoader.getSystemResource("midNight_Left.png").toString());
		squid_Right = new Image(ClassLoader.getSystemResource("squid_Right.png").toString());
		squid_Left = new Image(ClassLoader.getSystemResource("squid_Left.png").toString());
		rainbowTrout_Right = new Image(ClassLoader.getSystemResource("rainbowTrout_Right.png").toString());
		rainbowTrout_Left = new Image(ClassLoader.getSystemResource("rainbowTrout_Left.png").toString());

		trash_Right = new Image(ClassLoader.getSystemResource("trash_Right.png").toString());
		trash_Left = new Image(ClassLoader.getSystemResource("trash_Left.png").toString());	
	}

	public void add(IRenderable entity) {
		// System.out.println("add");
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
