//package com.kodilla.drinks_backend.mapper;
//
//import com.kodilla.drinks_backend.domain.drink.Drink;
//import com.kodilla.drinks_backend.domain.drink.DrinkDto;
//import com.kodilla.drinks_backend.domain.drink.DrinkDto_Create;
//import com.kodilla.drinks_backend.domain.drink.DrinkDto_Update;
//import com.kodilla.drinks_backend.domain.ingredients.Ingredient;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class DrinkMapperTest {
//    @InjectMocks
//    private DrinkMapper drinkMapper;
//    @Test
//    public void mapToDrinkTest() {
//        //Given
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient());
//        DrinkDto drinkDto = new DrinkDto(1L,"TU","TD","TR","I1, I2, I3", LocalDate.now());
//        //When
//        Drink drink = drinkMapper.mapToDrink(drinkDto);
//        //Then
//        assertEquals(drink.getId(), drinkDto.getId());
//        assertEquals(drink.getUsername(), drinkDto.getUsername());
//        assertEquals(drink.getDrinkName(), drinkDto.getDrinkName());
//        assertEquals(drink.getRecipe(), drinkDto.getRecipe());
//        assertEquals(drink.getIngredients().size(),3);
//    }
//    @Test
//    public void mapToDrinkDtoTest() {
//        //Given
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient());
//        Drink drink = new Drink(1L,"TU","TD","TR",ingredients);
//        //When
//        DrinkDto drinkDto = drinkMapper.mapToDrinkDto(drink);
//        //Then
//        assertEquals(drink.getId(), drinkDto.getId());
//        assertEquals(drink.getUsername(), drinkDto.getUsername());
//        assertEquals(drink.getDrinkName(), drinkDto.getDrinkName());
//        assertEquals(drink.getRecipe(), drinkDto.getRecipe());
//        assertEquals(drink.getIngredients().size(), 1);
//    }
//    @Test
//    public void mapToDrinkDtoListTest() {
//        //Given
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredients.add(new Ingredient());
//        Drink drink1 = new Drink(0L,"TU","TD","TR",ingredients);
//        Drink drink2 = new Drink(1L,"TU","TD","TR",ingredients);
//        List<Drink> drinks = new ArrayList<>();
//        drinks.add(drink1);
//        drinks.add(drink2);
//        //When
//        List<DrinkDto> drinkDtos = drinkMapper.mapToDrinkDtoList(drinks);
//        //Then
//        assertEquals(drinks.get(0).getId(),drinkDtos.get(0).getId());
//        assertEquals(drinks.get(0).getUsername(),drinkDtos.get(0).getUsername());
//        assertEquals(drinks.get(0).getDrinkName(),drinkDtos.get(0).getDrinkName());
//        assertEquals(drinks.get(0).getRecipe(),drinkDtos.get(0).getRecipe());
//
//        assertEquals(drinks.get(1).getId(),drinkDtos.get(1).getId());
//        assertEquals(drinks.get(1).getUsername(),drinkDtos.get(1).getUsername());
//        assertEquals(drinks.get(1).getDrinkName(),drinkDtos.get(1).getDrinkName());
//        assertEquals(drinks.get(1).getRecipe(),drinkDtos.get(1).getRecipe());
//
//        assertEquals(drinks.size(),drinkDtos.size());
//    }
//    @Test
//    public void mapToDrinkCreationTest() {
//        //Given
//        DrinkDto_Create drinkDto_create = new DrinkDto_Create("TU","TD","TR","1, 2, 3");
//        //When
//        Drink drink = drinkMapper.mapToDrink_Create(drinkDto_create);
//        //Then
//        assertEquals(drink.getUsername(), drinkDto_create.getUsername());
//        assertEquals(drink.getDrinkName(), drinkDto_create.getDrinkName());
//        assertEquals(drink.getRecipe(), drinkDto_create.getRecipe());
//        assertEquals(drink.getIngredients().size(),3);
//    }
//    @Test
//    public void mapToDrinkUpdateTest() {
//        //Given
//        DrinkDto_Update drinkDto_update = new DrinkDto_Update(1L,"TU","TD","TR","i1,i2,i3");
//        //When
//        Drink drink = drinkMapper.mapToDrink_Update(drinkDto_update);
//        //Then
//        assertEquals(drink.getId(), drinkDto_update.getId());
//        assertEquals(drink.getUsername(), drinkDto_update.getUsername());
//        assertEquals(drink.getDrinkName(), drinkDto_update.getDrinkName());
//        assertEquals(drink.getRecipe(), drinkDto_update.getRecipe());
//        assertEquals(drink.getIngredients().size(),3);
//        assertEquals(drink.getIngredients().get(0).getDescription(), "I1");
//    }
//}
