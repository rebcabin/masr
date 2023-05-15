(defproject masr "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure              "1.11.1"]
                 [org.clojure/test.check           "1.1.1"]
                 [org.clojure/core.async           "1.6.673"]
                 [org.clojure/math.numeric-tower   "0.0.5"]
                 [com.github.blasterai/clj-fstring "1.1.2"]
                 [com.hyperfiddle/rcf              "20220926-202227"]
                 [camel-snake-kebab                "0.4.3"]
                 #_[org.clojure/data.zip             "1.0.0"]
                 ]
  :main ^:skip-aot masr.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})

; stable
; {:deps {com.hyperfiddle/rcf {:mvn/version "20220926-202227"}}}
