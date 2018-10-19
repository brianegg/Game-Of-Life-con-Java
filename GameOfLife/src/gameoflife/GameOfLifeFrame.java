package gameoflife;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.awt.Image;
import java.awt.Graphics;

/**
 *
 * @author Brian
 */
public class GameOfLifeFrame extends javax.swing.JFrame {
    // Las dimensiones para el juego
    final int dimensiones = 200;
    // Se guardará la generación actual en un arreglo bidimensional de booleans
    boolean[][] celdasActuales = new boolean[dimensiones][dimensiones];
    // Se guardará la siguiente generacion en un arreglo bidimensional de booleans
    boolean[][] celdasSiguientes = new boolean[dimensiones][dimensiones];
    // Variable para pausar o continuar el juego
    boolean jugar = false;
    // Variable que indica la primera vez que se hace click en jugar
    boolean inicio = true;
    // Variables para pintar imagen en buffer
    Image bufferImage;
    Graphics bufferGraphics;    

    /**
     * Creates new form GameOfLifeFrame
     */
    public GameOfLifeFrame() {
        initComponents();
        bufferImage = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        bufferGraphics = bufferImage.getGraphics();
        Timer tiempo = new Timer();
        // Lo que se realizara con el tiempo
        TimerTask tarea = new TimerTask(){
            public void run(){
                // Si el juego no está pausado
                if(jugar){
                   for (int i = 0; i < dimensiones; i++) {
                       for (int j = 0; j < dimensiones; j++) {
                           celdasSiguientes[i][j] = siguienteGeneracion(i,j);
                       }
                   }
                   // Es necesario pasar los valores de celdasSiguientes a
                   // celdasActuales
                   for (int i = 0; i < dimensiones; i++) {
                       for (int j = 0; j < dimensiones; j++) {
                           celdasActuales[i][j] = celdasSiguientes[i][j];
                       }
                   }
                   dibujar();
                }
            }
        };
        // La tarea se realizara una vez cada 100 milisegundos
        tiempo.scheduleAtFixedRate(tarea, 0, 100);
        dibujar();
    }
    
    // regresa el valor de la celda para la siguiente generacion
    private boolean siguienteGeneracion(int i, int j) {
        boolean estadoActual = celdasActuales[i][j];
        int sum = contarVecinosVivos(i,j);
        // Si la celda tiene 3 vecinos vivos, nace o sigue con vida
        if(sum == 3) {
            return true;
        // Si celda esta viva y tiene 2 vecinos vivos, sigue con vida
        }else if(estadoActual && sum == 2) {
            return true;
        // En cualquier otra ocasion, esta "muerta" la celda
        }else {
            return false;
        }
    }
    
    // Revisa los vecinos de la celda actual y regresa el numero de 
    // vecinos vivos. 
    public int contarVecinosVivos(int i, int j){
        int vecinos = 0;
        for (int x = -1; x < 2; x++){
            for(int y = -1; y < 2; y++){
                // Con el modulo podemos tomar una celda en la orilla y tomar 
                // como sus vecinas las celdas de la orilla opuesta
                int vecinoX = (dimensiones+i+x)%dimensiones;
                int vecinoY = (dimensiones+j+y)%dimensiones;
                if(celdasActuales[vecinoX][vecinoY]){
                    vecinos++;
                }
            }
        }
        if(celdasActuales[i][j]){
            vecinos--;
        }
        return vecinos;
    }
    
    /**
     * Metodo para dibujar en buffer y pasarlo al panel
     */
    private void dibujar(){
        // Se obtiene el color de fondo del panel
        bufferGraphics.setColor(jPanel1.getBackground());
        bufferGraphics.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
        //Dibujar celdas "vivas"
        dibujarCeldasVivas();
        //Dibujar el resto de la cuadricula
        dibujarCuadricula();
        //La imagen en el buffer se dibuja en el panel
        jPanel1.getGraphics().drawImage(bufferImage,0,0,jPanel1);
    }
    
    private void dibujarCeldasVivas(){
        bufferGraphics.setColor(Color.red);
        for(int i = 0; i< dimensiones; i++){
            for(int j = 0; j< dimensiones; j++){
                //Si la celda esta viva
                if(celdasActuales[i][j]){
                    // se obtienen sus coordenadas
                    int x = i * jPanel1.getWidth() / dimensiones;
                    int y = j * jPanel1.getHeight() / dimensiones;
                    // se dibuja un cuadro en esas coordenadas en rojo
                    bufferGraphics.fillRect(x, y, jPanel1.getWidth()/dimensiones, jPanel1.getHeight()/dimensiones);
                }
            }
        }
    }

    private void dibujarCuadricula(){
        bufferGraphics.setColor(Color.black);
        // Lineas verticales
        for (int i = 1; i < dimensiones;i++){
            int x = i * jPanel1.getWidth() / dimensiones;
            bufferGraphics.drawLine(x, 0, x, jPanel1.getHeight());
        }
        // Lineas horizontales
        for (int i = 1; i < dimensiones;i++){
            int y = i * jPanel1.getWidth() / dimensiones;
            bufferGraphics.drawLine(0, y, jPanel1.getWidth(),y);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Game Of Life");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 102, 255));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setName("GameOfLifePanel"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Aleatorio");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Borrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Equis");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(35, 35, 35)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(35, 35, 35)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        borrarGeneracion();
        jugar = false;
        jButton1.setText("Jugar");
        dibujar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(inicio){
            generacionAleatoria();
            dibujar();
            jugar = true;
            inicio = false;
            jButton1.setText("Pausar");
        }else{
            jugar = !jugar;
            if(jugar){
                jButton1.setText("Pausar");
            }else{
                jButton1.setText("Jugar");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        generacionAleatoria();
        inicio = false;
        dibujar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        //Obtener la celda sobre la cual se hace click
        int x = evt.getX() * dimensiones / jPanel1.getWidth();
        int y = evt.getY() * dimensiones / jPanel1.getHeight();
        //Se cambia el valor de la celda
        celdasActuales[x][y] = !celdasActuales[x][y];
        inicio = false;
        dibujar();
    }//GEN-LAST:event_jPanel1MouseDragged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        formaUno();
        inicio = false;
        dibujar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        //Obtener la celda sobre la cual se hace click
        int x = evt.getX() * dimensiones / jPanel1.getWidth();
        int y = evt.getY() * dimensiones / jPanel1.getHeight();
        //Se cambia el valor de la celda
        celdasActuales[x][y] = !celdasActuales[x][y];
        inicio = false;
        dibujar();
    }//GEN-LAST:event_jPanel1MouseClicked

    //Genera una generacion de celdas aleatoria
    private void generacionAleatoria(){
        Random random = new Random();
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                celdasActuales[i][j] = random.nextBoolean();
            }
        }
    }
    
    //Genera una equis de celdas vivas en la cuadricula
    private void formaUno(){
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                if(i == j){
                    celdasActuales[i][j] = true;
                    celdasActuales[dimensiones-1-i][j] = true;
                }
            }
        }
    }
        
    // Pone todas las celdas en estado "muerta"
    private void borrarGeneracion(){
        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                celdasActuales[i][j] = false;
            }
        }
    }   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOfLifeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLifeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
