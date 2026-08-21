import java.io.*;
import java.util.ArrayList;
import java.lang.NumberFormatException;

public class GradeAnalyzer {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Input file name is required");
            return;
        };

        String filename = args[0];
        ArrayList<Integer> scores = readScores(filename);
        
        double average = calculateAverage(scores);
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int countA = 0, 
            countB = 0,
            countC = 0,
            countD = 0,
            countF = 0;


        for (int i = 0; i < scores.size() - 1; i++) {
            int score = scores.get(i);
            if (score < low) {
                low = score;
            } 
            
            if (score > high) {
                high = score;
            }

            if (score >= 90) countA++;
            else if (score >= 80) countB++;
            else if (score >= 70) countC++;
            else if (score >= 60) countD++;
            else countF++;
        }

        if (scores.size() == 1) {
            low = 0;
            high = 0;
        }

        writeReport(scores, average, high, low, "report.txt", countA, countB, countC, countD, countF);
    }

    // Returns a list of valid scores read from the file
    // last entry in scores indicates number of skipped/invalid lines
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        int numSkipped = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 0;

            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (line.trim().isEmpty()) {
                    System.out.println("[Warning]: Line " + lineNum + " Skipped");
                    numSkipped++;
                    continue;
                }
            
                try {
                    int currScore = Integer.parseInt(line);
                    scores.add(currScore);
                } catch (NumberFormatException e) {
                    System.out.println("[Warning]: Line " + lineNum + " Skipped");
                    numSkipped++;
                }
            }
            scores.add(numSkipped);
        } catch (IOException e) {
            System.out.println("Could not read " + filename + ": " + e.getMessage());
        }

        return scores;
    }

    // Returns the average of a list of scores, or 0.0 if the list is empty
    // Assumes arraylist last entry is skipped lines - not counted as part of total
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.size() == 1) return 0.0;
        int total = 0;
        for (int i = 0; i < scores.size() - 1; i++) {
            total += scores.get(i);
        }

        return (double) total / (scores.size() - 1);
    }

    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile,
                                   int countA, int countB, int countC, int countD, int countF) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("=== Grade Analysis Report ===");
            System.out.println("=== Grade Analysis Report ===");
            writer.newLine();
            
            writer.write(String.format("Total scores processed: %3d%n", scores.size() - 1));
            System.out.println(String.format("Total scores processed: %3d", scores.size() - 1));
            writer.write(String.format("Invalid lines skipped: %4d%n", scores.get(scores.size() - 1)));
            writer.newLine();
            System.out.println(String.format("Invalid lines skipped: %4d%n", scores.get(scores.size() - 1)));

            writer.write(String.format("Average score: %3.2f%n", avg));
            System.out.println(String.format("Average score: %3.2f", avg));
            writer.write(String.format("Highest score: %2d%n", high));
            System.out.println(String.format("Highest score: %2d", high));
            writer.write(String.format("Lowest score: %3d%n", low));
            writer.newLine();
            System.out.println(String.format("Lowest score: %3d%n", low));

            writer.write(String.format("Grade distribution:%n"));
            writer.write(String.format("A (90-100): %3d%n", countA));
            writer.write(String.format("B (80-89): %4d%n", countB));
            writer.write(String.format("C (70-79): %4d%n", countC));
            writer.write(String.format("D (60-69): %4d%n", countD));
            writer.write(String.format("F (below 60): %1d%n", countF));

            System.out.println(String.format("Grade distribution:"));
            System.out.println(String.format("A (90-100): %3d", countA));
            System.out.println(String.format("B (80-89): %4d", countB));
            System.out.println(String.format("C (70-79): %4d", countC));
            System.out.println(String.format("D (60-69): %4d", countD));
            System.out.println(String.format("F (below 60): %1d", countF));

        } catch (IOException e) {
            System.out.println("Could not write to " + outputFile + ": " + e.getMessage());
        }
    }
}