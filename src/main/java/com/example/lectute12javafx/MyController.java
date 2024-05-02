package com.example.lectute12javafx;

import com.example.lectute12javafx.model.Student;
import com.github.javafaker.Faker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class MyController {
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student,Integer> idColumn;
    @FXML private TableColumn<Student,String> nameColumn;
    @FXML private TableColumn<Student,Integer> ageColumn;
    private List<Student> studentList=new ArrayList<>();
public void initialize(){
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    studentTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
}
    public void close() {
        Platform.exit();
    }

    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("Example window alert");
        alert.show();
    }

    public void fillStudents() {

        Faker faker = new Faker();
        for (int i = 0; i < 100; i++) {
            studentList.add(new Student(i + 1, faker.name().fullName(), faker.number().numberBetween(18, 60)));
        }
        studentTable.setItems(FXCollections.observableList(studentList));
    }

    public void addStudent() {
    int id = Integer.parseInt(idField.getText());
    String name=nameField.getText();
    int age = Integer.parseInt(ageField.getText());
    Student student = new Student(id,name,age);
    studentList.addFirst(student);
    clearFields();
        studentTable.setItems(FXCollections.observableList(studentList));
    }

    private void clearFields() {
    idField.clear();
    ageField.clear();
    nameField.clear();
    }

    public void deleteStudent() {
    /*int deleteIndex= studentTable.getSelectionModel().getSelectedIndex();
    studentList.remove(deleteIndex);
    studentTable.setItems(FXCollections.observableList(studentList));*/
        List<Student> deleteListIndex= studentTable.getSelectionModel().getSelectedItems();
        studentList.removeAll(deleteListIndex);
        studentTable.setItems(FXCollections.observableList(studentList));

    }
}