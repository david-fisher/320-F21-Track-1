package Objects;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TileDeserializer implements JsonDeserializer<Tile> {

	@Override
	public Tile deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
//		JsonObject j = json.getAsJsonObject();
//
//		String id = context.deserialize(j.get("ID"), String.class);
//		int x = context.deserialize(j.get("x"), int.class);
//		int y = context.deserialize(j.get("y"), int.class);
//		ArrayList<Rule> rules = context.deserialize(j.get("rules"), new TypeToken<List<Rule>>(){}.getType());
//		Hashtable<String, String> attributes = context.deserialize(j.get("attributes"), new TypeToken<Hashtable<String, String>>(){}.getType());
//		Hashtable<String, Tile> neighbors = context.deserialize(j.get("neighbors"), new TypeToken<Hashtable<String, String>>(){}.getType());
//		ArrayList<Piece> pieces = context.deserialize(j.get("pieces"), new TypeToken<List<Piece>>(){}.getType());
//
//		return new Tile(id, x, y, rules, attributes, neighbors, pieces);
		return null;
	}
}
