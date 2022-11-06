package com.finndog.splot;

import android.graphics.Point;

import java.util.List;

public class FoundWord {
    String word;
    List<Point> indexes;

    FoundWord(String a_word, List<Point> a_indexes){
        word = a_word;
        indexes = a_indexes;
    }
}
