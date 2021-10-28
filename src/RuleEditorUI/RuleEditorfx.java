import java.util.*;

abstract class RuleEditor {
    Rule rule;
    LinkedList<Rule> rules; 

    abstract void save();
    abstract void cancel();
    abstract void delete();
    abstract void setType(String type)
}
