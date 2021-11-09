;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 20211102_3) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; PARTE 1
; funzione che moltiplica un numero binario dato ("bin-rep") per un numero (del tipo 2^k) abbastanza grande da eliminare la parte decimale
(define delete-pdec ; delete-pdec: string
  (lambda (bin-rep) ; bin-rep: string
    (number->string
     (abs(*
      (string->number bin-rep)
      (expt 10 (- (string-length bin-rep) 1))
      ))
     )
    )
  )

; funzione che converte un numero binario intero ("bin") in un numero decimale
(define bin->dec ; bin->dec: number (int)
  (lambda (bin)  ; bin: string
    (let ( (bin-lenght (string-length bin)) )
      (if (> bin-lenght 1)
          (+
           (*(bin->dec (substring bin 0 (- bin-lenght 1))) 2)
           (string->number (substring bin (- bin-lenght 1)))
           )
          (string->number (substring bin (- bin-lenght 1)))
       )
      )
    )
  )

; funzione che converte un numero binario razionale ("bin") in un numero decimale
(define bin-rep->number ; bin-rep->number: number
  (lambda (bin) ; bin: string
    (if (> (string->number bin) 0)
        (/
         (bin->dec (delete-pdec bin))
         (expt
          2
          (- (string-length bin) 1)
          )
         )
        (*
         (/
          (bin->dec (delete-pdec bin))
          (expt
           2
           (- (string-length bin) 1)
           )
          )
         -1)
     )
    )
  )




; PARTE 2
; funzione che controlla la presenza del carattere "char" nella stringa "string"
(define is-there?       ; is-there?: boolean
  (lambda (string char) ; string, char: string, char
    (if (char=? (string-ref string 0) char)
        #true
        (if (> (string-length string) 1) (is-there? (substring string 1) char) #false)
     )
    )
  )

; funzione che cancella il carattere "char" nella stringa "string"
(define char-delete     ; char-delete: string
  (lambda (string char) ; string, char: string char
    (if (< (string-length string) 2)
        (if (char=? char (string-ref string 0))
            ""
            (substring string 0 1)
         )
        (string-append
         (if (char=? char (string-ref string 0))
             ""
             (substring string 0 1)
          )
         (char-delete (substring string 1) char)
         )
     )
   )
 )

; funzione che determina la posizione di un carattere "digit" in una stringa "string". La variabile in input "n" indica il numero da cui iniziare a contare (di norma 0).
(define digit-pos           ; digit-pos: int
  (lambda (symbols digit n) ; symbols, digit, n: string, char, int
    (if (char=? (string-ref symbols 0) digit)
        n
        (digit-pos (substring symbols 1) digit (+ n 1))
     )
    )
  )

; funzione che converte la rappresentazione di un numero intero "rep" (scritto con le cifre ordinate crescenti "symbols") in un numero decimale
(define int-rep->number ; int-rep->number: number
  (lambda (symbols rep) ; symbols, rep; strings
    (let (
          (int-digits (string-length rep))
          (base (string-length symbols))
          )
      (if (< int-digits 2)
          (digit-pos symbols (string-ref rep 0) 0)
          (+
           (*
            (digit-pos symbols (string-ref rep 0) 0)
            (expt base (- int-digits 1))
            )
           (int-rep->number symbols (substring rep 1))
           )
       )
     )
   )
 )

; funzione che converte la rappresentazione di un numero razionale non negativo "rep" (scritto con le cifre ordinate crescenti "symbols") in un numero decimale
(define +rep->number    ; int-rep->number: number
  (lambda (symbols rep) ; symbols, rep; strings
    (let (
          (int-digits (if (is-there? rep #\.) (digit-pos rep #\. 0) (string-length rep) ))
          (base (string-length symbols))
          )
      (/
       (int-rep->number symbols (char-delete rep #\.))
       (expt base (- (string-length (char-delete rep #\.)) int-digits))
       )
     )
   )
 )

; funzione che converte la rappresentazione di un numero razionale "rep" (scritto con le cifre ordinate crescenti "symbols") in un numero decimale
(define rep->number     ; int-rep->number: number
  (lambda (symbols rep) ; symbols, rep; strings
    (cond
      ((char=? (string-ref rep 0) #\-)
       (* (+rep->number symbols (substring rep 1)) -1)
       )
      ((char=? (string-ref rep 0) #\+)
       (+rep->number symbols (substring rep 1))
       )
      (else
       (+rep->number symbols rep)
       )
     )
   )
 )