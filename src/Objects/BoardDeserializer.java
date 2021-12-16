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
		ArrayList<Tile> tiles = context.deserialize(j.get("tiles"), new TypeToken<List<Tile>>() {
		}.getType());
		ArrayList<Rule> rules = context.deserialize(j.get("rules"), new TypeToken<List<Rule>>() {
		}.getType());
		Deck deck = context.deserialize(j.get("deck"), Deck.class);
		Tile startTile = context.deserialize(j.get("startTile"), Tile.class);
		int winCondition = context.deserialize(j.get("winCondition"), int.class);
		int dimensionX = context.deserialize(j.get("dimensionX"), int.class);;
		int dimensionY = context.deserialize(j.get("dimensionY"), int.class);;
		
		
		Board board = new Board(id, tiles, rules, deck);
		for (Tile tile : board.get_tiles()) {
			ArrayList<Tile> neighbors = new ArrayList<>();
			for (int i = 0; i < tile.get_neighbors().size(); i++) {
				neighbors.add(board.tile_findByID(String.valueOf(tile.get_neighbors().get(i))));
			}
			tile.remove_neighbors();
			tile.update_neighbors(neighbors);
		}		
		
		board.setStartTile(board.tile_findByID(String.valueOf(startTile.get_id())));
		board.setWinCondition(winCondition);
		board.updateDimensions(dimensionX, dimensionY);
		
		return board;
	}
}