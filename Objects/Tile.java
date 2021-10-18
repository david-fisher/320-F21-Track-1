package Objects;

public class Tile {
    int x,y; Rule[] rules; Tile[] next_tiles;
    public Tile(int x, int y, Rule[] rules, Tile[] next_tiles){
        this.x = x;
        this.y = y;
        this.rules = rules;
        this.next_tiles = next_tiles;
    }
}
