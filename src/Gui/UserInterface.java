package Gui;

import DataStructures.BitStructure;

import java.nio.charset.Charset;
import java.util.*;
import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UserInterface extends Applet implements ActionListener{
    JTextArea title1;
    JTextArea title2;
    JTextArea textA;
    JTextArea textF;
    JButton b1,b2;

    public void init(){
        setSize(600,600);
        title1 = new JTextArea("Please enter plaintext:");
        textA=new JTextArea("",10,50);
        textA.setBackground(Color.pink);
        title2 = new JTextArea("The encoded text is:");
        textF=new JTextArea("",10,50);
        textF.setBackground(Color.cyan);
        b1=new JButton("Encode");
        b2=new JButton("Reset");
        textF.setEditable(false);
        title1.setEditable(false);
        title2.setEditable(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(title1);
        add(textA);
        add(title2);
        add(textF);
        add(b1);
        add(b2);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1){
            String s=textA.getText();   //get the user's input as String type

            //operation to perform encoding process
            byte[] bArr = s.getBytes(Charset.forName("UTF-8"));
            String string = "";
            for (int i = 0; i < bArr.length; i++) {
                new BitStructure(bArr[i], 16);
            }


            textF.setText(bArr.toString()); // set the encoded information on this board
        }
        else if(e.getSource()==b2){
            textA.setText(null);
            textF.setText(null);
        }
    }
}