package application;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Controller {

    @FXML
    private ResourceBundle resources;
    
    @FXML private ComboBox<String> dropdown0;
    @FXML private ComboBox<String> dropdown1;
    @FXML private ComboBox<String> dropdown2;
    @FXML private ComboBox<String> dropdown3;
    @FXML private ComboBox<String> dropdown4;
    
    @FXML private Button delete0;
    @FXML private Button delete1;
    @FXML private Button delete2;
    @FXML private Button delete3;
    @FXML private Button delete4;

    @FXML
    private ComboBox<String>[] dropdowns = new ComboBox[5];
    private Button[] deleteButtons = new Button[5];
    private int numVisible = 4;


    @FXML
    private URL location;

    @FXML
    void initializeTurnRule() {
            dropdowns[0] = dropdown0;
            dropdowns[1] = dropdown1;
            dropdowns[2] = dropdown2;
            dropdowns[3] = dropdown3;
            dropdowns[4] = dropdown4;
            deleteButtons[0] = delete0;
            deleteButtons[1] = delete1;
            deleteButtons[2] = delete2;
            deleteButtons[3] = delete3;
            deleteButtons[4] = delete4;
    }
    
    @FXML
    void addDropDown() {
        if(numVisible<5)
        {
            dropdowns[numVisible].setVisible(true);
            deleteButtons[numVisible].setVisible(true);
            deleteButtons[numVisible-1].setVisible(false);
            deleteButtons[numVisible].setManaged(true);
            dropdowns[numVisible].setManaged(true);
            numVisible++;
        }
        
    }
    @FXML
    void deleteDropDown() {
        if(numVisible>1)
        {
            numVisible--;
            deleteButtons[numVisible].setVisible(false);
            dropdowns[numVisible].setVisible(false); 
            dropdowns[numVisible].getSelectionModel().clearSelection();
            if(numVisible>1)
            {
                deleteButtons[numVisible-1].setVisible(true);
            }
        }
        
    }
    
    public static void main(String[] args)
    {
        
    }
    

}
