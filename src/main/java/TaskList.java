import javax.swing.*;
import java.awt.*;

public class TaskList extends JPanel {

    TaskList() {
        this.setLayout(new GridLayout(10,1,0,10));
        this.setPreferredSize(new Dimension(400, 200));
    }

    public void updateNumbers() {
        Component[] taskList = this.getComponents();
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] instanceof Task) {
                ((Task) taskList[i]).changeIndex(i+1);
            }
        }
    }
}
