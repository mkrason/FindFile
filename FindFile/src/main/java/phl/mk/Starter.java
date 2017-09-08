package phl.mk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Starter {

	private static ForkJoinPool pool = new ForkJoinPool(4);

	public static void main(String[] args) throws IOException {

		System.out.println("enter sth");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final List<String> targetNames = pool.invoke(new FinderRecursiveTask(br.readLine().toLowerCase(), Paths.get(".")));

		System.out.println(targetNames);
	}
}
