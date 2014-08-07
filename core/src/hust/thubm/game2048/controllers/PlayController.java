package hust.thubm.game2048.controllers;

import hust.thubm.game2048.entities.Cell;
import hust.thubm.game2048.entities.World;
import hust.thubm.game2048.screens.PlayScreen;
import hust.thubm.game2048.views.PlayRenderer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class PlayController extends Controller {
	public enum Touch {
		RIGHT_TO_LEFT, LEFT_TO_RIGHT, UP_TO_DOWN, DOWN_TO_UP, NOTHING
	}

	/**************************************/
	private Vector2 oldTouchPos = new Vector2();
	private boolean isAddNewCell = true;
	private World world;
	private Touch state;
	
	private boolean gameover = false;
	private boolean pause = false;

	/**************************************/
	public PlayController(World world) {
		this.world = world;
		this.state = Touch.NOTHING;
	}

	public boolean isGameover() {
		return gameover;
	}

	public boolean isPause() {
		return pause;
	}

	public void onTouchDragger(int x, int y) {
	}
	
	public void onKeyDown(int keycode) {
		// TODO Auto-generated method stub
		if (keycode == Keys.ESCAPE)
			pause = true;
	}

	public void onTouchUp(int x, int y) {
		float xdiff = Math.abs(oldTouchPos.x - x);
		float ydiff = Math.abs(oldTouchPos.y - y);

		if (xdiff > ydiff)
			state = (oldTouchPos.x < x) ? Touch.LEFT_TO_RIGHT : Touch.RIGHT_TO_LEFT;
		else
			state = (oldTouchPos.y < y) ? Touch.UP_TO_DOWN : Touch.DOWN_TO_UP;
	}

	public void onTouchDown(int x, int y) {
		this.oldTouchPos.x = x;
		this.oldTouchPos.y = y;
	}

	/**
	 * 
	 * @param delta
	 */
	public void updateWorld(float delta) {
		if (state == Touch.NOTHING) {
			world.update(delta);
			return;
		}
		
		gameover = checkGameOver();
		if (gameover) 
			return;
		
		
		isAddNewCell = false;

		if (state == Touch.DOWN_TO_UP)
			onDownToUpTouchDragger();
		else if (state == Touch.UP_TO_DOWN)
			onUpToDownTouchDragger();
		else if (state == Touch.LEFT_TO_RIGHT)
			onLeftToRightTouchDragger();
		else
			onRightToLeftTouchDragger();

		state = Touch.NOTHING;
		
		if (isAddNewCell)
			addNewCell();
		
	}
	
	private void addNewCell(){
		world.getNewCell();
	}

	/**
	 * 
	 */
	private void onRightToLeftTouchDragger() {
		// TODO Auto-generated method stub
		for (int i = 0; i < world.getN(); i++) {
			int j = 0, col = 0;
			Cell cell, temp = null;
			Check isChange = new Check();

			while (j < world.getN()) {
				cell = world.getArray()[i][j];
				j++;
				temp = updateCell(cell, temp, i, col, isChange);

				if (temp != null && isChange.check)
					col++;
			}
		}
	}

	/**
	 * 
	 */
	private void onLeftToRightTouchDragger() {
		// TODO Auto-generated method stub
		for (int i = 0; i < world.getN(); i++) {
			int j = world.getN() - 1;
			int col = world.getN() - 1;
			Cell cell, temp = null;
			Check isChange = new Check();

			while (j >= 0) {
				cell = world.getArray()[i][j];
				j--;
				temp = updateCell(cell, temp, i, col, isChange);

				if (temp != null && isChange.check)
					col--;
			}
		}
	}

	/**
	 * 
	 */
	private void onDownToUpTouchDragger() {
		// TODO Auto-generated method stub
		for (int j = 0; j < world.getN(); j++) {
			int i = 0, row = 0;
			Cell cell, temp = null;
			Check isChange = new Check();

			while (i < world.getN()) {
				cell = world.getArray()[i][j];
				i++;
				temp = updateCell(cell, temp, row, j, isChange);

				if (temp != null && isChange.check)
					row++;
			}
		}
	}

	/**
	 * 
	 */
	private void onUpToDownTouchDragger() {
		// TODO Auto-generated method stub
		
		for (int j = 0; j < world.getN(); j++) {
			int i = world.getN() - 1;
			int row = world.getN() - 1;
			Cell cell, temp = null;
			Check isChange = new Check();

			while (i >= 0) {
				cell = world.getArray()[i][j];
				i--;
				temp = updateCell(cell, temp, row, j, isChange);
				
				if (temp != null && isChange.check)
					row--;
			}
		}
	}

	private Cell updateCell(Cell cell, Cell temp, int desX, int desY,
			Check isChange) {
		if (cell.getValue() == 0) {
			isChange.setCheck(false);
			return temp;
		}

		if (temp == null) {
			temp = world.getArray()[desX][desY];
			temp.setValue(cell.getValue());
			if (temp != cell){
				cell.setValue(0);
				isAddNewCell = true;
			}

		} else if (temp.getValue() == cell.getValue()) {
			temp.setValue(temp.getValue() + cell.getValue());
			temp = null;
			cell.setValue(0);
			isAddNewCell = true;
		} else {
			temp = world.getArray()[desX][desY];
			temp.setValue(cell.getValue());
			if (temp != cell){
				cell.setValue(0);
				isAddNewCell = true;
			}
		}
		isChange.setCheck(true);
		return temp;
	}

	/**
	 * 
	 */
	public void update(float delta) {
		updateWorld(delta);
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean checkGameOver(){
		if (world.getNumberOfCell() != world.getN() * world.getN())
			return false;
		
		for (int i = 0; i < world.getN(); i++)
			for (int j = 0; j < world.getN() - 1; j++)
				if (world.getArray()[i][j].getValue() == world.getArray()[i][j+1].getValue())
					return false;
		
		for (int j = 0; j < world.getN(); j++)
			for (int i = 0; i < world.getN() - 1; i++)
				if (world.getArray()[i][j].getValue() == world.getArray()[i+1][j].getValue())
					return false;
		return true;
	}

	/***************************/
	private class Check {
		private boolean check;

		public void setCheck(boolean check) {
			this.check = check;
		}
	}

	public void onDlgGameOverBtnRestartClick() {
		// TODO Auto-generated method stub
		World world = new World(2);
		PlayController playController = new PlayController(world);
		PlayRenderer playRenderer = new PlayRenderer(playController, world);
		
		((Game)(Gdx.app.getApplicationListener())).setScreen(new PlayScreen(playController, playRenderer));
	}

	public void onDlgGameOverBtnBackClick() {
		// TODO Auto-generated method stub
		PlayController.setPlayScreen();
	}

	public void onDlgPauseBtnResumeClick() {
		// TODO Auto-generated method stub
		pause = false;
	}

	public void onDlgPauseBtnQuitClick() {
		// TODO Auto-generated method stub
		StartController.setStartScreen();
	}

	public void onBtnRestartClick() {
		// TODO Auto-generated method stub
		PlayController.setPlayScreen();
	}

	public static void setPlayScreen(){
		World world = new World(2);
		PlayController playController = new PlayController(world);
		PlayRenderer playRenderer = new PlayRenderer(playController, world);
		
		((Game)(Gdx.app.getApplicationListener())).setScreen(new PlayScreen(playController, playRenderer));
	}

}
