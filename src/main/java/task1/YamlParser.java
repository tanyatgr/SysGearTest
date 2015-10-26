package task1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public class YamlParser {

	public static Logger logger = LogManager.getLogger(YamlParser.class);

	public static int ARRAY_SIZE = 21;
	public static int MAX = 1000;
	public static int MIN = 0;
	private String fileName = "test.yml";

	/**
	 * Generates tested array
	 * 
	 * @return
	 */
	public Integer[] generateArray() {
		int rand;
		ArrayList<Integer> workList = new ArrayList<Integer>(ARRAY_SIZE);
		for (int i = 0; i < ARRAY_SIZE / 2; i++) {
			rand = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
			workList.add(rand);
			workList.add(rand);
		}
		workList.add(MIN + (int) (Math.random() * ((MAX - MIN) + 1)));
		Collections.shuffle(workList);
		Integer[] generatedArray = workList.toArray(new Integer[ARRAY_SIZE]);
		return generatedArray;
	}

	/**
	 * Writes generated array in file with YAML format
	 * 
	 * @param generatedArray
	 */

	public void writeArray(Integer[] generatedArray) {
		try (FileWriter writer = new FileWriter(new File(fileName));) {
			Yaml yaml = new Yaml();
			yaml.dump(generatedArray, writer);
			writer.close();
		} catch (IOException e) {
			logger.error("Not able to write file " + fileName, e);
		}
	}

	/**
	 * Reads array from file
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer[] readArray() {
		try (InputStream input = new FileInputStream(new File(fileName))) {

			Yaml yaml = new Yaml();
			Object data = yaml.load(input);

			if (data instanceof ArrayList<?>) {
				return ((ArrayList<Integer>) data)
						.toArray(new Integer[ARRAY_SIZE]);
			}

		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e1) {
			logger.error(e1);
		}
		return null;

	}

	/**
	 * Finds single entry in array
	 * 
	 * @param someArray
	 * @return
	 * @throws Exception
	 */
	public int findAlone(Integer[] someArray) throws Exception {
		if (someArray == null) {
			throw new Exception("Null array passed");
		}
		int result = 0;
		for (int i : someArray) {
			result ^= i;
		}
		return result;

	}

	public static void main(String args[]) {
		YamlParser yamlParser = new YamlParser();
		if (args.length == 1) {
			yamlParser.fileName = args[0];
		}
		try {
			Integer[] array = yamlParser.readArray();
			int result = yamlParser.findAlone(array);
			System.out.println("Number with only one entry : " + result);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
