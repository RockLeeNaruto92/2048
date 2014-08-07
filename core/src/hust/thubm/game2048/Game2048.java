package hust.thubm.game2048;

import hust.thubm.game2048.controllers.StartController;
import hust.thubm.game2048.screens.StartScreen;
import hust.thubm.game2048.views.StartRenderer;

import com.badlogic.gdx.Game;

public class Game2048 extends Game {
	
	@Override
	public void create () {
		StartController startController = new StartController();
		StartRenderer startRenderer = new StartRenderer(startController);
		
		setScreen(new StartScreen(startController, startRenderer));
	}
}
