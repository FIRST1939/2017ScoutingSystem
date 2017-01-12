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

/**
 * This class governs all the teleoperated interface and data.
 * @author Grayson Spidle
 *
 */
public class packaged extends RobotPanel {
	
	private static final long serialVersionUID = -8832379680749996395L;

	public Font nameFont = new Font("Tahoma", Font.BOLD, 15);
	public Font scoreLabelFont = new Font("Roboto", Font.PLAIN, 15);
	public Font scoreFieldFont = new Font("Roboto", Font.PLAIN, 15);
	
	public ScoreField lowGoalField;
	public ScoreField highGoalField;
	public ScoreField gearField;
	public ScoreField totalPointsField;
	
	public ScoreField blocksField;
	
	public RobotNumber name = new RobotNumber();
	public ScoreLabel lowGoalLabel;
	public ScoreLabel highGoalLabel;
	public ScoreLabel gearLabel;
	public ScoreLabel totalPointsLabel;
	
	public ScoreLabel blocksLabel;
	private ScoreField climbingField;
	private ScoreLabel climbLabel;
	
	/**
	 * The constructor.
	 * @param robotNumber The team number.
	 * @param teamColor The team color.
	 */
	public packaged(String robotNumber, Color teamColor) {
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
				ColumnSpec.decode("84px:grow"),
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
		
		climbLabel = new ScoreLabel("Blocks");
		climbLabel.setText("Climbing");
		climbLabel.setHorizontalAlignment(SwingConstants.LEFT);
		climbLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
		add(climbLabel, "8, 8, right, default");
		
		climbingField = new ScoreField();
		climbingField.setText("0");
		climbingField.setHorizontalAlignment(SwingConstants.RIGHT);
		climbingField.setFont(new Font("Dialog", Font.PLAIN, 15));
		climbingField.setEditable(false);
		climbingField.setColumns(10);
		add(climbingField, "10, 8, fill, default");
		
		gearLabel = new ScoreLabel("Gears");
		gearLabel.setHorizontalAlignment(SwingConstants.LEFT);
		gearLabel.setFont(scoreLabelFont);
		this.add(gearLabel, "2, 10, fill, center");
		
		blocksLabel = new ScoreLabel("Blocks");
		blocksLabel.setHorizontalAlignment(SwingConstants.LEFT);
		blocksLabel.setFont(scoreLabelFont);
		this.add(blocksLabel, "8, 6, fill, center");
		
		lowGoalField = new ScoreField();
		lowGoalField.setHorizontalAlignment(SwingConstants.TRAILING);
		lowGoalField.setFont(scoreFieldFont);
		lowGoalField.setText("0");
		lowGoalField.setName("lowGoalField");
		lowGoalField.setEditable(false);
		this.add(lowGoalField, "4, 6, fill, fill");
		lowGoalField.setColumns(10);
		
		highGoalField = new ScoreField();
		highGoalField.setFont(scoreFieldFont);
		highGoalField.setText("0");
		highGoalField.setName("highGoalField");
		highGoalField.setHorizontalAlignment(SwingConstants.RIGHT);
		highGoalField.setEditable(false);
		this.add(highGoalField, "4, 8, fill, fill");
		highGoalField.setColumns(10);
		
		gearField = new ScoreField();
		gearField.setEditable(false);
		gearField.setHorizontalAlignment(SwingConstants.RIGHT);
		gearField.setFont(scoreFieldFont);
		gearField.setText("0");
		gearField.setName("gearField");
		this.add(gearField, "4, 10, fill, fill");
		gearField.setColumns(10);
		
		totalPointsLabel = new ScoreLabel("Total Points");
		totalPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalPointsLabel.setFont(scoreLabelFont);
		this.add(totalPointsLabel, "6, 20, fill, center");
		
		totalPointsField = new ScoreField();
		totalPointsField.setEditable(false);
		totalPointsField.setText("0");
		totalPointsField.setHorizontalAlignment(SwingConstants.CENTER);
		totalPointsField.setFont(scoreFieldFont);
		this.add(totalPointsField, "6, 22, fill, fill");
		totalPointsField.setColumns(10);
		
		blocksField = new ScoreField();
		blocksField.setEditable(false);
		blocksField.setText("0");
		blocksField.setHorizontalAlignment(SwingConstants.RIGHT);
		blocksField.setFont(scoreFieldFont);
		this.add(blocksField, "10, 6, fill, fill");
		blocksField.setColumns(10);
		
	}
}