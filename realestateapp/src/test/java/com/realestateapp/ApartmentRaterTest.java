package com.realestateapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ApartmentRaterTest {
	@ParameterizedTest
	@CsvSource(value = {"72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2"})
	void should_ReturnCorrectRating_When_CorrectApartment(Double area, Double price, int rating) {
		Apartment apartment = new Apartment(area, new BigDecimal(price));
		int expected = rating;
		int actual = ApartmentRater.rateApartment(apartment);
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_ReturnErrorValue_When_IncorrectApartment() {
		Apartment apartment = new Apartment(0.0, new BigDecimal(350000.0));
		int expected = -1;
		int actual = ApartmentRater.rateApartment(apartment);
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_CalculateAverageRating_When_CorrectApartmentList() {
		List<Apartment> apartments = new ArrayList<>();
		apartments.add(new Apartment(72.0, new BigDecimal(250000.0)));
		apartments.add(new Apartment(48.0, new BigDecimal(350000.0)));
		apartments.add(new Apartment(30.0, new BigDecimal(600000.0)));
		float expected = 1.0f;
		float actual = (float) ApartmentRater.calculateAverageRating(apartments);
		assertEquals(expected, actual);		
	}
	
	@Test
	void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
		List<Apartment> apartments = new ArrayList<>();
		Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
		assertThrows(RuntimeException.class, executable);
	}
}


