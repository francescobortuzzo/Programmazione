;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname _5) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; SOLUZIONE NON RICORSIVA (a parte il fattoriale)
; funzione che calcola il fattoriale di "n"
(define fattoriale
 (lambda (n)
  (if (= n 0)
      1
      (* n (fattoriale (- n 1)))
   )
  )
 )

;funzione che restituisce il numero di percorsi diversi di lunghezza minima attraverso un reticolo tridimensionale "i" x "j" x "k"
(define manhattan-3d_non-ric
 (lambda (i j k)
  (/
   (fattoriale (+ i j k))
   (* (fattoriale i) (fattoriale j) (fattoriale k))
   )
  )
 )

; SOLUZIONE RICORSIVA
;funzione che restituisce il numero di percorsi diversi di lunghezza minima attraverso un reticolo tridimensionale "i" x "j" x "k"
(define manhattan-3d
  (lambda (i j k)
   (cond
    (
     (or (= 0 i j) (= 0 i k) (= 0 j k))
     1
     )
    (
     (= 0 i)
     (+ (manhattan-3d i (- j 1) k) (manhattan-3d i j (- k 1)))
     )
    (
     (= 0 j)
     (+ (manhattan-3d (- i 1) j k) (manhattan-3d i j (- k 1)))
     )
    (
     (= 0 k)
     (+ (manhattan-3d (- i 1) j k) (manhattan-3d i (- j 1) k))
     )
    (else
     (+ (manhattan-3d (- i 1) j k) (manhattan-3d i (- j 1) k) (manhattan-3d i j (- k 1)))
     )
    )
   )
 )