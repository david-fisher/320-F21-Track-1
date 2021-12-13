package Objects;

import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class TileDeserializer implements JsonDeserializer<Tile> {

	@Override
	public Tile deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject j = json.getAsJsonObject();
		
		String id = context.deserialize(j.get("ID"), String.class);
		int x = context.deserialize(j.get("x"), int.class);
		int y = context.deserialize(j.get("y"), int.class);
		ArrayList<Rule> rules = context.deserialize(j.get("rules"), new TypeToken<List<Rule>>(){}.getType());
		Hashtable<String, String> attributes = context.deserialize(j.get("attributes"), new TypeToken<Hashtable<String, String>>(){}.getType());
		ArrayList<Tile> neighbors = context.deserialize(j.get("neighbors"), new TypeToken<List<String>>(){}.getType());
		Deck deck = context.deserialize(j.get("deck"), Deck.class);
		
		return new Tile(id, x, y, rules, attributes, neighbors, deck);
	}
}