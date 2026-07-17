#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")"
exec bb -e '
(require (quote clojure.test)
         (quote hoshimori.methods.test-datom-emit)
         (quote hoshimori.methods.test-ie-flow)
         (quote hoshimori.methods.test-ingest)
         (quote hoshimori.murakumo-test)
         (quote hoshimori.social-publication-test)
         (quote hoshimori.tests.test-analyze)
         (quote hoshimori.tests.test-coverage)
         (quote hoshimori.tests.test-kotoba)
         (quote hoshimori.tests.test-stewardship-gap))
(let [namespaces [                  (quote hoshimori.methods.test-datom-emit)
                  (quote hoshimori.methods.test-ie-flow)
                  (quote hoshimori.methods.test-ingest)
                  (quote hoshimori.murakumo-test)
                  (quote hoshimori.social-publication-test)
                  (quote hoshimori.tests.test-analyze)
                  (quote hoshimori.tests.test-coverage)
                  (quote hoshimori.tests.test-kotoba)
                  (quote hoshimori.tests.test-stewardship-gap)]
      result (apply clojure.test/run-tests namespaces)]
  (System/exit (if (zero? (+ (:fail result) (:error result))) 0 1)))'
