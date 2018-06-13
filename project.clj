(defproject mj-blog "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
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
                 [mount "0.1.11"]
                 [funcool/promesa "1.9.0"]
                 [org.webjars.bower/bootstrap "4.1.1" :exclusions [org.webjars.bower/tether
                                                                   org.webjars.bower/jquery]]]

  :source-paths ["src/clj"  "src/cljc"]
  :test-paths   ["test/clj" "test/cljc"]

  :resource-paths ["resources" "target/resources"]

  :sass {:source-paths ["src/sass"]
         :target-path "resources/blog/public/css"
         :output-style :nested}

  :profiles
  {:dev  [:project/dev :profiles/dev]
   :repl {:repl-options {:init-ns user}}
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
                  :dependencies   [[thheller/shadow-cljs "2.3.16"]
                                   ;; cljs stuff
                                   [binaryage/devtools "0.9.7"]
                                   [re-frisk "0.5.3"]
                                   [com.cemerick/piggieback "0.2.2"]
                                   [org.clojure/tools.nrepl "0.2.13"]]
                  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                  :plugins [[lein-doo "0.1.8"]
                            [deraen/lein-sass4clj "0.3.1"]]}}

  :cljsbuild
  {:builds
   [{:id "dev"
     :source-paths ["src/cljs" "src/cljc" "dev/src/cljs"]
     :compiler {:main blog.dev
                :output-to "resources/blog/public/js/compiled/app/app.js"
                :output-dir "resources/blog/public/js/compiled/app/out"
                :asset-path "js/compiled/app/out"
                :source-map-timestamp true
                :preloads [devtools.preload re-frisk.preload]
                :external-config {:devtools/config {:features-to-install :all}}}}]})
