package src.main.java.objects;

public class Player extends Saveable {
    private Score score; private Tile current_tile; private Deck deck;
    private String name; private Boolean isAI;

    public Player(){
        super();
        this.score = new Score();
        this.current_tile = null;
        this.deck = new Deck();
    } 

    public Player(Score score){
        super();
        this.score = score;
        this.current_tile = null;
        this.deck = new Deck();
    } 

    public Player(Tile current_tile){
        super();
        this.score = new Score();
        this.current_tile = current_tile;
        this.deck = new Deck();
    } 

    public Player(String id, Score score, Tile current_tile, Deck deck){
        super(id);
        this.score = score;
        this.current_tile = current_tile;
        this.deck = deck;
    }

    public Deck get_deck() {
		return deck;
	}

    public int get_score(){
        return this.score.get_score();
    }

    public int update_score(int change){
        this.score.update(change);
        return get_score();
    }

    public Tile get_Tile(){
        return this.current_tile;
    }

    public void update_tile(Tile new_tile){
        this.current_tile = new_tile;
    }   
}