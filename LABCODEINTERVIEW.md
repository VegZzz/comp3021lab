Code Interview for Lab Group 1 (Lab 2)

Task 1:

Design & Implementation:

First, I acknowledge the classes that I need to create based on the class diagram in the pdf file. Then, I complete the implementation for the classes except for Notebook because they are relatively easy and will be used in Notebook class. In the end, I finish the Notebook class. 

Difficult Points Encountered:

There are two difficult points I encounter in this lab. First, I use the eclipse-generated equals(Note other) function in Note class as I miss the special note in pdf. Thus, I fail the test case as this equals(Note other) only requires us to compare the title. The second point is insertNote(String foldername, Note note) function in Notebook class. This function can be divided into two parts. The first part is to allocate the target folder if it exists else we create one, which can be done by simply traversing the folder list. The second part is to traverse the target folder to see whether it contains a same-name note. If so, report error else add the corresponding note.

Task 2:

Design Overview:

We will need to check whether the source folder and the target folder exist, which might lead to transfer failure (e.g., you cannot move a note from a non-existing folder or to a non-existing folder). Also, we need to check whether the note exists in the source folder and in the target folder (e.g., you cannot move a non-existing note or move a note to a target folder that contains a same-name note). Then, simply delete the reference to the note in the source folder and add a reference to the note in the target folder. Note that only the references are deleted and added while the note object itself does not change. For each successful move, we use a String to record the moving info.


Possible Data Structures:

No need.


Important Algorithms:

No need.


New Member Variable and Member Function:

ArrayList<String> movingHistory = new ArrayList<String>(); // member variable in Notebook

public void removeNote(Note note) // member function in Folder class
{
	notes.remove(note);
}

public Note searchNote(String title) // member function in Folder class
{
	for(Note i : notes)
	{
		if(i.getTitle().equals(title)
		{
			return i;
		}
	}
	return null;
}

public Folder searchFolder(String name) // member function in Notebook class
{
	for(Folder i : folders)
	{
		if(i.getName().equals(name)
		{
			return i;
		}
	}
	return null;
}

public boolean moveNote(String name, String source, String target) // member function in Notebook class
{
	Folder sourceFolder = searchFolder(source);
	Folder targetFolder = searchFolder(target);
	
	if(sourceFolder==null||targetFolder==null)
	{
		return false;
	}
	
	if(sourceFolder.searchNote(name)==null||targetFolder.searchNote(name)!=null)
	{
		return false;
	}
	
	Note n = sourceFolder.searchNote(name);
	
	targetFolder.addNote(n);
	sourceFolder.removeNote(n);
	
	movingHistory.add("Move note "+name+" from folder "+source+" to folder "+target+" "+new Date(System.currentTimeMillis()).toString());
	return true;
}


Possible Failure:

Stated in the overview.
	



