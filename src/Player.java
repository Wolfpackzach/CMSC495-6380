/*
 * File Name:		Player.java
 * Class:		CMSC 495-6380
 * Group:		Group 6
 * Authors:		Will Krohn, Stephen Parsons
 * Date: 		9/29/2018
 * Purpose:  This document is responsible for the Player java class for group 6's project to predict wins for a season of a 1v1 sport. Contains name, rank, and win/loss count.
 */

import java.util.*;

public class Player {
  String name;
  int winCount, lossCount, rank;

  Player(Scanner sc) {
    if (sc.hasNext()) {
      name = sc.next();
    }
    if (sc.hasNext()) {
      rank = sc.nextInt();
    }
  }

  int getRank() {
    return rank;
  }

  void setRank(int rank) {
    this.rank = rank;
  }

  void setWinCount(int winCount) {
    this.winCount = winCount;
  }

  void setLossCount(int lossCount) {
    this.lossCount = lossCount;
  }

  int getwinCount() {
    return winCount;
  }

  int getlossCount() {
    return lossCount;
  }

  void resetwinCountlossCount() {
    winCount = 0;
    lossCount = 0;
  }

  String getName() {
    return name;
  }

  void setName(String newName) {
    newName = name;
  }
}
