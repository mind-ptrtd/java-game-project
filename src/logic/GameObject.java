package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class GameObject {
	private static final GameObject instance = new GameObject();
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	public static Image map, playerPic, emptySprite, fishHook,bomb;
	public static AudioClip bombSound;
	public static Image blueFish_Right, blueFish_Left, tuna_Right, tuna_Left, trash_Right, trash_Left, bass_Right,
			bass_Left, carp_Right, carp_Left, lionFish_Right, lionFish_Left, midNight_Right, midNight_Left, squid_Right,
			squid_Left, rainbowTrout_Right, rainbowTrout_Left;
	static {
		loadResource();
	}

	public GameObject() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static GameObject getInstance() {
		return instance;
	}

	public static void loadResource() {
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

		// Miscellaneous
		map = new Image(ClassLoader.getSystemResource("beach.png").toString());
		playerPic = new Image(ClassLoader.getSystemResource("willy.png").toString());
		emptySprite = new Image(ClassLoader.getSystemResource("EmptySprite.png").toString());
		fishHook = new Image(ClassLoader.getSystemResource("fishHook.png").toString(), 32, 32, false, false);
		bomb = new Image(ClassLoader.getSystemResource("Bomb.png").toString());
		bombSound = new AudioClip(ClassLoader.getSystemResource("BombSound.wav").toString());
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void remove(IRenderable entity) {
		entities.remove(entity);
	}

	public void logicUpdate() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
