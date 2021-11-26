/**
 * This class creates a splay tree root from an array of work entries, and parses the user commands 
 * Known bugs: del command
 * 
 * @auhtor Hannah Whitmore
 * hwhitmore@brandeis.edu
 * 10/8/20
 * COSI 21 PA2
 */

package main;

public class WorkTimeAnalysisTool {
	
	private WorkEntrySearchNode root;
	
	public boolean haveSearched;
	
	public WorkTimeAnalysisTool(WorkEntry[] entries) {
		this.haveSearched = false;
		if(entries.length > 1) {
			this.root = new WorkEntrySearchNode(entries[0].getActivity());
			this.root.add(entries[0]);
		}
		
		for(int i = 1; i < entries.length; i++) {
			WorkEntry entry = entries[i];
			WorkEntrySearchNode toAdd = new WorkEntrySearchNode(entry.getActivity());
			this.root = this.root.insert(toAdd);
			this.root.add(entry);
		}
	
	}
	
	
	public String parse(String cmd) {
		if (cmd.charAt(0) == 's'){ // search "activity"
			this.haveSearched = true;
			String activity = cmd.substring(8, cmd.length()-1);
			WorkEntrySearchNode toFind = new WorkEntrySearchNode(activity);
			this.root = this.root.search(toFind);
			
			// search returns the node that matches or the last node encountered in the search
			// only want to print if it is a successful search, aka the node we splayed to the root
			// does actually match the activity key we searched for 
		
			// successful search 
			if (this.root.activity.equals(activity)){
				return this.root.getEntryData();
			} else {
				return "";
			}
			
		} else if (cmd.equals("list l")) {
			return this.root.toString();
		} else if (cmd.equals("list r")) {
			return this.root.getByRecent();			
		} else { // command is "del i"
			if (!this.root.getSuccessfulSearch()) {
				throw new IllegalStateException();
			} else if (this.haveSearched) {
				String i = cmd.substring(4);
				int index = Integer.parseInt(i);
				this.root = this.root.del(index);
			}
			return null;
		}
		
	}
	
	
	public WorkEntrySearchNode getRoot() {
		return this.root;
	}
	
	
}
