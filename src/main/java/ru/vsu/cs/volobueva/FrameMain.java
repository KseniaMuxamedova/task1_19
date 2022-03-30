package ru.vsu.cs.volobueva;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Locale;
import java.util.stream.IntStream;


public class FrameMain extends JFrame {
    private JPanel panelMain;
    private JButton buttonDraw;
    private JLabel labelImg;
    private JButton saveButton;
    private JButton buttonSquare;
    private JTextArea textSquare;
    private JButton buttonPerimeter;
    private JTextArea textPerimeter;
    private JButton ButtonLeft;
    private JButton ButtonRight;
    private JButton ButtonDown;
    private JButton ButtonUp;
    private JButton ButtonRectangle;
    private JScrollPane img;
    private JButton buttonMirror;
    private JButton buttonDownVertical;
    private JButton buttonDownHorizontal;
    private JButton buttonTopVertical;
    private JButton buttonTopHorizontal;
    private JButton buttonMiddleVertical;
    private JButton buttonMiddleHorizontal;
    private JTextField textFieldDownVertical;
    private JTextField textFieldDownHorizontal;
    private JTextField textFieldTopVertical;
    private JTextField textFieldTopHorizontal;
    private JTextField textFieldMiddleVertical;
    private JTextField textFieldMiddleHorizontal;
    private JButton buttonClean;
    private final JFileChooser fileChooserSave;
    private final JMenuBar menuBarMain;
    private final JMenu menuLookAndFeel;


    public FrameMain() {
        PaintFigure figure = new PaintFigure();

        $$$setupUI$$$();
        this.setTitle("Task1");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();
        int[] arrayX =  new int[5];
        figure.beginX(arrayX);
        int[] arrayY =  new int[5];
        figure.beginY(arrayY);
        Polygon poly = new Polygon(arrayX, arrayY, 5);
        BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_BGR);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setBackground(Color.WHITE);

        fileChooserSave = new JFileChooser();
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Сохранить в файл", "svg");
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Сохранить");

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Многоугольник");

        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        buttonDraw.addActionListener(e -> {
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(poly);
            g2d.fillPolygon(poly);

            labelImg.setIcon(new ImageIcon(image));
        });

        buttonSquare.addActionListener(e -> {
            textSquare.setText(Double.toString(figure.square(arrayX, arrayY)));
        });

        buttonPerimeter.addActionListener(e -> {
            textPerimeter.setText(Double.toString(figure.perimeter(arrayX, arrayY)));
        });

