# Programmazione
Corso di programmazione dell'anno accademico 2021/22

## 11/11/2021
### Liste
1) Lista vuota:
```scheme
null
```

```scheme
(cons 15 null)
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
    )
   )
  )
 )
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
