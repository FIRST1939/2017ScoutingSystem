package elements;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import com.adithyasairam.tba4j.models.Event;
import com.adithyasairam.tba4j.models.Event.Alliance;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class EventParser extends Parser<Event> {

	public EventParser(JsonReader reader) {
		super(reader);
	}

	@Override
	protected Event read() throws IOException {
		Event output = new Event();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("key")) {
				output.key = reader.nextString();
			}
			else if (name.equals("alliances")) {
				output.alliances = readAlliances(reader);
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}

	private Alliance[] readAlliances(JsonReader reader) throws IOException {
		Event.Alliance ally1 = new Event.Alliance();
		Event.Alliance ally2 = new Event.Alliance();
		String[] arr1 = null;
		String[] arr2 = null;
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("blue")) {
				reader.beginObject();
				arr1 = readTeam(reader);
				reader.endObject();
			}
			else if (name.equals("red")) {
				reader.beginObject();
				arr2 = readTeam(reader);
				reader.endObject();
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		String[] arr3 = new String[arr1.length + arr2.length];
		int i = 0;
		for (i = 0; i < arr1.length; i++) { 
			arr3[i] = arr1[i];
		}
		for (int k = i; k < arr2.length; k++) {
			arr3[k] = arr2[k];
		}
		ally1.picks = arr3;
		return new Alliance[] {ally1};
	}

	private String[] readStringArray(JsonReader reader) throws IOException {
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
		
		String[] arr = new String[output.size()];
		for (int i = 0; i < output.size(); i++) {
			arr[i] = output.get(i);
		}
		return arr;
	}
	
	private String[] readTeam(JsonReader reader) throws IOException {
		String[] output = null;		
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("teams")) {
				output = readStringArray(reader);
			}
			else {
				reader.skipValue();
			}
		}
		return output;
	}

}
