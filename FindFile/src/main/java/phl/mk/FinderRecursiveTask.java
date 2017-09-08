package phl.mk;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * Created by Tomasz Śmiechowicz on 08.09.17.
 */
public class FinderRecursiveTask extends RecursiveTask<List<String>> {

	private String targetName;

	private Path path;

	public FinderRecursiveTask(String targetName, Path path) {
		this.targetName = targetName;
		this.path = path;
	}

	@Override
	protected List<String> compute() {
		if(path.toFile().isDirectory()){

			System.out.println(Thread.currentThread() + "splitting " + path);
			List<FinderRecursiveTask> subtasks = new ArrayList<>();
			subtasks.addAll(createSubtasks());
			for(FinderRecursiveTask subtask : subtasks){
				subtask.fork();
			}

			List<String> result = new ArrayList<>();
			for(FinderRecursiveTask subtask : subtasks) {
				result.addAll(subtask.join());
			}
			return result;

		} else {
			System.out.println(Thread.currentThread() + "Doing workLoad myself: " + path);
			final ArrayList<String> list = new ArrayList<>();
			if (path.getFileName().toString().toLowerCase().contains(targetName)){
				list.add(path.toAbsolutePath().toString());
			}
			return list;
		}
	}

	private List<FinderRecursiveTask> createSubtasks() {
		try {

			return Files.list(path)
					.map(p -> new FinderRecursiveTask(targetName, p))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}