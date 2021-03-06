package Project.Experiments;

import Project.Experiments.SeedGeneration.SeedGenerator;
import Project.Game_Engine.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

public class TestEnvironment extends Application implements Constants {

    protected Stage primaryStage = new Stage();
    protected Scene scene;
    protected Environment environment;
    protected double width = 1000;
    protected double height = 1000;

    protected long maxTime = -1;   //Use -1 for unlimited time
    protected int maxStep = 150;

    protected boolean deterministic = true;

    protected int gridSize = 15;

    protected int numOfGoals = 2;
    protected int numOfAgents = 2;
    protected int numOfNonPassableCells = gridSize * gridSize / 5;

    protected String searchType = A_STAR_SEARCH;

    private int seed = 6;

    @Override
    public void start(Stage stage) {

        //Make environment with given seed
        if(seed >= 0 || seed <= 40) {
            environment = new Environment(width, height, gridSize, numOfAgents, numOfGoals);
            SeedGenerator generator = new SeedGenerator(searchType, environment, maxStep, maxTime);
            generator.buildSeed(seed);
        }
        //Random environment (no seed yet)
        else {
            environment = new Environment(width, height, gridSize, numOfGoals, numOfAgents, numOfNonPassableCells);
            new MoveAgents(environment, searchType, maxStep, maxTime);
        }

        scene = new Scene(environment, width, height);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Environment");
        primaryStage.setX(500);
        primaryStage.setY(50);
        primaryStage.show();
    }
    
    public static void main(String [] args) {
        
        Application.launch(args);
    }
}