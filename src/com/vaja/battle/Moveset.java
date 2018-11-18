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
     * Index:
     * 0 - accurate
     * 1 - wide
     * 2 - crit
     * 3 - heal
     */
    public Move[] moveset;
    public String[] text;

    public Moveset(ResourceManage rm) {
        this.rm = rm;
        rand = new Random();

        moveset = new Move[4];
        text = new String[4];
    }

    /**
     * Resets a Moveset with a set of 4 new random Moves
     */
    public void reset(int dmg, int hp) {
        moveset = getRandomMoves();
        for (int i = 0; i < 4; i++) {
            if (moveset[i].type == 3) moveset[i].setHeal(hp);
            else moveset[i].setDamage(dmg);
            // Concatenates move info into a full description
            if (moveset[i].type < 2) {
                text[i] = moveset[i].name + "\n" +
                        moveset[i].description + "\n" +
                        "Damage: " + moveset[i].minDamage + "-" + moveset[i].maxDamage;
            } else if (moveset[i].type == 2) {
                text[i] = moveset[i].name + "\n" +
                        moveset[i].description + "\n" +
                        "Damage: " + moveset[i].minDamage;
            } else {
                text[i] = moveset[i].name + "\n" +
                        moveset[i].description + "\n" +
                        "Heals: " + moveset[i].minHeal+ "-" + moveset[i].maxHeal;
            }
        }
    }

    /**
     * Returns a Move array  with 4 unique moves chosen from all possible Moves
     *
     * @return
     */
    private Move[] getRandomMoves() {
        Array<Move> all = new Array<Move>();
        all.addAll(rm.accurateMoves);
        all.addAll(rm.wideMoves);
        all.addAll(rm.critMoves);
        all.addAll(rm.healMoves);

        Move[] ret = new Move[4];

        int index;
        for (int i = 0; i < ret.length; i++) {
            index = rand.nextInt(all.size);
            ret[i] = all.get(index);
            all.removeIndex(index);
        }

        return ret;
    }

    public Move getAccurateMove() {
        return moveset[0];
    }

    public Move getWideMove() {
        return moveset[1];
    }

    public Move getCritMove() {
        return moveset[2];
    }

    public Move getHealMove() {
        return moveset[3];
    }

}
