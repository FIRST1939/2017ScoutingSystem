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
public class TeleoperatedRobotPanel extends RobotPanel {
	
	private static final long serialVersionUID = -8832379680749996395L;

	public Font nameFont = new Font("Tahoma", Font.BOLD, 15);
	public Font scoreLabelFont = new Font("Roboto", Font.PLAIN, 15);
	public Font scoreFieldFont = new Font("Roboto", Font.PLAIN, 15);
	
	public ScoreField score1Field;
	public ScoreField score2Field;
	public ScoreField score3Field;
	public ScoreField score4Field;
	
	public RobotNumber name = new RobotNumber();
	public ScoreLabel score1 = new ScoreLabel();
	public ScoreLabel score2 = new ScoreLabel();
	public ScoreLabel score3 = new ScoreLabel();
	public ScoreLabel score4 = new ScoreLabel();
	
	/**
	 * The constructor.
	 * @param robotNumber The team number.
	 * @param teamColor The team color.
	 */
	public TeleoperatedRobotPanel(String robotNumber, Color teamColor) {
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
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		name = new RobotNumber(robotNumber);
		name.setSize(this.getWidth(), 14);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(nameFont);
		this.add(name, "2, 2, 9, 1, center, fill");
		
		score1 = new ScoreLabel("score1");
		score1.setHorizontalAlignment(SwingConstants.LEFT);
		score1.setFont(scoreLabelFont);
		this.add(score1, "2, 6, fill, center");
		
		score2 = new ScoreLabel("score2");
		score2.setHorizontalAlignment(SwingConstants.LEFT);
		score2.setFont(scoreLabelFont);
		this.add(score2, "2, 8, fill, center");
		
		score3 = new ScoreLabel("score3");
		score3.setHorizontalAlignment(SwingConstants.LEFT);
		score3.setFont(scoreLabelFont);
		this.add(score3, "2, 10, fill, center");
		
		score4 = new ScoreLabel("score4");
		score4.setFont(scoreLabelFont);
		this.add(score4, "2, 12, fill, center");
		
		score1Field = new ScoreField();
		score1Field.setHorizontalAlignment(SwingConstants.TRAILING);
		score1Field.setFont(scoreFieldFont);
		score1Field.setText("0");
		score1Field.setEditable(false);
		this.add(score1Field, "4, 6, fill, fill");
		score1Field.setColumns(10);
		
		score2Field = new ScoreField();
		score2Field.setFont(scoreFieldFont);
		score2Field.setText("0");
		score2Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score2Field.setEditable(false);
		this.add(score2Field, "4, 8, fill, fill");
		score2Field.setColumns(10);
		
		score3Field = new ScoreField();
		score3Field.setEditable(false);
		score3Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score3Field.setFont(scoreFieldFont);
		score3Field.setText("0");
		this.add(score3Field, "4, 10, fill, fill");
		score3Field.setColumns(10);
		
		score4Field = new ScoreField();
		score4Field.setEditable(false);
		score4Field.setText("0");
		score4Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score4Field.setFont(scoreFieldFont);
		this.add(score4Field, "4, 12, fill, fill");
		score4Field.setColumns(10);
		
	}
}
