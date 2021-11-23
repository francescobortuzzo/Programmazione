;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 20211123_7) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
; Verifica se "x" è un elemento di "S"
(define belong? ; belong?: boolean
 (lambda (x S)  ; x: int ; S: ordered list
  (cond
   (
    (null? S)
    #false
    )
   (
    (= x (car S))
    #true
    )
   (
    else
    (belong? x (cdr S))
    )
   )
  )
 )

; Restituisce la posizione (indice) di "x" in "S"
(define position ; position: number
 (lambda (x S)   ; x: int ; S: ordered and without repetitions list
  (if (= x (car S))
      0
      (+ 1 (position x (cdr S)))
   )
  )
 )

; Restituisce la lista ordinata e senza ripetizioni che contine "x" e tutti gli elementi di "S".
(define sorted-ins ; sorted-ins: list
 (lambda (x S)     ; x: int ; S: ordered and without repetitions list
  (cond
   (
    (null? S)
    (cons x S)
    )
   (
    (= x (car S))
    S
    )
   (
    (< x (car S))
    (cons x S)
    )
   (
    else
    (cons (car S) (sorted-ins x (cdr S)))
    )
   )
  )
 )

; Restituisce la lista ordinata e senza ripetizioni che contine tutti e soli gli elementi di "S"
(define sorted-list ; sorted-list: list
 (lambda (list)     ; list: list
  (if (null? list)
      null
      (sorted-ins (car list) (sorted-list (cdr list)))
   )
  )
 )