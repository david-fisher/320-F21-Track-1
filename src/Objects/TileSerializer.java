package Objects;

import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.*;

public class TileSerializer implements JsonSerializer<Tile> {

	@Override
	public JsonElement serialize(Tile tile, Type type, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		
		JsonArray neighbors = new JsonArray();
		for (Tile neighbor : tile.get_neighbors()) {
			neighbors.add(neighbor.get_id());
		}
		
		JsonObject attributes = new JsonObject();
		for (Map.Entry<String, String> entry : tile.get_attributes().entrySet()) {
			String key = entry.getKey();
			String val = entry.getValue();
			
			attributes.addProperty(key, val);
		}
		
		obj.add("neighbors", neighbors);
		obj.add("deck", context.serialize(tile.get_deck()));
		obj.add("x", context.serialize(tile.get_x()));
		obj.add("y", context.serialize(tile.get_y()));
		obj.add("attributes", attributes);
		obj.add("rules", context.serialize(tile.get_rules()));
		obj.add("ID", context.serialize(tile.get_id()));
		return obj;
	}
}