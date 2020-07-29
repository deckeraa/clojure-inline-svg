(ns clojure-inline-svgs.strong-anchor-simple)

(defn bathroom-scale [style color size]
  [:div style
   [:svg {:xmlns "http://www.w3.org/2000/svg" :width size :height size :viewBox "0 0 8 8" :fill color :stroke color}
    [:g {:transform "translate(0 -276.88)" :fill "none" :stroke-linecap "round" :stroke-linejoin "round"}
     [:path {:d "m1.2814 277.32h5.4767c0.4644 0 0.83826 0.37537 0.83826 0.8416v5.4986c0 0.46628-0.37387 0.84165-0.83826 0.84165h-5.4767c-0.4644 0-0.83826-0.37537-0.83826-0.84165v-5.4986c0-0.46623 0.37387-0.8416 0.83826-0.8416z" :stroke-width ".55474"}]
     [:path {:d "m2.6342 278.14h2.7711c0.54666 0 0.98676 0.44187 0.98676 0.99073v0.50549c0 0.54888-0.44009 0.99072-0.98676 0.99072h-2.7711c-0.54666 0-0.98676-0.44186-0.98676-0.99072v-0.50549c0-0.54886 0.44009-0.99073 0.98676-0.99073z" :stroke-width ".299"}]
     [:path {:d "m1.7437 280.01a2.7745 2.7856 0 0 1 2.2595-1.2246 2.7745 2.7856 0 0 1 2.2926 1.161" :stroke-width ".22065"}]]
    ]])
