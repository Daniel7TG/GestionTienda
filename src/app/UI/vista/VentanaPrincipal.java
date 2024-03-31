package app.UI.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusEvent.Cause;
import java.awt.event.FocusListener;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import app.interfaces.Funcionable;
import app.modelos.Catalogo;
import app.modelos.Producto;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

import static mx.edu.tecnm.zitacuaro.sistemas.modelo.Utileria.*;
import java.awt.Insets;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private Funcionable catalogo;

	private JMenuBar barra;
	
	// Panel Menu Opciones
	private JButton registrarButton;
	private JButton consultarButton;
	private JButton eliminarButton;
	private JButton modificarButton;
	private JButton listarButton;
	private JButton ordenarButton;

	// Panel Opciones
	private JButton guardarButton;
	private JButton cancelarButton;

	private JPanel contentPane;
	private PanelCapturaProductos capturaProductosPane;
	private PanelEncabezados panelEncabezados;
	private PanelMenuProductos panelMenuProductos;
	private PanelOpciones panelOpciones;
	private PanelListadoProductos listadoProductosPane;

	// Proveedores
	private PanelMenuCompra panelMenuCompra;
	private JButton registrarCompButton;
	private JButton listarCompButton;

	private PanelCapturaCompra capturaCompraPane;
	// Fin Proveedores
	
	private JMenuItem sMenuProductos;
	private JMenuItem sMenuInventarioRT;
	private JMenuItem sMenuInventarioF;
	private JMenuItem sMenuStock;
	private JMenuItem sMenuListado;

	private JMenuItem pMenuCompra;
	private JMenuItem pMenuListado;

	private JMenu menuStock;
	private JMenu menuClientes;
	private JMenu menuProveedores;
	private JMenu menuContabilidad;
	private JMenu menuEstadistica;
	private JMenu menuConfiguracion;

	private Font font;

	private JButton confirmarButton;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public VentanaPrincipal() {
		font = new Font("Montserrat", Font.BOLD, 13);
		catalogo = new Catalogo();
		contentPane = new JPanel(new BorderLayout()){
			@Override
			public void paint(Graphics g){
				ImageIcon img = new ImageIcon("resources/img/GatoC.jpg");
				g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paint(g);
			}
		};
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/GatoC.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		setExtendedState(MAXIMIZED_BOTH);
		setContentPane(contentPane);


		barra = new JMenuBar();
		barra.setBackground(new Color(30, 2, 140));
		setJMenuBar(barra);

		menuStock = new JMenu("Gestion de Stock");
		barra.add(menuStock);

		sMenuProductos = new JMenuItem("Productos");
		sMenuInventarioRT = new JMenuItem("Inventario en tiempo real");
		sMenuInventarioF = new JMenuItem("Inventario Fisico");
		sMenuStock = new JMenuItem("Stock");
		sMenuListado = new JMenuItem("Listados");
		menuStock.add(sMenuProductos);
		menuStock.add(sMenuInventarioRT);
		menuStock.add(sMenuInventarioF);
		menuStock.add(sMenuStock);
		menuStock.add(sMenuListado);
		sMenuProductos.addActionListener( event -> {
			sMenuProdFunc();
		});
				
		
		menuClientes = new JMenu("Gestion de clientes");
		barra.add(menuClientes);

		pMenuListado = new JMenuItem("Compras");
		pMenuListado.addActionListener(event->{
			pMenuListFunc();
		});
		menuProveedores = new JMenu("Gestion de Provedores");
		menuProveedores.add(pMenuListado);
		barra.add(menuProveedores);

		menuContabilidad = new JMenu("Contabilidad");
		barra.add(menuContabilidad);

		menuEstadistica = new JMenu("Reportes y Analisis Estadistico");
		barra.add(menuEstadistica);

		menuConfiguracion = new JMenu("Configuracion");
		barra.add(menuConfiguracion);

		
		styleMenuItems(
		  sMenuProductos,
		  sMenuInventarioRT,
		  sMenuInventarioF,
		  sMenuStock,
		  sMenuListado
				);
		
		styleMenu(
		  menuStock,
		  menuClientes,
		  menuProveedores,
		  menuContabilidad,
		  menuEstadistica,
		  menuConfiguracion
				);
	}
	private void styleMenuItems(JMenuItem ...items) {
		for(JMenuItem item: items) {
			item.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		}
	}
	private void styleMenu(JMenu ...menus) {
		for(JMenu menu : menus) {
			menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
			menuConfiguracion.setBackground(new Color(38, 3, 180));
			menu.setForeground(new Color(255, 255, 255));
			menu.setFont(font);
			menu.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		}
	}
	
	// Inventario
	private void pMenuListFunc() {
		clearContentPane();
		if(panelMenuCompra != null) return;
		panelMenuCompra = new PanelMenuCompra();
		registrarCompButton = panelMenuCompra.getRegistrarButton();
		registrarCompButton.addActionListener(e->{
			regCompraFunc();
		});
		
		listarCompButton = panelMenuCompra.getListarButton();	
		contentPane.add(panelMenuCompra, BorderLayout.WEST);
		revalidate();
	}
	
	
	public void regCompraFunc() {
		capturaCompraPane = new PanelCapturaCompra(catalogo);
		panelEncabezados = new PanelEncabezados("Registro de Compra");
		panelOpciones = new PanelOpciones(capturaCompraPane.getField(), PanelOpciones.ALL);
		guardarButton = panelOpciones.getGuardarButton();
		cancelarButton = panelOpciones.getCancelarButton();
		confirmarButton = panelOpciones.getConfirmButton();
		
		confirmarButton.addActionListener(ec->{
			 capturaCompraPane.getDetalles();
		});
		
		guardarButton.addActionListener(ec -> {
			capturaCompraPane.guardarCompra();
		});
		cancelarButton.addActionListener(ec -> {
			cancelButton(panelEncabezados, panelMenuCompra, capturaCompraPane, panelOpciones);
		});
		panelEncabezados.add(capturaCompraPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones, BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuCompra, false);
		revalidate();
	}
	
	
	// Stock/Productos
	public void sMenuProdFunc() {
		clearContentPane();
		if(panelMenuProductos != null) return;
		panelMenuProductos = new PanelMenuProductos();
		
		registrarButton = panelMenuProductos.getRegistrarButton();
		registrarButton.addActionListener(e -> {
			regProdFunc();
		});
	
		consultarButton = panelMenuProductos.getConsultarButton();
		eliminarButton = panelMenuProductos.getEliminarButton();
		modificarButton = panelMenuProductos.getModificarButton();
		
		listarButton = panelMenuProductos.getListarButton();
		listarButton.addActionListener(e->{
			listProdFunc();
			revalidate();
		});
		
		ordenarButton = panelMenuProductos.getOrdenarButton();

		contentPane.add(panelMenuProductos, BorderLayout.WEST);
		revalidate();
	}
	
	
	public void regProdFunc() {
		capturaProductosPane = new PanelCapturaProductos(catalogo);
		panelEncabezados = new PanelEncabezados("Registro de Productos");
		panelOpciones = new PanelOpciones(capturaProductosPane.getDescripcionField(), PanelOpciones.BOTH);
		guardarButton = panelOpciones.getGuardarButton();
		cancelarButton = panelOpciones.getCancelarButton();

		guardarButton.addActionListener(ec -> {
			Optional<Producto> p = capturaProductosPane.getProducto();
			if (p.isPresent()) {
				catalogo.add(p.get());
				capturaProductosPane.actualizarTabla();
			}
		});
		cancelarButton.addActionListener(ec -> {
			cancelButton(panelEncabezados, panelMenuProductos, capturaProductosPane, panelOpciones);
		});
		panelEncabezados.add(capturaProductosPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones, BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuProductos , false);
		revalidate();
	}
	
	
	public void listProdFunc() {
		listadoProductosPane = new PanelListadoProductos(catalogo);
		panelEncabezados = new PanelEncabezados("Listado de Productos");
		panelOpciones = new PanelOpciones(null, PanelOpciones.CANCEL);
		
		cancelarButton = panelOpciones.getCancelarButton();
		cancelarButton.addActionListener(e->{
			cancelButton(panelEncabezados, panelMenuProductos, listadoProductosPane, panelOpciones);
		});

		panelEncabezados.add(listadoProductosPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones , BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuProductos , false);
		revalidate();		
	}
	
	
	
	// Utilidades
	
	public void cancelButton(JPanel parent, JPanel menu, JPanel...panels) {
		contentPane.remove(parent);
		parent = null;
		for(JPanel panel : panels) {
			panel = null;
		}
		enableButtons(menu, true);
		repaint();
		System.gc();
	}
	
	public void enableButtons(JPanel panel, boolean status) {
		for(Component comp : panel.getComponents()) {
			if(comp.getClass().equals(JButton.class)) {
				comp.setEnabled(status);
			}
		} 
	}
	
	public void clearContentPane() {
		contentPane.removeAll();
		panelMenuProductos = null;
		panelMenuCompra = null;
		repaint();
	}
	
	
	

}
