package hust.thubm.game2048.entities;

import hust.thubm.game2048.controllers.PlayController.Touch;
import hust.thubm.game2048.screens.GameScreen;

import java.util.Random;


public class World {
	private int n;
	private Cell array[][];
	private Touch beforeState = Touch.NOTHING;
	
	public World(int n) {
		// TODO Auto-generated constructor stub
		this.n = n;
		this.initArray();
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public Cell[][] getArray() {
		return array;
	}

	public void setArray(Cell[][] array) {
		this.array = array;
	}
	
	public Touch getBeforeState() {
		return beforeState;
	}

	public void setBeforeState(Touch beforeState) {
		this.beforeState = beforeState;
	}

	/***************************************/
	
	public void update(float delta){
		for (int i = 0; i < this.n; i++)
			for (int j = 0; j < this.n; j++)
				array[i][j].update(delta);
	}
	
	/**
	 * Init array
	 */
	private void initArray(){
		float posx;
		float posy;
		float width = 36f * GameScreen.ppuX / this.n;
		float height = 36f * GameScreen.ppuY / this.n;
		Random random = new Random();
		int appearNumbers = random.nextInt(3) + 1;
		
		this.array = new Cell[n][n];
		
		for (int i = 0; i < this.n; i++){
			posx = 2f * GameScreen.ppuX;
			posy = 5f * GameScreen.ppuY + i * width;
			
			for (int j = 0; j < this.n; j++){
				array[this.n - i - 1][j] = new Cell(i, j, posx, posy, width, height);
				posx += width;
			}
		}
		
		while (appearNumbers-- > 0)
			getNewCell();
	}
	
	/**
	 * Print array to console
	 */
	public void showArray(){
		int i;
		int j;
		for (i = 0 ; i < this.n; i++){
			for (j = 0; j < this.n; j++)
				System.out.print(" " + array[i][j].getValue());
			System.out.println();
		}
	}
	
	/**
	 * Get new cell
	 */
	public void getNewCell(){
		Random random = new Random();
		int i, j;
		do {
			i = random.nextInt(this.n);
			j = random.nextInt(this.n);
		} while (array[i][j].getValue() != 0);
		
		array[i][j].setValue((random.nextInt(2) + 1) * 2);
	}
	
	/**
	 * Get number of cell that have value different 0
	 * @return
	 */
	public int getNumberOfCell(){
		int count = 0;
		for (int i = 0; i < this.n; i++)
			for (Cell cell : this.array[i]) {
				if (cell.getValue() != 0)
					count ++;
			}
		
		return count;
	}
}
