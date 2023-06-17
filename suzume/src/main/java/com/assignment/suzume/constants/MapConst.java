package com.assignment.suzume.constants;

public enum MapConst {
    OBSTACLE(1),
    STATION(2),
    DESTINATION(3);
    
    public final int VALUE;

    MapConst(int val) {
        this.VALUE = val;
    }
    public boolean is(int val) {
        return this.VALUE == val;
    }
}
