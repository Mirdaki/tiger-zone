package entities;

import java.util.ArrayList;
import java.util.Collections;

public class addSubTigers {
	/*//these were created explicity to construct a test region
	private Player tester = new Player("human", false);
	private CrocodileObject croc = new CrocodileObject(tester);
	//if list is empty the test will have an index out of bounds exception
	private ArrayList<Integer> list = new ArrayList<Integer>(Collections.nCopies(1,0));*/

	private LakeTerrain testTerrain = new LakeTerrain();

	//test region
	private LakeRegion loneRegion = new LakeRegion(testTerrain);

	//lets you add howMany tigers to loneRegion
	public void setAddATiger(int howMany) {
		for(int i = 0; i < howMany; i++) {
			TigerObject stray = new TigerObject();
			this.loneRegion.addTiger();
		}
	}
	//Fitnesse requires variables be passed into void functions doOrDont
	//just means should the method run in fitnesse
	//a value of 0 allows method to run
	public void setSubTigers(int doOrDont) {
		if(doOrDont == 0) {
			this.loneRegion.removeAllTigers();
		}
		else {

		}
	}

	public void setSubTiger(int doOrDont) {
		if(doOrDont == 0) {
			this.loneRegion.removeTiger();
		}
		else {

		}
	}

	//tells how many tigers are in loneRegion should be equal to howMany
	public int amountOfTigers() {
		return this.loneRegion.theTigers.size();
	}

	//tells if tigers are in the region if howMany > 0 it should be true
	public boolean hasTigers() {
		return this.loneRegion.hasTigers();
	}
}
