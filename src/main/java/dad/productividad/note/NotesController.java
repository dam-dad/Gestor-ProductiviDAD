package dad.productividad.note;

import java.util.List;

import dad.productividad.dataManager.TableNotes;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;

public class NotesController {
	    
	private List<Note> noteList;

	
//	private JFXMasonryPane view=new JFXMasonryPane();
	private FlowPane view = new FlowPane();
    public NotesController() {
    	noteList = TableNotes.read(20);
    	
    	for(Note i : noteList) {
    	NoteComponent postIt1=new NoteComponent();
    	Note note = new Note(i.getContent(),"#708D81",false);
    	postIt1.setNote(i);
    	view.getChildren().addAll(postIt1);
   	
    	postIt1.getContentTA().focusedProperty().addListener((o,ov,nv)->{
   		
    		if (!nv) {
    			//cuando pierde el foco 
    			i.setContent(note.getContent());
    			TableNotes.update(i);
			}
    		    });
    	}
    	view.setHgap(5);
    	view.setVgap(5);
    	view.setPadding(new Insets(5));
 
	}


	public FlowPane getView() {
		return this.view;
	}
	

}   