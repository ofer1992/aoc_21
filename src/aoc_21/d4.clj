(ns aoc-21.d4
  (:require [clojure.string :as str]
            [utils :refer [get-problem-input mapmap transpose]]))

(defn parse-board [s]
  (let [rows (str/split-lines  s)
        flattened (for [r rows x (map #(Integer/parseInt %) (str/split (str/trim r) #" +"))]
                    x)]
    (partition 5 flattened)))


(defn process-input [in]
  (let [[draws & boards] (str/split in #"\n\n")
        draws (map #(Integer/parseInt %) (str/split draws #","))
        boards (map parse-board (map str boards))]
    [draws boards]))

(defn mark-board [draws board]
    (mapmap draws board)
  )

(defn bingo? [marked-board]
  (let [t (transpose marked-board)
        bingo-rows (map #(every? (complement nil?) %) marked-board)
        bingo-cols (map #(every? (complement nil?) %) t)]
    (some identity (concat bingo-rows bingo-cols))
    )
)

(defn bingo-round [draws boards]
  (let [marked (map #(mark-board draws %) boards)
        enumerated (map list (range) marked)]
    (filter #(bingo? (second %)) enumerated))
  )

(defn score [board win-num mask]
  (let [unmarked (filter first (map list (flatten mask) (flatten board)))
        sum (apply + (map second unmarked))]
    (* sum win-num)))


(defn find-winners [draws boards]
  (let [s (map #(bingo-round (set (take % draws)) boards) (range 1 (count draws)))
        winner (first (drop-while #(empty? (second %)) (map list (range) s)))
        [n-idx [[idx win-board] & rst]] winner
        win-num (nth draws n-idx)
        mask (mapmap nil? win-board)]
    (score (nth boards idx) win-num mask)))

(defn p1 [in]
  (let [ [draws boards] (process-input in) ]
    (find-winners draws boards)))

(defn p2 [in]
  (let [[draws boards] (process-input in)
        s (map #(bingo-round (set (take % draws)) boards) (range 1 (count draws)))
        parse-win (fn [n-idx [& winners]] (apply hash-map (flatten (for [[idx _] winners] [idx n-idx]))))
        ds (map #(apply parse-win %) (map list (range) s))
        win-round-d (apply merge-with min ds)
        [ last-to-win win-round]  (apply max-key val win-round-d) 
        board (nth boards last-to-win)
        mask (mark-board (set (take (inc win-round) draws)) board )
        mask (mapmap nil? mask)
        win-num (nth draws win-round)
        
        ] 
    
    (score board win-num mask)
    ))


(comment
  (def example "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

22 13 17 11  0
 8  2 23  4 24
21  9 14 16  7
 6 10  3 18  5
 1 12 20 15 19

 3 15  0  2 22
 9 18 13 17  5
19  8  7 25 23
20 11 10 24  4
14 21 16 12  6

14 21 17 24  4
10 16 15  9 19
18  8 23 26 20
22 11 13  6  5
 2  0 12  3  7")
  (def tmp (process-input example))
  tmp
  (def draws (first tmp))
  (def boards (second tmp))
  boards
  (set draws)
  (mark-board (set (take 5 draws)) (first boards))
  (def b (first boards))
  (map (every (complement nil?)) b)
  (bingo? (mark-board (set (take 14 draws)) b))
  boards
  (map #(mark-board (set (take 4 draws)) %) boards)

  (bingo-round (set (take 2 draws)) boards)
  (p2 (get-problem-input 4))
  )
  
