package Objects;

import java.lang.reflect.Type;
import com.google.gson.*;

public class RuleSerializer implements JsonSerializer<Rule> {

	@Override
	public JsonElement serialize(Rule rule, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject obj = new JsonObject();
		obj.add("ID", context.serialize(rule.get_id()));
		
		return null;
	}

}
