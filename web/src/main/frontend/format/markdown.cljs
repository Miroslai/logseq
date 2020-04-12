(ns frontend.format.markdown
  (:require [frontend.format.protocol :as protocol]
            [frontend.config :as config]
            [frontend.loader :as loader]))

(defn loaded? []
  js/window.showdown)

(defn to-html
  [content config]
  (when (loaded?)
    (.makeHtml (js/window.showdown.Converter.) content)))

(defrecord MdMode []
  protocol/Format
  (toHtml [this content config]
    (when (loaded?)
      (.makeHtml (js/window.showdown.Converter.) content)))
  (loaded? [this]
    (some? (loaded?)))
  (lazyLoad [this ok-handler error-handler]
    (loader/load
     "https://cdnjs.cloudflare.com/ajax/libs/showdown/1.9.1/showdown.min.js"
     ok-handler
     error-handler)))
