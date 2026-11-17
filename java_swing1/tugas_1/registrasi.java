import javax.swing.*;
import java.awt.*;

public class registrasi extends JFrame {
    public registrasi() {
        super("Form Registrasi");

        JTextField nama = new JTextField(15);
        JTextField email = new JTextField(15);
        JPasswordField sandi = new JPasswordField(15);
        JRadioButton lakiLaki = new JRadioButton("Laki-laki");
        JRadioButton perempuan = new JRadioButton("Perempuan");
        ButtonGroup gender = new ButtonGroup();
        gender.add(lakiLaki);
        gender.add(perempuan);

        String[] daftarProdi = {
            "Pilih program studi",
            "Informatika",
            "Sistem Informasi",
            "Teknik kimia",
            "DKV"
        };
        JComboBox<String> prodi = new JComboBox<>(daftarProdi);

        JCheckBox membaca = new JCheckBox("Membaca");
        JCheckBox menggambar = new JCheckBox("Menggambar");
        JCheckBox musik = new JCheckBox("Musik");

        JPanel pilihanGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pilihanGender.add(lakiLaki);
        pilihanGender.add(perempuan);
        JPanel pilihanHobi = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pilihanHobi.add(membaca);
        pilihanHobi.add(menggambar);
        pilihanHobi.add(musik);

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0;
        c.gridy = 0;
        form.add(new JLabel("Nama:"), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        form.add(nama, c);

        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        form.add(new JLabel("Email:"), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        form.add(email, c);

        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        form.add(new JLabel("Sandi:"), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        form.add(sandi, c);

        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        form.add(new JLabel("Gender:"), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        form.add(pilihanGender, c);

        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        form.add(new JLabel("Prodi:"), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        form.add(prodi, c);

        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        form.add(new JLabel("Hobi:"), c);
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        form.add(pilihanHobi, c);

        JButton daftar = new JButton("Daftar");
        daftar.addActionListener(e -> {
            String teksNama = nama.getText().trim();
            String teksEmail = email.getText().trim();
            String teksSandi = new String(sandi.getPassword());

            
            if (teksNama.isEmpty() || teksEmail.isEmpty() || teksSandi.isEmpty()
                || (!lakiLaki.isSelected() && !perempuan.isSelected())
                || prodi.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Lengkapi semua data sebelum mendaftar.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String teksGender = lakiLaki.isSelected() ? "Laki-laki" : "Perempuan";
            String daftarHobi = "";
            if (membaca.isSelected()) { daftarHobi += "Membaca "; }
            if (menggambar.isSelected()) { daftarHobi += "Menggambar "; }
            if (musik.isSelected()) { daftarHobi += "Musik "; }
            if (daftarHobi.isEmpty()) { daftarHobi = "Tidak ada"; }

            String ringkasan = "Nama: " + teksNama
                + "\nEmail: " + teksEmail
                + "\nGender: " + teksGender
                + "\nProdi: " + prodi.getSelectedItem()
                + "\nHobi: " + daftarHobi.trim();
            JOptionPane.showMessageDialog(this, ringkasan, "Ringkasan Registrasi", JOptionPane.INFORMATION_MESSAGE);
        });

        c.gridx = 1;
        c.gridy = 6;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        form.add(daftar, c);

        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
        add(form);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new registrasi().setVisible(true));
    }
}
