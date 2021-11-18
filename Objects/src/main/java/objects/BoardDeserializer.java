package objects;

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
		Deck deck = context.deserialize(j.get("deck"), Deck.class);
		
		Board board = new Board(id, tiles, rules, deck);
		for (Tile tile : board.get_tiles()) {
			ArrayList<Tile> neighbors = new ArrayList<>();
			for (int i = 0; i < tile.get_neighbors().size(); i++) {
				neighbors.add(board.tile_findByID(String.valueOf(tile.get_neighbors().get(i))));
			}
			tile.update_neighbors(neighbors);
		}
		
		return board;
	}
}