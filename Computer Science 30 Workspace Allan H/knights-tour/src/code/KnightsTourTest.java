package code;

public class KnightsTourTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		LinkedGrid LG = new LinkedGrid(5);
		LG.knightConnect();
		LG.display();
		LG.knightsTour(LG.findCoords(3, 3));
		System.out.println(LG.getCount());
	}

}
