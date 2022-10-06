# Programmazione
Corso di programmazione dell'anno accademico 2021/22

## 11/11/2021
### Liste
#### Lista vuota
```scheme
null
'()
```

#### Cons
cons serve per aggiugnere un elemento a una lista:
```scheme
(cons 15 null) ; aggiunge il numero 15 a una lista vuota
```

#### Null?
null? serve per sapere se una lista contiene degli elementi:
```scheme
(null? null) ; #true
(null? (cons 15 null)) ; #false
```

#### Car
car ritorna il primo elemento di una lista:
```scheme
(car (cons 15 null)) ; 15
```

#### Cdr
cdr ritorna la lista senza il primo elemento:
```scheme
(cdr (cons 15 null)) ; '()
```

#### Quote
Differenza list e ':
```scheme
'(+ 1 2) ; (list '+ 1 2)
list(+ 1 2) ; (list 3)
```

#### List-ref
```scheme
(define list-pos ; val: T
 (lambda (s i)   ; s: lista di T, i: intero
  (cond
   (
    (= i 0)
    (car s)
    )
   (
    else
    (list-pos (cdr s) (- i 1))
    ))))
```

#### Length
```scheme
(define lunghezza
 (lambda (list)
  (if (null? list)
      0
      (+ 1 (list-length (cdr list)))
   )
  )
 )
```

#### Append
```scheme
(define giustapponi ; val: lista
 (lambda (s t)      ; s, t: liste
  (cond
   ((null? s) t)
   ((null? t) s)
   (else (cons (car s) (giustapponi (cdr s) t)))
   )
  )
 )
```

#### Reverse
Versione meno efficiente:
```scheme
(define rovescia ; val: lista
 (lambda (s)     ; s: lista
  (if (null? s)
      null
      (append (rovescia (cdr s)) (list (car s)))
   )
  )
 )
 ```
Versione più efficiente:
```scheme
(define rovescia ; val: lista
 (lambda (s)     ; s: lista
  (rovescia-rec s null)
  )
 )

(define rovescia-rec ; val: lista
 (lambda (s r)         ; s: lista
  (if (null? s)
      r
      (rovescia-rec (cdr s) (cons (car s) r))
   )
  )
 )
 ```

## 18/11/2021
### Ricorsione ad albero
#### Moltiplicazione
```scheme
(define mul
 (lambda (m n)
  (cond
   ((= n 0) 0)
   ((even? n) (mul (* 2 m) (quotient n 2)))
   (else (+ m (mul (* 2 m) (quotient n 2))))
   )
  )
 )
```
### Ricorsione a coda
#### Moltiplicazione
```scheme
(define mul-tr   ; val: intero
 (lambda (m n p) ; m, n, p: interi non negativi
  (cond
   ((= n 0) p)
   ((even? n) (mul-tr (* 2 m) (quotient n 2) p))
   (else      (mul-tr (* 2 m) (quotient n 2) (+ m p)))
   )
  )
 )

(define mul2
 (lambda (m n)
  (mul-tr m n 0)
  )
 )
```
#### MCD
```scheme
(define mcd
 (lambda (x y)
  (cond
   ((= x y) x)
   ((< x y) (mcd x (- y x)))
   (else (mcd (- x y) y))
   )
  )
 )
 ```
