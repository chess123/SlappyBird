import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class SettingsPanel extends JPanel implements ChangeListener, ItemListener {

	private static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private JSlider volume;
	private FlappyPanel game;
	private JCheckBox retroMode;
	private JCheckBox ghostMode;
	private JComboBox defaultValues;

	public SettingsPanel(FlappyPanel game) {
		setLayout(null);
		setBackground(Color.BLUE);
		this.game = game;
		volume = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		add(volume);
		volume.setSize(800, 50);
		volume.setLocation(680, 200);
		volume.setMajorTickSpacing(25);
		volume.setMinorTickSpacing(5);
		volume.setPaintTicks(true);
		volume.setPaintLabels(true);
		volume.setBackground(Color.BLUE);
		volume.setForeground(Color.WHITE);
		volume.addChangeListener(this);
		retroMode = new JCheckBox("Retro mode?");
		add(retroMode);
		retroMode.setSize(200, 40);
		retroMode.setLocation(680, 275);
		retroMode.addItemListener(this);
		retroMode.setBackground(Color.BLUE);
		retroMode.setForeground(Color.WHITE);
		retroMode.setFont(new Font("Arial", Font.PLAIN, 16));
		ghostMode = new JCheckBox("Ghost mode?");
		add(ghostMode);
		ghostMode.setSize(200, 40);
		ghostMode.setLocation(680, 375);
		ghostMode.addItemListener(new ToggleGhost());
		ghostMode.setBackground(Color.BLUE);
		ghostMode.setForeground(Color.WHITE);
		ghostMode.setFont(new Font("Arial", Font.PLAIN, 16));
		defaultValues = new JComboBox(new String[] {"Default", "Original", "Ghost", "Retro", "Real Retro"});
		defaultValues.setSize(200, 40);
		defaultValues.setLocation(480, 175);
		defaultValues.addItemListener(new ToggleDefault());
		defaultValues.setBackground(Color.BLUE);
		defaultValues.setForeground(Color.WHITE);
		defaultValues.setFont(new Font("Arial", Font.PLAIN, 16));
		add(defaultValues);
	}

	private class ToggleGhost implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			game.setGhostPipes(ghostMode.isSelected());
		}
	}
	private class ToggleDefault implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			String chosen = defaultValues.getSelectedItem().toString();
			if (chosen.equals("Default"))	game.setValues(FlappyPanel.GAME_MODE.DEFAULT);
			else if (chosen.equals("Ghost"))	game.setValues(FlappyPanel.GAME_MODE.GHOST);
			else if (chosen.equals("Original"))	game.setValues(FlappyPanel.GAME_MODE.ORIGINAL);
			else if (chosen.equals("Retro"))	game.setValues(FlappyPanel.GAME_MODE.RETRO);
			else if (chosen.equals("Real Retro"))	game.setValues(FlappyPanel.GAME_MODE.REAL_RETRO);
		}
	}

	public void stateChanged(ChangeEvent e) {
		game.getBackgroundMusic().setVolume(volume.getValue() / 100.0);
		game.getInvinMusic().setVolume(volume.getValue() / 100.0);
	}

	public void itemStateChanged(ItemEvent e) {
		game.setRetro(retroMode.isSelected());
	}
}