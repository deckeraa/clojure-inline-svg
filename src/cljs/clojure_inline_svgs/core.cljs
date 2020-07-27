(ns clojure-inline-svgs.core
  (:require
   [reagent.core :as reagent]
   [clojure-inline-svgs.open-iconic :as open-iconic]
   ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce app-state
  (reagent/atom {}))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

(defn page [ratom]
  [:div
   "Welcome to reagent-figwheel."
   ;; [open-iconic/account-login {} "black" "24px"]
   ;; [open-iconic/account-logout {} "black" "24px"]
   ;; [open-iconic/action-redo {} "black" "24px"]
   ;; [open-iconic/action-undo {} "black" "24px"]
   ;; [open-iconic/align-center {} "black" "24px"]
   ;; [open-iconic/align-left {} "black" "24px"]
   ;; [open-iconic/align-right {} "black" "24px"]
   ;; [open-iconic/aperture {} "black" "24px"]
   ;; [open-iconic/arrow-bottom {} "black" "24px"]
   ;; [open-iconic/arrow-circle-bottom {} "black" "24px"]
   ;; [open-iconic/arrow-circle-left {} "black" "24px"]
   ;; [open-iconic/arrow-circle-right {} "black" "24px"]
   ;; [open-iconic/arrow-circle-top {} "black" "24px"]
   ;; [open-iconic/arrow-left {} "black" "24px"]
   ;; [open-iconic/arrow-right {} "black" "24px"]
   ;; [open-iconic/arrow-thick-bottom {} "black" "24px"]
   ;; [open-iconic/arrow-thick-left {} "black" "24px"]
   ;; [open-iconic/arrow-thick-right {} "black" "24px"]
   ;; [open-iconic/arrow-thick-top {} "black" "24px"]
   ;; [open-iconic/arrow-top {} "black" "24px"]
   ;; [open-iconic/audio-spectrum {} "black" "24px"]
   ;; [open-iconic/audio {} "black" "24px"]
   ;; [open-iconic/badge {} "black" "24px"]
   ;; [open-iconic/ban {} "black" "24px"]
   ;; [open-iconic/bar-chart {} "black" "24px"]
   ;; [open-iconic/basket {} "black" "24px"]
   ;; [open-iconic/battery-empty {} "black" "24px"]
   ;; [open-iconic/battery-full {} "black" "24px"]
   ;; [open-iconic/beaker {} "black" "24px"]
   ;; [open-iconic/bell {} "black" "24px"]
   ;; [open-iconic/bluetooth {} "black" "24px"]
   ;; [open-iconic/bold {} "black" "24px"]
   ;; [open-iconic/bolt {} "black" "24px"]
   ;; [open-iconic/bookmark {} "black" "24px"]
   ;; [open-iconic/book {} "black" "24px"]
   ;; [open-iconic/box {} "black" "24px"]
   ;; [open-iconic/briefcase {} "black" "24px"]
   ;; [open-iconic/british-pound {} "black" "24px"]
   ;; [open-iconic/browser {} "black" "24px"]
   ;; [open-iconic/brush {} "black" "24px"]
   ;; [open-iconic/bug {} "black" "24px"]
   ;; [open-iconic/bullhorn {} "black" "24px"]
   ;; [open-iconic/calculator {} "black" "24px"]
   ;; [open-iconic/calendar {} "black" "24px"]
   ;; [open-iconic/camera-slr {} "black" "24px"]
   ;; [open-iconic/caret-bottom {} "black" "24px"]
   ;; [open-iconic/caret-left {} "black" "24px"]
   ;; [open-iconic/caret-right {} "black" "24px"]
   ;; [open-iconic/caret-top {} "black" "24px"]
   ;; [open-iconic/cart {} "black" "24px"]
   ;; [open-iconic/chat {} "black" "24px"]
   ;; [open-iconic/check {} "black" "24px"]
   ;; [open-iconic/chevron-bottom {} "black" "24px"]
   ;; [open-iconic/chevron-left {} "black" "24px"]
   ;; [open-iconic/chevron-right {} "black" "24px"]
   ;; [open-iconic/chevron-top {} "black" "24px"]
   ;; [open-iconic/circle-check {} "black" "24px"]
   ;; [open-iconic/circle-x {} "black" "24px"]
   ;; [open-iconic/clipboard {} "black" "24px"]
   ;; [open-iconic/clock {} "black" "24px"]
   ;; [open-iconic/cloud-download {} "black" "24px"]
   ;; [open-iconic/cloud {} "black" "24px"]
   ;; [open-iconic/cloud-upload {} "black" "24px"]
   ;; [open-iconic/cloudy {} "black" "24px"]
   ;; [open-iconic/code {} "black" "24px"]
   ;; [open-iconic/cog {} "black" "24px"]
   ;; [open-iconic/collapse-down {} "black" "24px"]
   ;; [open-iconic/collapse-left {} "black" "24px"]
   ;; [open-iconic/collapse-right {} "black" "24px"]
   ;; [open-iconic/collapse-up {} "black" "24px"]
   ;; [open-iconic/command {} "black" "24px"]
   ;; [open-iconic/comment-square {} "black" "24px"]
   ;; [open-iconic/compass {} "black" "24px"]
   ;; [open-iconic/contrast {} "black" "24px"]
   ;; [open-iconic/copywriting {} "black" "24px"]
   ;; [open-iconic/credit-card {} "black" "24px"]
   ;; [open-iconic/crop {} "black" "24px"]
   ;; [open-iconic/dashboard {} "black" "24px"]
   ;; [open-iconic/data-transfer-download {} "black" "24px"]
   ;; [open-iconic/data-transfer-upload {} "black" "24px"]
   ;; [open-iconic/delete {} "black" "24px"]
   ;; [open-iconic/dial {} "black" "24px"]
   ;; [open-iconic/document {} "black" "24px"]
   [open-iconic/dollar {} "black" "24px"]
   [open-iconic/double-quote-sans-left {} "black" "24px"]
   [open-iconic/double-quote-sans-right {} "black" "24px"]
   [open-iconic/double-quote-serif-left {} "black" "24px"]
   [open-iconic/double-quote-serif-right {} "black" "24px"]
   [open-iconic/droplet {} "black" "24px"]
   [open-iconic/eject {} "black" "24px"]
   [open-iconic/elevator {} "black" "24px"]
   [open-iconic/ellipses {} "black" "24px"]
   [open-iconic/envelope-closed {} "black" "24px"]
   [open-iconic/envelope-open {} "black" "24px"]
   [open-iconic/euro {} "black" "24px"]
   [open-iconic/excerpt {} "black" "24px"]
   [open-iconic/expand-down {} "black" "24px"]
   [open-iconic/expand-left {} "black" "24px"]
   [open-iconic/expand-right {} "black" "24px"]
   [open-iconic/expand-up {} "black" "24px"]
   [open-iconic/external-link {} "black" "24px"]
   [open-iconic/eyedropper {} "black" "24px"]
   [open-iconic/eye {} "black" "24px"]
   [open-iconic/file {} "black" "24px"]
   [open-iconic/fire {} "black" "24px"]
   [open-iconic/flag {} "black" "24px"]
   [open-iconic/flash {} "black" "24px"]
   [open-iconic/folder {} "black" "24px"]
   [open-iconic/fork {} "black" "24px"]
   [open-iconic/fullscreen-enter {} "black" "24px"]
   [open-iconic/fullscreen-exit {} "black" "24px"]
   [open-iconic/globe {} "black" "24px"]
   [open-iconic/graph {} "black" "24px"]
   [open-iconic/grid-four-up {} "black" "24px"]
   [open-iconic/grid-three-up {} "black" "24px"]
   [open-iconic/grid-two-up {} "black" "24px"]
   [open-iconic/hard-drive {} "black" "24px"]
   [open-iconic/header {} "black" "24px"]
   [open-iconic/headphones {} "black" "24px"]
   [open-iconic/heart {} "black" "24px"]
   [open-iconic/home {} "black" "24px"]
   [open-iconic/image {} "black" "24px"]
   [open-iconic/inbox {} "black" "24px"]
   [open-iconic/infinity {} "black" "24px"]
   [open-iconic/info {} "black" "24px"]
   [open-iconic/italic {} "black" "24px"]
   [open-iconic/justify-center {} "black" "24px"]
   [open-iconic/justify-left {} "black" "24px"]
   [open-iconic/justify-right {} "black" "24px"]
   [open-iconic/key-icon {} "black" "24px"]
   [open-iconic/laptop {} "black" "24px"]
   [open-iconic/layers {} "black" "24px"]
   [open-iconic/lightbulb {} "black" "24px"]
   [open-iconic/link-broken {} "black" "24px"]
   [open-iconic/link-intact {} "black" "24px"]
   [open-iconic/list-rich {} "black" "24px"]
   [open-iconic/list-icon {} "black" "24px"]
   [open-iconic/location {} "black" "24px"]
   [open-iconic/lock-locked {} "black" "24px"]
   [open-iconic/lock-unlocked {} "black" "24px"]
   [open-iconic/loop-circular {} "black" "24px"]
   [open-iconic/loop-square {} "black" "24px"]
   [open-iconic/loop-icon {} "black" "24px"]
   [open-iconic/magnifying-glass {} "black" "24px"]
   [open-iconic/map-marker {} "black" "24px"]
   [open-iconic/map-icon {} "black" "24px"]
   [open-iconic/media-pause {} "black" "24px"]
   [open-iconic/media-play {} "black" "24px"]
   [open-iconic/media-record {} "black" "24px"]
   [open-iconic/media-skip-backward {} "black" "24px"]
   [open-iconic/media-skip-forward {} "black" "24px"]
   [open-iconic/media-step-backward {} "black" "24px"]
   [open-iconic/media-step-forward {} "black" "24px"]
   [open-iconic/media-stop {} "black" "24px"]
   [open-iconic/medical-cross {} "black" "24px"]
   [open-iconic/menu {} "black" "24px"]
   [open-iconic/microphone {} "black" "24px"]
   [open-iconic/minus {} "black" "24px"]
   [open-iconic/monitor {} "black" "24px"]
   [open-iconic/moon {} "black" "24px"]
   [open-iconic/move {} "black" "24px"]
   [open-iconic/musical-note {} "black" "24px"]
   [open-iconic/paperclip {} "black" "24px"]
   [open-iconic/pencil {} "black" "24px"]
   [open-iconic/people {} "black" "24px"]
   [open-iconic/person {} "black" "24px"]
   [open-iconic/phone {} "black" "24px"]
   [open-iconic/pie-chart {} "black" "24px"]
   [open-iconic/pin {} "black" "24px"]
   [open-iconic/play-circle {} "black" "24px"]
   [open-iconic/plus {} "black" "24px"]
   [open-iconic/power-standby {} "black" "24px"]
   [open-iconic/print-icon {} "black" "24px"]
   [open-iconic/project {} "black" "24px"]
   [open-iconic/pulse {} "black" "24px"]
   [open-iconic/puzzle-piece {} "black" "24px"]
   [open-iconic/question-mark {} "black" "24px"]
   [open-iconic/rain {} "black" "24px"]
   [open-iconic/random {} "black" "24px"]
   [open-iconic/reload {} "black" "24px"]
   [open-iconic/resize-both {} "black" "24px"]
   [open-iconic/resize-height {} "black" "24px"]
   [open-iconic/resize-width {} "black" "24px"]
   [open-iconic/rss-alt {} "black" "24px"]
   [open-iconic/rss {} "black" "24px"]
   [open-iconic/script {} "black" "24px"]
   [open-iconic/share-boxed {} "black" "24px"]
   [open-iconic/share {} "black" "24px"]
   [open-iconic/shield {} "black" "24px"]
   [open-iconic/signal {} "black" "24px"]
   [open-iconic/signpost {} "black" "24px"]
   [open-iconic/sort-ascending {} "black" "24px"]
   [open-iconic/sort-descending {} "black" "24px"]
   [open-iconic/spreadsheet {} "black" "24px"]
   [open-iconic/star {} "black" "24px"]
   [open-iconic/sun {} "black" "24px"]
   [open-iconic/tablet {} "black" "24px"]
   [open-iconic/tags {} "black" "24px"]
   [open-iconic/tag {} "black" "24px"]
   [open-iconic/target {} "black" "24px"]
   [open-iconic/task {} "black" "24px"]
   [open-iconic/terminal {} "black" "24px"]
   [open-iconic/text {} "black" "24px"]
   [open-iconic/thumb-down {} "black" "24px"]
   [open-iconic/thumb-up {} "black" "24px"]
   [open-iconic/timer {} "black" "24px"]
   [open-iconic/transfer {} "black" "24px"]
   [open-iconic/trash {} "black" "24px"]
   [open-iconic/underline {} "black" "24px"]
   [open-iconic/vertical-align-bottom {} "black" "24px"]
   [open-iconic/vertical-align-center {} "black" "24px"]
   [open-iconic/vertical-align-top {} "black" "24px"]
   [open-iconic/video {} "black" "24px"]
   [open-iconic/volume-high {} "black" "24px"]
   [open-iconic/volume-low {} "black" "24px"]
   [open-iconic/volume-off {} "black" "24px"]
   [open-iconic/warning {} "black" "24px"]
   [open-iconic/wifi {} "black" "24px"]
   [open-iconic/wrench {} "black" "24px"]
   [open-iconic/x {} "black" "24px"]
   [open-iconic/yen {} "black" "24px"]
   [open-iconic/zoom-in {} "black" "24px"]
   [open-iconic/zoom-out {} "black" "24px"]])



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
