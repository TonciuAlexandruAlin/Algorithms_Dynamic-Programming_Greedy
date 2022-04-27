import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Prinel {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(new File("prinel.in"));
		PrintWriter printWriter = new PrintWriter("prinel.out");

		// citesc N-ul si K-ul
		final int N = scanner.nextInt();
		final int K = scanner.nextInt();
		Prinel obj = new Prinel();

		int[] target = new int[N + 1];
		int[] p = new int[N + 1];

		// citesc vectorul de target-uri
		for (int i = 1; i <= N; i++) {
			target[i] = scanner.nextInt();
		}

		// citesc vectorul de puncte
		for (int i = 1; i <= N; i++) {
			p[i] = scanner.nextInt();
		}

		// printez solutia
		int[] steps;
		int sum1 = 0, sum2 = 0;
		steps = obj.steps(target);
		for (int i = 1; i < steps.length; i++) {
			sum1 += steps[i];
		}
		// calculez suma punctelor din vectorul p
		for (int i = 1; i < p.length; i++) {
			sum2 += p[i];
		}
		// daca suma elementelor din steps este mai mica decat capacitatea
		// rucsacului, e clar ca iau toate elementele si nu mai e nevoie
		// sa apelez functia rucsac
		if (sum1 < K) {
			printWriter.printf("%d\n", sum2);
		} else {
			printWriter.printf("%d\n", obj.rucsac(N, K, steps, p));
		}
		printWriter.close();
		scanner.close();
	}

	public int[] steps(int[] target) {

		// declar vectorul de pasi
		int[] step = new int[target.length];

		// caut maximul din vectorul target
		int max = -1;
		for (int i = 1; i < target.length; i++) {
			if (target[i] > max) {
				max = target[i];
			}
		}

		// initializez vectorul dp cu max + 1
		// deoarece cea mai proasta solutie ar fi
		// adunari repetate de 1 pana la numarul maxim din target
		int[] dp = new int[max + 1];
		dp[1] = 0;
		for (int i = 2; i < dp.length; i++) {
			// pentru functia min de mai jos
			dp[i] = Integer.MAX_VALUE;
		}

		// parcurg intervalul [1, target]
		for (int i = 1; i <= max; i++) {
			for (int j = 1; j * j <= i; j++) {
				// daca j este un divizor al lui i
				if (i % j == 0) {
					// daca pot aduna divizorul la numar pana ajung la maxim
					if (j + i <= max) {
						// aleg calea cea mai scurta
						dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
					}
					// daca pot aduna numarul la numarul impartit la divizor pana
					// ajung la maxim
					if ((i + i / j) <= max) {
						// aleg calea cea mai scurta
						dp[i + i / j] = Math.min(dp[i + i / j], dp[i] + 1);
					}
				}
			}
		}
		// adaug solutiile doar pentru targeturile mele
		for (int i = 1; i < target.length; i++) {
			step[i] = dp[target[i]];
		}

		return step;
	}

	// problema rucsacului pentru maximul de puncte
	public int rucsac(int N, int K, int[] steps, int[] p) {

		int[][] dp = new int[N + 1][K + 1];

		// caz de baza pentru rucsac
		for (int i = 0; i <= K; i++) {
			dp[0][i] = 0;
		}

		// cazul general pentru rucsac
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				// aleg cea mai buna solutie cu i-1 elemente
				dp[i][j] = dp[i - 1][j];

				if (j - steps[i] >= 0) {
					// castig in + punctele de pe pozitia i
					int sol_aux = dp[i - 1][j - steps[i]] + p[i];
					dp[i][j] = Math.max(dp[i][j], sol_aux);
				}
			}
		}
		// solutia e stocata in dreapta jos in matrice
		return dp[N][K];
	}
}
