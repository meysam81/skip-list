import java.util.Scanner;
public class Test3
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int q = scanner.nextInt();
		SkipList S = new SkipList();
		for (int i = 0; i < q; i++) {
			int type = scanner.nextInt();
			int value = scanner.nextInt();

			switch (type) {
			case 1:
				Integer tmp = S.get(new Integer(value));
				if (tmp != null)
					System.out.println(1);
				else
					System.out.println(0);
				break;
			case 2:
				S.put(Integer.toString(value), new Integer(value));
				break;
			case 3:
				S.deleteElement(new Integer(value));
				break;
			default:
				break;
			}
		}
	}
}

