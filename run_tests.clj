(require '[clojure.edn :as edn]
         '[clojure.java.io :as io]
         '[kotoba.lang.text :as str]
         '[clojure.test :as t]
         'hoshimori.methods.test-datom-emit
         'hoshimori.methods.test-ie-flow
         'hoshimori.methods.test-ingest
         'hoshimori.murakumo-test
         'hoshimori.social-publication-test
         'hoshimori.tests.test-analyze
         'hoshimori.tests.test-coverage
         'hoshimori.tests.test-kotoba
         'hoshimori.tests.test-stewardship-gap)

(def contracts (edn/read-string (slurp "repository-contracts.edn")))

(doseq [path (:required contracts)]
  (assert (.isFile (io/file path)) (str "required repository file missing: " path)))

(doseq [path (concat (:required contracts) ["repository-contracts.edn"])
        :when (str/ends-with? path ".edn")]
  (edn/read-string (slurp path)))

(let [forbidden (set (:forbidden-files contracts))
      forbidden-exts (:forbidden-extensions contracts)
      bad (->> (file-seq (io/file "."))
               (filter #(.isFile %))
               (map #(.getPath %))
               (remove #(str/starts-with? % "./.git/"))
               (filter #(or (forbidden (.getName (io/file %)))
                            (some (fn [ext] (str/ends-with? % ext)) forbidden-exts))))]
  (assert (empty? bad) (str "forbidden artifacts: " (vec bad))))

(let [namespaces '[hoshimori.methods.test-datom-emit
                   hoshimori.methods.test-ie-flow
                   hoshimori.methods.test-ingest
                   hoshimori.murakumo-test
                   hoshimori.social-publication-test
                   hoshimori.tests.test-analyze
                   hoshimori.tests.test-coverage
                   hoshimori.tests.test-kotoba
                   hoshimori.tests.test-stewardship-gap]
      result (apply t/run-tests namespaces)]
  (System/exit (if (zero? (+ (:fail result) (:error result))) 0 1)))
