package com.mfrockola.classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Configuracion extends JFrame
{
	private ObjectOutputStream salida;
	private OperacionesRegConfig registroDatos = new OperacionesRegConfig();
	private RegConfig configuraciones;

	private JTextField textFieldVideos;
	private JTextField textFieldVlc;
	private JTextField textFieldCantCreditos;
	private JTextField textFieldMusicaAleatoria;
	private JTextField textFieldReinicioMusicas;
	private JTextField textFieldVideoPromocional;
	private JTextField textFieldSubirL;
	private JTextField textFieldBajarL;
	private JTextField textFieldSubirGenero;
	private JTextField textFieldBajarGenero;
	private JTextField textFieldPantallaCompleta;
	private JTextField textFieldBorrar;
	private JPasswordField passwordField;
	private JTextField textFieldDirFondos;
	private JLabel labelCreditosUsados;
	private JLabel labelMonedasInsertadas;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox checkBoxFoundDefaultBackground;
	private JRadioButton rdbtnSi;
	private JRadioButton rdbtnNo;
	private boolean libre;
	private boolean videoPromocional;
	private boolean defaultBackground;
	private boolean cancelMusic;
	private boolean selectVideoProm;
	private int clickCreditos;
	JFileChooser selectorArchivos = new JFileChooser();
	private Color color1;
	private Color color2;
	JButton botonColor1;
	JButton botonColor2;
	private JLabel labelColor1;
	private JLabel labelColor2;
	
	public Configuracion() 
	{	
		setTitle("Configuración");
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/com/mfrockola/imagenes/icono.png")));
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JLabel bienvenido = new JLabel("Bienvenido a la configuración de MFRockola");
		panelPrincipal.add(bienvenido,BorderLayout.NORTH);
		
		JTabbedPane fichas = new JTabbedPane();
		fichas.setFocusable(false);
		
		Icon rockola = new ImageIcon(this.getClass().getResource("/com/mfrockola/imagenes/rockola.png"));
		JPanel panelBienvenido = new JPanel();
		panelBienvenido.setBackground(Color.WHITE);
		fichas.addTab("Bienvenido",null,panelBienvenido, "Bienvenido a MFRockola");
		panelBienvenido.setLayout(null);
		
		JTextPane txtpnBienvenidoAlPanel = new JTextPane();
		txtpnBienvenidoAlPanel.setText("Bienvenido al panel de configuración de MFRockola.\n\nAquí podrás modificar los aspectos"+" " +
				"básicos del funcionamiento de MFRockola como el precio de los créditos, la cantidad de tiempo necesario para la reproducción"+
				" aleatoria de una música y también las teclas de acción en el teclado.\n\nPuedes oprimir la tecla ESC en cualquier momento para "+
				"salir de la configuración sin modificarla.");
		txtpnBienvenidoAlPanel.setEditable(false);
		txtpnBienvenidoAlPanel.setFocusable(false);
		txtpnBienvenidoAlPanel.setBounds(220, 50, 367, 132);
		panelBienvenido.add(txtpnBienvenidoAlPanel);
		
		JLabel lblGraciasPorUsar = new JLabel("Gracias por Usar y Adquirir MFRockola");
		lblGraciasPorUsar.setFont(new Font("Calibri", Font.BOLD, 21));
		lblGraciasPorUsar.setBounds(220, 11, 331, 27);
		panelBienvenido.add(lblGraciasPorUsar);
		JLabel label = new JLabel();
		label.setIcon(rockola);		
		label.setBounds(10, 11, 200, 200);
		panelBienvenido.add(label);
		
		JPanel panelInferior = new JPanel();
		panelPrincipal.add(panelInferior,BorderLayout.SOUTH);
		JButton buttonReestablecer = new JButton("Reestablecer");
		buttonReestablecer.setFocusable(false);
		panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton buttonGuardar = new JButton("Guardar");
		buttonGuardar.setFocusable(false);
		buttonGuardar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				abrirRegConfigEscritura();
				agregarDatosRegConfig(false);
				cerrarRegConfig();
				JOptionPane.showMessageDialog(null,"Configuración guardada correctamente");
				new Thread(new Splash()).start();
				Splash.moveMouse = true;
				dispose();
			}
		});
		panelInferior.add(buttonGuardar);
		
		panelInferior.add(buttonReestablecer);
		
		Icon tiempo = new ImageIcon(this.getClass().getResource("/com/mfrockola/imagenes/tiempos.png"));
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		fichas.addTab("Tiempo",null,panel2, "Configuración de los tiempos de reproducción y pantalla completa");
		panel2.setLayout(null);
		JLabel labelTiempos = new JLabel();
		labelTiempos.setIcon(tiempo);
		labelTiempos.setBounds(10, 11, 200, 200);
		panel2.add(labelTiempos);
		
		JLabel lblConfiguracinDeLos = new JLabel("Configuración del Tiempo Interno");
		lblConfiguracinDeLos.setFont(new Font("Calibri", Font.BOLD, 21));
		lblConfiguracinDeLos.setBounds(220, 11, 295, 27);
		panel2.add(lblConfiguracinDeLos);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Este es el panel de configuración de los tiempos internos de la Rockola, como el tiempo que debe pasar para" +
				"reproducir una cancion aleatoria y el tiempo necesario para poner la pantalla completa");
		textPane.setEditable(false);
		textPane.setFocusable(false);
		textPane.setBounds(220, 50, 367, 48);
		panel2.add(textPane);
		
		JTextPane txtpnTiempoNecesarioPara = new JTextPane();
		txtpnTiempoNecesarioPara.setText("Tiempo necesario para reproducir una Musica Aleatoria (Minutos)");
		txtpnTiempoNecesarioPara.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtpnTiempoNecesarioPara.setBounds(220, 98, 225, 48);
		txtpnTiempoNecesarioPara.setFocusable(false);
		txtpnTiempoNecesarioPara.setEditable(false);
		panel2.add(txtpnTiempoNecesarioPara);
		
		textFieldMusicaAleatoria = new JTextField();
		textFieldMusicaAleatoria.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMusicaAleatoria.setFont(new Font("Calibri", Font.PLAIN, 13));
		textFieldMusicaAleatoria.setColumns(10);
		textFieldMusicaAleatoria.setBounds(455, 111, 86, 31);
		panel2.add(textFieldMusicaAleatoria);
		
		JTextPane txtpnTiempoNecesarioPara_1 = new JTextPane();
		txtpnTiempoNecesarioPara_1.setText("Tiempo necesario para reiniciar las musicas introducidas (Minutos)");
		txtpnTiempoNecesarioPara_1.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtpnTiempoNecesarioPara_1.setFocusable(false);
		txtpnTiempoNecesarioPara_1.setEditable(false);
		txtpnTiempoNecesarioPara_1.setBounds(220, 158, 225, 48);
		panel2.add(txtpnTiempoNecesarioPara_1);
		
		textFieldReinicioMusicas = new JTextField();
		textFieldReinicioMusicas.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReinicioMusicas.setFont(new Font("Calibri", Font.PLAIN, 13));
		textFieldReinicioMusicas.setColumns(10);
		textFieldReinicioMusicas.setBounds(455, 171, 86, 31);
		panel2.add(textFieldReinicioMusicas);
		
		textFieldVideoPromocional = new JTextField();
		textFieldVideoPromocional.setEnabled(false);
		textFieldVideoPromocional.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldVideoPromocional.setFont(new Font("Calibri", Font.PLAIN, 13));
		textFieldVideoPromocional.setColumns(10);
		textFieldVideoPromocional.setBounds(364, 230, 177, 23);
		panel2.add(textFieldVideoPromocional);

		final JButton buttonPathPromotionalVideo = new JButton("...");
		buttonPathPromotionalVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldVideoPromocional.setText(seleccionarArchivo());
			}
		});
		buttonPathPromotionalVideo.setBounds(551, 230, 45, 23);
		buttonPathPromotionalVideo.setEnabled(false);
		panel2.add(buttonPathPromotionalVideo);

		chckbxNewCheckBox = new JCheckBox("Video Promocional");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				if(chckbxNewCheckBox.isSelected())
				{
					videoPromocional = true;
					textFieldVideoPromocional.setEditable(true);
					textFieldVideoPromocional.setEnabled(true);
					buttonPathPromotionalVideo.setEnabled(true);
				}
				else
				{
					videoPromocional = false;
					textFieldVideoPromocional.setEditable(false);
					textFieldVideoPromocional.setEnabled(false);
					buttonPathPromotionalVideo.setEnabled(false);
				}
			}
		});
		chckbxNewCheckBox.setBackground(Color.WHITE);
		chckbxNewCheckBox.setFont(new Font("Calibri", Font.PLAIN, 15));
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxNewCheckBox.setBounds(207, 230, 154, 23);
		panel2.add(chckbxNewCheckBox);

		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		fichas.addTab("Créditos",null,panel3,"Configuración de los créditos y sus precios");
		panel3.setLayout(null);
		
		JLabel lblConfiguracionDeCreditos = new JLabel("Configuración de Créditos");
		lblConfiguracionDeCreditos.setFont(new Font("Calibri", Font.BOLD, 21));
		lblConfiguracionDeCreditos.setBounds(220, 11, 297, 27);
		panel3.add(lblConfiguracionDeCreditos);
		
		Icon moneda = new ImageIcon(this.getClass().getResource("/com/mfrockola/imagenes/moneda.png"));
		JLabel labelMoneda = new JLabel();
		labelMoneda.setIcon(moneda);
		
		labelMoneda.setBounds(10, 17, 200, 200);
		panel3.add(labelMoneda);
		
		textFieldCantCreditos = new JTextField();
		textFieldCantCreditos.setFont(new Font("Calibri", Font.PLAIN, 13));
		textFieldCantCreditos.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCantCreditos.setBounds(455, 102, 86, 31);
		panel3.add(textFieldCantCreditos);
		textFieldCantCreditos.setColumns(10);
		
		ButtonGroup grupo = new ButtonGroup();
		rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setBackground(Color.WHITE);
		rdbtnSi.setFocusable(false);
		rdbtnSi.addItemListener(new manejadorRadioButtons());
		rdbtnSi.setBounds(453, 142, 42, 23);
		panel3.add(rdbtnSi);
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBackground(Color.WHITE);
		rdbtnNo.setFocusable(false);
		rdbtnNo.addItemListener(new manejadorRadioButtons());
		rdbtnNo.setBounds(497, 142, 44, 23);
		panel3.add(rdbtnNo);
		
		grupo.add(rdbtnSi);
		grupo.add(rdbtnNo);
		
		JTextPane txtpnCantidadDeCreditos = new JTextPane();
		txtpnCantidadDeCreditos.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtpnCantidadDeCreditos.setText("Cantidad de creditos que recibe el cliente al introducir una moneda");
		txtpnCantidadDeCreditos.setEditable(false);
		txtpnCantidadDeCreditos.setFocusable(false);
		txtpnCantidadDeCreditos.setBounds(220, 89, 225, 44);
		panel3.add(txtpnCantidadDeCreditos);
		
		JTextPane txtpnModoACreditos = new JTextPane();
		txtpnModoACreditos.setText("Modo a creditos libre");
		txtpnModoACreditos.setEditable(false);
		txtpnModoACreditos.setFocusable(false);
		txtpnModoACreditos.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtpnModoACreditos.setBounds(303, 140, 144, 25);
		panel3.add(txtpnModoACreditos);
		
		JTextPane txtpnEnEstePanel = new JTextPane();
		txtpnEnEstePanel.setText("En este panel podras configurar lo referente a los creditos, como su precio, si se permiten libres o no.");
		txtpnEnEstePanel.setEditable(false);
		txtpnEnEstePanel.setFocusable(false);
		txtpnEnEstePanel.setBounds(220, 50, 367, 48);
		panel3.add(txtpnEnEstePanel);
		JPanel panel4 = new JPanel();
		panel4.setBackground(Color.WHITE);
		fichas.addTab("Carpetas",null,panel4, "Configuración de las carpetas y directorios de músicas y videos");
		panel4.setLayout(null);
		
		JButton buttonPathVideos = new JButton("...");
		buttonPathVideos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textFieldVideos.setText(seleccionarDirectorio());
			}
		});
		buttonPathVideos.setBounds(563, 127, 32, 23);
		panel4.add(buttonPathVideos);
		
		JButton buttonPathVLC = new JButton("...");
		buttonPathVLC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				textFieldVlc.setText(seleccionarDirectorio());
			}
		});
		buttonPathVLC.setBounds(563, 161, 32, 23);
		panel4.add(buttonPathVLC);
		
		textFieldVideos = new JTextField();
		textFieldVideos.setText("");
		textFieldVideos.setColumns(10);
		textFieldVideos.setBounds(328, 128, 225, 20);
		panel4.add(textFieldVideos);
		
		textFieldVlc = new JTextField();
		textFieldVlc.setColumns(10);
		textFieldVlc.setBounds(328, 162, 225, 20);
		panel4.add(textFieldVlc);
		
		JLabel lblVideos = new JLabel("Directorio de Videos");
		lblVideos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVideos.setBounds(217, 131, 101, 14);
		panel4.add(lblVideos);
		
		JLabel lblVlc = new JLabel("Directorio del VLC");
		lblVlc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVlc.setBounds(217, 165, 101, 14);
		panel4.add(lblVlc);
		
		Icon carpeta = new ImageIcon(this.getClass().getResource("/com/mfrockola/imagenes/carpeta.png"));
		JLabel label_1 = new JLabel(carpeta);
		label_1.setBounds(10, 11, 200, 203);
		panel4.add(label_1);
		
		JLabel lblConfiguracinDeLos_1 = new JLabel("Configuración de los Directorios");
		lblConfiguracinDeLos_1.setFont(new Font("Calibri", Font.BOLD, 21));
		lblConfiguracinDeLos_1.setBounds(220, 11, 274, 27);
		panel4.add(lblConfiguracinDeLos_1);
		
		JTextPane txtpnEsteEsEl = new JTextPane();
		txtpnEsteEsEl.setText("Este es el panel de configuracion de los directorios para un buen funcionamiento del sistema. Es importante saber que es necesario manejar esta zona con cuidado porque puede provocar que el programa se vuelva inestable.");
		txtpnEsteEsEl.setEditable(false);
		txtpnEsteEsEl.setFocusable(false);
		txtpnEsteEsEl.setBounds(220, 50, 367, 62);
		panel4.add(txtpnEsteEsEl);
		
		JTextPane txtpnAdvertenciaTengaCuidado = new JTextPane();
		txtpnAdvertenciaTengaCuidado.setForeground(Color.RED);
		txtpnAdvertenciaTengaCuidado.setText("Advertencia: Tenga cuidado al modificar el directorio del VLC. Consulte la documentación antes de realizar modificaciones");
		txtpnAdvertenciaTengaCuidado.setEditable(false);
		txtpnAdvertenciaTengaCuidado.setFocusable(false);
		txtpnAdvertenciaTengaCuidado.setBounds(10, 265, 585, 23);
		panel4.add(txtpnAdvertenciaTengaCuidado);
		
		Icon teclas = new ImageIcon(this.getClass().getResource("/com/mfrockola/imagenes/teclas.png"));
		JPanel panel5 = new JPanel();
		panel5.setBackground(Color.WHITE);
		fichas.addTab("Teclado y Mouse",null,panel5, "Configuración de las teclas de acción y mouse");
		panel5.setLayout(null);
		JLabel labelTeclas = new JLabel();
		labelTeclas.setIcon(teclas);
		labelTeclas.setBounds(10, 11, 200, 200);
		panel5.add(labelTeclas);
		
		JLabel lblConfiguracinDelTeclado = new JLabel("Configuración del Teclado y Mouse");
		lblConfiguracinDelTeclado.setFont(new Font("Calibri", Font.BOLD, 21));
		lblConfiguracinDelTeclado.setBounds(220, 11, 302, 27);
		panel5.add(lblConfiguracinDelTeclado);
		
		JTextPane txtpnEsteEsEl_1 = new JTextPane();
		txtpnEsteEsEl_1.setText("Este es el panel para configurar el teclado y el mouse si la configuracion por defecto no le conviene. Recuerde tener presente que el bloque numerico puede tener doble funcion o simplemente dejan de funcionar si oprime NUM LOCK.");
		txtpnEsteEsEl_1.setEditable(false);
		txtpnEsteEsEl_1.setFocusable(false);
		txtpnEsteEsEl_1.setBounds(220, 50, 367, 62);
		panel5.add(txtpnEsteEsEl_1);
		
		JLabel lblSubirbajrLista = new JLabel("Subir/Bajr Lista");
		lblSubirbajrLista.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubirbajrLista.setBounds(262, 119, 103, 14);
		panel5.add(lblSubirbajrLista);
		
		textFieldSubirL = new JTextField();
		textFieldSubirL.setBounds(375, 119, 30, 20);
		panel5.add(textFieldSubirL);
		textFieldSubirL.setColumns(10);
		
		JLabel label_2 = new JLabel("/");
		label_2.setBounds(415, 119, 4, 14);
		panel5.add(label_2);
		
		textFieldBajarL = new JTextField();
		textFieldBajarL.setColumns(10);
		textFieldBajarL.setBounds(431, 119, 30, 20);
		panel5.add(textFieldBajarL);
		
		JLabel lblSubirBajarGenero = new JLabel("Subir/Bajr Genero");
		lblSubirBajarGenero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubirBajarGenero.setBounds(262, 144, 103, 14);
		panel5.add(lblSubirBajarGenero);
		
		textFieldSubirGenero = new JTextField();
		textFieldSubirGenero.setColumns(10);
		textFieldSubirGenero.setBounds(375, 144, 30, 20);
		panel5.add(textFieldSubirGenero);
		
		JLabel label_4 = new JLabel("/");
		label_4.setBounds(415, 144, 4, 14);
		panel5.add(label_4);
		
		textFieldBajarGenero = new JTextField();
		textFieldBajarGenero.setColumns(10);
		textFieldBajarGenero.setBounds(431, 144, 30, 20);
		panel5.add(textFieldBajarGenero);
		
		JLabel lblPantallaCompleta = new JLabel("Pantalla Completa");
		lblPantallaCompleta.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPantallaCompleta.setBounds(262, 169, 103, 14);
		panel5.add(lblPantallaCompleta);
		
		textFieldPantallaCompleta = new JTextField();
		textFieldPantallaCompleta.setColumns(10);
		textFieldPantallaCompleta.setBounds(375, 169, 30, 20);
		panel5.add(textFieldPantallaCompleta);
		
		JLabel lblBorrar = new JLabel("Borrar");
		lblBorrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBorrar.setBounds(262, 194, 103, 14);
		panel5.add(lblBorrar);
		
		textFieldBorrar = new JTextField();
		textFieldBorrar.setColumns(10);
		textFieldBorrar.setBounds(375, 194, 30, 20);
		panel5.add(textFieldBorrar);
		
		JLabel lblAgregarCredito = new JLabel("Agregar Credito");
		lblAgregarCredito.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAgregarCredito.setBounds(262, 219, 103, 14);
		panel5.add(lblAgregarCredito);
		
		final JRadioButton rdbtnClickIzquierdo = new JRadioButton("Click Izquierdo");
		

		rdbtnClickIzquierdo.setBackground(Color.WHITE);
		rdbtnClickIzquierdo.setBounds(375, 215, 95, 23);
		panel5.add(rdbtnClickIzquierdo);
		
		JRadioButton rdbtnClickDerecho = new JRadioButton("Click Derecho");
		rdbtnClickDerecho.setBackground(Color.WHITE);
		rdbtnClickDerecho.setBounds(475, 215, 89, 23);
		panel5.add(rdbtnClickDerecho);
		
		ButtonGroup grupoMouse = new ButtonGroup();
		
		rdbtnClickIzquierdo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) 
			{
				if (rdbtnClickIzquierdo.isSelected())
					clickCreditos = 0;
				else
					clickCreditos = 1;				}
		});
		
		grupoMouse.add(rdbtnClickIzquierdo);
		grupoMouse.add(rdbtnClickDerecho);

		JCheckBox checkBoxCancelMusic = new JCheckBox("Cancelar musica con click Izquierdo");
		checkBoxCancelMusic.setBounds(375,238,200, 23);
		checkBoxCancelMusic.setBackground(Color.WHITE);
		checkBoxCancelMusic.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (checkBoxCancelMusic.isSelected()) {
					cancelMusic = true;
					passwordField.setEnabled(true);
					passwordField.setText("");
					rdbtnClickDerecho.setSelected(true);
					lblAgregarCredito.setEnabled(false);
					rdbtnClickDerecho.setEnabled(false);
					rdbtnClickIzquierdo.setEnabled(false);
				} else {
					cancelMusic = false;
					passwordField.setEnabled(false);
					passwordField.setText("");
					lblAgregarCredito.setEnabled(true);
					rdbtnClickDerecho.setEnabled(true);
					rdbtnClickIzquierdo.setEnabled(true);
				}
			}
		});

		panel5.add(checkBoxCancelMusic);

		JLabel labelPassword = new JLabel("Contraseña para eliminar canciones");
		labelPassword.setBounds(165, 261, 200, 23);
		labelPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		panel5.add(labelPassword);

		passwordField = new JPasswordField(5);
		passwordField.setBounds(375,265,200, 23);
		panel5.add(passwordField);
		
		JPanel panel6 = new JPanel();
		panel6.setBackground(Color.WHITE);
		fichas.addTab("Apariencia",null,panel6, "Configuracion de la apariencia general de MFRockola");
		panel6.setLayout(null);
		
		Icon apariencia = new ImageIcon(this.getClass().getResource("/com/mfrockola/imagenes/apariencia.png"));
		JLabel labelApariencia = new JLabel();
		labelApariencia.setIcon(apariencia);
		labelApariencia.setBounds(10, 11, 200, 152);
		panel6.add(labelApariencia);
		
		JLabel lblConfiguracinDeLa = new JLabel("Configuración de la Apariencia");
		lblConfiguracinDeLa.setFont(new Font("Calibri", Font.BOLD, 21));
		lblConfiguracinDeLa.setBounds(220, 11, 302, 27);
		panel6.add(lblConfiguracinDeLa);
		
		JTextPane txtpnEsteEsEl_2 = new JTextPane();
		txtpnEsteEsEl_2.setText("Este es el panel para configurar la apariencia de MFRockola como mas le guste entre lo que cabe la modificacion. Para el tamaño de la imagen de fondo se recomienda el mismo de la resolución de la pantalla que se usar.");
		txtpnEsteEsEl_2.setEditable(false);
		txtpnEsteEsEl_2.setFocusable(false);
		txtpnEsteEsEl_2.setBounds(220, 50, 367, 48);
		panel6.add(txtpnEsteEsEl_2);


		JButton buttonPathFunds = new JButton("...");
		buttonPathFunds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				textFieldDirFondos.setText(seleccionarArchivo());
			}
		});
		buttonPathFunds.setBounds(532, 138, 30, 23);
		panel6.add(buttonPathFunds);

		checkBoxFoundDefaultBackground = new JCheckBox("Fondo Predeterminado");
		checkBoxFoundDefaultBackground.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e)
			{
				if(checkBoxFoundDefaultBackground.isSelected())
				{
					defaultBackground = true;
					textFieldDirFondos.setEditable(false);
					buttonPathFunds.setEnabled(false);
					textFieldDirFondos.setText("");
				}
				else
				{
					defaultBackground = false;
					textFieldDirFondos.setEditable(true);
					buttonPathFunds.setEnabled(true);
					textFieldDirFondos.setText("");
				}
			}
		});

		checkBoxFoundDefaultBackground.setBackground(Color.WHITE);
		checkBoxFoundDefaultBackground.setHorizontalAlignment(SwingConstants.LEFT);
		checkBoxFoundDefaultBackground.setBounds(225, 112, 300, 14);
		panel6.add(checkBoxFoundDefaultBackground);

		JLabel lblDirectorioDeLos = new JLabel("Directorio de los Fondos");
		lblDirectorioDeLos.setBounds(230, 142, 115, 14);
		panel6.add(lblDirectorioDeLos);
		
		textFieldDirFondos = new JTextField();
		textFieldDirFondos.setBounds(355, 139, 167, 20);
		panel6.add(textFieldDirFondos);
		textFieldDirFondos.setColumns(10);

		JLabel labelCelda1 = new JLabel("Seleccionar Color #1");
		labelCelda1.setBounds(423, 182, 99, 14);
		panel6.add(labelCelda1);

		JLabel labelCelda2 = new JLabel("Seleccionar Color #2");
		labelCelda2.setBounds(423, 205, 99, 14);
		panel6.add(labelCelda2);

		labelColor1 = new JLabel("");
		labelColor1.setOpaque(true);
		labelColor1.setBounds(567, 181, 20, 14);
		panel6.add(labelColor1);

		labelColor2 = new JLabel("");
		labelColor2.setOpaque(true);
		labelColor2.setBounds(567, 215, 20, 14);
		panel6.add(labelColor2);

		botonColor1 = new JButton("...");
		botonColor1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				seleccionarColor(e);
			}
		});
		botonColor1.setBounds(532, 172, 30, 23);
		panel6.add(botonColor1);

		botonColor2 = new JButton("...");
		botonColor2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				seleccionarColor(e);
			}
		});
		botonColor2.setBounds(532, 205, 30, 23);
		panel6.add(botonColor2);

		JPanel panel7 = new JPanel();
		panel7.setBackground(Color.WHITE);
		fichas.addTab("Estadisticas",null,panel7, "Configuracion y Visualizacion de las estadisticas de uso");
		panel7.setLayout(null);
		
		Icon estadisticas = new ImageIcon(this.getClass().getResource("/com/mfrockola/imagenes/estadistica.png"));
		JLabel labelEstadisticas = new JLabel();
		labelEstadisticas.setIcon(estadisticas);
		labelEstadisticas.setBounds(10, 11, 200, 152);
		panel7.add(labelEstadisticas);
		
		JLabel lblConfiguracinEstadisticas = new JLabel("Configuración de Estadisticas");
		lblConfiguracinEstadisticas.setFont(new Font("Calibri", Font.BOLD, 21));
		lblConfiguracinEstadisticas.setBounds(220, 11, 302, 27);
		panel7.add(lblConfiguracinEstadisticas);
		
		JTextPane txtPaneEstadis = new JTextPane();
		txtPaneEstadis.setText("Este es el panel para configurar y visualizar las estadisticas de uso de MFRockola. " +
				"Encontraras las monedas intruducidas, asi como un monto aproximado de las ganancias generadas. Asi como cuantas canciones" +
				"aleatorias fueron reproducidas.");
		txtPaneEstadis.setEditable(false);
		txtPaneEstadis.setFocusable(false);
		txtPaneEstadis.setBounds(220, 50, 367, 62);
		panel7.add(txtPaneEstadis);
		
		JLabel lblCantidadDeCreditos = new JLabel("Cantidad de Creditos Usados");
		lblCantidadDeCreditos.setBounds(328, 124, 139, 14);
		panel7.add(lblCantidadDeCreditos);
		
		labelCreditosUsados = new JLabel("0");
		labelCreditosUsados.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelCreditosUsados.setBounds(475, 124, 47, 14);
		panel7.add(labelCreditosUsados);
		
		JLabel lblCantidadDeMonedas = new JLabel("Cantidad de Monedas Insertadas");
		lblCantidadDeMonedas.setBounds(308, 149, 159, 14);
		panel7.add(lblCantidadDeMonedas);
		
		labelMonedasInsertadas = new JLabel("0");
		labelMonedasInsertadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMonedasInsertadas.setBounds(475, 149, 47, 14);
		panel7.add(labelMonedasInsertadas);
		
		panelPrincipal.add(fichas);
		
		getContentPane().add(panelPrincipal);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 642, 400); // posicion de la ventana
		setVisible(true);
		setResizable(false);

		try {
			File file = new File("config.mfr");

			if (!file.exists())
			{
				salida = new ObjectOutputStream(new FileOutputStream("config.mfr"));
				abrirRegConfigEscritura();
				agregarDatosRegConfig(true);
				cerrarRegConfig();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try
		{
			registroDatos.abrirRegConfigLectura();
			configuraciones = registroDatos.leerRegConfigLectura();
		}
		catch (NullPointerException excepcion)
		{
			abrirRegConfigEscritura();
		}
		
		textFieldMusicaAleatoria.setText(String.format("%s",configuraciones.getMusicAleatoria()));
		textFieldReinicioMusicas.setText(String.format("%s",configuraciones.getReinicioMusicas()));

		textFieldCantCreditos.setText(String.format("%s", configuraciones.getCantidadCreditos()));
		textFieldVideoPromocional.setEnabled(configuraciones.isVideoPromocional());
		textFieldVideoPromocional.setEditable(configuraciones.isVideoPromocional());
		buttonPathPromotionalVideo.setEnabled(configuraciones.isVideoPromocional());
		chckbxNewCheckBox.setSelected(configuraciones.isVideoPromocional());
		textFieldVideos.setText(String.format("%s", configuraciones.getDireccionVideos()));
		textFieldVlc.setText(String.format("%s", configuraciones.getDireccionVlc()));
		textFieldVideoPromocional.setText(String.format("%s", configuraciones.getDireccionVideoPromocional()));
		textFieldSubirL.setText(String.format("%s", configuraciones.getTeclaSubirLista()));
		textFieldBajarL.setText(String.format("%s", configuraciones.getTeclaBajarLista()));
		textFieldSubirGenero.setText(String.format("%s", configuraciones.getTeclaSubirGenero()));
		textFieldBajarGenero.setText(String.format("%s", configuraciones.getTeclaBajarGenero()));
		textFieldPantallaCompleta.setText(String.format("%s", configuraciones.getTeclaPantallaCompleta()));
		textFieldBorrar.setText(String.format("%s", configuraciones.getTeclaBorrar()));
		labelCreditosUsados.setText(String.format("%s", configuraciones.getCantidadCreditosUsados()));
		labelMonedasInsertadas.setText(String.format("%s", configuraciones.getCantidadMonedasInsertadas()));
		checkBoxFoundDefaultBackground.setSelected(configuraciones.isDefaultBackground());
		checkBoxCancelMusic.setSelected(configuraciones.isCancelMusic());
		passwordField.setText(configuraciones.getPassword());
		if (configuraciones.isCancelMusic() == true) {
			passwordField.setEnabled(true);
		} else {
			passwordField.setEnabled(false);
		}

		if(configuraciones.isLibre() == true)
			rdbtnSi.setSelected(true);
		else
			rdbtnNo.setSelected(true);
		
		if (configuraciones.getClickCreditos() == 0)
		{
			rdbtnClickIzquierdo.setSelected(true);
			clickCreditos = 0;
		}
		else
		{
			rdbtnClickDerecho.setSelected(true);
			clickCreditos = 1;
		}

		labelColor1.setBackground(configuraciones.getColor1());
		labelColor2.setBackground(configuraciones.getColor2());
		
		color1 = configuraciones.getColor1();
		color2 = configuraciones.getColor2();

		textFieldDirFondos.setText(configuraciones.getDireccionFondo().getFile());
	}
	
	private class manejadorRadioButtons implements ItemListener
	{	
		public void itemStateChanged(ItemEvent e) 
		{
			if (rdbtnSi.isSelected()==true)
				libre = true;
			else
				libre = false;
		}
		
	}
	
	public void abrirRegConfigEscritura()
	{
		try
		{
			salida = new ObjectOutputStream(new FileOutputStream("config.mfr"));
		}
		catch(IOException ioExcepcion)
		{
			System.err.println("Error al abrir el archivo.");
		}
	}
	
	public void agregarDatosRegConfig(boolean firstOpen)
	{
		RegConfig configuraciones;

		if (firstOpen) {
			try
			{
				URL url = this.getClass().getResource("/com/mfrockola/imagenes/fondo.jpg");

				System.out.println(url);

				configuraciones = new RegConfig(
						"C:\\videos", // path of videos
						"C:\\Program Files\\VideoLAN\\VLC", // path of VLC
						"Seleccione un video", // path promotional video
						1, // time in minutes of random music
						1, // time in minutes of reset music
						1, // credits per click
						false, // is credit free?
						false, // is promotional video?
						1, // click credits
						0, // key up list
						0, // key down list
						0, // key up gender
						0, // key down gender
						0, // key fullscreen
						0, // key delete number selector
						false, // cancel music
						"",
						0, // cantidad de creditos usados
						0, // cantidad de monedas insertadas
						true, // is default background?
						url, // path background
						new Color(102,204,255), // color 1 of list
						new Color(255,255,255) // color 2 of list
				);

				salida.writeObject(configuraciones);
			} catch (Exception excepcion)
			{
				System.err.println("Error al escribir el archivo");
			}
		} else {
			try
			{
				URL url;
				if (defaultBackground) {
					url = this.getClass().getResource("/com/mfrockola/imagenes/fondo.jpg");
				} else {
					url = new URL("file:"+textFieldDirFondos.getText());
				}

				configuraciones = new RegConfig(
						textFieldVideos.getText(),
						textFieldVlc.getText(),
						textFieldVideoPromocional.getText(),
						Integer.parseInt(textFieldMusicaAleatoria.getText()),
						Integer.parseInt(textFieldReinicioMusicas.getText()),
						Integer.parseInt(textFieldCantCreditos.getText()),
						libre,
						videoPromocional,
						clickCreditos,
						Integer.parseInt(textFieldSubirL.getText()),
						Integer.parseInt(textFieldBajarL.getText()),
						Integer.parseInt(textFieldSubirGenero.getText()),
						Integer.parseInt(textFieldBajarGenero.getText()),
						Integer.parseInt(textFieldPantallaCompleta.getText()),
						Integer.parseInt(textFieldBorrar.getText()),
						cancelMusic,
						new String(passwordField.getPassword()),
						Integer.parseInt(labelCreditosUsados.getText()),
						Integer.parseInt(labelMonedasInsertadas.getText()),
						defaultBackground,
						url,
						color1,
						color2
				);

				salida.writeObject(configuraciones);
			} catch (IOException excepcion)
			{
				excepcion.printStackTrace();
			}
		}
	}
	
	public void cerrarRegConfig()
	{
		try
		{
			if (salida != null)
				salida.close();
		}
		catch (IOException ioExcepcion)
		{
			System.err.println("Error al cerrar el archivo");
			System.exit(1);
		}
	}
	
	public String seleccionarDirectorio ()
	{
		selectorArchivos.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		int resultado = selectorArchivos.showOpenDialog(this);
		
		if (resultado == JFileChooser.CANCEL_OPTION)
		{
			JOptionPane.showMessageDialog(this, "Debe Seleccionar un Directorio");
		}
		
		File nombreDirectorio = selectorArchivos.getSelectedFile();
		
		if ((nombreDirectorio == null) || (nombreDirectorio.getName().equals("")))
			JOptionPane.showMessageDialog(this, "Directorio invalido");
		
		String directorio = nombreDirectorio.getAbsolutePath();
		
		return directorio;
	}
	
	public String seleccionarArchivo ()
	{
		
		String directorio = null;
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagen JPEG", "jpg");
		
		selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
		selectorArchivos.setFileFilter(filtro);
		
		selectorArchivos.setDialogTitle("Seleccione el Archivo");
		
		int resultado = selectorArchivos.showOpenDialog(this);
		
		if (resultado == JFileChooser.APPROVE_OPTION)
		{
			File nombreArchivo = selectorArchivos.getSelectedFile();
			
			directorio = nombreArchivo.getAbsolutePath();
		}
			
		return directorio;
	}
	
	public void seleccionarColor(ActionEvent evento)
	{	
		if (evento.getSource().equals(botonColor1))
		{
			color1 = JColorChooser.showDialog(null, "Seleccione el Color", Color.WHITE);
			labelColor1.setBackground(color1);
		}
		else if (evento.getSource().equals(botonColor2))
		{
			color2 = JColorChooser.showDialog(null, "Seleccione el Color", Color.WHITE);
			labelColor2.setBackground(color2);
		}
		
	}
}
