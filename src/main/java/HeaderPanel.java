import javax.swing.*;
import java.awt.*;

public class HeaderPanel extends JPanel {
    private final TextField taskText;
    private final JButton newTaskBtn;
    private final JButton deleteDoneTasks;

    public HeaderPanel() {
        taskText = new TextField();
        this.setPreferredSize(new Dimension(400,100));
        taskText.setPreferredSize(new Dimension(200,25));
        newTaskBtn = new JButton("Добавить задачу:");
        deleteDoneTasks = new JButton("Удалить выполненные задачи");
        add(newTaskBtn);
        add(taskText);
        add(deleteDoneTasks);
        newTaskBtn.setVisible(true);
        deleteDoneTasks.setVisible(true);
    }

    public JButton getNewTaskBtn() {
        return newTaskBtn;
    }

    public JButton getDeleteDoneTasksBtn() {
        return deleteDoneTasks;
    }

    public String getText() {
        return taskText.getText();
    }

    public void clearTextField() {
        taskText.setText("");
    }
}
