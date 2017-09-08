package phl.mk;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Starter {

	public static void main(String[] args) {

		List<String> listFile = new ArrayList<>();
		String searchFile = args[0];
		Path rootDir = Paths.get(".");
		Worker.getFileNames(listFile, rootDir, searchFile);

	}
}
