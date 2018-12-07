package ru.indraft.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.indraft.model.TaskModel;
import ru.indraft.service.TaskService;
import ru.indraft.service.labor.LaborReportService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class LaborController {

    private static final String EXCEL_2007_FILE_EXTENSION = ".xlsx";

    private File chosenFile = null;
    private TaskService taskService = new TaskService();

    @FXML
    TextField chosenFileTextField;

    private enum Extension {
        EXCEL("XML files (*.xls, *.xlsx)", new String[]{"*.xls", EXCEL_2007_FILE_EXTENSION}),
        EXCEL_2007("XML files (*.xlsx)", new String[]{EXCEL_2007_FILE_EXTENSION});

        private String description;
        private String[] extensions;

        Extension(String description, String[] extensions) {
            this.description = description;
            this.extensions = extensions;
        }

        public String getDescription() {
            return description;
        }

        public String[] getExtensions() {
            return extensions;
        }
    }

    private FileChooser createFileChooser(Extension extension) {
        var fileChooser = new FileChooser();
        var extFilter = new FileChooser.ExtensionFilter(
                extension.getDescription(), extension.getExtensions());
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = createFileChooser(Extension.EXCEL);

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            chosenFileTextField.setText(file.getAbsolutePath());
            chosenFile = file;
        } else {
            chosenFileTextField.setText(null);
            chosenFile = null;
        }
    }

    @FXML
    public void generateReport() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(chosenFile));
        List<TaskModel> taskModels = taskService.getTaskModels(workbook);
        generateLaborReport(taskModels);
    }

    private File openSaveDialog() {
        FileChooser fileChooser = createFileChooser(Extension.EXCEL_2007);

        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            if (!file.getPath().endsWith(EXCEL_2007_FILE_EXTENSION)) {
                file = new File(file.getPath() + EXCEL_2007_FILE_EXTENSION);
            }
            return file;
        }
        return null;
    }

    private void generateLaborReport(List<TaskModel> taskModels) throws IOException {
        LaborReportService reportService = new LaborReportService();
        XSSFWorkbook workbook = reportService.generate(taskModels);
        File file = openSaveDialog();
        if (file != null) {
            workbook.write(new FileOutputStream(file));
            workbook.close();
        }
    }


}
