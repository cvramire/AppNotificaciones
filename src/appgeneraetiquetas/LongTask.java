/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgeneraetiquetas;

import static appgeneraetiquetas.sendsms.enviar;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 *
 * @author Lucas Monce
 */
class LongTask {

    private int lengthOfTask;
    private int current = 0;
    private boolean done = false;
    private boolean canceled = false;
    private String statMessage;

    public LongTask() {
        //Compute length of task...
        //In a real program, this would figure out
        //the number of bytes to read or whatever.
        lengthOfTask = 1000;
    }

    public void setLongTask(int valor) {
        System.out.println("Longitud de Tarea :" + valor);
        lengthOfTask = valor;
    }

    /**
     * Called from ProgressBarDemo to start the task.
     */
    public void go(final File f) {
        final SwingWorker worker = new SwingWorker() {
            public Object construct() {
                current = 0;
                done = false;
                canceled = false;
                statMessage = null;
                return new ActualTask(f);
            }
        };
        worker.start();
    }

    /**
     * Called from ProgressBarDemo to find out how much work needs to be done.
     */
    public int getLengthOfTask() {
        return lengthOfTask;
    }

    /**
     * Called from ProgressBarDemo to find out how much has been done.
     */
    public int getCurrent() {
        return current;
    }

    public void stop() {
        canceled = true;
        statMessage = null;
    }

    /**
     * Called from ProgressBarDemo to find out if the task has completed.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Returns the most recent status message, or null if there is no current
     * status message.
     */
    public String getMessage() {
        return statMessage;
    }

    /**
     * The actual long running task. This runs in a SwingWorker thread.
     */
    class ActualTask {

        ActualTask(File f) {
            try {
                //Fake a long task,
                //making a random amount of progress every second.
                String[] campos= new String[6];
                // leer archivo y sacar arreglos
                List cellDataList = new ArrayList();
                cellDataList = lectura.obtener(f);
                while (!canceled && !done && current < cellDataList.size()) {
                    try {
                        Thread.sleep(1000); //sleep for a second
                        List cellTempList = (List) cellDataList.get(current);
                        for (int j = 0; j < cellTempList.size(); j++) {
                            XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                            String stringCellValue = hssfCell.toString();
                            //System.out.print(stringCellValue + " ");
                            campos[j] = stringCellValue;
                        }
                        System.out.println(campos[5]+" "+campos[4]);
                        enviar(campos[5], campos[4]);
                        current++;
                        if (current >= lengthOfTask) {
                            done = true;
                            current = lengthOfTask;
                        }
                        statMessage = "Completado " + current
                                + " de " + lengthOfTask + ".";

                    } catch (InterruptedException e) {
                        System.out.println("ActualTask interrupted");
                    }
                }
                JOptionPane.showMessageDialog(null, "Envio de notificaciones Terminado!");
            } catch (ParseException ex) {
                Logger.getLogger(LongTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
