package com.example.chessproject;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


import java.util.HashMap;
import java.util.Map;

public class ChessView extends View {

    public String piecesData;
    private final Map<Integer, Bitmap> bitmaps = new HashMap<>();
    private final Paint painter;
    private final int darkColor;
    private final int lightColor;
    private float originX = 20f;
    private float originY = 200f;
    private float cellSize = 130f;
    private final int[] piecesIDs = new int[] {
                R.drawable.blac_knight,
                R.drawable.black_king,
                R.drawable.black_bishop,
                R.drawable.black_pawn,
                R.drawable.black_queen,
                R.drawable.black_rook,
                R.drawable.white_bishop,
                R.drawable.white_knight,
                R.drawable.white_king,
                R.drawable.white_pawn,
                R.drawable.white_queen,
                R.drawable.white_rook
    };


    public ChessView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        painter = new Paint();
        darkColor = Color.parseColor("#BBBBBB");
        lightColor = Color.parseColor("#ffcb77");
        loadBitmaps();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawChessBoard(canvas);
        drawPieces(canvas);
    }

    private void loadBitmaps() {
        for (int pieceID : piecesIDs) {
            bitmaps.put(pieceID, BitmapFactory.decodeResource(getResources(),
                    pieceID));
        }
    }

    private void drawChessBoard(Canvas canvas) {
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                drawSquareAt(canvas, row, col, (row + col) % 2 == 1);
            }
        }
    }

    private void drawPieces(Canvas canvas) {
        int row = 7, col = 0;
        for (int i = 0; i < piecesData.length(); ++i) {
            switch (piecesData.charAt(i)) {
                case 'r' :
                    drawPieceAt(canvas, row, col, R.drawable.black_rook);
                    break;
                case 'q' :
                    drawPieceAt(canvas, row, col, R.drawable.black_queen);
                    break;
                case 'p' :
                    drawPieceAt(canvas, row, col, R.drawable.black_pawn);
                    break;
                case 'k' :
                    drawPieceAt(canvas, row, col, R.drawable.black_king);
                    break;
                case 'b' :
                    drawPieceAt(canvas, row, col, R.drawable.black_bishop);
                    break;
                case 'n' :
                    drawPieceAt(canvas, row, col, R.drawable.blac_knight);
                    break;
                case 'R' :
                    drawPieceAt(canvas, row, col, R.drawable.white_rook);
                    break;
                case 'Q' :
                    drawPieceAt(canvas, row, col, R.drawable.white_queen);
                    break;
                case 'P' :
                    drawPieceAt(canvas, row, col, R.drawable.white_pawn);
                    break;
                case 'K' :
                    drawPieceAt(canvas, row, col, R.drawable.white_king);
                    break;
                case 'B' :
                    drawPieceAt(canvas, row, col, R.drawable.white_bishop);
                    break;
                case 'N' :
                    drawPieceAt(canvas, row, col, R.drawable.white_knight);
                    break;
                case  '/' :
                    --row;
                    col = -1;
                    break;
                default:
                    if (Character.isDigit(piecesData.charAt(i))) {
                        col += (piecesData.charAt(i) -'1');
                    }
                    break;
            };
            ++col;
        }
    }

    private void drawSquareAt(Canvas canvas, int row, int col, boolean isDark) {
        painter.setColor(isDark ? darkColor : lightColor);
        canvas.drawRect(originX + col * cellSize, originY + row * cellSize,
                originX + (col + 1) * cellSize, originY + (row + 1) * cellSize, painter);

    }

    private void drawPieceAt(Canvas canvas, int row, int col, int pieceID) {
        canvas.drawBitmap(bitmaps.get(pieceID), null, new RectF(originX + col * cellSize,
                originY + (7 - row) * cellSize, originX + (col + 1) * cellSize, originY + (8 - row) * cellSize), painter);
    }

}
