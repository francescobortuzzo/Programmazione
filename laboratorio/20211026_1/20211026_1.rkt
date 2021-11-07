;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 20211026_1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
; funzione per determinare il genere (#true se maschile, #false se femminile) di un nome ("nome")
(define maschile? ; maschile?: boolean
  (lambda (nome)  ; nome: string
    (let ( (char-finale (string-ref nome (- (string-length nome) 1))) )
      (or
       (char=? char-finale #\o)
       (char=? char-finale #\i)
       )
      )
    )
  )

; funzione per determinare il numero (#true se singolare, #false se plurale) di un nome ("nome")
(define singolare? ; singolare?: boolean
  (lambda (nome)   ; nome: string
    (let ( (char-finale (string-ref nome (- (string-length nome) 1))) )
      (or
       (char=? char-finale #\o)
       (char=? char-finale #\a)
       )
      )
    )
  )

; funzione per determinare l'articolo che precede un nome ("nome")
(define nome->articolo ; nome->articolo: string
  (lambda (nome)       ; nome: string
    (cond
      ((and (maschile? nome) (singolare? nome)) "il")
      ((maschile? nome) "i")
      ((singolare? nome) "la")
      (else "le")
      )
    )
  )

; funzione per coniugare un verbo ("infinito") riferito a un nome (con numero "singolare?")
(define coniugazione            ; coniugazione: string
  (lambda (infinito singolare?) ; infinito, singolare: string, boolean
    (let (
          (suffisso (substring infinito (- (string-length infinito) 3)))
          (prefisso (substring infinito 0 (- (string-length infinito) 3)))
          )
      (cond
        ((string=? suffisso "are")
         (if singolare?
             (string-append prefisso "a")
             (string-append prefisso "ano")
          )
         )
        ((string=? suffisso "ere")
         (if singolare?
             (string-append prefisso "e")
             (string-append prefisso "ono")
          )
         )
        (else
         (if singolare?
             (string-append prefisso "e")
             (string-append prefisso "ono")
          )
         )
        )
      )
    )
  )

; funzione che crea una frase a partire dal soggetto ("sogg"), dal predicato ("pred") e dal oggetto ("ogg")
(define frase             ; frase: string
  (lambda (sogg pred ogg) ; sogg, pred, ogg: strings
    (string-append
     (nome->articolo sogg)
     " "
     sogg
     " "
     (coniugazione pred (singolare? sogg))
     " "
     (nome->articolo ogg)
     " "
     ogg
     )
    )
  )