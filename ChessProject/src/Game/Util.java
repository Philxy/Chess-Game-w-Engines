package Game;

import Pieces.King;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Util {


    public static boolean isMate(GameState gs) {
        for (Piece p : gs.getAllPieces()) {
        }
        for(int r = 0; r<8; r++) {
            for(int c = 0; c<8; c++) {
                if(gs.getSq(r,c) != null && gs.getSq(r,c).legalMoves(gs).size() != 0) {
                    return false;
                }
            }
        }
        if (isCheck(gs, 'x')) {
            System.out.println("CHECKMATE");
        } else {
            System.out.println("STALEMATE");
        }
        return true;
    }

    /**
     * Returns a true if a square is being attacked by any piece.
     */
    public static boolean isAttacked(GameState gs, int[] attackedSq) {
        if (gs.getSq(attackedSq[0], attackedSq[1]) != null) {
            for (Piece p : gs.getAllPieces()) {
                if (p.getColor() == gs.getColor()) {
                    if (p.moveLegal(gs, p.getPos(), attackedSq)) {
                        return true;
                    }
                }
            }
        } else {
            GameState newGS = new GameState(gs.getBoard(), gs.blackNextMove(), gs.getAllPieces());
            newGS.setSq(null, attackedSq[0], attackedSq[1]);
            for (Piece p : gs.getAllPieces()) {
                if (p.moveLegal(gs, p.getPos(), attackedSq)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Returns true if the of a given color is in check
     * @param gs
     * @param color
     * @return
     */
    public static boolean isCheck(GameState gs, char color) {
        for(Piece p: gs.getAllPieces()) {
            if(p instanceof King && p.getColor() == color) {
                if(isAttacked(gs, p.getPos())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAttacked1(GameState gs, int[] attackingSquare, char attackingColor) {
        if(gs.getSq(attackingSquare[0],attackingSquare[1]) != null) {
            for (Piece p : gs.getAllPieces()) {
                    if(p.moveLegal(gs, p.getPos(), attackingSquare)) {
                        return true;
                    }
            }
        } else {
            for (Piece p : gs.getAllPieces()) {
                    if(p.getColor() == attackingColor) {
                        if(p.moveLegal(gs, p.getPos() , attackingSquare) && !Arrays.equals(attackingSquare, p.getPos())) {
                            return true;
                        }
                    }
            }
        }
        return false;
    }

    /**
     * Returns a list containing all the pieces attacking the king. Returns null if king isn't being attacked
     * @param gs
     * @return
     */
    public static boolean isCheck1(GameState gs, char color) {
        for(int r = 0; r<8;r++) {
            for(int c = 0; c<8;c++) {
                if(gs.getSq(r,c) instanceof King && gs.getSq(r,c).getColor() == color) {
                    if (isAttacked1(gs, new int[] {r,c}, 'x')) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Given a square of the chess board this method returns the formal name of the square using proper  chess notation
     * @param sq
     * @return
     */
    public static String sqName(int[] sq) {
        String s = "";
        switch (sq[1]) {
            case 0:
                s = "a";
                break;
            case 1:
                s = "b";
                break;
            case 2:
                s = "c";
                break;
            case 3:
                s = "d";
                break;
            case 4:
                s = "e";
                break;
            case 5:
                s = "f";
                break;
            case 6:
                s = "g";
                break;
            case 7:
                s = "h";
                break;
            default:
                s = "";
                break;
        }
        switch (sq[0]) {
            case 0:
                s = s + 8;
                break;
            case 1:
                s = s + 7;
                break;
            case 2:
                s = s + 6;
                break;
            case 3:
                s = s + 5;
                break;
            case 4:
                s = s + 4;
                break;
            case 5:
                s = s + 3;
                break;
            case 6:
                s = s + 2;
                break;
            case 7:
                s = s + 1;
                break;
            default:
                s = "";
                break;
        }
        return s;
    }








}