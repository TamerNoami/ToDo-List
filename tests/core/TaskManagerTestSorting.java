package core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TaskManagerTestSorting {
	
	private TaskManager tmTasks;
	private List<Task> testCases;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@Before
	public void setUp() throws Exception {
		
		tmTasks = new TaskManager();
		
	}	
		
	

	@After
	public void tearDown() throws Exception {
	}

	

	/*private void addTaskToListTypeA() throws ParseException {
		String project = "project1";
		String title = "title1";
		SimpleDateFormat dateFormat = new SimpleDateFormat(TaskManager.Date_format);
		Date dd = dateFormat.parse("12-12-2018");
		boolean status = false;

		tmTasks.AddTask(project, title, dd, status);
		
	}

	private void addTaskToListTypeB() throws ParseException {
		String project = "project2";
		String title = "title2";
		SimpleDateFormat dateFormat = new SimpleDateFormat(TaskManager.Date_format);
		Date dd = dateFormat.parse("12-12-2017");
		boolean status = true;

		tmTasks.AddTask(project, title, dd, status);
		
	}*/

	@BeforeEach
	public void doStuff() throws ParseException, ClassNotFoundException, IOException {
		tmTasks = new TaskManager();
		testCases = new ArrayList<>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(TaskManager.Date_format);
		Task task1 = new Task();
		task1.setter("project", "title", dateFormat.parse("02-12-2018"), false);
		testCases.add(task1);
		Task task2 = new Task();
		task2.setter("project", "title2", dateFormat.parse("22-11-2018"), true);
		testCases.add(task2);
		Task task3 = new Task();
		task3.setter("project", "title3", dateFormat.parse("14-01-2018"), true);
		testCases.add(task3);
	}	
	
	@ParameterizedTest
	@ValueSource(ints = { 2 })
	@DisplayName("Sort Tow tasks")
	public void testSortTaskTwoTasks(int num) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TaskManager.Date_format);
		for(int i=0; i<num;i++) {
			Task myTask = testCases.get(i);
			String projectName = myTask.getProjectName();
			String title = myTask.getTitle();
			Date DueDate = myTask.getDueDate();
			System.out.println(DueDate);//***
			boolean st = myTask.getTaskStatus().equalsIgnoreCase("ToDO") ? false : true;
			tmTasks.AddTask(projectName, title, DueDate, st);
		}
	
		String date = dateFormat.format(tmTasks.Sorting().get(0).getDueDate());
		System.out.println(date);//***
		assertEquals(true, date.equals("22-11-2018"));
		date = dateFormat.format(tmTasks.Sorting().get(1).getDueDate());
		assertEquals(true, date.equals("02-12-2018"));
	}

	@ParameterizedTest
	@ValueSource(ints = { 3 })
	@DisplayName("Sort three tasks")
	public void testSortTaskthreeTasks(int num) throws ParseException {
		for(int i=0; i<num;i++) {
			Task myTask = testCases.get(i);
			String projectName = myTask.getProjectName();
			String title = myTask.getTitle();
			Date DueDate = myTask.getDueDate();
			System.out.println(DueDate);
			boolean st = myTask.getTaskStatus().equalsIgnoreCase("ToDO") ? false : true;
			tmTasks.AddTask(projectName, title, DueDate, st);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(TaskManager.Date_format);
		String date = dateFormat.format(tmTasks.Sorting().get(0).getDueDate());
		assertEquals(true, date.equals("14-01-2018"));
		date = dateFormat.format(tmTasks.Sorting().get(1).getDueDate());
		assertEquals(true, date.equals("22-11-2018"));
		date = dateFormat.format(tmTasks.Sorting().get(2).getDueDate());
		assertEquals(true, date.equals("02-12-2018"));

	}
}
