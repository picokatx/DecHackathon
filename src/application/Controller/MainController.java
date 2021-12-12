package application.Controller;

import application.Main;
import application.Model.DocxUtils;
import application.Model.PdfUtils;
import application.Model.PptUtils;
import application.Model.Types.*;
import application.Model.VttUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button backBtn;

    @FXML
    private AnchorPane ap1;

    @FXML
    private AnchorPane ap2;


    @FXML
    private TextArea ta1;

    @FXML
    private TextArea ta2;

    File file;

    @FXML
    private void backBtnOnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("View/sample.fxml"));
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Stage stage = (Stage) backBtn.getScene().getWindow();
        stage.setResizable(false);
        file = Controller.file;
        if (Controller.pdf) {
            try {
                PdfData pdf = PdfUtils.decode(file);
                ta1.setText("Words Processed: " + pdf.getWordCount() + "\n" + "Time taken: " + PdfUtils.time);
                ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
                for (TextGroup t : pdf.getTextEntries()) {
                    for (String s : t.getWords()) {
                        list.add(new PieChart.Data(s, t.getWeight()));
                    }
                }
                PieChart pieChart = new PieChart(list);
                pieChart.setPrefSize(200, 200);
                pieChart.setLayoutX(51);
                pieChart.setLayoutY(33);

                ap2.getChildren().addAll(pieChart);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (Controller.ppt) {
            try {
                PptData ppt = PptUtils.decode(file);
                ta1.setText("Words Processed: " + ppt.getWordCount() + "\n" + "Time taken: " + PptUtils.time);
                ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
                for (TextGroup t : ppt.getSlideTxtGroup()) {
                    for (String s : t.getWords()) {
                        list.add(new PieChart.Data(s, t.getWeight()));
                    }
                }
                PieChart pieChart = new PieChart(list);
                pieChart.setPrefSize(200, 200);
                pieChart.setLayoutX(51);
                pieChart.setLayoutY(33);

                ObservableList<PieChart.Data> list2 = FXCollections.observableArrayList();
                for (TextGroup t : ppt.getCommentTxtGroup()) {
                    for (String s : t.getWords()) {
                        list2.add(new PieChart.Data(s, t.getWeight()));
                    }
                }
                PieChart pieChart2 = new PieChart(list2);
                pieChart2.setPrefSize(200, 200);
                pieChart2.setLayoutX(51);
                pieChart2.setLayoutY(266);
                ap2.getChildren().addAll(pieChart, pieChart2);
            } catch (IOException | SAXException | ParserConfigurationException e) {
                e.printStackTrace();
            }
        } else if (Controller.vtt) {
            try {
                VttData vtt = VttUtils.decode(file);

                ta1.setText("Words Processed: " + vtt.getWordCount() + "\n" + "Time taken: " + vtt.getDuration());
                ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
                for (TextGroup t : vtt.getText()) {
                    for (String s: t.getWords()) {
                        list.add(new PieChart.Data(s, t.getWeight()));
                    }
                }
                PieChart pieChart = new PieChart(list);
                pieChart.setPrefSize(200,200);
                pieChart.setLayoutX(51);
            } catch (FileNotFoundException | ParseException e) {
                e.printStackTrace();
            }
        } else if (Controller.vtt) {
            //Mp4Data mp4 = Mp4Utils.decode(file);
            //ta1.setText("Words Processed: " + mp4.getWordCount() + "\n" + "Time taken: " + Mp4Utils.time);
        } else {
            try {
                DocxData docx = DocxUtils.decode(file);
                ta1.setText("Words Processed: " + docx.getWordCount() + "\n" + "Time taken: " + DocxUtils.time);

                ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
                for (TextGroup t : docx.getTextGroup()) {
                    for (String s : t.getWords()) {
                        list.add(new PieChart.Data(s, t.getWeight()));
                    }
                }
                PieChart pieChart = new PieChart(list);
                pieChart.setPrefSize(200, 200);
                pieChart.setLayoutX(51);
                pieChart.setLayoutY(33);
            } catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        }
    }
}
