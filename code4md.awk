/^```clojure$/{incode=1; print ";; #+begin_src clojure\n"; next}
/^```$/{if(incode == 1) {incode=0; print";; #+end_src\n"; next}}
{if(incode == 1){print $0}
    else {print ";; " $0}}
