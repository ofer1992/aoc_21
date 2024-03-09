(ns aoc-21.d1-test
  (:require [clojure.test :refer [is deftest]]
            [aoc-21.d1 :refer [p1 p2]]))

(def fixture
  "199
200
208
210
200
207
240
269
260
263
")

(deftest d1-test
  (is (= (p1 fixture) 7))
  (is (= (p2 fixture) 5)))