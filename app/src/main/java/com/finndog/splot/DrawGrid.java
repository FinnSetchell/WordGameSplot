package com.finndog.splot;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class DrawGrid extends View {
    private float size;
    private float space;

    Paint textPaint;
    Paint fillPaint;
    Paint strokePaint;
    Random rand;
    WordGame game = new WordGame();
    int gridSize = game.GetGridSize();
    Point dragStartIndex = new Point(-1, -1);
    Point hoverIndex = new Point(-1, -1);

    public DrawGrid(Context context) {
        super(context);
        textPaint = new Paint();
        fillPaint = new Paint();
        strokePaint = new Paint();
        rand = new Random();
    }

    @Override
    protected void onDraw(Canvas LetterGrid) {
        super.onDraw(LetterGrid);
        space = 20;
        size = ((getWidth()-(space*gridSize+space))/gridSize);

        int i;
        for (int j = 0; j < gridSize; j++)
        {
            for (i = 0; i < gridSize; i++)
            {
                boolean IsHover = (hoverIndex.x == i && hoverIndex.y == j);
                boolean IsDragStart = (dragStartIndex.x == i && dragStartIndex.y == j);
//                boolean IsInWord = game.IsIndexInAFoundWord(i, j);
                boolean IsInWord = i % 2 == 0; //just makes it display true and false while IsIndexInFoundWord not implemented


                // fill
                fillPaint.setStyle(Paint.Style.FILL);
                fillPaint.setColor(IsInWord ? Color.GREEN : Color.GRAY);
                // stroke
                strokePaint.setStyle(Paint.Style.STROKE);
                strokePaint.setColor(Color.BLACK);
                strokePaint.setStrokeWidth((IsHover || IsDragStart) ? 12 : 8);

                int cornerRadius = (int) size / 5;
                LetterGrid.drawRoundRect(space, space, size, size, cornerRadius, cornerRadius, fillPaint);    // fill
                LetterGrid.drawRoundRect(space, space, size, size, cornerRadius, cornerRadius, strokePaint);  // stroke

//                LetterGrid.Children.Add(rectangle);


                textPaint.setTextSize((int)size-space);
                textPaint.setTextAlign(Paint.Align.CENTER);
                textPaint.setColor(Color.BLACK);
                LetterGrid.drawText(Character.toString(Character.toUpperCase(game.GetGridChar(i, j))), 80, 120, textPaint);
//                Canvas.SetLeft(textBlock, i * (size + space) + space / 2);
//                Canvas.SetTop(textBlock, j * (size + space) + space / 2);
//                LetterGrid.Children.Add(textBlock);
                LetterGrid.translate(size + space / 2, 0);
            }
            LetterGrid.translate(-(i*(size + space / 2)), size + space / 2);
        }
    }


    public void DrawRectangles(Canvas LetterGrid) {
                //need to clear grid before generating new one
        //LetterGrid.Children.Clear();

        for (int j = 0; j < game.GetGridSize(); j++)
        {
            for (int i = 0; i < game.GetGridSize(); i++)
            {
                boolean IsHover = (hoverIndex.x == i && hoverIndex.y == j);
                boolean IsDragStart = (dragStartIndex.x == i && dragStartIndex.y == j);
//                boolean IsInWord = game.IsIndexInAFoundWord(i, j);
                boolean IsInWord = i % 2 == 0; //just makes it display true and false while IsIndexInFoundWord not implemented

                Paint fillPaint = new Paint();
                Paint strokePaint = new Paint();
                RectF r = new RectF(size, size, size, size);

                // fill
                fillPaint.setStyle(Paint.Style.FILL);
                fillPaint.setColor(IsInWord ? Color.GREEN : Color.GRAY);
                // stroke
                strokePaint.setStyle(Paint.Style.STROKE);
                strokePaint.setColor(Color.BLACK);
                strokePaint.setStrokeWidth((IsHover || IsDragStart) ? 4 : 0);

                int cornerRadius = (int) size / 5;
                LetterGrid.drawRoundRect(r, cornerRadius, cornerRadius, fillPaint);    // fill
                LetterGrid.drawRoundRect(r, cornerRadius, cornerRadius, strokePaint);  // stroke


//                LetterGrid.Children.Add(rectangle);


                LetterGrid.translate(i * (size + space) + space / 2, j * (size + space) + space / 2);
//                Canvas.SetLeft(rectangle, i * (size + space) + space / 2);
//                Canvas.SetTop(rectangle, j * (size + space) + space / 2);


//                TextBlock textBlock = new TextBlock();
//                textBlock.Height = size;
//                textBlock.Width = size;
//                textBlock.TextAlignment = TextAlignment.Center;
//                textBlock.FontSize = 30;
//                textBlock.Text = game.GetGridChar(i, j).ToString().ToUpper();
//                textBlock.Foreground = new SolidColorBrush(Color.rgb(0, 0, 0));
//                Canvas.SetLeft(textBlock, i * (size + space) + space / 2);
//                Canvas.SetTop(textBlock, j * (size + space) + space / 2);
//                LetterGrid.Children.Add(textBlock);
            }
        }

//        if (game.IsGameComplete() != WordGame.GameCompleteState.NOT_COMPLETE)
//        {
//            string msg = "";
//            switch (game.IsGameComplete())
//            {
//                case WordGame.GameCompleteState.COMPLETE_WIN:
//                    GameCompleteOverlay.Visibility = Visibility.Visible;
//                    msg = "you win!";
//                    break;
//
//                case WordGame.GameCompleteState.COMPLETE_LOSE:
//                    GameCompleteOverlay.Visibility = Visibility.Visible;
//                    msg = "You ran out of swaps!";
//                    break;
//            }
//            //Specify if the player won or lost here!!!
//            Debug.Write(msg);
//            GameCompleteOverlay.Visibility = Visibility.Visible;
//        }
//        else
//            GameCompleteOverlay.Visibility = Visibility.Hidden;
    }

//    public void DisplayGameOver(Grid GameCompleteOverlay, WordGame game, TextBox PlayedScore, TextBox WonPercentageScore, TextBox CurrentStreakScore, TextBox MaxStreakScore, TextBox SwapCountScore, TextBox WordCountScore, TextBox WonOrLostDisplay)
//    {
//        WonOrLostDisplay.Text = game.GetWonOrLostDisplay();
//        PlayedScore.Text = game.GetPlayedScore().ToString();
//        WonPercentageScore.Text = game.GetWonPercentageScore().ToString();
//        CurrentStreakScore.Text = game.GetCurrentStreakScore().ToString();
//        MaxStreakScore.Text = game.GetMaxStreakScore().ToString();
//        if(game.GetMaxSwaps() == 0)
//        {
//            SwapCountScore.Text = game.GetSwapCountScore().ToString() + "/∞";
//        } else
//        {
//            SwapCountScore.Text = game.GetSwapCountScore().ToString() + "/" + game.GetMaxSwaps().ToString();
//        }
//        WordCountScore.Text = game.GetFoundWordCount().ToString();
//
//    }

//    public Point ConvertPosToGridIndex(Point pos)
//    {
//        Point index;
//
//        index.X = Math.Floor(pos.X / (space + size));
//        index.Y = Math.Floor(pos.Y / (space + size));
//
//        return index;
//    }
}
