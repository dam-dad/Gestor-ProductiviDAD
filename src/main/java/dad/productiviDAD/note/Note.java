package dad.productiviDAD.note;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Note {

	private StringProperty title = new SimpleStringProperty();
	private StringProperty content = new SimpleStringProperty();
	private IntegerProperty id = new SimpleIntegerProperty();
	private IntegerProperty idPage = new SimpleIntegerProperty();
	
	//TODO Implementar color de texto y nota en la base de datos¿?
	private StringProperty color=new SimpleStringProperty();
	private BooleanProperty white=new SimpleBooleanProperty();

	public Note(String title,String content,String color,Boolean white) {
		this.title.set(title);
		this.content.set(content);
		this.color.set(color);
		this.white.set(white);
	}
	public Note() {
		
	}

	public final StringProperty titleProperty() {
		return this.title;
	}
	

	public final String getTitle() {
		return this.titleProperty().get();
	}
	

	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	

	public final StringProperty contentProperty() {
		return this.content;
	}
	

	public final String getContent() {
		return this.contentProperty().get();
	}
	

	public final void setContent(final String content) {
		this.contentProperty().set(content);
	}
	

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

	public final IntegerProperty idPageProperty() {
		return this.idPage;
	}
	

	public final int getIdPage() {
		return this.idPageProperty().get();
	}
	

	public final void setIdPage(final int idPage) {
		this.idPageProperty().set(idPage);
	}
	

	public final StringProperty colorProperty() {
		return this.color;
	}
	

	public final String getColor() {
		return this.colorProperty().get();
	}
	

	public final void setColor(final String color) {
		this.colorProperty().set(color);
	}

	public final BooleanProperty whiteProperty() {
		return this.white;
	}
	

	public final boolean isWhite() {
		return this.whiteProperty().get();
	}
	

	public final void setWhite(final boolean white) {
		this.whiteProperty().set(white);
	}
	
	
	

}