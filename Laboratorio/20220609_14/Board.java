import java.util.Stack;
import puzzleboard.PuzzleBoard;

/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Problema 14 - Il “rompicapo del 15”
 * 9 giugno 2022
 * 
 * Della Giustina Lorenzo
 * 
 * * * * * * * * * * * * * * * * * * * * * *
 * 
 * Protocollo:
 * new Board(int n)     : Board         costruttore per istanziare un modello della tavoletta
 * isOrdrd()            : boolean       metodo per verificare se i tasselli sono ordinati
 * canBeMoved(int i)    : boolean       metodo per verificare se un dato tassello può essere spostato
 * toString()           : String        metodo per mostrare in forma testuale (stringa) la configurazione
 * move(int i)          : void          metodo per spostare un dato tassello
 * 
 */
public class Board
{
    // variabili d'istanza - sostituisci l'esempio che segue con il tuo
    private int[][] boardConfig;
    private String config;

    /**
     * costruttore per istanziare un modello della tavoletta
     * 
     * @param  n   lato della tavoletta
     * @return     board nxn
     */
    public Board(int n) {
        boardConfig = new int[n*n][2];
        Stack<Integer> stack = new Stack<Integer>();
        String str = "";
        for(int i=0; i<n*n; i++) {
            stack.push(i);
        }

        for(int i=0; i<n*n; i++) {
            double range = n*n + 1 - i;
            int rand = (int) (range * Math.random());
            if(rand!=0) {
                int[] backup = new int[rand-1];
                for(int j=0; j<rand-1; j++) {
                    backup[j] = stack.pop();
                }
                int tmp = stack.pop();
                int row = ((int) i/n)+1;
                int col = (i%n)+1;
                boardConfig[tmp][0] = row;
                boardConfig[tmp][1] = col;
                str = str + tmp + " ";
                for(int j=rand-2; j>=0; j--) {
                    stack.push(backup[j]);
                }
            } else {
                int tmp = stack.pop();
                int row = ((int) i/n)+1;
                int col = (i%n)+1;
                boardConfig[tmp][0] = row;
                boardConfig[tmp][1] = col;
                str = str + tmp + " ";
            }
        }
        config = str.substring(0,str.length()-1);
    }

    /**
     * metodo per verificare se i tasselli sono ordinati
     * 
     * @return     booleano che indica se i tasselli sono ordinati
     */
    public boolean isOrdrd() {
        for(int i=1; i<(boardConfig.length-1); i++){
            if ((boardConfig[i][0]-1)*4+boardConfig[i][1] > (boardConfig[i+1][0]-1)*4+boardConfig[i+1][1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * metodo per verificare se un dato tassello può essere spostato
     * 
     * @param  i   tassello che si vuole spostare
     * @return     booleano che indica se il tassello può essere spostato
     */
    public boolean canBeMoved(int i) {
        if(boardConfig[i][0]==boardConfig[0][0]) {
            return boardConfig[i][1]==boardConfig[0][1]+1 || boardConfig[i][1]==boardConfig[0][1]-1;
        } else if(boardConfig[i][1]==boardConfig[0][1]) {
            return boardConfig[i][0]==boardConfig[0][0]+1 || boardConfig[i][0]==boardConfig[0][0]-1;
        }
        return false;
    }

    /**
     * metodo per mostrare in forma testuale (stringa) la configurazione
     * 
     * @return     stringa che rappresenta la configurazione
     */
    public String toString() {
        return config;
    }

    /**
     * metodo per spostare un dato tassello
     * 
     * @return     void
     */
    public void move(int i) {
        if(canBeMoved(i)) {
            if(boardConfig[i][0]==boardConfig[0][0]) { // stessa riga
                if(boardConfig[i][1]==boardConfig[0][1]+1) { // casella vuota a sinistra
                    boardConfig[0][1]++;
                    boardConfig[i][1]--;
                } else { // casella vuota a destra
                    boardConfig[0][1]--;
                    boardConfig[i][1]++;
                }
            } else if(boardConfig[i][1]==boardConfig[0][1]) { // stessa colonna
                if(boardConfig[i][0]==boardConfig[0][0]+1) { // casella vuota sopra
                    boardConfig[0][0]++;
                    boardConfig[i][0]--;
                } else { // casella vuota sotto
                    boardConfig[0][0]--;
                    boardConfig[i][0]++;
                }
            }
        }

        String str = "";
        int n = (int) Math.sqrt(boardConfig.length);
        for(int j=1; j<=n; j++) {
            for(int l=1; l<=n; l++) {
                int k=0;
                while(boardConfig[k][0]!=j || boardConfig[k][1]!=l) {
                    k++;
                }
                str = str + k + " ";
            }
        }
        config = str.substring(0,str.length()-1);
    }

    public void play() {
        int n = (int) Math.sqrt(boardConfig.length);
        PuzzleBoard gui = new PuzzleBoard( n );
        for(int i=0; i<boardConfig.length; i++) {
            gui.setNumber( boardConfig[i][0], boardConfig[i][1], i );
        }
        gui.display();
        while(!isOrdrd()) {
            int k = gui.get();
            if(canBeMoved(k)) {
                gui.clear(boardConfig[k][0], boardConfig[k][1]);
                gui.setNumber(boardConfig[0][0],boardConfig[0][1],k);
                move(k);
            }
            gui.display();
        }
    }
}
