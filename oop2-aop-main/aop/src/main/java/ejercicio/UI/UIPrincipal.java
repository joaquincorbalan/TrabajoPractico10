package ejercicio.UI;

import ejercicio.Modele.*;
import ejercicio.Persistence.CVSReader;
import ejercicio.Persistence.CVSWriter;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class UIPrincipal {

    public static final String FILECONCURSO = "/concursos.txt";
    public static final String FILEINSCRIPTOS = "C:\\Users\\joaqu\\IdeaProjects\\TrabajoPractico10\\oop2-aop-main\\aop\\src\\main\\resources\\data\\inscriptos.txt";


    public static class RadioCompetition {
        //Declaracion de variables del objeto son todas de UI
        private JPanel contentPane;
        private JLabel lblName;
        private JTextField txtName;
        private JLabel lblLastName;
        private JTextField txtLastName;
        private JLabel lblId;
        private JTextField txtId;
        private JLabel lblPhone;
        private JTextField txtPhone;
        private JLabel lblEmail;
        private JTextField txtEmail;
        private JComboBox<Concursos> comboBox;
        private JButton btnOk;
        private JLabel lblCompetition;

        public RadioCompetition() {
            var frame = new JFrame("Inscription to Competition");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 451, 229);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            frame.setContentPane(contentPane);
            formElements();
            layout();
            frame.setVisible(true);
        }

        private void formElements() {
            lblName = new JLabel("Nombre:");
            txtName = new JTextField();
            txtName.setColumns(10);
            lblLastName = new JLabel("Apellido:");
            txtLastName = new JTextField();
            txtLastName.setColumns(10);
            lblId = new JLabel("Dni:");
            txtId = new JTextField();
            txtId.setColumns(10);
            lblPhone = new JLabel("Telefono:");
            txtPhone = new JTextField();
            txtPhone.setColumns(10);
            lblEmail = new JLabel("Email:");
            txtEmail = new JTextField();
            txtEmail.setColumns(10);
            btnOk = new JButton("Ok");
            btnOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnOk.setEnabled(false);
                    saveInscription();
                    btnOk.setEnabled(true);
                }
            });
            lblCompetition = new JLabel("Concurso:");
            comboBox = new JComboBox<Concursos>();
            todosLosConcursos();
        }

        @Log
        private void todosLosConcursos() {
            LectorMetod lector = new CVSReader();
            List<Concursos> todos = lector.recuperarConcursos(FILECONCURSO);
            GestorConcursos gestor = new GestorConcursos(todos);
            List<Concursos> vigentes = gestor.getConcursosVigentes(LocalDate.now());
            comboBox.removeAllItems();
            comboBox.addItem(new Concursos("", "Seleccione un concurso", null, null));
            for (Concursos c : vigentes) {
                comboBox.addItem(c);
            }
        }

        @Log
        private void saveInscription() {
            EscritorMetod escritor = new CVSWriter();
            try {
                Concursos seleccionado = (Concursos) comboBox.getSelectedItem();
                if (seleccionado == null || seleccionado.getIdConcurso().isEmpty()) {
                    JOptionPane.showMessageDialog(this.contentPane, "Debe elegir un Concurso válido");
                    return;
                }

                Participantes p = new Participantes(
                        txtName.getText(),
                        txtLastName.getText(),
                        txtPhone.getText(),
                        txtEmail.getText(),
                        txtId.getText(),
                        seleccionado.getIdConcurso()
                );

                escritor.inscribir(p, FILEINSCRIPTOS);

                JOptionPane.showMessageDialog(this.contentPane, "Inscripción guardada correctamente en inscriptos.txt");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this.contentPane, "Error al inscribir concurso: " + e.getMessage());
            }
        }


        private void layout() {
            GroupLayout gl_contentPane = new GroupLayout(contentPane);
            gl_contentPane.setHorizontalGroup(gl_contentPane
                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
                            .addGroup(gl_contentPane
                                    .createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(gl_contentPane
                                            .createSequentialGroup()
                                            .addGroup(gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblLastName).addComponent(lblId)
                                                    .addComponent(lblPhone).addComponent(lblEmail)
                                                    .addComponent(lblName).addComponent(lblCompetition))
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                            .addGroup(
                                                    gl_contentPane.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE,
                                                                    Short.MAX_VALUE)
                                                            .addComponent(txtEmail, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(txtPhone, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(txtId, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(txtLastName, GroupLayout.Alignment.TRAILING)
                                                            .addComponent(txtName, Alignment.TRAILING,
                                                                    GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                                    .addComponent(btnOk, Alignment.TRAILING,
                                            GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap()));
            gl_contentPane
                    .setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_contentPane.createSequentialGroup()
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(txtName, GroupLayout.PREFERRED_SIZE,
                                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblName))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                            .addComponent(lblLastName).addComponent(txtLastName,
                                                    GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                    GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                            .addComponent(lblId).addComponent(
                                                    txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                                    GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(ComponentPlacement.RELATED)
                                    .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                            .addGroup(
                                                    gl_contentPane.createSequentialGroup().addComponent(lblPhone)
                                                            .addPreferredGap(ComponentPlacement.UNRELATED)
                                                            .addComponent(lblEmail))
                                            .addGroup(gl_contentPane.createSequentialGroup()
                                                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE,
                                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(ComponentPlacement.RELATED)
                                                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE,
                                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(ComponentPlacement.RELATED).addGroup(
                                                            gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                                                    .addComponent(comboBox, GroupLayout.PREFERRED_SIZE,
                                                                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(lblCompetition))))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnOk)
                                    .addContainerGap(67, Short.MAX_VALUE)));
            contentPane.setLayout(gl_contentPane);
        }
    }

}