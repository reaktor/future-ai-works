(ns scenarios.school
  (:require [gps.operation :as op]))

(def +school-state+ #{:son-at-home
                      :car-needs-battery
                      :have-money
                      :have-phone-book})

(def +school-goal+ #{:son-at-school})

(def +school-ops+
  (->> [{:action   :drive-son-to-school
         :preconds #{:son-at-home :car-works}
         :add-list #{:son-at-school}
         :del-list #{:son-at-home}}
        {:action   :shop-installs-battery
         :preconds #{:car-needs-battery :shop-knows-problem :shop-has-money}
         :add-list #{:car-works}
         :del-list #{}}
        {:action   :tell-shop-problem
         :preconds #{:in-communication-with-shop}
         :add-list #{:shop-knows-problem}
         :del-list #{}}
        {:action   :telephone-shop
         :preconds #{:know-phone-number}
         :add-list #{:in-communication-with-shop}
         :del-list #{}}
        {:action   :look-up-number
         :preconds #{:have-phone-book}
         :add-list #{:know-phone-number}
         :del-list #{}}
        {:action   :give-shop-money
         :preconds #{:have-money}
         :add-list #{:shop-has-money}
         :del-list #{:have-money}}]
       (map op/map->Op)))

