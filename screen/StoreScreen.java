package hust.soict.globalict.aims.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.LimitExceededException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hust.soict.globalict.aims.cart.Cart.Cart;
import hust.soict.globalict.aims.media.Book;
import hust.soict.globalict.aims.media.CompactDisc;
import hust.soict.globalict.aims.media.DigitalVideoDisc;
import hust.soict.globalict.aims.media.Media;
import hust.soict.globalict.aims.media.Playable;
import hust.soict.globalict.aims.store.Store.Store;

@SuppressWarnings("serial")
public class StoreScreen extends JFrame{
	private Store store;
	boolean change = false;
	private JPanel center;
	
	public StoreScreen(AimsScreen aims) {
		this.store = aims.store;
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		this.center = createCenter(aims);
		cp.add(center, BorderLayout.CENTER);
		
		cp.add(createNorth(aims), BorderLayout.NORTH);
		
		setVisible(true);
		setTitle("Store");
		setSize(1024, 768);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	JPanel createNorth(AimsScreen aims) {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar(aims, this));
		north.add(createHeader(aims));
		return north;
	}
	
	JMenuBar createMenuBar(AimsScreen aims, StoreScreen store_screen) {		
		JMenu menu = new JMenu("Options");
		JMenuItem add_book = new JMenuItem("Add Book");
		add_book.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddBookToStoreScreen(aims, store_screen);
				
				
			}
		});
		menu.add(add_book);
		
		JMenuItem add_cd = new JMenuItem("Add CD");
		add_cd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddCompactDiscToStoreScreen(aims, store_screen);				
			}
		});
		menu.add(add_cd);
		
		JMenuItem add_dvd = new JMenuItem("Add DVD");
		add_dvd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddDigitalVideoDiscToStoreScreen(aims, store_screen);
			}
		});
		menu.add(add_dvd);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuBar.add(menu);
		
		return menuBar;
	}
	
	JPanel createHeader(AimsScreen aims) {
		JPanel header = new JPanel();
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel("AIMS");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
		title.setForeground(Color.CYAN);
		
		JButton cart = new JButton("View cart");
		cart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CartScreen(aims);
				
			}
		});
		cart.setPreferredSize(new Dimension(100, 50));
		cart.setMaximumSize(new Dimension(100, 50));
		
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		header.add(title);
		header.add(Box.createHorizontalGlue());
		header.add(cart);
		header.add(Box.createRigidArea(new Dimension(10, 10)));
		
		return header;
	}
	
	JPanel createCenter(AimsScreen aims) {
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(3, 3, 2, 2));
		
		ArrayList<Media> mediaInStore = this.store.getItemsInStore();
		for(int i = 0; i < mediaInStore.size(); i++) {
			MediaStore cell = new MediaStore(mediaInStore.get(i));
			JPanel container = new JPanel();
			container.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			JButton add2Cart = new JButton("Add to cart");
			final int j = i;
			
			add2Cart.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						aims.cart.addMedia(mediaInStore.get(j));
					} catch (LimitExceededException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			container.add(add2Cart);
			if(mediaInStore.get(i) instanceof Playable) {
				JButton playBtn = new JButton("Play");
				playBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame f = new JFrame();
						if(mediaInStore.get(j) instanceof DigitalVideoDisc) {
							DigitalVideoDisc dvd = (DigitalVideoDisc)mediaInStore.get(j);
							JOptionPane.showMessageDialog(f, "Now playing: "+dvd.getTitle()+
									"\nDVD Length: "+dvd.getLength());
						}
						else {
							CompactDisc cd = (CompactDisc)mediaInStore.get(j);
							JOptionPane.showMessageDialog(f, "Now playing: "+cd.getTitle()+
									"\nCD Length: "+cd.getLength());
						}
					}
				});
				container.add(playBtn);
			}
			cell.add(container);
			center.add(cell);
		}
		
		return center;
	}
	
	public JPanel getCenter() {
		return this.center;
	}
	
	public static void main(String[] args) {
		AimsScreen aims = new AimsScreen(new Cart(), new Store());
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc(1, "Hello friends", "Fantasy", "David Nop", 120, 15.99f);
		CompactDisc cd1 = new CompactDisc(2, "Rockazella", "Netflix", "TheAnh28Entertainment", 16.88f, "Harry Keanes");
		DigitalVideoDisc dvd2 = new DigitalVideoDisc(3, "Hala Madrid", "Sport", "Karim Benzema", 109, 13.87f);
		CompactDisc cd2 = new CompactDisc(4, "Manchester is Blue", "Sport", "Pep Guardiola", 21.99f, "Jack Hundred Potatoes");
		Book b1 = new Book(5, "How to Win Friends and Influence People", "Business", 13.55f);
		Book b2 = new Book(6, "Harry Maguire and the Elephant-eared Cup", "Fiction", 15.66f);
		
		try {
			aims.store.addMedia(b1);
			aims.store.addMedia(b2);
			aims.store.addMedia(cd1);
			aims.store.addMedia(cd2);
			aims.store.addMedia(dvd1);
			aims.store.addMedia(dvd2);
		}
		catch(Exception npe) {
			npe.printStackTrace();
		}
		
		new StoreScreen(aims);
	}
}
