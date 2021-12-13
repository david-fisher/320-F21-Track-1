package Objects;

import java.text.MessageFormat;
import java.util.*;

public class action {
    String ID; int repeat, step_count; ArrayList<Rule> Rules; RNG rng;

    public action(int repeat, ArrayList<Rule> Rules, RNG rng){
        this.ID = UUID.randomUUID().toString();
        this.repeat = repeat;
        this.step_count = 0;
        this.Rules = Rules;
        this.rng = rng;
    }

    public action(String id, int repeat, int step_count, ArrayList<Rule> Rules, RNG rng){
        this.ID = id;
        this.repeat = repeat;
        this.step_count = step_count;
        this.Rules = Rules;
        this.rng = rng;
    }

    public String print_action(){
        String msg = MessageFormat.format("Steps: {}, repeat: {}", this.step_count, this.repeat);
        System.out.println(msg);
        return msg;
    }

    public int[] do_action(){
        this.rng.replace_repeat(this.repeat);
        return this.rng.ran_int();
    }

    public void to_json(){}
}
