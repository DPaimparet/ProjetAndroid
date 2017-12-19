package com.example.paimp.projet08.model;

/**
 * Created by Paimp on 18-12-17.
 */

public class MapJeu {
    private int[][] map;

    public MapJeu(int[][] map) {
        this.map = map;
    }
    public MapJeu() {}

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
