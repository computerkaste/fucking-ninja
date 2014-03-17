package Terminkal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//* thanks to the GNU and some sources
// from Toedter.com
//Author: computerkaste.de

public class JCalender extends JPanel 
//implements ActionListener, KeyListener,FocusListener 
{
	
	protected JButton[] days;

	protected JButton[] weeks;
	
	protected JButton selectedDay;

	protected JPanel weekPanel;

	protected JPanel dayPanel;
	
	//...
	
	protected Calendar calendar;

	protected Calendar today;

	protected Locale locale;
	
	//...
	
	protected Color selectedColor;

	protected Color sundayForeground;

	protected Color weekdayForeground;

	protected Color decorationBackgroundColor;
	
	
	//es folgt der Konstruktor
	public JCalender () {
		setName("SuperGeilKalender");
		setBackground(Color.blue);
		
		
		//Farbig machen
		selectedColor=new Color(153,51,255);
		sundayForeground = new Color(164, 0, 0);
		weekdayForeground = new Color(0, 90, 164);
		decorationBackgroundColor = new Color(210, 228, 238);
		// decorationBackgroundColor = new Color(194, 211, 252);
		// decorationBackgroundColor = new Color(206, 219, 246);
		
		
		
		locale = Locale.getDefault();
		days = new JButton[49];
		selectedDay = null;
		calendar = Calendar.getInstance(locale);
		today = (Calendar) calendar.clone();
		
		setLayout(new BorderLayout());
		
		//der eig Kalender
		//das dayPanel mit dem Gridlayout 
		dayPanel = new JPanel();
		dayPanel.setLayout(new GridLayout(7, 7));
		
		//Die Superschleife füllt das new GridLayout(7, 7)
		//deshalb auch for(y<7){for(y<7)}
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; x++) {
				int index = x + (7 * y);

				if (y == 0) {
					//Decorbutton versteckt sich in einer inneren Klasse
					days[index] = new DecoratorButton();
				} else {
					days[index] = new JButton("x") {
						//private static final long serialVersionUID = -7433645992591669725L;

						public void paint(Graphics g) {
							
								if (selectedDay == this) {
									g.setColor(selectedColor);
									g.fillRect(0, 0, getWidth(), getHeight());
									
								}
							
							super.paint(g);
						}

					};
					
					//den JButtons(Tagen) kann man folgende
					//Listener mitgeben
					/*
					days[index].addActionListener(this);
					days[index].addKeyListener(this);
					days[index].addFocusListener(this);*/
				}

				days[index].setMargin(new Insets(0, 0, 0, 0));
				days[index].setFocusPainted(false);
				dayPanel.add(days[index]);
			}
		}
		
		weekPanel = new JPanel();
		weekPanel.setLayout(new GridLayout(7, 1));
		weeks = new JButton[7];

		for (int i = 0; i < 7; i++) {
			weeks[i] = new DecoratorButton();
			weeks[i].setMargin(new Insets(0, 0, 0, 0));
			weeks[i].setFocusPainted(false);
			weeks[i].setForeground(new Color(100, 100, 100));

			if (i != 0) {
				weeks[i].setText("0" + (i ));
			}

			weekPanel.add(weeks[i]);
		}
		
		
	

		
		add(dayPanel, BorderLayout.CENTER);

		
		add(weekPanel, BorderLayout.WEST);
		

		//initialized = true;
		updateUI();
		
	//endlich das Ende des konstruktors 
	}
	
	
	
	
	/**
	 * Creates a JFrame with a JDayChooser inside and can be used for testing.
	 * 
	 * @param s
	 *            The command line arguments
	 */
	public static void main(String[] s) {
		JFrame frame = new JFrame("This is a Calendar coded by computerkaste");
		frame.getContentPane().add(new JCalender());
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	class DecoratorButton extends JButton {
		private static final long serialVersionUID = -5306477668406547496L;

		public DecoratorButton() {
			setBackground(decorationBackgroundColor);
			setContentAreaFilled(true);
			setBorderPainted(false);
		}

		/*
		public void addMouseListener(MouseListener l) {
		}*/

		public boolean isFocusable() {
			return false;
		}

		public void paint(Graphics g) {
			//if("windows") ist hier überflüssig,
			//weil kein look&feel
			/*
			if ("Windows".equals(UIManager.getLookAndFeel().getID())) {
				// this is a hack to get the background painted
				// when using Windows Look & Feel
				
				g.setColor(decorationBackgroundColor);
				
				g.fillRect(0, 0, getWidth(), getHeight());
				if (isBorderPainted()) {
					setContentAreaFilled(true);
				} else {
					setContentAreaFilled(false);
				}
			}*/
			super.paint(g);
		}
	};


}
