(ns aoc-21.d2-test
   (:require [clojure.test :refer [is deftest]]
            [aoc-21.d2 :refer [p1 p2]]))


(def fixture
  "forward 5
down 5
forward 8
up 3
down 8
forward 2")

(deftest d2-test
  (is (= (p1 fixture) 150))
  (is (= (p2 fixture) 900)))
