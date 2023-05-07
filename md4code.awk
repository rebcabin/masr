/^;; #\+begin_src clojure$/{printf("```clojure"); incode=1; next; next}
/^;; #\+end_src$/{print "```"; incode=0; next}
/^;;[ ]?$/{if(incode == 0) {printf("\n")}}
/^;; .+$/{if (incode == 0) {printf("%s\n", substr($0, 4, length($0)-2))}}
{if(incode == 1) {print $0}}
