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

import hust.soict.globalict.aims.exception.AuthorPresentedException;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;

public class AddBookToStoreScreen extends AddItemToStoreScreen{
	
	public AddBookToStoreScreen() {
	}
	
	public AddBookToStoreScreen(AimsScreen aims, StoreScreen storeScreen) {	
		this.aims = aims;
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(6, 2));
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
		frame.add(label("Enter authors(separated by commas): "));
		JTextField authortf = tfInput();
		frame.add(authortf);
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
					String[] authors = authortf.getText().split(", ");
					
					Book book = new Book(id, title, category, cost);
					aims.store.addMedia(book);
					for(String au : authors) {
						try {
							book.addAuthor(au);
						} catch (AuthorPresentedException e1) {
							e1.printStackTrace();
						}
					}
					
					if(aims.store.getItemsInStore().contains(book)) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "The book is added successfully to the store");
					}

					idtf.setText("");
					titletf.setText("");
					catetf.setText("");
					costtf.setText("");
					authortf.setText("");
					
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
		
		this.aims = aims;
		
		frame.setVisible(true);
		frame.setTitle("Add Book To The Store");
		frame.setSize(1024, 768);
	}
	

}
