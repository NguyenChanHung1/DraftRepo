package hust.soict.globalict.aims.screen;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hust.soict.globalict.aims.cart.Cart.Cart;
import hust.soict.globalict.aims.media.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController{
	
	@SuppressWarnings("unused")
	private AimsScreen aims;
	
	private Cart cart;
	
	@FXML
	private Label labelCost;
	
	@FXML
	private Button btnPlay;
	
	@FXML
	private Button btnRemove;
	
	@FXML
	private TableView<Media> tblMedia;
	
	@FXML
	private TableColumn<Media, String> colMediaTitle;
	
	@FXML
	private TableColumn<Media, String> colMediaCategory;
	
	@FXML
	private TableColumn<Media, Float> colMediaCost;
	
	@FXML 
	private TextField tfFilter;
	
	@FXML
	private RadioButton radioBtnFilterId;
	
	@FXML 
	private RadioButton radioBtnFilterTitle;
	
	public CartScreenController(AimsScreen aims) {
		super();
		this.cart = aims.cart;
		this.aims = aims;
	}
	
	private void showFilteredMedia(String newValue) {
		if(radioBtnFilterId.isSelected()) {
			try {
				Integer.parseInt(newValue);
				Predicate<? super Media> idFilterPred = i -> String.valueOf(i.getId()).contains(newValue);
				FilteredList<Media> filteredMediasById = new FilteredList<>(this.cart.getItemsOrdered(), idFilterPred);
				tblMedia.setItems(filteredMediasById);
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		if(radioBtnFilterTitle.isSelected()) {
			String LowerValue = newValue.toLowerCase();
			Predicate<? super Media> titleFilterPred = i -> i.getTitle().toLowerCase().contains(LowerValue);
			FilteredList<Media> filteredMediasByTitle = new FilteredList<>(this.cart.getItemsOrdered(), titleFilterPred);
			tblMedia.setItems(filteredMediasByTitle);
		}
	}
	
	@FXML
	private void initialize() {
		colMediaTitle.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
		colMediaCategory.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
		colMediaCost.setCellValueFactory(new PropertyValueFactory<Media, Float>("cost"));
		tblMedia.setItems(this.cart.getItemsOrdered());
		
		btnPlay.setVisible(false);
		btnRemove.setVisible(false);
		
		tblMedia.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Media>() {
					
				@Override
				public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
					if(newValue != null) {
						updateButtonBar(newValue);
					}
				}
			});
		String labelTextInitialized = String.format("%.2f $", this.cart.totalCost());
		labelCost.setText(labelTextInitialized);
		
		tfFilter.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				showFilteredMedia(newValue);
		}
		});
	}
	
	void updateButtonBar(Media media) {
		btnRemove.setVisible(true);
		if(media instanceof Playable) {
			btnPlay.setVisible(true);
		}
		else {
			btnPlay.setVisible(false);
		}
	}
	
	@FXML
	void btnRemovePressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		float cost_after_removed = cart.totalCost() - media.getCost();
		String labelText = String.format("%.2f $", cost_after_removed);
		labelCost.setText(labelText);
		cart.removeMedia(media);
	}
	
	@FXML
	void placeOrderButtonPressed(ActionEvent event) {
		cart.removeAllItems();
		labelCost.setText("0 $");
	}
	
	@FXML
	void btnPlayPressed(ActionEvent event) {
		Media media = tblMedia.getSelectionModel().getSelectedItem();
		if(media instanceof DigitalVideoDisc) {
			DigitalVideoDisc dvd = (DigitalVideoDisc) media;
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "Now playing: "+dvd.getTitle()+
					"\nDVD Length: "+dvd.getLength());
		}
		if(media instanceof CompactDisc) {
			CompactDisc cd = (CompactDisc) media;
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "Now playing: "+cd.getTitle()+
					"\nCD Length: "+cd.getLength());
		}
	}
}
