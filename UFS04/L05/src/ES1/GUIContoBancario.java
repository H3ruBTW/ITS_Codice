package ES1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;

public class GUIContoBancario extends JFrame {
    private ContoBancario conto;
    private DecimalFormat df = new DecimalFormat("€#,##0.00");
    
    // GUI Components
    private JLabel lblSaldo;
    private JTextField txtImporto, txtDescrizione;
    private JTextArea txtOutput;
    private JButton btnDeposito, btnPrelievo, btnPagamento;
    private JButton btnTutte, btnFiltroTipo, btnFiltroDesc;
    private JComboBox<String> cmbTipo;
    private JTextField txtFiltroDesc;
    
    public GUIContoBancario() {
        conto = new ContoBancario(1000.0);
        initGUI();
    }
    
    private void initGUI() {
        setTitle("🏦 Conto Bancario GUI");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Layout principale
        setLayout(new BorderLayout(10, 10));
        
        // 1. SALDO
        JPanel pnlSaldo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblSaldo = new JLabel("Saldo: " + df.format(conto.getSoldi()));
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 24));
        lblSaldo.setForeground(Color.BLUE);
        pnlSaldo.add(lblSaldo);
        add(pnlSaldo, BorderLayout.NORTH);
        
        // 2. OPERAZIONI
        JPanel pnlOps = createOperazioniPanel();
        add(pnlOps, BorderLayout.WEST);
        
        // 3. OUTPUT (principale)
        txtOutput = new JTextArea(20, 40);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtOutput.setEditable(false);
        add(new JScrollPane(txtOutput), BorderLayout.CENTER);
        
        // 4. Filtri
        JPanel pnlFiltri = createFiltriPanel();
        add(pnlFiltri, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel createOperazioniPanel() {
        JPanel pnl = new JPanel(new GridLayout(8, 1, 10, 10));
        pnl.setBorder(BorderFactory.createTitledBorder("💳 Operazioni"));
        pnl.setPreferredSize(new Dimension(250, 500));
        
        // Campi input
        pnl.add(new JLabel("Importo:", JLabel.CENTER));
        txtImporto = new JTextField("100.00");
        pnl.add(txtImporto);
        
        pnl.add(new JLabel("Descrizione:", JLabel.CENTER));
        txtDescrizione = new JTextField("Operazione GUI");
        pnl.add(txtDescrizione);
        
        // Bottoni operazioni
        btnDeposito = new JButton("✅ DEPOSITO");
        btnPrelievo = new JButton("💳 PRELIEVO");
        btnPagamento = new JButton("💰 PAGAMENTO");
        
        btnDeposito.setBackground(Color.GREEN);
        btnPrelievo.setBackground(Color.ORANGE);
        btnPagamento.setBackground(Color.RED);
        
        btnDeposito.setFont(new Font("Arial", Font.BOLD, 12));
        btnPrelievo.setFont(new Font("Arial", Font.BOLD, 12));
        btnPagamento.setFont(new Font("Arial", Font.BOLD, 12));
        
        pnl.add(btnDeposito);
        pnl.add(btnPrelievo);
        pnl.add(btnPagamento);
        
        // Listener operazioni
        ActionListener opListener = e -> {
            try {
                double importo = Double.parseDouble(txtImporto.getText());
                String desc = txtDescrizione.getText();
                
                if (e.getSource() == btnDeposito) conto.doDeposito(importo, desc);
                else if (e.getSource() == btnPrelievo) conto.doPrelievo(importo, desc);
                else conto.doPagamento(importo, desc);
                
                aggiornaSaldo();
                JOptionPane.showMessageDialog(this, "✅ Operazione OK!\nSaldo: " + df.format(conto.getSoldi()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage());
            }
        };
        
        btnDeposito.addActionListener(opListener);
        btnPrelievo.addActionListener(opListener);
        btnPagamento.addActionListener(opListener);
        
        return pnl;
    }
    
    private JPanel createFiltriPanel() {
        JPanel pnl = new JPanel(new GridLayout(1, 4));
        pnl.setBorder(BorderFactory.createTitledBorder("🔍 Visualizza Transazioni"));
        
        // Filtro tipo
        cmbTipo = new JComboBox<>(new String[]{"Tutte", "Prelievo", "Deposito", "Pagamento"});
        btnTutte = new JButton("📋 Tutte");
        btnFiltroTipo = new JButton("🔎 Per Tipo");
        pnl.add(cmbTipo);
        pnl.add(btnTutte);
        pnl.add(btnFiltroTipo);
        
        // Filtro descrizione
        txtFiltroDesc = new JTextField("test", 8);
        btnFiltroDesc = new JButton("🔍 Per Descrizione");
        pnl.add(txtFiltroDesc);
        pnl.add(btnFiltroDesc);
        
        // Listener filtri
        btnTutte.addActionListener(e -> printTutte("Nessuno"));
        btnFiltroTipo.addActionListener(e -> {
            String tipo = (String) cmbTipo.getSelectedItem();
            printTutte(tipo.equals("Tutte") ? "Nessuno" : tipo);
        });
        btnFiltroDesc.addActionListener(e -> {
            String desc = txtFiltroDesc.getText();
            printPerDescrizione(desc);
        });
        
        return pnl;
    }
    
    // MAGIC: Cattura System.out dei tuoi metodi!
    private String catturaOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        
        action.run();
        
        System.setOut(originalOut);
        return baos.toString();
    }
    
    private void printTutte(String filtro) {
        String output = catturaOutput(() -> conto.printTransazioni(filtro));
        txtOutput.setText(output);
        txtOutput.setCaretPosition(0);
    }
    
    private void printPerDescrizione(String desc) {
        String output = catturaOutput(() -> conto.printTransazioniByDesc(desc));
        txtOutput.setText(output);
        txtOutput.setCaretPosition(0);
    }
    
    private void aggiornaSaldo() {
        lblSaldo.setText("Saldo: " + df.format(conto.getSoldi()));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIContoBancario::new);
    }
}
