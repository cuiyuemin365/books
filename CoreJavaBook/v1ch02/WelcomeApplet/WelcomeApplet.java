import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This applet displays a greeting from the authors.
 *
 * @author Cay Horstmann
 * @version 1.22 2007-04-08
 */
public class WelcomeApplet extends JApplet {
    public void init() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                // 添加布局
                setLayout(new BorderLayout());
                // 生成并添加标签组件
                JLabel label = new JLabel(getParameter("greeting"), SwingConstants.CENTER);
                label.setFont(new Font("Serif", Font.BOLD, 18));
                add(label, BorderLayout.CENTER);
                // 生成并添加面板组件
                JPanel panel = new JPanel();
                // 将两个按钮添加到面板上
                JButton cayButton = new JButton("Cay Horstmann");
                cayButton.addActionListener(makeAction("http://www.horstmann.com"));
                panel.add(cayButton);

                JButton garyButton = new JButton("Gary Cornell");
                garyButton.addActionListener(makeAction("mailto:gary_cornell@apress.com"));
                panel.add(garyButton);

                add(panel, BorderLayout.SOUTH);
            }
        });
    }

    private ActionListener makeAction(final String urlString) {
        return new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    getAppletContext().showDocument(new URL(urlString));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
