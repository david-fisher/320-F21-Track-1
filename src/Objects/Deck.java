package src.main.java.objects;

import java.util.*;

public class Deck extends Saveable{
    /*
    parem: pieces - an array list of pieces (including cards)
    */
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

    // add new piece into deck
    public ArrayList<Piece> update_piece(Piece new_piece){
        try {
            int index = this.pieces.indexOf(piece_findByID(new_piece.get_id()));
		    this.pieces.set(index, new_piece);
        } catch (Exception e) {
            this.pieces.add(new_piece);
        }
        return this.pieces;
    }

    // remove a piece from deck, takes the piece that needs removal of as parem
    public ArrayList<Piece> remove_piece(Piece input){
        try {
		    this.pieces.remove(this.pieces.indexOf(piece_findByID(input.get_id())));
        } catch (Exception e) {
        	System.out.println("Error: Unable to remove target piece:");
            System.out.println(e);
        }		
        return this.pieces;
    }

    // add a list of pieces into the deck, takes an array list of pieces that need to be added
    public ArrayList<Piece> update_pieces(ArrayList<Piece> new_pieces){
        for (Piece new_piece: new_pieces){
            update_piece(new_piece);
        }
        return this.pieces;
    }

    // clear all pieces from deck
    public ArrayList<Piece> remove_pieces(){
        this.pieces.clear();
        return this.pieces;
    }

    // find a piece in the deck by ID, return null if not found
    public Piece piece_findByID(String ID) {
	    return this.pieces.stream().filter(piece -> piece.get_id().equals(ID)).findFirst().orElse(null);
	}
}