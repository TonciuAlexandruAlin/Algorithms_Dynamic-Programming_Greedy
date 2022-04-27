import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Walsh {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(new File("walsh.in"));
		PrintWriter printWriter = new PrintWriter("walsh.out");

		// citesc dimensiunea patratului
		int n = scanner.nextInt();
		// citesc numarul de perechi
		int x = scanner.nextInt();
		Walsh obj = new Walsh();

		for (int i = 0; i < x; i++) {
			// citesc cate o pereche
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			// apelez functia pentru fiecare pereche, iar ca parametri de start
			// dau coltul stanga sus si coltul dreapta jos al patratului mare
			printWriter.printf("%d\n", obj.solve(a, b, 1, 1, n, n, 0));
		}
		printWriter.close();
		scanner.close();
	}

	int solve(int x, int y, int tlx, int tly, int drx, int dry, int count) {

		// verific daca coltul stanga sus este egal cu coltul dreapta jos
		// (daca am ajuns la o singura celula)
		if (tlx == drx && tly == dry) {
			// count numara de cate ori am intrat in cadranul 4
			// (cadranul cu numerele negate); 0 negat de 2 ori este
			// 0;
			if (count % 2 == 0) {
				return 0;
			}
			return 1;
		}

		// calculez mijlocul patratului curent
		int middle_x = (tlx + drx) / 2;
		int middle_y = (tly + dry) / 2;

		// daca sunt in patratul din stanga sus
		if (x <= middle_x && y <= middle_y) {
			return solve(x, y, tlx, tly, middle_x, middle_y, count);
		}

		// daca sunt in patratul din dreapta sus
		if (x > middle_x && y <= middle_y) {
			return solve(x, y, middle_x + 1, tly, drx, middle_y, count);
		}

		// daca sunt in patratul din stanga jos
		if (x <= middle_x) {
			return solve(x, y, tlx, middle_y + 1, middle_x, dry, count);
		}

		// daca sunt in patratul din stanga sus
		return solve(x, y, middle_x + 1, middle_y + 1, drx, dry, count + 1);
	}
}