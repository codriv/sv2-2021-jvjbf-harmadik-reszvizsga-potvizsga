package examinformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ExamService {

    private Map<String, ExamResult> results = new TreeMap<>(String::compareTo);
    private int theoryMax;
    private int practiceMax;

    public Map<String, ExamResult> getResults() {
        return results;
    }

    public void readFromFIle(Path path) {
        try {
            fillResults(Files.readAllLines(path));
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: src\\main\\java\\data.txt");
        }
    }

    private void fillResults(List<String> fileContent) {
        theoryMax = Integer.parseInt(fileContent.get(0).split(" ")[0]);
        practiceMax = Integer.parseInt(fileContent.get(0).split(" ")[1]);
        fileContent.remove(0);
        fileContent.forEach(line -> {
            String[] parts = line.split(";");
            String name = parts[0];
            String[] pointParts = parts[1].split(" ");
            int theory = Integer.parseInt(pointParts[0]);
            int practice = Integer.parseInt(pointParts[1]);
            results.put(name, new ExamResult(theory, practice));
        });
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public List<String> findPeopleFailed() {
        return results.keySet().stream()
                .filter(this::isExamUnsuccessful).toList();
    }

    public String findBestPerson() {
        return results.keySet().stream()
                .filter(this::isExamSuccessful)
                .max(Comparator.comparingInt(this::getFullResult).thenComparing(o -> o))
                .orElseThrow(() -> new IllegalStateException("List of results maybe empty!"));
    }

    private int getFullResult(String name) {
        return results.get(name).getTheory() + results.get(name).getPractice();
    }

    private boolean isExamSuccessful(String name) {
        return results.get(name).getTheory() >= (int)(theoryMax * 0.51)
                && results.get(name).getPractice() >= (int)(practiceMax * 0.51);
    }

    private boolean isExamUnsuccessful(String name) {
        return results.get(name).getTheory() < (int)(theoryMax * 0.51)
                || results.get(name).getPractice() < (int)(practiceMax * 0.51);
    }
}