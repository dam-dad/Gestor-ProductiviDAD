package dad.productividad.task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dad.productividad.app.MainController;
import dad.productividad.dataManager.TableTasks;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class TaskDetailController implements Initializable {

    @FXML
    private GridPane view;

    @FXML
    private CheckBox doneTaskDetailCB;
    
    @FXML
    private CheckBox favouriteTaskDetailCB;
    
    @FXML
    private JFXTextField titleTaskDetailTF;

    @FXML
    private JFXTextArea descriptionTaskDetailTA;
     
    @FXML 
    private Button arrowButton;
    
    @FXML
    private Label creationDateLabel;

	private ObjectProperty<Task> task = new SimpleObjectProperty<>();

	private BooleanProperty done=new SimpleBooleanProperty();
	private BooleanProperty favourite=new SimpleBooleanProperty();
	private StringProperty title=new SimpleStringProperty();
	private StringProperty description=new SimpleStringProperty();
	private StringProperty creationDate=new SimpleStringProperty();

	private Media sound=new Media(this.getClass().getResource("/sound/cartoon_wink_magic_sparkle.wav").toExternalForm());
	private MediaPlayer mediaPlayer;
	
	public TaskDetailController() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskDetail.fxml"));
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		doneTaskDetailCB.selectedProperty().bindBidirectional(done);
		favouriteTaskDetailCB.selectedProperty().bindBidirectional(favourite);
		titleTaskDetailTF.textProperty().bindBidirectional(title);
		descriptionTaskDetailTA.textProperty().bindBidirectional(description);
		creationDateLabel.textProperty().bindBidirectional(creationDate);
		
		task.addListener((o,oc,nv)->{
			done.set(task.get().isDone());
			favourite.set(task.get().isFavourite());
			title.set(task.get().getTitle());
			description.set(task.get().getDescription());
			
			if(task.get().getCreationDate()!=null)
				creationDate.set(task.get().getCreationDate().toString());
		});
 
		titleTaskDetailTF.focusedProperty().addListener((o,ov,nv)->{
			if(title.get().isEmpty())
				title.set(task.get().getTitle());
			else{
				if(task.get().getTitle()!=title.get()) {
					task.get().setTitle(title.get());
					updateTaskAndWrapper();
				}
			}
		});
		descriptionTaskDetailTA.focusedProperty().addListener((o,ov,nv)->{
			if(description.get()!=null) {
				task.get().setDescription(description.get());
		    	TableTasks.updateHomeTask(task.get());
			} 
		});
	}       
    
	@FXML
    void onArrowButton(ActionEvent event) {
   	    MainController.mainController.setRightSideNull();
    }
    
	@FXML
	private void onTitleTaskDetailTF(ActionEvent event) {
		view.requestFocus();
	}
	
    @FXML
    private void onDoneClicked(ActionEvent event) {

    	if(doneTaskDetailCB.selectedProperty().get()) {
	    	mediaPlayer=new MediaPlayer(sound);
	        mediaPlayer.play();
    	}
    	task.get().setDone(doneTaskDetailCB.selectedProperty().get());
    	updateTaskAndWrapper();
    }

    @FXML
    private void onFavouriteClicked(ActionEvent event) {

    	task.get().setFavourite(favouriteTaskDetailCB.selectedProperty().get());
    	updateTaskAndWrapper();
    } 
    
    private void updateTaskAndWrapper() {
    	TableTasks.updateHomeTask(task.get());
    	MainController.mainController.updateTaskWrapper();
    }
    
    @FXML
    private void onDeleteTask(ActionEvent event) {
    	System.out.println("TODO Implementar un dialogo");
    	TableTasks.delete(task.get());
   	    MainController.mainController.setRightSideNull();
    	MainController.mainController.updateTaskWrapper();
    }
    
	public GridPane getView() { 
		return this.view; 
	}  
 
	public final ObjectProperty<Task> taskProperty() {
		return this.task;
	}
	

	public final Task getTask() {
		return this.taskProperty().get();
	}
	

	public final void setTask(final Task task) {
		this.taskProperty().set(task);
	}
	
	
	
}
