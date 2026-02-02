package ES1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.text.DecimalFormat;

public class GUIContoBancario extends JFrame {
    private ContoBancario conto;
    private DecimalFormat df = new DecimalFormat("€#,##0.00");
    
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
        setTitle("🏦 Conto Bancario");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Saldo
        lblSaldo = new JLabel("Saldo: " + df.format(conto.getSoldi()));
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 24));
        lblSaldo.setHorizontalAlignment(JLabel.CENTER);
        lblSaldo.setOpaque(true);
        lblSaldo.setBackground(Color.CYAN);
        lblSaldo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(lblSaldo, BorderLayout.NORTH);
        
        // Operazioni + Output
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        
        // Sinistra: Operazioni
        JPanel pnlOps = createOperazioniPanel();
        split.setLeftComponent(pnlOps);
        
        // Destra: Output
        txtOutput = new JTextArea(25, 35);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtOutput.setEditable(false);
        txtOutput.setBorder(BorderFactory.createTitledBorder("📋 Log Transazioni"));
        split.setRightComponent(new JScrollPane(txtOutput));
        
        add(split, BorderLayout.CENTER);
        
        // Filtri
        add(createFiltriPanel(), BorderLayout.SOUTH);
        
        split.setDividerLocation(300);
        setVisible(true);
    }
    
    private JPanel createOperazioniPanel() {
        JPanel pnl = new JPanel(new GridLayout(7, 1, 10, 10));
        pnl.setBorder(BorderFactory.createTitledBorder("💳 Operazioni"));
        pnl.setBackground(Color.WHITE);
        
        // Campi
        pnl.add(new JLabel("Importo:", JLabel.CENTER));
        txtImporto = new JTextField("100.00");
        pnl.add(txtImporto);
        
        pnl.add(new JLabel("Descrizione:", JLabel.CENTER));
        txtDescrizione = new JTextField("Test GUI");
        pnl.add(txtDescrizione);
        
        // Bottoni SEPARATI (no switch problematico!)
        btnDeposito = new JButton("✅ DEPOSITO");
        btnPrelievo = new JButton("💳 PRELIEVO");
        btnPagamento = new JButton("💰 PAGAMENTO");
        
        btnDeposito.setBackground(Color.GREEN);
        btnPrelievo.setBackground(Color.ORANGE);
        btnPagamento.setBackground(Color.RED);
        btnDeposito.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnPrelievo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnPagamento.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        
        pnl.add(btnDeposito);
        pnl.add(btnPrelievo);
        pnl.add(btnPagamento);
        
        // ⭐ LISTENER SEPARATI (semplice e sicuro)
        btnDeposito.addActionListener(e -> eseguiDeposito());
        btnPrelievo.addActionListener(e -> eseguiPrelievo());
        btnPagamento.addActionListener(e -> eseguiPagamento());
        
        return pnl;
    }
    
    // ⭐ METODI SEPARATI (no ActionEvent cast!)
    private void eseguiDeposito() {
        eseguiOperazione("✅ DEPOSITO", "Deposito", () -> conto.doDeposito(importo(), descrizione()));
    }
    
    private void eseguiPrelievo() {
        eseguiOperazione("💳 PRELIEVO", "Prelievo", () -> conto.doPrelievo(importo(), descrizione()));
    }
    
    private void eseguiPagamento() {
        eseguiOperazione("💰 PAGAMENTO", "Pagamento", () -> conto.doPagamento(importo(), descrizione()));
    }
    
    private double importo() {
        return Double.parseDouble(txtImporto.getText().replace(",", "."));
    }
    
    private String descrizione() {
        return txtDescrizione.getText();
    }
    
    // ⭐ METODO CENTRALE CON CONFERMA VISIVA
    private void eseguiOperazione(String emoji, String tipoOp, Runnable operazione) {
        try {
            operazione.run();
            
            // 🔥 MOSTRA CONFERMA
            String conferma = String.format("""
                    %s OPERAZIONE COMPLETATA!
                    ╔══════════════════════════════════════╗
                    ║ Tipo:          %s
                    ║ Data:          %s
                    ║ Descrizione:   %s
                    ║ Importo:       %s
                    ║ NUOVO SALDO:   %s
                    ╚══════════════════════════════════════╝
                    """, 
                emoji, tipoOp, LocalDateTime.now().withNano(0), 
                descrizione(), df.format(importo()), df.format(conto.getSoldi()));
            
            txtOutput.insert(conferma + "\n\n", 0);
            txtOutput.setCaretPosition(0);
            
            aggiornaSaldo();
            Toolkit.getDefaultToolkit().beep();  // Suono!
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage());
        }
    }
    
    private JPanel createFiltriPanel() {
        JPanel pnl = new JPanel(new FlowLayout());
        pnl.setBorder(BorderFactory.createTitledBorder("🔍 Filtri"));
        
        cmbTipo = new JComboBox<>(new String[]{"Tutte", "Prelievo", "Deposito", "Pagamento"});
        btnTutte = new JButton("📋 Tutte");
        btnFiltroTipo = new JButton("🔎 Tipo");
        txtFiltroDesc = new JTextField(12);
        btnFiltroDesc = new JButton("🔍 Descr.");
        
        pnl.add(cmbTipo);
        pnl.add(btnTutte);
        pnl.add(btnFiltroTipo);
        pnl.add(txtFiltroDesc);
        pnl.add(btnFiltroDesc);
        
        btnTutte.addActionListener(e -> printTutte("Nessuno"));
        btnFiltroTipo.addActionListener(e -> printTutte((String)cmbTipo.getSelectedItem()));
        btnFiltroDesc.addActionListener(e -> printPerDescrizione(txtFiltroDesc.getText()));
        
        return pnl;
    }
    
    private String catturaOutput(Runnable r) {
        PrintStream orig = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        r.run();
        System.setOut(orig);
        return baos.toString();
    }
    
    private void printTutte(String filtro) {
        txtOutput.setText(catturaOutput(() -> conto.printTransazioni(filtro)));
    }
    
    private void printPerDescrizione(String desc) {
        txtOutput.setText(catturaOutput(() -> conto.printTransazioniByDesc(desc)));
    }
    
    private void aggiornaSaldo() {
        lblSaldo.setText("Saldo: " + df.format(conto.getSoldi()));
        setTitle("🏦 Conto - " + df.format(conto.getSoldi()));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUIContoBancario::new);
    }
}
