package org.MigraineBot.tg;

import java.util.*;

class Status {
    Map<Long, Map<Integer, Integer>> statusChat;
    Map<Integer, String> idName;


    public Status() {
        this.statusChat = new HashMap<Long, Map<Integer , Integer>>();
        this.idName = new HashMap<Integer, String>();
    }

    public synchronized void setIdName(int userId, String userName){
        idName.put(userId, userName);
    }

    public synchronized String getCount(long chatId) {
        Map <Integer, Integer> map = statusChat.get(chatId);

        StringBuilder str = new StringBuilder();

            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();

            while (iterator.hasNext())
            {
                //получение «пары» элементов
                Map.Entry<Integer, Integer> pair = iterator.next();
                int key = pair.getKey();
                String keyName = idName.get(key);
                str.append(keyName+" send "); //ключ
                int value = pair.getValue();
                str.append(value+" message \n"); //значение
            }

        return str.toString();
    }

    public synchronized void incCount(long ChatId, int userId) {

        Map <Integer, Integer> statusUser= statusChat.getOrDefault(ChatId, new HashMap<Integer, Integer>(){{put(userId, 0);}});
        int count=statusUser.getOrDefault(userId, 0);
        statusUser.put(userId,count+1);
        statusChat.put(ChatId, statusUser);

    }
    public synchronized ArrayList<Long> getChatsId () {
        ArrayList<Long> str = new ArrayList<Long>();
        Iterator<Map.Entry<Long, Map<Integer, Integer>>> iterator = statusChat.entrySet().iterator();

        while (iterator.hasNext())
        {
            Map.Entry<Long, Map<Integer , Integer>> pair = iterator.next();
            Long key = pair.getKey();
            str.add(key);

        }
        return str;
    }





}
