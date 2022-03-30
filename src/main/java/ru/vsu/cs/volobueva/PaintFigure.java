package ru.vsu.cs.volobueva;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PaintFigure {

    public double square(int[] arrayX, int[] arrayY) {
        double s = 0;

        for (int i = 0; i < arrayX.length - 1; i++) {
            s += 0.5 * (arrayX[i] * arrayY[i + 1] - arrayX[i + 1] * arrayY[i]);
        }
        return s;
    }

    public double perimeter(int[] arrayX, int[] arrayY) {
        double p = 0;

        for (int i = 0; i < arrayX.length; i++) {
            if (i == arrayX.length - 1) {
                p += length(arrayX[i], arrayX[0], arrayY[i], arrayY[0]);
            } else {
                p += length(arrayX[i], arrayX[i + 1], arrayY[i], arrayY[i + 1]);
            }
        }
        return p;
    }

    public void scaling(int[] a, int k, int b) {
        for (int i = 1; i < a.length; i++) {
            a[i] = scaling(a[i], k, b);
        }
    }

    private int scaling(int a, int k, int b) {
        a = (a - b) * k + b;
        return a;
    }


    public double length(int x1, int x2, int y1, int y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    public void moving(int[] a, int move) {

        for (int i = 0; i < a.length; i++) {
            a[i] += 50 * move;
        }
    }

    public int findMiddle (int [] a) {
        int sum = 0;
        for (int j : a) {
            sum += j;
        }
        return sum/ 5;
    }


    public Rectangle rectangle(Polygon poly) {
        Rectangle rect = new Rectangle();
        rect.x = minFromArray(poly.xpoints);
        rect.y = minFromArray(poly.ypoints);
        rect.width = maxFromArray(poly.xpoints) - minFromArray(poly.xpoints);
        rect.height = maxFromArray(poly.ypoints) - minFromArray(poly.ypoints);
        return rect;
    }


    public int minFromArray(int[] a) {
        int min = a[0];
        for (int j : a) {
            if (j < min) {
                min = j;
            }
        }

        return min;
    }

    public int maxFromArray(int[] a) {
        int max = a[0];
        for (int j : a) {
            if (j > max) {
                max = j;
            }
        }

        return max;
    }

    public int indexMin(int[] a) {
        int min = a[0];
        int iMin = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
                iMin = i;
            }
        }

        return iMin;
    }

    public int indexMax(int[] a) {
        int max = a[0];
        int iMax = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < max) {
                max = a[i];
                iMax = i;
            }
        }

        return iMax;
    }

    public void beginX(int[] a) {
        a[0] = 260;
        a[1] = 360;
        a[2] = 420;
        a[3] = 320;
        a[4] = 240;
    }

    public void beginY(int[] a) {
        a[0] = 180;
        a[1] = 140;
        a[2] = 220;
        a[3] = 300;
        a[4] = 280;
    }

    public void saveDrawing(String fileName, BufferedImage drawing) throws IOException {
        File file = new File(fileName);
        ImageIO.write(drawing, "png", file);
    }
}
