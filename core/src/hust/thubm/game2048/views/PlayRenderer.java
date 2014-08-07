package hust.thubm.game2048.views;

import hust.thubm.game2048.constant.Constant;
import hust.thubm.game2048.controllers.Controller;
import hust.thubm.game2048.controllers.PlayController;
import hust.thubm.game2048.entities.Cell;
import hust.thubm.game2048.entities.World;
import hust.thubm.game2048.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class PlayRenderer extends GameRenderer {
	private TextureAtlas atlas;
	private TextureRegion background;
	private TextureRegion gamelayout;
	private TextureRegion number[];
	
	private Dialog dlgGameOver;
	private Dialog dlgPause;
	
	private Button btnRestart;
	private Button btnUndo;
	
	private World world;
	private Skin skin;
	private Stage stage;
	
	public PlayRenderer(Controller controller, World world) {
		super(controller);
		this.world = world;
		this.stage = new Stage();
		load();
		createDialog();
		createButton();
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private void createDialog(){
		createGameOverDlg();
		createPauseDlg();
	}
	
	private void createButton(){
		createBtnRestart();
		createBtnUndo();
	}

	private void createBtnRestart() {
		btnRestart = new Button(skin, "btn-restart");
		btnRestart.setX(Constant.BUTTON_RESTART_X * GameScreen.ppuX);
		btnRestart.setY(Constant.BUTTON_RESTART_Y * GameScreen.ppuY);
		btnRestart.setWidth(Constant.BUTTON_RESTART_WIDTH * GameScreen.ppuX);
		btnRestart.setHeight(Constant.BUTTON_RESTART_HEIGHT * GameScreen.ppuY);
		
		btnRestart.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((PlayController)getController()).onBtnRestartClick();
			}
		});
		btnRestart.setVisible(true);
		stage.addActor(btnRestart);
	}
	
	private void createBtnUndo(){
		
	}

	private void load(){
		loadAtlas();
		loadBackground();
		loadTexture();
	}

	private void loadAtlas(){
		atlas = new TextureAtlas(Gdx.files.internal("img/playscreen.atlas"));
		skin = new Skin(Gdx.files.internal("img/playscreenskin.json"), atlas);
	}
	
	private void loadBackground(){
		background = atlas.findRegion("background");
		gamelayout = atlas.findRegion("gamelayout");
	}
	
	private void loadTexture(){
		number = new TextureRegion[15];
		
		for (int i = 0; i < 15; i++){
			number[i] = atlas.findRegion((int)Math.pow(2, i+1) + ""); 
		}
	}
	
	public void render(float delta){
		
		
		getSpriteBatch().begin();

		getSpriteBatch().draw(background, 0, 0, GameScreen.width, GameScreen.height);
		renderLayout(delta);
		renderWorld(delta);

		getSpriteBatch().end();
		
		renderStage();
	}
	
	private void renderStage(){
		boolean gameover = ((PlayController)getController()).isGameover();
		boolean pause = ((PlayController)getController()).isPause();
		
		if (gameover) {
			showGameoverDlg();
			unshowPauseDlg();
		} else if (pause){
			unshowGameOverDlg();
			showPauseDlg();
		}
		
		stage.draw();
	}
	
	private void renderLayout(float delta){
		float posx = 2f * GameScreen.ppuX;
		float posy = 5f * GameScreen.ppuY;
		float width = 36f * GameScreen.ppuX;
		float height = 36f * GameScreen.ppuY;
		
		getSpriteBatch().draw(gamelayout, posx, posy, width, height);
		
	}
	
	private void renderWorld(float delta){
		renderArray(getSpriteBatch());
	}
	
	private void renderArray(SpriteBatch sp){
		for (int i = 0; i < world.getN(); i++){
			for (Cell cell : world.getArray()[i]) {
				TextureRegion region = chooseRegion(cell);
				if (region != null)
					sp.draw(region, cell.getBound().x, cell.getBound().y, cell.getBound().width, cell.getBound().height);
			}
		}
	}
	
	private TextureRegion chooseRegion(Cell cell){
		return (cell.getValue() == 0) ? null : atlas.findRegion(cell.getValue() + "");
	}
	
	/**
	 * 
	 */
	private void createGameOverDlg(){
		Label labelScore;
		Button btnRestart = new Button(skin, "btn-restart");
		Button btnBack = new Button(skin, "btn-back");
		
		dlgGameOver = new Dialog("Game over", skin, "dlg-gameover");
		dlgGameOver.setSize(Constant.DIALOG_GAMEOVER_WIDTH * GameScreen.ppuX, Constant.DIALOG_GAMEOVER_HEIGHT * GameScreen.ppuY);
		dlgGameOver.setPosition(Constant.DIALOG_GAMEOVER_X * GameScreen.ppuX, Constant.DIALOG_GAMEOVER_Y * GameScreen.ppuY);
		dlgGameOver.row();
		
		// set btn restart
		//   *  set listener
		btnRestart.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((PlayController)getController()).onDlgGameOverBtnRestartClick();
			}
		});
		//   *  set position, width, height
		dlgGameOver.add(btnRestart)
				.width(Constant.DIALOG_GAMEOVER_BUTTON_RESTART_WIDTH * GameScreen.ppuX)
				.height(Constant.DIALOG_GAMEOVER_BUTTON_RESTART_HEIGHT * GameScreen.ppuY)
				.padLeft(Constant.DIALOG_GAMEOVER_BUTTON_RESTART_PAD_LEFT * GameScreen.ppuX)
				.padBottom(Constant.DIALOG_GAMEOVER_BUTTON_RESTART_PAD_BOTTOM * GameScreen.ppuY);

		// set btn back
		//   *  set listener
		btnBack.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((PlayController)getController()).onDlgGameOverBtnBackClick();
			}
		});
		//   *  set position, width, height
		dlgGameOver.add(btnBack)
				.width(Constant.DIALOG_GAMEOVER_BUTTON_BACK_WIDTH * GameScreen.ppuX)
				.height(Constant.DIALOG_GAMEOVER_BUTTON_BACK_HEIGHT * GameScreen.ppuY)
				.padRight(Constant.DIALOG_GAMEOVER_BUTTON_BACK_PAD_RIGHT * GameScreen.ppuX)
				.padBottom(Constant.DIALOG_GAMEOVER_BUTTON_BACK_PAD_BOTTOM * GameScreen.ppuY);
		dlgGameOver.setVisible(false);
		stage.addActor(dlgGameOver);
	}
	
	/**
	 * 
	 */
	private void createPauseDlg(){
		Button btnResume = new Button(skin, "btn-resume");
		Button btnQuit = new Button(skin, "btn-quit");
		
		dlgPause = new Dialog("Pause", skin, "dlg-pause");
		dlgPause.setSize(Constant.DIALOG_PAUSE_WIDTH * GameScreen.ppuX, Constant.DIALOG_PAUSE_HEIGHT * GameScreen.ppuY);
		dlgPause.setPosition(Constant.DIALOG_PAUSE_X * GameScreen.ppuX, Constant.DIALOG_PAUSE_Y * GameScreen.ppuY);
		dlgPause.row();
		
		// set btn resume 
		//  * set listener
		btnResume.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((PlayController)getController()).onDlgPauseBtnResumeClick();
			}
		});
		//  * set position, width, height
		dlgPause.add(btnResume)
				.width(Constant.DIALOG_PAUSE_BUTTON_RESUME_WIDTH * GameScreen.ppuX)
				.height(Constant.DIALOG_PAUSE_BUTTON_RESUME_HEIGHT * GameScreen.ppuY)
				.padLeft(Constant.DIALOG_PAUSE_BUTTON_RESUME_PAD_LEFT * GameScreen.ppuX)
				.padBottom(Constant.DIALOG_PAUSE_BUTTON_RESUME_PAD_BOTTOM * GameScreen.ppuY);
		
		
		
		// set btn quit
		//  * set listener
		btnQuit.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				((PlayController)getController()).onDlgPauseBtnQuitClick();
			}
		});
		//  * set position, width, height
		dlgPause.add(btnQuit)
				.width(Constant.DIALOG_PAUSE_BUTTON_QUIT_WIDTH * GameScreen.ppuX)
				.height(Constant.DIALOG_PAUSE_BUTTON_QUIT_HEIGHT * GameScreen.ppuY)
				.padRight(Constant.DIALOG_PAUSE_BUTTON_QUIT_PAD_RIGHT * GameScreen.ppuX)
				.padBottom(Constant.DIALOG_PAUSE_BUTTON_QUIT_PAD_BOTTOM * GameScreen.ppuY);
		dlgPause.setVisible(false);
		stage.addActor(dlgPause);
	}
	
	public void showGameoverDlg(){
		dlgGameOver.setVisible(true);
	}
	
	public void unshowGameOverDlg(){
		dlgGameOver.setVisible(false);
	}
	
	public void showPauseDlg(){
		dlgPause.setVisible(true);
	}
	
	public void unshowPauseDlg(){
		dlgPause.setVisible(false);
	}
}
