/^```clojure$/{incode=1; print ""; next}
/^```$/{if(incode == 1) {incode=0}; print""; next}
{if(incode == 1){print $0}
    else {print ";; " $0}}
