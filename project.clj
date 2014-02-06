(defproject clj-cas-client "0.0.6"
  :description "Makes it possible to wrap a Cas Client middleware around Ring handlers"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/tools.logging "0.2.3"]
                 [org.jasig.cas.client/cas-client-core "3.2.1"]
                 [ring "1.2.1"]
                 [org.clojure/data.zip "0.1.1"]
                 [robert/hooke "1.3.0"]]
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]}}
  :aot [clj-cas-client.core])
