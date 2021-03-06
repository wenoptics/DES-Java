package Gui;

import DataStructures.BitStructure;
import Util.Utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import static javax.xml.bind.DatatypeConverter.printHexBinary;

public class UIEncryptText extends Applet implements ActionListener{
    JTextArea title_key;
    JTextArea title1;
    JTextArea title2;
    JTextArea textA;
    JTextArea textF;
    JTextArea text_key;
    JButton b1,b2;

    public void init(){
        setSize(600,600);
        title1 = new JTextArea("Please enter plaintext:");
        textA=new JTextArea("",10,50);
        textA.setBackground(Color.pink);

        title2 = new JTextArea("The encrypted text is:");
        textF=new JTextArea("",10,50);
        textF.setBackground(Color.cyan);

        title_key = new JTextArea("Enter your key:");
        text_key=new JTextArea("",1,50);
        text_key.setBackground(Color.orange);

        b1=new JButton("Encrypt");
        b2=new JButton("Reset");
        textF.setEditable(false);
        title1.setEditable(false);
        title2.setEditable(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(title_key);
        add(text_key);
        add(title1);
        add(textA);
        add(title2);
        add(textF);
        add(b1);
        add(b2);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1){

            //operation to perform encoding process
            byte[] textByte = textA.getText().getBytes(Charset.forName("UTF-8"));
            byte[] byteKey = text_key.getText().getBytes(Charset.forName("UTF-8"));
            textByte = Utils.encryptBytes(textByte, byteKey);
            // textByte = Utils.decryptBytes(textByte, byteKey);

            //textF.setText(new String(textByte, StandardCharsets.UTF_8)); // set the encoded information on this board
            textF.setText( printHexBinary(textByte) ); // set the encoded information on this board
        }
        else if(e.getSource()==b2){
            textA.setText(null);
            textF.setText(null);
        }
    }
}