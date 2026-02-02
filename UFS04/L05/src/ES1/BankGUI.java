package ES1;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class BankGUI extends JFrame {

    private ContoBancario conto;
    private JLabel lblSaldo;
    private JTable tableTransazioni;
    private DefaultTableModel tableModel;

    private JTextField txtImporto;
    private JTextField txtDescrizione;

    public BankGUI() {
        conto = new ContoBancario(1000.0);

        setTitle("Gestione Conto Bancario - ES1");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout(10, 10));

        JPanel panelHeader = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelHeader.setBackground(new Color(44, 62, 80));
        lblSaldo = new JLabel("Saldo Attuale: € " + String.format("%.2f", conto.getSoldi()));
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setBorder(new EmptyBorder(10, 20, 10, 20));
        panelHeader.add(lblSaldo);
        add(panelHeader, BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(200);
        splitPane.setTopComponent(createOperationsPanel());
        splitPane.setBottomComponent(createHistoryPanel());
        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel createOperationsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Nuova Operazione", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Importo (€):"), gbc);
        txtImporto = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtImporto, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Descrizione:"), gbc);
        txtDescrizione = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtDescrizione, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton btnDeposito = new JButton("Deposito");
        styleButton(btnDeposito, new Color(46, 204, 113));
        btnDeposito.addActionListener(e -> eseguiTransazione("Deposito"));

        JButton btnPrelievo = new JButton("Prelievo");
        styleButton(btnPrelievo, new Color(231, 76, 60));
        btnPrelievo.addActionListener(e -> eseguiTransazione("Prelievo"));

        JButton btnPagamento = new JButton("Pagamento");
        styleButton(btnPagamento, new Color(52, 152, 219));
        btnPagamento.addActionListener(e -> eseguiTransazione("Pagamento"));

        btnPanel.add(btnDeposito);
        btnPanel.add(btnPrelievo);
        btnPanel.add(btnPagamento);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);

        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Storico Transazioni", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 14)));

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        filterPanel.add(new JLabel("Tipo: "));
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Tutti", "Deposito", "Prelievo", "Pagamento"});
        filterPanel.add(cmbTipo);
        JButton btnTipo = new JButton("Filtra");
        styleButton(btnTipo, new Color(52, 152, 219));
        btnTipo.addActionListener(e -> filtraPerTipo((String) cmbTipo.getSelectedItem()));
        filterPanel.add(btnTipo);

        filterPanel.add(new JLabel("Desc: "));
        JTextField txtDesc = new JTextField(10);
        filterPanel.add(txtDesc);
        JButton btnDesc = new JButton("Filtra");
        styleButton(btnDesc, new Color(46, 204, 113));
        btnDesc.addActionListener(e -> {
            if (!txtDesc.getText().trim().isEmpty()) filtraPerDescrizione(txtDesc.getText().trim());
        });
        filterPanel.add(btnDesc);

        filterPanel.add(new JLabel("Dal: "));
        JTextField txtDal = new JTextField(8);
        filterPanel.add(txtDal);
        filterPanel.add(new JLabel("Al: "));
        JTextField txtAl = new JTextField(8);
        filterPanel.add(txtAl);
        JButton btnDate = new JButton("Filtra");
        styleButton(btnDate, new Color(155, 89, 182));
        btnDate.addActionListener(e -> filtraPerDate(txtDal.getText().trim(), txtAl.getText().trim()));
        filterPanel.add(btnDate);

        JButton btnTutte = new JButton("Tutte");
        styleButton(btnTutte, new Color(52, 73, 94));
        btnTutte.addActionListener(e -> mostraTutteTransazioni());
        filterPanel.add(btnTutte);

        panel.add(filterPanel, BorderLayout.NORTH);

        String[] columns = {"Data", "Tipo", "Descrizione", "Importo (€)"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableTransazioni = new JTable(tableModel);
        tableTransazioni.setRowHeight(25);
        tableTransazioni.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        tableTransazioni.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    String tipo = (String) table.getModel().getValueAt(row, 1);
                    if ("Deposito".equals(tipo)) c.setForeground(new Color(39, 174, 96));
                    else if ("Prelievo".equals(tipo)) c.setForeground(new Color(192, 57, 43));
                }
                return c;
            }
        });

        panel.add(new JScrollPane(tableTransazioni), BorderLayout.CENTER);
        return panel;
    }

    private void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.setPreferredSize(new Dimension(70, 30));
    }

    private void eseguiTransazione(String tipo) {
        try {
            double importo = Double.parseDouble(txtImporto.getText().replace(",", "."));
            String desc = txtDescrizione.getText().trim();

            if (desc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Descrizione obbligatoria!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            switch (tipo) {
                case "Deposito" -> conto.doDeposito(importo, desc);
                case "Prelievo" -> conto.doPrelievo(importo, desc);
                case "Pagamento" -> conto.doPagamento(importo, desc);
            }

            lblSaldo.setText("Saldo Attuale: € " + String.format("%.2f", conto.getSoldi()));
            txtImporto.setText(""); 
            txtDescrizione.setText("");
            mostraTutteTransazioni();
            JOptionPane.showMessageDialog(this, tipo + " eseguito!", "Successo", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Importo non valido!", "Errore", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Attenzione", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void filtraPerTipo(String tipo) {
        tableModel.setRowCount(0);
        String output = captureOutput(() -> conto.printTransazioni(tipo));
        parseAndPopulateTable(output);
    }

    private void filtraPerDescrizione(String descrizione) {
        tableModel.setRowCount(0);
        String output = captureOutput(() -> conto.printTransazioniByDesc(descrizione));
        parseAndPopulateTable(output);
    }

    private void filtraPerDate(String dataInizio, String dataFine) {
        tableModel.setRowCount(0);
        
        // CONVERTE CAMPI VUOTI IN "0" come richiede ContoBancario
        String dataInizioParam = dataInizio.isEmpty() ? "0" : dataInizio;
        String dataFineParam = dataFine.isEmpty() ? "0" : dataFine;
        
        try {
            String output = captureOutput(() -> conto.printTransazioniByDate(dataInizioParam, dataFineParam));
            parseAndPopulateTable(output);
            JOptionPane.showMessageDialog(this, "Filtro date applicato", "Fatto", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Formato data: dd/MM/yyyy", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostraTutteTransazioni() {
        filtraPerTipo("Tutti");
    }

    private String captureOutput(Runnable action) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        PrintStream newOut = new PrintStream(baos);
        
        try {
            System.setOut(newOut);
            action.run();
            newOut.flush();
            return baos.toString();
        } finally {
            System.setOut(oldOut);
        }
    }

    private void parseAndPopulateTable(String output) {
        String[] lines = output.split("\n");
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        
        for (int i = 0; i < lines.length - 3; i++) {
            String line1 = lines[i].trim();
            if (line1.startsWith("Tipologia:")) {
                try {
                    String tipo = line1.substring(line1.indexOf(":") + 1).trim();
                    
                    String dataStr = lines[i + 1].substring(lines[i + 1].indexOf(":") + 1).trim();
                    LocalDateTime data = LocalDateTime.parse(dataStr, fmt);
                    
                    String desc = lines[i + 2].substring(lines[i + 2].indexOf(":") + 1).trim();
                    String importoStr = lines[i + 3].substring(lines[i + 3].indexOf(":") + 1).trim();
                    double importo = Double.parseDouble(importoStr.replace(",", "."));
                    
                    tableModel.addRow(new Object[]{
                        data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                        tipo,
                        desc,
                        String.format("%.2f", importo)
                    });
                } catch (Exception ignored) {}
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankGUI().setVisible(true));
    }
}
