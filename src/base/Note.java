package base;

import java.util.Date;

public class Note {
	private Date date;
	private String title;
	
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

}


