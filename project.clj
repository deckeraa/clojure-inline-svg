(defproject com.stronganchortech/clojure-inline-svgs "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.946"]
                 [javax.xml.bind/jaxb-api "2.2.11"] ;; this is to workaround a Java issue on my machine
                 [reagent "0.7.0"]]
  :description "Inline svgs for ClojureScript / Reagent / Hiccup"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}

  :min-lein-version "2.5.3"

  :source-paths ["src"]

  :plugins [[lein-cljsbuild "1.1.4"]]

  :clean-targets ^{:protect false} ["resources/public/js"
                                    "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies []

    :plugins      [[lein-figwheel "0.5.15"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/com/stronganchortech"]
     :figwheel     {:on-jsload "com.stronganchortech.clojure-inline-svgs/reload"}
     :compiler     {:main                 com.stronganchortech.clojure-inline-svgs
                    :optimizations        :none
                    :output-to            "resources/public/js/app.js"
                    :output-dir           "resources/public/js/dev"
                    :asset-path           "js/dev"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/com/stronganchortech"]
     :compiler     {:main            com.stronganchortech.clojure-inline-svgs
                    :optimizations   :simple
                    :output-to       "resources/public/js/app.js"
                    :output-dir      "resources/public/js/min"
                    :asset-path           "js/dev"
                    :elide-asserts   true
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    ]})
