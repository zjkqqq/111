package UI;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;


//��֤�������
import java.awt.*;
import java.util.*;


//�������ʵ��
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mypanel extends Panel {
	  public void paint(Graphics g)
	  {
	      int height = 50;
	      int width = 90;
	      //��֤��򱳾���ɫ
	      g.setColor(Color.LIGHT_GRAY);
	      //�����֤�뱳��
	      g.fillRect(0, 0, width, height);
	      g.setColor(Color.BLACK);
	      g.drawRect(0, 0, width-1, height-1);
	      Random r = new Random();
	      //���ø��ŵ�
	      for(int i = 0;i<100;i++)
	      {
	          int x = r.nextInt(width)-1;
	          int y = r.nextInt(height)-1;
	          g.drawOval(x, y, 2, 2);
	      }
	      g.setFont(new Font("����",Font.BOLD,20));//������֤�������Լ���С
	      g.setColor(Color.RED);//������֤��������ɫ
	      //���������֤��
	      char[] tmp = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	      StringBuilder sb = new StringBuilder();
	      for(int i = 0;i<4;i++)
	      {
	          int pos = r.nextInt(tmp.length);
	          char c = tmp[pos];
	          sb.append(c + " ");
	      }
	      g.drawString(sb.toString(), 10, 15);//д����֤��
	  }
	}