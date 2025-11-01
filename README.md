# FIT2099 Assignment (Semester 2, 2025)
```                                                                             
`7MMF'     A     `7MF'`7MMF'`7MN.   `7MF'MMP""MM""YMM `7MM"""YMM  `7MM"""Mq.  
  `MA     ,MA     ,V    MM    MMN.    M  P'   MM   `7   MM    `7    MM   `MM. 
   VM:   ,VVM:   ,V     MM    M YMb   M       MM        MM   d      MM   ,M9  
    MM.  M' MM.  M'     MM    M  `MN. M       MM        MMmmMM      MMmmdM9   
    `MM A'  `MM A'      MM    M   `MM.M       MM        MM   Y  ,   MM  YM.   
     :MM;    :MM;       MM    M     YMM       MM        MM     ,M   MM   `Mb. 
      VF      VF      .JMML..JML.    YM     .JMML.    .JMMmmmmMMM .JMML. .JMM.
```

Contribution Log Link: https://docs.google.com/spreadsheets/d/1oX6jqnUVR0a0dpvEsl9jClG-8xcurkCpfvVJvy_VQuA/edit?usp=sharing

A3 (Req 3) scenario:
  In this requirement there will be 3 items which can be wearable. 
  This item call Armor, it has 3 type of Armor, leather Armor which can help to block damage of 2 hits and have cold resistance
  , iron Armor block 5 hits,
  diamond Armor 10 hits and have immune to all status effect, when actor who has wear on it attacked by another actor.

  In addition, when an actor wearing any type of Armor successfully blocks more damage than received (that is, the Armor’s block 
  value exceeds the incoming damage), the excess block amount will be converted into healing, allowing the actor to recover 
  hitpoints equal to the remaining block value.

  An actor only can have one Armor at a time and it is not destroyable

A4 (Req 4) scenario:
  In this requirement when actor defeat animal it will drop different diamond.
  Diamond has, green diamond worth 5, blue diamond worth 10, red diamond worth 20.
  When actor collect the diamond inside his invenroty, the actor will has behaviour to combine diamond,
  2 green diamond merge to 1 blue diamond and 2 blue diamond merge to 1 red diamond.

A3 (Req 5) scenario:
  The Mysterio Store will spawn if the floor contains Dimensional Ground. Dimensional Ground is created when a Dimensional Bottle is 
  thrown onto the floor. When the bottle breaks, the place of the choosen locatiobn (where the bottle throw to)
  normal ground are consumed and transformed into Dimensional Ground. After 3 turns, the Dimensional Ground will cause a Mysterio 
  Store to appear. The store remains active for 3 turns before disappearing. Inside the Mysterio Store the actor can enter the store 
  once it appears. Inside the store there have a seller (sationary seller) and sell anything (custom) including the armor 
  that when actor buy it will directly apply into actor (equivalent to wear it) and if actor originally has an armor it will replace 
  the old one. From the store actor can go back to original map with a random chosen location within the original map.

  Additional information: the dimensional bottle when smash onto the ground will turn the ground into dimensional ground, this ground
  will randomly spwan one of three diffenrent type of mysterio store, each type of store offers different items and has its own unique 
  seller. The thing sell in store is depend on what the seller sell.

  The system only will have 3 different store u can decide what to sell in it but one of the store must sell armor. There won't be
  any other store except this 3 in the system. Hint: U may store the type of the store inside the dimensional ground.
