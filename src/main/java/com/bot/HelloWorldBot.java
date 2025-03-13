package com.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class HelloWorldBot extends TelegramLongPollingBot {
    private boolean inSecondMenu = false;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start" -> {
                    inSecondMenu = false;
                    sendMenu(chatId);
                }
                case "Далі" -> {
                    inSecondMenu = true;
                    sendMenu(chatId);
                }
                case "Назад" -> {
                    inSecondMenu = false;
                    sendMenu(chatId);
                }
                case "Кнопка 1", "Кнопка 2" -> sendMessage(chatId, messageText);
            }
        }
    }

    @SneakyThrows
    private void sendMenu(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Виберіть кнопку:");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add("Кнопка 1");
        row1.add("Кнопка 2");
        keyboard.add(row1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(inSecondMenu ? "Назад" : "Далі");
        keyboard.add(row2);

        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
        execute(message);
    }

    @SneakyThrows
    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        execute(message);
    }

    @Override
    public String getBotUsername() {
        return "andrii_storchak_bot";
    }

    @Override
    public String getBotToken() {
        return "7336125887:AAEuAJqZHg2waV8Jlz-nslVRkzMRedrCEv8";
    }
}
