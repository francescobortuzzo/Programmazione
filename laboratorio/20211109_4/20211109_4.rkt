;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname _4) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; date due cifre BTR “incolonnate” e il relativo riporto BTR in entrata (caratteri), restituisce il riporto BTR in uscita (carattere) conseguente alla somma delle cifre
(define btr-carry                ; btr-carry: char
 (lambda (digit-1 digit-2 carry) ; digit-1, digit-2, carry: chars
  (cond
   (
    (or
     (char=? digit-1 digit-2 #\.)
     (char=? digit-1 carry #\.)
     (char=? digit-2 carry #\.)
     (and (char=? digit-1 #\-) (char=? digit-2 #\+))
     (and (char=? digit-1 #\+) (char=? digit-2 #\-))
     (and (char=? digit-1 #\-) (char=? carry #\+))
     (and (char=? digit-1 #\+) (char=? carry #\-))
     (and (char=? digit-2 #\-) (char=? carry #\+))
     (and (char=? digit-2 #\+) (char=? carry #\-))
     )
    #\.
    )
   (
    (or
     (char=? digit-1 digit-2 #\+)
     (char=? digit-1 carry #\+)
     (char=? digit-2 carry #\+)
     )
    #\+
    )
   (else #\-)
   )
  )
 )


; date due cifre BTR “incolonnate” e il relativo riporto BTR in entrata (caratteri), restituisce la cifra BTR corrispondente (carattere) della rappresentazione della somma
(define btr-digit-sum                    ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\+)
                        ((char=? c #\+)  ; - - +
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\+)
                        ((char=? c #\.)  ; - . .
                         #\-)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\+)
                        ((char=? c #\.)  ; . - .
                         #\-)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\+)
                        ((char=? c #\+)  ; . + +
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\+)
                        ((char=? c #\+)  ; + . +
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\+)
                        ((char=? c #\.)  ; + + .
                         #\-)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))


; data una rappresentazione BTR (stringa), restituisce la parte che precede l’ultima cifra (stringa) oppure la stringa vuota ("") se l’argomento è la stringa vuota
(define head      ; head: string
 (lambda (string) ; string: string
  (if (< (string-length string) 2)
      ""
      (substring string 0 (- (string-length string) 1))
   )
  )
 )


; data una rappresentazione BTR (stringa), restituisce la cifra meno significativa (carattere) oppure zero (#\.) se l’argomento è la stringa vuota
(define lsd
 (lambda (string)
  (cond
   ((string=? string "") #\.)
   ((< (string-length string) 2) (string-ref string 0))
   (else (string-ref string (- (string-length string) 1)))
   )
  )
 )


; data una rappresentazione BTR (stringa), restituisce la rappresentazione non vuota equivalente in cui le eventuali cifre zero (#\.) in testa, ininfluenti, sono rimosse
(define normalized-btr
 (lambda (string)
  (if (or (= (string-length string) 1) (char=? (string-ref string 0) #\+) (char=? (string-ref string 0) #\-))
      string
      (normalized-btr (substring string 1))
   )
  )
 )


; date le rappresentazioni BTR di due interi (stringhe) e il riporto in entrata (carattere), restituisce la rappresentazione BTR della somma inclusiva del riporto
(define btr-carry-sum        ; btr-carry-sum: string
 (lambda (int-1 int-2 carry) ; int-1, int-2, carry: string, string, char
  (normalized-btr
   (if (and (string=? int-1 "") (string=? int-2 ""))
       (string carry)
       (string-append
        (btr-carry-sum (head int-1) (head int-2) (btr-carry (lsd int-1) (lsd int-2) carry))
        (string (btr-digit-sum (lsd int-1) (lsd int-2) carry))
        )
    )
   )
  )
 )


; la procedura principale, il cui obiettivo è descritto nel pdf
(define btr-sum
 (lambda (int-1 int-2)
  (btr-carry-sum int-1 int-2 #\.)
  )
 )