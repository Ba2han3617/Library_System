import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoginScreenUI extends JFrame{
    private final MembersDAO girisdao=new MembersDAO();


    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"id","name","surname","email","username","password"}, 0) {
        @Override public boolean isCellEditable(int r,int c){ return false; }
    };

    private final JTextField txtuserr = new JTextField(20);
    private final JTextField txtpassw= new JTextField(20);
    private JButton btnSignIn=new JButton("Entry");
    private JButton btnBecomeMember=new JButton("Become Member");


    public LoginScreenUI(){
        super("Library System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(12,12));



        JPanel top = new JPanel(new GridLayout(0, 2, 8, 8));
        top.add(new JLabel("Username:"));
        top.add(txtuserr);
        top.add(new JLabel("Password:"));
        top.add(txtpassw);
        top.add(btnSignIn);
        top.add(btnBecomeMember);

        JPanel root = new JPanel(new BorderLayout(12,12));
        root.add(top, BorderLayout.NORTH);
        setContentPane(root);


        setSize(800, 500);

        btnSignIn.addActionListener(e -> {
            String user=txtuserr.getText().trim();
            String passw=txtpassw.getText().trim();
            if(user.isEmpty()||passw.isEmpty()){
                msg("No fields can be left empty when logging in!");
                return;
            }
            try{
                boolean ok=girisdao.SıgnIn(user,passw);
                if(!ok){
                    msg("The username or password is wrong!");
                    return;
                }
                    msg("You've logged in!");
                dispose();
                LibraryUI library=new LibraryUI();
                library.setVisible(true);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });

        btnBecomeMember.addActionListener(e -> {
            try{
                dispose();
                MembersUI members=new MembersUI();
                members.setLocationRelativeTo(null);
                members.setVisible(true);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        });

    }
    private void msg(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
