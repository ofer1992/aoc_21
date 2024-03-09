(ns aoc-21.d2
  (:require [clojure.string :as str]
            [utils]))

(defn process-input [s]
  (->> s
       (re-seq #"(\w+) (\d+)")
       (map rest)
       (map #(list (first %) (Integer/parseInt (second %))))))

(defn execute-instruction [[x depth] [inst n]]
  (case inst
    "forward" (list (+ x n) depth)
    "down" (list x (+ depth n))
    "up" (list x (- depth n))
    :error))

(defn execute-instruction2 [[x depth aim] [inst n]]
  (case inst
    "forward" (list (+ x n) (+ depth (* aim n)) aim)
    "down" (list x depth (+ aim n))
    "up" (list x depth (- aim n))
    :error))

(defn p1 [in]
  (->> in
       (process-input)
       (reduce execute-instruction '(0 0))
       (apply *)))

(defn p2 [in]
  (->> in
       (process-input)
       (reduce execute-instruction2 '(0 0 0))
       (take 2)
       (apply *)))

(comment
(def example
  "forward 5
down 5
forward 8
up 3
down 8
forward 2
")

    (p1 (utils/get-problem-input 2))
    (p2 (utils/get-problem-input 2))
    (p2 example)
    (reduce execute-instruction '(0 0) insts )

  (def insts (process-input example))
  )