(ns {{project}}.http
  (:require [integrant.core :as ig]
            [muuntaja.interceptor]
            [reitit.http :as http]
            [reitit.interceptor.sieppari :as sieppari]
            [reitit.ring :as ring]
            [ring.adapter.jetty :as jetty]
            [{{project}}.routes :as routes]))

(defn interceptor [number]
  {:enter (fn [ctx] (update-in ctx [:request :number] (fnil + 0) number))})

(defn handler []
  (http/ring-handler
   (http/router (routes/routes))
   (ring/create-default-handler)
   {:executor sieppari/executor
    :interceptors [(muuntaja.interceptor/format-interceptor)]}))

(defmethod ig/init-key ::server [_ {:keys [port rebuild-on-request?]}]
  (jetty/run-jetty (if rebuild-on-request?
                     #((handler) %)
                     (handler))
                   {:port port
                    :join? false}))
