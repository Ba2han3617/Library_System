import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class LibraryUI extends JFrame {
    private final BooksDAO dao = new BooksDAO();

    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID","Book","Author","Page","Stock"}, 0) {
        @Override public boolean isCellEditable(int r,int c){ return false; }
    };
    private final JTable tbl = new JTable(model);
    private JButton btnAddProduct;
    private JButton btnEntry;
    private JButton btnSubtract;
    private JButton btnRefresh;
    private JButton btnDelete;
    private final JTextField txtSearch = new JTextField(18);
    private final JLabel btSearch     = new JLabel("Search");




    public LibraryUI() {
        super("Library System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(880, 500);
        setLocationRelativeTo(null);

        btnAddProduct = new JButton("+ Add Product");
        btnEntry    = new JButton("Stock Entry");
        btnSubtract=new JButton("Subtract Stock");
        btnRefresh   = new JButton("↻ Refresh");
        btnDelete   = new JButton("Delete Product");




        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(btnAddProduct);
        top.add(btnEntry);
        top.add(btnSubtract);
        top.add(btnRefresh);
        top.add(new JLabel(" Search:"));
        top.add(txtSearch);
        top.add(btnDelete);



        tbl.setRowHeight(22);
        add(new JScrollPane(tbl), BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);



        btnRefresh.addActionListener(e -> refresh());




        btnEntry.addActionListener(e -> {
            Integer id = askInt("Product ID to be entered in stock:");
            Integer q  = askInt("Amount:");
            if (id == null || q == null) { msg("This part cannot be left empty!"); return; }
            if( q <= 0){msg("The amount must be greater than 0."); return ;}
            dao.updatestock(id, q);
            msg("Stock entry: PID " + id + " +" + q);
            refresh();
        });

        btnSubtract.addActionListener(e -> {
            Integer id = askInt("Product ID to be reduced stock:");
            Integer q  = askInt("Amount:");
            if (id == null || q == null) { msg("This part cannot be left empty!"); return; }
            if (q <= 0) { msg("The amount must be greater than 0."); return; }
            dao.updatestock(id, -q);
            msg("Stock subtract: PID " + id + " +" + q);
            refresh();

        });

        btnDelete.addActionListener(e ->{
            Integer id = askInt("Deleted product ID: ");
            if (id == null) { msg("This part cannot be left empty!"); return; }
            try { dao.deletebooks(id); }
            catch (SQLException ex) { throw new RuntimeException(ex); }
            msg(id + " The book with the ID number has been deleted!");
            refresh();
        });

        btnAddProduct.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Book Name:");
            if (name == null || name.isBlank()) { msg("This part cannot be left empty!"); return; }

            String author = JOptionPane.showInputDialog(this, "Author Name:");
            if (author == null || author.isBlank()) { msg("This part cannot be left empty!"); return; }

            Integer page  = askInt("Number of pages:");
            if (page == null || page <= 0) { msg("This part cannot be left empty!"); return; }

            Integer stock = askInt("Starting stock:");
            if (stock == null || stock < 0) { msg("This part cannot be left empty!"); return; }

            boolean ok = dao.insertbooks(name.trim(), author.trim(), page, stock);
            if (ok) { msg("The product has been added: " + name); refresh(); }
            else {
                JOptionPane.showMessageDialog(this,
                        "The insertion failed. Details are on the console.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        txtSearch.addActionListener(e-> {
            String  q=txtSearch.getText().trim();
            if(q==null||q.isEmpty()){msg("You have left the search bar empty! "); return;};
            try{
                List<Books> rows = dao.SearchProduct(q);
                model.setRowCount(0);
                for(Books b:rows){
                    model.addRow(new Object[]{b.getId(), b.getName(),b.getAuthor(), b.getPage(), b.getStock() });
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });

        refresh();
    }


    private void refresh() {
        model.setRowCount(0);
        for (Books p : dao.findAll()) {
            model.addRow(new Object[]{ p.getId(), p.getName(), p.getAuthor(), p.getPage(), p.getStock() });
        }
    }

    private Integer askInt(String prompt) {
        String s = JOptionPane.showInputDialog(this, prompt);
        if (s == null || s.isBlank()) return null;
        try { return Integer.valueOf(s.trim()); }
        catch (NumberFormatException ex) { msg("Invalid number."); return null; }
    }

    private void msg(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
