# clojure-inline-svgs

Live demo to browse the icons: [https://stronganchortech.com/inline-svg-icons-for-clojurescript/](https://stronganchortech.com/inline-svg-icons-for-clojurescript/)

A curated set of inline svgs for your ClojureScript/Reagent app, licensed under the [MIT License](http://opensource.org/licenses/MIT).

Why generate svgs inline in code instead of just including an svg file?

- Convenience: just add the dependency and you're ready to go. No copying & pasting files.
- Size: use ClojureScript's dead-code optimizations to only ship the svg data that your app actually uses.
- Flexibliity: having hiccup for the svgs in code gives you full control over color, animations, etc.

In your project.clj's dependencies:
```
      [com.stronganchortech/clojure-inline-svgs "1.0.0"]
```     
To use:
```
      (ns your-cool-project.core
        (:require
         [clojure-inline-svgs.open-iconic :as open-iconic]
         [clojure-inline-svgs.strong-anchor-simple :as strong-anchor-simple]))

      (defn your-reagent-component []
        [:div
          [open-iconic/check 
            {:on-click (fn [e] (println "Icon clicked."))}
            "green"
            "24px"]
          [:p "There's an inline svg in this div!"]])
```  

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```
lein clean
lein cljsbuild once min
```
