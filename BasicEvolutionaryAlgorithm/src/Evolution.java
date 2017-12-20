import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Evolution {
	private static Random random = new Random();
	static List<String> keySelect = new LinkedList<String>();
	/* static List<String> crossOvered = new ArrayList<String>(); */

	public void selection(int k,int n) {// how about k>2 tournament though, how does that supposed to happen??
		for (int i = 0; i < k; i++) {
			StringBuilder strb = new StringBuilder(Candidate.population.get(random.nextInt(Candidate.population.size())));
			if (strb.length() < n) {
				strb.reverse();
				for (int j = 0; j < n - strb.length(); j++) {
					strb.append("0");
				}
			}
			strb.reverse();
			keySelect.add(strb.toString());
		}

	}

	public void crossOver(int p, double cRate) {
		/*
		 * for (String str : keySelect) { StringBuilder build = new StringBuilder();
		 * build.append(str); for (int i = 0; i < p; i++) {// what about p-point
		 * crossover though, how does that happen?? int cPoint =
		 * random.nextInt(str.length()); build.replace(cPoint - 1, str.length(),
		 * build.substring(cPoint - 1, str.length()).replaceAll("1", "x")
		 * .replaceAll("0", "1").replaceAll("x", "0")); }
		 * crossOvered.add(build.toString()); }
		 */
		for (String str : keySelect) {
			if (Math.random() >= cRate) {
				if (keySelect.indexOf(str)+1 < keySelect.size()) {
					int cPoint = random.nextInt(str.length() - 2) + 1;
					StringBuilder str1 = new StringBuilder(str);
					StringBuilder str2 = new StringBuilder(keySelect.get(keySelect.indexOf(str) + 1));
					
					String replacement = str1.substring(cPoint, str.length());
					// Now replace the bit from the crossover point for the first element.
					str1.replace(cPoint, str1.length(), str2.substring(cPoint, str2.length()));
					int index = keySelect.indexOf(str);
					keySelect.set(index, str1.toString());
					// Now same with the second element.
					str2.replace(cPoint, str2.length(), replacement);
					keySelect.set(index+1, str2.toString());
				}
			}
		}

	}

	public void mutation(double mutRate,int n) {
		for (String str : keySelect) {
			StringBuilder strb = new StringBuilder(str);
			for (int i = 0; i < str.length(); i++) {
				if (Math.random() >= mutRate) {
					strb.setCharAt(i, (char) ~str.charAt(i));
					//strb.delete(0, strb.length()).append(Utility.zeroPadding(strb, n).toString());
					if (strb.length() < n) {
						strb.reverse();
						for (int j = 0; j < n - strb.length(); j++) {
							strb.append("0");
						}
					}
					strb.reverse();
				}
			}
			keySelect.set(keySelect.indexOf(str), strb.toString());
		}

	}

	public void replacement(int n,int K) {
		for(int i=0; i<K; i++) { 
			double valMin = Collections.min(Candidate.valueList);
			int index = Candidate.valueList.indexOf(valMin);
			Candidate replCan = new Candidate();
			replCan.addCandidate(Evolution.keySelect.get(i));
			double value = replCan.setFitness(n);
			Candidate.valueList.set(index, value);
			Candidate.population.set(index, Evolution.keySelect.get(i));
		}
		
	}

}
