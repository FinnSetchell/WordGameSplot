package com.finndog.splot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Lexicon {
    public Lexicon() {
        //load in lexicon file
        HashMap<String, String> m_lexicon = new HashMap<>();
        try {
            for (String line : Files.readAllLines(Paths.get("data/dictionaries/CSW22.txt"))) { //System.IO.File.ReadLines(@".\CSW22.txt")
                m_lexicon.put(line.toLowerCase(), "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean IsWord(String word)
    {
        return m_lexicon.containsKey(word.toLowerCase());
    }

    HashMap<String, String> m_lexicon;
}
