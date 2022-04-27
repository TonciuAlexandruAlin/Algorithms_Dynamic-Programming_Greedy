import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Statistics {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(new File("statistics.in"));
		PrintWriter printWriter = new PrintWriter("statistics.out");

		int n = scanner.nextInt();
		ArrayList<String> x = new ArrayList<>(n);
		Statistics obj = new Statistics();

		// citesc fiecare string din fisierul de intrare
		for (int i = 0; i < n; i++) {
			x.add(i, scanner.next());
		}
		printWriter.printf("%d\n", obj.solve(x));
		printWriter.close();
		scanner.close();
	}

	int solve(ArrayList<String> x) {

		int counter = 0, nr_words = 0, nr_letters = 0, nr_total = 0;

		// parcurg fiecare litera din alfabet
		for (char letter = 'a'; letter <= 'z'; letter++) {

			// sortez arraylist-ul meu de String-uri dupa regula din comparator
			final char finalLetter = letter;
			x.sort((o1, o2) -> (2 * number_letters(finalLetter, o2) - o2.length())
					- (2 * number_letters(finalLetter, o1) - o1.length()));

			//iterez prin cuvintele din arraylist
			for (String s : x) {
				// daca 2*numarul de aparitii este mai mare decat
				// lungimea totala
				if (2 * (nr_letters + number_letters(letter, s))
						> nr_total + s.length()) {
					// cresc numarul de cuvinte concatenate, la lungimea
					// totala adaug lungimea cuvantului pe care il concatenez
					// si adaug numarul de aparitii al literei la numarul
					// total de aparitii
					nr_words++;
					nr_total = nr_total + s.length();
					nr_letters = nr_letters + number_letters(letter, s);
				}
			}
			// counterul ia cel mai mare numar de cuvinte concatenate
			if (nr_words > counter) {
				counter = nr_words;
			}
			// resetez valorile pentru urmatoarea litera
			nr_total = nr_letters = nr_words = 0;
		}
		return counter;
	}

	// verific de cate ori se afla o litera intr-un cuvant
	int number_letters(char x, String word) {
		int c = 0;
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == x) {
				c++;
			}
		}
		return c;
	}
}