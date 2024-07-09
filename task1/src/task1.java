import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java task1 <array_size> <path_length>");
            return;
        }
        
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        
        String path = findPath(n, m);
        System.out.println("Путь: " + path);
    }

    public static String findPath(int n, int m) {
        StringBuilder path = new StringBuilder();
        int[] circularArray = new int[n];

        // Заполнение массива от 1 до n
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }

        int currentIndex = 0;
        // Перемещение по массиву с учетом кругового характера
        do {
            path.append(circularArray[currentIndex]);
            currentIndex = (currentIndex + m - 1) % n;
        } while (currentIndex != 0);

        return path.toString();
    }
}
