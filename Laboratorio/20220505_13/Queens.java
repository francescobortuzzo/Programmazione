import queens.*;

public class Queens {

    public static int numberOfSolutions(int n) {

        return numberOfCompletions(new Board(n));
    }

    private static int numberOfCompletions( Board board ) {

        int n = board.size();
        int q = board.queensOn();
        int count = 0;

        if (q == n) {
            return 1;
        } else {

            int i = q + 1;

            for (int j = 1; j <= n; j++) {
                if (!board.underAttack(i,j)) {
                    count = count + numberOfCompletions(board.addQueen(i,j));
                }
            }
        }

        return count;
    }

    public static SList<Board> listOfSolutions(int n) {
        return listOfCompletions(new Board(n));
    }

    private static SList<Board> listOfCompletions( Board board ) {

        int n = board.size();
        int q = board.queensOn();

        if (q == n) {
            return (new SList<Board>()).cons(board);
        } else {

            SList<Board> list = new SList<Board>();
            int i = q + 1;

            for (int j = 1; j <= n; j++) {
                if (!board.underAttack(i,j)) {
                    list = list.append(listOfCompletions(board.addQueen(i,j)));
                }
            }
            return list;
        }
    }

    /**
     * 
     * LABORATORIO DI PROGRAMMAZIONE
     * a.a. 2021-22
     * 
     * Problema 13 parte 2
     * 5 Maggio 2022
     * 
     * Della Giustina Lorenzo
     * 
     * * * * * * * * * * * * * * * * * * * * * */
    /* 
     * Metodo statico che, data una lista di tipo SList<Board> relativa a scacchiere tutte della stessa dimensione,
     * visualizza in successione l’immagine di ciascuna scacchiera che fa parte della lista sulla GUI.
     *  
     */
    public static void boardView( SList<Board> LsB ) {
        queens.ChessboardView view = new queens.ChessboardView( LsB.car().size() );
        for ( SList<Board> k = LsB; !k.isNull(); k = k.cdr() ) {
            view.setQueens( k.car().toString() );
        }
    }
} // class queens