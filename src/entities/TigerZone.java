import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class TigerZone {


	public TigerZone() {

	}

	public static void main(String[] args) {


		BoardObject test = new BoardObject(); //create the board

		//example of getting a tile: 'G' (last one on first row)
		SquareTile test0 = test.getTile('G',0); //0 degree rotation
		SquareTile test90 = test.getTile('G',1); //90
		SquareTile test180 = test.getTile('G',2); //180
		SquareTile test270 = test.getTile('G',3); //270

		System.out.println(test0.toString());
		System.out.println(test90.toString());
		System.out.println(test180.toString());
		System.out.println(test270.toString());


	} //end of main

} //end of class
