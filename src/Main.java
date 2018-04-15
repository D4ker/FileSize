import java.util.Scanner;

public class Main {

    // Метод для работы с консолью
    public static void main(String[] args) {
        try {
            final Scanner in = new Scanner(System.in);
            final FileSize data = new FileSize(in.nextLine());

            final String[] size = data.getSize();
            final String[] filePath = data.getFilePath();
            final int length = size.length;
            for (int i = 0; i < length; i++) {
                System.out.println(filePath[i] + ": " + size[i]);
            }
        } catch (IllegalArgumentException e) {
            System.exit(1);
        }
    }

}