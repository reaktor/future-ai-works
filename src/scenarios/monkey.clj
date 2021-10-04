(ns scenarios.monkey
    (:require [gps.operation :as op]))

(def +monkey-state+ #{:at-door
                      :on-floor
                      :has-ball
                      :hungry
                      :chair-at-door})

(def +monkey-goal+ #{:not-hungry})

(def +monkey-ops+
  (->> [{:action   :climb-on-chair
         :preconds #{:chair-at-middle-room :at-middle-room :on-floor}
         :add-list #{:at-bananas :on-chair}
         :del-list #{:at-middle-room :on-floor}}
        {:action   :push-chair-from-door-to-middle-room
         :preconds #{:chair-at-door :at-door}
         :add-list #{:chair-at-middle-room :at-middle-room}
         :del-list #{:chair-at-door :at-door}}
        {:action   :walk-from-door-to-middle-room
         :preconds #{:at-door :on-floor}
         :add-list #{:at-middle-room}
         :del-list #{:at-door}}
        {:action   :grasp-bananas
         :preconds #{:at-bananas :empty-handed}
         :add-list #{:has-bananas}
         :del-list #{:empty-handed}}
        {:action   :drop-ball
         :preconds #{:has-ball}
         :add-list #{:empty-handed}
         :del-list #{:has-ball}}
        {:action   :eat-bananas
         :preconds #{:has-bananas}
         :add-list #{:empty-handed :not-hungry}
         :del-list #{:has-bananas :hungry}}]
       (map op/map->Op)))
