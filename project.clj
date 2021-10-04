(defproject clj-ai-meetup "0.1.0-SNAPSHOT"
  :description "Case Studies in AI"
  :url "https://github.com/metosin/clj-ai-meetup"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :plugins [[nightlight/lein-nightlight "RELEASE"]]
  :source-paths ["src"]
  :target-path "target/%s/"
  :main ^:skip-aot gps.solver)
