package Objects;

//Score class that can used to store and update a player's score
public class Score extends Savable {
    int cur_score;

    public Score() {
        super();
        this.cur_score = 0;
    }

    public Score(int score) {
        super();
        this.cur_score = score;
    }

    // This is done so that the score can be initialized based on the player's ID
    public Score(String id, int score) {
        super(id);
        this.cur_score = score;
    }

    public int get_score() {
        return this.cur_score;
    }

    // To update the score depending on the player's move
    public int update(int change) {
        this.cur_score = change;
        return this.cur_score;
    }
}
