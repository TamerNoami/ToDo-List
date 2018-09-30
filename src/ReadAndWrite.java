/**
 * This class will be responsible for reading and writing using txt file.
 * This class is under development 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


public class ReadAndWrite  {
	
	TaskManager TM;
	FileOutputStream fileOut ;
	FileInputStream fileIn ;
	BufferedReader reader ;
	Task task;
	
	
	public ReadAndWrite(TaskManager taskManager) throws FileNotFoundException
	{
		//fileOut = new FileOutputStream("DataFile.txt");
		//fileIn = new FileInputStream("DataFile.txt");
		//reader = new BufferedReader(new FileReader("DataFile.txt"));
		this.TM = taskManager;
		
		
	}
	
	public void writeToFile(Map<String,List<Task>> ListOfTasks) 
		{
		try
		{
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(ListOfTasks.toString().replace('=', ' ').replace('[', ' ').replace(']', ' ').replace(',', ' ').replaceAll("�� t �", " "));
		
		out.close();
		fileOut.close();
		System.out.println("\nSerialization Successful... Checkout your specified output file..\n");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		}
		
	public void writeToFile(List<Task> ListOfTasks) throws FileNotFoundException 
	{
		fileOut = new FileOutputStream("DataFile.txt");
	try
	{
	ObjectOutputStream out = new ObjectOutputStream(fileOut);
	out.writeObject(ListOfTasks.toString().replace('=' , ' ').replace(',' , ' '));
	
	out.close();
	fileOut.close();
	System.out.println("\nSerialization Successful... Checkout your specified output file..\n");
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	}

	public void readFromFile() throws IOException, ClassNotFoundException, ParseException
	{
		//BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("DataFile.txt"));
			String line = reader.readLine();
			while (line != null && !line.equals("]"))
			{
				String line1 = reader.readLine().trim();
				String line2 = reader.readLine().trim();
				String line3 = reader.readLine().trim();
				String line4 = reader.readLine().trim();
				String line5 = reader.readLine().trim();
				task=new Task();
				boolean st = line4.equalsIgnoreCase("false") ? false : true;
				task.setter(line1, line2,TM.StringToDate(line3),st);
				TM.AddTask2(line1,task);
				//System.out.println(line1+"  " +line2+"  " +line3+"  " +line4+"  " +line5);
				line = reader.readLine().trim();
			}
			reader.close();
			//System.exit(0);
			}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	}

