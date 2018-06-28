(ns blog.domain.resources
  (:require
   [clojure.java.io :as io]
   [resauce.core    :as resauce]))

(defn- clj-file->ns-name
  "Read clj file and grap namespace name from it"
  [file-content]
  (-> (re-seq #"(?m)^(?:.+ns\s)(.*)$" file-content)
      first
      second))

(defn- resolve-fn!
  "Try resolve fn symbol from particular namespace"
  [namespace-symbol fn-symbol]
  (or (ns-resolve namespace-symbol fn-symbol)
      (throw (Exception. (format "Could not resolve %s/%s on the classpath"
                                 (name namespace-symbol)
                                 (name fn-symbol))))))

(defn- load-blog-files
  "Load blog files and and get their content.
   Each blog resource has function 'metadata' that returns data like:
   - title
   - create-at
   - hiccup content
   - ..."
  [files]
  (for [file files]
    (let [ns-sym  (-> file slurp clj-file->ns-name symbol)
          _       (require ns-sym)
          data-fn (resolve-fn! ns-sym 'metadata)]
      (data-fn))))

(defn- load-resources
  "Get all content from each blog resource stored on disk at particular path"
  [path]
  (-> path resauce/resource-dir load-blog-files))

(defn get-posts
  "Get all content from each post stored on disk"
  []
  (load-resources "blog/data/posts/"))
