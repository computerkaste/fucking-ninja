package Terminkal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.text.TextAction;

//* thanks to the GNU and some sources
// from Toedter.com
//Author: computerkaste.de

public class JCalender extends JPanel 
//implements ActionListener, KeyListener,FocusListener 
{
	
	protected Day[] days;

	protected JButton[] weeks;
	
	protected JButton selectedDay;

	protected JPanel weekPanel;

	protected JPanel dayPanel;
	
	protected int day;
	
	protected String[] dayNames;
	
	//...
	
	protected Calendar calendar;

	protected Calendar today;

	protected Locale locale;
	
	//...
	
	protected Color selectedColor;
	
	protected Color bgColor;

	protected Color sundayForeground;

	protected Color weekdayForeground;

	protected Color decorationBackgroundColor;
	
	
	//es folgt der Konstruktor
	public JCalender () {
		setName("SuperGeilKalender");
		setBackground(Color.blue);
		
		
		//Farbig machen
		selectedColor=new Color(153,51,255);
		bgColor=new Color(215, 215, 215);
		sundayForeground = new Color(164, 0, 0);
		weekdayForeground = new Color(0, 90, 164);
		decorationBackgroundColor = new Color(210, 228, 238);
		// decorationBackgroundColor = new Color(194, 211, 252);
		// decorationBackgroundColor = new Color(206, 219, 246);
		
		
		
		locale = Locale.getDefault();
		days = new Day[49];
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
					
					days[index] = new Day();
					days[index].setBackground(decorationBackgroundColor);
					//days[index].add(new DecoratorButton());
					
					
					
				} else {
					days[index] = new Day() {
						//private static final long serialVersionUID = -7433645992591669725L;

						public void paint(Graphics g) {
							
							
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

				//days[index].setMargin(new Insets(0, 0, 0, 0));
				//days[index].setFocusPainted(false);
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
		drawDays();
		

		//initialized = true;
		updateUI();
		
	//endlich das Ende des konstruktors 
	}
	
	/**
	 * Hides and shows the week buttons.
	 */
	protected void drawWeeks() {
		Calendar tmpCalendar = (Calendar) calendar.clone();

		for (int i = 1; i < 7; i++) {
			tmpCalendar.set(Calendar.DAY_OF_MONTH, (i * 7) - 6);

			int week = tmpCalendar.get(Calendar.WEEK_OF_YEAR);
			String buttonText = Integer.toString(week);

			if (week < 10) {
				buttonText = "0" + buttonText;
			}

			weeks[i].setText(buttonText);

			if ((i == 5) || (i == 6)) {
				weeks[i].setVisible(days[i * 7].isVisible());
			}
		}
	}
	
	
	
	/**
	 * Hides and shows the day buttons.
	 *but must add day buttons
	*/
	protected void drawDays() {
		Calendar tmpCalendar = (Calendar) calendar.clone();
		tmpCalendar.set(Calendar.HOUR_OF_DAY, 0);
		tmpCalendar.set(Calendar.MINUTE, 0);
		tmpCalendar.set(Calendar.SECOND, 0);
		tmpCalendar.set(Calendar.MILLISECOND, 0);

		int firstDayOfWeek = tmpCalendar.getFirstDayOfWeek();
		tmpCalendar.set(Calendar.DAY_OF_MONTH, 1);

		int firstDay = tmpCalendar.get(Calendar.DAY_OF_WEEK) - firstDayOfWeek;

		if (firstDay < 0) {
			firstDay += 7;
		}

		int i;

		for (i = 0; i < firstDay; i++) {
			days[i + 7].setVisible(false);
			
		}

		tmpCalendar.add(Calendar.MONTH, 1);

		Date firstDayInNextMonth = tmpCalendar.getTime();
		tmpCalendar.add(Calendar.MONTH, -1);

		Date day = tmpCalendar.getTime();
		int n = 0;
		Color foregroundColor = getForeground();

		while (day.before(firstDayInNextMonth)) {
			days[i + n + 7].add(new JLabel(Integer.toString(n + 1)));
			//Datumsübergabe
			days[i+n+7].setDayCalendar(tmpCalendar);
		
			//nur zum testen decoratorButton
			//days[i+n+7].add(new DecoratorButton());
			days[i + n + 7].setVisible(true);
			
			//ändert layout für today
			if ((tmpCalendar.get(Calendar.DAY_OF_YEAR) == today
					.get(Calendar.DAY_OF_YEAR))
					&& (tmpCalendar.get(Calendar.YEAR) == today
							.get(Calendar.YEAR))) {
				days[i + n + 7].setForeground(selectedColor);
				days[i + n + 7].setBorder(BorderFactory.createLineBorder(selectedColor, 4));
			} else {
				days[i + n + 7].setForeground(foregroundColor);
			}

			if ((n + 1) == this.day) {
				days[i + n + 7].setBackground(selectedColor);
				//selectedDay = days[i + n + 7];
			} 
			
			n++;
			tmpCalendar.add(Calendar.DATE, 1);
			day = tmpCalendar.getTime();
		}

		for (int k = n + i + 7; k < 49; k++) {
			days[k].setVisible(false);
			
		}

		drawWeeks();
		drawDayNames();
	}

	/**
	 * Draws the day names of the day columnes.
	 */
	private void drawDayNames() {
		int firstDayOfWeek = calendar.getFirstDayOfWeek();
		DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
		dayNames = dateFormatSymbols.getShortWeekdays();

		int day = firstDayOfWeek;

		for (int i = 0; i < 7; i++) {
			
				if (dayNames[day].length() >= 2) {
					dayNames[day] = dayNames[day]
							.substring(0, 2);
				}
			

			days[i].add( new JLabel(dayNames[day]));

			if (day == 1) {
				days[i].setForeground(sundayForeground);
			} else {
				days[i].setForeground(weekdayForeground);
			}

			if (day < 7) {
				day++;
			} else {
				day -= 6;
			}
		}
	}
	
	//addet buttons mit terminen auf die Tage
	public JButton makeButton(Eintrag ein){
		
		String str=ein.datum+ein.name;
		
		
		
		return new TerminButton(str);
	}
	
	
	
	
	
	
	
	class Day extends JPanel{
		
		private Calendar dayCalendar;
		
		public Day(){
			this.setSize(160, 100);
			this.setBackground(bgColor);
			this.setLayout(new GridLayout(0,1));
			Border border = new BevelBorder( BevelBorder.RAISED );
			this.setBorder(border);
		}
		
		
		
		
		public void setDayCalendar(Calendar cal){
			this.dayCalendar = cal;
		}
		public Calendar getDayCalendar(){
			 return dayCalendar;			
		}
		
	}
	
	
	class TerminButton extends JButton {
		
		public TerminButton(String str){
			super(str);
			this.setBackground(decorationBackgroundColor);
		}
		
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
	}
	
	
	
	class DecoratorButton extends JButton {
		private static final long serialVersionUID = -5306477668406547496L;

		public DecoratorButton() {
			setBackground(decorationBackgroundColor);
			//setContentAreaFilled(true);
			//setBorderPainted(false);
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
