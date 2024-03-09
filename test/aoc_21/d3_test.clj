(ns aoc-21.d3-test
  (:require [clojure.test :refer [deftest is]]
            [aoc-21.d3 :refer [p1 p2]]))

(def fixture
  "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010")


(deftest p1-test
  (is (= (p1 fixture) 198)))

(deftest p2-test
  (is (= (p2 fixture) 230)))