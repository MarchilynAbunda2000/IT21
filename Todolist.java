/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package todolist;

import javax.swing.JOptionPane;
import java.util.LinkedList;
import java.util.Stack;
/**
 *
 * @author HP
 */
public class Todolist {
    static LinkedList<String> toDoList;
    static LinkedList<String> completedTasks;
    static Stack<String> undoStack;

    public Todolist() {
        toDoList = new LinkedList<>();
        completedTasks = new LinkedList<>();
        undoStack = new Stack<>();
    }
    /**
     * @param args the command line arguments
     */
    
    
    // method
    public static void main(String[] args) {
        // TODO code application logic here
        Todolist manager = new Todolist();
        manager.run();
    }
    
    // run method
    public void run() {
        while (true) {
            String[] options = {"Add Task", "Mark Task as Done", "Undo", "View To-Do List", "View Completed Tasks", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "To-Do List", "Choose an option", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                addTask();
            } else if (choice == 1) {
                markTaskAsDone();
            } else if (choice == 2) {
                undo();
            } else if (choice == 3) {
                viewToDoList();
            } else if (choice == 4) {
                viewCompletedTasks();
            } else if (choice == 5) {
                JOptionPane.showMessageDialog(null, "Thank you and Good day!");
                return;
            }
        }
    }

// add Task method
    static void addTask() {
        String task = JOptionPane.showInputDialog("Enter task to add");
        toDoList.add(task);
        undoStack.push("add:" + task);
    }
// mark task as Done method
    static void markTaskAsDone() {
        if (toDoList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks to mark as done.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toDoList.size(); i++) {
            sb.append((i + 1) + ". " + toDoList.get(i) + "\n");
        }
        String taskList = sb.toString();

        String taskNumberStr = JOptionPane.showInputDialog("To-Do List:\n" + taskList + "\nEnter number of task to mark as done");
        int taskNumber = Integer.parseInt(taskNumberStr);

        if (taskNumber < 1 || taskNumber > toDoList.size()) {
            JOptionPane.showMessageDialog(null, "Invalid task number.");
            return;
        }

        String task = toDoList.remove(taskNumber - 1);
        completedTasks.add(task);
        undoStack.push("done:" + task);
    }

    // undo method 
    static void undo() {
        if (undoStack.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No actions to undo.");
            return;
        }

        String action = undoStack.pop();
        String[] parts = action.split(":");

        if (parts[0].equals("add")) {
            toDoList.remove(parts[1]);
        } else if (parts[0].equals("done")) {
            completedTasks.remove(parts[1]);
            toDoList.add(parts[1]);
        }
    }
// view To Do List method
    static void viewToDoList() {
        StringBuilder sb = new StringBuilder();
        for (String task : toDoList) {
            sb.append(task + "\n");
        }
        JOptionPane.showMessageDialog(null, "To-Do List:\n" + sb.toString());
    }

    // view Completed Tasks method
    static void viewCompletedTasks() {
        StringBuilder sb = new StringBuilder();
        for (String task : completedTasks) {
            sb.append(task + "\n");
        }
        JOptionPane.showMessageDialog(null, "Completed Tasks:\n" + sb.toString());
    }
}
    
    

