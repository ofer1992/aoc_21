(ns utils)

(defn get-problem-input [n]
  (slurp (str  "inputs/" n ".txt")))

(defn mapmap [f xxs]
  (for [xs xxs]
    (map f xs)))

(defn transpose [xxs]
  (apply map list xxs))

