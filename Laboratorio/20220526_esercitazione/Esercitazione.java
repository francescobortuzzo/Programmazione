
/**
 * Aggiungi qui una descrizione della classe Esercitazione
 * 
 * @author (il tuo nome) 
 * @version (un numero di versione o una data)
 */
public class Esercitazione {
    public static int middleOfThree( int x, int y, int z ) {
        if ( x < y ) { // x y
            if ( y < z ) { // x y z
                return y;
            } else { // x z y || z x y
                return ( x < z ) ? z : x;
            }
        } else { // y x
            if ( x < z ) { // y x z
                return x;
            } else { // y z x || z y x
                return ( y < z ) ? z : y;
            }
        }
    }
    
    public static int lunchTime( int branch, int rest, int leaf ) {
        int count = 0;
        if ( rest < leaf ) {
            for ( int k = 0; k <= branch; k = k + leaf ) {
                if ( (k % rest) == 0 ) {
                    count++;
                }
            }
        } else {
            for ( int k = 0; k <= branch; k = k + rest ) {
                if ( (k % leaf) == 0 ) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static double[] closestPair( double[] arr ) {
        int len = arr.length;
        double min = Math.abs(arr[0] - arr[1]);
        double[] r = new double[] {arr[0], arr[1]};
        for ( int i = 0; (i < len-1) && (min != 0); i++ ) {
            for ( int j = i + 1; (j < len) && (min != 0); j++ ) {
                if ( Math.abs(arr[i] - arr[j]) < min) {
                    r = new double[] {arr[i], arr[j]};
                    min = Math.abs(arr[i] - arr[j]);
                }
            }
        }
        return r;
    }
    
    public static boolean symmetricalMatrix( double[][] arr ) {
        int len = arr.length;
        boolean r = true;
        for ( int i = 0; (i < len-1) && r; i++ ) {
            for ( int j = i + 1; (j < len) && r; j++ ) {
                r = arr[i][j] == arr[j][i];
            }
        }
        return r;
    }
}
