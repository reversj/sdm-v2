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

    private final SoftwareDevServer sdmserv;
    private final JFrame frame = new JFrame();
    private final Container top = new Container();
    private JButton btnsend = new JButton("Send");
    private JTextArea txtarea = new JTextArea();
    private JTextArea txtinput = new JTextArea();

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
            sdmserv.sys.addToList(message);
            txtarea.append("Server: " + message + "\n");
        }
    }
    
    public void txtAreaAppend(String printMessage){
        txtarea.append(printMessage);
    }
}
