;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 20211130_8) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "hanoi.ss" "installed-teachpacks")) #f)))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; LABORATORIO DI PROGRAMMAZIONE
;; a.a. 2021-22
;;
;; Problema 8
;; 30 Novembre 2021
;;
;; Della Giustina Lorenzo
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; La procedura hanoi-moves restituisce la lista di mosse che risolve il rompicapo della Torre di Hanoi per n dischi
(define hanoi-moves ; val: lista di coppie
  (lambda (n)       ; n > 0 intero
    (hanoi-rec n 1 2 3)
    ))

(define hanoi-rec   ; val: lista di coppie
  (lambda (n s d t) ; n intero, s, d, t: posizioni
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          ))
    ))

;; Restituisce la configurazione al termine della "k"-esima mossa
(define hanoi-disks ; hanoi-disks: list
 (lambda (n k)      ; n, k: int
  (hanoi-rec-cod n k '(1 0) '(2 0) '(3 0) n)
  )
 )

(define hanoi-rec-cod         ; hanoi-rec-cod: list
 (lambda (n k s d t n_backup) ; n, k, n_backup: int  ; s, d, t: lists
  (let
   (
    (l (expt 2 (- n 1)))
    (h (+ (cadr s) (cadr d) (cadr t)))
    )
    (cond
      ((= h n_backup) (list s d t))
      ((< k l) (hanoi-rec-cod (- n 1) k (list (car s) (+ (cadr s) 1)) t d n_backup))
      ((not (< k l)) (hanoi-rec-cod (- n 1) (- k l) t (list (car d) (+ (cadr d) 1)) s n_backup))
      )
   )
  )
 )

;; Restituisce un’immagine della disposizione dei dischi al termine della "k"-esima mossa
(define hanoi-picture ; hanoi-picture: picture
 (lambda (n k)        ; n, k: int
  (hanoi-picture-rec n k '(1 0) '(2 0) '(3 0) n (towers-background n))
  )
 )

(define hanoi-picture-rec             ; hanoi-picture-rec: picture
 (lambda (n k s d t n_backup picture) ; n, k, n_backup: int  ; s, d, t: lists  ; picture: picture
  (above
   (let
       (
        (l (expt 2 (- n 1)))
        (h (+ (cadr s) (cadr d) (cadr t)))
        )
     (cond
       ((= h n_backup) picture)
       ((< k l) (hanoi-picture-rec (- n 1) k (list (car s) (+ (cadr s) 1)) t d n_backup (disk-image n n_backup (car s) (cadr s))))
       ((not (< k l)) (hanoi-picture-rec (- n 1) (- k l) t (list (car d) (+ (cadr d) 1)) s n_backup (disk-image n n_backup (car d) (cadr d))))
       )
     )
   picture
   )
  )
 )