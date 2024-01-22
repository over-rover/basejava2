package webapp.storage;

import maintest.MainResumeTestData;

public class StrategyMain {
    public static void main(String[] args) {
        //Создаем объект, реализующий Storage.
        Storage storage = new ArrayStorage();
        //Создаем объект "промежуточного" класса,
        StrategyObject strategyObject = new StrategyObject(storage);

        //В результате имеем возможность вызвать любой метод Storage
        strategyObject.save(MainResumeTestData.autoFillContent("name10", "uuid10"));

        //В то же время это можно сделать так, если обеспечить видимость поля.
        strategyObject.storage.save(MainResumeTestData.autoFillContent("name10", "uuid10"));

        /*
        * В "промежуточном" классе
        * 1. храним ссылку на объект-реализатор интерфейса.
        * 2. дублируем нужные методы интерфейса. Можем проигнорировать ненужные.
        * Если я правильно понял паттерн, то смысл в нем есть только тогда, когда нужно проигнорировать какой-либо
        * метод интерфейса.
        * сразу приходит на ум, что CRUD - доступно для любого уровня доступа, а
        * "технологические методы" (size, getAll, clear) - для админов.
        * Если всё не так, значит я не правильно понял паттерн и требуются разъяснения.
        */
    }
}