package hust.soict.globalict.aims.screen;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.LimitExceededException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen{

	public AddDigitalVideoDiscToStoreScreen() {
	}
	
	public AddDigitalVideoDiscToStoreScreen(AimsScreen aims, StoreScreen storeScreen) {
		this.aims = aims;
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(7, 2));
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
		frame.add(label("Enter the length: "));
		JTextField lentf = tfInput();
		frame.add(lentf);
		
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
					int length = Integer.parseInt(lentf.getText());
					
					DigitalVideoDisc dvd = new DigitalVideoDisc(id, title, category, director, length, cost);
					aims.store.addMedia(dvd);
					
					if(aims.store.getItemsInStore().contains(dvd)) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "The DVD is added successfully to the store");
					}
					
					idtf.setText("");
					titletf.setText("");
					catetf.setText("");
					costtf.setText("");
					dirtf.setText("");
					lentf.setText("");
					
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
		frame.setTitle("Add DVD To The Store");
		frame.setSize(1024, 768);
	}

}
