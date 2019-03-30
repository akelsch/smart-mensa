package de.stl.saar.internetentw1;

import de.stl.saar.internetentw1.model.Category;
import de.stl.saar.internetentw1.model.Dish;
import de.stl.saar.internetentw1.model.Role;
import de.stl.saar.internetentw1.model.User;
import de.stl.saar.internetentw1.repository.DishRepository;
import de.stl.saar.internetentw1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static java.util.Arrays.asList;

@SpringBootApplication
public class SmartMensaApplication {

    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    @Autowired
    public SmartMensaApplication(DishRepository dishRepository, UserRepository userRepository) {
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SmartMensaApplication.class, args);
    }

    /**
     * Diese Bean initialisiert uns die Datenbank. Dazu verwenden wir eine
     * In-Memory-Datenbank (H2) und Spring Data Repositories.
     *
     * @return Ein {@link CommandLineRunner}, der direkt nach dem Start der
     * Applikation ausgeführt wird
     */
    @Bean
    public CommandLineRunner init() {
        return args -> {
            // User
            User user1 = new User("colbertz", "1234", "colbertz@htwsaar.de", Role.ADMIN);
            User user2 = new User("wpy", "qwertz", "wpy@htwsaar.de", Role.USER);
            User user3 = new User("api", "5678", "api@htwsaar.de", Role.USER);

            // Dish
            Dish dish1 = new Dish("Baumkuchen", 2.0, Category.DESSERT, "baumkuchen");
            Dish dish2 = new Dish("Creme Brulee", 2.5, Category.DESSERT, "cremeBrulee");
            Dish dish3 = new Dish("Flammkuchen", 7.5, Category.MAIN_DISH, "flammkuchen");
            Dish dish4 = new Dish("Grießnockerl-Suppe", 4, Category.SOUP, "griessnockerlsuppe");
            Dish dish5 = new Dish("Pudding", 2, Category.DESSERT, "pudding");
            Dish dish6 = new Dish("Rindfleischsuppe", 3.5, Category.SOUP, "rindfleischsuppe");
            Dish dish7 = new Dish("Rumänischer Salat", 3.5, Category.SALAD, "rumaenischerSalat");
            Dish dish8 = new Dish("Einfach nur Salat", 3.5, Category.SALAD, "salat");
            Dish dish9 = new Dish("Wiener Schnitzel", 7.0, Category.MAIN_DISH, "schnitzel");
            Dish dish10 = new Dish("Tomate-Mozzarella", 4.0, Category.SALAD, "tomateMozzarella");

            userRepository.saveAll(asList(user1, user2, user3));
            dishRepository.saveAll(asList(dish1, dish2, dish3, dish4, dish5, dish6, dish7, dish8, dish9, dish10));
        };
    }
}
