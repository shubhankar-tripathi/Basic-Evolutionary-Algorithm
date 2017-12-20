import java.util.LinkedList;
import java.util.List;

public class Candidate {
	//static LinkedHashMap<String, Double> population = new LinkedHashMap<String,Double>();
	static List<String> population = new LinkedList<String>();
	static List<Double> valueList = new LinkedList<Double>();
	static int popSize = 500;
	static String id;
	int n; // number of bits of the binary vector representing a candidate solution.
	double fitVal;

	public void addCandidate(String id) {
		Candidate.id = id;
	}

	public double setFitness(int n) {
		this.n = n;
		int sum = 0;
		char[] arr = id.toCharArray();
		for (char c : arr) {
			if (Character.getNumericValue(c) == 1) {
				sum++;
			}
		}
		fitVal = (float) sum / n;
		return fitVal;
	}

}
