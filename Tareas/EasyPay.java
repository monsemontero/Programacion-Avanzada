/**
 * Programa desarrollado por el Mtro. Raul Fabian Colorado Pimentel <rcolorado@uv.mx>
 * Programación Avanzada
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;
import adaptadores.*;

class EasyPay implements ActionListener {
	
	public JList <String> descarga1,descarga2,descarga3;
	public JList <String> carga1,carga2,carga3;
	public DefaultListModel<String> modelo1, modelo2, modelo3;
	EasyPay contexto;
	
    JMenuBar JMenuBar_miMenu;
    JFrame JFrame_miJFrame;
    JDesktopPane JDesktopPane_miDesktopPane;

    //Variables de nuestros JInternalFrame 
    JInternalFrame JInternalFrame_usuarios;
    JInternalFrame JInternalFrame_recibos;
    JInternalFrame JInternalFrame_pagos;
    JInternalFrame componentes;
    JInternalFrame blcompo;
    JInternalFrame glcompo;
    JInternalFrame flcompo;
    JInternalFrame eventos;
    JInternalFrame ventanaDescargas;
    JInternalFrame iFCargas1,iFCargas2,iFCargas3;
    
    // Datos de prueba
    Datos misDatos = new Datos();
    
    /**
     * Constructor
     */
    EasyPay() {
    	
    	contexto = this;
        modelo1  = new DefaultListModel<String>();
        modelo2 = new DefaultListModel<String>();
        modelo3 = new DefaultListModel<String>();
    	
        // Create a new JFrame container.
        JFrame_miJFrame = new JFrame("- Easy Pay -");

        // Establecemos las dimensiones de nuestro JFrame (en pixeles)
        JFrame_miJFrame.setSize(800, 600);
        // Terminamo el programa cuando el usuario cierra el JFrame
        JFrame_miJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creamos el MenuBar
        JMenuBar_miMenu = new JMenuBar();

        // Creamos el menú programa
        makeMenuPrograma();
        // Creamos el menú usuarios
        makeMenuUsuarios();
        // Creamos menú CFE
        makeMenuCFE();
        // Creamos el menú Ejercicios
        makeMenuEjercicios();
        // Creamos menú de ayuda
        makeMenuAyuda();

        // Agregamos el Menu a nuestro JFrame.
        JFrame_miJFrame.setJMenuBar(JMenuBar_miMenu);

        // Creamos nuestro JDesktopPane para nuestro programa MDI "Multiple Document Interface"
        JDesktopPane_miDesktopPane = new JDesktopPane();
        JDesktopPane_miDesktopPane.setBackground(Color.GRAY);

        /**
         * Sección JInternalFrame Usuarios
         */

        // Agregamos un Container denominado JInternalFrame
        JInternalFrame_usuarios = new JInternalFrame("Usuarios", true, true, true, true);
        JInternalFrame_usuarios.setSize(400, 300);
        // Lo establecemos oculto
        JInternalFrame_usuarios.setVisible(false);
        // Establecemos que cuando el usuario de clic en el boton cerrar, se oculte solamente
        JInternalFrame_usuarios.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Creamos un componente JList el cual tendra nuestro listado de Usuarios
        JList<Usuario> jlist = new  JList<Usuario>();
        // Asigamos a nuestro JList el modelo. Para este caso se creo un modelo Custom (Este tema no es obligatorio aprenderlo)
        jlist.setModel(new MiListModelUsuarios(misDatos.usuarios));
        // También se creo un render Custom ((Este tema no es obligatorio aprenderlo))
        jlist.setCellRenderer(new UsuariosListCellRenderer());
        // Los JList deben de ir dentro de un container JScrollPane, para que se muestre una barra de Scroll. Si no se establece, solo se mostrarán los elementos que quepan al alto del JInternalFrame
        JScrollPane scrollPane1 = new JScrollPane(jlist);
        // Agregamos el componente scrollPane1 a nuestro JInternalFrame_usuarios en un Layout Manager
        JInternalFrame_usuarios.add(scrollPane1, BorderLayout.CENTER);
        // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(JInternalFrame_usuarios);
        //---------------

        
        /**
         * Seccion JInternalFrame Recibos
         */

        // Agregamos un Container denominado JInternalFrame
        JInternalFrame_recibos = new JInternalFrame("Recibos", true, true, true, true);
        JInternalFrame_recibos.setSize(500, 300);
        // Lo establecemos oculto
        JInternalFrame_recibos.setVisible(false);
        // Establecemos que cuando el usuario de clic en el botón cerrar, se oculte solamente
        JInternalFrame_recibos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Creamos un componente JList el cual tendrá nuestro listado de Usuarios
        JList<Recibo> jlist_recibos = new  JList<Recibo>();
        // Asigamos a nuestro jlist_recibos el modelo. Para este caso se creo un modelo Custom (Este tema no es obligatorio aprenderlo)
        jlist_recibos.setModel(new MiListModelRecibos(misDatos.recibos));
        // También se creo un render Custom ((Este tema no es obligatorio aprenderlo))
        jlist_recibos.setCellRenderer(new RecibosListCellRenderer());
        // Los JList deben de ir dentro de un container JScrollPane, para que se muestre una barra de Scroll. Si no se establece, solo se mostrarán los elementos que quepan al alto del JInternalFrame
        JScrollPane scrollPanel_recibos = new JScrollPane(jlist_recibos);
        // Agregamos el componente scrollPanel_recibos a nuestro JInternalFrame_recibos en un Layout Manager
        JInternalFrame_recibos.add(scrollPanel_recibos, BorderLayout.CENTER);
        // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(JInternalFrame_recibos);
        //---------------


        /**
         * Seccion JInternalFrame Recibos
         */

        // Agregamos un Container denominado JInternalFrame
        JInternalFrame_pagos = new JInternalFrame("Pagos", true, true, true, true);
        JInternalFrame_pagos.setSize(700, 300);
        // Lo establecemos oculto
        JInternalFrame_pagos.setVisible(false);
        // Establecemos que cuando el usuario de clic en el botón cerrar, se oculte solamente
        JInternalFrame_pagos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Creamos un componente JList el cual tendrá nuestro listado de Usuarios
        JList<Pago> jlist_pagos = new  JList<Pago>();
        // Asigamos a nuestro jlist_pagos el modelo. Para este caso se creo un modelo Custom (Este tema no es obligatorio aprenderlo)
        jlist_pagos.setModel(new MiListModelPagos(misDatos.pagos));
        // También se creo un render Custom ((Este tema no es obligatorio aprenderlo))
        jlist_pagos.setCellRenderer(new PagosListCellRenderer());
        // Los JList deben de ir dentro de un container JScrollPane, para que se muestre una barra de Scroll. Si no se establece, solo se mostrarán los elementos que quepan al alto del JInternalFrame
        JScrollPane scrollPanel_pagos = new JScrollPane(jlist_pagos);
        // Agregamos el componente scrollPanel_pagos a nuestro JInternalFrame_pagos en un Layout Manager
        JInternalFrame_pagos.add(scrollPanel_pagos, BorderLayout.CENTER);
        // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(JInternalFrame_pagos);
        //---------------
        
        /**
         * Seccion JInternalFrame COMPONENTES
        */
        
        // Agregamos un Container denominado JInternalFrame
        componentes = new JInternalFrame("Componentes", true,true, true, true);
        componentes.setLayout(new FlowLayout());
        componentes.setSize(400, 300);
        componentes.setVisible(false);
        componentes.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JLabel nombre = new JLabel ("Nombre");
        JTextField tnombre = new JTextField (30);
        JLabel email = new JLabel ("Email");
        JTextField temail = new JTextField (30);
        JRadioButton miOpcion1 = new JRadioButton("Femenino");
		JRadioButton miOpcion2 = new JRadioButton("Masculino");
		ButtonGroup miGrupo = new ButtonGroup();
		miGrupo.add(miOpcion1);
		miGrupo.add(miOpcion2);
        JButton boton1 = new JButton ("Registrar");
        componentes.add(nombre);
        componentes.add(tnombre);
        componentes.add(email);
        componentes.add(temail);
        componentes.add(miOpcion1);
        componentes.add(miOpcion2);
        componentes.add(boton1);
        
        // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(componentes);
        
        /**
         * Seccion JInternalFrame FLOWlAYOUT
         */

        // Agregamos un Container denominado JInternalFrame
        flcompo = new JInternalFrame("FlowLayout", true,true, true, true);
        flcompo.setLayout(new FlowLayout(FlowLayout.CENTER));
        flcompo.setSize(150, 200);
        flcompo.setVisible(false);
        flcompo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JLabel buno = new JLabel (" Boton ");
        JButton btnUno = new JButton("UNO");
        JLabel bdos = new JLabel (" Boton ");
        JButton btnDos = new JButton("DOS");
        JLabel btres = new JLabel (" Boton ");
        JButton btnTres = new JButton("TRES");
        flcompo.add(buno);
        flcompo.add(btnUno);
        flcompo.add(bdos);
        flcompo.add(btnDos);
        flcompo.add(btres);
        flcompo.add(btnTres);
        // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(flcompo);
        
        /**
         * Seccion JInternalFrame BORDERLAyOUT
         */

        // Agregamos un Container denominado JInternalFrame
        blcompo = new JInternalFrame("BorderLayout", true,true, true, true);
        blcompo.setLayout(new BorderLayout());
        blcompo.setSize(400, 300);
        blcompo.setVisible(false);
        blcompo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JButton boton = new JButton ("Boton Prueba");
        JButton botonn = new JButton ("Boton Norte");
		JButton botons= new JButton ("Guardar");
		JButton botone= new JButton ("Boton Este");
		JButton botono = new JButton ("Boton Oeste");
		JTextArea texto= new JTextArea ();
        blcompo.add(botonn, BorderLayout.NORTH);
        blcompo.add(botons, BorderLayout.SOUTH);
        blcompo.add(botone, BorderLayout.EAST);
        blcompo.add(botono, BorderLayout.WEST);
        blcompo.add(texto, BorderLayout.CENTER);
       
        // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(blcompo);
        /**
         * Seccion JInternalFrame GRIDLAYOUT
         */

        // Agregamos un Container denominado JInternalFrame
        glcompo = new JInternalFrame("GridLayout", true,true, true, true);
        glcompo.setSize(400, 300);
        glcompo.setLayout(new GridLayout(5, 4));
        JTextField txtResultado = new JTextField(40);
        JPanel resultado = new JPanel ();
        resultado.setLayout(new FlowLayout());
        JButton btn0 = new JButton ("0");
        JButton btn1 = new JButton ("1");
        JButton btn2 = new JButton ("2");
        JButton btn3 = new JButton ("3");
        JButton btn4 = new JButton ("4");
        JButton btn5 = new JButton ("5");
        JButton btn6 = new JButton ("6");
        JButton btn7 = new JButton ("7");
        JButton btn8 = new JButton ("8");
        JButton btn9 = new JButton ("9");
        JButton btnsuma = new JButton ("+");
        JButton btnresta = new JButton ("-");
        JButton btnmultiplicacion = new JButton ("x");
        JButton btndivision = new JButton ("/");
        
        glcompo.add(resultado);
        resultado.add(txtResultado);
        glcompo.add(btn1);
        glcompo.add(btn2);
        glcompo.add(btn3);
        glcompo.add(btn4);
        glcompo.add(btn5);
        glcompo.add(btn6);
        glcompo.add(btn7);
        glcompo.add(btn8);
        glcompo.add(btn9);
        glcompo.add(btn0);
        glcompo.add(btnsuma);
        glcompo.add(btnresta);
        glcompo.add(btnmultiplicacion);
        glcompo.add(btndivision);
        glcompo.setVisible(false);
        glcompo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(glcompo);

         /**
          * Seccion JInternalFrame EVENTOS
          */

         // Agregamos un Container denominado JInternalFrame
         eventos = new JInternalFrame("Eventos", true,true, true, true);
         eventos.setLayout(new FlowLayout());
         eventos.setSize(400, 300);
         eventos.setVisible(false);
         eventos.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JButton boton01 = new JButton ("Boton 01");
 		JButton boton02= new JButton ("Boton 02");
 		JButton boton03= new JButton ("Boton 03");
 		
 		boton01.addActionListener(this);
 		boton02.addActionListener(this);
 		boton03.addActionListener(this);
 		
 		eventos.add(boton01);
 		eventos.add(boton02);
 		eventos.add(boton03);
         // Agreganmos nuestro JInternalFrame a nuestro JDesktopPane_miDesktopPane
        JDesktopPane_miDesktopPane.add(eventos);
        
       
        /**
         * Seccion JInternalFrame Hilos
         */
        ventanaDescargas = new JInternalFrame("Descarga-Hilos",true, true, true, true);
		ventanaDescargas.setSize(600,400);
		ventanaDescargas.setVisible(false);
		
		 
		JButton btndescarga = new JButton("Iniciar descarga");
		ActionListener listener1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Hilo1(contexto)).start();
				new Thread(new Hilo2(contexto)).start();
				new Thread(new Hilo3(contexto)).start();
			}
			
		};
		btndescarga.addActionListener(listener1);
		
		
		JDesktopPane desktop = new JDesktopPane();
		
		ventanaDescargas.add(btndescarga,BorderLayout.NORTH);
		
		JPanel miPanel = new JPanel(new GridLayout(1,3,3,3));
		descarga1 = new JList<String>(modelo1);
		descarga2 = new JList<String>(modelo2);
		descarga3 = new JList<String>(modelo3);
		        
		miPanel.add(descarga1);
		miPanel.add(descarga2);
		miPanel.add(descarga3);

		ventanaDescargas.add(miPanel);
		desktop.add(ventanaDescargas);
		JFrame_miJFrame.setContentPane(desktop);

		JDesktopPane_miDesktopPane.add(ventanaDescargas);
		// Agregamos nuestro contenido al JFrame_miJFrame
		JFrame_miJFrame.setContentPane(JDesktopPane_miDesktopPane);
		
		
		/**
         * Seccion JInternalFrame Hilos 2
         * 
        * */
		iFCargas1 = new JInternalFrame("Ventana Hilo 1",true, true, true, true);
		iFCargas1.setSize(400,400);
		iFCargas1.setVisible(false);
		
		iFCargas2 = new JInternalFrame("Ventana Hilo 2",true, true, true, true);
		iFCargas2.setSize(350,350);
		iFCargas2.setVisible(false);
		
		iFCargas3 = new JInternalFrame("Ventana Hilo 3",true, true, true, true);
		iFCargas3.setSize(300,300);
		iFCargas3.setVisible(false);
		
		ActionListener listenerHilo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread(new Hilo1(contexto)).start();
				new Thread(new Hilo2(contexto)).start();
				new Thread(new Hilo3(contexto)).start();
			}
				
		};
				
			
		JDesktopPane desktopC = new JDesktopPane();
			
		JPanel miPanelC = new JPanel();
		carga1 = new JList<String>(modelo1);
		carga2 = new JList<String>(modelo2);
		carga3 = new JList<String>(modelo3);
			        
		miPanelC.add(carga1);
		miPanelC.add(carga2);
		miPanelC.add(carga3);

		iFCargas1.add(miPanelC);
		desktopC.add(iFCargas1);
		JFrame_miJFrame.setContentPane(desktopC);

		JDesktopPane_miDesktopPane.add(iFCargas1);
		// Agregamos nuestro contenido al JFrame_miJFrame
		JFrame_miJFrame.setContentPane(JDesktopPane_miDesktopPane);

		iFCargas2.add(miPanelC);
		desktopC.add(iFCargas2);
		JFrame_miJFrame.setContentPane(desktopC);

		JDesktopPane_miDesktopPane.add(iFCargas2);
		// Agregamos nuestro contenido al JFrame_miJFrame
		JFrame_miJFrame.setContentPane(JDesktopPane_miDesktopPane);
		
		iFCargas3.add(miPanelC);
		desktopC.add(iFCargas3);
		JFrame_miJFrame.setContentPane(desktopC);

		JDesktopPane_miDesktopPane.add(iFCargas3);
		// Agregamos nuestro contenido al JFrame_miJFrame
		JFrame_miJFrame.setContentPane(JDesktopPane_miDesktopPane);
		
		
		
		// Mostramos el Frame.
        JFrame_miJFrame.setVisible(true);
    }


    /**
     * Listener que maneja los eventos de nuestro JFrame
     */
    public void actionPerformed(ActionEvent actionEvent) {
        // Obtiene el "action command" del "event dispatching thread"
        String comStr = actionEvent.getActionCommand();

        // Imprimos en consola esta accion
        System.out.println(comStr);

        // A partir de aquí comparamos de donde se lanzó el ActionEvent
        if (comStr.equals("Salir")) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "�Está seguro de salir?", "Easy Pay", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        if (comStr.equals("Acerca")) {
            JOptionPane.showMessageDialog(JDesktopPane_miDesktopPane, "-- Easy Pay --  \n Versión 0.0.1", "Easy Pay",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (comStr.equals("Ver usuarios")) {
            JInternalFrame_usuarios.setVisible(true);
        }
        if (comStr.equals("Recibos/Folios")) {
            JInternalFrame_recibos.setVisible(true);
        }

        if (comStr.equals("Recibos/Folios pagados")) {
            JInternalFrame_pagos.setVisible(true);
        }
        if (comStr.equals("Componentes")) {
        	componentes.setVisible(true);
        }
        if (comStr.equals("FlowLayout")) {
        	flcompo.setVisible(true);
        }
        if (comStr.equals("BorderLayout")) {
        	blcompo.setVisible(true);
        }
        if (comStr.equals("GridLayout")) {
        	glcompo.setVisible(true);
        }
        if (comStr.equals("Eventos")) {
        	eventos.setVisible(true);
        }
        if (comStr.equals("Hilos")) {
        	ventanaDescargas.setVisible(true);
        }
        if (comStr.equals("Hilos 2")) {
			iFCargas1.setVisible(true);
        }
        if (comStr.equals("Hilos 2")) {
			iFCargas2.setVisible(true);
        }
        if (comStr.equals("Hilos 2")) {
			iFCargas3.setVisible(true);
        }
        
    }

	/**
    * Esta funcion se encarga de crear el Menu Ejercicios
    * El menu Ejercicios sera agregado a nuestra barra de Menu
    */
    void makeMenuEjercicios() {

        // Creamos un objeto del tipo JMenu
        JMenu JMenu_Ejercicios = new JMenu("Ejercicios");

        // ---------- SECCION EJERCICIOS GUI ----------
        // Creamos un objeto del tipo JMenu
        JMenu JMenu_EjerciciosGUI = new JMenu("GUI");
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenu_EjerciciosGUI (se activa cuando obtiene el OVER por el Mouse)
        JMenu_EjerciciosGUI.setToolTipText("Ejercicios GUI");
        // Agregamos al menu Ejercicios, el submenu Ejercicios GUI
        JMenu_Ejercicios.add(JMenu_EjerciciosGUI);

        
        // Creamos el item 
        JMenuItem JMenu_EjerciciosGUI_01 = new JMenuItem("Componentes");
        // Agregamos el item a nuestro menu 
        JMenu_EjerciciosGUI.add(JMenu_EjerciciosGUI_01);
        // Agregamos un action listener
        JMenu_EjerciciosGUI_01.addActionListener(this);
        
        // Creamos el item 
        JMenuItem JMenu_EjerciciosGUI_02 = new JMenuItem("FlowLayout");
        // Agregamos el item a nuestro menu 
        JMenu_EjerciciosGUI.add(JMenu_EjerciciosGUI_02);
        // Agregamos un action listener
        JMenu_EjerciciosGUI_02.addActionListener(this);
        
        // Creamos el item 
        JMenuItem JMenu_EjerciciosGUI_03 = new JMenuItem("BorderLayout");
        // Agregamos el item a nuestro menu 
        JMenu_EjerciciosGUI.add(JMenu_EjerciciosGUI_03);
        // Agregamos un action listener
        JMenu_EjerciciosGUI_03.addActionListener(this);
        
        // Creamos el item 
        JMenuItem JMenu_EjerciciosGUI_04= new JMenuItem("GridLayout");
        // Agregamos el item a nuestro menu 
        JMenu_EjerciciosGUI.add(JMenu_EjerciciosGUI_04);
        // Agregamos un action listener
        JMenu_EjerciciosGUI_04.addActionListener(this);
        
        // Creamos el item 
        JMenuItem JMenu_EjerciciosGUI_07 = new JMenuItem("Eventos");
        // Agregamos el item a nuestro menu 
        JMenu_EjerciciosGUI.add(JMenu_EjerciciosGUI_07);
        // Agregamos un action listener
        JMenu_EjerciciosGUI_07.addActionListener(this);
        

        // ---------- SECCIÓN EJERCICIOS PROGRAMACION CONCURRENTE ----------
        // Creamos un objeto del tipo JMenu
        JMenu JMenu_EjerciciosPC = new JMenu("Programacion Concurrente");
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenu_EjerciciosPC (se activa cuando obtiene el OVER por el Mouse)
        JMenu_EjerciciosGUI.setToolTipText("Ejercicios Programacion Concurrente");
        // Agregamos al menu Ejercicios, el submenu Ejercicios GUI
        JMenu_Ejercicios.add(JMenu_EjerciciosPC);
        
        // Creamos el item 
        JMenuItem JMenu_EjerciciosPC_01 = new JMenuItem("Hilos");
        // Agregamos el item a nuestro menu 
        JMenu_EjerciciosPC.add(JMenu_EjerciciosPC_01);
        // Agregamos un action listener
        JMenu_EjerciciosPC_01.addActionListener(this);
        
        // Creamos el item 
        JMenuItem JMenu_EjerciciosPC_02 = new JMenuItem("Hilos 2");
        // Agregamos el item a nuestro menu 
        JMenu_EjerciciosPC.add(JMenu_EjerciciosPC_02);
		// Agregamos un action listener
        JMenu_EjerciciosPC_02.addActionListener(this);

        // ---------- SECCIÓN EJERCICIOS PROGRAMACION REFLEXIVA ----------
        // Creamos un objeto del tipo JMenu
        JMenu JMenu_EjerciciosPR = new JMenu("Programacion Reflexiva");
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenu_EjerciciosPR (se activa cuando obtiene el OVER por el Mouse)
        JMenu_EjerciciosGUI.setToolTipText("Ejercicios Programación Reflexiva");
        // Agregamos al menu Ejercicios, el submenu Ejercicios GUI
        JMenu_Ejercicios.add(JMenu_EjerciciosPR);

        // ---------- SECCIÓN EJERCICIOS ACCESO A DATOS ----------
        // Creamos un objeto del tipo JMenu
        JMenu JMenu_EjerciciosORM = new JMenu("Acceso a datos");
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenu_EjerciciosORM (se activa cuando obtiene el OVER por el Mouse)
        JMenu_EjerciciosGUI.setToolTipText("Ejercicios Programación de Acceso a datos");
        // Agregamos al menu Ejercicios, el submenu Ejercicios GUI
        JMenu_Ejercicios.add(JMenu_EjerciciosORM);

        // Agregamos a nuestra Barra de Menú, nuestro menú "JMenu_Usuarios" 
        JMenuBar_miMenu.add(JMenu_Ejercicios);
    }

    /**
    * Esta función se encarga de crear el Menú Usuarios
    * El menú Usuarios será agregado a nuestra barra de Menu
    */
    void makeMenuUsuarios() {

        // Creamos un objeto del tipo JMenu
        JMenu JMenu_Usuarios = new JMenu("Usuarios");
        // Agregamos un objeto de tipo ImageIcon representantiva al item de "Usuarios"
        ImageIcon icono = new ImageIcon("img/usuarios.png");
        // Creamos el item "Ver" 
        JMenuItem JMenuItem_ver = new JMenuItem("Ver usuarios", icono);
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenuItem_ver (se activa cuando obtiene el OVER por el Mouse)
        JMenuItem_ver.setToolTipText("Ver usuarios");
        // Agregamos el item "Salir" a nuestro menú CFE
        JMenu_Usuarios.add(JMenuItem_ver);
        // Agregamos un action listener
        JMenuItem_ver.addActionListener(this);

        // Agregamos a nuestra Barra de Menú, nuestro menú "JMenu_Usuarios" 
        JMenuBar_miMenu.add(JMenu_Usuarios);
    }

    /**
    * Esta función se encarga de crear el Menú Programa
    * El menú Programa será agregado a nuestra barra de Menu
    */
    void makeMenuPrograma() {
        // Creamos un objeto del tipo JMenu
        JMenu JMenu_Programa = new JMenu("Programa");
        // Creamos el item "Salir" 
        JMenuItem JMenuItem_salir = new JMenuItem("Salir");
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenuItem_salir (se activa cuando obtiene el OVER por el Mouse)
        JMenuItem_salir.setToolTipText("Salir de Easy Pay");
        // Agregamos el item "Salir" a nuestro menú CFE
        JMenu_Programa.add(JMenuItem_salir);
        // Agregamos un action listener
        JMenuItem_salir.addActionListener(this);

        // Agregamos a nuestra Barra de Menú, nuestro menú "Programa" 
        JMenuBar_miMenu.add(JMenu_Programa);
    }

    /**
    * Esta función se encarga de crear el Menú CFE
    * El menú CFE será agregado a nuestra barra de Menu
    */
    void makeMenuCFE() {
        // Creamos un objeto del tipo JMenu
        JMenu JMenu_CFE = new JMenu("CFE");

        // Agregamos un objeto de tipo ImageIcon representantiva al item de "Recibos/Folios"
        ImageIcon icono = new ImageIcon("img/folios.png");
        // Creamos el item "Recibos/Folios" 
        JMenuItem JMenuItem_folios = new JMenuItem("Recibos/Folios", icono);
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenuItem_folios (se activa cuando obtiene el OVER por el Mouse)
        JMenuItem_folios.setToolTipText("Listado de recibos de Easy Pay");
        // Agregamos el item "Recibos/Folios" a nuestro menú CFE
        JMenu_CFE.add(JMenuItem_folios);
        // Agregamos un action listener
        JMenuItem_folios.addActionListener(this);

        // Agregamos un separador en nuestro menú CFE
        JMenu_CFE.addSeparator();

        // Agregamos un objeto de tipo ImageIcon representantiva al item de "Recibos/Folios pagados"
        ImageIcon icono_pagos = new ImageIcon("img/pagos.png");
        // Creamos el item "Recibos/Folios pagados" 
        JMenuItem JMenuItem_foliosPagados = new JMenuItem("Recibos/Folios pagados", icono_pagos);
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenuItem_foliosPagados (se activa cuando obtiene el OVER por el Mouse)
        JMenuItem_foliosPagados.setToolTipText("Listado de recibos pagados de Easy Pay");
        // Agregamos el item "Recibos/Folios pagados" a nuestro menú CFE
        JMenu_CFE.add(JMenuItem_foliosPagados);
        // Agregamos un action listener
        JMenuItem_foliosPagados.addActionListener(this);

        // Agregamos a nuestra Barra de Menú, nuestro menú "CFE" 
        JMenuBar_miMenu.add(JMenu_CFE);
    }

    /**
     * Esta función se encarga de crear el Menú ayuda
     * El menú ayuda será agregado a nuestra barra de Menu
     */
    void makeMenuAyuda() {
        // Creamos un objeto del tipo JMenu
        JMenu JMenu_ayuda = new JMenu("Ayuda");
        // Agregamos un objeto de tipo ImageIcon representantiva al item de "Acerca"
        ImageIcon icono = new ImageIcon("img/ayuda.png");
        // Agregamos el item "Acerca" al menú ayuda
        JMenuItem JMenuItem_acerca = new JMenuItem("Acerca", icono);
        // Agregamos un ToolTipText para agregar un Tooltipo al JMenuItem_acerca (se activa cuando obtiene el OVER por el Mouse)
        JMenuItem_acerca.setToolTipText("Información acerca de Easy Pay");
        // Agregamos el item "Acerca" a nuestro menú ayuda
        JMenu_ayuda.add(JMenuItem_acerca);
        // Agregamos un action listener
        JMenuItem_acerca.addActionListener(this);

        // Agregamos a nuestra Barra de Menú, nuestro menú "Ayuda" 
        JMenuBar_miMenu.add(JMenu_ayuda);
    }

    /**
     * Metodo Main de inicializacion
     */
    public static void main(String args[]) {
        // Creamos el frame en el "event dispatching thread".
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EasyPay();
            }
        });
    }
}