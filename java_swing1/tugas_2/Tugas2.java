import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tugas2 extends JFrame {
    private final DefaultListModel<String> modelBelanja = new DefaultListModel<>();
    private final JList<String> daftarBelanja = new JList<>(modelBelanja);
    private final JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
    private final JButton ubahLayout = new JButton("Ubah ke GridLayout");
    private final JLabel statusLayout = new JLabel("Layout aktif: FlowLayout");

    public Tugas2() {
        super("Tugas 2 - Java Swing");

        setJMenuBar(buatMenu());

        JTabbedPane tab = new JTabbedPane();
        tab.addTab("Layout Tombol", buatPanelLayout());
        tab.addTab("Form Login", buatPanelLogin());
        tab.addTab("Daftar Belanja", buatPanelBelanja());
        tab.addTab("Jam Digital", buatPanelJam());

        add(tab, BorderLayout.CENTER);
        ((JComponent) getContentPane()).setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(520, 390));
        pack();
        setLocationRelativeTo(null);
    }

    private JMenuBar buatMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem keluar = new JMenuItem("Keluar");
        keluar.addActionListener(e -> {
            int pilihan = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menutup aplikasi?",
                "Konfirmasi Keluar",
                JOptionPane.YES_NO_OPTION
            );
            if (pilihan == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        menuFile.add(keluar);

        JMenu menuBantuan = new JMenu("Bantuan");
        JMenuItem tentang = new JMenuItem("Tentang");
        tentang.addActionListener(e -> JOptionPane.showMessageDialog(
            this,
            "Tugas 2 Pemrograman 1\nContoh penggunaan layout, form, JList, dan Timer.",
            "Tentang Aplikasi",
            JOptionPane.INFORMATION_MESSAGE
        ));
        menuBantuan.add(tentang);

        menuBar.add(menuFile);
        menuBar.add(menuBantuan);
        return menuBar;
    }

    private JPanel buatPanelLayout() {
        JPanel panel = new JPanel(new BorderLayout(8, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JButton satu = new JButton("Tombol Satu");
        JButton dua = new JButton("Tombol Dua");
        JButton tiga = new JButton("Tombol Tiga");
        satu.addActionListener(e -> statusLayout.setText("Tombol Satu dipilih."));
        dua.addActionListener(e -> statusLayout.setText("Tombol Dua dipilih."));
        tiga.addActionListener(e -> statusLayout.setText("Tombol Tiga dipilih."));
        panelTombol.add(satu);
        panelTombol.add(dua);
        panelTombol.add(tiga);
        panel.add(panelTombol, BorderLayout.CENTER);

        JPanel bagianBawah = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 4));
        ubahLayout.addActionListener(e -> {
            if (panelTombol.getLayout() instanceof FlowLayout) {
                panelTombol.setLayout(new GridLayout(1, 3, 8, 8));
                ubahLayout.setText("Ubah ke FlowLayout");
                statusLayout.setText("Layout aktif: GridLayout 1 x 3");
            } else {
                panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
                ubahLayout.setText("Ubah ke GridLayout");
                statusLayout.setText("Layout aktif: FlowLayout");
            }
            panelTombol.revalidate();
            panelTombol.repaint();
        });
        bagianBawah.add(ubahLayout);
        bagianBawah.add(statusLayout);
        panel.add(bagianBawah, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buatPanelLogin() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEmptyBorder(18, 24, 18, 24),
            "Form Login"
        ));

        JTextField username = new JTextField(18);
        JPasswordField password = new JPasswordField(18);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(7, 7, 7, 7);
        c.anchor = GridBagConstraints.WEST;

        tambahBarisForm(panel, c, 0, "Username:", username);
        tambahBarisForm(panel, c, 1, "Password:", password);

        JButton masuk = new JButton("Masuk");
        masuk.setToolTipText("Periksa apakah username dan password sudah diisi");
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.EAST;
        panel.add(masuk, c);

        masuk.addActionListener(e -> {
            String teksUsername = username.getText().trim();
            char[] teksPassword = password.getPassword();
            if (teksUsername.isEmpty() || teksPassword.length == 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Username dan password wajib diisi.",
                    "Data Belum Lengkap",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            JOptionPane.showMessageDialog(
                this,
                "Form berhasil diisi. Halo, " + teksUsername + "!",
                "Login Berhasil",
                JOptionPane.INFORMATION_MESSAGE
            );
            password.setText("");
        });
        return panel;
    }

    private void tambahBarisForm(
        JPanel panel,
        GridBagConstraints c,
        int baris,
        String label,
        JComponent input
    ) {
        c.gridy = baris;
        c.gridx = 0;
        c.weightx = 0;
        c.fill = GridBagConstraints.NONE;
        panel.add(new JLabel(label), c);

        c.gridx = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(input, c);
    }

    private JPanel buatPanelBelanja() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        JTextField barang = new JTextField();
        JButton tambah = new JButton("Tambah");
        JPanel input = new JPanel(new BorderLayout(8, 0));
        input.add(new JLabel("Nama barang:"), BorderLayout.WEST);
        input.add(barang, BorderLayout.CENTER);
        input.add(tambah, BorderLayout.EAST);
        panel.add(input, BorderLayout.NORTH);

        daftarBelanja.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        daftarBelanja.setVisibleRowCount(8);
        panel.add(new JScrollPane(daftarBelanja), BorderLayout.CENTER);

        JButton hapus = new JButton("Hapus Terpilih");
        JPanel bagianBawah = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bagianBawah.add(hapus);
        panel.add(bagianBawah, BorderLayout.SOUTH);

        tambah.addActionListener(e -> {
            String namaBarang = barang.getText().trim();
            if (namaBarang.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Nama barang tidak boleh kosong.",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE
                );
                barang.requestFocusInWindow();
                return;
            }
            modelBelanja.addElement(namaBarang);
            barang.setText("");
            barang.requestFocusInWindow();
        });
        barang.addActionListener(e -> tambah.doClick());

        hapus.addActionListener(e -> {
            int indeks = daftarBelanja.getSelectedIndex();
            if (indeks == -1) {
                JOptionPane.showMessageDialog(
                    this,
                    "Pilih barang yang ingin dihapus.",
                    "Belum Ada Pilihan",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            modelBelanja.remove(indeks);
        });
        return panel;
    }

    private JPanel buatPanelJam() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Jam Digital"));

        JLabel jam = new JLabel();
        jam.setFont(new Font("Monospaced", Font.BOLD, 48));
        jam.setForeground(new Color(35, 91, 135));
        DateTimeFormatter formatJam = DateTimeFormatter.ofPattern("HH:mm:ss");
        jam.setText(LocalTime.now().format(formatJam));

        JLabel keterangan = new JLabel("Waktu saat ini");
        keterangan.setFont(new Font("SansSerif", Font.PLAIN, 15));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(8, 8, 8, 8);
        panel.add(jam, c);
        c.gridy = 1;
        panel.add(keterangan, c);

        Timer timer = new Timer(1000, e -> jam.setText(LocalTime.now().format(formatJam)));
        timer.start();
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tugas2().setVisible(true));
    }
}
