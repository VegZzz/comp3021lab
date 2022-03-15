package base;

import java.util.Date;
import java.io.Serializable;

public class Note implements Comparable<Note>,Serializable{
	private Date date;
	private String title;
	
	@Override
	public int compareTo(Note other)
	{
		if(date.compareTo(other.date)==0)
		{
			return 0;
		}else if(date.compareTo(other.date)>0)
		{
			return -1;
		}else
		{
			return 1;
		}
	}
	
	public Note(String title) 
	{
		this.title = new String(title);
		date = new Date(System.currentTimeMillis());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	public boolean equals(Note other) {
		return title.equals(other.title);
	}

	public String getTitle() {
		return title;
	}
	
	public String toString()
	{
		return date.toString()+"\t"+title;
	}

}


