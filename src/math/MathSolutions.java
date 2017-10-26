package math;

import java.util.Random;

public class MathSolutions {
	
	public boolean isPrime(int n) {
		if(n < 2)
			return false;
		
		for(int i = 0; i <= Math.sqrt(n); i++) {
			if((n % i) == 0)
				return false;
		}
		return true;
	}
	
	public double probOfOneGirlChildPerFamily(int n) {
		
		int totalGirls = 0;
		int totalBoys = 0;
		for(int i = 0; i < n; i++) {
			int girlChildren = 0;
			int boyChildren = 0;
			Random random = new Random();
			while(girlChildren == 0) {
				if(random.nextBoolean()) {
					girlChildren += 1;
				} else {
					boyChildren += 1;
				}
			}
			totalGirls += girlChildren;
			totalBoys += boyChildren;
		}
		
		return totalGirls / (double)(totalGirls + totalBoys);
	}

}
