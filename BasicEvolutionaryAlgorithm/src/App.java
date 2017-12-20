import java.util.Collections;
import java.util.Random;

public class App {
	private static Random random = new Random();
	private static final int maxgen = 50000;
	private static final int n = 100;
	private static final int K = 200; // K- Tournament
	private static final int p = 1; // p- point crossOver method
	private static final double mutRate = 0.05;
	private static final double cRate = 0.8;

	public static void main(String[] args) {
		int max = 0;
		int min = 1;
		for (int i = n; i > 0; i--) {
			max = (int) (max + Math.pow(2, i - 1));

		}
		for (int j = 0; j < Candidate.popSize; j++) {
			Integer rand = random.nextInt(max - min + 1) + min;
			// String str = Utility.zeroPadding(new
			// StringBuilder(Integer.toBinaryString(rand)), n).toString();
			StringBuilder strb = new StringBuilder(Integer.toBinaryString(rand));
			int length = strb.length();
			if (length < n) {
				strb.reverse();
				for (int i = 0; i < n - length; i++) {
					strb.append("0");
				}
			}
			String str = strb.reverse().toString();
			Candidate soln = new Candidate();
			soln.addCandidate(str);
			Candidate.population.add(str);
			double value = soln.setFitness(n);
			Candidate.valueList.add(value);

		}
		String whatever = "max fitness: " + Collections.max(Candidate.valueList) + ", "
				+ Collections.min(Candidate.valueList);
		for (int l = 0; l < maxgen; l++) {
			Evolution breeding = new Evolution();
			breeding.selection(K, n);
			breeding.crossOver(p, cRate);
			breeding.mutation(mutRate, n);
			breeding.replacement(n, K);

			int gen = l + 1;
			System.out.println("Fittest candidate with value: " + Collections.max(Candidate.valueList) + " after " + gen
					+ " generations.");

			Evolution.keySelect.clear();
		}
		System.out.println("Before evolution::> " + whatever);
		System.out.println("After evolution::> " + "max fitness: " + Collections.max(Candidate.valueList) + ", "
				+ Collections.min(Candidate.valueList));
		System.out.println(Candidate.population.size());
		System.out.println(Candidate.valueList.size());
	}
}
