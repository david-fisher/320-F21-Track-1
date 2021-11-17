package objects;

import java.util.*;

public class Deck extends Savable{
    ArrayList<Piece> pieces;

    public Deck(){
        super();
        this.pieces = new ArrayList<Piece>();
    }

    public Deck(ArrayList<Piece> pieces){
    	super();
        this.pieces = pieces;
    }
    public Deck(String id, ArrayList<Piece> pieces){
    	super(id);
        this.pieces = pieces;
    }

    
    public ArrayList<Piece> get_pieces(){
        return this.pieces;
    }

    public ArrayList<Piece> update_piece(Piece new_piece){
        try {
            int index = this.pieces.indexOf(piece_findByID(new_piece.get_id()));
		    this.pieces.set(index, new_piece);
        } catch (Exception e) {
            this.pieces.add(new_piece);
        }
        return this.pieces;
    }

    public ArrayList<Piece> remove_piece(Piece input){
        try {
            int index = this.pieces.indexOf(piece_findByID(input.get_id()));
		    this.pieces.remove(index);
        } catch (Exception e) {
            System.out.println(e);
        }		
        return this.pieces;
    }

    public ArrayList<Piece> update_pieces(ArrayList<Piece> new_pieces){
        for (Piece new_piece: new_pieces){
            update_piece(new_piece);
        }
        return this.pieces;
    }

    public ArrayList<Piece> remove_pieces(){
        this.pieces.clear();
        return this.pieces;
    }

    public Piece piece_findByID(String ID) {
	    return this.pieces.stream().filter(piece -> piece.get_id().equals(ID)).findFirst().orElse(null);
	}
}
