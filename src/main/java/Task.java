import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Task extends JPanel {
    private final JButton doneBtn;
    private final JLabel index;
    private final int id;
    DBConnector dbConnector;
    static private final Map<Integer,Color> status = new HashMap<>();
    static {
            status.put(0, Color.orange);
            status.put(1, Color.green);
    }

    Task(int id, String task, int state) {
        this.setPreferredSize(new Dimension(300, 40));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.orange);

        index = new JLabel("");
        index.setPreferredSize(new Dimension(20, 20));
        index.setHorizontalAlignment(JLabel.CENTER);
        this.add(index, BorderLayout.WEST);

        JLabel taskText = new JLabel(task);
        taskText.setPreferredSize(new Dimension(220, 40));
        this.add(taskText, BorderLayout.CENTER);

        doneBtn = new JButton("Выполнено");
        doneBtn.setPreferredSize(new Dimension(80, 20));
        this.add(doneBtn, BorderLayout.EAST);
        doneBtn.setFocusPainted(false);
        this.id = id;
        setBackground(status.get(state));
        dbConnector = new DBConnector();
        addListener();
    }

    public JButton getButton() {
        return doneBtn;
    }

    public void changeIndex(int num) {
        this.index.setText(num + "");
        this.revalidate();
    }

    public void setCompleted() {
        setBackground(Color.green);
        dbConnector.updateTask(id,1);
    }

    public void addListener() {
        getButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setCompleted();
            }
        });
    }
}
