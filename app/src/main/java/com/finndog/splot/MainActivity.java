package com.finndog.splot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DrawGrid drawGrid;
    Canvas LetterGrid = new Canvas();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m_IsDragging = false;
        m_DragStartIndex = new Point(-1, -1);
        m_HoverIndex = new Point(-1, -1);


        m_game = new WordGame();
        m_game.Reset(1, m_lexicon);

//        setContentView(R.layout.activity_main);
        drawGrid = new DrawGrid(this);
        drawGrid.setBackgroundColor(Color.argb(1, 236, 243, 246));
        setContentView(drawGrid);

//        drawGrid.DisplayGameOver(GameCompleteOverlay, m_game, PlayedScore, WonPercentageScore, CurrentStreakScore, MaxStreakScore, SwapCountScore, WordCountScore, WonOrLostDisplay);

//        WordCount.Text = m_game.GetFoundWordCount().ToString();
//        Words.Text = string.Join("\n",m_game.GetFoundWords());
//        SwapCounter.Text = m_game.GetSwapCounter().ToString();
    }

//    private void LetterGrid_MouseDown(object sender, MouseButtonEventArgs e)
//    {
//        Point index = drawGrid.ConvertPosToGridIndex(e.GetPosition(LetterGrid));
//
//        m_IsDragging = true;
//        m_DragStartIndex = index;
//    }

//    private void LetterGrid_MouseUp(object sender, MouseButtonEventArgs e)
//    {
//        Point index = drawGrid.ConvertPosToGridIndex(e.GetPosition(LetterGrid));
//
//        if (m_IsDragging)
//        {
//            m_game.Swap((int)m_DragStartIndex.X, (int)m_DragStartIndex.Y, (int)index.X, (int)index.Y);
//            m_IsDragging = false;
//            m_DragStartIndex = new Point(-1, -1);
//
//            Words.Text = string.Join("\n", m_game.GetFoundWords());
//            drawGrid.DrawRectangles(LetterGrid, GameCompleteOverlay, m_game, m_HoverIndex, m_DragStartIndex);
//            drawGrid.DisplayGameOver(GameCompleteOverlay, m_game, PlayedScore, WonPercentageScore, CurrentStreakScore, MaxStreakScore, SwapCountScore, WordCountScore, WonOrLostDisplay);
//            WordCount.Text = m_game.GetFoundWordCount().ToString();
//            SwapCounter.Text = (m_game.GetSwapCounter().ToString() + "/∞");
//            //m_game.IsIndexInAFoundWord(m_HoverIndex);
//        }
//    }


//    private void LetterGrid_MouseMove(object sender, MouseEventArgs e)
//    {
//        Point index = drawGrid.ConvertPosToGridIndex(e.GetPosition(LetterGrid));
//
//        if (m_HoverIndex != index && m_game.IsGameComplete() == WordGame.GameCompleteState.NOT_COMPLETE)
//        {
//            m_HoverIndex = index;
//
//            drawGrid.DrawRectangles(LetterGrid, GameCompleteOverlay, m_game, m_HoverIndex, m_DragStartIndex);
//        }
//    }

//    private void Reset_Button(object sender, RoutedEventArgs e)
//    {
//        if (InputMaxSwaps.Text != "")
//        {
//            m_game.SetMaxSwaps(int.Parse(InputMaxSwaps.Text));
//        }
//        m_game.Reset(GridSize.SelectedIndex);
//
//        drawGrid.DrawRectangles(LetterGrid, GameCompleteOverlay, m_game, m_HoverIndex, m_DragStartIndex);
//
//        WordCount.Text = m_game.GetFoundWordCount().ToString();
//        Words.Text = string.Join("\n", m_game.GetFoundWords());
//        SwapCounter.Text = (m_game.GetSwapCounter().ToString() + "/∞");
//        GameCompleteOverlay.Visibility = Visibility.Hidden;
//    }

//    private void LetterGrid_Background_MouseDown(object sender, RoutedEventArgs e)
//    {
//        if (GameCompleteOverlay.Visibility == Visibility.Visible)
//        {
//            GameCompleteOverlay.Visibility = Visibility.Hidden;
//        }
//    }

        /*public void ScoreUpdater(int PlayedCount, int WonPercentage, int CurrentStreak, int MaxStreak, int SwapCountScore, int WordCountScore)
        {
            SwapCountScore.text = m_game.GetSwapCounter().ToString();
            WordCountScore = m_game.GetFoundWordCount();

        }*/

    public HashMap<String, String> LexiconActivity() {
        m_lexicon = new HashMap<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("data/dictionaries/ScrabbleEnglish.txt"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String line;
            while ((line = reader.readLine()) != null) {
                //process line
                m_lexicon.put(line.toLowerCase(), "");
            }
        } catch (IOException e) {
            //log the exception
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                    e.printStackTrace();
                }
            }
        }

        return m_lexicon;
    }

    HashMap<String, String> m_lexicon;
    WordGame m_game;
    Point m_DragStartIndex;
    boolean m_IsDragging;
    Point m_HoverIndex;
}