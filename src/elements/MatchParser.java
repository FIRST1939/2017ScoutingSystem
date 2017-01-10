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
			}
			
			else if (name.equals("key")) {
				output.key = reader.nextString();
			}
			else if (name.equals("time")) {
				output.time = reader.nextInt();
			}
			else if (name.equals("alliances")) {
				output.alliances = (Alliance[]) readAlliances(reader).toArray();
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}

	private List<Alliance> readAlliances(JsonReader reader) throws IOException {
		List<Alliance> output = new Vector<Alliance>();
		reader.beginArray();
		while (reader.hasNext()) {
			if (reader.peek().equals(JsonToken.BEGIN_OBJECT)) {
				output.add(readAlliance(reader));
			}
			else {
				reader.skipValue();
			}
		}
		reader.endArray();
		return output;
	}

	private Alliance readAlliance(JsonReader reader) throws IOException {
		Alliance output = new Alliance();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("blue")) {
				output.teams[0] = readStringArray(reader).toString().replaceAll("[", "").replaceAll("]", "");
			}
			else if (name.equals("red")) {
				output.teams[1] = readStringArray(reader).toString().replaceAll("[", "").replaceAll("]", "");
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}
	
	private List<String> readStringArray(JsonReader reader) throws IOException {
		List<String> output = new Vector<String>();
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
