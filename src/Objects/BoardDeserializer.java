package Objects;

import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class BoardDeserializer implements JsonDeserializer<Board> {

	@Override
	public Board deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject j = json.getAsJsonObject();
		
		String id = context.deserialize(j.get("ID"), String.class);
		ArrayList<Tile> tiles = context.deserialize(j.get("tiles"), new TypeToken<List<Tile>>(){}.getType());
		ArrayList<Rule> rules = context.deserialize(j.get("rules"), new TypeToken<List<Rule>>(){}.getType());
		
		Board board = new Board(id, tiles, rules);
		for (Tile tile : board.get_tiles()) {
			for (Map.Entry<String, Tile> entry : tile.get_neighbors().entrySet()) {
				String key = entry.getKey();
				String val = String.valueOf(entry.getValue());
				
				Hashtable<String, Tile> neighbors = new Hashtable<>();
				neighbors.put(key, board.tile_findByID(val));
				tile.update_neighbors(neighbors);
			}
		}
		
		return board;
	}
}
