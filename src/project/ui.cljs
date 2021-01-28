(ns {{project}}.ui)

(defn main []
  [:p "Hello, {{project}}"])

(defn mount []
  (reagent-dom/render
   [main]
   (js/document.getElementById "app")))

(mount)
