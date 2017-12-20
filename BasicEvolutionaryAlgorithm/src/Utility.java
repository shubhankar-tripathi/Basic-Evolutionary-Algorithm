
public class Utility {
	public static StringBuilder zeroPadding(StringBuilder strb, int n) {
		if (strb.length() < n) {
			strb.reverse();
			for (int i = 0; i < n-strb.length(); i++) {
				strb.append("0");
			}
		}
		return strb.reverse();

	}
}
