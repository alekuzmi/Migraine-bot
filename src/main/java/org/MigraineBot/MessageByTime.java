package org.MigraineBot;
import org.MigraineBot.tg.MigraineBot;
import org.MigraineBot.tg.Database;

import java.util.*;

public class MessageByTime extends TimerTask {


    Calendar  calendarSend ;

    public MessageByTime() {
        calendarSend = new GregorianCalendar();
        calendarSend.add(Calendar.DAY_OF_YEAR, -1);

    }

    @Override
    public void run() {

        Calendar currentCalendar = new GregorianCalendar();

        if (currentCalendar.get(Calendar.DAY_OF_MONTH) > calendarSend.get(Calendar.DAY_OF_MONTH) &
                currentCalendar.get(Calendar.HOUR_OF_DAY) == 23 &
                currentCalendar.get(Calendar.MINUTE)== 01 ){

            MigraineBot status = MigraineBot.getInstance();

            status.sendMessageToAllChat();

            calendarSend=Calendar.getInstance();

        }
    }
}