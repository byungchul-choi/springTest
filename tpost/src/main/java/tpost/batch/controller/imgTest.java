package tpost.batch.controller;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public abstract class imgTest{



@SuppressWarnings("serial")

static class Kit extends HTMLEditorKit{

	public Document createDefaultDocument() {
	
	HTMLDocument doc = (HTMLDocument) super.createDefaultDocument();
	
	doc.setTokenThreshold(Integer.MAX_VALUE);
	
	doc.setAsynchronousLoadPriority(-1);
	
	return doc;
	
	}

}



public static BufferedImage create(String src, int width, int height){
	
	BufferedImage image = null;
	
	JEditorPane pane = new JEditorPane();
	
	Kit kit = new Kit();
	
	pane.setEditorKit(kit);
	
	pane.setEditable(false);
	
	pane.setMargin(new Insets(0,20,0,20));
	
	pane.setContentType("text/html; charset=UTF-8");
	
	
	
	try{
		
		pane.setPage(new URL(src));
		
		// HTML 내용을 콘솔창 출력.
		
		System.out.println(pane.getText());
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.createGraphics();
		
		Container c = new Container();
		
		SwingUtilities.paintComponent(g, pane, c, 0, 0, width, height);
		
		g.dispose();
		
	}catch (Exception e) {
	
		System.out.println(e);
	
	}
	
	return image;



}



	public static void batchtest_img (String url, String path){
		
		BufferedImage ire;
		
		// 서버의 웹서버로 접근.
		
//		url="http://localhost/print.jsp";
		
		//String url="file:///D:\\print.html"; 서버의 파일을 부를 때.
		
		// 저장될 이미지 위치와 이름
		
//		path="D:\\tmp1.jpg";
		
		// 이미지 크기.
		ire = imgTest.create(url, 800, 800);
		
		try{
		
			ImageIO.write(ire, "PNG", new File(path));
		
		}catch(IOException e){
		
			e.printStackTrace();
		
		}catch(IllegalArgumentException e){
		
			e.printStackTrace();
		
		}
		
	}

}