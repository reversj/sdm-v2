/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Jan
 */
public class ServerFrame extends JFrame implements ActionListener {

    public SoftwareDevServer sdmserv;
    final JFrame frame = new JFrame();
    final Container top = new Container();
    public JButton btnsend = new JButton("Send");
    public JTextArea txtarea = new JTextArea();
    public JTextArea txtinput = new JTextArea();

    public ServerFrame() {
        sdmserv = new SoftwareDevServer();

        top.setLayout(new GridLayout(1, 3));
        top.add(btnsend);
        top.add(txtinput);
        btnsend.addActionListener(this);
        this.add(top, BorderLayout.NORTH);

        txtarea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtarea);

        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnsend)) {
            String message = txtinput.getText();
            // soft.mServer.sent(message);
            sdmserv.sys.addToList(message);
            txtarea.append("Server: " + message + "\n");
        }
    }
}
