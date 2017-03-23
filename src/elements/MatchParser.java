package elements;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import com.adithyasairam.tba4j.models.Match.Alliance;
import com.adithyasairam.tba4j.models.Match;

public class MatchParser extends Parser<Match> {

	public MatchParser(JsonReader reader) {
		super(reader);
	}

	@Override
	protected Match read() throws IOException {
		Match output = new Match();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("comp_level")) {
				output.comp_level = reader.nextString();
			} else if (name.equals("match_number")) {
				output.match_number = reader.nextInt();
			} else if (name.equals("set_number")) {
				output.set_number = reader.nextInt();
			} else if (name.equals("key")) {
				output.key = reader.nextString();
			}
			else if (name.equals("time")) {
				output.time = reader.nextInt();
			}
			else if (name.equals("alliances")) {
				List<Alliance> arr = readAlliances(reader);
				output.alliances = new Match.Alliance[arr.size()];
				output.alliances = arr.toArray(output.alliances);
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	
	private Vector<Alliance> readAlliances(JsonReader reader) throws IOException {
		Vector<Alliance> output = new Vector<Alliance>();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("blue") || name.equals("red")) {
				output.add(readAlliance(reader));
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}

	private Alliance readAlliance(JsonReader reader) throws IOException {
		Alliance output = new Alliance();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("teams")) {
				List<String> arr = readStringArray(reader);
				output.teams = new String[arr.size()];
				output.teams = arr.toArray(output.teams);
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	private Vector<String> readStringArray(JsonReader reader) throws IOException {
		Vector<String> output = new Vector<String>();
		reader.beginArray();
		while (reader.hasNext()) {
			if (reader.peek().equals(JsonToken.STRING)) {
				output.add(reader.nextString());
			}
			else {
				reader.skipValue();
			}
		}
		reader.endArray();
		return output;
	}

}
