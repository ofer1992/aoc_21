(ns aoc-21.d1
  (:require [clojure.string :as str]
            [utils]))


(defn process-input [s]
  (let [splitted (str/split s #"\n")]
    (map #(Integer/parseInt %) splitted)))

(defn count-increase [s]
  (count (filter identity (map < s (drop 1 s)))))

(defn p1 [in] (count-increase (process-input in)))

(defn p2 [in] 
  (let [x (process-input in)
        slided (map #(apply + %) (partition 3 1 x))]
    (count-increase slided)))

(defn solve [& args]
  (let [in (utils/get-problem-input 1)
        part1 (p1 in)
        part2 (p2 in)]
    (println (str "part 1 solution: " part1))
    (println (str "part 2 solution: " part2))))

(comment
  (def example
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
  (p2 (utils/get-problem-input 1))
  (def x (process-input example))
  (map #(apply + %) (partition 3 1 x))

  :rcf)