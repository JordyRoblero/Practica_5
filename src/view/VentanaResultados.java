
package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.text.DecimalFormat;

public class VentanaResultados extends JFrame {

    private DefaultTableModel modelo;
    private JTable tabla;

    public VentanaResultados() {

        setTitle("Resultados de Revision Vehicular");
        setSize(1150, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color azul = new Color(42, 70, 95);
        Color fondo = new Color(245, 247, 250);
        Color gris = new Color(90, 100, 110);

        JPanel panel = new JPanel(new BorderLayout(10, 15));
        panel.setBackground(fondo);
        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );

        JLabel titulo = new JLabel(
                "RESULTADOS DE REVISION VEHICULAR",
                JLabel.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 21));
        titulo.setForeground(azul);

        JLabel descripcion = new JLabel(
                "Detalle de vehiculos procesados por cada mecanico",
                JLabel.CENTER
        );

        descripcion.setFont(new Font("Arial", Font.PLAIN, 13));
        descripcion.setForeground(gris);

        JPanel encabezado = new JPanel(new BorderLayout(5, 5));
        encabezado.setBackground(new Color(64, 128, 128));
        encabezado.add(titulo, BorderLayout.CENTER);
        encabezado.add(descripcion, BorderLayout.SOUTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("Mecanico");
        modelo.addColumn("Marca");
        modelo.addColumn("Placa");
        modelo.addColumn("Lambda");
        modelo.addColumn("O2");
        modelo.addColumn("N2O");
        modelo.addColumn("CO2");
        modelo.addColumn("Resultado");

        tabla = new JTable(modelo);

        tabla.setFont(new Font("Arial", Font.PLAIN, 13));
        tabla.setRowHeight(30);
        tabla.setForeground(new Color(45, 50, 55));
        tabla.setBackground(new Color(192, 192, 192));
        tabla.setSelectionBackground(new Color(220, 230, 240));
        tabla.setSelectionForeground(Color.BLACK);
        tabla.setGridColor(new Color(215, 220, 225));
        tabla.setShowGrid(true);

        tabla.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );

        tabla.getTableHeader().setBackground(azul);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setPreferredSize(
                new Dimension(100, 35)
        );

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);

        tabla.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);

        tabla.getColumnModel().getColumn(7).setPreferredWidth(210);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
                BorderFactory.createLineBorder(
                        new Color(215, 220, 225)
                )
        );

        panel.add(encabezado, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        getContentPane().add(panel);
    }

    public void agregarResultado(
            String mecanico,
            String marca,
            String placa,
            double lambda,
            double o2,
            double n2o,
            double co2,
            String resultado) {

        DecimalFormat formato = new DecimalFormat("0.00");

        SwingUtilities.invokeLater(() -> {

            modelo.addRow(new Object[] {
                mecanico,
                marca,
                placa,
                formato.format(lambda),
                formato.format(o2),
                formato.format(n2o),
                formato.format(co2),
                resultado
            });

        });
    }

}
