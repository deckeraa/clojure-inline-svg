(ns clojure-inline-svgs.core
  (:require
   [reagent.core :as reagent]
   [clojure-inline-svgs.open-iconic :as open-iconic]
   ))

(defonce app-state
  (reagent/atom {:color "black"
                 :size  24}))

(defn icon-card [color size [icon-fn display-name]]
  ^{:key display-name}
  [:div {:style {:display :flex
                 :align-items :center
                 :text-align :center
                 :flex-direction :column
                 :padding "20px 10px"
                 :min-width "150px"}}
   [icon-fn {} color (str size "px")]
   [:p {} display-name]])

(defn color-picker [color-cursor]
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
               ["black" "red" "blue"]))])

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

(defn page [ratom]
  [:div {:style {:font-family "sans-serif"}}
   [:h1
    "Inline ClojureScript/Reagent/Hiccup SVGs from "
    [:a {:href "https://github.com/iconic/open-iconic"} "Open Iconic"]]
   [:p "Licensed under the "
    [:a {:href "http://opensource.org/licenses/MIT"} "MIT License"]
    " (the same license as open-iconic)."]
   [color-picker (reagent/cursor ratom [:color])]
   [size-picker  (reagent/cursor ratom [:size])]
   [:div {:style {:display :flex
                  :flex-wrap :wrap}}
    (doall (map (partial icon-card (:color @ratom) (:size @ratom))
                ;; Use a vector that contains the function and the function name so that we
                ;; avoid issues with fn names getting munged during the cljs->js process.
                [[open-iconic/account-login "account-login"]
                 [open-iconic/account-logout "account-logout"]
                 [open-iconic/action-redo "action-redo"]
                 [open-iconic/action-undo "action-undo"]
                 [open-iconic/align-center "align-center"]
                 [open-iconic/align-left "align-left"]
                 [open-iconic/align-right "align-right"]
                 [open-iconic/aperture "aperture"]
                 [open-iconic/arrow-bottom "arrow-bottom"]
                 [open-iconic/arrow-circle-bottom "arrow-circle-bottom"]
                 [open-iconic/arrow-circle-left "arrow-circle-left"]
                 [open-iconic/arrow-circle-right "arrow-circle-right"]
                 [open-iconic/arrow-circle-top "arrow-circle-top"]
                 [open-iconic/arrow-left "arrow-left"]
                 [open-iconic/arrow-right "arrow-right"]
                 [open-iconic/arrow-thick-bottom "arrow-thick-bottom"]
                 [open-iconic/arrow-thick-left "arrow-thick-left"]
                 [open-iconic/arrow-thick-right "arrow-thick-right"]
                 [open-iconic/arrow-thick-top "arrow-thick-top"]
                 [open-iconic/arrow-top "arrow-top"]
                 [open-iconic/audio-spectrum "audio-spectrum"]
                 [open-iconic/audio "audio"]
                 [open-iconic/badge "badge"]
                 [open-iconic/ban "ban"]
                 [open-iconic/bar-chart "bar-chart"]
                 [open-iconic/basket "basket"]
                 [open-iconic/battery-empty "battery-empty"]
                 [open-iconic/battery-full "battery-full"]
                 [open-iconic/beaker "beaker"]
                 [open-iconic/bell "bell"]
                 [open-iconic/bluetooth "bluetooth"]
                 [open-iconic/bold "bold"]
                 [open-iconic/bolt "bolt"]
                 [open-iconic/bookmark "bookmark"]
                 [open-iconic/book "book"]
                 [open-iconic/box "box"]
                 [open-iconic/briefcase "briefcase"]
                 [open-iconic/british-pound "british-pound"]
                 [open-iconic/browser "browser"]
                 [open-iconic/brush "brush"]
                 [open-iconic/bug "bug"]
                 [open-iconic/bullhorn "bullhorn"]
                 [open-iconic/calculator "calculator"]
                 [open-iconic/calendar "calendar"]
                 [open-iconic/camera-slr "camera-slr"]
                 [open-iconic/caret-bottom "caret-bottom"]
                 [open-iconic/caret-left "caret-left"]
                 [open-iconic/caret-right "caret-right"]
                 [open-iconic/caret-top "caret-top"]
                 [open-iconic/cart "cart"]
                 [open-iconic/chat "chat"]
                 [open-iconic/check "check"]
                 [open-iconic/chevron-bottom "chevron-bottom"]
                 [open-iconic/chevron-left "chevron-left"]
                 [open-iconic/chevron-right "chevron-right"]
                 [open-iconic/chevron-top "chevron-top"]
                 [open-iconic/circle-check "circle-check"]
                 [open-iconic/circle-x "circle-x"]
                 [open-iconic/clipboard "clipboard"]
                 [open-iconic/clock "clock"]
                 [open-iconic/cloud-download "cloud-download"]
                 [open-iconic/cloud "cloud"]
                 [open-iconic/cloud-upload "cloud-upload"]
                 [open-iconic/cloudy "cloudy"]
                 [open-iconic/code "code"]
                 [open-iconic/cog "cog"]
                 [open-iconic/collapse-down "collapse-down"]
                 [open-iconic/collapse-left "collapse-left"]
                 [open-iconic/collapse-right "collapse-right"]
                 [open-iconic/collapse-up "collapse-up"]
                 [open-iconic/command "command"]
                 [open-iconic/comment-square "comment-square"]
                 [open-iconic/compass "compass"]
                 [open-iconic/contrast "contrast"]
                 [open-iconic/copywriting "copywriting"]
                 [open-iconic/credit-card "credit-card"]
                 [open-iconic/crop "crop"]
                 [open-iconic/dashboard "dashboard"]
                 [open-iconic/data-transfer-download "data-transfer-download"]
                 [open-iconic/data-transfer-upload "data-transfer-upload"]
                 [open-iconic/delete "delete"]
                 [open-iconic/dial "dial"]
                 [open-iconic/document "document"]
                 [open-iconic/dollar "dollar"]
                 [open-iconic/double-quote-sans-left "double-quote-sans-left"]
                 [open-iconic/double-quote-sans-right "double-quote-sans-right"]
                 [open-iconic/double-quote-serif-left "double-quote-serif-left"]
                 [open-iconic/double-quote-serif-right "double-quote-serif-right"]
                 [open-iconic/droplet "droplet"]
                 [open-iconic/eject "eject"]
                 [open-iconic/elevator "elevator"]
                 [open-iconic/ellipses "ellipses"]
                 [open-iconic/envelope-closed "envelope-closed"]
                 [open-iconic/envelope-open "envelope-open"]
                 [open-iconic/euro "euro"]
                 [open-iconic/excerpt "excerpt"]
                 [open-iconic/expand-down "expand-down"]
                 [open-iconic/expand-left "expand-left"]
                 [open-iconic/expand-right "expand-right"]
                 [open-iconic/expand-up "expand-up"]
                 [open-iconic/external-link "external-link"]
                 [open-iconic/eyedropper "eyedropper"]
                 [open-iconic/eye "eye"]
                 [open-iconic/file "file"]
                 [open-iconic/fire "fire"]
                 [open-iconic/flag "flag"]
                 [open-iconic/flash "flash"]
                 [open-iconic/folder "folder"]
                 [open-iconic/fork "fork"]
                 [open-iconic/fullscreen-enter "fullscreen-enter"]
                 [open-iconic/fullscreen-exit "fullscreen-exit"]
                 [open-iconic/globe "globe"]
                 [open-iconic/graph "graph"]
                 [open-iconic/grid-four-up "grid-four-up"]
                 [open-iconic/grid-three-up "grid-three-up"]
                 [open-iconic/grid-two-up "grid-two-up"]
                 [open-iconic/hard-drive "hard-drive"]
                 [open-iconic/header "header"]
                 [open-iconic/headphones "headphones"]
                 [open-iconic/heart "heart"]
                 [open-iconic/home "home"]
                 [open-iconic/image "image"]
                 [open-iconic/inbox "inbox"]
                 [open-iconic/infinity "infinity"]
                 [open-iconic/info "info"]
                 [open-iconic/italic "italic"]
                 [open-iconic/justify-center "justify-center"]
                 [open-iconic/justify-left "justify-left"]
                 [open-iconic/justify-right "justify-right"]
                 [open-iconic/key-icon "key-icon"]
                 [open-iconic/laptop "laptop"]
                 [open-iconic/layers "layers"]
                 [open-iconic/lightbulb "lightbulb"]
                 [open-iconic/link-broken "link-broken"]
                 [open-iconic/link-intact "link-intact"]
                 [open-iconic/list-rich "list-rich"]
                 [open-iconic/list-icon "list-icon"]
                 [open-iconic/location "location"]
                 [open-iconic/lock-locked "lock-locked"]
                 [open-iconic/lock-unlocked "lock-unlocked"]
                 [open-iconic/loop-circular "loop-circular"]
                 [open-iconic/loop-square "loop-square"]
                 [open-iconic/loop-icon "loop-icon"]
                 [open-iconic/magnifying-glass "magnifying-glass"]
                 [open-iconic/map-marker "map-marker"]
                 [open-iconic/map-icon "map-icon"]
                 [open-iconic/media-pause "media-pause"]
                 [open-iconic/media-play "media-play"]
                 [open-iconic/media-record "media-record"]
                 [open-iconic/media-skip-backward "media-skip-backward"]
                 [open-iconic/media-skip-forward "media-skip-forward"]
                 [open-iconic/media-step-backward "media-step-backward"]
                 [open-iconic/media-step-forward "media-step-forward"]
                 [open-iconic/media-stop "media-stop"]
                 [open-iconic/medical-cross "medical-cross"]
                 [open-iconic/menu "menu"]
                 [open-iconic/microphone "microphone"]
                 [open-iconic/minus "minus"]
                 [open-iconic/monitor "monitor"]
                 [open-iconic/moon "moon"]
                 [open-iconic/move "move"]
                 [open-iconic/musical-note "musical-note"]
                 [open-iconic/paperclip "paperclip"]
                 [open-iconic/pencil "pencil"]
                 [open-iconic/people "people"]
                 [open-iconic/person "person"]
                 [open-iconic/phone "phone"]
                 [open-iconic/pie-chart "pie-chart"]
                 [open-iconic/pin "pin"]
                 [open-iconic/play-circle "play-circle"]
                 [open-iconic/plus "plus"]
                 [open-iconic/power-standby "power-standby"]
                 [open-iconic/print-icon "print-icon"]
                 [open-iconic/project "project"]
                 [open-iconic/pulse "pulse"]
                 [open-iconic/puzzle-piece "puzzle-piece"]
                 [open-iconic/question-mark "question-mark"]
                 [open-iconic/rain "rain"]
                 [open-iconic/random "random"]
                 [open-iconic/reload "reload"]
                 [open-iconic/resize-both "resize-both"]
                 [open-iconic/resize-height "resize-height"]
                 [open-iconic/resize-width "resize-width"]
                 [open-iconic/rss-alt "rss-alt"]
                 [open-iconic/rss "rss"]
                 [open-iconic/script "script"]
                 [open-iconic/share-boxed "share-boxed"]
                 [open-iconic/share "share"]
                 [open-iconic/shield "shield"]
                 [open-iconic/signal "signal"]
                 [open-iconic/signpost "signpost"]
                 [open-iconic/sort-ascending "sort-ascending"]
                 [open-iconic/sort-descending "sort-descending"]
                 [open-iconic/spreadsheet "spreadsheet"]
                 [open-iconic/star "star"]
                 [open-iconic/sun "sun"]
                 [open-iconic/tablet "tablet"]
                 [open-iconic/tags "tags"]
                 [open-iconic/tag "tag"]
                 [open-iconic/target "target"]
                 [open-iconic/task "task"]
                 [open-iconic/terminal "terminal"]
                 [open-iconic/text "text"]
                 [open-iconic/thumb-down "thumb-down"]
                 [open-iconic/thumb-up "thumb-up"]
                 [open-iconic/timer "timer"]
                 [open-iconic/transfer "transfer"]
                 [open-iconic/trash "trash"]
                 [open-iconic/underline "underline"]
                 [open-iconic/vertical-align-bottom "vertical-align-bottom"]
                 [open-iconic/vertical-align-center "vertical-align-center"]
                 [open-iconic/vertical-align-top "vertical-align-top"]
                 [open-iconic/video "video"]
                 [open-iconic/volume-high "volume-high"]
                 [open-iconic/volume-low "volume-low"]
                 [open-iconic/volume-off "volume-off"]
                 [open-iconic/warning "warning"]
                 [open-iconic/wifi "wifi"]
                 [open-iconic/wrench "wrench"]
                 [open-iconic/x "x"]
                 [open-iconic/yen "yen"]
                 [open-iconic/zoom-in "zoom-in"]
                 [open-iconic/zoom-out "zoom-out"]]))]])



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
