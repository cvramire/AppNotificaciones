/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgeneraetiquetas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.marre.SmsSender;
import org.marre.sms.SmsException;
import org.marre.sms.SmsMessage;

/**
 *
 * @author Lucas Monce
 */
public class sendsms {
    public static void enviar(String msg, String reciever) {
        try {
            // Send SMS with clickatell
            SmsSender smsSender = SmsSender.getClickatellSender("cvramire", "Cramire1982", "3372059");
            // Number of sender (not supported on all transports)
            String sender = "59385043947";
            try {
                smsSender.connect();
                String msgids = smsSender.sendTextSms(msg, reciever);
                smsSender.disconnect();
                System.out.print("...Procesado..."+msgids);
            } catch (IOException ex) {
                System.out.print("...Error..."+ex);
                Logger.getLogger(sendsms.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SmsException ex) {
            System.out.println(ex);
            Logger.getLogger(sendsms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
