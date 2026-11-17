import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EditorTeks extends JFrame {
    private final JTextArea area = new JTextArea(20, 60);
    private Path fileAktif;

    public EditorTeks() {
        super("Editor Teks - Dokumen Baru");

        JToolBar toolbar = new JToolBar();
        tambahTombol(toolbar, "Baru", this::dokumenBaru);
        tambahTombol(toolbar, "Buka", this::bukaFile);
        tambahTombol(toolbar, "Simpan", this::simpanFile);
        toolbar.addSeparator();
        tambahTombol(toolbar, "Potong", area::cut);
        tambahTombol(toolbar, "Salin", area::copy);
        tambahTombol(toolbar, "Tempel", area::paste);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        tambahMenuItem(menuFile, "Baru", this::dokumenBaru, KeyEvent.VK_N);
        tambahMenuItem(menuFile, "Buka", this::bukaFile, KeyEvent.VK_O);
        tambahMenuItem(menuFile, "Simpan", this::simpanFile, KeyEvent.VK_S);
        menuFile.addSeparator();
        JMenuItem keluar = new JMenuItem("Keluar");
        keluar.addActionListener(e -> dispose());
        menuFile.add(keluar);

        JMenu menuEdit = new JMenu("Edit");
        tambahMenuItem(menuEdit, "Potong", area::cut, KeyEvent.VK_X);
        tambahMenuItem(menuEdit, "Salin", area::copy, KeyEvent.VK_C);
        tambahMenuItem(menuEdit, "Tempel", area::paste, KeyEvent.VK_V);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        setJMenuBar(menuBar);

        add(toolbar, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    private void tambahTombol(JToolBar toolbar, String teks, Runnable aksi) {
        JButton tombol = new JButton(teks);
        tombol.addActionListener(e -> aksi.run());
        toolbar.add(tombol);
    }

    private void tambahMenuItem(JMenu menu, String teks, Runnable aksi, int tombolPintas) {
        JMenuItem item = new JMenuItem(teks);
        item.setAccelerator(KeyStroke.getKeyStroke(tombolPintas, KeyEvent.CTRL_DOWN_MASK));
        item.addActionListener(e -> aksi.run());
        menu.add(item);
    }

    private void dokumenBaru() {
        area.setText("");
        fileAktif = null;
        setTitle("Editor Teks - Dokumen Baru");
    }

    private void bukaFile() {
        JFileChooser pemilih = new JFileChooser();
        if (pemilih.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path file = pemilih.getSelectedFile().toPath();
        try {
            area.setText(Files.readString(file));
            fileAktif = file;
            setTitle("Editor Teks - " + file.getFileName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "File tidak dapat dibuka: " + ex.getMessage(),
                "Kesalahan",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void simpanFile() {
        Path tujuan = fileAktif;
        if (tujuan == null) {
            JFileChooser pemilih = new JFileChooser();
            if (pemilih.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            tujuan = pemilih.getSelectedFile().toPath();
        }

        try {
            Files.writeString(tujuan, area.getText());
            fileAktif = tujuan;
            setTitle("Editor Teks - " + tujuan.getFileName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "File tidak dapat disimpan: " + ex.getMessage(),
                "Kesalahan",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorTeks().setVisible(true));
    }
}
