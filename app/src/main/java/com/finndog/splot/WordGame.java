package com.finndog.splot;

import android.content.Context;
import android.graphics.Point;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class WordGame {

    public WordGame()
    {
        m_MaxSwaps = 0;
    }





    public void Reset(int difficulty, HashMap<String, String> lexicon)
    {
//        m_state = GameCompleteState.NOT_COMPLETE;
        int MaxNumWords;

        HashMap<Character, Integer> letters = new HashMap<>();
        letters.clear();
        switch (difficulty)
        {
            case 0:
                m_gridSize = 3;
                m_allowDiagonals = true;
                m_minWordLength = 3;
                MaxNumWords = 1;
                letters.put('a', 9);
                letters.put('b', 2);
                letters.put('c', 2);
                letters.put('d', 4);
                letters.put('e', 1);
                letters.put('f', 2);
                letters.put('g', 3);
                letters.put('h', 2);
                letters.put('i', 9);
                letters.put('j', 1);
                letters.put('k', 1);
                letters.put('l', 4);
                letters.put('m', 2);
                letters.put('n', 6);
                letters.put('o', 8);
                letters.put('p', 2);
                letters.put('q', 1);
                letters.put('r', 6);
                letters.put('s', 4);
                letters.put('t', 6);
                letters.put('u', 4);
                letters.put('v', 2);
                letters.put('w', 2);
                letters.put('x', 1);
                letters.put('y', 2);
                letters.put('z', 1);
                break;
            case 1:
                m_gridSize = 4;
                m_allowDiagonals = false;
                m_minWordLength = 3;
                MaxNumWords = 2;
                letters.put('a', 9);
                letters.put('b', 2);
                letters.put('c', 2);
                letters.put('d', 4);
                letters.put('e', 1);
                letters.put('f', 2);
                letters.put('g', 3);
                letters.put('h', 2);
                letters.put('i', 9);
                letters.put('j', 1);
                letters.put('k', 1);
                letters.put('l', 4);
                letters.put('m', 2);
                letters.put('n', 6);
                letters.put('o', 8);
                letters.put('p', 2);
                letters.put('q', 1);
                letters.put('r', 6);
                letters.put('s', 4);
                letters.put('t', 6);
                letters.put('u', 4);
                letters.put('v', 2);
                letters.put('w', 2);
                letters.put('x', 1);
                letters.put('y', 2);
                letters.put('z', 1);
                break;
            case 2:
            default:
                m_gridSize = 5;
                m_allowDiagonals = false;
                m_minWordLength = 4;
                MaxNumWords = 3;
                letters.put('a', 9);
                letters.put('b', 2);
                letters.put('c', 2);
                letters.put('d', 4);
                letters.put('e', 1);
                letters.put('f', 2);
                letters.put('g', 3);
                letters.put('h', 2);
                letters.put('i', 9);
                letters.put('j', 1);
                letters.put('k', 1);
                letters.put('l', 4);
                letters.put('m', 2);
                letters.put('n', 6);
                letters.put('o', 8);
                letters.put('p', 2);
                letters.put('q', 1);
                letters.put('r', 6);
                letters.put('s', 4);
                letters.put('t', 6);
                letters.put('u', 4);
                letters.put('v', 2);
                letters.put('w', 2);
                letters.put('x', 1);
                letters.put('y', 2);
                letters.put('z', 1);
                break;
        }

        m_swapCounter = 0;
        m_grid = new char[m_gridSize][m_gridSize];
        Random random = new Random();

        ArrayList<Character> allLetters = new ArrayList<>();
        for(char key : letters.keySet()) {
            for (int i = 0; i < letters.get(key); i++) allLetters.add(key);
        }
        do {
            for (int i = 0; i < m_gridSize; i++) {
                for (int j = 0; j < m_gridSize; j++) {
                    m_grid[i][j] = allLetters.get(random.nextInt(allLetters.size()));
                }
            }
            FindWords(lexicon);
        } while (m_foundWords.size() != MaxNumWords);
    }

    public String RenderToString()
    {
        String gridText = "";
        for (int j = 0; j < m_gridSize; j++)
        {
            for (int i = 0; i < m_gridSize; i++)
            {
                gridText += " | " + m_grid[i][j];
                if (i == m_gridSize - 1)
                {
                    gridText += '\n';
                }
            }
        }
        return gridText;
    }

    private void FindWords(HashMap<String, String> lexicon) {
        ArrayList<FoundWord> words = new ArrayList<>();
        int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
        int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

        //create structure holding all of the indexes. For each index, store all of the words that contain it

        for (int j = 0; j < m_gridSize; j++)
        {
            for (int i = 0; i < m_gridSize; i++)
            {
                for (int dir = 0; dir < 8; dir += (m_allowDiagonals ? 1 : 2))
                {
                    StringBuilder word = new StringBuilder();
                    ArrayList<Point> indexes = new ArrayList<>();
                    int i1 = i, j1 = j;
                    while (i1 >= 0 & j1 >= 0 & i1 < m_gridSize & j1 < m_gridSize)
                    {
                        word.append(m_grid[i1][j1]);
                        indexes.add(new Point(i1, j1));

                        if (word.length() >= m_minWordLength & IsWord(lexicon, word.toString()))
                        {
                            FoundWord foundWord = new FoundWord(word.toString(), indexes);
//                            foundWord.word = word.toString();
//                            foundWord.indexes = new ArrayList<Point>(indexes);
                            words.add(foundWord);

                        }
                        i1 += dx[dir];
                        j1 += dy[dir];
                    }

                }
            }
        }
        m_foundWords = words;
    }

    public boolean IsWord(HashMap<String, String> lexicon, String word) {
        return lexicon.containsKey(word.toLowerCase());
    }

//    public void Swap(int i1, int j1, int i2, int j2)
//    {
//        if ((i1 == i2) & (j1 == j2)) return;
//        if (m_state != GameCompleteState.NOT_COMPLETE) return;
//
//        char temp;
//
//        temp = m_grid[i1][j1];
//        m_grid[i1][j1] = m_grid[i2][j2];
//        m_grid[i2][j2] = temp;
//
//        m_swapCounter++;
//        FindWords();
//        UpdateGameState();
//    }

//    private void UpdateGameState()
//    {
//
//        // check if game is won
//        boolean win = true;
//        //loop through all i & j, if not, return false. if is, return true
//        for (int i = 0; i < m_gridSize; i++)
//        {
//            for (int j = 0; j < m_gridSize; j++)
//            {
//                if (!IsIndexInAFoundWord(i, j))
//                {
//                    win = false;
//                }
//            }
//        }
//
//        if (win)
//        {
//            m_state = GameCompleteState.COMPLETE_WIN;
//            UpdateGameOverScores();
//        }
//        else if (m_MaxSwaps > 0 && m_swapCounter >= m_MaxSwaps)
//        {
//            m_state = GameCompleteState.COMPLETE_LOSE;
//            UpdateGameOverScores();
//        }
//        else m_state = GameCompleteState.NOT_COMPLETE;
//    }

    public int GetSwapCounter()
    {
        return m_swapCounter;
    }
    public int GetFoundWordCount()
    {
        return m_foundWords.size();
    }

    /*public List<String> GetFoundWords()
    {
        var words = new List<String>();

        foreach(FoundWord word in m_foundWords)
        {
            words.Add(word.word);
        }
        return words;
    }*/

    /*public boolean IsIndexInAFoundWord(int i, int j)
    {
        var p = new Point(i, j);

        foreach(FoundWord word in m_foundWords)
        {
            foreach(Point index in word.indexes)
            {
                if (index == p) return true;
            }
        }

        return false;
    }*/

    /*private void UpdateGameOverScores()
    {
        m_playedScore += 1;

        if (m_state == GameCompleteState.COMPLETE_WIN)
        {
            m_WonOrLostDisplay = "You Won!";
            m_wonTotal += 1;
            m_currentStreakScore += 1;
            if (m_currentStreakScore > m_maxStreakScore)
            {
                m_maxStreakScore = m_currentStreakScore;
            }

        } else if (m_state == GameCompleteState.COMPLETE_LOSE)
        {
            m_WonOrLostDisplay = "You Lost :(";
            m_lostTotal += 1;
            m_currentStreakScore = 0;
        }
        m_wonPercentageScore = m_wonTotal / m_playedScore * 100;
    }*/



    public enum GameCompleteState { NOT_COMPLETE, COMPLETE_WIN, COMPLETE_LOSE };

//    public GameCompleteState IsGameComplete()
//    {
//        return m_state;
//    }


    private int m_MaxSwaps;
    public int GetMaxSwaps()
    {
        return m_MaxSwaps;
    }
    public void SetMaxSwaps(int maxSwaps)
    {
        m_MaxSwaps = maxSwaps;
    }
    private static int m_gridSize;
    public static int GetGridSize() {return m_gridSize;}
    public static char GetGridChar(int i, int j) {return m_grid[i][j];}
    private static char[][] m_grid;
    private boolean m_allowDiagonals;
    private int m_minWordLength;
    private List<FoundWord> m_foundWords;
    private int m_swapCounter;
    private GameCompleteState m_state;

    private String m_WonOrLostDisplay;
    private int m_playedScore;
    private int m_wonPercentageScore;
    private int m_wonTotal;
    private int m_lostTotal;
    private int m_currentStreakScore;
    private int m_maxStreakScore;
    public String GetWonOrLostDisplay()
    {
        return m_WonOrLostDisplay;
    }
    public int GetPlayedScore() {
        return m_playedScore;
    }
    public int GetWonPercentageScore()
    {
        return m_wonPercentageScore;
    }
    public int GetCurrentStreakScore()
    {
        return m_currentStreakScore;
    }
    public int GetMaxStreakScore()
    {
        return m_maxStreakScore;
    }
    public int GetSwapCountScore()
    {
        return m_swapCounter;
    }
}
