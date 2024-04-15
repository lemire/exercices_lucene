
Prenez note que la lemmatisation française par défault avec Lucène est limitée en qualité.

Windows :

```
❯ javac -cp "*" *.java   
❯ java -cp .;"*" ajout1        
Index créé.
❯ java -cp .;"*" ajout_stem_eng
Index créé.
> java -cp .;"*" recherche_eng 
// tapez "swim"
// tapez "chanterai"
❯ java -cp .;"*" ajout_stem_frs
Index créé.
❯ java -cp .;"*" recherche_frs 
// tapez "swim"
// tapez "chanterai"
```

Autres systèmes :

```
❯ javac -cp "*" *.java   
❯ java -cp .:"*" ajout1        
Index créé.
❯ java -cp .:"*" ajout_stem_eng
Index créé.
> java -cp .:"*" recherche_eng 
// tapez "swim"
// tapez "chanterai"
❯ java -cp .:"*" ajout_stem_frs
Index créé.
❯ java -cp .:"*" recherche_frs 
// tapez "swim"
// tapez "chanterai"
```
