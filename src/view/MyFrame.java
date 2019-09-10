package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import balls.IModel2ViewAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

/**
 * GUI display of different types of balls bouncing on the screen.
 * 
 * @author BJ Kim and William Su
 */
public class MyFrame extends JFrame {

	/**
	 * For serialization.
	 */
	private static final long serialVersionUID = -3883274713763024864L;

	/**
	 * Creates the content pane of the GUI
	 */
	private JPanel contentPane;

	/**
	 * Model-to-view adapter to communicate model's actions to the view
	 */
	IModel2ViewAdapter model2ViewAdpt = IModel2ViewAdapter.NULL_OBJECT;

	/**
	 * View-to-model adapter to send desired actions to the model
	 */
	private IView2ModelAdapter view2ModelAdpt = IView2ModelAdapter.NULL_OBJECT;

	/**
	 * GUI's Center panel, in which the balls bounce
	 */
	private final JPanel centerPanel = new JPanel() {

		/**
		 * For serialization.
		 */
		private static final long serialVersionUID = -64671824251513701L;

		/**
		 * Overridden paintComponent method to paint a shape in the panel.
		 * @param g The Graphics object to paint on.
		 **/
		public void paintComponent(Graphics g) {

			super.paintComponent(g); //Do everything normally done first, e.g. clear the screen.
			view2ModelAdpt.update(g); //Update the view with using the adapter
		}
	};

	/**
	 * North panel containing buttons and dropdown menu
	 */
	private final JPanel northPanel = new JPanel();

	/**
	 * Make a new Ball button
	 */
	private final JButton makeBallButton = new JButton("Make Ball");

	/**
	 * Clear all Balls button
	 */
	private final JButton clearAllButton = new JButton("Clear All!");

	/**
	 * Drop down menu to select a ball type
	 */
	private final JComboBox<String> dropDown = new JComboBox<String>();

	/**
	 * Starts the frame
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Updates the center panel
	 */
	public void update() {
		centerPanel.repaint();
	}

	/**
	 * Initializes the GUI
	 */
	public void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setToolTipText("Balls will appear here!");

		contentPane.add(centerPanel, BorderLayout.CENTER);

		contentPane.add(northPanel, BorderLayout.NORTH);
		makeBallButton.setToolTipText("Click to make a ball!");
		makeBallButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.addBall(dropDown.getSelectedItem().toString(), centerPanel);
				centerPanel.repaint();
			}
		});

		dropDown.setToolTipText("Select or type the ball you want!");
		dropDown.setEditable(true);
		dropDown.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Straight", "VelChange", "Trippy", "ColorBreathing", "Random" }));

		northPanel.add(dropDown);

		northPanel.add(makeBallButton);
		clearAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.clearAll();
			}
		});

		clearAllButton.setToolTipText("Click to clear all balls!");

		northPanel.add(clearAllButton);
	}

	/**
	 * Create the frame and assign the view-to-model adapter.
	 * 
	 * @param view2ModelAdapter View-to-model adapter to send desired actions to model
	 */
	public MyFrame(IView2ModelAdapter view2ModelAdapter) {
		initGUI();
		view2ModelAdpt = view2ModelAdapter;
	}
}