        ButtonLeft.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            figure.moving(arrayX, -1);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        ButtonRight.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            figure.moving(arrayX, 1);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        ButtonDown.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            figure.moving(arrayY, 1);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        ButtonUp.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            figure.moving(arrayY, -1);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });


        ButtonRectangle.addActionListener(e -> {
            g2d.setColor(Color.YELLOW);
            Polygon polyMove = new Polygon(arrayX, arrayY, 5);
            Rectangle rect = figure.rectangle(polyMove);
            g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
            labelImg.repaint();
        });

        buttonMirror.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            IntStream.range(0, arrayX.length).forEach(i -> arrayX[i] = 480 - arrayX[i]);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        buttonDownVertical.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            int k = Integer.parseInt(textFieldDownVertical.getText());
            figure.scaling(arrayY, k, arrayY[figure.indexMin(arrayY)]);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        buttonDownHorizontal.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            int k = Integer.parseInt(textFieldDownVertical.getText());
            figure.scaling(arrayX, k, arrayX[figure.indexMin(arrayY)]);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        buttonTopVertical.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            int k = Integer.parseInt(textFieldDownVertical.getText());
            figure.scaling(arrayY, k, arrayY[figure.indexMax(arrayY)]);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        buttonTopHorizontal.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            int k = Integer.parseInt(textFieldDownVertical.getText());
            figure.scaling(arrayX, k, arrayX[figure.indexMax(arrayY)]);
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        buttonMiddleVertical.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            int k = Integer.parseInt(textFieldDownVertical.getText());
            figure.scaling(arrayY, k, figure.findMiddle(arrayY));
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        buttonMiddleHorizontal.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            int k = Integer.parseInt(textFieldDownVertical.getText());
            figure.scaling(arrayX, k, figure.findMiddle(arrayX));
            g2d.setColor(Color.WHITE);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
        });

        

        buttonClean.addActionListener(e -> {
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(arrayX, arrayY, 5);
            g2d.fillPolygon(arrayX, arrayY, 5);
            labelImg.repaint();
            figure.beginX(arrayX);
            figure.beginY(arrayY);
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        Icon drawingIcon = labelImg.getIcon();
                        BufferedImage image = new BufferedImage(drawingIcon.getIconWidth(), drawingIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                        Graphics2D g2d = image.createGraphics();

                        drawingIcon.paintIcon(null, g2d, 0, 0);

                        g2d.dispose();

                        String file = fileChooserSave.getSelectedFile().getPath();

                        if (!file.toLowerCase().endsWith(".png")) {
                            file += ".png";
                        }

                        figure.saveDrawing(file, image);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(4, 7, new Insets(10, 10, 10, 10), -1, -1));
        img = new JScrollPane();
        panelMain.add(img, new GridConstraints(2, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        labelImg = new JLabel();
        labelImg.setHorizontalAlignment(2);
        labelImg.setVerticalAlignment(1);
        img.setViewportView(labelImg);
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setText("Сохранить");
        panelMain.add(saveButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonDraw = new JButton();
        buttonDraw.setText("Нарисовать фигуру");
        panelMain.add(buttonDraw, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonSquare = new JButton();
        buttonSquare.setText("Найти площадь");
        panelMain.add(buttonSquare, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonPerimeter = new JButton();
        buttonPerimeter.setText("Найти периметр");
        panelMain.add(buttonPerimeter, new GridConstraints(0, 3, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panelMain.add(panel1, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ButtonLeft = new JButton();
        Font ButtonLeftFont = this.$$$getFont$$$(null, -1, 22, ButtonLeft.getFont());
        if (ButtonLeftFont != null) ButtonLeft.setFont(ButtonLeftFont);
        ButtonLeft.setText("\uF0E7");
        panel3.add(ButtonLeft, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonRight = new JButton();
        Font ButtonRightFont = this.$$$getFont$$$(null, -1, 22, ButtonRight.getFont());
        if (ButtonRightFont != null) ButtonRight.setFont(ButtonRightFont);
        ButtonRight.setText("\uF0E8");
        panel3.add(ButtonRight, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ButtonUp = new JButton();
        Font ButtonUpFont = this.$$$getFont$$$(null, -1, 22, ButtonUp.getFont());
        if (ButtonUpFont != null) ButtonUp.setFont(ButtonUpFont);
        ButtonUp.setText("\uF0E9");
        panel4.add(ButtonUp, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonDown = new JButton();
        Font ButtonDownFont = this.$$$getFont$$$(null, -1, 22, ButtonDown.getFont());
        if (ButtonDownFont != null) ButtonDown.setFont(ButtonDownFont);
        ButtonDown.setText("\uF0EA");
        panel4.add(ButtonDown, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel5.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        buttonDownVertical = new JButton();
        buttonDownVertical.setText("Масшабирование относительно низа по вертикали");
        panel5.add(buttonDownVertical, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonDownHorizontal = new JButton();
        buttonDownHorizontal.setText("Масшабирование относительно низа по горизонтали");
        panel5.add(buttonDownHorizontal, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTopVertical = new JButton();
        buttonTopVertical.setText("Масшабирование относительно верха по вертикали");
        panel5.add(buttonTopVertical, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonTopHorizontal = new JButton();
        buttonTopHorizontal.setText("Масшабирование относительно веха по горизонтали");
        panel5.add(buttonTopHorizontal, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonMiddleVertical = new JButton();
        buttonMiddleVertical.setText("Масшабирование относительно середины по вертикали");
        panel5.add(buttonMiddleVertical, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonMiddleHorizontal = new JButton();
        buttonMiddleHorizontal.setText("Масшабирование относительно  середины по горизогтали");
        panel5.add(buttonMiddleHorizontal, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textFieldDownVertical = new JTextField();
        panel5.add(textFieldDownVertical, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldDownHorizontal = new JTextField();
        panel5.add(textFieldDownHorizontal, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldTopVertical = new JTextField();
        panel5.add(textFieldTopVertical, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldTopHorizontal = new JTextField();
        panel5.add(textFieldTopHorizontal, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldMiddleVertical = new JTextField();
        panel5.add(textFieldMiddleVertical, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textFieldMiddleHorizontal = new JTextField();
        panel5.add(textFieldMiddleHorizontal, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textSquare = new JTextArea();
        panelMain.add(textSquare, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        textPerimeter = new JTextArea();
        panelMain.add(textPerimeter, new GridConstraints(0, 4, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        ButtonRectangle = new JButton();
        ButtonRectangle.setText("Описанный прямоугольник");
        panelMain.add(ButtonRectangle, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonMirror = new JButton();
        buttonMirror.setText("Отразить");
        panelMain.add(buttonMirror, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonClean = new JButton();
        buttonClean.setText("Очистить");
        panelMain.add(buttonClean, new GridConstraints(3, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }


}
