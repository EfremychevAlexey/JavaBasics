package org.example;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;


    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> regUsers;

    private final static String KEY = "REGISTERED_USERS";

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        regUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    // Фиксирует посещение пользователем страницы
    void logPageVisit(User user) {
        //ZADD ONLINE_USERS
       regUsers.add(user.registrationTime, "" + user.getName() + "");
    }

    public ArrayList<String> listUsers() {;
        Iterable<String> usersIterable = rKeys.getKeys();
        ArrayList<String> list = new ArrayList<>();
        regUsers.forEach(list::add);
        return list;
    }

}
