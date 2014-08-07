package hust.thubm.game2048.controllers;

import hust.thubm.game2048.screens.StartScreen;
import hust.thubm.game2048.views.StartRenderer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class StartController extends Controller{
	
	
	public StartController() {
		// TODO Auto-generated constructor stub
	}

	public void onStartBtnClick(){
		PlayController.setPlayScreen();
	}
	
	public void onSettingBtnClick(){
		
	}
	
	public void onHighScoreBtnClick(){
		
	}
	
	public void onAboutBtnClick(){
		
	}
	
	public static void setStartScreen(){
		StartController startController = new StartController();
		StartRenderer startRenderer = new StartRenderer(startController);
		
		((Game)Gdx.app.getApplicationListener()).setScreen(new StartScreen(startController, startRenderer));
	}
}
