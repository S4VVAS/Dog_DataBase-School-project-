package prog1_inlm_upg_v4;

public class StaticMethods {
	
	public static String firstStrToUp(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	public static boolean checkStringIfEmpty(String name) {
		return (name.isEmpty() || name == null) ? true : false;
	}
	
	public static void printMessage(String x) {
		System.out.print(x);
		ln(1);
	}
	
	public static void ln(int x) {
		for (int i = 0; i < x; i++)
			System.out.println();
	}
}
