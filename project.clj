(defproject mj-blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]

                 [duct/core           "0.6.2"]
                 [duct/module.logging "0.3.1"]
                 [duct/module.web     "0.6.3"]

                 [resauce "0.1.0"]

                 ;; cljs stuff
                 [com.google.javascript/closure-compiler-unshaded "v20180319"]
                 [thheller/shadow-cljs "2.3.16"]
                 #_[org.clojure/clojurescript "1.9.946"]

                 [medley "1.0.0"]
                 [venantius/accountant "0.2.4"] ;; browser navigation helper
                 [bidi "2.1.3"]                 ;; ui routing
                 [cljs-ajax "0.7.3"]            ;; ajax
                 [com.andrewmcveigh/cljs-time "0.5.2"] ;; handle time data
                 [com.cemerick/url "0.1.1"]            ;; work with urls
                 [re-frame "0.10.5"] ;; ui framework
                 [day8.re-frame/http-fx "0.1.6"]
                 [funcool/promesa "1.9.0"]
                 [org.webjars.bower/bootstrap "4.1.1" :exclusions [org.webjars.bower/tether
                                                                   org.webjars.bower/jquery]]
                 #_[cljsjs/highlight "9.12.0-2"]
                 [cljsjs/react-highlight "1.0.7-2"]

                 [ring-transit "0.1.6"]
                 [com.cognitect/transit-cljs "0.8.256"]
                 [ring-cors "0.1.12"]]

  :main ^:skip-aot blog.main
  :source-paths ["src/clj"  "src/cljc"]
  :test-paths   ["test/clj" "test/cljc"]

  :resource-paths ["resources" "target/resources"]
  :prep-tasks     ["javac" "compile" ["run" ":duct/compiler"]]

  :plugins [[duct/lein-duct "0.10.6"]]

  :sass {:source-paths ["src/sass"]
         :target-path "resources/blog/public/css"
         :output-style :nested}

  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:prep-tasks   ^:replace ["javac" "compile"]
          :repl-options {:init-ns user}}
   :uberjar {:aot :all}
   ;; for shadow-cljs
   :cljs {:source-paths ["src/cljs" "src/cljc" "dev/src/cljs"]
          :dependencies [;; cljs stuff
                         [binaryage/devtools "0.9.7"]
                         [re-frisk "0.5.3"]
                         [com.cemerick/piggieback "0.2.2"]
                         [org.clojure/tools.nrepl "0.2.13"]]}
   :profiles/dev {}
   :project/dev  {:source-paths   ["dev/src/clj"]
                  :resource-paths ["dev/resources"]
                  :dependencies   [[integrant/repl "0.2.0"]
                                   [eftest "0.4.1"]
                                   [thheller/shadow-cljs "2.3.16"]
                                   ;; cljs stuff
                                   [binaryage/devtools "0.9.7"]
                                   [re-frisk "0.5.3"]
                                   [com.cemerick/piggieback "0.2.2"]
                                   [org.clojure/tools.nrepl "0.2.13"]]
                  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                  :plugins [[lein-doo "0.1.8"]
                            [deraen/lein-sass4clj "0.3.1"]]}})
