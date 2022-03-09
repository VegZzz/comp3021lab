package base;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public boolean createTextNote(String foldername,String title,String content)
	{
		TextNote note = new TextNote(title, content);
		return insertNote(foldername, note);
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
	
	public void sortFolders()
	{
		Collections.sort(folders);
	}
	
	public ArrayList<Note> searchNotes(String keywords)
	{
		ArrayList<Note> result = new ArrayList<Note>();
		for(Folder i : folders)
		{
			result.addAll(i.searchNotes(keywords));
		}
		return result;
	}
}
