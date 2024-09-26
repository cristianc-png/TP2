package tp.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import tp.services.SucursalService;

public class GrafoCanvas extends Canvas{
	private void drawArrow(Graphics2D g2, int x1, int y1, int x2, int y2) {
        g2.drawLine(x1, y1, x2, y2);

        double angle = Math.atan2(y2 - y1, x2 - x1);
        int arrowSize = 10;
        
        int x3 = (int) (x2 - arrowSize * Math.cos(angle - Math.PI / 6));
        int y3 = (int) (y2 - arrowSize * Math.sin(angle - Math.PI / 6));
        int x4 = (int) (x2 - arrowSize * Math.cos(angle + Math.PI / 6));
        int y4 = (int) (y2 - arrowSize * Math.sin(angle + Math.PI / 6));


        g2.drawLine(x2, y2, x3, y3);
        g2.drawLine(x2, y2, x4, y4);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		//Creo los nodos
		Node nodo1 = new Node(30, 140, 30, 30);
		Node nodo2 = new Node(180, 40, 30, 30);
		Node nodo3 = new Node(180, 140, 30, 30);
		Node nodo4 = new Node(330, 40, 30, 30);
		Node nodo5 = new Node(480, 40, 30, 30);
		Node nodo6 = new Node(330, 140, 30, 30);
		Node nodo7 = new Node(330, 240, 30, 30);
		Node nodo8 = new Node(180, 240, 30, 30);
		Node nodo9 = new Node(480, 140, 30, 30);
		Node nodo10 = new Node(630, 140, 30, 30);
        
		SucursalService sucursalService = SucursalService.getInstance();

        g2.setColor(Color.MAGENTA);
        Node nodo2aux = new Node(165, 40, 30, 30);
        Node nodo3aux = new Node(165, 140, 30, 30);
        Node nodo8aux = new Node(165, 240, 30, 30);
        //ruta 1
        //drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo2aux.getCenterX(), nodo2aux.getCenterY());
        //ruta 2
        //drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo3aux.getCenterX(), nodo3aux.getCenterY());
        //ruta 3
        //drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo8aux.getCenterX(), nodo8aux.getCenterY());
        Node nodo3aux2 = new Node(180, 125, 30, 30);
        Node nodo4aux = new Node(315, 40, 30, 30);
        //ruta 4
        //drawArrow(g2, nodo2.getCenterX(), nodo2.getCenterY(), nodo3aux2.getCenterX(), nodo3aux2.getCenterY());
        //ruta 5-B-D
        //drawArrow(g2, nodo2.getCenterX(), nodo2.getCenterY(), nodo4aux.getCenterX(), nodo4aux.getCenterY());
        //ruta 6-C-D
        //drawArrow(g2, nodo3.getCenterX(), nodo3.getCenterY(), nodo4aux.getCenterX(), nodo4aux.getCenterY());
        Node nodo6aux = new Node(315, 140, 30, 30);
        Node nodo3aux3 = new Node(180, 155, 30, 30);
        //ruta 7-C-F
        //drawArrow(g2, nodo3.getCenterX(), nodo3.getCenterY(), nodo6aux.getCenterX(), nodo6aux.getCenterY());
        //ruta 8-H-C
        //drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo3aux3.getCenterX(), nodo3aux3.getCenterY());
        //ruta 9-H-G
        Node nodo7aux2 = new Node(315, 240, 30, 30);
        //drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo7aux2.getCenterX(), nodo7aux2.getCenterY());
        //ruta 10-H-I
        Node nodo9aux = new Node(465, 140, 30, 30);
        //drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo9aux.getCenterX(), nodo9aux.getCenterY());
        //ruta 11-D-E
        Node nodo5aux = new Node(465, 40, 30, 30);
        //drawArrow(g2, nodo4.getCenterX(), nodo4.getCenterY(), nodo5aux.getCenterX(), nodo5aux.getCenterY());
        //ruta 12-D-F
        Node nodo6aux2 = new Node(330, 125, 30, 30);
        //drawArrow(g2, nodo4.getCenterX(), nodo4.getCenterY(), nodo6aux2.getCenterX(), nodo6aux2.getCenterY());
        //ruta 13-F-G
        Node nodo7aux = new Node(330, 225, 30, 30);
        //drawArrow(g2, nodo6.getCenterX(), nodo6.getCenterY(), nodo7aux.getCenterX(), nodo7aux.getCenterY());
        //ruta 14-F-I
        //drawArrow(g2, nodo6.getCenterX(), nodo6.getCenterY(), nodo9aux.getCenterX(), nodo9aux.getCenterY());
        //ruta 15-E-Centro
        Node nodo10aux = new Node(615, 140, 30, 30);
        //drawArrow(g2, nodo5.getCenterX(), nodo5.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
        //ruta 16-G-Centro
        //drawArrow(g2, nodo7.getCenterX(), nodo7.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
        //ruta 17-I-Centro
        //drawArrow(g2, nodo9.getCenterX(), nodo9.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
        
        
        
     // Dibujo las aristas con flechas
     		if(sucursalService.estadoRuta(1)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo2aux.getCenterX(), nodo2aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo2aux.getCenterX(), nodo2aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(2)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo3aux.getCenterX(), nodo3aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo3aux.getCenterX(), nodo3aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(3)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo8aux.getCenterX(), nodo8aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo1.getCenterX(), nodo1.getCenterY(), nodo8aux.getCenterX(), nodo8aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(4)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo2.getCenterX(), nodo2.getCenterY(), nodo3aux2.getCenterX(), nodo3aux2.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo2.getCenterX(), nodo2.getCenterY(), nodo3aux2.getCenterX(), nodo3aux2.getCenterY());
                
             }
             if(sucursalService.estadoRuta(5)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo2.getCenterX(), nodo2.getCenterY(), nodo4aux.getCenterX(), nodo4aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo2.getCenterX(), nodo2.getCenterY(), nodo4aux.getCenterX(), nodo4aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(6)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo3.getCenterX(), nodo3.getCenterY(), nodo4aux.getCenterX(), nodo4aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo3.getCenterX(), nodo3.getCenterY(), nodo4aux.getCenterX(), nodo4aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(7)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo3.getCenterX(), nodo3.getCenterY(), nodo6aux.getCenterX(), nodo6aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo3.getCenterX(), nodo3.getCenterY(), nodo6aux.getCenterX(), nodo6aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(8)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo3aux3.getCenterX(), nodo3aux3.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo3aux3.getCenterX(), nodo3aux3.getCenterY());
                
             }
             if(sucursalService.estadoRuta(9)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo7aux2.getCenterX(), nodo7aux2.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo7aux2.getCenterX(), nodo7aux2.getCenterY());
                
             }
             if(sucursalService.estadoRuta(10)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo9aux.getCenterX(), nodo9aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo8.getCenterX(), nodo8.getCenterY(), nodo9aux.getCenterX(), nodo9aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(11)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo4.getCenterX(), nodo4.getCenterY(), nodo5aux.getCenterX(), nodo5aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo4.getCenterX(), nodo4.getCenterY(), nodo5aux.getCenterX(), nodo5aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(12)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo4.getCenterX(), nodo4.getCenterY(), nodo6aux2.getCenterX(), nodo6aux2.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo4.getCenterX(), nodo4.getCenterY(), nodo6aux2.getCenterX(), nodo6aux2.getCenterY());
                
             }
             if(sucursalService.estadoRuta(13)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo6.getCenterX(), nodo6.getCenterY(), nodo7aux.getCenterX(), nodo7aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo6.getCenterX(), nodo6.getCenterY(), nodo7aux.getCenterX(), nodo7aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(14)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo6.getCenterX(), nodo6.getCenterY(), nodo9aux.getCenterX(), nodo9aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo6.getCenterX(), nodo6.getCenterY(), nodo9aux.getCenterX(), nodo9aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(15)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo5.getCenterX(), nodo5.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo5.getCenterX(), nodo5.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(16)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo7.getCenterX(), nodo7.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo7.getCenterX(), nodo7.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
                
             }
             if(sucursalService.estadoRuta(17)) {
             	g2.setColor(Color.MAGENTA);
             	drawArrow(g2, nodo9.getCenterX(), nodo9.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
                
             }else {
             	g2.setColor(Color.GRAY);
             	drawArrow(g2, nodo9.getCenterX(), nodo9.getCenterY(), nodo10aux.getCenterX(), nodo10aux.getCenterY());
                
             }
        
		//Dibujo los nodos
        if(sucursalService.estadoSucursal(1)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo1.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo1.getShape());
        }
        if(sucursalService.estadoSucursal(2)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo2.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo2.getShape());
        }
        if(sucursalService.estadoSucursal(3)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo3.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo3.getShape());
        }
        if(sucursalService.estadoSucursal(4)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo4.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo4.getShape());
        }
        if(sucursalService.estadoSucursal(5)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo5.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo5.getShape());
        }
        if(sucursalService.estadoSucursal(6)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo6.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo6.getShape());
        }
        if(sucursalService.estadoSucursal(7)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo7.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo7.getShape());
        }
        if(sucursalService.estadoSucursal(8)) {
        	g2.setColor(Color.ORANGE);
        	g2.fill(nodo8.getShape());
        }else {
        	g2.setColor(Color.GRAY);
        	g2.fill(nodo8.getShape());
        }
        if(sucursalService.estadoSucursal(9)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo9.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo9.getShape());
        }
        if(sucursalService.estadoSucursal(10)) {
        	g2.setColor(Color.ORANGE);
    		g2.fill(nodo10.getShape());
        }else {
        	g2.setColor(Color.GRAY);
    		g2.fill(nodo10.getShape());
        }/*
		
		g2.fill(nodo2.getShape());
		g2.fill(nodo3.getShape());
		g2.fill(nodo4.getShape());
		g2.fill(nodo5.getShape());
		g2.fill(nodo6.getShape());
		g2.fill(nodo7.getShape());
		g2.fill(nodo8.getShape());
		g2.fill(nodo9.getShape());
		g2.fill(nodo10.getShape());*/
		
		//Agregar nombre a las aristas
		g2.setColor(Color.BLACK);
		//g2.drawString("Arista 1", getMedio(arista1.x, arista1.x2), getMedio(arista1.y, arista1.y2));
		g2.drawString("Puerto", 27, 160);
		g2.drawString("B", 191, 60);
		g2.drawString("C", 191, 160);
		g2.drawString("H", 191, 260);
		g2.drawString("D", 341, 60);
		g2.drawString("F", 342, 160);
		g2.drawString("G", 341, 260);
		g2.drawString("E", 492, 60);
		g2.drawString("I", 494, 160);
		g2.drawString("Centro", 627, 160);
		g2.setColor(Color.BLACK);
		g2.drawString("ruta 1", 89, 100);
		g2.drawString("ruta 2", 110, 153);
		g2.drawString("ruta 3", 89, 220);
		g2.drawString("ruta 4", 173, 100);
		g2.drawString("ruta 5", 255, 53);
		g2.drawString("ruta 6", 255, 100);
		g2.drawString("ruta 7", 255, 153);
		g2.drawString("ruta 8", 173, 210);
		g2.drawString("ruta 9", 255, 267);
		g2.drawString("ruta 10", 255, 221);
		g2.drawString("ruta 11", 400, 53);
		g2.drawString("ruta 12", 323, 100);
		g2.drawString("ruta 13", 323, 190);
		g2.drawString("ruta 14", 400, 153);
		g2.drawString("ruta 15", 557, 100);
		g2.drawString("ruta 16", 470, 220);
		g2.drawString("ruta 17", 550, 153);
	}
}

class Node{
	public int x,y,w,h;

	public Node(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Ellipse2D.Float getShape(){ //devuelve la forma, asi el nodo ya sabe como dibujarse
		return new Ellipse2D.Float(this.x, this.y, this.w, this.h);
	}
	public int getCenterX() {
        return x + w / 2;
    }

    public int getCenterY() {
        return y + h / 2;
    }
}
