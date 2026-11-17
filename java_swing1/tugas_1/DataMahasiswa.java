import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataMahasiswa extends JFrame {
    private final DefaultTableModel model = new DefaultTableModel(
        new Object[] { "NIM", "Nama", "Program Studi", "IPK" },
        0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabel = new JTable(model);

    public DataMahasiswa() {
        super("Data Mahasiswa");

        model.addRow(new Object[] { "23001", "Adit", "Informatika", 3.45 });
        model.addRow(new Object[] { "23002", "Vian", "Sistem Informasi", 3.20 });
        model.addRow(new Object[] { "23003", "Satria", "Teknik kimia", 3.75 });

        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton ubah = new JButton("Ubah Data Terpilih");
        ubah.addActionListener(e -> ubahDataTerpilih());

        add(new JScrollPane(tabel), BorderLayout.CENTER);
        JPanel bawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bawah.add(ubah);
        add(bawah, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
    }

    private void ubahDataTerpilih() {
        int barisTabel = tabel.getSelectedRow();
        if (barisTabel == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Pilih data mahasiswa yang ingin diubah.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int baris = tabel.convertRowIndexToModel(barisTabel);
        JTextField nim = new JTextField(model.getValueAt(baris, 0).toString(), 12);
        JTextField nama = new JTextField(model.getValueAt(baris, 1).toString(), 12);
        JTextField prodi = new JTextField(model.getValueAt(baris, 2).toString(), 12);
        JTextField ipk = new JTextField(model.getValueAt(baris, 3).toString(), 12);

        JPanel form = new JPanel(new GridLayout(0, 2, 6, 6));
        form.add(new JLabel("NIM:"));
        form.add(nim);
        form.add(new JLabel("Nama:"));
        form.add(nama);
        form.add(new JLabel("Program Studi:"));
        form.add(prodi);
        form.add(new JLabel("IPK (0-4):"));
        form.add(ipk);

        int pilihan = JOptionPane.showConfirmDialog(
            this,
            form,
            "Ubah Data Mahasiswa",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        if (pilihan != JOptionPane.OK_OPTION) {
            return;
        }

        String teksNim = nim.getText().trim();
        String teksNama = nama.getText().trim();
        String teksProdi = prodi.getText().trim();
        String teksIpk = ipk.getText().trim();
        if (teksNim.isEmpty() || teksNama.isEmpty() || teksProdi.isEmpty() || teksIpk.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Semua data harus diisi.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double nilaiIpk;
        try {
            nilaiIpk = Double.parseDouble(teksIpk);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "IPK harus berupa angka.",
                "Kesalahan",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (nilaiIpk < 0 || nilaiIpk > 4) {
            JOptionPane.showMessageDialog(
                this,
                "IPK harus berada di antara 0 dan 4.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        model.setValueAt(teksNim, baris, 0);
        model.setValueAt(teksNama, baris, 1);
        model.setValueAt(teksProdi, baris, 2);
        model.setValueAt(nilaiIpk, baris, 3);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataMahasiswa().setVisible(true));
    }
}
