(ns aoc-21.d3
    (:require [clojure.string :as str]
              [utils]))

(def example 
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

(defn process-input [s]
  (->> s
       (str/split-lines )
       (map seq)
       (map (fn [s] (map #(- (int %) 48) s)))))

(defn counter [coll]
 (reduce #(conj %1 [%2 (inc (get %1 %2 0))]) {} coll) )

(defn most-common [& args]
(let [c (counter args)]
;;   (prn args)
;;   (prn c)
  (key (apply max-key val c))))

(defn complement-bin [bin]
    (map #(mod (inc %) 2) bin)
  )

(defn bin-to-int [bin]
  (reduce #(+ (* 2 %1) %2) bin))

(defn gamma [nums]
  (apply map most-common nums))

(defn epsilon [nums]
  (complement-bin (gamma nums)))

(defn p1 [in]
  (let [nums (process-input in)
        gamma-val (bin-to-int (gamma nums))
        epsilon-val (bin-to-int (epsilon nums))
        ] 
    (prn gamma-val epsilon-val)
    (* gamma-val epsilon-val)))

(defn oxygen-critera [col]
  (let [len (count col)
        c (counter col)]
    (if (>= (get c 1 0) (/ len 2)) 1 0)))

(defn co2-critera [col]
  (let [len (count col)
        c (counter col)]
    (if (<= (get c 0 0) (/ len 2)) 0 1)))

(defn filter-criteria [nums criteria i]
  (let [col ( map #(nth % i) nums )
        d (criteria col)]
    (filter #(= (nth % i) d) nums)))

(defn filter-nums [nums criteria]
  (let [idxs (range (count (first nums)))
        p (reductions #(filter-criteria %1 criteria %2) nums idxs)]
    (first (last (filter (complement empty?) p))))
  )

(defn p2 [in]
  (let [nums (process-input in)
        oxy (filter-nums nums oxygen-critera)
        oxy (bin-to-int oxy)
        co2 (filter-nums nums co2-critera)
        co2 (bin-to-int co2)]
    (prn oxy co2)
    (* oxy co2)))

(comment
  (def tmp (process-input example))
  tmp
  (apply map prn tmp)
  (def d {})
  (conj d [5 6])
  (get d 0 0)
  (key (apply max-key val {:a 3 :b 7 :c 9}))
  (apply max-key val {:a 3 :b 7 :c 9})
  (int \0)
  (gamma tmp)
  (bin-to-int (gamma tmp))
  (p1 (utils/get-problem-input 3))
  (p1 example)
  tmp
  (reductions #(filter-criteria %1 co2-critera %2) tmp (range (count (first tmp))))
  (p2 (utils/get-problem-input 3))
  (fr)
  )
