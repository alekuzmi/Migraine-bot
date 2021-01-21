package org.MigraineBot.tg;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MigraineBot extends TelegramLongPollingBot {

    private static volatile MigraineBot instance;

    public static MigraineBot getInstance() {
        MigraineBot localInstance = instance;
        if (localInstance == null) {
            synchronized (MigraineBot.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MigraineBot();
                }
            }
        }
        return localInstance;
    }

    Database stat;

    public MigraineBot() {
        this.stat = new Database();
    }


    @Override
    public String getBotUsername() {
        return System.getenv("MIGRAINE_BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("MIGRAINE_BOT_TOKEN");

    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();
        stat.setIdName(message.getFrom().getId(), message.getFrom().getUserName());
        stat.setChatId(update.getMessage().getChatId());

        if (message == null || !message.hasText()) {
            return;
        }

        if (message.getText().equals("/help")) {
            sendMsg(message, "Nope");

        }
        else {
            sendMsg(message, "Hy");
        }

    }


    protected void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Long ChatId, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(String.valueOf(ChatId));
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    public synchronized void sendMessageToAllChat (){
        Set<Long> id = Database.getAllChatId();
        for (Long text : id)
        {
            sendMsg(text, "Hello");
        }
    }


}

