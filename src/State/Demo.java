package State;

import Objects.*;

import java.lang.reflect.Array;
import java.util.*;

import javafx.scene.image.ImageView;

public class Demo {
    public Demo() {
    }

    public Board getBoard () {
        /*
        *   [t1]  -> [t2] -> [t3] ->  [t4]
        *     ^        |        ^       v
        *   [t14]      |        |      [t5] 
        *     ^        |        |      v
        *   [t13]      |        |     [t6] 
        *     ^        |        |      v
        *   [t12]      |        |      [t7] 
        *     ^        v        |       v
        *   [t11] <- [t10] <- [t9] <- [t8]            
        */ 
        
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        // ADD TILES TO BOARD  |
        //                     |
        //                     |
        //                     V

        // t1
        Tile t1 = new Tile(0, 0, null, null, 0);

        // t2
        ArrayList<Rule> t2Rule = new ArrayList<Rule>();
        t2Rule.add(new DrawCardRule(2));
        Tile t2 = new Tile(0, 1, t2Rule, null, 0);

        // t3
        Tile t3 = new Tile(0, 2, null, null, 5);

        // t4
        ArrayList<Rule> t4Rule = new ArrayList<Rule>();
        t4Rule.add(new DrawCardRule(1));
        Tile t4 = new Tile(0, 3, t4Rule, null, 0);

        // t5
        Tile t5 = new Tile(1, 3, null, null, -5);

        // t6
        Tile t6 = new Tile(2, 3, null, null, -10);

        // t7
        ArrayList<Rule> t7Rule = new ArrayList<Rule>();
        t7Rule.add(new MoveRule(new RNG(new int[] {0, 3})));
        Tile t7 = new Tile(3, 3, t7Rule, null, 7);

        // t8
        Tile t8 = new Tile(4, 3, null, null, 0);

        // t9
        Tile t9 = new Tile(4, 2, null, null, 20);

        // t10
        Tile t10 = new Tile(4, 1, null, null, -1);

        // t11
        ArrayList<Rule> t10Rule = new ArrayList<Rule>();
        t10Rule.add(new DrawCardRule(1));
        Tile t11 = new Tile(4, 0, t10Rule, null, -1);

        // t12
        Tile t12 = new Tile(3, 0, null, null, 0);

        // t13
        Tile t13 = new Tile(2, 0, null, null, new int[] {0, 15});

        // t14
        Tile t14 = new Tile(1, 0, null, null, 0);

        t1.update_neighbor(t2);
        t2.update_neighbor(t3);
        t2.update_neighbor(t10);
        t3.update_neighbor(t4);
        t4.update_neighbor(t5);
        t5.update_neighbor(t6);
        t6.update_neighbor(t7);
        t7.update_neighbor(t8);
        t8.update_neighbor(t9);
        t9.update_neighbor(t10);
        t9.update_neighbor(t3);
        t10.update_neighbor(t11);
        t11.update_neighbor(t12);
        t12.update_neighbor(t13);
        t13.update_neighbor(t14);
        t14.update_neighbor(t1);

        tiles.add(t1);
        tiles.add(t2);
        tiles.add(t3);
        tiles.add(t4);
        tiles.add(t5);
        tiles.add(t6);
        tiles.add(t7);
        tiles.add(t8);
        tiles.add(t9);
        tiles.add(t10);
        tiles.add(t11);
        tiles.add(t12);
        tiles.add(t13);
        tiles.add(t14);

        ArrayList<Rule> turnRules = new ArrayList<Rule>();
        turnRules.add(new MoveRule(new RNG(new int[] {1, 3})));
        turnRules.add(new PlayCardRule());
                
        Deck deck = new Deck();

        
        // ADD CARDS TO DECK  |
        //                     |
        //                     |
        //                     V

        // Add card that moves you to the starting tile
        deck.addCard(new Card(new ArrayList<Rule>(Arrays.asList(new MoveRule(t1))), new ImageView()));

        // Add card that moves you 4 tiles
        deck.addCard(new Card(new ArrayList<Rule>(Arrays.asList(new MoveRule(new RNG(4)))), new ImageView()));

        // Add card that moves you 2 tiles
        deck.addCard(new Card(new ArrayList<Rule>(Arrays.asList(new MoveRule(new RNG(2)))), new ImageView()));

        // Add card that moves you 5-7 tiles
        deck.addCard(new Card(new ArrayList<Rule>(Arrays.asList(new MoveRule(new RNG(new int[] { 5, 7 })))), new ImageView()));

        // Add card that moves you 2-5 tiles
        deck.addCard(new Card(new ArrayList<Rule>(Arrays.asList(new MoveRule(new RNG(new int[] { 2, 5 })))), new ImageView()));

        // Add card that gives you 5 points
        deck.addCard(new Card(5, new ImageView()));

        // Add card that gives you 10 points
        deck.addCard(new Card(10, new ImageView()));

        // Add card that gives you 15 points
        deck.addCard(new Card(15, new ImageView()));

        // Add card that gives you 50 points
        deck.addCard(new Card(50, new ImageView()));

        // Add card that gives you 2 more cards but subtracts 10 points
        deck.addCard(new Card(new ArrayList<Rule>(Arrays.asList(new DrawCardRule(2))), -10, new ImageView()));

        Board b = new Board(tiles, turnRules, deck);
        b.setStartTile(t1);
        return b;
    }

    public static void main(String[] args) {
        Board board = new Demo().getBoard();
        board.setWinCondition(50);
        
        ArrayList<Player> players = new ArrayList<Player>();

        Hashtable<String, String> player1Attr = new Hashtable<String, String>();
        player1Attr.put("name", "Player 1");
        Hashtable<String, String> aiPlayerAttr = new Hashtable<String, String>();
        aiPlayerAttr.put("name", "Bot");
        
        players.add(new Player(board.getStartTile(), player1Attr));
        players.add(new AIPlayer(board.getStartTile(), aiPlayerAttr));

        GameState gs = new GameState(players, board);
        
        List<Choice> choices = gs.progressGame();
        Scanner input = new Scanner(System.in);

        while (!(choices.get(0) instanceof WinChoice)) {
        
            System.out.println("Choices: ");
            for (int i = 0; i < choices.size(); i++) {
                System.out.println(i + ": " + choices.get(i).toString());
            }
            
            System.out.println("Player: " + gs.getCurPlayer().getAttributes().get("name"));
            System.out.println("Current Tile: " + gs.getCurPlayer().getTile());
            System.out.println("Total Points: " + gs.getCurPlayer().getScore());

            int option = input.nextInt();
            
            choices = gs.progressGame(option);
        }
        input.close();
    }

}

