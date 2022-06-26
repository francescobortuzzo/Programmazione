
/**
 * 
 * LABORATORIO DI PROGRAMMAZIONE
 * a.a. 2021-22
 * 
 * Esercitazione sulla codifica in Java
 * 31/03/2022
 * 
 * Della Giustina Lorenzo
 * 
 */
public class Esercitazione
{
    /**
     * PARTE I
     * 
     * Dato un intero non negativo in notazione ternaria bilanciata (stringa di cifre –/./+), la seguente procedura in Scheme
     * restituisce la rappresentazione dell’intero successivo, calcolata operando direttamente sulle cifre a livello testuale:
     * 
     * (define btr-succ                            ; val: stringa di -/./+
     *  (lambda (btr)                              ; btr: stringa di -/./+
     *   (let ((n (string-length btr)))            ; (brt = "." oppure inizia con "+")
     *    (let ((lsb (string-ref btr (- n 1))))
     *     (if (= n 1)
     *         (if (char=? lsb #\+)
     *             "+-"
     *             "+")
     *         (let ((pre (substring btr 0 (- n 1))))
     *          (if (char=? lsb #\+)
     *              (string-append (btr-succ pre) "-")
     *              (string-append pre (if (char=? lsb #\-) "." "+"))
     *              ))
     *         )))
     *   ))
     *   
     * Traduci in Java la procedura btr-succ e verifica sperimentalmente che i risultati siano consistenti con quelli ottenuti
     * applicando la procedura riportata sopra nell’ambiente DrRacket.
     */
    public static String btrSucc(String btr) {
        int n = btr.length();
        char lsb = btr.charAt(n - 1);
        if (n == 1) {
            if (lsb == '+') {
                return "+-";
            } else {
                return "+";
            }
        } else {
            String pre = btr.substring(0, n - 1);
            if (lsb == '+') {
                return btrSucc(pre) + "-";
            } else {
                return pre + ((lsb == '-') ? "." : "+");
            }
        }
    }

    /**
     * PARTE II
     * 
     * Considera il programma in Scheme per realizzare la procedura ones-complement che, data una stringa di bit,
     * restituisce la rappresentazione del corrispondente complemento a uno, in cui le cifre 0 e 1 sono “scambiate” fra loro
     * (sorgenti Scheme: recursion_strings.scm di cui è riportato il link anche a fianco di questo testo).
     * Prova a trasformare il programma per realizzarne una versione imperativa in Java, che non si avvalga della ricorsione
     * ma applichi il costrutto for per l’iterazione.
     * Sperimenta infine il programma nell’ambiente BlueJ oppure DrJava e verificane i risultati.
     * 
     * (define bit-complement   ; val: stringa
     *  (lambda (bit)          ; bit: stringa
     *   (if (string=? bit "0")
     *       "1"
     *       "0"
     *   )))
     * 
     * (define ones-complement  ; val: stringa di 0/1
     *  (lambda (bin)          ; bin: stringa di 0/1
     *   (if (string=? bin "")
     *       ""
     *       (string-append
     *        (ones-complement (substring bin 0 (- (string-length bin) 1)))
     *        (bit-complement (substring bin (- (string-length bin) 1)))
     *        ))))
     */

    public static String bitComplement(String bit) {
        return "0".equals(bit) ? "1" : "0";
    }

    public static String onesComplement(String bin) {
        String r = "";
        int s = 0;
        for (int i=0; i<bin.length(); i++) {    
            r = r + bitComplement(bin.substring(i, i+1));
        }
        return r;
    }

}