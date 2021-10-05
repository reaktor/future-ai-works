(ns gps.solver
  (:require [scenarios.school :as school]
            [scenarios.monkey :as monkey]
            [clojure.set :refer [union difference]]))

(declare solve)

(defn apply-op
  [world op]
  (let [adds (:add-list op)
        dels (:del-list op)
        action (:action op)
        preconds (:preconds op)]
    (some-> world
            (solve preconds)
            (update :plan (fn [plan] (conj plan action)))
            (update
              :state
              (fn [state] (-> state
                              (union adds)
                              (difference dels)))))))
                                     
(defn applicable?
  [goal op]
  (let [adds (:add-list op)]
    (get adds goal)))

(defn achieve
  [world goal]
  (let [state (:state world)
        ops (:ops world)]
    (if (get state goal)
      world
      (let [potential (filter (fn [op] (applicable? goal op)) ops)]
        (some (fn [op] (apply-op world op)) potential)))))

(defn solve
  [world goals]
  (reduce (fn [world' goal] (achieve world' goal)) world goals))

(-> {:state school/+school-state+
      :ops school/+school-ops+
      :plan []}
    (solve school/+school-goal+)
    :plan)


(-> {:state monkey/+monkey-state+
      :ops monkey/+monkey-ops+
      :plan []}
    (solve monkey/+monkey-goal+)
    :plan)

