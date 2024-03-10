(ns aoc-21.utils-test
  (:require [clojure.test :refer [deftest is]]
            [utils]))

(def board '((1 2 3)
             (4 5 6)
             (7 8 9)))

(deftest mapmap-test
  (is (= (utils/mapmap inc board) '((2 3 4) (5 6 7) (8 9 10))))
  )

(deftest tranpose-test
  (is (= (utils/transpose board) '((1 4 7) (2 5 8) ( 3 6 9))))
  )

