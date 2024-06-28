import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class task2 {
    public static void main(String[] args) throws IOException {
        String circleFile = "src\\file1.txt";
        String pointsFile = "src\\file2.txt";

        Circle circle = readCircleData(circleFile);
        Point[] points = readPointsData(pointsFile);

        for (Point point : points) {
            System.out.println(determinePosition(circle, point));
        }

    }

    private static Circle readCircleData(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] center = reader.readLine().trim().split("\\s+");
            double x = Double.parseDouble(center[0]);
            double y = Double.parseDouble(center[1]);
            double radius = Double.parseDouble(reader.readLine().trim());
            return new Circle(x, y, radius);
        }
    }

    private static Point[] readPointsData(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.lines()
                    .map(line -> {
                        String[] coordinates = line.trim().split("\\s+");
                        double x = Double.parseDouble(coordinates[0]);
                        double y = Double.parseDouble(coordinates[1]);
                        return new Point(x, y);
                    })
                    .toArray(Point[]::new);
        }
    }

    private static int determinePosition(Circle circle, Point point) {
        double distance =
                Math.sqrt(Math.pow(point.x - circle.x, 2) + Math.pow(point.y - circle.y, 2));
        if (distance == circle.radius) {
            return 0;
        } else if (distance < circle.radius) {
            return 1;
        } else {
            return 2;
        }
    }

    static class Circle {
        double x, y, radius;

        Circle(double x, double y, double radius) {
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }

    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
