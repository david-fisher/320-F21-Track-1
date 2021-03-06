package Objects;

import java.lang.reflect.Type;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

// Do not touch, helper for saving
public class TokenDeserializer implements JsonDeserializer<Token> {

	@Override
	public Token deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject j = json.getAsJsonObject();

		String id = context.deserialize(j.get("ID"), String.class);
		Board board = context.deserialize(j.get("board"), Board.class);
		ArrayList<Player> players = context.deserialize(j.get("players"), new TypeToken<List<Player>>() {
		}.getType());

		Token token = new Token(id, players, board);
		for (Player player : token.get_players()) {
			player.updateTile(board.tile_findByID(player.getTile().get_id()));
		}

		return token;
	}
}