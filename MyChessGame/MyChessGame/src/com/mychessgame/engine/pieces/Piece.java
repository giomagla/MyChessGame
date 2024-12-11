package com.mychessgame.engine.pieces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class Piece {
    private final PieceTypes type;  // Type of piece (e.g., PAWN, ROOK)
    private  final String color;
     ImageIcon image;

    public Piece(PieceTypes type, String color,String imagePath) {
        this.type = type;
        this.color = color;
        try {
            // Load the image as a BufferedImage
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));

            // Iterate over all pixels of the piece image
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    int rgba = bufferedImage.getRGB(x, y);

                    // Get the RGBA components of the pixel
                    Color pixelColor = new Color(rgba, true);

                    // If the pixel is white (255, 255, 255), it's considered as transparent
                    if (pixelColor.getRed() == 255 && pixelColor.getGreen() == 255 && pixelColor.getBlue() == 255) {

                        // Set the pixel transparent
                        bufferedImage.setRGB(x, y, pixelColor.getTransparency());
                    }
                }
            }

            // Resize the image if necessary (e.g., 50x50)
            Image resizedImage = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

            // Set the image for the pawn
            this.image = new ImageIcon(resizedImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public abstract boolean move(Piece[][] board, String color, int row, int col, int desiredRow, int desiredCol);
    public abstract List<int[]> validMoves(Piece[][] board, String color, int row, int col);

    public String getColor() {
        return color;
    }
    public PieceTypes getType() {
        return type;
    }

    public abstract ImageIcon getImage();

}

