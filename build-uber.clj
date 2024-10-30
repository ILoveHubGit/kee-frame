;; build.clj
(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'kee-frame/kee-frame) ; replace with your desired group/artifact name
(def version "1.3.3")               ; replace with your desired version
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file (format "target/%s-%s.jar" (name lib) version))

(defn clean [_]
  (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs   ["src"]
               :target-dir class-dir})
  (b/compile-clj {:basis basis
                  :src-dirs ["src"]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis     basis
           :main      'kee-frame/kee-frame})) ; replace with your main namespace
