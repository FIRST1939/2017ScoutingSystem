package elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Vector;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import com.adithyasairam.tba4j.models.Event;
import com.adithyasairam.tba4j.models.Match;

public class Tools {
	
	static String matchKey = "";
	static Object result;
	static final String EVENT_URL_BASE = "https://www.thebluealliance.com/api/v2/event/";
	static final String MATCH_URL_BASE = "https://www.thebluealliance.com/api/v2/match/";
	static final String URL_END = "?X-TBA-App-Id=frc1939:ScoutingSystem:v2";
	
	static List<String> output = new Vector<String>();
	static int counter = -1;
	
	private final static String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static List<String> getTeamNumbers(String matchId) throws IOException {
		List<String> nums = null;
		try {
			JsonReader reader = createMatchJsonReader(matchId);
			nums = readTeamNumbers(reader);
			
		} catch (MalformedURLException e) {
			System.err.println("Cannot create the url");
		} catch (UnknownHostException e) {
			System.err.println("Cannot connect to www.thebluealliance.com");
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		for (int i = 0; i < nums.size(); i++) {
			nums.set(i, nums.get(i).replace("frc", ""));
		}
		
		Vector<String> temp = new Vector<String>();
		temp.add(nums.get(3));
		temp.add(nums.get(4));
		temp.add(nums.get(5));
		temp.add(nums.get(0));
		temp.add(nums.get(1));
		temp.add(nums.get(2));
		return temp;
	}
	
	private static JsonReader createMatchJsonReader(String matchId) throws IOException {
		try {
			URL url = new URL(MATCH_URL_BASE + matchId + URL_END);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows 10; WOW64; rv:25.0) Gecko/20100101 Chrome/51.0.2704.103");
			connection.connect();
			JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
			
			return reader;			
		} catch (MalformedURLException e) {
			System.err.println("Cannot create the url");
		} catch (UnknownHostException e) {
			System.err.println("Cannot connect to www.thebluealliance.com");
		} catch (IOException e) {
			throw e;
		}
		return null;
	}
	
	private static String getMatchId(JsonReader reader) throws IOException {
		String output = "";
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("key")) {
				output = reader.nextString();
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		return output;
	}
	
	private static List<String> readTeamNumbers(JsonReader reader) throws IOException {
		List<String> output = new Vector<String>();
		
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("alliances")) {
				output.addAll(readAlliances(reader));
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		return output;
	}
	
	private static List<String> readAlliances(JsonReader reader) throws IOException {
		List<String> output = new Vector<String>();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("blue")) {
				output.addAll(readAlliance(reader));
			}
			else if (name.equals("red")) {
				output.addAll(readAlliance(reader));
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}	
	
	
	private static List<String> readAlliance(JsonReader reader) throws IOException {
		List<String> output = new Vector<String>();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("teams")) {
				output = readTeams(reader);
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}

	private static List<String> readTeams(JsonReader reader) throws IOException {
		Vector<String> output = new Vector<String>();
		reader.beginArray();
		while (reader.hasNext()) {
			output.add(reader.nextString());
		}
		reader.endArray();
		return output;
	}
	
	private static Path createNewJsonFile() {
		try {
			counter++;
			return Files.createTempFile("temp" + counter, ".json");

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}