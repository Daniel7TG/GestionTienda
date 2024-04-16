package app.UI.vista.general;

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

import app.UI.vista.captura.PanelCapturaCompra;
import app.UI.vista.captura.PanelCapturaProductos;
import app.UI.vista.captura.PanelCapturaProveedor;
import app.UI.vista.captura.PanelCapturaVenta;
import app.UI.vista.consulta.PanelConsultaProductos;
import app.UI.vista.eliminar.PanelEliminarProductos;
import app.UI.vista.general.PanelEncabezados;
import app.UI.vista.general.PanelOpciones;
import app.UI.vista.listado.PanelListadoCompras;
import app.UI.vista.listado.PanelListadoProductos;
import app.UI.vista.listado.PanelListadoVentas;
import app.UI.vista.menus.PanelMenuCompra;
import app.UI.vista.menus.PanelMenuProductos;
import app.UI.vista.menus.PanelMenuProveedores;
import app.UI.vista.menus.PanelMenuVenta;
import app.dao.DaoUtility;
import app.interfaces.Funcionable;
import app.modelos.Producto;
import app.modelos.containers.Catalogo;
import app.modelos.containers.HistorialCompra;
import app.modelos.containers.HistorialVenta;
import app.modelos.containers.Proveedores;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;

import static mx.edu.tecnm.zitacuaro.sistemas.modelo.Utileria.*;
import java.awt.Insets;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private Catalogo catalogo;

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
	private PanelConsultaProductos consultaProductosPane;
	private PanelEliminarProductos panelEliminarProductos;
	
	// Clientes
	private HistorialVenta historialVenta;
	private JMenuItem cMenuVenta;
	private PanelMenuVenta panelMenuVenta;
	private PanelCapturaVenta capturaVentaPane;
	private JButton registrarVentButton;
	private JButton listarVentButton;
	private PanelListadoVentas listadoVentasPane;
	// Fin Clientes
	
	// Proveedores
	private Proveedores proveedores;
	
	private HistorialCompra historialCompra;
	private PanelMenuCompra panelMenuCompra;
	private JButton registrarCompButton;
	private JButton listarCompButton;
	private JMenuItem pMenuCompra;
	private JMenuItem pMenuProveedores;
	private PanelListadoCompras listadoComprasPane;
	private PanelCapturaCompra capturaCompraPane;
	
	private JButton registrarProvButton;
	private PanelCapturaProveedor capturaProveedorPane;
	private PanelMenuProveedores panelMenuProveedores;
	// Fin Proveedores
	
	private JMenuItem sMenuProductos;
	private JMenuItem sMenuInventarioRT;
	private JMenuItem sMenuInventarioF;
	private JMenuItem sMenuStock;
	private JMenuItem sMenuListado;


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
		historialCompra = new HistorialCompra();
		historialVenta = new HistorialVenta();
		proveedores = new Proveedores();
		
		// Datos de ejemplo
		DaoUtility.getProductos().forEach(catalogo::add);
		
		contentPane = new JPanel(new BorderLayout()){
			@Override
			public void paint(Graphics g){
				ImageIcon img = new ImageIcon("resources/img/fondo.jpeg");
				g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paint(g);
			}
		};
		
