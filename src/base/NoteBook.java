package base;

import java.util.ArrayList;

public class NoteBook {
	
	private ArrayList<Folder> folders;
	
	public NoteBook()
	{
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName,String title)
	{
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);
	}
	
	public boolean createImageNote(String folderName,String title)
	{
		ImageNote note = new ImageNote(title);
		return insertNote(folderName,note);
	}
	
	public ArrayList<Folder> getFolders()
	{
		return folders;
	}
	
	public boolean insertNote(String foldername,Note note)
	{
		Folder toInsert = null;
		
		for(Folder i:folders)
		{
			if(i.getName().equals(foldername))
			{
				toInsert = i;
				break;
			}
		}
		
		if(toInsert==null)
		{
			toInsert = new Folder(foldername);
			folders.add(toInsert);
		}
		
		for(Note i:toInsert.getNotes())
		{
			if(i.equals(note))
			{
				System.out.println("Creating note "+note.getTitle()+" under folder "+foldername+" failed");
				return false;
			}
		}
		
		toInsert.addNote(note);
		return true;
	}
}
