package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class   MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("BMW", 677);
      Car car2 = new Car("Mercedes", 144);
      Car car3 = new Car("Audi", 544);

      User userAbdul = new User("Абдуль", "Абдулов", "abd@mail.ru");
      userAbdul.setCar(car1);

      User userAnna = new User("Анна", "Аннова", "anna@mail.ru");
      userAnna.setCar(car2);

      User userSergey = new User("Сергей", "Симонов", "som@mail.ru");
      userSergey.setCar(car3);

      User userTolyan = new User("Анатолий", "Маркин", "tolyan@mail.ru");

      userService.add(userAbdul);
      userService.add(userAnna);
      userService.add(userSergey);
      userService.add(userTolyan);

      System.out.println("\nВсе пользователи после добавления:");
      List<User> users = userService.listUsers();

      for (User user : users) {
         System.out.println("Id       = " + user.getId());
         System.out.println("Имя      = " + user.getFirstName());
         System.out.println("Фамилия  = " + user.getLastName());
         System.out.println("Email    = " + user.getEmail());

         Car car = user.getCar();
         if (car != null) {
            System.out.println("Машина   = " + car.getModel() + " (series " + car.getSeries() + ")");
         } else {
            System.out.println("Машина   = отсутствует");
         }
         System.out.println("-------------------");
      }

      context.close();
   }
}
