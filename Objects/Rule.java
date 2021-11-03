import java.util.*;

public class Rule implements IRule{
    final String ID;

    public Rule(){
        this.ID = UUID.randomUUID().toString();
    }

    public Rule(String id){
        this.ID = id;
    }
}
