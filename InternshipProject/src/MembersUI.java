import javax.swing.*; import java.awt.*;
import javax.swing.table.DefaultTableModel;


public class MembersUI extends JFrame{
private final MembersDAO membersDAO=new MembersDAO();

    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"id","name","surname","email","username","password"}, 0) {
        @Override public boolean isCellEditable(int r,int c){ return false; }
    };

    private final JTextField txtName = new JTextField(20);
    private final JTextField txtSurname = new JTextField(20);
    private final JTextField txtMail = new JTextField(20);
    private final JTextField txtUser = new JTextField(20);
    private final JTextField txtPassword= new JTextField(20);
    private JButton btnEntry=new JButton("Save/Login");




    public MembersUI() {
        super("Library System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12,12));


        JPanel top = new JPanel(new GridLayout(0, 2, 8, 8));
        top.add(new JLabel("Name:"));
        top.add(txtName);
        top.add(new JLabel("Surname:"));
        top.add(txtSurname);
        top.add(new JLabel("E-mail:"));
        top.add(txtMail);
        top.add(new JLabel("Username:"));
        top.add(txtUser);
        top.add(new JLabel("Password:"));
        top.add(txtPassword);
        top.add(btnEntry);

        JPanel root = new JPanel(new BorderLayout(12,12));
        root.add(top, BorderLayout.NORTH);
        setContentPane(root);


        setSize(900, 600);

        btnEntry.addActionListener(e ->
        {
            String ad=txtName.getText().trim();
            String soyad=txtSurname.getText().trim();
            String mail=txtMail.getText().trim();
            String userName=txtUser.getText().trim();
            String passWord=txtPassword.getText().trim();
            if(ad==null||soyad==null||mail ==null||userName==null||passWord==null){
                msg("The entered information is missing!");
                return;
            }
            try{
                membersDAO.addUser(ad,soyad,mail,userName,passWord);
                msg("Registration has been added");

                dispose();
                LibraryUI library=new LibraryUI();
                library.setVisible(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });


    }


    private void refresh() {
        model.setRowCount(0);
        for (Members p : MembersDAO.findAll()) {
            model.addRow(new Object[]{ p.getId(), p.getName(), p.getSurname(), p.getEmail(), p.getUsername(),p.getPassword() });
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
