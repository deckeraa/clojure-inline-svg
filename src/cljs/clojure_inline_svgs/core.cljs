(ns clojure-inline-svgs.core
  (:require
   [reagent.core :as reagent]
   [clojure-inline-svgs.open-iconic :as open-iconic]
   [clojure-inline-svgs.strong-anchor-simple :as strong-anchor-simple]
   ))

(defonce app-state
  (reagent/atom {:color "black"
                 :size  24
                 :icon-sets #{"open-iconic" "strong-anchor-simple"}}))

(defn icon-card [color-cursor size-cursor icon-fn display-name icon-set]
  (let [hovered? (reagent/atom false)
        copy-animation? (reagent/atom false)]
    (fn []
      [:div {:style {:display :flex
                     :align-items :center
                     :text-align :center
                     :flex-direction :column
                     :padding "20px 10px"
                     :min-width "150px"
                     :position :relative}
             :on-mouse-over #(reset! hovered? true)
             :on-mouse-out  #(reset! hovered? false)
             :on-click (fn [e]
                         (.writeText js/navigator.clipboard (str icon-set "/" display-name))
                         (reset! copy-animation? true)
                         (js/setTimeout #(reset! copy-animation? false) 2000) ;; 2000 matches the 2s specified for the animation in animations.css
                         )
             }
       [icon-fn {} @color-cursor (str @size-cursor "px")]
       [:p {:style (merge
                    {:margin "0px" :padding "0px"}
                    (if @hovered?
                      {:color "#000000ff"}
                      {:color "#00000000"}))} icon-set "/"]
       [:p {:style {:margin-top "0px"}} display-name]
       (when @copy-animation? [:div {:style {:position :absolute
                                             :top "60px"}
                                     :class "copy-fade-out"}
                               "Copied to clipboard"])])))

(defn is-valid-hex-string? [s]
  (or (re-matches #"[a-fA-F0-9]{6}" s)
      (re-matches #"[a-fA-F0-9]{8}" s)))

(defn color-picker [color-cursor]
  (let [hex-input (reagent/atom "")]
    (fn []
      [:div {:style {:display :flex
                     :flex-direction :row
                     :align-items :center}}
       (doall (map (fn [color]
                     ^{:key color}
                     [:div {:style
                            (merge {:width "50px" :height "50px"
                                    :background-color color
                                    :margin "5px 5px 5px 5px"}
                                   (when (= color @color-cursor)
                                     {:border "5px solid"}))
                            :on-click (fn [e]
                                        (reset! color-cursor color))}])
                   ["black" "red" "blue" "green" "orange"]))
       [:label {:for "hex-input"
                :style {:padding-left "10px"}} "#"]
       [:input {:type :text :value @hex-input
                :id "hex-input"
                :placeholder "00abff"
                :on-change (fn [e]
                             (let [v (-> e .-target .-value)]
                               (reset! hex-input v)
                               (when (is-valid-hex-string? v)
                                 (reset! color-cursor (str "#" v)))))}]])))

(defn size-picker [size-cursor]
  [:div {:style {:display :flex
                 :align-items :center}}
   [:input {:type :range
            :id "size-picker"
            :min "8" :max "192"
            :value @size-cursor
            :on-change (fn [e]
                         (reset! size-cursor (-> e .-target .-value)))}]
   [:span {} @size-cursor "px"]])

(defn icon-set-picker [set-cursor]
  [:div {:style {:margin-bottom "15px"}}
   [:p {:style {:margin-bottom "3px"}} "Icon Set:"]
   (doall (map (fn [[icon-set set-url]]
                  ^{:key icon-set}
                  [:div {:display :inline}
                   [:input {:type :checkbox
                            :checked (contains? @set-cursor icon-set)
                            :on-change (fn [e]
                                         (if (-> e .-target .-checked)
                                           (swap! set-cursor conj icon-set)
                                           (swap! set-cursor disj icon-set)))}]
                   (if set-url
                     [:a {:href set-url} icon-set]
                     [:label icon-set])])
                [["open-iconic" "https://github.com/iconic/open-iconic"]
                 ["strong-anchor-simple"]]))])

(defn your-reagent-component []
  [:div {:style {:padding "40px"}}
   [open-iconic/check 
    {:on-click (fn [e] (println "Icon clicked."))}
    "green"
    "24px"]
   [:p "There's an inline svg in this div!"]])

(defn page [ratom]
  (let [color-cursor (reagent/cursor ratom [:color])
        size-cursor  (reagent/cursor ratom [:size])
        set-cursor   (reagent/cursor ratom [:icon-sets])]
    (fn []
      [:div {:style {:font-family "sans-serif"}}
       [:h1 [:a {:href "https://github.com/deckeraa/clojure-inline-svg"} "clojure-inline-svg"]]
       [:p "A curated set of inline svgs for your ClojureScript/Reagent app, licensed under the "
        [:a {:href "http://opensource.org/licenses/MIT"} "MIT License"] "."]
       [:p "Why generate svgs inline in code instead of just including an svg file?"]
       [:ul
        [:li "Convenience: just add the dependency and you're ready to go. No copying & pasting files."]
        [:li "Size: use ClojureScript's dead-code optimizations to only ship the svg data that your app actually uses."]
        [:li "Flexibliity: having hiccup for the svgs in code gives you full control over color, animations, etc."]]
       [:div {:style {:display :flex
                      :align-items :center}}
        [:pre
         [:code
          "In your project.clj's dependencies:\n
      [com.stronganchortech/clojure-inline-svgs \"1.0.0\"]
     
To use:
      (ns your-cool-project.core
        (:require
         [clojure-inline-svgs.open-iconic :as open-iconic]
         [clojure-inline-svgs.strong-anchor-simple :as strong-anchor-simple]))

      (defn your-reagent-component []
        [:div
          [open-iconic/check 
            {:on-click (fn [e] (println \"Icon clicked.\"))}
            \"green\"
            \"24px\"]
          [:p \"There's an inline svg in this div!\"]])
     "]]
        [your-reagent-component]]
       [color-picker color-cursor]
       [size-picker  size-cursor]
       [icon-set-picker set-cursor]
       [:div {:style {:display :flex
                      :flex-wrap :wrap}}
        (doall (map (fn [[icon-fn display-name icon-set]]
                      ^{:key display-name}
                      [icon-card color-cursor size-cursor icon-fn display-name icon-set])
                    (filter (fn [[_ _ icon-set]] (contains? @set-cursor icon-set))
                            ;; Use a vector that contains the function and the function name so that we
                            ;; avoid issues with fn names getting munged during the cljs->js process.
                            [[open-iconic/account-login "account-login" "open-iconic"]
                             [open-iconic/account-logout "account-logout" "open-iconic"]
                             [open-iconic/action-redo "action-redo" "open-iconic"]
                             [open-iconic/action-undo "action-undo" "open-iconic"]
                             [open-iconic/align-center "align-center" "open-iconic"]
                             [open-iconic/align-left "align-left" "open-iconic"]
                             [open-iconic/align-right "align-right" "open-iconic"]
                             [open-iconic/aperture "aperture" "open-iconic"]
                             [open-iconic/arrow-bottom "arrow-bottom" "open-iconic"]
                             [open-iconic/arrow-circle-bottom "arrow-circle-bottom" "open-iconic"]
                             [open-iconic/arrow-circle-left "arrow-circle-left" "open-iconic"]
                             [open-iconic/arrow-circle-right "arrow-circle-right" "open-iconic"]
                             [open-iconic/arrow-circle-top "arrow-circle-top" "open-iconic"]
                             [open-iconic/arrow-left "arrow-left" "open-iconic"]
                             [open-iconic/arrow-right "arrow-right" "open-iconic"]
                             [open-iconic/arrow-thick-bottom "arrow-thick-bottom" "open-iconic"]
                             [open-iconic/arrow-thick-left "arrow-thick-left" "open-iconic"]
                             [open-iconic/arrow-thick-right "arrow-thick-right" "open-iconic"]
                             [open-iconic/arrow-thick-top "arrow-thick-top" "open-iconic"]
                             [open-iconic/arrow-top "arrow-top" "open-iconic"]
                             [open-iconic/audio-spectrum "audio-spectrum" "open-iconic"]
                             [open-iconic/audio "audio" "open-iconic"]
                             [open-iconic/badge "badge" "open-iconic"]
                             [open-iconic/ban "ban" "open-iconic"]
                             [open-iconic/bar-chart "bar-chart" "open-iconic"]
                             [open-iconic/basket "basket" "open-iconic"]
                             [strong-anchor-simple/bathroom-scale "bathroom-scale" "strong-anchor-simple"]
                             [open-iconic/battery-empty "battery-empty" "open-iconic"]
                             [open-iconic/battery-full "battery-full" "open-iconic"]
                             [open-iconic/beaker "beaker" "open-iconic"]
                             [open-iconic/bell "bell" "open-iconic"]
                             [open-iconic/bluetooth "bluetooth" "open-iconic"]
                             [open-iconic/bold "bold" "open-iconic"]
                             [open-iconic/bolt "bolt" "open-iconic"]
                             [open-iconic/bookmark "bookmark" "open-iconic"]
                             [open-iconic/book "book" "open-iconic"]
                             [open-iconic/box "box" "open-iconic"]
                             [open-iconic/briefcase "briefcase" "open-iconic"]
                             [open-iconic/british-pound "british-pound" "open-iconic"]
                             [open-iconic/browser "browser" "open-iconic"]
                             [open-iconic/brush "brush" "open-iconic"]
                             [open-iconic/bug "bug" "open-iconic"]
                             [open-iconic/bullhorn "bullhorn" "open-iconic"]
                             [open-iconic/calculator "calculator" "open-iconic"]
                             [open-iconic/calendar "calendar" "open-iconic"]
                             [open-iconic/camera-slr "camera-slr" "open-iconic"]
                             [open-iconic/caret-bottom "caret-bottom" "open-iconic"]
                             [open-iconic/caret-left "caret-left" "open-iconic"]
                             [open-iconic/caret-right "caret-right" "open-iconic"]
                             [open-iconic/caret-top "caret-top" "open-iconic"]
                             [open-iconic/cart "cart" "open-iconic"]
                             [open-iconic/chat "chat" "open-iconic"]
                             [open-iconic/check "check" "open-iconic"]
                             [open-iconic/chevron-bottom "chevron-bottom" "open-iconic"]
                             [open-iconic/chevron-left "chevron-left" "open-iconic"]
                             [open-iconic/chevron-right "chevron-right" "open-iconic"]
                             [open-iconic/chevron-top "chevron-top" "open-iconic"]
                             [open-iconic/circle-check "circle-check" "open-iconic"]
                             [open-iconic/circle-x "circle-x" "open-iconic"]
                             [open-iconic/clipboard "clipboard" "open-iconic"]
                             [open-iconic/clock "clock" "open-iconic"]
                             [open-iconic/cloud-download "cloud-download" "open-iconic"]
                             [open-iconic/cloud "cloud" "open-iconic"]
                             [open-iconic/cloud-upload "cloud-upload" "open-iconic"]
                             [open-iconic/cloudy "cloudy" "open-iconic"]
                             [open-iconic/code "code" "open-iconic"]
                             [open-iconic/cog "cog" "open-iconic"]
                             [open-iconic/collapse-down "collapse-down" "open-iconic"]
                             [open-iconic/collapse-left "collapse-left" "open-iconic"]
                             [open-iconic/collapse-right "collapse-right" "open-iconic"]
                             [open-iconic/collapse-up "collapse-up" "open-iconic"]
                             [open-iconic/command "command" "open-iconic"]
                             [open-iconic/comment-square "comment-square" "open-iconic"]
                             [open-iconic/compass "compass" "open-iconic"]
                             [open-iconic/contrast "contrast" "open-iconic"]
                             [open-iconic/copywriting "copywriting" "open-iconic"]
                             [open-iconic/credit-card "credit-card" "open-iconic"]
                             [open-iconic/crop "crop" "open-iconic"]
                             [open-iconic/dashboard "dashboard" "open-iconic"]
                             [open-iconic/data-transfer-download "data-transfer-download" "open-iconic"]
                             [open-iconic/data-transfer-upload "data-transfer-upload" "open-iconic"]
                             [open-iconic/delete "delete" "open-iconic"]
                             [open-iconic/dial "dial" "open-iconic"]
                             [open-iconic/document "document" "open-iconic"]
                             [open-iconic/dollar "dollar" "open-iconic"]
                             [open-iconic/double-quote-sans-left "double-quote-sans-left" "open-iconic"]
                             [open-iconic/double-quote-sans-right "double-quote-sans-right" "open-iconic"]
                             [open-iconic/double-quote-serif-left "double-quote-serif-left" "open-iconic"]
                             [open-iconic/double-quote-serif-right "double-quote-serif-right" "open-iconic"]
                             [open-iconic/droplet "droplet" "open-iconic"]
                             [open-iconic/eject "eject" "open-iconic"]
                             [open-iconic/elevator "elevator" "open-iconic"]
                             [open-iconic/ellipses "ellipses" "open-iconic"]
                             [open-iconic/envelope-closed "envelope-closed" "open-iconic"]
                             [open-iconic/envelope-open "envelope-open" "open-iconic"]
                             [open-iconic/euro "euro" "open-iconic"]
                             [open-iconic/excerpt "excerpt" "open-iconic"]
                             [open-iconic/expand-down "expand-down" "open-iconic"]
                             [open-iconic/expand-left "expand-left" "open-iconic"]
                             [open-iconic/expand-right "expand-right" "open-iconic"]
                             [open-iconic/expand-up "expand-up" "open-iconic"]
                             [open-iconic/external-link "external-link" "open-iconic"]
                             [open-iconic/eyedropper "eyedropper" "open-iconic"]
                             [open-iconic/eye "eye" "open-iconic"]
                             [open-iconic/file "file" "open-iconic"]
                             [open-iconic/fire "fire" "open-iconic"]
                             [strong-anchor-simple/fire-extinguisher "fire-extinguisher" "strong-anchor-simple"]
                             [open-iconic/flag "flag" "open-iconic"]
                             [open-iconic/flash "flash" "open-iconic"]
                             [open-iconic/folder "folder" "open-iconic"]
                             [open-iconic/fork "fork" "open-iconic"]
                             [open-iconic/fullscreen-enter "fullscreen-enter" "open-iconic"]
                             [open-iconic/fullscreen-exit "fullscreen-exit" "open-iconic"]
                             [open-iconic/globe "globe" "open-iconic"]
                             [open-iconic/graph "graph" "open-iconic"]
                             [open-iconic/grid-four-up "grid-four-up" "open-iconic"]
                             [open-iconic/grid-three-up "grid-three-up" "open-iconic"]
                             [open-iconic/grid-two-up "grid-two-up" "open-iconic"]
                             [open-iconic/hard-drive "hard-drive" "open-iconic"]
                             [open-iconic/header "header" "open-iconic"]
                             [open-iconic/headphones "headphones" "open-iconic"]
                             [open-iconic/heart "heart" "open-iconic"]
                             [open-iconic/home "home" "open-iconic"]
                             [open-iconic/image "image" "open-iconic"]
                             [open-iconic/inbox "inbox" "open-iconic"]
                             [open-iconic/infinity "infinity" "open-iconic"]
                             [open-iconic/info "info" "open-iconic"]
                             [open-iconic/italic "italic" "open-iconic"]
                             [open-iconic/justify-center "justify-center" "open-iconic"]
                             [open-iconic/justify-left "justify-left" "open-iconic"]
                             [open-iconic/justify-right "justify-right" "open-iconic"]
                             [open-iconic/key-icon "key-icon" "open-iconic"]
                             [open-iconic/laptop "laptop" "open-iconic"]
                             [open-iconic/layers "layers" "open-iconic"]
                             [open-iconic/lightbulb "lightbulb" "open-iconic"]
                             [open-iconic/link-broken "link-broken" "open-iconic"]
                             [open-iconic/link-intact "link-intact" "open-iconic"]
                             [open-iconic/list-rich "list-rich" "open-iconic"]
                             [open-iconic/list-icon "list-icon" "open-iconic"]
                             [open-iconic/location "location" "open-iconic"]
                             [open-iconic/lock-locked "lock-locked" "open-iconic"]
                             [open-iconic/lock-unlocked "lock-unlocked" "open-iconic"]
                             [open-iconic/loop-circular "loop-circular" "open-iconic"]
                             [open-iconic/loop-square "loop-square" "open-iconic"]
                             [open-iconic/loop-icon "loop-icon" "open-iconic"]
                             [open-iconic/magnifying-glass "magnifying-glass" "open-iconic"]
                             [open-iconic/map-marker "map-marker" "open-iconic"]
                             [open-iconic/map-icon "map-icon" "open-iconic"]
                             [open-iconic/media-pause "media-pause" "open-iconic"]
                             [open-iconic/media-play "media-play" "open-iconic"]
                             [open-iconic/media-record "media-record" "open-iconic"]
                             [open-iconic/media-skip-backward "media-skip-backward" "open-iconic"]
                             [open-iconic/media-skip-forward "media-skip-forward" "open-iconic"]
                             [open-iconic/media-step-backward "media-step-backward" "open-iconic"]
                             [open-iconic/media-step-forward "media-step-forward" "open-iconic"]
                             [open-iconic/media-stop "media-stop" "open-iconic"]
                             [open-iconic/medical-cross "medical-cross" "open-iconic"]
                             [open-iconic/menu "menu" "open-iconic"]
                             [open-iconic/microphone "microphone" "open-iconic"]
                             [open-iconic/minus "minus" "open-iconic"]
                             [open-iconic/monitor "monitor" "open-iconic"]
                             [open-iconic/moon "moon" "open-iconic"]
                             [open-iconic/move "move" "open-iconic"]
                             [open-iconic/musical-note "musical-note" "open-iconic"]
                             [open-iconic/paperclip "paperclip" "open-iconic"]
                             [open-iconic/pencil "pencil" "open-iconic"]
                             [open-iconic/people "people" "open-iconic"]
                             [open-iconic/person "person" "open-iconic"]
                             [open-iconic/phone "phone" "open-iconic"]
                             [open-iconic/pie-chart "pie-chart" "open-iconic"]
                             [open-iconic/pin "pin" "open-iconic"]
                             [open-iconic/play-circle "play-circle" "open-iconic"]
                             [open-iconic/plus "plus" "open-iconic"]
                             [open-iconic/power-standby "power-standby" "open-iconic"]
                             [open-iconic/print-icon "print-icon" "open-iconic"]
                             [open-iconic/project "project" "open-iconic"]
                             [open-iconic/pulse "pulse" "open-iconic"]
                             [open-iconic/puzzle-piece "puzzle-piece" "open-iconic"]
                             [open-iconic/question-mark "question-mark" "open-iconic"]
                             [open-iconic/rain "rain" "open-iconic"]
                             [open-iconic/random "random" "open-iconic"]
                             [open-iconic/reload "reload" "open-iconic"]
                             [open-iconic/resize-both "resize-both" "open-iconic"]
                             [open-iconic/resize-height "resize-height" "open-iconic"]
                             [open-iconic/resize-width "resize-width" "open-iconic"]
                             [open-iconic/rss-alt "rss-alt" "open-iconic"]
                             [open-iconic/rss "rss" "open-iconic"]
                             [open-iconic/script "script" "open-iconic"]
                             [open-iconic/share-boxed "share-boxed" "open-iconic"]
                             [open-iconic/share "share" "open-iconic"]
                             [open-iconic/shield "shield" "open-iconic"]
                             [open-iconic/signal "signal" "open-iconic"]
                             [open-iconic/signpost "signpost" "open-iconic"]
                             [open-iconic/sort-ascending "sort-ascending" "open-iconic"]
                             [open-iconic/sort-descending "sort-descending" "open-iconic"]
                             [open-iconic/spreadsheet "spreadsheet" "open-iconic"]
                             [open-iconic/star "star" "open-iconic"]
                             [open-iconic/sun "sun" "open-iconic"]
                             [open-iconic/tablet "tablet" "open-iconic"]
                             [open-iconic/tags "tags" "open-iconic"]
                             [open-iconic/tag "tag" "open-iconic"]
                             [open-iconic/target "target" "open-iconic"]
                             [open-iconic/task "task" "open-iconic"]
                             [open-iconic/terminal "terminal" "open-iconic"]
                             [open-iconic/text "text" "open-iconic"]
                             [open-iconic/thumb-down "thumb-down" "open-iconic"]
                             [open-iconic/thumb-up "thumb-up" "open-iconic"]
                             [open-iconic/timer "timer" "open-iconic"]
                             [open-iconic/transfer "transfer" "open-iconic"]
                             [open-iconic/trash "trash" "open-iconic"]
                             [open-iconic/underline "underline" "open-iconic"]
                             [open-iconic/vertical-align-bottom "vertical-align-bottom" "open-iconic"]
                             [open-iconic/vertical-align-center "vertical-align-center" "open-iconic"]
                             [open-iconic/vertical-align-top "vertical-align-top" "open-iconic"]
                             [open-iconic/video "video" "open-iconic"]
                             [open-iconic/volume-high "volume-high" "open-iconic"]
                             [open-iconic/volume-low "volume-low" "open-iconic"]
                             [open-iconic/volume-off "volume-off" "open-iconic"]
                             [open-iconic/warning "warning" "open-iconic"]
                             [open-iconic/wifi "wifi" "open-iconic"]
                             [open-iconic/wrench "wrench" "open-iconic"]
                             [open-iconic/x "x" "open-iconic"]
                             [open-iconic/yen "yen" "open-iconic"]
                             [open-iconic/zoom-in "zoom-in" "open-iconic"]
                             [open-iconic/zoom-out "zoom-out" "open-iconic"]])))]])))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
