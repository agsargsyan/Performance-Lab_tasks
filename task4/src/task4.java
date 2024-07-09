import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class task4 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java task4 <input_file_path>");
            return;
        }

        String inputFile = args[0];
        int[] nums = readIntArrayFromFile(inputFile);
        int minMoves = minMoves(nums);
        System.out.println("Минимальное количество ходов: " + minMoves);
    }

    private static int[] readIntArrayFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            String[] parts = line.trim().split("\\s+");
            int[] nums = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                nums[i] = Integer.parseInt(parts[i]);
            }
            return nums;
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return new int[0];
        }
    }

    private static int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums); // Сортировка массива

        int medianIndex = nums.length / 2;
        int median = nums[medianIndex]; // Поиск медианы

        int minMoves = 0;
        for (int num : nums) {
            minMoves += Math.abs(num - median); // Суммируем разности до медианы
        }

        return minMoves;
    }
}

