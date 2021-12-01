package Objects;

import java.io.*;
import com.google.gson.*;

public class JSONConverter {
	private String fileLocation;
	private Token token;
	private GsonBuilder gsonBuilder;
	private Gson gson;
	
	public JSONConverter(Token token, String fileLocation) {
		this.fileLocation = fileLocation;
		this.token = token;
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Tile.class, new TileSerializer());
		gsonBuilder.registerTypeAdapter(Tile.class, new TileDeserializer());
		gsonBuilder.registerTypeAdapter(Board.class, new BoardDeserializer());
		gsonBuilder.registerTypeAdapter(Token.class, new TokenDeserializer());
		gson = gsonBuilder.setPrettyPrinting().create();
	}
	
	public void To_JSON() throws IOException {
		FileWriter fw = new FileWriter(fileLocation);
		gson.toJson(token, fw);
		fw.close();
	}
	
	public Token From_JSON() throws IOException {
		FileReader fr = new FileReader(fileLocation);
		BufferedReader br = new BufferedReader(fr);
		return gson.fromJson(br, Token.class);
	}
}