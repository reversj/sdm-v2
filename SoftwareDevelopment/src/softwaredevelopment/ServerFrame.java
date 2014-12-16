/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package softwaredevelopment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Niels Riemersma (Jan ter Schure)
 */
public class ServerFrame extends JFrame {

    private SoftwareDevServer softDevServer;
    private Container frameTop = new Container();
    private JTextArea txtArea;
    private JLabel lblFrame;
    private JScrollPane scrollPane;
    private Font myFont = new Font("Arial", Font.BOLD, 12);

    public ServerFrame() {
        softDevServer = new SoftwareDevServer();
        lblFrame = new JLabel("KRUISPUNT SIM");
        txtArea = new JTextArea();
        scrollPane = new JScrollPane(txtArea);
        
        frameTop.setLayout(new GridLayout(1, 3));
        frameTop.add(lblFrame);
        this.add(frameTop, BorderLayout.NORTH);
        txtArea.setFont(myFont);
        txtArea.setBackground(Color.LIGHT_GRAY);
        txtArea.append("\n" + "Sietse van der Werf\n" + "Niels Riemersma\n" + "NHL Informatica\n" + "SDM --- 2014");
        txtArea.setEditable(false);
        
        this.add(scrollPane);
    }

    public void txtAreaAppend(String printMessage) {
        txtArea.append(printMessage + "\n");
    }
}
