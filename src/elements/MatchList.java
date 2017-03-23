package elements;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.adithyasairam.tba4j.models.Match;

public class MatchList {
	
	private Vector<Match> quals = new Vector<Match>();
	private Vector<Match> quarters = new Vector<Match>();
	private Vector<Match> semis = new Vector<Match>();
	private Vector<Match> finals = new Vector<Match>();
	private Vector<Match> extraFinals = new Vector<Match>();
	
	public MatchList(List<Match> matches) {
		for (Match m : matches) {
			String level = m.comp_level;
			if (level.equals("qm")) {
				quals.add(m);
			} else if (level.equals("qf")) {
				quarters.add(m);
			} else if (level.equals("sf")) {
				semis.add(m);
			} else if (level.equals("f")) {
				finals.add(m);
			} else if (level.equals("ef")) {
				extraFinals.add(m);
			} else {
				System.err.println("error in classifying " + m.key);
			}
		}
		sort();
	}
	
	private void sort() {
		Comparator<Match> sorter = new Comparator<Match>() {
			@Override
			public int compare(Match o1, Match o2) {
				int matchComparison = Integer.compare(o1.match_number, o2.match_number);
				int setComparison = Integer.compare(o1.set_number, o2.set_number);
				if (setComparison != 0) return setComparison;
				else return matchComparison;
			}
		};
		quals.sort(sorter);
		quarters.sort(sorter);
		semis.sort(sorter);
		finals.sort(sorter);
		extraFinals.sort(sorter);
	}
	
	public List<Match> getAllMatches() {
		Vector<Match> matches = new Vector<Match>();
		matches.addAll(quals);
		matches.addAll(quarters);
		matches.addAll(semis);
		matches.addAll(finals);
		matches.addAll(extraFinals);
		return matches;
	}

	public List<Match> getQuals() {
		return quals;
	}

	public List<Match> getQuarters() {
		return quarters;
	}

	public List<Match> getSemis() {
		return semis;
	}

	public List<Match> getFinals() {
		return finals;
	}

	public List<Match> getExtraFinals() {
		return extraFinals;
	}
	
	

}
