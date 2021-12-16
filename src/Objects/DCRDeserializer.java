package Objects;

import java.lang.reflect.Type;
import com.google.gson.*;

public class DCRDeserializer implements JsonDeserializer<DrawCardRule> {

	@Override
	public DrawCardRule deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject j = json.getAsJsonObject();
		
		String id = context.deserialize(j.get("ID"), String.class);
		int repeat = context.deserialize(j.get("repeat"), int.class);
		DrawCardRule rule = new DrawCardRule(id, repeat);
		
		System.out.println(rule.get_id());
		return rule;
	}

}
