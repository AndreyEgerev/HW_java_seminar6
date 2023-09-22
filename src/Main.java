//Реализуйте структуру телефонной книги с помощью HashMap.
//Программа также должна учитывать, что во входной структуре будут повторяющиеся имена с разными телефонами,
//их необходимо считать, как одного человека с разными телефонами.
//Вывод должен быть отсортирован по убыванию числа телефонов.
//Используется HashSet для исключения дублирования номеров телефонов у одного человека
import java.util.*;
import java.util.Comparator;



/** Описание класса телефонного справочника
 */
class PhoneBook {
    static Map<String, HashSet<String>> phoneBook = new HashMap<>();

//    public PhoneBook(){
//        phoneBook = new HashMap<String, LinkedList<String>>();
//    }

    /** Метод добавления записи в экземпляр телефонной книги
     * @param name Имя абонента. Используется в качестве ключа
     * @param phoneNumber номер телефона
     * */
    public void addRecord (String name, String phoneNumber){
        HashSet<String> numbers = new HashSet<>();
        if (!phoneBook.containsKey(name)){
            numbers.add(phoneNumber);
            phoneBook.put(name,numbers);
        }
        else {
//            numbers = phoneBook.getOrDefault(name, numbers);
//            numbers.add(phoneNumber);
//            phoneBook.put(name, numbers);
            phoneBook.get(name).add(phoneNumber);
        }

    }

    @Override
    public String toString() {
        return phoneBook.entrySet().toString();
    }

    /**Метод вывода отсортированной телефонной книги в консоль
     * */
    public void displayBook() {
        //Сортировка в новом списке
        ArrayList<Map.Entry<String, HashSet<String>>> records = new ArrayList<>(phoneBook.entrySet());
        records.sort(new Comparator<Map.Entry<String, HashSet<String>>>() {
            @Override
            public int compare(Map.Entry<String, HashSet<String>> o1, Map.Entry<String, HashSet<String>> o2) {
                return o2.getValue().size()-o1.getValue().size();
            }
        });
        //Вывод на экран
        for (Map.Entry<String, HashSet<String>> elment :
                records) {
            System.out.print(elment.getKey()+ "\t");
            for (String phone:
                    elment.getValue()) {
                System.out.print(phone + " ");
            }
            System.out.println();
        }

//        for (Map.Entry<String,HashSet<String>> element:
//                phoneBook.entrySet()) {
//            System.out.printf("%s: \t",element.getKey());
//            for (String phone:
//                    element.getValue()) {
//                System.out.print(phone + " ");
//            }
//            Collections.sort(element.getValue(), Comparator.comparing((HashSet<String>set) -> set.size()));
//            //System.out.printf("%d", Collections.sort(element.getValue(), (Comparator.comparing((Object t) -> HashSet.size(t)))));
//            System.out.println("");
//
//        }

    }
}

public class Main {

    /** Ввод данных в экземпляр телефонного справочника
     * @param phoneBook экземпляр телефонного справочника для ввода данных */
    public static void InputRecord(PhoneBook phoneBook){
        System.out.println("Выберети действие:\nЧтобы ввести имена и телефоны в ручном режиме нажмите \'1\' \nили воспольуйтесь тестовым справочником нажав любую клавишу");
        Scanner recordIn = new Scanner(System.in);
        String console = recordIn.nextLine();
        if (console.equalsIgnoreCase("1")) {
            boolean inputFlag = true;
            while (inputFlag) {
                String[] contactIn;
                System.out.println("Введите Имя и телефон через \',\' и нажмите ENTER \nЧтобы закончить, введите end");
                console = recordIn.nextLine();
                if (console.equalsIgnoreCase("end")) {
                    inputFlag = false;
                }
                if (inputFlag) {
                    contactIn =console.split(",");
                    phoneBook.addRecord(contactIn[0],contactIn[1]);
                    System.out.println("Запись добавлена");
                }
            }
        }
        else {
            System.out.println("Вводим тестовый справочник");
            phoneBook.addRecord("Андрей", "89051000212");
            phoneBook.addRecord("Андрей", "89051000515");//дублирование ключа
            phoneBook.addRecord("Федор", "88001001010");
            phoneBook.addRecord("Федор", "88001001300");//дублирование ключа
            phoneBook.addRecord("Андрей", "89054613010");//дублирование ключа
            phoneBook.addRecord("Влад", "89058000212");
            phoneBook.addRecord("Ольга", "88008008010");
            phoneBook.addRecord("Влад", "89058000212");//дублирование ключа, дублирование номера
        }
    }

    public static void main(String[] args) {
        System.out.println("Телефонный справочник");
        PhoneBook phoneBook = new PhoneBook();
        InputRecord(phoneBook);

        System.out.println("Вывод отсортированного справочника:");
        phoneBook.displayBook();

        System.out.println("Вывод телефонного справочника:\n" + phoneBook);
    }
}