;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 20211207_9) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss" "installed-teachpacks")) #f)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; LABORATORIO DI PROGRAMMAZIONE
;; a.a. 2021-22
;;
;; Problema 9
;; 7 / 10 Dicembre 2021
;;
;; Della Giustina Lorenzo
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; PARTE 1

; definisco l'alfabeto latino come una lista di indici e lettere
(define alL
  (map list
   (list 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19)
   (list #\A #\B #\C #\D #\E #\F #\G #\H #\I #\L #\M #\N #\O #\P #\Q #\R #\S #\T #\V #\X)))

; procedura che dati un carattere e un alfabeto (definito come sopra) in input ritorna l'indice del carattere nell'alfabeto
(define al->integer ; val: int
  (lambda (char al) ; char: carattere, al: lista di liste
    (if (char=? (cadar al) char)
        (caar al)
        (al->integer char (cdr al)))))

; procedura che dati un indice e un alfabeto (definito come sopra) in input ritorna il carattere associato all'indice nell'alfabeto
(define integer->al ; val: char
  (lambda (int al)  ; int: int, al: lista di liste
    (if (= (caar al) int)
        (cadar al)
        (integer->al int (cdr al)))))

; procedura che applica una funzione di crittazione a un messaggio
(define crittazione ; val: stringa
  (lambda (msg reg) ; msg: stringa, reg: procedura [lett --> lett]
    (if (string=? msg "")
        ""
        (string-append
         (string (reg (string-ref msg 0)))
         (crittazione (substring msg 1) reg)
         ))
    ))

; procedura con valori procedurali che, data una chiave, restituisce la corrispondente funzione di crittazione
(define regola-cesare ; val: procedura
  (lambda (rot)       ; rot: intero ∈ [0, 19]
    (lambda (x) (integer->al (remainder (+ rot (al->integer x alL)) 20) alL))
    ))



;; PARTE 2

; definisco l'operatore funzionale H tale che h (in questo caso add, mul e pow) = H(f,g)
(define H       ; val: procedura
  (lambda (f g) ; f,g: procedure
    (lambda (m n)
      (if (= n 0)
          (f m)
          (g m ((H f g) m (- n 1)))))))

; definisco i casi base
(define i     ; val: int
  (lambda (m) ; m: int
    m))
(define z     ; val: int
  (lambda (m) ; m: int
    0))
(define u     ; val: int
  (lambda (m) ; m: int
    1))

; definisco la funzione "succ" con due valori in input per poter definire add in modo ricorsivo
(define s2      ; val: int
  (lambda (u v) ; u,v: int
    (+ v 1)))
; definisco add = H(i, s2)
(define add ; val: int
  (H i s2))
; definisco mul = H(z, add)
(define mul ; val: int
  (H z add))
; definisco pow = H(u, mul)
(define pow ; val: int
  (H u mul))

; definisco la tetrazione = H(u, pow)
(define tetra
  (H u pow))