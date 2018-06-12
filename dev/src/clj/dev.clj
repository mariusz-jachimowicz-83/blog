(ns dev
  (:refer-clojure :exclude [test])
  (:require [clojure.repl :refer :all]
            [clojure.tools.namespace.repl :refer [refresh]]
            [clojure.java.io :as io]
            [shadow.cljs.devtools.api :as dapi]))

(defn ui-repl [] (dapi/nrepl-select :app))

;; commands
(comment
  (ui-repl)

)
