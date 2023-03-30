import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.translate.TranslateException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class MyApp extends Application {

    static Predictor<String, float[]> model;

    static {
        try {
            model = Criteria.builder()
                    .optApplication(ai.djl.Application.NLP.TEXT_EMBEDDING)
                    .setTypes(String.class, float[].class)
                    .optTranslator(new MyTranslator(Paths.get("tokenizer.json")))
                    .optModelPath(Paths.get("model.pt"))
                    .optEngine("PyTorch")
                    .build().loadModel().newPredictor();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();

        try {
            model.predict("test 1");
            model.predict("test 2");
            System.out.println("Prediction done");
        } catch (TranslateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() throws Exception {
        model.close();
    }
}
