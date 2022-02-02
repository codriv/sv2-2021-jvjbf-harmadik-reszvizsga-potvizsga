package examinformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                .filter(name -> results.get(name).getTheory() < (int)(theoryMax * 0.51) || results.get(name).getPractice() < (int)(practiceMax * 0.51)).toList();
    }

    public String findBestPerson() {
        return results.keySet().stream()
                .filter(name -> results.get(name).getTheory() > (int)(theoryMax * 0.5) && results.get(name).getPractice() > (int)(practiceMax * 0.5))
                .max((o1, o2) -> getfullResult(o1) - getfullResult(o2) == 0 ? o1.compareTo(o2) : getfullResult(o1) - getfullResult(o2))
                .orElseThrow(() -> new IllegalStateException("List of results maybe empty!"));
    }

    private int getfullResult(String name) {
        return results.get(name).getTheory() + results.get(name).getPractice();
    }
}
