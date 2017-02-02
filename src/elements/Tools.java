package elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Vector;

import com.adithyasairam.tba4j.models.Event;
import com.adithyasairam.tba4j.models.Event.Alliance;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Tools {
	
	static String matchKey = "";
	static Object result;
	static final String EVENT_URL_BASE = "https://www.thebluealliance.com/api/v2/event/";
	static final String MATCH_URL_BASE = "https://www.thebluealliance.com/api/v2/match/";
	static final String URL_END = "?X-TBA-App-Id=frc1939:ScoutingSystem:v2";
	
	static List<String> output = new Vector<String>();
	static int counter = -1;
	
	private final static String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static List<List<String>> getEventTeamNumbers(String eventId) throws IOException {
		List<List<String>> output = new Vector<List<String>>();
		try {
			JsonReader reader = createEventJsonReader(eventId);
			reader.beginArray();
			while (reader.hasNext()) {
				if (reader.peek().equals(JsonToken.BEGIN_OBJECT)) {
					EventParser parser = new EventParser(reader);
					Event event = parser.read();
					List<String> temp = new Vector<String>();
					for (Alliance a : event.alliances) {
						temp = new Vector<String>();
						StringBuilder b = new StringBuilder();
						for (String s : a.picks) {
							b.append(s);
						}
						temp.add(b.toString());
					}
					output.add(temp);
					
				}
				else {
					reader.skipValue();
				}
			}
			reader.endArray();
			
		} catch (MalformedURLException e) {
			System.err.println("Cannot create the url");
		} catch (UnknownHostException e) {
			System.err.println("Cannot connect to www.thebluealliance.com");
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		return output;
	}
	
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
	
	
	private static JsonReader createEventJsonReader(String eventId) throws IOException {
		try {
			URL url = new URL(EVENT_URL_BASE + eventId + "/matches" + URL_END);
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
	
}
