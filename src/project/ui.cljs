(ns {{project}}.ui
  (:require [reagent.core :as reagent]
            [reagent.dom :as reagent-dom]))

(defn main []
  [:p "Hello, {{project}}"])

(defn mount []
  (reagent-dom/render
   [main]
   (js/document.getElementById "app")))

(mount)
