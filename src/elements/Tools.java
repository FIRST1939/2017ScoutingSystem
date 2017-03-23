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

import com.adithyasairam.tba4j.models.Match;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class Tools {
	
	private static final String EVENT_URL_BASE = "https://www.thebluealliance.com/api/v2/event/";
	private static final String MATCH_URL_BASE = "https://www.thebluealliance.com/api/v2/match/";
	private static final String URL_END = "?X-TBA-App-Id=frc1939:ScoutingSystem:v2";
	
	public static MatchList getMatches(String eventId) throws IOException {	
		List<Match> matches = new Vector<Match>();
		JsonReader reader = createEventJsonReader(eventId);
		reader.beginArray();
		while (reader.hasNext()) {
			if (reader.peek().equals(JsonToken.BEGIN_OBJECT)) {
				matches.add(new MatchParser(reader).read());
			} else {
				reader.skipValue();
			}
		}
		reader.endArray();
		return new MatchList(matches);
	}
	
	public static Match getMatch(String matchId) throws IOException {
		return new MatchParser(createMatchJsonReader(matchId)).read();
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
	
}
