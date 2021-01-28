(ns {{project}}.bootstrap
  (:require [aero.core :as aero]
            [clojure.java.io :as io]
            [integrant.core :as ig]
            [integrant.repl :as ig-repl]))

(defmethod aero/reader 'ig/ref
  [_ _tag value]
  (ig/ref value))

(defmethod aero/reader 'ig/refset
  [_ _tag value]
  (ig/refset value))

(defn ig-config
  ([]
   (ig-config :default))
  ([profile]
   (aero/read-config (io/resource "config.edn") {:profile profile})))

(defn set-prep!
  ([]
   (set-prep! :default))
  ([profile]
   (ig-repl/set-prep! #(doto (ig-config profile) ig/load-namespaces))))

(defn go [& _]
  (set-prep!)
  (ig-repl/go))
