package com.assignment.suzume.connecting.game;

import com.assignment.suzume.tictactoe.board.rules.Rule;

public class GameConstant {
    public static final int WIN = 1;
    public static final int LOSE = -1;
    public static final int DRAW = 0;

    public static final int PVP = 1;
    public static final int PVE = 2;
    public static final int EVE = 3;

    public static final Rule[] RULES = { Rule.REGULAR, Rule.REVERSE, Rule.VARIANT};
    

}