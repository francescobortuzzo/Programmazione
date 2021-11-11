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
