package com.kodilla.drinks_backend.email_services;

import com.kodilla.drinks_backend.config.AdminConfig;
import com.kodilla.drinks_backend.domain.comment.Comment;
import com.kodilla.drinks_backend.domain.drink.Drink;
import com.kodilla.drinks_backend.domain.comment.CommentDao;
import com.kodilla.drinks_backend.domain.drink.DrinkDao;
import com.kodilla.drinks_backend.recipePuppyAPI.domain.ProposedIngredients;
import com.kodilla.drinks_backend.recipePuppyAPI.service.RPService;
import com.kodilla.drinks_backend.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmailScheduler {

    private static final String SUBJECT_ONCE_A_DAY = "Drinks Application: Once a day email";
    private static final String SUBJECT_AUTO_RATE = "Drinks Application: One of drinks is highly rated";
    private static final String SUBJECT_PROPOSED_iNGREDIENT = "Drinks Application: Proposed Ingredient voted to create drink";

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DrinkDao drinkDao;
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private RPService RPService;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendOnceADayInformationEmail() {
        long size = drinkDao.count();
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_ONCE_A_DAY,
                adminConfig.getAdminName() + " currently in database you got: " + size + " drink recipes")
        );
    }

    public void sendEmailAboutIngredientProposedToCreateDrink(Long id) {
        ProposedIngredients proposedIngredients = RPService.getIngredientToPropose(id);
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT_PROPOSED_iNGREDIENT,
                adminConfig.getAdminName() + " Ingredient: " + proposedIngredients.getDescription() + " has reached "
                        + proposedIngredients.getVotes() + " votes. Its time to create new drink!")
        );
    }


    public void sendAutoInformation(Long drinkId) {
        //Sprawdzam, czy kiedyś został już wysłany
        Drink drink = drinkService.getDrink(drinkId);
        if (!drink.isSend()) {
            List<Comment> filteredComments = commentDao.findAll().stream()
                    .filter(c -> c.getDrink().getId().equals(drinkId))
                    .collect(Collectors.toList());
            int size = filteredComments.size();

            if (size > 5) {
                int sumOfRates = 0;
                for (int i = 0; i < size; i++) {
                    sumOfRates = sumOfRates + filteredComments.get(i).getRate();
                }
                double meanRate = sumOfRates/size;
                if (meanRate >= 7) {
                    //Skoro spełnił wszystkie kryteria to można zupdatować drinka i wywołać metodę wysłania tego drinka dla siebie na zewnętrzny serwis
                    drink.setSend(true);
                    drinkService.updateDrink(drink);

                    // ---> Wywołanie metody która to zrobi

                    String drinkName = drink.getDrinkName();

                    emailService.send(new Mail(
                            adminConfig.getAdminMail(),
                            SUBJECT_AUTO_RATE,
                            adminConfig.getAdminName() + "! Drink: " + drinkName + " reached " + size + " comments and mean rate of" + meanRate + ". It's popular!!! Lets try it out:)"
                    ));
                }
            }
        }
    }
}
