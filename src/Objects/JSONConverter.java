package Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Class to read and write into a JSON file
public class JSONConverter {
	private String fileLocation;
	private Token token;
	private GsonBuilder gsonBuilder;
	private Gson gson;

	public JSONConverter(Token token, String fileLocation) {
		this.fileLocation = fileLocation;
		this.token = token;
		gsonBuilder = new GsonBuilder();
		//gsonBuilder.registerTypeAdapter(Rule.class, new RuleDeserializer());
		//gsonBuilder.registerTypeAdapter(DrawCardRule.class, new DCRDeserializer());
		gsonBuilder.registerTypeAdapter(Tile.class, new TileSerializer());
		gsonBuilder.registerTypeAdapter(Tile.class, new TileDeserializer());
		gsonBuilder.registerTypeAdapter(Board.class, new BoardDeserializer());
		gsonBuilder.registerTypeAdapter(Token.class, new TokenDeserializer());
		gson = gsonBuilder.setPrettyPrinting().create();
	}

	// Writing into a JSON which will be stored in fileLocation
	public void To_JSON() throws IOException {
		FileWriter fw = new FileWriter(fileLocation);
		gson.toJson(token, fw);
		fw.close();
	}

	// Reading from a JSON at fileLocation
	public Token From_JSON() throws IOException {
		FileReader fr = new FileReader(fileLocation);
		BufferedReader br = new BufferedReader(fr);
		return gson.fromJson(br, Token.class);
	}
}
