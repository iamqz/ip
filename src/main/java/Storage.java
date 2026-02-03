import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Handles the reading (loading) and writing (saving) of Tasks as data to a file.
 * The file location must be specified when creating a Storage object
 */
public class Storage {

    private final Path filePath;

    /**
     * Creates a Storage object using the file path given as {@code filePath}
     * @param filePath The path to the data file used for the storage of the Tasks
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the file specified in the {@code filePath} exists.
     * If no, it creates directory (if necessary) and the file.
     * @throws IOException If an Input/Output error occurs while creating the directory or the file
     */
    private void checkFile() throws IOException {
        if (!Files.exists(filePath)) {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            Files.createFile(filePath);
        }
    }

    /**
     * Reads Tasks from the file specified in the {@code filePath} and returns it as List<String>
     * @return A list of strings, where each string represents one task
     * @throws IOException If an Input/Output error occurs while reading (loading) from the storage file
     */
    public List<String> readTasks() throws IOException {
        checkFile();
        return Files.readAllLines(filePath);
    }

    /**
     * Writes Tasks to the file specified in the {@code filePath}
     * @param tasks A list of strings, where each string represents one task
     * @throws IOException If an Input/Output error occurs while writing (saving) from the storage file
     */
    public void writeTasks(List<String> tasks) throws IOException {
        checkFile();
        Files.write(filePath, tasks);
    }
}
