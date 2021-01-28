(ns {{project}}.routes)

(defn routes []
  ["/"
   {:get
    {:handler
     (fn [_]
       {:status 200
        :headers {"Content-Type" "text/plain"}
        :body "ok"})}}])
