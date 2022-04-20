package base;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Collections;

public class Folder implements Comparable<Folder>,Serializable{
	
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name = new String(name);
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	@Override
	public String toString()
	{
		int nText = 0;
		int nImage = 0;
		
		for(Note i:notes)
		{
			if(i instanceof TextNote)
			{
				nText++;
			}else if (i instanceof ImageNote)
			{
				nImage++;
			}
		}
		
		return name+":"+nText+":"+nImage;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Folder other)
	{
		return name.compareTo(other.name);
	}
	
	public void sortNotes()
	{
		Collections.sort(notes);
	}
	
	private ArrayList<ArrayList<String>> normalizeKeywords(String keywords)
	{
		int prevESI = -1;
		int curESI = keywords.indexOf(" ", 0); //curEmptySpaceIndex
		ArrayList<ArrayList<String>> n_keywords = new ArrayList<ArrayList<String>>();
		boolean isPrevOr= false; 
		while(curESI!=-1)
		{
			String subStr = keywords.substring(prevESI+1, curESI);
			if(subStr.toLowerCase().equals("or"))
			{
				isPrevOr = true;
			}else
			{
				if(isPrevOr)
				{
					n_keywords.get(n_keywords.size()-1).add(subStr);
				}else
				{
					n_keywords.add(new ArrayList<String>());
					n_keywords.get(n_keywords.size()-1).add(subStr);
				}
				isPrevOr = false;
			}
			prevESI = curESI;
			curESI = keywords.indexOf(" ", prevESI+1);
		}
		
		if(isPrevOr)
		{
			n_keywords.get(n_keywords.size()-1).add(keywords.substring(prevESI+1));
		}else
		{
			n_keywords.add(new ArrayList<String>());
			n_keywords.get(n_keywords.size()-1).add(keywords.substring(prevESI+1));
		}
		
		return n_keywords;
	}
	
	private boolean contains(Note note, String keyword)
	{
		if(note instanceof ImageNote)
		{
			return note.getTitle().toLowerCase().indexOf(keyword.toLowerCase())!=-1;
		}else if(note instanceof TextNote)
		{
			return note.getTitle().toLowerCase().indexOf(keyword.toLowerCase())!=-1
					||((TextNote)note).getContent().toLowerCase().indexOf(keyword.toLowerCase())!=-1;
		}else
		{
			return false;
		}
	}
	
	public ArrayList<Note> searchNotes(String keywords)
	{
		ArrayList<Note> result = new ArrayList<Note>();
		ArrayList<ArrayList<String>> n_keywords = normalizeKeywords(keywords);
		
		for(int i=0;i<notes.size();i++)
		{
			boolean flag1 = true;
			int j=0;
			while(j<n_keywords.size()&&flag1)
			{
				boolean flag2 = true;
				int k = 0;
				while(k<n_keywords.get(j).size()&&flag2)
				{
					if(contains(notes.get(i),n_keywords.get(j).get(k)))
					{
						flag2 = false;
					}else
					{
						k++;
					}
				}
				if(k==n_keywords.get(j).size())
				{
					flag1 = false;
				}else
				{
					j++;
				}
			}
			if(j==n_keywords.size())
			{
				result.add(notes.get(i));
			}
		}
		return result;
	}
	
	public boolean removeNotes(String title) {
		   // TODO
		   // Given the title of the note, delete it from the folder.
		   // Return true if it is deleted successfully, otherwise return false. 
			for(Note i:notes)
			{
				if(i.getTitle().equals(title))
				{
					notes.remove(i);
					return true;
				}
			}
			
			return false;
		}

}
