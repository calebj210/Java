import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Load and apply filters and manipulations to images
 *
 * Date Last Modified: 11 / 1 / 2020
 * @author Caleb Jacobs
 *
 * CS1131 Fall 2020
 * Lab Section: L03
 */
public class ImageManipulator extends Application implements ImageManipulatorInterface {
    /**
     * Load the specified PPM image file.
     *
     * @param filename - filename of image to be loaded
     * @return - WriteableImage of ppm image
     * @throws FileNotFoundException - Throw exception if image is not found
     */
    @Override
    public WritableImage loadImage( String filename ) throws FileNotFoundException {
        File file = new File( filename );
        Scanner input = new Scanner(file);
        int width = 0;
        int height = 0;

        // Check magic number
        if ( !( input.next().equals( "P3" ) ) ) {
            return null;
        }

        // ignore comments at any part of a line until the width is found
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                width = input.nextInt();
                break;
            } else {
                input.nextLine();
            }
        }

        // Find the height
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                height = input.nextInt();
                break;
            } else {
                input.nextLine();
            }
        }

        // Find the pixel color range
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                input.nextInt();
                break;
            } else {
                input.nextLine();
            }
        }

        int r, g, b;
        Color pixelColor;
        WritableImage image = new WritableImage(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                r = input.nextInt();
                g = input.nextInt();
                b = input.nextInt();

                pixelColor = Color.rgb(r, g, b);
                image.getPixelWriter().setColor(j, i, pixelColor);
            }
        }

        return image;
    }

    /**
     * Save image to a file
     *
     * @param filename - File location to save to.
     * @param image - Image to save.
     * @throws FileNotFoundException - Exception for nonwriteable files
     */
    @Override
    public void saveImage(String filename, WritableImage image) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter output = new PrintWriter(file);

        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        int r, g, b;

        output.printf("P3\n%d %d\n255\n", width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = image.getPixelReader().getColor(j, i);
                r = (int)(color.getRed() * 255);
                g = (int)(color.getGreen() * 255);
                b = (int)(color.getBlue() * 255);

                output.printf("%03d %03d %03d  ", r, g, b);
            }
            output.print("\n");
        }

        output.close();
    }

    /**
     * Invert image colors.
     *
     * @param image - the image to be inverted, do not modify!
     * @return - inverted image.
     */
    @Override
    public WritableImage invertImage(WritableImage image) {
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();

        WritableImage invertedImage = new WritableImage(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = image.getPixelReader().getColor(j, i);

                color = Color.rgb(
                        (255 - (int)(color.getRed() * 255)),
                        (255 - (int)(color.getGreen() * 255)),
                        (255 - (int)(color.getBlue() * 255)));

                invertedImage.getPixelWriter().setColor(j, i, color);
            }
        }

        return invertedImage;
    }

    /**
     * Convert images to grayscale.
     *
     * @param image - the image to be converted to grayscale, do not modify!
     * @return - grayscale image.
     */
    @Override
    public WritableImage grayifyImage(WritableImage image) {
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        int r, g, b;
        int lum;

        WritableImage grayedImage = new WritableImage(width, height);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = image.getPixelReader().getColor(j, i);
                r = (int)(color.getRed()*255);
                g = (int)(color.getGreen()*255);
                b = (int)(color.getBlue()*255);

                lum = (int)(0.2989 * r + 0.5870 * g + 0.1140 * b);

                color = Color.rgb(lum, lum, lum);

                grayedImage.getPixelWriter().setColor(j, i, color);
            }
        }
        return grayedImage;
    }

    /**
     * Apply a pixelation filter to an image.
     *
     * @param image - the image to be converted to grayscale, do not modify!
     * @return - pixelated image.
     */
    @Override
    public WritableImage pixelateImage(WritableImage image) {
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        Color color;

        WritableImage pixelatedImage = new WritableImage(width, height);

        // Pixelate internal pixels
        for (int i = 2; i < height - 2; i += 5) {
            for (int j = 2; j < width - 2; j += 5) {
                color = image.getPixelReader().getColor(j, i);
                for (int k = -2; k <= 2; k++) {
                    for (int s = -2; s <= 2; s++) {
                        pixelatedImage.getPixelWriter().setColor(j + s, i + k, color);
                    }
                }
            }
        }

        // Pixelate edge cases
        int rightSlop = width % 5;
        if (rightSlop != 0) {
            for (int i = 2; i < height - 2; i += 5) {
                color = image.getPixelReader().getColor(width - 1, i);
                for (int j = rightSlop; j >= 1; j--) {
                    for (int k = -2; k <= 2; k++) {
                        pixelatedImage.getPixelWriter().setColor(width - j, i + k, color);
                    }
                }
            }
        }

        int bottomSlop = height % 5;
        if (bottomSlop != 0) {
            for (int j = 2; j < width - 2; j += 5) {
                color = image.getPixelReader().getColor(j, height - 1);
                for (int i = bottomSlop; i >= 1; i--) {
                    for (int s = -2; s <= 2; s++) {
                        pixelatedImage.getPixelWriter().setColor(j + s, height - i, color);
                    }
                }
            }
        }

        if (bottomSlop != 0 && rightSlop != 0) {
            color = image.getPixelReader().getColor(width - 1, height -1);
            for (int i = bottomSlop; i >= 1; i--) {
                for (int j = rightSlop; j >= 1; j--) {
                    pixelatedImage.getPixelWriter().setColor(width - j, height - i, color);
                }
            }
        }

        return pixelatedImage;
    }

    /**
     * Flip image vertically
     *
     * @param image - the image to be flipped, do not modify!
     * @return - flipped image
     */
    @Override
    public WritableImage flipImage(WritableImage image) {
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();

        WritableImage flippedImage = new WritableImage(width, height);
        for (int i = 0; i < height - 1 - i; i++) {
            for (int j = 0; j < width; j++) {
                flippedImage.getPixelWriter().setColor(j, i, image.getPixelReader().getColor(j, height - 1 - i));
                flippedImage.getPixelWriter().setColor(j, height - 1 - i, image.getPixelReader().getColor(j, i));
            }
        }

        return flippedImage;
    }

    /**
     * Create button strip for loading and processing images
     *
     * @param imageView - Currently displayed image
     * @return - HBox of the image manipulating buttons
     */
    public HBox createButtonStrip(ImageView imageView) {
        HBox hBox = new HBox();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        Stage fileStage = new Stage();

        Button loadButton = new Button("Load");
        loadButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(fileStage);
            if (file != null) {
                try {
                    imageView.setImage(loadImage(file.getPath()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(fileStage);
            if (file != null) {
                try {
                    saveImage(file.getPath(), (WritableImage)imageView.getImage());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        Button flipButton = new Button("Flip");
        flipButton.setOnAction(event -> {
            imageView.setImage(flipImage((WritableImage)imageView.getImage()));
        });

        Button invertButton = new Button("Invert");
        invertButton.setOnAction(event -> {
            imageView.setImage(invertImage((WritableImage)imageView.getImage()));
        });

        Button grayButton = new Button("Grayscale");
        grayButton.setOnAction(event -> {
            imageView.setImage(grayifyImage((WritableImage)imageView.getImage()));
        });

        Button pixelateButton = new Button("Pixelate");
        pixelateButton.setOnAction(event -> {
            imageView.setImage(pixelateImage((WritableImage)imageView.getImage()));
        });

        hBox.getChildren().addAll(
                loadButton,
                saveButton,
                flipButton,
                invertButton,
                grayButton,
                pixelateButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);

        return hBox;
    }

    /**
     * Driver for creating graphics
     *
     * @param stage - Stage to draw graphics on
     * @throws FileNotFoundException - exception for image file
     */
    public void start(Stage stage) throws FileNotFoundException {
        BorderPane pane = new BorderPane();

        ImageView imageView = new ImageView();

        Label label = new Label();
        label.setGraphic(imageView);

        pane.setCenter(label);
        pane.setBottom(createButtonStrip(imageView));

        Scene scene = new Scene(pane, 1000, 1000);
        stage.setTitle("Image Manipulator");
        stage.setScene(scene);
        stage.show();
    }
}


