package elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.stream.JsonReader;

public abstract class Parser<T> {
	
	public static File file;
	public JsonReader reader;
	
	public Parser(File inputFile) {
		file = inputFile;
		try {
			reader = new JsonReader(Files.newBufferedReader(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Parser(JsonReader reader) {
		this.reader = reader;
	}
	
	protected abstract T read() throws IOException;

}
