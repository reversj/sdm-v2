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
 * @author Niels Riemersma (Jan ter Schure)
 */
public class ServerFrame extends JFrame implements ActionListener {

    private SoftwareDevServer softDevServer;
    private Container frameTop = new Container();
    private JButton btnSend = new JButton("Send");
    private JTextArea txtArea = new JTextArea();
    private JTextArea txtInput = new JTextArea();

    public ServerFrame() {
        softDevServer = new SoftwareDevServer();

        frameTop.setLayout(new GridLayout(1, 3));
        frameTop.add(btnSend);
        frameTop.add(txtInput);
        btnSend.addActionListener(this);
        this.add(frameTop, BorderLayout.NORTH);

        txtArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtArea);

        this.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSend)) {
            String message = txtInput.getText();
            txtArea.append("Server: " + message + "\n");
        }
    }

    public void txtAreaAppend(String printMessage) {
        txtArea.append(printMessage + "\n");
    }
}
