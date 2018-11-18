package com.vaja.battle;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.vaja.resource.ResourceManage;

/**
 * this class is set of random move 
 * @author khingbmc
 *
 */
public class Moveset {
	private Random rand;
	private ResourceManage rm;
	
	/**
	 * index of moveset:
	 * 0 - accurate
	 * 1 - wide
	 * 2 - crit
	 * 3 - heal
	 */
	
	public Move[] moveset;
	
	public Moveset(ResourceManage rm) {
		this.rm = rm;
		this.rand = new Random();
		this.moveset = new Move[4];
	}
	
	//Reset Moveset with set of 4 new Random Move
	public void reset(int dmg, int hp) {
		this.moveset = getRandomMove();
		for (int i = 0; i < 4;i++) {
			if(this.moveset[i].type == 3) this.moveset[i].setHeal(hp);
			else this.moveset[i].setDmg(dmg);
		}
	}
	
	/**
	 * return move array with 4 moveset so it is possible all move
	 */
	
	private Move[] getRandomMove() {
		Array<Move> all = new Array<Move>();
		all.addAll(rm.accurateMove);
		all.addAll(rm.wideMove);
		all.addAll(rm.critMove);
		all.addAll(rm.healMove);
		
		Move[] check = new Move[4];
		
		int checkIndex;
		for(int i = 0;i<check.length;i++) {
			checkIndex = this.rand.nextInt(all.size);
			check[i] = all.get(checkIndex);
			all.removeIndex(checkIndex);
		}
		return check;
	}
	public Move getAccMove() {
		return this.moveset[0];
	}
	
	public Move getWideMove() {
		return this.moveset[1];
	}
	
	public Move getCritMove() {
		return this.moveset[2];
	}
	
	public Move getHealMove() {
		return this.moveset[3];
	}
}
