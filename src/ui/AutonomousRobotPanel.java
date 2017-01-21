package ui;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import buildingBlocks.RobotNumber;
import buildingBlocks.RobotPanel;
import buildingBlocks.ScoreField;
import buildingBlocks.ScoreLabel;
import buildingBlocks.ValueChangeEvent;
import buildingBlocks.ValueChangeListener;

/**
 * This class governs all the teleoperated interface and data.
 * @author Grayson Spidle
 *
 */
public class AutonomousRobotPanel extends RobotPanel {
	private static final long serialVersionUID = -8832379680749996395L;

	public Font nameFont = new Font("Tahoma", Font.BOLD, 15);
	public Font scoreLabelFont = new Font("Roboto", Font.PLAIN, 15);
	public Font scoreFieldFont = new Font("Roboto", Font.PLAIN, 15);
	
	public ScoreField lowGoalField;
	public ScoreField highGoalField;
	public ScoreField gearField;
	public ScoreField baselineField;
	public ScoreField totalPointsField;

	public ScoreField lowGoalAttempts;
	public ScoreField highGoalAttempts;
	public ScoreField gearAttempts;
	
	public RobotNumber name;
	
	public ScoreLabel lowGoalLabel;
	public ScoreLabel highGoalLabel;
	public ScoreLabel gearLabel;
	public ScoreLabel baselineLabel;
	public ScoreLabel totalPointsLabel;
	
	
	/**
	 * The constructor.
	 * @param robotNumber The team number.
	 * @param teamColor The team color.
	 */
	public AutonomousRobotPanel(String robotNumber, Color teamColor) {
		super();
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBackground(teamColor);
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		name = new RobotNumber(robotNumber);
		name.setSize(this.getWidth(), 14);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(nameFont);
		this.add(name, "2, 2, 9, 1, center, fill");
		
		lowGoalLabel = new ScoreLabel("Low Goal");
		lowGoalLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lowGoalLabel.setFont(scoreLabelFont);
		this.add(lowGoalLabel, "2, 6, fill, center");
		
		highGoalLabel = new ScoreLabel("High Goal");
		highGoalLabel.setHorizontalAlignment(SwingConstants.LEFT);
		highGoalLabel.setFont(scoreLabelFont);
		this.add(highGoalLabel, "2, 8, fill, center");
		
		gearLabel = new ScoreLabel("Gears");
		gearLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gearLabel.setFont(scoreLabelFont);
		this.add(gearLabel, "2, 10, fill, center");
		
		baselineLabel = new ScoreLabel("Baseline");
		baselineLabel.setHorizontalAlignment(SwingConstants.LEFT);
		baselineLabel.setFont(scoreLabelFont);
		this.add(baselineLabel, "2, 12, fill, center");
		
		lowGoalField = new ScoreField();
		lowGoalField.setHorizontalAlignment(SwingConstants.RIGHT);
		lowGoalField.setFont(scoreFieldFont);
		lowGoalField.setText("0");
		lowGoalField.setEditable(false);
		this.add(lowGoalField, "4, 6, fill, fill");
		lowGoalField.setColumns(10);
		
		lowGoalAttempts = new ScoreField();
		lowGoalAttempts.setHorizontalAlignment(SwingConstants.RIGHT);
		lowGoalAttempts.setFont(scoreFieldFont);
		lowGoalAttempts.setText("0");
		lowGoalAttempts.setEditable(false);
		this.add(lowGoalAttempts, "6, 6, fill, fill");
		lowGoalAttempts.setColumns(10);
		
		highGoalField = new ScoreField();
		highGoalField.setFont(scoreFieldFont);
		highGoalField.setText("0");
		highGoalField.setHorizontalAlignment(SwingConstants.RIGHT);
		highGoalField.setEditable(false);
		this.add(highGoalField, "4, 8, fill, fill");
		highGoalField.setColumns(10);
		
		highGoalAttempts = new ScoreField();
		highGoalAttempts.setHorizontalAlignment(SwingConstants.RIGHT);
		highGoalAttempts.setFont(scoreFieldFont);
		highGoalAttempts.setText("0");
		highGoalAttempts.setEditable(false);
		this.add(highGoalAttempts, "6, 8, fill, fill");
		highGoalAttempts.setColumns(10);
		
		gearField = new ScoreField();
		gearField.setEditable(false);
		gearField.setHorizontalAlignment(SwingConstants.RIGHT);
		gearField.setFont(scoreFieldFont);
		gearField.setText("0");
		this.add(gearField, "4, 10, fill, fill");
		gearField.setColumns(10);
		
		gearAttempts = new ScoreField();
		gearAttempts.setHorizontalAlignment(SwingConstants.RIGHT);
		gearAttempts.setFont(scoreFieldFont);
		gearAttempts.setText("0");
		gearAttempts.setEditable(false);
		this.add(gearAttempts, "6, 10, fill, fill");
		gearAttempts.setColumns(10);
		
		baselineField = new ScoreField();
		baselineField.setEditable(false);
		baselineField.setText("false");
		baselineField.setName("baselineField");
		baselineField.setHorizontalAlignment(SwingConstants.RIGHT);
		baselineField.setFont(scoreFieldFont);
		this.add(baselineField, "4, 12, fill, fill");
		baselineField.setColumns(10);
		
		
	}
}
