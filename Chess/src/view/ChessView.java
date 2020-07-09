package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

import model.ChessModel;
import model.Model;
import controller.ChessController;
import controller.Controller;

/**
 * Classe adibita alla gestione grafica del gioco
 */

public class ChessView extends JFrame implements View {

	private static final long serialVersionUID = 1L;
	private Model model;
	private JButton[][] buttons;
	private Controller controller;

	public ChessView() {
		this.model = new ChessModel();
		this.controller = new ChessController(this);
		create();
	}

	/**
	 * Ridisegna la configurazione iniziale
	 */
	private void restart() {
		this.model = new ChessModel();
		this.controller = new ChessController(this);
		onConfigurationChange();
	}

	/**
	 * Crea la grafica della scacchiera
	 */
	private void create() {
		// barra dei menu
		JMenuBar menu = new JMenuBar();
		JMenu file = new JMenu("Opzioni");
		JMenuItem newGame = new JMenuItem("Nuova partita");
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		JMenuItem exit = new JMenuItem("Esci");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		JMenuItem info = new JMenuItem("Info");
		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
						.showMessageDialog(
								null,
								"Elaborato di Programmazione II a cura di:"
										+ '\n'
										+ "Caldana Riccardo VR377851 - Ceoletta Valentina VR381758",
								"Informazioni",
								JOptionPane.INFORMATION_MESSAGE, null);
			}
		});

		this.setJMenuBar(menu);
		menu.add(file);
		file.add(newGame);
		file.add(info);
		file.add(exit);

		// etichetta turno
		JPanel northPanel = new JPanel();
		JLabel turno = new JLabel("Muove il " + controller.getTurn());
		northPanel.add(turno, CENTER_ALIGNMENT);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(8, 8));
		buttons = new JButton[8][8];

		// creazione bottoni
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				centerPanel.add(buttons[i][j] = new JButton(model
						.getPiece(i, j).getImage()));
				buttons[i][j].setPreferredSize(new Dimension(75, 75));

				// colore celle n/b
				if ((i + j) % 2 != 0)
					buttons[i][j].setBackground(Color.BLACK);
				else
					buttons[i][j].setBackground(Color.WHITE);

				// azione bottone
				final int k = i;
				final int w = j;
				buttons[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						controller.onClick(k, w);
						turno.setText("Muove il " + controller.getTurn());
					}

				});

			}
		}
		add(centerPanel, BorderLayout.CENTER);
		add(northPanel, BorderLayout.NORTH);
		this.pack();
	}

	@Override
	public void onConfigurationChange() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				buttons[i][j].setIcon(model.getPiece(i, j).getImage());
			}
		}
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public Model getModel() {
		return model;
	}

	public void colorPiece(int x, int y) {
		buttons[x][y].setBorder(new LineBorder(Color.RED, 2));
	}

	public void noColorPiece(int x, int y) {
		buttons[x][y].setBorder(new LineBorder(Color.GRAY));
	}

	public void showCheckWindow() {
		JOptionPane.showMessageDialog(null, "Scacco!", "Attenzione!",
				JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void showCheckMateWindow() {
		ImageIcon win = new ImageIcon(getClass().getResource("/img/win.png"));
		JOptionPane.showMessageDialog(null, "Scacco matto!",
				"Partita terminata!", JOptionPane.QUESTION_MESSAGE, win);
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/icon.png"));
		int scelta = JOptionPane.showConfirmDialog(null,
				"Vuoi iniziare una nuova partita?", "Nuova Partita",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
		if (scelta == JOptionPane.YES_OPTION) {
			restart();
		} else
			System.exit(EXIT_ON_CLOSE);
	}

	@Override
	public int showPawnUpgrade(int x, int y) {
		JRadioButton[] pieces = new JRadioButton[4];
		pieces[0] = new JRadioButton("Torre", false);
		pieces[1] = new JRadioButton("Cavallo", false);
		pieces[2] = new JRadioButton("Alfiere", false);
		pieces[3] = new JRadioButton("Regina", false);

		JPanel choose = new JPanel();
		ButtonGroup group = new ButtonGroup();
		choose.setLayout(new GridLayout(4, 1));

		choose.add(pieces[0]);
		choose.add(pieces[1]);
		choose.add(pieces[2]);
		choose.add(pieces[3]);

		group.add(pieces[0]);
		group.add(pieces[1]);
		group.add(pieces[2]);
		group.add(pieces[3]);

		int promo = JOptionPane.showConfirmDialog(null, choose,
				"Promozione pedone", JOptionPane.OK_CANCEL_OPTION);
		if (promo == JOptionPane.OK_OPTION) {
			for (int i = 0; i < 4; i++) {
				if (pieces[i].isSelected())
					return i;
			}
			return 4; // rimane pedone
		} else
			return 4; // rimane pedone
	}

	@Override
	public void showTieChessWindow() {
		JOptionPane.showMessageDialog(null, "Pareggio!");
		int scelta = JOptionPane.showConfirmDialog(null,
				"Vuoi iniziare una nuova partita?", "Nuova Partita",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (scelta == JOptionPane.YES_OPTION) {
			restart();
		} else
			System.exit(EXIT_ON_CLOSE);
	}
}