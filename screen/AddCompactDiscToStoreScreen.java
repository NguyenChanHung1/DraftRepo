package hust.soict.globalict.aims.screen;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.LimitExceededException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.media.Track;


public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen{

	public AddCompactDiscToStoreScreen() {
	}

	public JLabel directorLabel() {
		JLabel director_label = new JLabel();
		return director_label;
	}
	
	public JTextField directorTfInput() {
		JTextField dir_tf = new JTextField();
		return dir_tf;
	}
	
	public JLabel artistLabel() {
		JLabel art_label = new JLabel("Enter the artist: ");
		return art_label;
	}
	
	public JTextField artistTfInput() {
		JTextField art_tf = new JTextField();
		return art_tf;
	}
	
	public AddCompactDiscToStoreScreen(AimsScreen aims, StoreScreen storeScreen) {
		this.aims = aims;
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(9, 2));
		frame.add(label("Enter the id: "));
		JTextField idtf = tfInput();
		frame.add(idtf);
		frame.add(label("Enter the title: "));
		JTextField titletf = tfInput();
		frame.add(titletf);
		frame.add(label("Enter the category: "));
		JTextField catetf = tfInput();
		frame.add(catetf);
		frame.add(label("Enter the cost: "));
		JTextField costtf = tfInput();
		frame.add(costtf);
		
		frame.add(label("Enter director: "));
		JTextField dirtf = tfInput();
		frame.add(dirtf);
		frame.add(label("Enter the artist"));
		JTextField artisttf = tfInput();
		frame.add(artisttf);
		frame.add(label("Enter the titles of the tracks (in order, separated by commas): "));
		JTextField tracktitletf = tfInput();
		frame.add(tracktitletf);
		frame.add(label("Enter the track lengths (corresponding \norder with titles, separated by commas): "));
		JTextField tracklentf = tfInput();
		frame.add(tracklentf);
		
		JButton addButton = addItemBtn();
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int priorItemsSize = aims.store.getItemsInStore().size();

					int id = Integer.parseInt(idtf.getText());
					String title = titletf.getText();
					String category = catetf.getText();
					float cost = Float.parseFloat(costtf.getText());
					
					String director = dirtf.getText();
					String artist = artisttf.getText();
					
					CompactDisc cd = new CompactDisc(id, title, category, director, cost, artist);
					
					
					String[] trackTitles = tracktitletf.getText().split(", ");
					String[] trackLens = tracklentf.getText().split(", ");
					int min_len = (trackTitles.length > trackLens.length ? trackLens.length : trackTitles.length);
					for(int i=0; i<min_len; i++) {
						String track_title = trackTitles[i];
						int track_len = Integer.parseInt(trackLens[i]);
						cd.addTrack(new Track(track_title, track_len));
					}
					
					aims.store.addMedia(cd);
					
					if(aims.store.getItemsInStore().contains(cd)) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "The CD is added successfully to the store");
					}
					
					idtf.setText("");
					titletf.setText("");
					catetf.setText("");
					costtf.setText("");
					dirtf.setText("");
					artisttf.setText("");
					tracktitletf.setText("");
					tracklentf.setText("");
					
					JPanel center = storeScreen.getCenter();
					
					ArrayList<Media> mediaInStore = aims.store.getItemsInStore();
					int diff = mediaInStore.size() - priorItemsSize;
					for(int i=1; i<=diff; i++) {
						MediaStore cell = new MediaStore(mediaInStore.get(i+priorItemsSize-1));
						JPanel container = new JPanel();
						container.setLayout(new FlowLayout(FlowLayout.CENTER));
						
						JButton add2Cart = new JButton("Add to cart");
						final int j = i;
						
						add2Cart.addActionListener(new ActionListener() {	
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									aims.cart.addMedia(mediaInStore.get(j+priorItemsSize-1));
								} catch (LimitExceededException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						});
						
						container.add(add2Cart);
						if(mediaInStore.get(i+priorItemsSize-1) instanceof Playable) {
							container.add(new JButton("Play"));
						}
						cell.add(container);
						center.add(cell);
					}
				}
				catch (NumberFormatException e1) {
					
				}
			}
		});
		
		frame.add(addButton);
		frame.setVisible(true);
		frame.setTitle("Add CD To The Store");
		frame.setSize(1024, 768);
	}
}
