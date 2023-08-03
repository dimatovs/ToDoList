import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainFrame extends JFrame {
    HeaderPanel headerPanel;
    TaskList taskList;
    DBConnector dbConnector;

    public MainFrame() {
        headerPanel = new HeaderPanel();
        taskList = new TaskList();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,600);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - this.getWidth())/2);
        int y = (int) ((dimension.getHeight() - this.getWidth())/2);
        setLocation(x,y);

        add(headerPanel, BorderLayout.SOUTH);
        add(taskList, BorderLayout.CENTER);
        add(new JLabel("Список дел"), BorderLayout.NORTH);
        setVisible(true);
        showTasks();
        addListeners();
    }

    public void addListeners() {
        dbConnector = new DBConnector();
        headerPanel.getNewTaskBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (headerPanel.getText().equals("")) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Задача не может быть пустой");
                    return;
                }
                dbConnector.addTask(headerPanel.getText());
                showTasks();
            }
        });
        headerPanel.getDeleteDoneTasksBtn().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dbConnector.deleteDoneTasks();
                showTasks();
            }
        });
    }

    public void showTasks() {
        taskList.removeAll();
        dbConnector = new DBConnector();
        List<Task> result = dbConnector.getAllTasks();
        if (result == null) {
            return;
        }
        for (Task task : result) {
            taskList.add(task);
        }
        taskList.updateNumbers();
        headerPanel.clearTextField();
        revalidate();
        repaint();
    }
}
