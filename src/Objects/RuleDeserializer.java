package Objects;

import java.lang.reflect.Type;
import com.google.gson.*;

public class RuleDeserializer implements JsonDeserializer<Rule> {

	@Override
	public Rule deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject j = json.getAsJsonObject();
		
		String id = context.deserialize(j.get("ID"), String.class);
		PlayCardRule rule = new PlayCardRule(id);
		
		System.out.println(rule.get_id());
		return rule;
	}

}
