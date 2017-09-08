package phl.mk;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Worker {

	public static List<String> getFileNames(List<String> fileNames, Path dir, String searchFile) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for (Path path : stream) {
				if (path.toFile().isDirectory()) {
					getFileNames(fileNames, path, searchFile);
				} else if (path.getFileName().toString().toLowerCase().contains(searchFile.toLowerCase())) {

					fileNames.add(path.toAbsolutePath().toString());
					System.out.println(path.toAbsolutePath().toString());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileNames;
	}

}
