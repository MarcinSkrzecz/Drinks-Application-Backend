package com.kodilla.drinks_backend.email_services;

import com.kodilla.drinks_backend.config.AdminConfig;
import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import com.kodilla.drinks_backend.domain.RP.proposedIngredients.ProposedIngredients;
import com.kodilla.drinks_backend.service.RPService;
import com.kodilla.drinks_backend.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private static final String SUBJECT_ONCE_A_DAY = "Drinks Application: Once a day email";
    private static final String SUBJECT_HIGH_DRINK_RATING = "Drinks Application: One of drinks is highly rated";
    private static final String SUBJECT_PROPOSED_INGREDIENT = "Drinks Application: Proposed Ingredient voted to create drink";
    private static final String SUBJECT_CONTACT_US_MESSAGE = "Drinks Application: Someone send a message";

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DrinkDao drinkDao;
    @Autowired
    private RPService RPService;
    @Autowired
    private DrinkService drinkService;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendOnceADayInformationEmail() {
        long size = drinkDao.count();
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_ONCE_A_DAY,
                adminConfig.getAdminName() + " currently in database you got: " + size + " drink recipes")
        );
    }

    public void sendEmailAboutHighlyRatedDrink(Long drinkId) {
        Drink drink = drinkService.getDrink(drinkId);
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_HIGH_DRINK_RATING,
                adminConfig.getAdminName() + ", Drink: " + drink.getDrinkName() + " has reached high rating and was send to Trello.")
        );
    }

    public void sendEmailAboutIngredientProposedToCreateDrink(Long id) {
        ProposedIngredients proposedIngredients = RPService.getIngredientToPropose(id);
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_PROPOSED_INGREDIENT,
                adminConfig.getAdminName() + " Ingredient: " + proposedIngredients.getDescription() + " has reached "
                        + proposedIngredients.getVotes() + " votes and was send to Trello. Its time to create new drink!")
        );
    }

    public void sendEmailAboutSomeoneContactingUs(final String title, final String content) {
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_CONTACT_US_MESSAGE,
                adminConfig.getAdminName() + ", Someone has contacted us and was send to Trello. Message title - " +
                title + ". Message content - " + content + ".")
        );
    }
}