//		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/GatoC.jpg")));
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
		cMenuVenta = new JMenuItem("Venta");
		menuClientes.add(cMenuVenta);
		cMenuVenta.addActionListener(event->{
			cMenuVentFunc();
		});
		barra.add(menuClientes);

		pMenuCompra = new JMenuItem("Compras");
		pMenuCompra.addActionListener(event-> pMenuCompFunc());
		pMenuProveedores = new JMenuItem("Proveedores");
		pMenuProveedores.addActionListener(event-> pMenuProvFunc());
		menuProveedores = new JMenu("Gestion de Provedores");
		menuProveedores.add(pMenuProveedores);
		menuProveedores.add(pMenuCompra);
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
	
	// Clientes
	private void cMenuVentFunc() {
		clearContentPane();
		if(panelMenuVenta != null) return;
		panelMenuVenta = new PanelMenuVenta();
		registrarVentButton = panelMenuVenta.getRegistrarButton();
		registrarVentButton.addActionListener(e->{
			regVentaFunc();
		});
		listarVentButton = panelMenuVenta.getListarButton();
		listarVentButton.addActionListener(e->{
			listVentFunc();
		});
		contentPane.add(panelMenuVenta, BorderLayout.WEST);
		revalidate();
	}
	
	
	public void regVentaFunc() {
		capturaVentaPane = new PanelCapturaVenta(catalogo, historialVenta);
		panelEncabezados = new PanelEncabezados("Registro de Venta");
		panelOpciones = new PanelOpciones(null, PanelOpciones.BOTH);
		guardarButton = panelOpciones.getGuardarButton();
		cancelarButton = panelOpciones.getCancelarButton();
		
		guardarButton.addActionListener(ec -> {
			capturaVentaPane.guardarVenta();
		});
		cancelarButton.addActionListener(ec -> {
			cancelButton(panelEncabezados, panelMenuVenta, capturaVentaPane, panelOpciones);
		});
		panelEncabezados.add(capturaVentaPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones, BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuVenta, false);
		revalidate();
	}
	
	
	public void listVentFunc() {
		listadoVentasPane = new PanelListadoVentas(historialVenta);
		panelEncabezados = new PanelEncabezados("Listado de Ventas");
		panelOpciones = new PanelOpciones(null, PanelOpciones.CANCEL);
		
		cancelarButton = panelOpciones.getCancelarButton();
		cancelarButton.addActionListener(e->{
			cancelButton(panelEncabezados, panelMenuVenta, listadoVentasPane, panelOpciones);
		});

		panelEncabezados.add(listadoVentasPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones , BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuVenta , false);
		revalidate();	
	}
	
	// Inventario / Proveedores
	private void pMenuProvFunc() {
		clearContentPane();
		if(panelMenuProveedores != null) return;
		panelMenuProveedores = new PanelMenuProveedores();
		registrarProvButton = panelMenuProveedores.getRegistrarButton();
		registrarProvButton.addActionListener(e->{
			regProvFunc();
		});
		contentPane.add(panelMenuProveedores, BorderLayout.WEST);
		revalidate();
	}
	
	private void regProvFunc() {
		capturaProveedorPane = new PanelCapturaProveedor(proveedores);
		panelEncabezados = new PanelEncabezados("Registro de Proveedores");
		panelOpciones = new PanelOpciones(capturaProveedorPane, PanelOpciones.BOTH);
		guardarButton = panelOpciones.getGuardarButton();
		cancelarButton = panelOpciones.getCancelarButton();
		
		guardarButton.addActionListener(ec -> {
			capturaProveedorPane.guardarProveedor();
		});
		cancelarButton.addActionListener(ec -> {
			cancelButton(panelEncabezados, panelMenuProveedores, capturaProveedorPane, panelOpciones);
		});
		panelEncabezados.add(capturaProveedorPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones, BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuProveedores, false);
		revalidate();
	}

	private void pMenuCompFunc() {
		clearContentPane();
		if(panelMenuCompra != null) return;
		panelMenuCompra = new PanelMenuCompra();
		registrarCompButton = panelMenuCompra.getRegistrarButton();
		registrarCompButton.addActionListener(e->{
			regCompraFunc();
		});
		listarCompButton = panelMenuCompra.getListarButton();	
		listarCompButton.addActionListener(e->{
			listCompFunc();
		});
		contentPane.add(panelMenuCompra, BorderLayout.WEST);
		revalidate();
	}
	
	
	public void regCompraFunc() {
		capturaCompraPane = new PanelCapturaCompra(catalogo, historialCompra, proveedores);
		panelEncabezados = new PanelEncabezados("Registro de Compra");
		panelOpciones = new PanelOpciones(capturaCompraPane.getField(), PanelOpciones.BOTH);
		guardarButton = panelOpciones.getGuardarButton();
		cancelarButton = panelOpciones.getCancelarButton();
		
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
	
	
	public void listCompFunc() {
		listadoComprasPane = new PanelListadoCompras(historialCompra);
		panelEncabezados = new PanelEncabezados("Listado de Compras");
		panelOpciones = new PanelOpciones(null, PanelOpciones.CANCEL);
		cancelarButton = panelOpciones.getCancelarButton();
		cancelarButton.addActionListener(e->{
			cancelButton(panelEncabezados, panelMenuCompra, listadoComprasPane, panelOpciones);
		});
		panelEncabezados.add(listadoComprasPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones , BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuCompra , false);
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
		consultarButton.addActionListener(e->{
			consProdFunc();
		});
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
	
	
	public void consProdFunc() {
		consultaProductosPane = new PanelConsultaProductos(catalogo);
		panelEncabezados = new PanelEncabezados("Consultar Productos");
		panelOpciones = new PanelOpciones(null, PanelOpciones.CANCEL);
		
		cancelarButton = panelOpciones.getCancelarButton();
		cancelarButton.addActionListener(e->{
			cancelButton(panelEncabezados, panelMenuProductos, consultaProductosPane, panelOpciones);
		});
		
		panelEncabezados.add(consultaProductosPane, BorderLayout.CENTER);
		panelEncabezados.add(panelOpciones , BorderLayout.SOUTH);
		contentPane.add(panelEncabezados, BorderLayout.CENTER);
		enableButtons(panelMenuProductos , false);
		revalidate();				
	}

	
	public void elimProdFunc() {
		eliminarProductosPane = new PanelEliminarProductos(catalogo);
		panelEncabezados = new PanelEncabezados("Eliminar Productos");
		panelOpciones = new PanelOpciones(null, PanelOpciones.BOTH, "Eliminar", "Cancelar");
		
		guardarButton = panelOpciones.getCancelarButton();
		guardarButton.addActionListener(e->{
			eliminarProductosPane.eliminarProducto();
		});
		cancelarButton = panelOpciones.getCancelarButton();
		cancelarButton.addActionListener(e->{
			cancelButton(panelEncabezados, panelMenuProductos, eliminarProductosPane, panelOpciones);
		});
		panelEncabezados.add(eliminarProductosPane, BorderLayout.CENTER);
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
		panelMenuProveedores = null;
		panelMenuProductos = null;
		panelMenuCompra = null;
		panelMenuVenta = null;
		repaint();
	}
	
	
	

}
