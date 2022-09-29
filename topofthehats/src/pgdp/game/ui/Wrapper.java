package pgdp.game.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.swing.JLabel;
import javax.swing.Timer;

import pgdp.game.CollisionBoard;
import pgdp.game.LocatedBoundingBox;
import pgdp.game.Position;
import pgdp.game.helper.Random;

public class Wrapper {

	private static final MethodHandle runTick, getEntities, getHitBox, getImage, getEntityAdd, newHamster, newWolfPingu,
			newHat, newGame, setControls, newPlayerControls, newWolfPinguAI, newHamsterAI, isHasHat;

	private static final EnumMap<pgdp.game.Image, Image> images;

	static {
		try {
			var lookup = MethodHandles.lookup();
			var game = Wrapper.class.getClassLoader().loadClass("pgdp.game.Game");
			{
				var m = game.getDeclaredMethod("runTick");
				if (!m.getReturnType().equals(void.class)) {
					throw new IllegalStateException("Wrong return type for runTick");
				}
				runTick = lookup.unreflect(m);
			}
			{
				var m = game.getDeclaredMethod("getEntities");
				if (!m.getReturnType().equals(List.class)) {
					throw new IllegalStateException("Wrong return type for getEntities");
				}
				getEntities = lookup.unreflect(m);
			}
			{
				var m = game.getDeclaredMethod("getEntityAdd");
				if (!m.getReturnType().equals(List.class)) {
					throw new IllegalStateException("Wrong return type for getEntityAdd");
				}
				getEntityAdd = lookup.unreflect(m);
			}
			newGame = lookup.unreflectConstructor(game.getConstructor(CollisionBoard.class));
			var entity = game.getClassLoader().loadClass("pgdp.game.Entity");
			{
				var m = entity.getDeclaredMethod("getHitBox");
				if (!m.getReturnType().equals(Optional.class)) {
					throw new IllegalStateException("Wrong return type for getHitBox");
				}
				getHitBox = lookup.unreflect(m);
			}
			{
				var m = entity.getDeclaredMethod("getImage");
				if (!m.getReturnType().equals(pgdp.game.Image.class)) {
					throw new IllegalStateException("wrong return type for getImage");
				}
				getImage = lookup.unreflect(m);
			}
			var figure = Wrapper.class.getClassLoader().loadClass("pgdp.game.Figure");
			{
				var m = figure.getDeclaredMethod("setControls",
						Wrapper.class.getClassLoader().loadClass("pgdp.game.Controls"));
				if (!m.getReturnType().equals(void.class)) {
					throw new IllegalStateException("wrong return type for getImage");
				}
				setControls = lookup.unreflect(m);
			}
			{
				var m = figure.getDeclaredMethod("isHasHat");
				if (!m.getReturnType().equals(boolean.class)) {
					throw new IllegalStateException("wrong return type for getImage");
				}
				isHasHat = lookup.unreflect(m);
			}
			newHat = lookup.unreflectConstructor(
					game.getClassLoader().loadClass("pgdp.game.Hat").getConstructor(Position.class));
			newHamster = lookup.unreflectConstructor(
					game.getClassLoader().loadClass("pgdp.game.Hamster").getConstructor(Position.class));
			newWolfPingu = lookup.unreflectConstructor(
					game.getClassLoader().loadClass("pgdp.game.WolfPingu").getConstructor(Position.class));
			newPlayerControls = lookup
					.unreflectConstructor(game.getClassLoader().loadClass("pgdp.game.PlayerControls").getConstructor());
			newHamsterAI = lookup
					.unreflectConstructor(game.getClassLoader().loadClass("pgdp.game.HamsterAI").getConstructor());
			newWolfPinguAI = lookup
					.unreflectConstructor(game.getClassLoader().loadClass("pgdp.game.WolfPinguAI").getConstructor());

		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		var toolkit = Toolkit.getDefaultToolkit();
		images = new EnumMap<>(pgdp.game.Image.class);
		for (var i : pgdp.game.Image.values()) {
			var stream = Wrapper.class.getResource(i.getResource());
			images.put(i, toolkit.createImage(stream));
		}

	}

	private final Object game;

	public Wrapper() throws Throwable {
		game = newGame.invoke(new CollisionBoard(80, 45, 5, 5));

	}

	public void run(Settings settings, GameBoard board, JLabel seconds, JLabel score, Runnable finished)
			throws Throwable {
		List<Object> add = (List<Object>) getEntityAdd.invoke(game);
		for (int i = 0; i < settings.numHamsters; i++) {
			var hamster = newHamster.invoke(new Position(Random.get(0, 80 - 3), Random.get(0, 45 - 3)));
			setControls.invoke(hamster, newHamsterAI.invoke());
			add.add(hamster);
		}
		for (int i = 0; i < settings.numPingus; i++) {
			var pingu = newWolfPingu.invoke(new Position(Random.get(0, 80 - 3), Random.get(0, 45 - 3)));
			setControls.invoke(pingu, newWolfPinguAI.invoke());
			add.add(pingu);
		}
		for (int i = 0; i < settings.numHats; i++) {
			add.add(newHat.invoke(new Position(Random.get(0, 80 - 2), Random.get(0, 45 - 1))));
		}
		var position = new Position(Random.get(0, 80 - 3), Random.get(0, 45 - 1));
		var player = settings.pingu ? newWolfPingu.invoke(position) : newHamster.invoke(position);
		setControls.invoke(player, newPlayerControls.invoke());
		add.add(player);
		var ref = new Object() {
			int time = settings.numSeconds * 60;
			int points = 0;
		};
		score.setText(Integer.toString(ref.points));
		seconds.setText(Integer.toString(ref.time / 60) + ':' + ref.time % 60);
		runTick.invoke(game);
		var timer = new Object() {
			Timer timer;
		};
		Function<Object, Optional<ToRender>> extractToRender = e -> {
			try {
				var o = (Optional<LocatedBoundingBox>) getHitBox.invoke(e);
				return o.map(b -> {
					try {
						return new ToRender(b, fromConstant((pgdp.game.Image) getImage.invoke(e)));
					} catch (Throwable ex) {
						throw new RuntimeException(ex);
					}
				});
			} catch (Throwable ex) {
				throw new RuntimeException(ex);
			}
		};
		board.render(((List<Object>) getEntities.invoke(game)).stream().map(extractToRender).filter(Optional::isPresent)
				.map(Optional::get).toList());
		timer.timer = new Timer(17, a -> {
			try {
				if ((Boolean) isHasHat.invoke(player))
					ref.points++;
				ref.time--;
				runTick.invoke(game);
				board.render(((List<Object>) getEntities.invoke(game)).stream().map(extractToRender)
						.filter(Optional::isPresent).map(Optional::get).toList());
				score.setText(Integer.toString(ref.points));
				seconds.setText(Integer.toString(ref.time / 60) + ':' + ref.time % 60);
				if (ref.time <= 0) {
					timer.timer.stop();
				}
			} catch (Throwable e) {
				throw new RuntimeException(e);
			}
		});
		timer.timer.start();
	}

	public static Image fromConstant(pgdp.game.Image image) {
		return images.get(image);
	}

	public static class ToRender {
		private final LocatedBoundingBox box;
		private final Image image;

		public ToRender(LocatedBoundingBox box, Image image) {
			this.box = box;
			this.image = image;
		}

		public LocatedBoundingBox getBox() {
			return box;
		}

		public Image getImage() {
			return image;
		}
	}
}
