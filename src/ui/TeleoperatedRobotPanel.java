package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.frcteam1939.ui.RobotNumber;
import com.frcteam1939.ui.RobotPanel;
import com.frcteam1939.ui.ScoreField;
import com.frcteam1939.ui.ScoreLabel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * This class governs the teleoperated graphical user interface.
 * @author Grayson Spidle
 *
 */
public class TeleoperatedRobotPanel extends RobotPanel {
	
	private static final long serialVersionUID = -8832379680749996395L;
	
	private static final FocusListener EDITABILITY_TOGGLER = new FocusListener() {
		
		@Override
		public void focusLost(FocusEvent e) {
			if  (e.getSource().getClass().isAssignableFrom(ScoreField.class)) {
				ScoreField comp = (ScoreField) e.getSource();
				comp.setEditable(false);
			}
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			if  (e.getSource().getClass().isAssignableFrom(ScoreField.class)) {
				ScoreField comp = (ScoreField) e.getSource();
				comp.setEditable(true);
			}
		}
	}; 

	public Font nameFont = new Font("Tahoma", Font.BOLD, 25);
	public Font scoreLabelFont = new Font("Roboto", Font.BOLD, 25);
	public Font scoreFieldFont = new Font("Roboto", Font.PLAIN, 25);
	
	public ScoreField lowGoalField;
	public ScoreField highGoalField;
	public ScoreField gearField;
	public ScoreField totalPointsField;
	public ScoreField blocksField;
	public ScoreField deadBotField;
	
	public ScoreField lowGoalAttempts;
	public ScoreField highGoalAttempts;
	public ScoreField gearAttempts;
	public ScoreField baselineAttempts;
	public ScoreField climbingAttempts;
	
	public RobotNumber name;
	public ScoreLabel lowGoalLabel;
	public ScoreLabel highGoalLabel;
	public ScoreLabel gearLabel;
	public ScoreLabel totalPointsLabel;
	
	public ScoreLabel blocksLabel;
	public ScoreField climbingField;
	public ScoreLabel climbLabel;
	public ScoreLabel deadBotLabel;
	
	
	/**
	 * The constructor.
	 * @param robotNumber The team number.
	 * @param teamColor The team color.
	 */
	public TeleoperatedRobotPanel(int robotNumber, Color teamColor) {
		super();
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBackground(teamColor);
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		name = new RobotNumber(robotNumber);
		name.setForeground(Color.WHITE);
		name.setSize(this.getWidth(), 14);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(nameFont);
		this.add(name, "2, 2, 9, 1, center, fill");
		
		lowGoalLabel = new ScoreLabel("Low Goal");
		lowGoalLabel.setForeground(Color.WHITE);
		lowGoalLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lowGoalLabel.setFont(scoreLabelFont);
		this.add(lowGoalLabel, "2, 6, fill, center");
		
		highGoalLabel = new ScoreLabel("High Goal");
		highGoalLabel.setForeground(Color.WHITE);
		highGoalLabel.setHorizontalAlignment(SwingConstants.LEFT);
		highGoalLabel.setFont(scoreLabelFont);
		this.add(highGoalLabel, "2, 8, fill, center");
		
		gearLabel = new ScoreLabel("Gears");
		gearLabel.setForeground(Color.WHITE);
		gearLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gearLabel.setFont(scoreLabelFont);
		this.add(gearLabel, "2, 10, fill, center");
		
		lowGoalField = new ScoreField();
		lowGoalField.setText("0");
		lowGoalField.setDefaultValue("0");
		lowGoalField.setName("lowGoalField");
		this.add(lowGoalField, "4, 6, fill, fill");
		lowGoalField.setColumns(10);
		
		lowGoalAttempts = new ScoreField();
		lowGoalAttempts.setText("0");
		lowGoalAttempts.setDefaultValue("0");
		this.add(lowGoalAttempts, "6, 6, fill, fill");
		lowGoalAttempts.setColumns(10);
		
		highGoalField = new ScoreField();
		highGoalField.setText("0");
		highGoalField.setDefaultValue("0");
		highGoalField.setName("highGoalField");
		this.add(highGoalField, "4, 8, fill, fill");
		highGoalField.setColumns(10);
		
		highGoalAttempts = new ScoreField();
		highGoalAttempts.setText("0");
		highGoalAttempts.setDefaultValue("0");
		this.add(highGoalAttempts, "6, 8, fill, fill");
		highGoalAttempts.setColumns(10);
		
		gearField = new ScoreField();
		gearField.setEditable(false);
		gearField.setText("0");
		gearField.setDefaultValue("0");
		gearField.setName("gearField");
		this.add(gearField, "4, 10, fill, fill");
		gearField.setColumns(10);
		
		gearAttempts = new ScoreField();
		gearAttempts.setText("0");
		gearAttempts.setDefaultValue("0");
		this.add(gearAttempts, "6, 10, fill, fill");
		gearAttempts.setColumns(10);
		
		blocksLabel = new ScoreLabel("Blocks");
		blocksLabel.setForeground(Color.WHITE);
		blocksLabel.setHorizontalAlignment(SwingConstants.LEFT);
		blocksLabel.setFont(scoreLabelFont);
		this.add(blocksLabel, "2, 12, fill, center");
		
		blocksField = new ScoreField();
		blocksField.setText("0");
		blocksField.setDefaultValue("0");
		this.add(blocksField, "4, 12, fill, fill");
		blocksField.setColumns(10);
		
		climbLabel = new ScoreLabel("Climbing");
		climbLabel.setForeground(Color.WHITE);
		climbLabel.setText("Climbing");
		climbLabel.setHorizontalAlignment(SwingConstants.LEFT);
		climbLabel.setFont(scoreLabelFont);
		this.add(climbLabel, "2, 14, left, default");
		
		climbingField = new ScoreField();
		climbingField.setText("false");
		climbingField.setDefaultValue("false");
		climbingField.setColumns(10);
		this.add(climbingField, "4, 14, fill, default");
		
		climbingAttempts = new ScoreField();
		climbingAttempts.setText("false");
		climbingAttempts.setDefaultValue("false");
		climbingAttempts.setColumns(10);
		this.add(climbingAttempts, "6, 14, fill, default");
		
		deadBotLabel = new ScoreLabel("Dead Bot");
		deadBotLabel.setForeground(Color.WHITE);
		deadBotLabel.setHorizontalAlignment(SwingConstants.LEFT);
		deadBotLabel.setFont(scoreLabelFont);
		this.add(deadBotLabel, "2, 16, left, default");
		
		deadBotField = new ScoreField();
		deadBotField.setText("false");
		deadBotField.setColumns(10);
		this.add(deadBotField, "4, 16, fill, top");
		
		for (ScoreField sf : fields) {
			sf.setEditable(false);
			sf.setFont(scoreFieldFont);
			sf.setHorizontalAlignment(SwingConstants.RIGHT);
			sf.addFocusListener(EDITABILITY_TOGGLER);
		}
		
	}
}
