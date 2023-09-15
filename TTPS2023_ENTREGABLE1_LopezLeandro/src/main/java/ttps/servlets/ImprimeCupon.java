package ttps.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;

import javax.imageio.ImageIO;

/**
 * Servlet implementation class ImprimeCupon
 */
public class ImprimeCupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImprimeCupon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext contexto = request.getServletContext();
		Integer cantRemeras = (Integer) contexto.getAttribute("cantidadRemeras");
		cantRemeras--;
		contexto.setAttribute("cantidadRemeras", cantRemeras);
		System.out.println(cantRemeras);
		
		ServletOutputStream outputStream = response.getOutputStream();
		BufferedImage image = new BufferedImage(500, 300, BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics = image.createGraphics();
		graphics.setBackground(Color.WHITE);
		graphics.clearRect(0, 0, 500,300);
		graphics.setFont(new Font("Roboto Condensed", Font.BOLD, 14));
		graphics.setColor(Color.WHITE);
		BufferedImage img = ImageIO.read(this.getServletContext().getResourceAsStream("/WEB-INF/resources/remera.jpg"));
		Image imgAjustada = img.getScaledInstance(500, 300,java.awt.Image.SCALE_SMOOTH);
		graphics.drawImage(imgAjustada, 0, 0, null, null);
		graphics.drawString(request.getParameter("textRemera"), 102, 250);
		graphics.drawString(Integer.toString(ThreadLocalRandom.current().nextInt(1000000,99999999)), 345, 260);
		javax.imageio.ImageIO.write(image, "png", outputStream);
		outputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
