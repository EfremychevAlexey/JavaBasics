package org.example;

import java.util.ArrayList;

public class Main {

    public static void userRegistration(RedisStorage redisStorage) {
        for (int i = 1; i <= 20; i++) {
            redisStorage.logPageVisit(new User(i));

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void showUsers(RedisStorage redisStorage) {
        int periodPromotionInSearch = 0;
        int count = 0;
        while (true) {
            ArrayList<String> list = redisStorage.listUsers();
            if (count == list.size()) {
                count = 0;
                continue;
            }
            if (count < list.size()) {
                System.out.println("— На главной странице показываем пользователя "
                        + list.get(count++));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (++periodPromotionInSearch % 10 == 0) {
                int id = (int) (Math.random() * list.size());
                System.out.println("— Пользователь " + id + " оплатил платную услугу ");
                System.out.println("— На главной странице показываем пользователя "
                        + list.get(id - 1));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        RedisStorage redis = new RedisStorage();
        redis.init();

        new Thread(() -> {
            userRegistration(redis);
        }).start();

        new Thread(() -> {
            showUsers(redis);
        }).start();
    }

}