package ch.fhnw.baumanncar;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Rectangle colorField = new Rectangle(0, 0, 80, 80);

    private IntegerProperty red = new SimpleIntegerProperty(0);
    private IntegerProperty green = new SimpleIntegerProperty(0);
    private IntegerProperty blue = new SimpleIntegerProperty(0);

    private TextField redValue = new TextField();
    private TextField greenValue = new TextField();
    private TextField blueValue = new TextField();

    private Label redHex = new Label();
    private Label greenHex = new Label();
    private Label blueHex = new Label();


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("depa - ColorPicker");

        HBox pane = new HBox();
        pane.setSpacing(10);
        pane.setPadding(new Insets(10, 10, 10, 10));

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.add(createSlider(ColorCommand.RED), 0, 0);
        grid.add(createSlider(ColorCommand.GREEN), 0, 1);
        grid.add(createSlider(ColorCommand.BLUE), 0, 2);

        grid.add(redValue, 1, 0);
        grid.add(greenValue, 1, 1);
        grid.add(blueValue, 1, 2);

        grid.add(redHex, 2, 0);
        grid.add(greenHex, 2, 1);
        grid.add(blueHex, 2, 2);

        redValue.textProperty().bind(red.asString());
        greenValue.textProperty().bind(green.asString());
        blueValue.textProperty().bind(blue.asString());

        redValue.setMaxWidth(50);
        redValue.setStyle("-fx-padding: 0");
        greenValue.setMaxWidth(50);
        greenValue.setStyle("-fx-padding: 0");
        blueValue.setMaxWidth(50);
        blueValue.setStyle("-fx-padding: 0");

        redHex.textProperty().bind(red.asString());
        greenHex.textProperty().bind(green.asString());
        blueHex.textProperty().bind(blue.asString());

        ToggleGroup buttonGroup = new ToggleGroup();
        RadioButton redButton = new RadioButton("red");
        redButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    red.set(ColorCommand.RED.r);
                    green.set(ColorCommand.RED.g);
                    blue.set(ColorCommand.RED.b);
                }
            }
        });
        redButton.setToggleGroup(buttonGroup);
        RadioButton blueButton = new RadioButton("blue");
        blueButton.setToggleGroup(buttonGroup);
        blueButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    red.set(ColorCommand.BLUE.r);
                    green.set(ColorCommand.BLUE.g);
                    blue.set(ColorCommand.BLUE.b);
                }
            }
        });
        RadioButton greenButton = new RadioButton("green");
        greenButton.setToggleGroup(buttonGroup);
        greenButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    red.set(ColorCommand.GREEN.r);
                    green.set(ColorCommand.GREEN.g);
                    blue.set(ColorCommand.GREEN.b);
                }
            }
        });
        RadioButton yellowButton = new RadioButton("yellow");
        yellowButton.setToggleGroup(buttonGroup);
        yellowButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    red.set(ColorCommand.YELLOW.r);
                    green.set(ColorCommand.YELLOW.g);
                    blue.set(ColorCommand.YELLOW.b);
                }
            }
        });
        RadioButton cyanButton = new RadioButton("cyan");
        cyanButton.setToggleGroup(buttonGroup);
        cyanButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    red.set(ColorCommand.CYAN.r);
                    green.set(ColorCommand.CYAN.g);
                    blue.set(ColorCommand.CYAN.b);
                }
            }
        });
        RadioButton orangeButton = new RadioButton("orange");
        orangeButton.setToggleGroup(buttonGroup);
        orangeButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    red.set(ColorCommand.ORANGE.r);
                    green.set(ColorCommand.ORANGE.g);
                    blue.set(ColorCommand.ORANGE.b);
                }
            }
        });
        RadioButton blackButton = new RadioButton("black");
        blackButton.setToggleGroup(buttonGroup);
        blackButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    red.set(ColorCommand.BLACK.r);
                    green.set(ColorCommand.BLACK.g);
                    blue.set(ColorCommand.BLACK.b);
                }
            }
        });

        VBox predefiendColorBox = new VBox();
        predefiendColorBox.getChildren().addAll(redButton, blueButton, greenButton, yellowButton, cyanButton, orangeButton, blackButton);
        grid.add(predefiendColorBox, 1, 3);

        VBox dlBox = new VBox();
        Button darker = new Button("Darker");
        darker.setMinWidth(80);
        darker.setDisable(true);
        Button lighter = new Button("Ligher");
        lighter.setMinWidth(80);
        lighter.setDisable(true);
        dlBox.getChildren().addAll(darker, lighter);
        grid.add(dlBox, 3, 3);

        StackPane colorFieldPane = new StackPane();
        colorFieldPane.setAlignment(Pos.CENTER);
        colorFieldPane.setStyle("-fx-padding: 20");
        grid.add(colorFieldPane, 0, 3);
        pane.getChildren().add(grid);

        colorField.setStroke(Color.BLACK);

        colorFieldPane.getChildren().add(colorField);

        VBox root = new VBox();
        root.getChildren().add(createMenuBar());
        root.getChildren().add(pane);

        primaryStage.setScene(new Scene(root, 340, 300));
        primaryStage.show();
    }

    enum ColorCommand {
        RED(255, 0, 0),
        GREEN(0, 255, 0),
        BLUE(0, 0, 255),
        YELLOW(255, 255, 0),
        CYAN(0, 255, 255),
        ORANGE(255, 165, 0),
        BLACK(0, 0, 0);

        private int r = 0;
        private int g = 0;
        private int b = 0;

        ColorCommand(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }

        public Color getColor() {
            return Color.rgb(r, g, b);
        }
    }


    private Slider createSlider(ColorCommand c) {
        Slider s = new Slider();
        s.setMin(0);
        s.setMax(255);
        switch (c) {
            case RED:
                s.valueProperty().bindBidirectional(red);
                s.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        red.setValue(newValue);
                        colorField.setFill(Color.rgb(red.getValue(), green.get(), blue.get()));
                    }
                });
                s.setStyle("-fx-background-color:red;");
                return s;
            case BLUE:
                s.valueProperty().bindBidirectional(blue);
                s.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        blue.setValue(newValue);
                        colorField.setFill(Color.rgb(red.getValue(), green.get(), blue.get()));
                    }
                });
                s.setStyle("-fx-background-color:blue;");
                return s;
            case GREEN:
                s.valueProperty().bindBidirectional(green);
                s.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        green.setValue(newValue);
                        colorField.setFill(Color.rgb(red.getValue(), green.get(), blue.get()));
                    }
                });
                s.setStyle("-fx-background-color:green;");
                return s;
            default:
                return null;
        }
    }


    private String toHex(Number value) {
        return Integer.toHexString(value.intValue());
    }

    public MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();

        Menu fileItem = new Menu("File");
        Menu attributesItem = new Menu("Attributes");

        menuBar.getMenus().addAll(fileItem, attributesItem);

        return menuBar;

    }
}
