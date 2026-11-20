
package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import view.VentanaResultados;
import controller.MecanicoC;

public class VentanaPrincipal extends JFrame {

    private JButton btnIniciar;
    private JLabel titulo;
    private JLabel lblNewLabel;

    public VentanaPrincipal() {

        setTitle("Sistema de Revision Vehicular");
        setSize(650, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color fondo = new Color(245, 247, 250);
        Color azul = new Color(42, 70, 95);
        Color gris = new Color(90, 100, 110);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(15, 15));
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBackground(new Color(0, 255, 0));
        encabezado.setBorder(
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        );

        JLabel nombreSistema = new JLabel(
                "SISTEMA DE REVISION VEHICULAR",
                SwingConstants.CENTER
        );

        nombreSistema.setFont(
                new Font("Arial", Font.BOLD, 21)
        );
        nombreSistema.setForeground(Color.WHITE);

        encabezado.add(nombreSistema, BorderLayout.CENTER);

        titulo = new JLabel(
                "MANEJO DE HILOS - MECANICA",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 23));
        titulo.setForeground(azul);

        JPanel contenido = new JPanel(new BorderLayout(10, 15));
        contenido.setBackground(fondo);

        JLabel descripcion = new JLabel(
                "<html><center>Procesamiento simultaneo de vehiculos por mecanico</center></html>",
                SwingConstants.CENTER
        );

        descripcion.setFont(new Font("Arial", Font.PLAIN, 15));
        descripcion.setForeground(gris);

        btnIniciar = new JButton("INICIAR REVISION");

        btnIniciar.setFont(new Font("Arial", Font.BOLD, 15));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setBackground(new Color(0, 255, 0));
        btnIniciar.setFocusPainted(false);
        btnIniciar.setBorderPainted(false);
        btnIniciar.setPreferredSize(new Dimension(220, 45));
        btnIniciar.setCursor(
                new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)
        );

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(fondo);
        panelBoton.add(btnIniciar);

        lblNewLabel = new JLabel(
                "10 mecanicos registrados  |  50 vehiculos por revisar",
                SwingConstants.CENTER
        );

        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNewLabel.setForeground(gris);

        contenido.add(titulo, BorderLayout.NORTH);
        contenido.add(descripcion, BorderLayout.CENTER);
        contenido.add(panelBoton, BorderLayout.SOUTH);

        panel.add(encabezado, BorderLayout.NORTH);
        panel.add(contenido, BorderLayout.CENTER);
        panel.add(lblNewLabel, BorderLayout.SOUTH);

        getContentPane().add(panel);

        btnIniciar.addActionListener(e -> {

            btnIniciar.setEnabled(false);

            VentanaResultados resultados = new VentanaResultados();
            resultados.setVisible(true);

            Thread proceso = new Thread(() -> {

                MecanicoC controlador =
                        new MecanicoC(resultados);

                controlador.ejecutarProcesos();

                javax.swing.SwingUtilities.invokeLater(() -> {
                    btnIniciar.setEnabled(true);
                });
            });

            proceso.start();
        });
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
